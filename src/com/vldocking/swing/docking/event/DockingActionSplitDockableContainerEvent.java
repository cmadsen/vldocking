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

package com.vldocking.swing.docking.event;

import com.vldocking.swing.docking.DockableState;
import com.vldocking.swing.docking.DockingConstants;
import com.vldocking.swing.docking.DockingDesktop;
import java.awt.Component;
import java.awt.Container;

/** A split event : split a base component in two, and puts a dockable container
 * in the other split position.
 *
 * @author Lilian Chamontin, VLSolutions
 * @since 2.1 
 */
public class DockingActionSplitDockableContainerEvent extends DockingActionEvent {

	private Container dockableContainer;

	private DockingConstants.Split position;

	private float location;

	private Component base;

	/** Constructs a new event 
	 */
	public DockingActionSplitDockableContainerEvent(DockingDesktop desk, DockableState.Location initialLocation, DockableState.Location nextLocation, Component base, Container dockableContainer, DockingConstants.Split position, float location) {
		super(desk, initialLocation, nextLocation, ACTION_SPLIT_DOCKABLE);
		this.base = base;
		this.dockableContainer = dockableContainer;
		this.position = position;
		this.location = location;
	}

	public Container getDockableContainer() {
		return dockableContainer;
	}

	public DockingConstants.Split getSplitPosition() {
		return position;
	}

	public float getLocation() {
		return location;
	}

	public Component getBase() {
		return base;
	}

}
