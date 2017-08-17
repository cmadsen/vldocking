#summary Working with heavyweight components
#sidebar TableOfContents

= Lesson 7 : Working with heavyweight components = 


This is the 7th and last part of the VLDocking Framework for Java Swing applications.

This lessons focuses on Heavyweight (AWT) components integration. 


Note : this feature is available since VLDocking Framework *1.1* and requires *Java 1.5*

<p align="center">[http://vldocking.googlecode.com/svn/wiki/heavyweight.jpg]<br>
The JOGL heavyweight sample application (<a href="/jogldockingdemo.jnlp">launch it </a> with java web start)


== Mixing heavyweight components - an overview == 

=== The problem === 


If you are familiar with Swing, you already know it's a bit tricky to have AWT
component included in a Swing application.


This is because AWT components are rendered by the os (they are called "heavyweight components"),
whereas Swing components are rendered by software, on top of a single heavyweight component (Swing
component are called "lightweight"). Swing components are independant from the OS as long at it can provide
a top-level heavyweight container.


When mixing lightweight and heavyweight components, you are basically trying to mix two
concurrent painting systems on your window. Unfortunately, the OS is always the winner, and
you end up with heavyweight component *always on top* of Swing components.


To avoid this problem, one has to avoid overlapping components, meaning :
  

 * don't use JInternalFrame
 * dont use a JScrollPane to contain a heavyweight component
 * use all possible workarounds provided by swing (in an effort to support mixing) :
tooltips and popup menus can be made heavyweight by a method call on their factories.
  

Concerning the Docking Framework, overlapping is limited due to the  relative positionning of
dockables, but it still can occur on the following situations :
  

 * a Dockable containing a heavyweight component is near a border of the desktop : it will overlap all auto-hide dockables coming from this border.
 * a lightweight Dockable is maximized (top layer of the desktop) :  the heavyweight components on the bottom desktop layer will appear right through it.
 * the custom rendering of drag and gesture (done on a glasspane above the desktop) is intercepted by heavyweight components.
   

=== But why is this feature important ? === 
 
You may ask "why implement such a support in the docking framework when swing is now
as fast as heavyweight components ?"
 
Here are some reason why we think it's important :
 

 * The *Open Cascade* renderer with java bindings provides a high performance simulation and 2D/3D rendering engine, and is used in industrial contexts.
 * The *Java3D* applications rely on an OpenGL or DirectX heavyweight binding and are used in many scientific/modeling projects.   
 * The *JOGL project *(and probably the future OpenGL JSR-231 official extension) provides an OpenGL pipeline to Swing applications. It is faster (especially visible when rendering animations) on low end systems when using its heavyweight renderer instead of the Swing one.
 * The *JDIC browser component* allows you to embed a native browser (much better than JEditorPane !) into a Swing application. Unfortunately, it's a heavyweight component. But include it in a Dockable and you application will rock ! (It's not yet perfect due to some resource management bugs in JDIC, but works fine with community workarounds in most cases)
 * Many *legacy applications* that have been adapted to be displayed as AWT Canvas / ActiveX are still beeing used(and will probably remain for a long time).
 

=== So is there a workaround ? === 
   
The answer is YES !
   
But, you must be aware it will work only beginning *with Java 1.5 * (J2SE 5.0).
   
The workaround is based on a new method : `Container.setComponentZOrder(Component comp, int order)` that has been
added to AWT in jdk 1.5. 

With this method, you can precisely define how heavyweight components overlapping is managed, with
a Z-order priority (lower value = on top). Before that, it was platform-dependent and changing the priority was not specified.
   
As Swing components can be included in the Panel class (an AWT container), we just had to ensure the
overlapping cases were managed with a correct Z-order and a proper heavyweight inclusion.
   
* Drawbacks*
    
The solution has some minor drawbacks : the drag and drop shape painting displays a grey rectangle when appearing
on top of some heavyweight components (for example in the case of JOGL).
    
And painting is not generally as smooth as when using pure Swing components, so the animation effects are turned off
when a heavyweight component is used.

== Adding heavyweight support to the VLDocking Framework  == 

 
Now than you know the pros and the cons of using heavyweight components in the docking framework, let's have a look at
how to enable it.

=== A single method call === 

It's easy, you just have to place a single method call * before using the DockingDesktop *, generally in
the main method of your application, or the pre-initialisation part of your application if it's more structured.


{{{
DockingPreferences.initHeavyWeightUsage();
}}}


 
And that's it !

  
Under the hood, this method also performs some additional settings : it calls
     {{{ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false)}}} and {{{
JPopupMenu.setDefaultLightWeightPopupEnabled(false)}}} to use the Swing standard workarounds
(so you don't have to do it yourself).

   
=== Dynamic swichting of heavyweight support  === 

Please note that you cannot currently switch dynamically between pure lightweight and heavyweight support.

Call us if you have such a need...


== Special workaround and optimization for the JDIC Web Browser component  == 


This is a new feature starting from VLDocking 2.0.2. 

The JDIC Browser (and may be other native components unknown to us at this time) has a severe limitation (a bug) that
made it very unfriendly for VLDocking : you cannot change its native peer ancestor. We have developped a workaround
for such cases, which works perfectly as long as you follow these simple rules :


 * The JDIC Browser (or any heavyweight component with the same limitations) must be the only native component used, in a single instance.
 * Support for the JDIC Browser starts from release 20050930
 * You have to turn on another DockingPreferences switch 


{{{
DockingPreferences.setSingleHeavyWeightComponent(true);
}}}



 * You cannot make the heavyweight Floating (as this option is disabled by default, just think about not turning it on)
 * And finally, you must create the WebBrowser instance with the {{{autodispose}}} flag set to false, and don't 
   forget to {{{dispose()}}} the browser when your window is closed (to release associated resources). 




How does it work ? Well it's rather simple : as we know the Browser is the only one heavyweight component around, we don't have to 
use our dedicated heavyweight container (used to manage the Z-order of multiple heavywieght dockable) as there will never be 
two components competing for beeing on top of the other. Simple, reliable, and easy !


You can download our <a href="/download/jdicdemo.zip"> JDIC sample application source code</a> to learn more on (and play with) the
JDIC Browser support. 

== Fixing slow repaint problems (mostly on Linux)  == 

VLDocking 2.1.4 introduces a new UI Property that can be used to disable background 
painting during drag and drop.


With this flag set, instead of displaying the Drop shapes above background components with transparency,
a straight grey colored background is shown, reducing the overhead of creating and displaying the 
background snapshot.


UIManager.put("DragControler.paintBackgroundUnderDragRect", Boolean.FALSE)

Note : You can also use this property on other systems : it is not specific to Linux (it's just that 
the slow repaint is more visible there).

----

Next : [tutorial8 Lesson 8 customizing the UI]
