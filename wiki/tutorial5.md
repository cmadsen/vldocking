#summary Extending the framework
#sidebar TableOfContents
= Lesson 5 : Extending the framework = 



This is the 5th part of the VLDocking Framework tutorial for Java Swing applications.


In this lesson, you will learn (basically) how to extend the VLDocking Framework. But
reading this lesson is not enough : you'll also have to browse the source code
to get a feeling on how to do things....



== Changing of DockableContainers == 

This chapter will show you how to replace the default DockableContainers by
others of your own (or an extended version of the default ones).


Let's begin by the {{{DockableContainerFactory}}}.

=== The DockableContainerFactory === 


The {{{com.vlsolutions.swing.docking.DockableContainerFactory}}} is an
abstract class used by the {{{DockingDesktop}}} to create instances of
Dockable containers.


When no factory is defined, it will rely on the {{{DefaultDockableContainerFactory}}}
implementation.


To change of factory, create a new Factory class by subclassing either the abstract
class or the default implementation, and implement/override the methods you
want to change.


Once your class is ready, invoke the static method {{{DockableContainerFactory.setFactory()}}}
and provide and instance of your class as parameter.


And this is it ! your factory has been adopted by the framework...


==== Factory methods ==== 


The methods you'd want to implement or override are :


  	 * {{{SingleDockableContainer createDockableContainer(Dockable dockable, int parentType)}}} : provide
a new container for this dockable, according to its container hierarchy ({{{parentType}}} may
be DockableContainerFactory.PARENT_DESKTOP, PARENT_TABBED_CONTAINER, 
PARENT_SPLIT_CONTAINER, or PARENT_DETACHED_WINDOW.<br>
    
  	 * {{{TabbedDockableContainer createTabbedDockableContainer()}}} : provide a
new tabbed dockable container.
   	 * {{{DockViewTitleBar createTitleBar()}}} : provide a
new customized header for single dockables.




Their default implementations return a {{{DockView}}} (or a subclass of DockView for floating and tabbed 
dockables) and a {{{DockTabbedPane}}} or a {{{DockViewTitleBar}}}.

=== Extending SingleDockableContainer === 


SingleDockableContainer is the interface that must be implemented by Containers
displaying one (and only one) dockable.


The default implementation is DockView, which comes with a draggable title bar
of the {{{DockViewTitleBar}}} class (see the factory method to replace it).


You can extend the DockView class to provide new decorations to your dockables, 
or to remove the title bar (which isn't
supported yet).

=== Extending TabbedDockableContainer === 


TabbedDockableContainer is the interface that must be implemented by Containers
displaying multiple dockables in a tabbed pane fashion.



The default implementation is DockTabbedPane, which is a JTabbedPane subclass,
with tabs at bottom.
  



You might want to implement your own TabbedDockableContainer, for example to
provide closeable tabs - a feature not yet supported by JTabbedPane, but that should
be available with the Mustang (java 1.6) release.

  
=== Extending a DockViewTitleBar : example of adding a JButton inside  === 

First, you will have to declare a new dockable factory and return your custom title bar :


{{{
class CustomDockableFactory extends DefaultDockableContainerFactory{  
  public DockViewTitleBar createTitleBar() {
    return new CustomizedDockViewTitleBar();
  }
} 
}}}


The custom title bar can then be declared like this :


{{{
class CustomizedDockViewTitleBar extends DockViewTitleBar {
  CustomizedDockViewTitleBar(){
  }
  
  protected void layoutTitleBar(){
    // this method is responsible for laying out the buttons in the title bar (jpanel) 
    super.layoutTitleBar();
    
    // insert a button before the "hide" button
    int buttonIndex = 0;
    for (int i=0; i &lt; getComponentCount(); i++){
        Component child = getComponent(i);
        if (child == getHideOrDockButton()){
          buttonIndex = i;
          break;
        }
    }
    
    final JButton btn = new JButton("test");
    btn.setBorderPainted(false);
    btn.setFocusable(false);
    btn.setContentAreaFilled(false);
    btn.setToolTipText("Click me !");
    Insets buttonMargin = new Insets(0, 0, 0, 0);
    btn.setMargin(buttonMargin);
      
    add(btn, buttonIndex);
      
    btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("clicked !");
        }
      });
    }  
  }
}}}


=== Adding new types of containers === 


This is possible, but will require modifications on the DockingDesktop class.


Why would you create new types of containers ? a good reason could be to
display things in a non-standard way : circular layout, rollover
expandable components,...


This could be tricky... please contact us in case of doubt, we could give you
some advices.


The path to follow would be :
 

   	 * add new methods to the DockingDesktop reflecting the new ways of
     adding dockables (think about {{{split}}}, {{{createTab}}}...).
   	 * create your own DockableDragSource for the new containers (don't forget
     to declare them to the desktop with {{{desktop.installDragSource()}}}).
   	 * implement DockDropReceiver on your container or one of its children to
     enable sub-docking.
   	 * ... test your classes !
 
----

Next [tutorial6]