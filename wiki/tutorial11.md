#summary Multi-desktop applications
#sidebar TableOfContents

= Lesson 11 : Multi-desktop applications = 


This is the 11th part of the VLDocking Framework for Java Swing applications.
 
This lesson covers the topic of Multiple Desktop applications (applications that are made of many docking desktops sharing a common set of dockables)


== Overview == 


Sharing dockables across Docking Desktops is a new feature provided by VLDocking 2.1


To support this, a new class had to be introduced : the `DockingContext`.


Two (or more) desktops sharing the same context can exchange dockables with drag and drop gestures, as if they were parts of the same hierarchy.

[http://vldocking.googlecode.com/svn/wiki/multipleDesktops.jpg]

_Two destops, sharing their dockables_


It is also possible to have two desktop with different contexts. In that case, Drag and 
Drop between these desktops will be ignored.

== Usage == 


To enable context sharing is easy : just use the new `DockingDesktop` constructors : 


Example : 
{{{
DockingDesktop desk1 = new DockingDesktop("desktop one");
DockingContext sharedContext = desk1.getDockingContext();
DockingDesktop desk2 = new DockingDesktop("desktop two", sharedContext);
// and that's it ! Now desk1 and desk2 can share their dockables.
}}}



== Remarks == 


Loading and saving desktops are now forwarded to the DockingContext. This means you j
ust have to call readXML()/writeXML() once per context (i.e., not per desktop).


There are two ways to acheive this : 


	 * Select a desktop, and invoke readXML() / writeXML() from it.
	 * Get the DockingContext from one of the desktops (desk.getDockingContext()), and then invoke the readXML() and writeXML() methods from there.




In the case of an application with two or more contexts (i.e. independant desktops), the readXML() and writeXML() method have to be invoked on each set of desktops (on each context).

----
next [tutorial12]