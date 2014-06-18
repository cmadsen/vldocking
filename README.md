# vldocking

This is a fork of https://bitbucket.org/akuhtz/vldocking

## Why ?

Because the original project seems to be dead :(

See https://code.google.com/p/vldocking/ for more information, tutorials etc.

## And so, what's new ?

* Switched from ant to mvn
* Renamed package names to use old vlsolutions instead of vldocking
* Released a 3.0.3 version available through maven central
* Fixed issues with read/wrilXML on multiple screens
* Added new empty ctor to `FloatingDialog` to make floating windows appear in taskbar. Extend `DefaultDockableContainerFactory.createFloatingDockableContainer` and return `new FloatingDialog()`
* Fixed LAF issues e.g., when switching Substance skin
* Fixed issue with borders not being set on single dock windows until an ancestor change events occured

## How to get the released versions ?

### Maven

Just add this to your `pom.xml` file:

The dependencies:

```xml
<dependency>
    <groupId>dk.navicon</groupId>
    <artifactId>vldocking</artifactId>
    <version>3.0.4</version>
</dependency>
```

VLDocking, the swing docking framework

The VLDocking framework is a commercial-grade Swing Framework providing rich docking features which can easily be integrated with existing applications (and of course new ones).

VLDocking has been created in 2004, and is licenced as under the LGPL terms.

VLDocking is used by companies worldwide and open source projects.

![](http://vldocking.googlecode.com/svn/wiki/vldocking3.jpg)

License
=======

VLDocking is licensed as LGPL

Contributions
=============

VLDocking was initially designed by Lilian Chamontin at his former company VLSolutions. 

Contributors are welcome !

Contact me through the mailing list at google groups.

https://groups.google.com/forum/#!forum/vldocking

Documentation
============

The VLDocking tutorial can be found here https://code.google.com/p/vldocking/wiki/tutorial1

## I found a bug, what can I do ?

Feel free to fork this repo, and make a "pull request":http://help.github.com/send-pull-requests/. Ideally, write a test !
 
