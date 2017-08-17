#summary Anchored dockables
#sidebar TableOfContents
= Lesson 12 : Anchored dockables = 


This is the 12th part of the VLDocking Framework for Java Swing applications.

 
This short lesson covers the topic of Anchored dockables (dockables with a limited set of movements alloweds, sticking to one or more borders)


== Overview == 


Anchored dockables are Dockable that remain at given positions on their desktop and that can prevent other dockable to be moved between them and their anchored border.

Anchors are specified in terms of borders : Top, Left, Bottom, Right.


For example, to have a dockable anchored at the bottom of its desktop, taking the full width of it, the anchors to use should be LEFT + BOTTOM + RIGHT.

== Usage == 


To activate anchoring, use the AnchorManager like this :


{{{
DockingDesktop desk = ...;
Dockable dockable1 = ...;
// create an anchor manager for a given context and a top level container (here : the desktop)
AnchorManager am = new AnchorManager(desk.getContext(), desk);
// and specify constraints (here : top + right)
AnchorConstraints constraints = 
     new AnchorConstraints(AnchorConstraints.ANCHOR_RIGHT|AnchorConstraints.ANCHOR_TOP); 
am.putDockableContraints(dockable1, constraints);
// and that's it, the top right corner of your desktop is now reserved to "dockable1".
}}}


The two object to understand here are {{{AnchorManager}}} and {{{AnchorConstraints}}}.


=== AnchorManager === 


The AnchorManager is responsible of the constraints enforcement for a given container 
(usually a DockingDesktop, but it could also be used with a CompoundDockableContainer).


It works as a DockingActionEvent listener, and analyses (and vetoes) the actions coming from 
Drag and Drop gestures.


=== AnchorConstraints === 


This object represent an anchor constraint.

 
A constraint is expressed by a logical OR between the following fields
{{{
  AnchorConstraints.ANCHOR_TOP
  AnchorConstraints.ANCHOR_LEFT
  AnchorConstraints.ANCHOR_BOTTOM
  AnchorConstraints.ANCHOR_RIGHT
}}}


To specify a dockable spread along a full side of the container, you have to OR the fields 
corresponding to this side, and the two adjacents sides.

Examples


{{{
  // top left corner :
  AnchorConstraints topLeft = new AnchorConstraints(
  	 AnchorConstraints.ANCHOR_TOP
	|AnchorConstraint.ANCHOR_LEFT)
  
  // full top side :
  AnchorConstraints fullTop = new AnchorConstraints(
  	 AnchorConstraints.ANCHOR_TOP                      // side
	|AnchorConstraint.ANCHOR_LEFT  			   // and borders
	|AnchorConstaints.RIGHT)
  
  // always on the left, but not taking full height :
  AnchorConstraints left = new AnchorConstraints(
  	 AnchorConstraint.ANCHOR_LEFT );
	   
}}}





== The End  == 

 And this is the end of the tutorial !


Feel free to contact us through the google code mailing list if you are having any trouble getting started with VLDocking, or 
with a specific feature of the framework. 

Lilian Chamontin