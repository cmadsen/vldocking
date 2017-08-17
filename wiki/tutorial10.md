#summary Compound dockables
#sidebar TableOfContents
= Lesson 10 : Compound Dockables = 


This is the 10th part of the VLDocking Framework for Java Swing applications.

 
This lesson covers the Compound Dockables, which give a means to create nested hierarchy of dockables.


== Overview == 

Compound dockable containers is a feature available since VLDocking 2.1.


This feature adds support for nested containers, for example a Tabbed container in which would be nested splitted dockables.


[http://vldocking.googlecode.com/svn/wiki/compoundDockables.jpg]

_Compound Dockables (inside a tabbed dockable container)_


== How does it work ? == 


VLDocking now contains a specialized Dockable, called CompoundDockable.


Unlike Dockable which is an interface, `CompondDockable` is a class, and it comes with an 
associated Component : the `CompoundDockingPanel`. There shouldn't be any need to subclass  `CompoundDockable`.


You interact with `CompoundDockable` through its DockKey, which gives you a means 
to name it, to give it an icon and a tooltip (as that what's DockKeys are meant to be...).


Nesting dockables is performed with the new DockingDesktop method 
`addDockable(CompoundDockable base, Dockable dockable)` which can be used 
to start nesting, and then standard `split()` or `createTab()` 
methods can be used to alter the layout of the nested components.


 === Example : creating a tab containing two dockable (horizontal split) === 


{{{
DockingDesktop desk = ...
Dockable tab1 = ...;
Dockable tab2 = ...;
CompoundDockable compound = new CompoundDockable(new DockKey("Nested"));
Dockable nested1 = ...;
Dockable nested2 = ...;
desk.addDockable(tab1);
desk.createTab(tab1, tab2, 1);
desk.createTab(tab1, compound, 2); // compound is added as a tab
desk.addDockable(compound, nested1); // now we insert nested1
desk.split(nested1, nested2, DockingConstants.SPLIT_RIGHT); // and we split it
}}}




Of course, another way of dealing with nested dockable is to use the 
Workspace Manager Application 
and load directly your layout from a workspace XML stream.

----
Next : [tutorial11]