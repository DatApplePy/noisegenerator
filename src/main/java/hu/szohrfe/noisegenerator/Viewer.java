package hu.szohrfe.noisegenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Viewer extends JPanel {
    private final BufferedImage image;
    private final PerlinNoise perlin;

    public Viewer() {
        super();
        setPreferredSize(new Dimension(2000, 1000));
        this.image = new BufferedImage(1000, 1000,
                BufferedImage.TYPE_INT_RGB);
        perlin = new PerlinNoise(512);
    }

    private void generateNoiseImage() {
        int octaves = 8;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                double freq = 0.05;
                double amp = 1.0;
                double res = 0.0;

//                for (int o = 0; o < octaves; o++) {
//                    double perlinValue = amp * perlin.noise2D(i * freq, j * freq);
//                    res += perlinValue;
//                    amp *= 0.5;
//                    freq *= 2.0;
//                }
                res = perlin.noise2D(i * freq, j * freq);

                int colorValue = (int) perlin.map(res, -1.0, 1.0, 0, 255);
                Color color = new Color(colorValue, colorValue, colorValue);
                image.setRGB(i, j, color.getRGB());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        generateNoiseImage();

        //image.getScaledInstance();
        g.drawImage(image, 0, 0, null);
        g.drawImage(image, 1000, 0, null);
    }
}
