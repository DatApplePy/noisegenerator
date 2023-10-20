package hu.szohrfe.noisegenerator.view;

import hu.szohrfe.noisegenerator.observer.Observer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class View extends JPanel implements Observer {
    private BufferedImage image;
    private static final int INIT_WIDTH = 1000;
    private static final int INIT_HEIGHT = 1000;

    public View() {
        super();
        setBackground(new Color(150, 150, 150));
        setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(image != null) {
            int newWidth = image.getWidth();
            int newHeight = image.getHeight();

            if (image.getWidth() > INIT_WIDTH) {
                newWidth = INIT_WIDTH;
                newHeight = (newWidth * image.getHeight()) / image.getWidth();
            }
            if (image.getHeight() > INIT_HEIGHT) {
                newHeight = INIT_HEIGHT;
                newWidth = (newHeight * image.getWidth()) / image.getHeight();
            }


            Image img = image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_DEFAULT);
            g.drawImage(img,
                    getWidth()/2-img.getWidth(null)/2,
                    getHeight()/2-img.getHeight(null)/2,
                    null);
        }
    }

    @Override
    public void update(BufferedImage image) {
        setImage(image);
        repaint();
    }
}
