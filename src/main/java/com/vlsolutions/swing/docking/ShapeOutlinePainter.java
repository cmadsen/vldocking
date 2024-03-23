package com.vlsolutions.swing.docking;

import java.awt.*;

/**
 * the object responsible for painting the shape outline
 */
class ShapeOutlinePainter {

    private Color innerColor = new Color(64, 64, 64);

    public void paintShape(Graphics2D g2, Shape s) {
        Composite old = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        g2.setStroke(new BasicStroke(3));
        g2.setColor(innerColor);
        g2.fill(s);
        g2.setComposite(old);
        g2.setColor(Color.DARK_GRAY);
        g2.draw(s);
    }
}
