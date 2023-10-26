package hu.szohrfe.noisegenerator.styles;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;

public class MinimalNeonTextFieldUI extends BasicTextFieldUI {
    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(g);
        JComponent c = getComponent();
    }
}
