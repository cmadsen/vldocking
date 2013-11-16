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

package com.vldocking.swing.docking.ws;

import com.vldocking.swing.docking.DockingContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JMenuItem;

/** A simple menu item that applies a workspace to a given Docking context when selected .
 *
 * @author Lilian Chamontin, VLSolutions
 * @since 2.1.3
 */
public class WorkspaceMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;
	private Workspace workspace;
	private DockingContext ctx;
	private ActionListener actionListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			try {
				applyWorkspace();
			} catch(WorkspaceException ex) {
				ex.printStackTrace();
			}
		}
	};

	/** Default constructor */
	public WorkspaceMenuItem() {
		addActionListener(actionListener);
	}

	/** constructor with a menu text */
	public WorkspaceMenuItem(String text) {
		super(text);
		addActionListener(actionListener);
	}

	/** constructor with a menu text and an icon */
	public WorkspaceMenuItem(String text, Icon icon) {
		super(text, icon);
		addActionListener(actionListener);
	}

	/** Invoked when the button is pressed : applies the workspace to the associated context */
	public void applyWorkspace() throws WorkspaceException {
		workspace.apply(ctx);
	}

	/** Returns the workspace used by this menu item */
	public Workspace getWorkspace() {
		return workspace;
	}

	/** updates the workspace to be used by this menu item  */
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	/** Returns the docking context used by this menu item  */
	public DockingContext getDockingContext() {
		return ctx;
	}

	/** Updates the docking context used by this menu item  */
	public void setDockingContext(DockingContext ctx) {
		this.ctx = ctx;
	}

}
