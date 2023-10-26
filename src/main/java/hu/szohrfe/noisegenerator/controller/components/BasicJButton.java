package hu.szohrfe.noisegenerator.controller.components;

import javax.swing.*;

public class BasicJButton extends JButton {
    public BasicJButton(String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
    }
}
