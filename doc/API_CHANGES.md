API Changes
===

Over time our API has changed significantly. Originally we thought the API would just consist of a few public methods in two interfaces (one for the front-end and one for the back-end). However, we realized that this would require us to keep the entire front-end and back-end in just two packages. As a group we agreed that this seemed like very sloppy design, so we began splitting up class into different packages and adding public methods to allow everything to communicate.

##### Change 1: Point
We were originally going to use java.awt.Point as a class to encapsulate coordinates in the TurtleDisplay. We have decided to make our own class (utils/Point.java) with double precision coordinates to support very fine-tuned movement of the turtle.

##### Change 2: isPointInBounds
For now we have decided to handle bounds checking within TurtleDisplay, which means we no longer need to provide this method to the back-end.

##### Change 3: getTurtle
We decided to add a method to get the turtle object from the front end so that the back end can get the location and rotation of the Turtle. 

##### Change 4: getCommand
We realized that we did not have to have a getter for our Command class, so we removed this method from our API.

##### Change 5: getNumParameters
While we parse through our input, we decided we needed a public getNumParameters for each command. We added this to make parsing smoother.

##### Change 6: getPosition, hasPen, isHidden, getHeading, setPosition, setPen, setHidden, setHeading
All of these methods pertaining to the turtle were moved to the front end. This is because we decided the visualization of the turtle required the majority of these methods.

##### Change 7: setPenColor, getColorPalette, getImagePalette, setBackgroundColor, setShape
We added additional commands that extended our basic implementation by adding the above methods.

##### Change 8: throw TurtleOutofBounds
We removed this exception because we decided to have a wrap-around turtle. This is handled in the visualization for the front end.