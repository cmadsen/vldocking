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

package com.vldocking.swing.toolbars;

import java.util.ArrayList;
import java.util.List;

/** Descriptor of the loading operation of toolbars.
 *
 * <p> returns useful informations such as the toolbars registered and successfully loaded, and
 * the ones not loaded.
 *
 * @author Lilian Chamontin, VLSolutions
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ToolBarIOReadInfo {

	List notInstalledToolBarList;
	List installedToolBarList = new ArrayList();
	List unknownToolBarNames = new ArrayList();
	List registeredToolbars;

	public ToolBarIOReadInfo() {}

	/** returns the list of toolbars that were successfully loaded from the toolbarIO operation */
	public List getInstalledToolbars() {
		return installedToolBarList;
	}

	/** returns the list of toolbars that weren't loaded from the stream (but registered) */
	public List getNotInstalledToolbars() {
		return notInstalledToolBarList;
	}

	/** Return the list of toolbars names (String) found in the stream but not registered
	 * (these toolbars cannot be loaded, so the API can only report their name). 
	 */
	public List getUnknownToolbarNames() {
		return unknownToolBarNames;
	}

	void notifyToolbarInstalled(VLToolBar tb) {
		installedToolBarList.add(tb);
	}

	void setRegisteredToolbars(List registered) {
		this.registeredToolbars = registered;
	}

	/** invoked on completion of loading */
	void finishLoading() {

		this.notInstalledToolBarList = new ArrayList(registeredToolbars);
		notInstalledToolBarList.removeAll(installedToolBarList);

	}

	void notifyUnknownToolbarFound(String toolbarName) {
		unknownToolBarNames.add(toolbarName);
	}

}
