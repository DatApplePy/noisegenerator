package hu.szohrfe.noisegenerator;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AppFrame extends JFrame {

    public AppFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Model model = new Model(
                (ThreadPoolExecutor) Executors.newFixedThreadPool(4),
                new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB),
                new PerlinNoise(512),
                0.01, 1, -1, 1, 0, 8
        );
        Controller controller = new Controller(model);

        View view = new View();
        model.addObserver(view);
        add(view);
//        view.revalidate();
//        view.repaint();
        controller.update();

        pack();
        setVisible(true);
    }
}
