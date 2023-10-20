package hu.szohrfe.noisegenerator.model;

import hu.szohrfe.noisegenerator.observer.Observable;
import hu.szohrfe.noisegenerator.observer.Observer;
import hu.szohrfe.noisegenerator.util.ResettableCountDownLatch;
import lombok.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@Getter
@Setter
public class Model extends Observable {

    private final ThreadPoolExecutor executor;
    private SwingWorker<Void, Void> worker;
    private final ResettableCountDownLatch latch;
    private BufferedImage image;
    private PerlinNoise perlin;
    private double frequency;
    private double amplitude;
    private double thresholdMin;
    private double thresholdMax;
    private double offset;
    private int octaves;

    public Model(ThreadPoolExecutor executor) {
        this.executor = executor;
        this.perlin = new PerlinNoise();
        this.latch = new ResettableCountDownLatch(4);
    }

    private void generateNoiseImage() {
        executor.submit(() -> {
            generateNoiseImageSegment(0, 0, image.getWidth()/2, image.getHeight()/2);
            latch.countDown();
            return null;
        });
        executor.submit(() -> {
            generateNoiseImageSegment(image.getWidth()/2, 0, image.getWidth(), image.getHeight()/2);
            latch.countDown();
            return null;
        });
        executor.submit(() -> {
            generateNoiseImageSegment(0, image.getHeight()/2, image.getWidth()/2, image.getHeight());
            latch.countDown();
            return null;
        });
        executor.submit(() -> {
            generateNoiseImageSegment(image.getWidth()/2, image.getHeight()/2,image.getWidth(), image.getHeight());
            latch.countDown();
            return null;
        });
    }
    private void generateNoiseImageSegment(int startX, int startY, int endX, int endY) {

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                double freq = frequency;
                double amp = amplitude;
                double res = offset;
                for (int o = 0; o < octaves; o++) {
                    double gridWidth = image.getWidth() * freq;
                    double gridHeight = image.getHeight() * freq;
                    int gridStretchedWidth = (int) (image.getWidth() * freq) + 1;
                    int gridStretchedHeight = (int) (image.getHeight() * freq) + 1;
                    double x = perlin.map(i * freq,
                            0, gridWidth,
                            0, gridStretchedWidth);
                    double y = perlin.map(j * freq,
                            0, gridHeight,
                            0, gridStretchedHeight);
                    double perlinValue = amp * perlin.noise2D(x, y,
                            gridStretchedWidth, gridStretchedHeight);
                    freq *= 2;
                    amp *= 0.5;
                    res += perlinValue;
                }
                int colorValue = (int) perlin.map(res, thresholdMin, thresholdMax, 0, 255);
                Color color = new Color(colorValue, colorValue, colorValue);
                image.setRGB(i, j, color.getRGB());
            }
        }
    }

    public void setSeed(long seed) {

    }

    public void update() {
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                generateNoiseImage();
                latch.await();
                return null;
            }

            @Override
            protected void done() {
                latch.reset();
                notifyObservers();
            }
        };

        worker.execute();
    }

    @Override
    protected void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(image);
        }
    }
}
