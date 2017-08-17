#summary Customizing the User interface
#sidebar TableOfContents

= Lesson 8 : Customizing the User Interface  = 


This is the 8th part of the VLDocking Framework for Java Swing applications.

This lesson describes how to change the Look And Feel of VLDocking. 


== The new UI delegation model of VLDocking == 

=== VLDocking UI Delegates === 


VLDocking uses UI delegate classes to paint the look and feel of many of its components.
Theses classes are dynamically loaded at startup, and use many UI properties to give your application the desired appearance. Here is the list of the current UI delegates :


<table border="1"><thead><tr><th> Component </th><th>UI Delegate class</th></tr></thead>
<tbody>
<tr><td>DockView</td><td>com.vlsolutions.swing.docking.ui.DockViewUI</td></tr>
<tr><td>DockViewTitleBar</td><td>com.vlsolutions.swing.docking.ui.DockViewTitleBarUI</td></tr>
<tr><td>SplitContainer</td><td>com.vlsolutions.swing.docking.ui.DockingSplitPaneUI</td></tr>
<tr><td>DetachedDockView</td><td>com.vlsolutions.swing.docking.ui.DetachedDockViewUI</td></tr>
<tr><td>AutoHideExpandPanel</td><td>com.vlsolutions.swing.docking.ui.AutoHideExpandPanelUI</td></tr>
<tr><td>AutoHideButtonPanel</td><td>com.vlsolutions.swing.docking.ui.AutoHideButtonPanelUI</td></tr>
<tr><td>AutoHideButton</td><td>com.vlsolutions.swing.docking.ui.AutoHideButtonUI</td></tr>
</tbody></table>



=== Replacing UI Delegates === 


Please note that most UI settings are accessible with UI properties, and thus cas be changed 
without the need of writing a java class. The only case where you should have to write your own
UI delegate class is when you want a very different rendering (non rectangular for example). 


So, if you need a very different rendering for your dockables, then you will have to provide your custom classes.


Here is how to declare them :


 * pre-install the default ui settings in you main() method, or any method prior DockingDesktop usage
 * put your new settings as UIManager properties	

Example :


{{{
public static void main(String[] args){
    // first, preload the UI to avoid erasing your own customizations
    DockingUISettings.getInstance().installUI(); 
    // and start customizing...
    UIManager.put("DockViewTitleBarUI", "your.own.ui.UIDelegateClassName"); 
    // (replaced the DockViewTitleBar UI by another class)
    ...
}
}}}



===  Updating the existing UI  === 

The easiest way to update the UI is by tweaking the UIManager properties.

For example, if you want a special border for maximized dockable, use : 

{{{
// declare your border 
Border myBorder = ...
// put it in the UI property map. 
*UIManager.put("DockView.maximizedDockableBorder", myBorder);*
// and that's it !
}}}


Please note that *to avoid having your own UI settings beeing erased *by the default ones, 
you will have to follow the pattern :


 * pre-install the default ui settings in you main() method, or any method prior DockingDesktop usage
 * put your new settings as UIManager properties	




{{{
public static void main(String[] args){
    // first, preload the UI to avoid erasing your own customizations
    DockingUISettings.getInstance().installUI(); 
    
    // declare your border 
    Border myBorder = ...
    // and start customizing...
    *UIManager.put("DockView.maximizedDockableBorder", myBorder);*    
    ...
}
}}}

Now that you know how to proceed, here is the long list of customizable properties...
 
== Customizable properties ==  
===  Border properties === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr><td>DockView.singleDockableBorder</td> <td>Border</td> <td>border used when the DockView is docked alone (not in a tab)</td></tr>
<tr><td>DockView.tabbedDockableBorder</td> <td>Border</td> <td>border used when the DockView is contained in a tabbed pane</td></tr>
<tr><td>DockView.maximizedDockableBorder</td> <td>Border</td> <td>border used when the DockView is maxmized</td></tr>
<tr> <td>AutoHideButton.expandBorderTop</td> <td>Border</td> <td>Border of the autohide button when it is on top of the desktop</td></tr>
<tr> <td>AutoHideButton.expandBorderBottom</td> <td>Border</td> <td>Border of the autohide button when it is at bottom of the desktop</td></tr>
<tr> <td>AutoHideButton.expandBorderLeft</td> <td>Border</td> <td>Border of the autohide button when it is on the left of the desktop</td></tr>
<tr> <td>AutoHideButton.expandBorderRight</td> <td>Border</td> <td>Border of the autohide button when it is on the right of the desktop</td></tr>
<tr> <td>AutoHideButtonPanel.topBorder</td> <td>Border</td> <td>Border of the AutoHideButtonPanel when it is on top of the desktop</td></tr>
<tr> <td>AutoHideButtonPanel.bottomBorder</td> <td>Border</td> <td>Border of the AutoHideButtonPanel when it is at bottom of the desktop</td></tr>
<tr> <td>AutoHideButtonPanel.leftBorder</td> <td>Border</td> <td>Border of the AutoHideButtonPanel when it is on the left of the desktop</td></tr>
<tr> <td>AutoHideButtonPanel.rightBorder</td> <td>Border</td> <td>Border of the AutoHideButtonPanel when it is on the right of the desktop</td></tr>
<tr> <td>DockViewTitleBar.border</td> <td>Border</td> <td>Border of the title bar</td></tr>
<tr> <td>ToolBarPanel.topBorder</td> <td>Border</td> <td>Border used when a toolbar in on the top of a container</td></tr>
<tr> <td>ToolBarPanel.leftBorder</td> <td>Border</td> <td>Border used when a toolbar in on the left of a container</td></tr>
<tr> <td>ToolBarPanel.bottomBorder</td> <td>Border</td> <td>Border used when a toolbar in at the bottom of a container</td></tr>
<tr> <td>ToolBarPanel.rightBorder</td> <td>Border</td> <td>Border used when a toolbar in on the right of a container</td></tr>
<tr> <td>FloatingDialog.dialogBorder</td> <td>Border</td> <td>Border used for the FloatingDialog</td></tr>
<tr> <td>FloatingDialog.titleBorder</td> <td>Border</td> <td>Border used for the title (drag header) of the FloatingDialog</td></tr>
</tbody></table>

===  Color properties  === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr> <td>DockingDesktop.notificationColor</td> <td>Color</td> <td>blinking color for notifications</td></tr>
</tbody></table>

===  Icons === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr> <td>DockViewTitleBar.close</td> <td>Icon</td> <td>Icon for the close button</td></tr>
<tr> <td>DockViewTitleBar.close.rollover</td> <td>Icon</td> <td>Icon for the close button</td></tr>
<tr> <td>DockViewTitleBar.close.pressed</td> <td>Icon</td> <td>Icon for the close button</td></tr>
<tr> <td>DockViewTitleBar.dock</td> <td>Icon</td> <td>Icon for the dock button</td></tr>
<tr> <td>DockViewTitleBar.dock.rollover</td> <td>Icon</td> <td>Icon for the dock button</td></tr>
<tr> <td>DockViewTitleBar.dock.pressed</td> <td>Icon</td> <td>Icon for the dock button</td></tr>
<tr> <td>DockViewTitleBar.hide</td> <td>Icon</td> <td>Icon for the hide button</td></tr>
<tr> <td>DockViewTitleBar.hide.rollover</td> <td>Icon</td> <td>Icon for the hide button</td></tr>
<tr> <td>DockViewTitleBar.hide.pressed</td> <td>Icon</td> <td>Icon for the hide button</td></tr>
<tr> <td>DockViewTitleBar.maximize</td> <td>Icon</td> <td>Icon for the maximize button</td></tr>
<tr> <td>DockViewTitleBar.maximize.rollover</td> <td>Icon</td> <td>Icon for the maximize button</td></tr>
<tr> <td>DockViewTitleBar.maximize.pressed</td> <td>Icon</td> <td>Icon for the maximize button</td></tr>
<tr> <td>DockViewTitleBar.restore</td> <td>Icon</td> <td>Icon for the restore button</td></tr>
<tr> <td>DockViewTitleBar.restore.rollover</td> <td>Icon</td> <td>Icon for the restore button</td></tr>
<tr> <td>DockViewTitleBar.restore.pressed</td> <td>Icon</td> <td>Icon for the restore button</td></tr>
<tr> <td>DockViewTitleBar.float</td> <td>Icon</td> <td>Icon for the float button</td></tr>
<tr> <td>DockViewTitleBar.float.rollover</td> <td>Icon</td> <td>Icon for the float button</td></tr>
<tr> <td>DockViewTitleBar.float.pressed</td> <td>Icon</td> <td>Icon for the float button</td></tr>
<tr> <td>DockViewTitleBar.attach</td> <td>Icon</td> <td>Icon for the attach button</td></tr>
<tr> <td>DockViewTitleBar.attach.rollover</td> <td>Icon</td> <td>Icon for the attach button</td></tr>
<tr> <td>DockViewTitleBar.attach.pressed</td> <td>Icon</td> <td>Icon for the attach button</td></tr>
<tr> <td>DockViewTitleBar.menu.close</td> <td>Icon </td> <td>Icon for the close button, in pop-up menu</td></tr>
<tr> <td>DockViewTitleBar.menu.hide</td> <td>Icon </td> <td>Icon for the hide button, in pop-up menu</td></tr>
<tr> <td>DockViewTitleBar.menu.maximize</td> <td>Icon </td> <td>Icon for the maximize button, in pop-up menu</td></tr>
<tr> <td>DockViewTitleBar.menu.restore</td> <td>Icon </td> <td>Icon for the restore button, in pop-up menu</td></tr>
<tr> <td>DockViewTitleBar.menu.dock</td> <td>Icon </td> <td>Icon for the dock button, in pop-up menu</td></tr>
<tr> <td>DockViewTitleBar.menu.float</td> <td>Icon </td> <td>Icon for the float button, in pop-up menu</td></tr>
<tr> <td>DockViewTitleBar.menu.attach</td> <td>Icon </td> <td>Icon for the attach button, in pop-up menu</td></tr>
<tr> <td>DockTabbedPane.close</td> <td>Icon </td> <td>Icon for the close button, in tabs</td></tr>
<tr> <td>DockTabbedPane.close.rollover</td> <td>Icon </td> <td>Icon for the close button, in tabs</td></tr>
<tr> <td>DockTabbedPane.close.pressed</td> <td>Icon </td> <td>Icon for the close button, in tabs</td></tr>
<tr> <td>DockTabbedPane.unselected_close</td> <td>Icon </td> <td>Icon for the close button, in tabs (for an unselected tab). The "TabbedPane.alternateTabIcons" 
property must also be set to true</td></tr>
<tr> <td>DockTabbedPane.unselected_close.rollover</td> <td>Icon </td> <td>Icon for the close button, in tabs (for an unselected tab)</td></tr>
<tr> <td>DockTabbedPane.unselected_close.pressed</td> <td>Icon </td> <td>Icon for the close button, in tabs(for an unselected tab)</td></tr>
<tr> <td>DockTabbedPane.menu.close</td> <td>Icon </td> <td>Icon for the close button, in tab pop-up menu</td></tr>
<tr> <td>DockTabbedPane.menu.hide</td> <td>Icon </td> <td>Icon for the hide button, in tab pop-up menu</td></tr>
<tr> <td>DockTabbedPane.menu.maximize</td> <td>Icon </td> <td>Icon for the maximize button, in tab pop-up menu</td></tr>
<tr> <td>DockTabbedPane.menu.float</td> <td>Icon </td> <td>Icon for the float button, in tab pop-up menu</td></tr>
<tr> <td>DockTabbedPane.menu.closeAll</td> <td>Icon </td> <td>Icon for the "close all" button, in tab pop-up menu</td></tr>
<tr> <td>DockTabbedPane.menu.closeAllOther</td> <td>Icon </td> <td>Icon for the "close all other" button, in tab pop-up menu</td></tr>
</tbody></table>


===  Labels and Fonts  === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr> <td>DockViewTitleBar.closeButtonText</td> <td>String</td> <td>Text of the close button</td></tr>
<tr> <td>DockViewTitleBar.minimizeButtonText</td> <td>String</td> <td>Text of the minimize (hide) button</td></tr>
<tr> <td>DockViewTitleBar.maximizeButtonText</td> <td>String</td> <td>Text of the maximize button</td></tr>
<tr> <td>DockViewTitleBar.restoreButtonText</td> <td>String</td> <td>Text of the restore button(opposite of maximize)</td></tr>
<tr> <td>DockViewTitleBar.floatButtonText</td> <td>String</td> <td>Text of the float button (detach)</td></tr>
<tr> <td>DockViewTitleBar.attachButtonText</td> <td>String</td> <td>Text of the attach button(opposite of float)</td></tr>
<tr> <td>DockViewTitleBar.titleFont</td> <td>Font</td> <td>Font used by the title bar</td></tr>
<tr> <td>DockTabbedPane.closeButtonText</td> <td>String</td> <td>Text for the close button in tab</td></tr>
<tr> <td>DockTabbedPane.minimizeButtonText</td> <td>String</td> <td>Text for the minimize button in tab</td></tr>
<tr> <td>DockTabbedPane.restoreButtonText</td> <td>String</td> <td>Text for the restore button in tab</td></tr>
<tr> <td>DockTabbedPane.maximizeButtonText</td> <td>String</td> <td>Text for the maximize button in tab</td></tr>
<tr> <td>DockTabbedPane.floatButtonText</td> <td>String</td> <td>Text for the float button in tab</td></tr>
</tbody></table>

===  Displaying buttons in title bars  === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr> <td>DockViewTitleBar.isCloseButtonDisplayed</td> <td>boolean</td> <td>display or not the close button in the title bar (still accessible from pop-up menu)</td></tr>
<tr> <td>DockViewTitleBar.isHideButtonDisplayed</td> <td>boolean</td> <td>display or not the hide button in the title bar</td></tr>
<tr> <td>DockViewTitleBar.isDockButtonDisplayed</td> <td>boolean</td> <td>display or not the dock button in the title bar</td></tr>
<tr> <td>DockViewTitleBar.isMaximizeButtonDisplayed</td> <td>boolean</td> <td>display or not the maximize button in the title bar</td></tr>
<tr> <td>DockViewTitleBar.isRestoreButtonDisplayed</td> <td>boolean</td> <td>display or not the restore button in the title bar</td></tr>
<tr> <td>DockViewTitleBar.isFloatButtonDisplayed</td> <td>boolean</td> <td>display or not the float button in the title bar</td></tr>
<tr> <td>DockViewTitleBar.isAttachButtonDisplayed</td> <td>boolean</td> <td>display or not the attach button in the title bar</td></tr>
</tbody></table>

===  KeyStrokes  === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr> <td>DockingDesktop.closeActionAccelerator</td> <td>KeyStroke</td> <td>KeyStroke for close action (on selected dockable)</td></tr>
<tr> <td>DockingDesktop.maximizeActionAccelerator</td> <td>KeyStroke</td> <td>KeyStroke for maximize/restore action (on selected dockable)</td></tr>
<tr> <td>DockingDesktop.dockActionAccelerator</td> <td>KeyStroke</td> <td>KeyStroke for hide/dock action (on selected dockable)</td></tr>
<tr> <td>DockingDesktop.floatActionAccelerator</td> <td>KeyStroke</td> <td>KeyStroke for float/attach action (on selected dockable)</td></tr>
</tbody></table>

===  UI Delegates  === 


<table border="1"><thead><tr><th>UI property</th><th>type</th><th>effect</th></tr></thead><tbody>
<tr><td>AutoHideButtonUI</td> <td>class name</td> <td>UI delegate for the AutoHideButton</td></tr>
<tr><td>AutoHideButtonPanelUI</td> <td>class name</td> <td>UI delegate for the AutoHideButtonPanel</td></tr>
<tr><td>AutoHideExpandPanelUI</td> <td>class name</td> <td>UI delegate for the AutoHideExpandPanel</td></tr>
<tr><td>DockViewUI</td> <td>class name</td> <td>UI delegate for DockView</td></tr>
<tr><td>DetachedDockViewUI</td> <td>class name</td> <td>UI delegate for DetachedDockView</td></tr>
<tr><td>DockViewTitleBarUI</td> <td>class name</td> <td>UI delegate for DockViewTitleBar</td></tr>
<tr><td>DockViewTitleBar.height</td> <td>int</td> <td>Height of the title bars</td></tr>
<tr><td>DockingSplitPaneUI</td> <td>class name</td> <td>UI delegate for SplitContainer component</td></tr>
<tr><td>ToolBarGripperUI</td> <td>class name</td> <td>UI delegate for the toolbar "gripper"</td></tr>
</tbody></table>

===  Cursors === 

Starting from version 2.0.3 it is now possible to change the mouse cursor images used during 
drag gestures.

<table border="1"><thead><tr><th>UI property</th><th>type</th><th>effect</th></tr></thead><tbody>
<tr><td>DragControler.stopDragCursor</td> <td>java.awt.Image</td> <td>Image (max 32x32) used when the mouse pointer is above a place where drop is not possible</td></tr>
<tr><td>DragControler.detachCursor</td> <td>Image</td> <td>Image used to denote a floating action </td></tr>
<tr><td>DragControler.dragCursor</td> <td>Image</td> <td>Image used (together with a shape) to denote a position where drop is possible</td></tr>
<tr><td>DragControler.swapDragCursor</td> <td>Image</td><td>Cursor image used when doing a drag and drop with Ctrl key ("Hot Swap") </td></tr>
</tbody></table>


===  Other properties === 

<table border="1">
<thead>
<tr><th>UI property</th> <th>type</th> <th>effect</th></tr></thead>
<tbody>
<tr> <td>SplitContainer.dividerSize</td> <td>int</td> <td>Divider size of the split panes</td></tr>
<tr> <td>TabbedDockableContainer.tabPlacement</td> <td>int (SwingConstants.TOP / BOTTOM)</td> <td>Global tab style</td></tr>
<tr> <td>TabbedContainer.requestFocusOnTabSelection</td> <td>boolean</td> <td>Automatically puts focus on the selected tabbed component (default false)</td></tr>
<tr> <td>TabbedPane.otherIconsGap</td> <td>int</td> <td>Gap between text and close icon in closeable tab</td></tr>
<tr> <td>TabbedPane.alternateTabIcons</td> <td>boolean</td> <td>allows an alternative set of close icons for selected and unselected tabs (default is false)</td></tr>
<tr> <td>DockingDesktop.notificationBlinkCount</td> <td>int</td> <td>maximum number of blinking for notifications </td></tr>
<tr> <td>DockingDesktop.notificationBlinkCount</td> <td>int</td> <td>maximum number of blinking for notifications </td></tr>
<tr> <td>FloatingContainer.followParentWindow</td> <td>Boolean</td> <td>if true, the floating dialogs will follow the movements of their parent window on screen</td></tr>
<tr> <td>FloatingContainer.paintDragShape </td> <td>Boolean</td> <td>if true, a drag outline shape will follow the mouse when dragging (warning : consumes CPU)</td></tr>
</tbody> </table>

== The two basic VLDocking styles == 


VLDocking 1.0 provided a "shadowed" style (similar to the eclipse look and feel).


VLDocking 2.0 comes with a default "flat style" where borders take less space. It also comes
with tabs on top of tabbed containers (version 1.0 had them at bottom).


You can choose select between the following provided styles (more to come) with the methods : 


{{{
// version 1.0 style
DockingPreferences.setShadowDesktopStyle();
}}}

 [http://vldocking.googlecode.com/svn/wiki/shadowed_style.jpg]

 _The "Shadow Style"_

{{{
// or version 1.0 alternative style
DockingPreferences.setDottedDesktopStyle();
}}}

 [http://vldocking.googlecode.com/svn/wiki/dotted_style.jpg]

 _The "Dotted Style"_

{{{
// or version 2.0 default style 
DockingPreferences.setFlatDesktopStyle();
}}}

 [http://vldocking.googlecode.com/svn/wiki/flat_style.jpg]
 
 _The default "Flat Style"_



----

Next : [tutorial9]