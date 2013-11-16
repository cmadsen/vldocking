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

/** An exception raised when a workspace operation has gone wrong (can encapsulate 
 * another exception).
 *
 * @author Lilian Chamontin, VLSolutions
 */
public class WorkspaceException extends Exception {

	private static final long serialVersionUID = 1L;

	public WorkspaceException(String message) {
		super(message);
	}

	public WorkspaceException(Exception cause) {
		super(cause);
	}

	public WorkspaceException(String message, Exception cause) {
		super(message, cause);
	}

}
