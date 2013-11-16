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

/** A factory that replaces DockViews (with title headers) by tabbed panes with a single tab.
 *<p>
 * This factory provides a unified GUI for single AND tabbed components (with the exception of 
 * auto-hide and single-floating that still use the standard title bars).
 *<p> 
 * It is still in an early stage and will be improved in future releases, depending on its 
 * adoption.
 *
 * @author Lilian Chamontin, VLSolutions
 * @since 2.1.3
 *
 */
public class TabFactory extends DefaultDockableContainerFactory {

	public SingleDockableContainer createDockableContainer(Dockable dockable, ParentType parentType) {
		switch(parentType) {
			case PARENT_TABBED_CONTAINER:
				return new TabbedDockView(dockable);
			case PARENT_DESKTOP:
				return new MaximizedDockViewAsTab(dockable);//MaximizedDockView(dockable);
			case PARENT_SPLIT_CONTAINER:
				return new DockViewAsTab(dockable);
			case PARENT_DETACHED_WINDOW:
				return new DetachedDockView(dockable);
			default:
				throw new RuntimeException("Wrong dockable container type");
		}
	}

}