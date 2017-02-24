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

## This is an analysis of Nikita's SLogo team's external back-end API.
	
### Part 1

#### What about your API/design is intended to be flexible?
We designed our API to be flexible with respect to adding new commands. This can be seen in our inheritance hierarchy for commands. It is easy to add a command by just adding a new class that inherits from the `Command` superclass.  Additionally, we designed scripts to behave exactly like commands. Because of this, there was no need to write extra code to represent scripts. This provides flexibility if additional functionality or plugins were to be written for the IDE on the frontend side. 

#### How is your API/design encapsulating your implementation decisions?
The API for commands has one main method, `execute`, which returns the return value of the executed command. This encapsulates all of the functionality of specific commands, making it therefore unnecessary for an outside user to know how a command works. This is the main encapsulation element of the backend. Additionally, Additionally, functionality of the `Parser` is encapsulated, in that the outside user is only able to call `parse`. This is useful because the outside caller does not have to worry about any string processing, and just hand the entered string to the user.

#### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
One exception that occurs in the backend happens in the `Parser` class in the method `parse`. This occurs when a command entered is not matched to any existing or user defined commands. These exceptions are thrown and caught (1) when the command is parsed and (2) when a command instance is attempted to be created from the string parsed in. These errors are handled inside the `parse` method. They are caught and the front end is updated accordingly, displaying to the user the cause of the error (the offending string entered). This happens with variables as well. 

#### Why do you think your API/design is good (also define what your measure of good is)?
I believe that my API design is good because it is flexible and extensible. The main task of the backend is to handle many different commands, which suggests that it should be easy to add new kinds of commands in the future. This has been handled in our design. Next, it is also possible to add functionality to the parser or create a new kind of parser that behaves differently. All one would need to do to make this is implement the methods in the `ParserInterface`. This enforces all of the functionality required for a proper parser. 

### Part 2

#### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
The factory design pattern is represented in the design of the `Parser`. It handles creating the different kinds of `Command` instances based on what is entered by the user. Additionally, The design uses the chain of responsibility design pattern. This is seen when the front end portion of the project calls the `evaluate` method, which in turn calls the `parse` method to parse the entered code, which in turn creates commands and tells them to `execute`, which in turn calls other private methods which carry out `Command` specific functionality. 
#### How do you think the "advanced" Java features will help you implement your design?
Reflection is used to create different instances of `Command` objects, determined by the string entered by the user. Regular expressions are also used to parse the command entered by the user, in order to create those commands using reflection. Generics may also be helpful for reducing duplicated code in the `Commands` inheritance hierarchy.
#### What feature/design problem are you most excited to work on?
I am most excited to work on the `Parser` and `Command` hierarchy. This is an interesting feature and also an interesting problem from the design side, because it will be tricky to set up all of the commands and have them check the `VariableTable` and `CommandTable`. 
#### What feature/design problem are you most worried about working on?
I am worried about the amount of work to be done in the `Command` hierarchy. Additionally, figuring out user defined commands will be hard, because we chose to represent them as regular commands. This will be interesting but hard to figure out. 
#### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams)
1. *The user defines a new command* The user will define a new command in the frontend. This will be passed into the backend. The backend will parse the `TO` command and create a new `ToCommand` instance. The arguments will be parsed and added to the command. Next, the command will `execute`. This will create the new command as the user requested and add it to the `CommandTable`. If the command is created successfully, 0 will be returned and printed to the user. Otherwise, 1 will be returned to the user on the console. 
2. *The user enters a non existing command* The `Parser` will try to parse the command. It will not be found, and it will not exist in the user defined `CommandTable` either. An exception will be thrown and `parse` will catch it and update the front end with the offending command.
3. *The user enters fd 50* The command will be created as usual by the method `parse` in the class `Parser`. Then, the command will be executed and 50 will be returned to the front end to be printed on the console. 
4. *The user updates a variable from the UI* The variable that the user clicked on will be notified of the update along with the new value. This will call the `update` method in the corresponding `Variable` object, and will set the new value of the variable, and update it in the `VariableTable`.
5. *The user executes a script from the UI* The script will be passed to the `evaluate` method. The script will be then fed to `parse` and will be evaluated command by command the usual way. The final value will be returned to the user. 