package hu.szohrfe.noisegenerator.styles;

import hu.szohrfe.noisegenerator.util.ColorPalette;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;

public class MinimalNeonTextFieldUI extends BasicTextFieldUI {
    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(g);
        JTextField c = (JTextField) getComponent();
        c.setHorizontalAlignment(JTextField.CENTER);
        c.setBackground(ColorPalette.DARK_GRAY);
        c.setForeground(ColorPalette.LIGHT_GRAY);
        c.setCaretColor(ColorPalette.PINKISH_RED);
        c.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        c.setBorder(new MatteBorder(0,0,2,0,ColorPalette.PINKISH_RED));
    }
}
