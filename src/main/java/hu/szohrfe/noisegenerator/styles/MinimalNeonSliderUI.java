package hu.szohrfe.noisegenerator.styles;

import hu.szohrfe.noisegenerator.util.ColorPalette;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

public class MinimalNeonSliderUI extends BasicSliderUI {

    private Area primaryRhombusShape;
    private Area secondaryRhombusShape;
    private final Color trackPrimaryColor;
    private final Color trackSecondaryColor;
    private final Color thumbPrimaryColor;
    private final Color thumbSecondaryColor;
    public MinimalNeonSliderUI() {
        super();
        initGraphics();
        trackPrimaryColor = ColorPalette.PINKISH_RED;
        trackSecondaryColor = ColorPalette.NAVY;
        thumbPrimaryColor = ColorPalette.BLACK;
        thumbSecondaryColor = ColorPalette.PINKISH_RED;
    }

    private void initGraphics() {
        int thumbX = getThumbSize().width;
        int thumbY = getThumbSize().height;
        int insetSize = 7;
        Path2D primaryRhombusPath = new Path2D.Double();
        primaryRhombusPath.moveTo(thumbX/2f, 0);
        primaryRhombusPath.lineTo(0,thumbY/2f);
        primaryRhombusPath.lineTo(thumbX/2f, thumbY);
        primaryRhombusPath.lineTo(thumbX, thumbY/2f);
        primaryRhombusPath.closePath();
        primaryRhombusShape = new Area(primaryRhombusPath);
        Path2D secondaryRhombusPath = new Path2D.Double();
        secondaryRhombusPath.moveTo(thumbX/2f, insetSize);
        secondaryRhombusPath.lineTo(insetSize,thumbY/2f);
        secondaryRhombusPath.lineTo(thumbX/2f, thumbY-insetSize);
        secondaryRhombusPath.lineTo(thumbX-insetSize, thumbY/2f);
        secondaryRhombusPath.closePath();
        secondaryRhombusShape = new Area(secondaryRhombusPath);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        c.setBackground(ColorPalette.DARK_GRAY);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(30, 30);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.translate(thumbRect.x, thumbRect.y);
        graphics2D.setPaint(thumbPrimaryColor);
        graphics2D.fill(primaryRhombusShape);
        graphics2D.setPaint(thumbSecondaryColor);
        graphics2D.fill(secondaryRhombusShape);
    }

    @Override
    public void paintFocus(Graphics g) {}

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            int size = 10;
            int y = (trackRect.height - size) / 2;
            graphics2D.setPaint(trackSecondaryColor);
            graphics2D.fill(new Rectangle(trackRect.x, trackRect.y + y, trackRect.width, size));
            graphics2D.setPaint(trackPrimaryColor);
            graphics2D.fill(new Rectangle(trackRect.x, trackRect.y + y, thumbRect.x, size));
        }
    }

}
