/*
    VLDocking Framework 3.0
    Copyright Lilian Chamontin, 2004-2013
    
    www.vldocking.com
    vldocking@googlegroups.com
------------------------------------------------------------------------
This software is distributed under the LGPL license

The fact that you are presently reading this and using this class means that you have had
knowledge of the LGPL license and that you accept its terms.

You can read the complete license here :

    http://www.gnu.org/licenses/lgpl.html

*/

package com.vldocking.swing.docking;

import com.vldocking.swing.docking.event.DockDragEvent;
import com.vldocking.swing.docking.event.DockDropEvent;
import java.awt.Point;
import javax.swing.AbstractAction;
import javax.swing.UIManager;

/** A maximized view to use with the TabFactory.
 *
 * @author Lilian Chamontin, VLSolutions
 * @see TabFactory
 * @since 2.1.3
 */
public class MaximizedDockViewAsTab extends DockViewAsTab {

	private static final long serialVersionUID = 1L;

	public MaximizedDockViewAsTab(Dockable dockable) {
		super(dockable);
	}

	protected void configureMaximizeButton() {
		if(isRestoreButtonDisplayed) {
			maximizeSmartIcon.setIcon(UIManager.getIcon("DockViewTitleBar.restore"));
			maximizeSmartIcon.setPressedIcon(UIManager.getIcon("DockViewTitleBar.restore.pressed"));
			maximizeSmartIcon.setRolloverIcon(UIManager.getIcon("DockViewTitleBar.restore.rollover"));
			// add a tooltip
			maximizeAction.putValue(AbstractAction.SHORT_DESCRIPTION, UIManager.get("DockViewTitleBar.restoreButtonText"));
		}
	}

	protected void configureFloatButton() {
		isFloatButtonDisplayed = false;
	}

	protected void configureHideButton() {
		isHideButtonDisplayed = false;
	}

	protected void configureCloseButton() {
		isCloseButtonDisplayed = false;
	}

	protected TabHeader createTabHeader() {
		return new MaximizedTabHeader();
	}

	public void processDockableDrag(DockDragEvent event) {
		event.rejectDrag();
	}

	public void processDockableDrop(DockDropEvent event) {
		event.rejectDrop();
	}

	protected class MaximizedTabHeader extends TabHeader {

		private static final long serialVersionUID = 1L;

		/** {@inheritDoc} */
		public boolean startDragComponent(Point p) {
			// disable DnD for some cases :
			// - child of a compound dockable, in hidden state
			// - child of a maximized compund dockable
			// - maximized dockable
			Dockable target = getDockable();
			DockableState.Location targetState = target.getDockKey().getLocation();
			if(targetState == DockableState.Location.HIDDEN) {
				if(DockingUtilities.isChildOfCompoundDockable(target)) {
					// nested hidden dockables cannot be drag and dropped
					return false;
				}
			} else if(targetState == DockableState.Location.DOCKED) {
				boolean isChildOfMaximizedContainer = false;
				if(desktop != null) {
					Dockable max = desktop.getMaximizedDockable();
					if(max != null && max.getComponent().getParent().isAncestorOf(this)) {
						isChildOfMaximizedContainer = true;
					}
				}
				if(isChildOfMaximizedContainer) {
					return false;
				}
			} else if(targetState == DockableState.Location.MAXIMIZED) {
				return false;
			}

			return true;

		}
	}

}
