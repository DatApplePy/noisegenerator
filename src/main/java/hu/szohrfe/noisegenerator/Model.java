package hu.szohrfe.noisegenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Getter
@Setter
@AllArgsConstructor
public class Model extends Observable {

    private final ThreadPoolExecutor executor;
    private BufferedImage image;
    private PerlinNoise perlin;
    private double frequency;
    private double amplitude;
    private double thresholdMin;
    private double thresholdMax;
    private double offset;
    private int octaves;

    private void generateNoiseImage() {
        executor.submit(() -> {
            generateNoiseImageSegment(0, 0, image.getWidth()/2, image.getHeight()/2);
            return null;
        });
        executor.submit(() -> {
            generateNoiseImageSegment(image.getWidth()/2, 0, image.getWidth(), image.getHeight()/2);
            return null;
        });
        executor.submit(() -> {
            generateNoiseImageSegment(0, image.getHeight()/2, image.getWidth()/2, image.getHeight());
            return null;
        });
        executor.submit(() -> {
            generateNoiseImageSegment(image.getWidth()/2, image.getHeight()/2,image.getWidth(), image.getHeight());
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

    public void update() {
        generateNoiseImage();
        notifyObservers();
    }

    @Override
    protected void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(image);
        }
    }
}
