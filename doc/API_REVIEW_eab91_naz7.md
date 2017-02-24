SLogo API Review
================

Written by Elliott Bolzan (eab91) and Nikita Zemlevskiy (naz7).


## This is an analysis of Elliott's SLogo team's external front-end API.

My API/design contains the following methods:

```
public void print(String string);
public void clearConsole();
public void moveTo(Point point);
public void turn(double degrees);
public void setPenDown(boolean down);
public void setTurtleVisible(boolean visible);
public void clearDisplay();
public Dimension getDisplaySize();
public boolean isPointInBounds(Point point);
```

### Part 1

**What about your API/design is intended to be flexible?**

My API is designed to be flexible in the following ways:

- It provides a **variety** of methods. Some of these are not required yet / now, like: `clearConsole()`. The ability to clear the console is not required, but because it could be useful down the road, we chose to include it and be flexible.
- It is flexible in that several of its methods can be used in combination: `moveTo(Point point)` and `turn(double degrees)` can be used together to create new functionality.
- It allows for easily adding new methods. Because we have one top-level `View` class that implements the API, adding a new method is as simple as adding it to the `View`. From there, a coder can choose which front-end components to interact with.

**How is your API/design encapsulating your implementation decisions?**

My API encapsulates my implementation decisions in the following ways:

- `clearConsole()` and `moveTo(Point point)` do not reveal their implementation. These methods do not reveal which user interface components they make use of, or how they interact with them.
-  Likewise, `setTurtleVisible(boolean visible)` does not reveal the inner-workings of the turtle's visibility.
- Finally, `getDisplaySize()` returns a Dimension object, which is convenient for the caller, but does not reveal how this information is obtained / stored internally.

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

My API will not throw any exceptions.

However, my API will catch the exceptions thrown by the model. These exceptions are of the following types:

- **BadInput**: the user did not provide the proper input.
- **OutOfBounds**: the turtle would be out-of-bounds.
- **InvalidOperation**: the operation the user is trying to run is incorrect (i.e., `10 / 0`).

Once these Exceptions are caught by my `View` system, my view will present a dialog. This dialog will:

- Declare that an error has occured.
- Inform the user of the type of the error.
- Present an optional message provided by the model.

The text in this dialog will be given from a `Resources` file. 

**Why do you think your API/design is good (also define what your measure of good is)?**

I think an API is good when it provides the following characteristics:

- Simplicity.
- Extensibility.
- Power.

For each of these points, I think my API does a good job, in the following ways:

- **Simplicity**: my method names are self-explanatory. `print(String string)` and `clearConsole()` are clear names. In addition, the arguments that are to be provided to the functions are of simple types, and are named as one would expect. 
- **Extensibility**: I think my API is able to be extended because its methods can be combined. The methods are atomic: they do their job, and their job only. Bigger commands can be built from associating these functions in succession.
- **Power**: my API does what it purports to do, using few methods. It accomplishes a significant amount using one method, in many cases, like `moveTo(Point point)`: that method can move a turtle and draw a line, without exposing any of its implementation.

### Part 2

**How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**

The following Design Patterns are currently represented in the design: 

- **Chain of Responsibility**: right now, the `View` implements the API. It then calls its own components, based on which API method was called. The responsibility for fulfilling the request is delegated downwards, in terms of hierarchy.
- **Mediator**: the `View` class mediates between different components: a **TurtleDisplay** and a **Console**, for one. 
- **Observer**: when data is modified in the **Panel**, a part of the View structure, the Model structure will be notified through the **Observer** protocol.
- **Factory**: different `ListView` objects can be created with different parameters, without exposing the instantiation code, in the **View** structure.

Down the road, this API could make use of the following pattern: **NullObject** (when a **Command** is returned that requires no action on the View's part).

**How do you think the "advanced" Java features will help you implement your design?**

There is no need for **Reflection**, the first "advanced" Java feature that comes to my mind, in this code. 

That being said, the following two "advanced" Java features could be useful in the design:

- **Lambda functions**: when exchanging information between the View and the Model, the design could make use of **lambda** functions. The **Observer** principle is an alternative, and the final implementation should choose between the two options based on how much it wishes to rely on a Controller element.
- **Generics:** the design will need to present several different ListViews and TableViews. Generics could help with code compression. Indeed, the implementation would only need to use one component as a **ListView** factory, and could then use generics to pass in data to this component. This would prevent the existence of a high number of similar classes.

**What feature/design problem are you most excited to work on?**

I'm most excited to work on the **Console** and its implementation. 

There are several reasons for this:

- It presents **visual challenges**: how should I make it look like an actual console?
- The external API methods that relate to it, like `clearConsole()` and `print(String string)`, are relatively **easy to implement**.
- It needs to interact directly with the **Panel**, to be able to display commands the user has clicked on.
- In requiring the building of a user interface tool, practically from the ground up, it presents numerous interesting design challenges: a simple `TextArea` will not suffice.

Overall, the **Console** will be the component the user interacts with the most, and I look forward to building it. 

**What feature/design problem are you most worried about working on?**

In my portion of the project, the **Panel** is the feature I am most worried about working on.

There are two reasons for this:

- From experience, I know that areas like control panels easily develop clutter. Features are often progressively added to such a panel, and as a consequence, the class responsible for the features grows accordingly. I will seek to encaspulate all of the individual components for my panel in their own classes, to prevent such a build-up.
- The panel needs to interact with the model. From the panel, then, I will have to determine whether to interact with the model directly, or through a controller. This will probably be my toughest design decision in implementing my design for the **View**. 

Despite these minor causes for worry, I look forward to designing the **Panel** and integrating it to our front-end.

**Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**

1\. *A coder, for some new feature down the road, needs to implement a reset feature, that returns the shell and turtle display to its original state. How should this API be used to this end?*

- The model should reset its own state, first. This step does not make use of this API.
- The model should call the `clearConsole()` function on the view. 
- The model should call the `clearDisplay()` function on the view.
- Once these two commands have been called, the user interface should look fresh again.

2\. *A coder, working on the model, wishes to determine whether a point lies within 5 pixels of the edges of the turtle display. How should this coder use this API?*

- The coder cannot use the `isPointInBounds(Point point)` function alone, obviously. 
- The coder should use the `getDisplaySize()` method, and store the Dimension object that is returned.
- From this Dimension and knowledge of the coordinate system used throughout SLogo, the coder could determine whether his point is within 5 pixels of the turtle display's edge.

This is, I think, one of the main displays of flexibility on this API's part. The API **should not** provide a method to determine whether a point lies within 5 pixels of the edge of the turtle display, but the capability **should** be there – and it is.

3\. *A coder wishes to add a new feature: to account for toroidal edges to the turtle display. How would the model use this API to achieve this purpose?*

- The coder cannot use the `isPointInBounds(Point point)` function alone: this method would not the model **where** a turtle would have gone off screen.
- The model would instead need the size of the turtle display, to figure out at which coordinate the turtle would disappear – and where it should reappear.
- The model should call the `getDisplaySize()` method and store its result.
- From the dimensions returned by this method, the model can determine where the turtle will run off screen in its model, and where it should reappear using a toroidal edge system.

4\. *The user runs three commands in succession: he hides the turtle, moves it left by 50, and shows it again. How should this API be used during these commands?*

- After the back-end has parsed the first command into a **Command** object, that object's `execute()` method should call `setTurtleVisible(false)` on its reference to the front-end.
- After the back-end has parsed the second command into a **Command** object, that object's `execute()` method should call `moveTo(destinationPoint)` on its reference to the front-end, passing the point the **Turtle** should move to as a parameter.
- After the back-end has parsed the third command into a **Command** object, that object's `execute()` method should call `setTurtleVisible(true)` on its reference to the front-end.

5\. *The back-end wishes to determine whether a point is within the bounds of the **TurtleDisplay**. If the point is not in bounds, the back-end wishes to inform the user. How would the back-end use this API to achieve this task?*

- The back-end should call the `boolean isPointInBounds(Point point)` method with the point in question.
- Based on the result, the back-end can either continue, or raise an `OutOfBounds` exception.
- In the event an exception is raised, the **Console** will catch it, and the front-end will display a Dialog informing the user of the error.