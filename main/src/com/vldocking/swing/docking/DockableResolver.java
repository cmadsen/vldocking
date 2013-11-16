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

/** An interface used to find a dockable (when read from an input stream like XML)
 * when the only information given is its DockKey id.
 * 
 * <p>This is mainly to allow auto-registration of new dockables when loading a new workspace.
 *
 * @author Lilian Chamontin, VLSolutions
 * @since 2.1.2
 */
public interface DockableResolver {

	/** Returns the dockable which should be associated to this DockKey identifier, or null if 
	 * not found.
	 */
	public Dockable resolveDockable(String keyName);

}
