package hu.szohrfe.noisegenerator.controller.components;

import javax.swing.*;

public class DoubleJSlider extends JSlider {

    private final int scale;

    public DoubleJSlider(int min, int max, int value, int scale) {
        super(min, max, value);
        this.scale = scale;
    }

    public double getScaledValue() {
        return (double) super.getValue() / scale;
    }

    public void setScaledValue(double value) {
        super.setValue((int) (value * scale));
    }
}
