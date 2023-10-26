package hu.szohrfe.noisegenerator.styles;

import hu.szohrfe.noisegenerator.util.ColorPalette;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MinimalNeonButtonUI extends BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        c.setBackground(ColorPalette.LIGHT_GRAY);
        c.setForeground(ColorPalette.DARK_GRAY);
    }
}
