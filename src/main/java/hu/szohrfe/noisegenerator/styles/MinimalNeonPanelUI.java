package hu.szohrfe.noisegenerator.styles;

import hu.szohrfe.noisegenerator.util.ColorPalette;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;

public class MinimalNeonPanelUI extends BasicPanelUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(ColorPalette.DARK_GRAY);
    }
}
