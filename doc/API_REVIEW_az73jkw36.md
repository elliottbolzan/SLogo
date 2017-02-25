# Peer API Review
## Part 1:
Flexible:
My API Design is intended to be flexible because it allows for the method handling of Commands in both the Back-End and the User-Interface depending on the command that is passed from the parser to the front-end to have called on the execute method of. The flexible component of this method is that if the Command is passed back, then there could be Commands that are executed and defined within the User-Interface that acts upon it. An example of this could be the displaying of the previous commands called through a list of strings passed back to the User-Interface.

Encapsulating:
This API design is encapsulating because it makes it so that there is no necessity for the User-Interface to interact at all with the actual commands. Instead, the User-Interface just passes the initial string input to the parser, and then the model is manipulated from there changing whatever states were changed.

Exceptions:
There will be an exception for an invalid Command input. There will be an exception for an incorrect number of arguments. And we discussed an exception for a null string being sent to the parser, but we came up with the idea that the user-interface should really handle that so that it does not even send a null string.


## Part 2:
Design Patterns:
Currently, I believe that our design implements both a Command and a Chain of Responsibility design pattern. The reason this is so is because our code separates the User-Interface input (the asker) from the receiver (the actual command classes). This form of encapsulation occurs because of the parser being a kind of sorting of the request into those different receiver Commands. The Chain of Responsibility pattern is displayed by the fact that we are using a front-end, back-end, model workflow, and the fact that the way that commands are instantiated and called flows responsibility from one interface to the next.

Advanced Java Features:
Using reflection would be extremely helpful, because it could allow for the differential use of different commands at runtime, giving the program more functionality in how it can execute each command. Further, the use of the Lambda expression would very easily connect methods like getHistory in the back end to the User-Interface to very efficiently and easily display.

Most Excited:
I am most excited to work on some of the Commands. My primary responsibility is working on the parser, which is pretty stressful as it is the main logistical component of actually getting responses from the back-end and displaying. Once this is done, however, then I can work on the more interesting and tangible business of making commands themselves.

Most Worried:
I am most worried about implementing the parser as described before. It is such an integral part of the code that, without it the whole program would not really work correctly or at all.

Use Cases:
*The user sends a nested command string.*
Using recursion, the parser will get all nested function's returns and make those the arguments.

*The user types in forward -50.*
The parser will still see this as going forward. It will NOT handle it as backward 50. 

*The user does not input a string and hits run.*
The front-end will realize this and display an error message.

*The user inputs the wrong amount of arguments.*
The parser will internally check for this and it will throw an exception if observed.

*The user inputs a command that will send the turtle to an invalid place.*
The parser will pass the command to front-end and when it executes, there will be an error in the back-end command logic.