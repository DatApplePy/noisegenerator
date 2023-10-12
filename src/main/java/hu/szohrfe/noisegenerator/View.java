package hu.szohrfe.noisegenerator;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Setter
public class View extends JPanel implements Observer {
    private BufferedImage image;

    public View() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void update(BufferedImage image) {
        setImage(image);
        this.revalidate();
        this.repaint();
    }
}
