#summary Vldocking workspaces
#sidebar TableOfContents
= Lesson 4 : VLDocking Workspaces = 



This is the 4th part of the VLDocking Framework tutorial for Java Swing applications.


In this lesson, you will learn to load and save desktop configurations, also
known as _workspaces_.

== Workspaces == 



Workspaces are getting more common in Java applications, they are called "perspectives" in eclipse,
workbenches in other applications, but we'll stick to workspace for the Docking Framework.


A full description of a workspace in our case could be "a set of Dockables,
at a given location and size, on a given visibility state".


  	 * A set of dockables : usually the full set of the application's dockables.
  	 * At a given location and size : location is determined by the containment
hierarchy of the DockingDesktop, size is taken from SplitContainers divider positions.
  	 * On a given visibility state : this includes auto-hidden and closed dockables.


This is an example of two workpaces of the same application :

 [http://vldocking.googlecode.com/svn/wiki/workspace1.jpg]

 _Workspace 1_


  [http://vldocking.googlecode.com/svn/wiki/workspace2.jpg]
 
  _Workspace 2_



* Note :* as of version 2.0 of VLDocking, a new set of components has been 
added : the *VLToolBars*. These components
have their own methods for saving and reloading (as they may be used from outside the framework).

 
VLToolbars will be explained later, in their own <a href="tutorial9.php"> tutorial</a>.



== The Workspace Editor application == 

_This is a new feature from VLDocking 2.0.5_
 
This new application can be started from <a href="../../../products/docking/workspace.php"> here </a> and can be used to 
define or update workspace files with a GUI editor.

The user guide is currently limited to the start page of the application, but should be enough to help you define a set of 
workspaces in a matter of minutes.

Once your workspace is defined and saved as XML, go to the <a href="#loading">Loading a workspace</a> section of this page to 
learn how to integrate it into your application.


== Saving a workspace == 



To save a workspace, use the {{{ writeXML(OutputStream out)}}} of the
{{{DockingDesktop}}} object.


Here is an example of saving to a file :


{{{
  import java.io.*;

  DockingDesktop desk = ...;
  File saveFile = ...;

  // here we go !
  try {
    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile)));
    desk.writeXML(out);
    out.close(); // stream isn't closed in case you'd like to save something else after
  } catch (IOException ioe){
    // process exception here
  }
}}}




*Note :* Of course, you can save your workspace into something else (a byte
array, for example, via the {{{ByteArrayOutputStream}}} output);


Let's have a look at the resulting file (indented for readability) :


{{{
&lt;?xml version="1.0" ?&gt;
&lt;DockingDesktop version="1.0"&gt;
  &lt;DockingPanel&gt;
    &lt;Split orientation="1"&gt;
      &lt;Split orientation="0"&gt;
        &lt;Dockable&gt;
          &lt;Key dockName="table"/&gt;
        &lt;/Dockable&gt;
        &lt;Dockable&gt;
          &lt;Key dockName="grid of buttons"/&gt;
        &lt;/Dockable&gt;
      &lt;/Split&gt;
      &lt;Dockable&gt;
        &lt;Key dockName="textEditor"/&gt;
      &lt;/Dockable&gt;
    &lt;/Split&gt;
  &lt;/DockingPanel&gt;
  &lt;Border zone="1"&gt;
    &lt;Dockable&gt;
      &lt;Key dockName="tree"/&gt;
      &lt;RelativePosition x="0.0" y="0.0" w="0.2" h="1.0" /&gt;
    &lt;/Dockable&gt;
  &lt;/Border&gt;
&lt;/DockingDesktop&gt;
}}}




As you can see, it's a very simple XML structure, with nested Split (and Tab) elements.



== Loading a workspace from an XML stream == 


Loading a workspace is as easy as saving it, but requires another step if you
use this feature to populate an empty desktop : you have to {{{registerDockable(Dockable d)}}}
all your dockables before calling {{{ readXML(InputStream in)}}}.


Loading from an XML File to populate an empty DockingDesktop :
{{{
  import java.io.*;

  DockingDesktop desk = ...;
  File loadFile = ...;

  // here we go !
  try {
    *// first : declare the dockables to the desktop (they will be in the "closed" dockable state).
    desk.registerDockable(editorPanel);
    desk.registerDockable(tablePanel);
    desk.registerDockable(buttonGrid);
    desk.registerDockable(treePanel);
    *  
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(loadFile)));
    *// then, load the workspace 
    desk.readXML(in);
    *
    in.close(); // stream isn't closed
  } catch (IOException ioe){
    // process exception here
  }
}}}


 If your desktop is already populated, you can skip the {{{registerDockable}}} step
 (assuming the dockable keys of the XML file are the same than those of the desktop's dockables).



==  Lazy Dockable registration : DockableResolver  == 


Introduced with VLDocking 2.1.2, the {{{DockableResolver}}} interface is a way to register 
dockable as lazily as possible (with this interface, they will be registered during the readXML() parsing).



The inteface contains a single method : 

{{{
public interface DockableResolver {
  
  /** Returns the dockable which should be associated to this DockKey identifier, or null if 
   * not found.
   */
   public Dockable resolveDockable(String keyName);
}}}



To use this interface, you just have to give it to a DockingContext before applying a 
workspace :



{{{
DockingContext ctx = ...
DockableResolver resolver = new DockableResolver(){
	public Dockable resolveDockable(String keyName){
		if (keyName.equals("dockable1")){
			// instanciate dockable1 
			return dockable1;
		} else if (keyName.equals("dockable2"){
			// instanciate dockable2 
			return dockable2;
		} else {
			return null; // for unknown dockable keys
		}
	}
}
ctx.setDockableResolver(resolver);
// now, there's no need to call registerDockablefirst
ctx.readXML(aWorkspaceInputStream); 
}}}



== The com.vlsolutions.swing.docking.ws package : logical Workspace management == 


This new package has been introduced with VLDocking 2.1.2, it contains an API to manage workspace configurations. This 
API will certainly be enhanced in future releases.

=== Workspace, WSDesktop and WSDockKey === 

A VLDocking-enabled application can be made of multiple Desktops (on single or multiple JFrames), with dockables
transferable from a desktop to another (this is possible with the new concept or {{{DockingContext}}}). 



So, the equivalent of {{{DockingContext}}} is the {{{Workspace}}} class. 


{{{
  DockingContext ctx = ...;
  Workspace workspace1 = new Workspace();
  // define the workspace layout here
  ...
  // layout defined
  
  // later...
  workspace1.apply(ctx);
  // workspace applied to the desktops of this docking context
  
}}}





A WSDesktop is the equivalent of a DockingDesktop : it is a child of a Workspace (one per desktop) and 
contains layout methods with the same name than the ones of DockingDesktop : split(), createTab(), etc.
The API beeing similar, it's very easy to understand : defining a layout into a WSDesktop is like creating 
a layout in DockingDesktop, but that layout can be reused as often as you want with a simple {{{apply()}}} 
invocation.  



As workspace should be very light objects, they must be decoupled from desktop classes.
As Dockkeys are often lazily created (at the same time than Dockable objets), its equivalent has been 
defined, and is called {{{WSDockKey}}}. Like DockKeys, a WSDockKey is used to 
identify dockables without the need to instanciate them. 



{{{
  DockingContext ctx = ...;  
  Workspace workspace1 = new Workspace();
  WSDesktop wdesk = workspace1.getDesktop(0); // a single default WSDesktop object is provided 
  // the three dockables used by this workspace/desktop
  WSDockKey key1 = new WSDockKey("dockable1");
  WSDockKey key2 = new WSDockKey("dockable2");
  WSDockKey key3 = new WSDockKey("dockable3");
  
  wdesk.addDockable(key1); // similar API, but uses WSDockKey as arguments
  wdesk.split(key1, key2, DockingConstants.SPLIT_LEFT, 0.4f);
  wdesk.createTab(key2, key3, 1);
  // layout has been defined
  
  // later...
  workspace1.apply(ctx);
  // workspace applied to the desktops of this docking context
  // registered dockables must have the same DockKey 
  // identifiers ( DockKey k1 = new DockKey("dockable1") )
  
}}}




Note : the {{{DockableResolver}}} inteface defined <a href="#resolver">here</a> works also 
with Workspaces, so both registering methods work :


{{{
  DockingContext ctx = ...;  
  Workspace workspace1 = new Workspace();
  WSDesktop wdesk = workspace1.getDesktop(0);  
  // define keys and layout
  
  // later...
  *ctx.registerDockable(dockable1);
  ctx.registerDockable(dockable2);
  ctx.registerDockable(dockable3);*
  workspace1.apply(ctx);
  
}}}



Or :



{{{
  DockingContext ctx = ...;  
  Workspace workspace1 = new Workspace();
  WSDesktop wdesk = workspace1.getDesktop(0);  
  // define keys and layout
  
  // later...
  *ctx.setDockableResolver(resolver);* // lazy dockable resolution
  workspace1.apply(ctx);
  
}}}




== Implementing a workspace switcher (updated for VLDocking 2.1.3) == 


Implementing a workspace switcher with the Workspace objets is really straightforward :
you just have to invoke {{{workspace.loadFrom(dockingContext)}}} to save a layout into a workspace, and then     
{{{workspace.apply(dockingContext)}}} to get back to this layout later.


== Notes about workspaces and Multi-Desktop applications == 


As of VLDocking version 2.1, new features have been introduced that change the contents of 
workspace files, such as *multiple desktop* sharing a common set of dockables, 
and *compound dockable containers* allowing more complex component nesting.


There features don't change the way you load or save your workspaces, but the new workspace 
XML file format can't be read from VLDocking 2.0 (while the opposite remains possible).


The only thing to remember is that readXML() and writeXML() are now redirected to the 
shared DockingContext (so a *single load/save operation is required for a set of desktops* sharing the 
same context). And that when using multiple desktops, you'd better create the desktop with the 
{{{DockingDesktop(String name)}}} constructor : this naming information is saved 
with the desktop and used on workspace reloading (this information is more detailed in <a href="tutorial11.php">lesson 11</a>).

----

Next [tutorial5]