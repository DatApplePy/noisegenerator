package hu.szohrfe.noisegenerator.styles;

import hu.szohrfe.noisegenerator.util.ColorPalette;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class MinimalNeonLabelUI extends BasicLabelUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        c.setBackground(ColorPalette.DARK_GRAY);
        c.setForeground(ColorPalette.LIGHT_GRAY);
    }
}