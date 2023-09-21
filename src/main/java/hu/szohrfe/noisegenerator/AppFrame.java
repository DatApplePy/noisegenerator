package hu.szohrfe.noisegenerator;

import javax.swing.*;

public class AppFrame extends JFrame {

    public AppFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLayout(new SpringLayout());

        Viewer viewer = new Viewer();
        add(viewer);
        viewer.revalidate();
        viewer.repaint();

        pack();
        setVisible(true);
    }
}
