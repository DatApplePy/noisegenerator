package hu.szohrfe.noisegenerator.controller;

import hu.szohrfe.noisegenerator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Controller extends JPanel {

    private final static int INIT_WIDTH = 500;
    private final static int INIT_HEIGHT = 1000;
    private static final String FLOAT_NUMBER_REGEX = "-?\\d+(\\.\\d*)?";
    private static final String INTEGER_REGEX = "-?\\d+";
    private static final String WHOLE_NUMBER_REGEX = "\\d+";
    private final Model model;

    public Controller(Model model) {
        super();
        this.model = model;
        controllerInit();
    }

    private void controllerInit() {
        setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));

        //Seed Field
        JPanel seedPanel = new JPanel();
        JLabel seedLabel = new JLabel("Seed: ");
        JTextField seedTextField = new JTextField(10);

        seedPanel.add(seedLabel);
        seedPanel.add(seedTextField);
        add(seedPanel);

        //Image size
        JPanel imageSizePanel = new JPanel();
        JLabel imageSizeLabel = new JLabel("Image size: ");
        JTextField imageWidthTextField = new JTextField("512", 10);
        JLabel filler = new JLabel("x");
        JTextField imageHeightTextField = new JTextField("512", 10);

        imageSizePanel.add(imageSizeLabel);
        imageSizePanel.add(imageWidthTextField);
        imageSizePanel.add(filler);
        imageSizePanel.add(imageHeightTextField);
        add(imageSizePanel);

        //Frequency Slider
        JPanel freqPanel = new JPanel();
        JLabel freqLabel = new JLabel("Frequency: ");
        DoubleJSlider freqSlider = new DoubleJSlider(1, 1000, 10, 1000);
        JTextField freqTextField = new JTextField(
                String.valueOf(freqSlider.getScaledValue()), 5);

        freqSlider.addChangeListener(e -> {
            freqTextField.setText(String.valueOf(freqSlider.getScaledValue()));
        });

        freqTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String typedValue = freqTextField.getText();
                if(!typedValue.matches(FLOAT_NUMBER_REGEX)) {
                    return;
                }
                freqSlider.setScaledValue(Double.parseDouble(typedValue));
            }
        });

        freqPanel.add(freqLabel);
        freqPanel.add(freqSlider);
        freqPanel.add(freqTextField);
        add(freqPanel);

        //Amplitude Slider
        JPanel ampPanel = new JPanel();
        JLabel ampLabel = new JLabel("Amplitude: ");
        DoubleJSlider ampSlider = new DoubleJSlider(1, 2000, 1000, 1000);
        JTextField ampTextField = new JTextField(
                String.valueOf(ampSlider.getScaledValue()), 5);

        ampSlider.addChangeListener(e -> {
            ampTextField.setText(String.valueOf(ampSlider.getScaledValue()));
        });

        ampTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String typedValue = ampTextField.getText();
                if(!typedValue.matches(FLOAT_NUMBER_REGEX)) {
                    return;
                }
                ampSlider.setScaledValue(Double.parseDouble(typedValue));
            }
        });

        ampPanel.add(ampLabel);
        ampPanel.add(ampSlider);
        ampPanel.add(ampTextField);
        add(ampPanel);

        //Threshold Minimum Slider
        JPanel thresholdMinPanel = new JPanel();
        JLabel thresholdMinLabel = new JLabel("Min threshold: ");
        DoubleJSlider thresholdMinSlider = new DoubleJSlider(-2000, 0, -1000, 1000);
        JTextField thresholdMinTextField = new JTextField(
                String.valueOf(thresholdMinSlider.getScaledValue()), 5);

        thresholdMinSlider.addChangeListener(e -> {
            thresholdMinTextField.setText(String.valueOf(thresholdMinSlider.getScaledValue()));
        });

        thresholdMinTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String typedValue = thresholdMinTextField.getText();
                if(!typedValue.matches(FLOAT_NUMBER_REGEX)) {
                    return;
                }
                thresholdMinSlider.setScaledValue(Double.parseDouble(typedValue));
            }
        });

        thresholdMinPanel.add(thresholdMinLabel);
        thresholdMinPanel.add(thresholdMinSlider);
        thresholdMinPanel.add(thresholdMinTextField);
        add(thresholdMinPanel);

        //Threshold Maximum Slider
        JPanel thresholdMaxPanel = new JPanel();
        JLabel thresholdMaxLabel = new JLabel("Max threshold: ");
        DoubleJSlider thresholdMaxSlider = new DoubleJSlider(0, 2000, 1000, 1000);
        JTextField thresholdMaxTextField = new JTextField(
                String.valueOf(thresholdMaxSlider.getScaledValue()), 5);

        thresholdMaxSlider.addChangeListener(e -> {
            thresholdMaxTextField.setText(String.valueOf(thresholdMaxSlider.getScaledValue()));
        });

        thresholdMaxTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String typedValue = thresholdMaxTextField.getText();
                if(!typedValue.matches(FLOAT_NUMBER_REGEX)) {
                    return;
                }
                thresholdMaxSlider.setScaledValue(Double.parseDouble(typedValue));
            }
        });

        thresholdMaxPanel.add(thresholdMaxLabel);
        thresholdMaxPanel.add(thresholdMaxSlider);
        thresholdMaxPanel.add(thresholdMaxTextField);
        add(thresholdMaxPanel);

        //Offset Slider
        JPanel offsetPanel = new JPanel();
        JLabel offsetLabel = new JLabel("Offset: ");
        DoubleJSlider offsetSlider = new DoubleJSlider(-1000, 1000, 0, 1000);
        JTextField offsetTextField = new JTextField(
                String.valueOf(offsetSlider.getScaledValue()), 5);

        offsetSlider.addChangeListener(e -> {
            offsetTextField.setText(String.valueOf(offsetSlider.getScaledValue()));
        });

        offsetTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String typedValue = offsetTextField.getText();
                if(!typedValue.matches(FLOAT_NUMBER_REGEX)) {
                    return;
                }
                offsetSlider.setScaledValue(Double.parseDouble(typedValue));
            }
        });

        offsetPanel.add(offsetLabel);
        offsetPanel.add(offsetSlider);
        offsetPanel.add(offsetTextField);
        add(offsetPanel);

        //Octave Slider
        JPanel octavePanel = new JPanel();
        JLabel octaveLabel = new JLabel("Octave: ");
        JSlider octaveSlider = new JSlider(1, 8, 1);
        JLabel octaveSliderValueLabel = new JLabel(String.valueOf(octaveSlider.getValue()));

        octaveSlider.setPaintTicks(true);
        octaveSlider.setPaintTrack(false);
        octaveSlider.setMajorTickSpacing(1);
        octaveSlider.setSnapToTicks(true);

        octaveSlider.addChangeListener(e -> {
            octaveSliderValueLabel.setText(String.valueOf(octaveSlider.getValue()));
        });

        octavePanel.add(octaveLabel);
        octavePanel.add(octaveSlider);
        octavePanel.add(octaveSliderValueLabel);
        add(octavePanel);

        //Generator Button
        JButton generatorButton = new JButton("Generate");
        generatorButton.addActionListener(e -> {
            String seedString = seedTextField.getText();
            if (!seedString.matches(INTEGER_REGEX)) {
                return;
            }
            long newSeed = Long.parseLong(seedString);

            String imageWidthString = imageWidthTextField.getText();
            String imageHeightString = imageHeightTextField.getText();
            if (!(imageWidthString.matches(WHOLE_NUMBER_REGEX) &&
                    imageHeightString.matches(WHOLE_NUMBER_REGEX))) {
                return;
            }
            int newImageWidth = Integer.parseInt(imageWidthString);
            int newImageHeight = Integer.parseInt(imageHeightString);

            model.setSeed(newSeed);
            model.setImage(new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB));
            model.setFrequency(freqSlider.getScaledValue());
            model.setAmplitude(ampSlider.getScaledValue());
            model.setThresholdMin(thresholdMinSlider.getScaledValue());
            model.setThresholdMax(thresholdMaxSlider.getScaledValue());
            model.setOffset(offsetSlider.getScaledValue());
            model.setOctaves(octaveSlider.getValue());
            update();
        });
        add(generatorButton);

        //Layout
        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.NORTH, seedPanel, 5,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, seedPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, imageSizePanel, 5,
                SpringLayout.SOUTH, seedPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, imageSizePanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, freqPanel, 5,
                SpringLayout.SOUTH, imageSizePanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, freqPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, ampPanel, 5,
                SpringLayout.SOUTH, freqPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ampPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, thresholdMinPanel, 5,
                SpringLayout.SOUTH, ampPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, thresholdMinPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, thresholdMaxPanel, 5,
                SpringLayout.SOUTH, thresholdMinPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, thresholdMaxPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, offsetPanel, 5,
                SpringLayout.SOUTH, thresholdMaxPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, offsetPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, octavePanel, 5,
                SpringLayout.SOUTH, offsetPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, octavePanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, generatorButton, 5,
                SpringLayout.SOUTH, octavePanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, generatorButton, 0,
                SpringLayout.HORIZONTAL_CENTER, this);

        setLayout(layout);
    }
    private void update() {
        model.update();
    }
}
