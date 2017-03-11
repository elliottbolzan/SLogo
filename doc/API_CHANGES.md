API Changes
===

Over time our API has changed significantly. Originally we thought the API would just consist of a few public methods in two interfaces (one for the front-end and one for the back-end). 

However, we realized that this would require us to keep the entire front-end and back-end in just two packages. As a group, we agreed that this seemed like very sloppy design, so we began splitting up classes into different packages and adding public methods to allow them to communicate.

We felt this was a reasonable tradeoff: we increased the number of public methods a bit, but we did so in order to maintain a clear organizational structure at the package level. 

##### Change 1: `Point`
We were originally going to use `java.awt.Point` as a class to encapsulate coordinates in the TurtleDisplay. We have decided to make our own class (`utils/Point.java`) with double precision coordinates to support very fine-tuned movement of the turtle.

##### Change 2: `isPointInBounds`
For now we have decided to handle bounds checking within TurtleDisplay, which means we no longer need to provide this method to the back-end.

##### Change 3: `getTurtle`
We decided to add a method to get the turtle object from the front end so that the back end can get the location and rotation of the Turtle. 

##### Change 4: `getCommand`
We realized that we did not have to have a getter for our Command class, so we removed this method from our API.

##### Change 5: `getNumParameters`
While we parse through our input, we decided we needed a public `getNumParameters` for each command. We added this to make parsing smoother.

##### Change 6: `getPosition`, `hasPen`, `isHidden`, `getHeading`, `setPosition`, `setPen`, `setHidden`, `setHeading`
All of these methods pertaining to the turtle were moved to the front-end. This is because we decided the visualization of the turtle required the majority of these methods. As a consequence, we did not need to make these methods public or available to the back-end anymore.

##### Change 7: `setPenColor`, `getColorPalette`, `getImagePalette`, `setBackgroundColor`, `setShape`
We added additional commands that extended our basic implementation by adding the above methods.

##### Change 8: `throw TurtleOutofBounds`
We removed this exception because we decided to have a wrap-around, toroidal turtle. This is handled in the visualization, from the front end.

##### Change 9: `getTurtleManager`
As we transitioned to a multiple turtle environment, we chose to expose a `TurtleManager` object to the back-end instead of a `Turtle` one. As the `TurtleManager` provides a `getCurrentTurtle()` method, this change did not significantly modify the backend's logic.

##### Change 10: `printToConsole`
We renamed our `print` method to `printToConsole`, to distinguish between printing to our shell and printing to our script view. We didn't have a script view in our original implementation, so the distinction only became apparent during the second sprint.

##### Change 11: `getLanguage`
We realized we needed to add a `getLanguage` method to our API, in addition to a `setLanguage` one. Indeed, the front-end occasionally needs to display the current language, and that data resides in the back-end.