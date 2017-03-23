Design Specifications - Team 10
=============

Written by Elliott Bolzan (eab91), Jay Doherty (jld60), Dennis Ling (dl186), and Alex Zapata (az73).

### Introduction

Our "Simple Logo" program is intended to provide an integrated development environment that allows users to write Slogo programs. We are attempting to solve the problem of developing a flexible, extensible set of API's that work together with the GUI to create a positive IDE experience for the user. The primary design goals of our program are to separate unnecessary dependencies between the front-end and back-end and to create a minimal, yet useful and extensible API. 

In particular, we have drawn clear lines among our four necessary APIs for the internal/external back-end and front-end that allow us to control the flow of dependencies and necessary objects and data. We will utilize the use of java Interfaces, superclasses, and subclasses in order to minimize duplicated code and to allow our code to be the most flexible; our program will be extremely flexible in the cases where we may need to implement an Interface or extend a superclass. 

The primary architecture of our program will maintain mostly private classes unless the need for the exchange of data are necessary among our classes; this will allow for the open extension of commands, displayed objects on the GUI's, turtle properties, and more while closing off access to most (private) inner methods and classes that the user and other parts of the program do not need to see.

### Design Overview

The front-end and back-end of our design is divided in the following way:

![](images/overview.png)

The basic idea of our organization is that the front-end will detect user input, and the back-end will handle it. When the front-end detects a new input, it will pass it to the the back-end as a String to be parsed. Once the back-end has finished parsing, it will create a Command object. This Command object has a single public method that can be called to execute it. If a Command gets its arguments from the return value of another Command, then it will first execute that Command. In this way the chain of execution gets pushed out in a tree-like structure. The front-end provides Strings and the back-end provides ready-made Commands, and the front-end doesn't need to know or care about how these commands get made, it just knows that all commands can be executed.

The rest of the front-end external API is provided to make Commands powerful. They should be able to do interesting stuff like modifying display colors or moving the Turtle around screen, so methods that support this behavior must be provided by the front-end. 

The rest of the back-end external API is provided to make the UI powerful. While `parse(String input)` provides the main functionality in terms of executing commands, other kinds of input might need to be handled differently. The UI should be able to see the history of commands and the current state of variables, so methods that support this behavior must be provided by the back-end.

The front-end internal API is focused on providing features that do not require any processing by the back-end. This should include changing the color or formating of the display, changing the input language, and viewing lists of old commands or variables. 

The back-end internal API is once again focused on providing features and ways of changing the back-end processing that the front-end does not care about. Part of this extensibility is built into the Command interface, which allows an obvious way for new commands to added to the Slogo language. 

### User Interface

Our user interface for this project will look as follows:

![](images/ui.png)

Three major components are visible on this image:

1\. **The Input Area**: this section, on the left, accepts the user's text input.

It is divided into two sections:

- The shell, in which the user can type commands and execute them one-by-one by pressing the `Enter` key. Pressing the `UP` key on the keyboard will let the user view, and optionally run, his or her past commands.
- The script editor, from which the user can load, save, edit, and run scripts.

The results from the executed commands are run in the following section, the `TurtleDisplay`.

2\. **The `TurtleDisplay`**: this section, in the center, contains the turtle and its drawings. The user should be able to view the turtle's movement in this section.

In addition, the user can control the animation using buttons located to the bottom of this display. 

- Pressing the `Play` button will play a paused animation.
- Pressing the `Stop` will pause an animation.
- Pressing the `Step` button step through movement commands that are queued up one-by-one.

Finally, the display contains a slider that controls the animation's speed. Additional customization options can be found in the `Control Panel`.

3\. **The `Control Panel`**: this section, on the right, contains the following items:

- The `user's command history`: past commands are made clickable in this section.
- The `user's variables`: set variables are displayed and can be edited. In addition, the user can either load or save variables using the corresponding buttons.
- The `user's commands`: displays clickable commands that the user has defined.
- The `settings`: displays the workspace's settings and makes them editable. Allows for the saving of default settings.
- The `colors`: associates default colors to indices.
- The `images`: associates default turtle images to indices.
- The `turtle settings`: displays each turtle's location and heading. Also allows for the movement of individual turtles and the modification of each turtle's active property.

----

There are several possible errors that the program can encounter when an individual is using it. These errors will all be displayed using a JavaFX Dialog. The following errors will be handled:

- Invalid input.
- Command does not exist.
- Faulty comment.
- Attempt at redefining a command.
- Invalid arguments.
- Could not write to file.
- Could not read from file.

An example of such an error dialog would be:

![](images/error.png)

We chose to group all the customizations options under a `Control Panel`. We had several reasons for designing our program this way:

- To prevent our `TurtleDisplay` from becoming cluttered and to provide our `Turtle` with the most possible real estate.
- To accomodate further customization options in a more flexible manner. 
- To let the user selectively show and hide the customization panels.

**Note**: our program allows for multiple `Workspaces`. Each `Workspace` contains all of the previously cited user interface elements.

### API Details

Here are the details for our four APIs:

#### Front-end External:

This API's methods are those that the back-end needs to have access to. Here are the methods made available by the front-end external API: 

```
// General Methods
public void clearConsole();
public void clearDisplay();
public TurtleManager getTurtleManager();
public void printToConsole(String string);
public void showMessage(String message);
public void setBackgroundColor(Color color);
// Turtle Methods
public int getID();
public void moveTo(Point point);
public void turn(double degrees);
public Point getDestination();
public double getRotation();
public boolean isPenDown();
public boolean isPenDown();
public void setPenDown(boolean down);
public Color getPenColor();
public void setPenColor(Color color);
public int getPenColorIndex();
public void setColorIndex(int index);
public void setPenWidth(double width);
public boolean isVisible();
public void setVisible(boolean visible);
public void setImage(String url);
public int getShapeIndex();
public void setShapeIndex(int index);
// TurtleManager Methods
public ObservableList<Turtle> getActiveTurtles();
public void setActiveTurtles(List<Integer> indices);
public Map<Integer, Turtle> getAllTurtles();
public Turtle getTurtleByID(int ID);
public Turtle getCurrentTurtle();
public void setCurrentTurtle(Turtle turtle);
public void setTurtleImage(int imageIndex, String URL);
```

**How does this API support features from the assignment specification?**

This API supports these features:

- *See the results of the turtle executing commands displayed visually*: the API's `moveTo(Point point)`, `turn(double degrees)`, `setPenDown(boolean down)`, `setVisible(boolean visible)`, and `clearDisplay()`, for example, are functions used to accomplish this task.
- *Support multiple turtles*: the API's `int getID()` method, as well as its `ObservableList<Turtle> getActiveTurtles()`,  `setActiveTurtles(List<Integer> indices)`, and `getCurrentTurtle()` methods, for instance, are crucial to accomplishing this task.
- *See errors that may result from entered commands in a user friendly way*: the API's `showMessage(String message)` method serves this purpose.
- *Set a background color for the turtle's display area*: the API's `setBackgroundColor(Color color)` serves this purpose.
- *Set an image to use for the turtle* and *set a color to use for the pen*: the API's `setTurtleImage(int imageIndex, String URL)` and `setPenColor(Color color)` serve this purpose.

This front-end external API supports most of the graphic requirements from the specifications.

Some features, like *clearing the console*, are included here because we think they might be useful down the road – even though they aren't part of our current assignment specifications.

**What resources does this API use?**

This API will make use of the following classes or resources:

- The `Input Area`: printing to and clearing the `ShellView` will necessarily require a reference to the `ShellView` object.
- The `TurtleDisplay`: moving or turning the turtle, for instance, will require a reference to the `TurtleDisplay` object. 
- The `Turtle`: setting whether the pen is active, or hiding the turtle, will require a reference to the Turtle object.
- The `TurtleManager`: obtaining the active turtle, for example, will require a reference to this resource.

**How is this API intended to be used?**

This API is intended to be used from the back-end.

We expect the following, general use-case:

- The `execute()` function, located in the `Command` class in the back-end, gets information from this API (`getCurrentTurtle()` could be called, for instance). 
- The back-end computes a new value using the data obtained from the API.
- The back-end tells the front-end to update its visualization, using methods from the API like `moveTo(Point point)` or `showMessage(String message)`.

**Note**: this API is not intended to compute new destination points or do math of any sort. It is simply designed to display the results of calculations computed in the back-end.

**How can this API be extended?**

This API can be extended in the following ways:

- By **making use of** current methods in conjunction. Consider the following scenario: a coder needs to implement a new feature, in which the `TurtleDisplay` is cleared and the `Turtle` is moved to the bottom left of the screen – not the center. The coder could construct this feature using the `clearDisplay()` and `moveTo(Point point)` methods in conjunction.
- By **adding** methods. While this method is not ideal because it requires an update to the entire API, it does remain a possibility.

Ideally, we would like this API's methods to be closed for modification: they were designed to fulfill a role, and they fulfill it. 

New features should be constructed by either combining functionality available in the current methods, or, less ideally, by creating entirely new methods in the API. 

**Why are we introducing these classes?**

In discussing this front-end external API, we mentionned several classes: the `Input Area`, the `TurtleDisplay` and the `Turtle`. Here, we justify the inclusion and creation of these classes.

- `Input Area`: intuitively, a program like **SLogo** that requires a user's input to a shell will need a `ShellView` class. This API needs this class to exist, in order to be able to print to the shell and clear it, eventually. Additionally, it makes sense for this class to be its own abstraction: the user interface classes will get large and cluttered if they are not split into several functional modules.
- `TurtleDisplay`: likewise, a program that wants to visualize the movements of a turtle across a screen will need a class to control that space. This API contains `moveTo(Point point)` and `turn(double degrees)` methods, so it makes sense to create a standalone class in which these events can take place. 
- `Turtle`: finally, it is normal for a program that wishes to control a turtle to have a `Turtle` class. Why, one could ask, is the turtle not simply an image controlled from the `TurtleDisplay`? This API has methods like `setPenDown(boolean down)`; those methods control instance variables that conceptually belong to the `Turtle`. As a consequence, it makes sense to have a `Turtle` class to encapsulate those variables.

#### Front-end Internal:

The purpose of this API is to provide methods that the front-end will use to implement features that are exclusively handled within the front-end. These methods should provide areas for extension, and they should be able to be overrode without impacting the back-end. Over the course of the project, this API got significantly smaller as we found ways to take advantage of JavaFX bindings that made it in some cases unnecessary for the front-end objects to communicate with each other (ie, we removed the concept of an `updateVariables` method and had `getVariables` in the back-end API return an ObservableList instead of a plain List). Many of these methods were also moved to the front-end external API if we decided they should be accessible to the back-end as well.

```
// General Access Methods
public Controller getController();
public TurtleDisplay getDisplay();
public ShellView getShell();
public Defaults getDefaults();
// Default Initialization Methods
public Color getBackgroundColor();
public int getNumberOfTurtles();
public String getLanguage();
public Image getTurtleImage();
public String getScriptPath();
// Retired Methods (either made external, or moved to the back-end)
public void setLanguage(String language);
public void showHelp();
public void showHistory();
public void updateVariables();
public void updateCommands();
```

**How does this API support features from the assignment specification?**

The following features are listed with the commands that implement them:

* *See commands previously run in the environment (even better, make them directly clickable to execute)*: originally implemented by `showHistory()`, but now commands are stored in an ObservableList that is stored and accessed through `getHistory()` in the back-end external API.

* *See variables currently available in the environment*: originally implemented by `updateVariables()`, but now variables are stored in an ObservableList that is stored and accessed through `getVariables()` in the back-end external API.

* *See user-defined commands currently available in the environment* originally implemented by `updateCommands()`, but now user-defined commands are stored in an ObservableList that is stored and accessed through `getUserDefinedCommands()` in the back-end external API.

* *Choose the language in which SLogo commands are understood* originally implemented by `setLanguage(String language)`, but we decided it made more sense for the back-end to keep track of the language file it is using, since it needs to use it to interpret commands, so now this feature is implemented by the back-end external API.

* *Access an HTML formatted help page*: originally implemented by `showHelp()`, but is now fully implemented by the constructor of the `HelpView` object.

**What resources does this API use?**

- The `Controller`: this API provides `getController()` to aid communication between various components, so it needs a reference to the `Controller`.
- The `TurtleDisplay`: this API provides `getDisplay()` to aid communication to the display elements, so it needs a reference to the `TurtleDisplay`.
- The `ShellView`: this API provides `getShell()` to aid communication to the shell to modify text in the shell, so it needs a reference to the `ShellView`.
- The `Defaults`: this API provides `getDefaults()` to let objects check on the configuration settings for the environment, so it needs a reference to the `Defaults`.

**How is this API intended to be used?**

This API is intended to specify the methods that are internal to the front-end, so it should be used to implement front-end specific features and provide an extendible interface. 

We would expect this API to be used in the following scenarios:
* The program is starting up and needs to find the default configuration settings
* The user wants to change the visuals of the Turtle Display (change colors or images)
* The user wants to see old commands
* The user wants to add a variable and see its value on screen
* The user wants to add a custom command
* The user wants to see help from a web page

**How can this API be extended?**

This API can be extended to include more UI-specific features, like more sidebars to display different kinds of information about the state of the environment. New methods could also be added that give more power to the GUI. Right now, Commands are very powerful, and there isn't much that the GUI can do that the user couldn't also do by entering a Command. This is why methods like `setBackgroundColor(Color color)` got moved to the external API, to make things more flexible. But you could just as easily add methods to this API without adding a corresponding command. Something like `setDisplayBounds(int width, int height)`. But to be honest, that feature is pretty much implemented by dragging the display edges, and the display is smart and binds all of its elements to adjust their layout if its size changes. That seems to be a theme with this API: there are lots of features that are solved by JavaFX bindings that remove the need to have clunky methods for passing everything around in the front-end.

**Why are we introducing these classes?**

- The `Controller`: As mentioned, we have a Controller object that this API references. The purpose of the Controller is to provide a bridge between the front-end and back-end. We then have a `getController()` method so that all front-end objects communicate with the back-end through a single Controller instance.
- The `ShellView`: As mentioned, we have a ShellView object that this API references. the purpose of the ShellView is to encapsulate the components of the display that deal with handling user input to the shell. We then have a `getShell()` method so all the front-end objects can communicate with that shell and modify its text, for instance if the user clicks on a command in the command history panel.
- The `Defaults`: As mentioned, we have a Defaults object that this API references. The purpose of the Defaults is to encapsulate the configuration settings for starting the program. Default language, display color, turtle image, number of turtles, and script path are all kept in this object. We then have a `getDefaults` so all the different front-end components can check on the value of these parameters so that everything can be initialized properly.

#### Back-end External:

The purpose of this API is to supply the front-end with necessary methods that pertain to the parsing, commands, turtle, or exceptions. Here is an initial list of methods made available by the back-end external API:

```
// Command methods
public void setup(Controller controller, State state)
public Argument evaluate()
public Node parse(String input, boolean addToHistory);
// Observable lists for automatic updating
public ObservableList<String> getHistory();
public ObservableList<Variable> getVariables();
public ObservableList<String> getUserDefinedCommands();
public ObservableList<IndexedColor> getColorPalette();
public ObservableList<IndexedImage> getImagePalette();
// Language methods
public void setLanguage(String language);
public String getLanguage();
// Edited methods or those moved to Front End
public Command parse(String input);
public List<String> getHistory();
public String getPreviousCommand(int k);
public Point getTurtlePosition();
throw BadInput();
throw InvalidOperation();
throw TurtleOutofBounds();
public void execute();
public List<Variable> getVariables();
public void setVariable(Variable variable);
public List<Command> getUserDefinedCommands();
```

**How does this API support features from the assignment specification?**

* *Recognize these basic commands*  -- implemented by `public Node parse(String input, boolean addToHistory);` which parses the string input and returns the correct command to the front-end.

* *Throw errors that result from incorrectly entered commands* -- implemented within the parser by calling the `public void showMessage(String message);` which shows a message when the command is incorrectly entered.

**What resources does this API use?**

This API will make use of the following back-end resources:

- The **State** class, which will maintain the necessary Observable Lists for the history, variables, etc.
- The **Node** superclass, which hosts the methods that all commands will necessarily extend.
- The **History** data structure, which will maintain a comprehensive history of the commands that have been processed.

**How is this API intended to be used?**

This API is intended to be used by the front-end. We expect the front-end to be able to:

- Obtain the history and display commands as needed
- Know what type of exception to throw and display when an error occurs by using the showMessage method from the front end

**How can this API be extended?**

This API can be extended by:

- Editing current methods to be more flexible and comprehensive. For example, if the front-end needs more data on the turtle besides just the position, we can return a more comprehensive list of the Turtle data besides just the position (including a boolean for pen, a double for heading, etc.).

- Adding public methods that the front-end will be granted access to. In the case where more objects/data are necessary for front-end processing, more getters can be made available.

**Why are we introducing these classes?**

The primary purpose of these methods is to allow the front-end to function smoothly by providing the required resources. The parser is necessary and intuitive as it provides the resource to parse through the input and to return a command. The History data structure maintains the list of commands as a resource available to both the front end and back-end. The Turtle class object will have public methods that allow the front-end to access whatever data it needs for Turtle related inquiries that are kept updated in the back-end.

#### Back-end Internal:

The purpose of this API is to provide methods for the back-end that will be used to implement the functionality of our program. These methods will supply the necessary resources for our back-end logic to function smoothly; here is a list of methods that we believe we will need:

```
public Command getCommand();
public Command parse(String input);
public Variable getVariable();
public void setVariable(String key);
public void addHistory();
public Point getPosition();
public boolean hasPen();
public boolean isHidden();
public double getHeading();
public void setPosition();
public void setPen();
public void setHidden();
public void setHeading();
```

**How does this API support features from the assignment specification?**

* *Recognize these basic commands*  -- implemented by all of our necessary `getX()` and `setX()` methods that will update the required variables and maintain the data/objects as needed.

* *Throw errors that result from incorrectly entered commands* -- implemented by the parse command by recognizing when an incorrectly entered command is given as the input string.

**What resources does this API use?**

This API will make use of several classes as resources (super and sub-classes) such as the following:

- The **Command** object, which is a superclass to any potential command. Subclasses will include the LogicCommand, MathCommand, TurtleActionCommand, and TurtleLogicCommand.
- The **Turtle** object will maintain all of the necessary information about the turtle, including the position, heading, pen, and hiding data.
- The **Parser** class, which will host the history and catching of errors.
- The **Variable** class, which will hold the creation of all variables throughout the running of the program.

**How is this API intended to be used?**

This API is intended to be used as a way to supply information throughout the back-end. With these methods, we can:

- Add to our history, 
- Update our Turtle information 
- Maintain variables

Additionally, the parser will be used as a way to catch exceptions and throw them to the front-end to display.

**How can this API be extended?**

This API can be extended by:

- Extending our current hierarchy of super and sub-classes. For example, if we need to add another math command, we can extend the MathCommand class (which extends the Command class).

- Add public methods that supply information that the back-end will need. In the case where more data about the Turtle may be needed, we can simply add more methods to access this information to make it available throughout the back-end classes. 

**Why are we introducing these classes?**

The primary goal of adding all of these classes is to grant a cohesive way to manipulate data throughout the back-end. The Parser class is necessary to return the correct command and to check for errors, as listed in the design write-up. The Turtle class will be used as an efficient way to store all of the information we need that relates to each Turtle object. The Command superclass will be easily extensible with the use of layers of subclasses in order to maintain a clear heirarchy of commands. These classes will maintain the logic and functionality of much of the program.

### API Example Code

We seek to go through the following use-case: 

*The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.*

Here are the steps necessary to complete that task:

- When the user presses `Enter`, the `ShellView` calls `parse(String string)` (in `public class TreeParser`) using the user's input.
- The command is added to the environment's history. This is done internally by the back-end, using its `State` class and the user's textual input.
- The `evaluate()` method is called, from the back-end, on the parsed`Command`.
- From the `Command`'s `execute()` method, the destination point for the turtle is computed from the current point (obtained using `Point getDestination()` (in `public class Turtle`) and the 50 pixel increase. The `moveTo(Point point)` method (in `Turtle`) is called with the new destination. The position of the Turtle is then updated from the `TurtleDisplay`'s `moveTurtle(Turtle turtle, Point point)` method.
- The user sees the turtle move to its new point in the `TurtleDisplay`, leaving behind a trail.

More use cases follow, two for each API. 

#### Front-end External:

1\. *The user runs three commands in succession: he hides the turtle, moves it left by 50, and shows it again. How should this API be used during these commands?*

- After the back-end has parsed the first command into a `Command` object, that object's `execute()` method should call `setTurtleVisible(false)` on its reference to the current turtle (a reference obtained through the `TurtleManager`).
- After the back-end has parsed the second command into a `Command` object, that object's `execute()` method should call `moveTo(destinationPoint)` on its reference to the current turtle, passing the point the `Turtle` should move to as a parameter.
- After the back-end has parsed the third command into a `Command`object, that object's `execute()` method should call `setTurtleVisible(true)` on its reference to the current turtle.

2\. *The back-end wishes to create a new `Command`. Its objective: to temporarily make certain turtle active, move them forward by their ID times 50, and restore the old list of active turtles. How should this API be used to achieve this?*

The back-end should follow this sequence of steps:

- The back-end should obtain and save a `List` of active turtles using the `ObservableList<Turtle> getActiveTurtles()` method.
- The back-end should call `moveTo(Point point)` on the current turtle. The `point` parameter should be computed by the back-end, using the `Turtle`'s current position and its `getID()` method. **Note**: the `TurtleManager` will automatically apply the necessary changes to all active turtles.
- The back-end should restore the old list of active turtles using the `setActiveTurtles(List<Integer> indices)` method, passing the `List` obtained in the first bullet point as a parameter.

#### Front-end Internal:
1\. *The user wants to re-run an old command. How would the front-end use this API to achieve this task?*

- When the user presses the up arrow key to prompt the display, `showHistory()` should be called and a pop-up scrollable list of commands should appear. The user should be able to navigate this list with the arrow keys.
- When the user finds the command they wish to execute and click on it or highlight it and press enter, the `runCommand(String command)` method should be invoked.
- At this point, the program will invoke `parse(String string)` on the command, and execution of the command will be handled as described in the other APIs. The job of the internal front-end API is just to provide the command selection list.

2\. *The user tries to set a new image for the turtle, but the file is corrupted and does not load properly. The UI should display an error and abort changing the turtle image. How would the front-end use this API to achieve this task?*

- To change the turtle image, `setTurtleImage(String path)` is invoked by passing it the the file path as a String.
- When the front-end tries to open the image and gets an IOException, it will invoke `showMessage(String message)` to display an alert and inform the user that there is an issue with their file.
- Depending on the implementation, it may be necessary to re-invoke `setTurtleImage(String path)` on the previous path to properly reset the image, but in all likelihood this will not be necessary and will just be handled by the interrupt caused by the Exception.

#### Back-end External:
1\. *The user wants to obtain the previous command, but incorrectly enters a badly formatted command instead.*

- The command will be sent to the `public Command parse(String input)` method, which will attempt to parse the String.
- Upon realizing the String is incorrectly formatted, the `parse` method will throw a `badInput();` error to the front-end.
- The front-end will receive the exception and will display the correct exception on the GUI.

2\. *The user wants to obtain the previous command, and correctly inputs the String command!*

- The command to obtain the history will be sent to the parser `parse(String input)`, which will correctly return the previous history Command. 
- The front-end will receive this command, and will run `execute()` on this particular command.
- In the `execute()` method, the back-end will supply the front-end with the correct previous command with `getPreviousCommand(int k)` depending on the integer that the user specified. 
- The `print()` method in the front-end is called in order to notify the user what the previous command was.

#### Back-end Internal:
1\. *The user moves the Turtle forward 5, and then forward another 6, but the grid is only 10 lengths long.*

- The first forward command is sent to the parser `parse(String input)` method, which parses the command and sends it to the front-end.
- The front-end calls `execute()` on *forward 5* in the back-end.
- The command will call `setPosition()` to update the Turtle position to be 5 more of its initial position.
- Control is returned to the front end to update the position on the GUI.
- The second forward command is parsed and execute is called again.
- Upon execution, the logic will check if `setPosition()` will make the position of the Turtle be larger than the Grid. 
- In this case, it will, so an exception will be thrown to the front end to display.

2\. *The user wants to use the TOWARD command, but chooses an inappropriate point that exceeds the bounds of the screen.*

- The first command that is called is `parse(String input)` as called on the user-input. This parses the string and sends the subsequent command to the front-end.
- The front-end calls `execute()` on the command *TOWARD x y* in the back end.
- `TOWARD x y` begins to execute with the position (x, y).
- Through an exception-handling test, the TOWARD method determines that the point (x, y) is out of bounds and throws an out of bounds exception to the front-end to display the error.

3\. *The user inputs a lower-case string into the console.*

- The string gets passed to the parser, who uses `parse(String input)` on the string.
- In the process of parsing, the parser will use a `toLowerCase()` method on some component of the input and proceeds to define the command from the string.
- The program proceeds sending the command back and error-handling.

### Design Considerations

* The main design considerations at this stage of developing the interactions between the front and back-end APIs resides in deciding how the two interact and communicate to parse information and manipulate the visual/informational components of the
program. In regards to communication between the front-end and back-end, the team immediately bonded to the idea of the user
inputting a string into the console, that will be sent to the back-end and understood by means of a parser for all possible commands. Then, once the command (in which each object will become its own class) has been parsed, it will be sent back to the 
front-end to be executed. 
* It was clear from the beginning that the input of the user would have to be parsed and made into some kind of executable object, but other design considerations arose in the aspect of how that object would be passed from the front-end to the back-end and what that meant for the command object. One other consideration was to have only an explicit flow of information from the front-end to the back-end. Although pretty enticing because of the limitation of dependencies, this was decided against because it made direct manipulations to the display an unnatural event. In other words, commands like CLEARSCREEN would have to use a completely different mechanism, because after parsing there would be no communication to the front-end to tell it to for example clear the group.
* The other main consideration was for the front-end to call the back-end to parse, then have the front-end execute the method to change states in itself, versus getting the back-end to do so. The problem with this method is that the internal states of objects like the turtle should remain hidden, and the methods that manipulate the turtle's data protected by varying levels of privacy.
* What needs to be discussed about the design is how the rather generic execute() methods within our command classes will be able to access the objects within the front-end whose properties need to be changed. One possibility, although not particularly pleasing is to instantiate a Method superclass that hold the current command that will already have been instantiated with the necessary instance variables of the turtle to get its location and other states. 
* Overall, the method of Command management aforementioned offers a low amount of dependencies while still allowing for a wide variety of types of commands to take place, in regard to the screen that the user sees and the private variables of the object on the screen. Once the execute command has been decided upon, the communication between the front-end and back-end will effectively be protected as a series of calls to objects versus the unnecessary passing around of objects not involved with the needed command. 

### Team Responsibilities

Our team is split-up into two groups: the **front-end** group (Elliott Bolzan and Jay Doherty) and the **back-end** group (Dennis Ling and Alexander Zapata). 

All members of the group are responsible for documenting and refactoring their subgroup's code, in addition to their own.

#### Within the front-end group:

- Elliott had primary responsibility for the **Input Area**, the **Control Panel**, and the layout of the views (`Workspace` and `WorkspaceBrowser`). He had secondary responsibility for the **TurtleDisplay**, its components, and parts of the back end (re-writing the parser during the third sprint). 
- Jay will take primary responsibility for the **TurtleDisplay**, the **Turtle**, and their components. He will take secondary responsibility for the **Console**.

Both Elliott and Jay will tie in the different front-end elements as part of their primary responsibility.

#### Within the back-end group:
- Dennis will primarily be responsible for the **Command** superclass and it's subclasses such as **MathCommand** and **TurtleActionCommand.** Additionally, Dennis will be in charge of maintaining the information with regards to the **Turtle** class such as the heading, 
- Alex will primarily be responsible for the **Parser** class and the error throwing and recognition within parser. Additionally, Alex will also help with the **Turtle** class in maintaining its functionality.
- Both Alex and Dennis will work together to fill-in any holes throughout the back-end logic and classes that may need to be added throughout the project and its extensions. 