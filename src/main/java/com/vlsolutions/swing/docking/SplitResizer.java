package com.vlsolutions.swing.docking;

import javax.swing.*;

/* Utility class, resizes a splitcontainer after its size is known (needs an invokelater after
 * having added it).
 * allows us to avoid the nasty resizing of a splitpane when a component
 * is added on the right/bottom (it will then take most of the split surface,
 * which is not good when we add a small dockable to the right of a big dockable
 */
class SplitResizer implements Runnable {

    SplitContainer split;

    int location;
    double doubleloc;
    boolean isDouble;

    SplitResizer(SplitContainer split, int location) {
        this.split = split;
        this.location = location;
        isDouble = false;
    }

    SplitResizer(SplitContainer split, double location) {
        this.split = split;
        this.doubleloc = location;
        isDouble = true;
    }

    public void run() {
        // used as invokeLater, as the size of the splitpane is not known
        // when split is first inserted.
        if (isDouble) {
            split.setDividerLocation(doubleloc);
        } else {

            // clamp resizing to the half width/height of the splitpane
            int maxWidth = split.getWidth() / 2;
            int maxHeight = split.getHeight() / 2;
            if (location < 0) { //meaning it's a resize of the opposite component
                if (split.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                    location = Math.max(maxWidth, split.getWidth() + location);
                } else {
                    location = Math.max(maxHeight, split.getHeight() + location);
                }
            } else {
                if (split.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                    location = Math.min(maxWidth, location);
                } else {
                    location = Math.min(maxHeight, location);
                }
            }

            split.setDividerLocation(location);
        }
    }

}
