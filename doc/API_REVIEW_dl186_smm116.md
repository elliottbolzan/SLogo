# API Peer Review


## Dennis Ling dl186 


## Stone Mathers smm117


###  Part 1


1\. **What about your API/design is intended to be flexible?**
Our API is intended to be flexible in the cases where you need to extend a superclass such as Commands or use the methods necessary among our different classes to update the necessary state. Additionally, our shared execute method in all commands creates a blanket 


2\. **How is your API/design encapsulating your implementation decisions?**
We only give the front-end the necessary execute method in order to update the states; it doesn't need to know the states of variables in between. We hide these from the implementation as necessary.


3\. **What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**
Some errors that might occur in command processing is a math error (such as divide by zero) or an illegal variable setting (int overflow). We would send this error and its description to the front end to display.


4\. **Why do you think your API/design is good (also define what your measure of good is)?**
It successfully implements all of the functionality we need while only providing the necessary methods and information publicly. Any information that can be hidden from the implementation is.


###  Part 2
1\. **How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**
Design Patterns can be added to our API from the way we implement interfaces; right now, we use superclasses and subclasses, kind of like the abstract factory example. We create many instances of the same command class by extending it in order to maintain an extensible set of commands.


2\. **How do you think the "advanced" Java features will help you implement your design?**
The "advanced" Java features that will help us implemented our design are potentially reflection and enumeration. Reflection will help with the cleanliness, extensibility, and ease of debugging while handling the creation of commands. Enumeration will be helpful while implementing the functionality of the designs.


3\. **What feature/design problem are you most excited to work on?**
I'm most excited on implementing the functionality behind commands that update the Turtle, such as position, heading, or pen drawing. The interaction among back-end and front-end classes should be interesting to reason out as we update the necessary states.


4\. **What feature/design problem are you most worried about working on?**
I'm most worried about connecting my back-end functionality with the front-end; in the case that we do not correctly update states at the correct time, this connection may become messy and difficult to debug.


5\. **Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**


1\. The user wants to obtain the previous command, but incorrectly enters a badly formatted command instead.

- The command will be sent to the public Command parse(String input) method, which will attempt to parse the String.
- Upon realizing the String is incorrectly formatted, the parse method will throw a badInput(); error to the front-end.
- The front-end will receive the exception and will display the correct exception on the GUI.


2\. The user wants to obtain the previous command, and correctly inputs the String command!

- The command to obtain the history will be sent to the parser parse(String input), which will correctly return the previous history Command.
- The front-end will receive this command, and will run execute() on this particular command.
- In the execute() method, the back-end will supply the front-end with the correct previous command with getPreviousCommand(int k) depending on the integer that the user specified.
- The print() method in the front-end is called in order to notify the user what the previous command was.


3\. The user moves the Turtle forward 5, and then forward another 6, but the grid is only 10 lengths long.

- The first forward command is sent to the parser parse(String input) method, which parses the command and sends it to the front-end.
- The front-end calls execute() on forward 5 in the back-end.
- The command will call setPosition() to update the Turtle position to be 5 more of its initial position.
- Control is returned to the front end to update the position on the GUI.
- The second forward command is parsed and execute is called again.
- Upon execution, the logic will check if setPosition() will make the position of the Turtle be larger than the Grid.
- In this case, it will, so an exception will be thrown to the front end to display.


4\. The user creates a legal command containing foward 50 and setheading 20.

- The first command that is called is parse(String input) as called on the user-input. This parses the string and sends the subsequent command to the front-end.
- The front-end calls execute() on the command that is used to create new commands. 
- In execute method, we create a new command object that is stored in a map (key: command name, value: list of commands to execute) that contains the functionality of the the command as the combination of commands.


5\. The user creates a new variable and assigns it a value.

- The input string gets parsed by the back end and the command is sent to the front end.
- The front-end calls execute on the command to create the variable.
- The back-end execute method for variable creation is called, creating a variable object from our variable class. We store these in a map (key: variable name, value: value) and access them as necessary.
