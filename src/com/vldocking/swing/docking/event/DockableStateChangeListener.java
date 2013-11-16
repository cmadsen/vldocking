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

/** Interface implemented by classes that need to be notified of
 * a state change of a dockable.
 * <p>
 * Events are triggered when a Dockable component is :
 * <ul>
 * <LI> Docked (added to the desktop)
 * <LI> set in Auto-hide mode (collapsed as a border button)
 * <LI> closed (removed from the desktop)
 * <LI> maximized (the only one visible)
 * <LI> floating (detached from the desktop)
 * </UL>
 * <p>
 *
 *
 * @see DockableStateChangeEvent
 * @see DockableStateWillChangeEvent
 * @see com.vldocking.swing.docking.DockingDesktop#addDockableStateChangeListener(DockableStateChangeListener)
 *
 * @author Lilian Chamontin, vlsolutions.
 * @version 1.0
 * */
public interface DockableStateChangeListener {

	/** this method will be called after a dockable component has changed of state
	 * (closed, hidden, docked) */
	public void dockableStateChanged(DockableStateChangeEvent event);
}
