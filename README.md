# vldocking

This is a fork of https://github.com/cmadsen/vldocking, a fork of https://bitbucket.org/akuhtz/vldocking

## Why?

Because upstream appears to have been abandoned, but bug fixes and new features were desired for [OmegaT](http://www.omegat.org/).

See https://code.google.com/p/vldocking/ for more information, tutorials etc.

## So what's new?

* Switched to Gradle
* Made an important fix for Linux (see https://github.com/cmadsen/vldocking/pull/9)
* Additional customization:
  * The background of `AutoHideButton`s is now customizable via the `AutoHideButton.background` key for `UIManager` (`Color` value)
  * The background of `AutoHideButtonPanel` is now customizable via the `AutoHideButtonPanel.background` key for `UIManager` (`Color` value)
  * Disable the gradient on `DockViewTitleBar` via the `DockViewTitleBar.disableCustomPaint` key for `UIManager` (`Boolean` value)
  * The border of the main `DockingDesktop` component can be set via the `DockingDesktop.border` key for `UIManager` (`Border` value)
* Released version 3.0.5 with the above changes

Changes introduced by cmadsen/vldocking:

* Renamed package names to use old vlsolutions instead of vldocking
* Fixed issues with read/wrilXML on multiple screens
* Added new empty ctor to `FloatingDialog` to make floating windows appear in taskbar. Extend `DefaultDockableContainerFactory.createFloatingDockableContainer` and return `new FloatingDialog()`
* Fixed LAF issues e.g., when switching Substance skin
* Fixed issue with borders not being set on single dock windows until an ancestor change events occurred

## How to get the released versions?

### Maven

Just add this to your `build.gradle` file's `dependencies` block:

```
compile 'org.omegat:vldocking:3.0.5'
```

VLDocking, the swing docking framework

The VLDocking framework is a commercial-grade Swing Framework providing rich docking features which can easily be integrated with existing applications (and of course new ones).

VLDocking was created in 2004, and is licensed under the LGPL-3.0.

VLDocking is used by companies worldwide and open source projects.

![](http://vldocking.googlecode.com/svn/wiki/vldocking3.jpg)

License
=======

VLDocking is licensed as LGPL

Contributions
=============

VLDocking was initially designed by Lilian Chamontin at his former company VLSolutions. 

Contributors are welcome!

Contact me through the mailing list at google groups.

https://groups.google.com/forum/#!forum/vldocking

Documentation
============

The VLDocking tutorial can be found here https://code.google.com/p/vldocking/wiki/tutorial1

## I found a bug; what can I do?

Feel free to fork this repo, and make a "pull request": http://help.github.com/send-pull-requests/. Ideally, write a test!
 
