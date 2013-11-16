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

import java.awt.Graphics;
import javax.swing.JComponent;

/** An interface implemented by objects which can paint toolbar backgrounds. 
 *
 *
 * @author Lilian Chamontin, VLSolutions
 * @since 2.1.4
 */
public interface BackgroundPainter {

	public void paintBackground(JComponent component, Graphics g);

}
