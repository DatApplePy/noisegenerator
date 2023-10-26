package hu.szohrfe.noisegenerator.view;

import hu.szohrfe.noisegenerator.controller.Controller;
import hu.szohrfe.noisegenerator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AppFrame extends JFrame {

    public AppFrame() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        View view = new View();
        Model model = new Model((ThreadPoolExecutor) Executors.newFixedThreadPool(4));
        Controller controller = new Controller(model);

        model.addObserver(view);
        add(controller, BorderLayout.WEST);
        add(view, BorderLayout.EAST);

        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
