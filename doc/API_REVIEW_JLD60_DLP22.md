Part 1
=====

*What about your API/design is intended to be flexible?*

Our API was designed to be simple but also relatively powerful. There public methods for all of the major features so that these can be called at any time. If we need to be able to add a new type of movement command, that should be able to be handled without changing the way that the turtle is displayed.
	
*How is your API/design encapsulating your implementation decisions?*

Our API is designed so that the front end and back end can support all of the features but they do not have to worry about how the implementation is handled. Most of the API methods can be mapped to specific features that they are designed to support, so there are not lots of method calls handed back and forth. (ie the front end tells the back end to parse input, and it expects the back end to fully handle that).

*What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?*

I think the vast majority of exceptions that can occur will occur in the back end. The front end will be responsible for displaying that message to the user.

*Why do you think your API/design is good (also define what your measure of good is)?*

I think our API is very concise, which is good because it minimizes the amount of interaction between the front end and back end. But while we've been talking about it, I think our API might be a little bit too exposed. It might be better for the back end to have some way to show the front end the state of the simulation, rather than calling individual methods in the front end to move and turn the turtle. If there are more turtle commands introduced, we might have to add more methods to the front-end API which wouldn't be very good.

Part 2
=====

*How do you think Design Patterns are currently represented in the design or could be used to help improve the design?*

We haven't really incorporated any Design Patterns yet because we are planning to incorporate them as problems arise rather than basing our design around them.

*How do you think the "advanced" Java features will help you implement your design?*

Regular expressions will be very helpful in parsing the string input from the user and handling slight discrepancies (like ignoring extra whitespace in a command).

*What feature/design problem are you most excited to work on?*

I'm excited to work on moving the turtle around the display. It seems very tricky how to keep track of this state and keep the front end and back end well encapsulated.

*What feature/design problem are you most worried about working on?*

I'm most worried about finding ways to incorporate inheritance into our design. That was the main lesson that we learned from working on Cell Society, and as far as I can tell, there aren't any clear inheritance hierarchies within the front end. The only one that I can think of now is the display for variables and the display for previous commands. Perhaps those could both be encapsulated in a ListDisplay object that ties an ObservableList to a TextArea in the view.

*Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).*

1. User changes the language of all of the different components
2. User types "fd 50" and the turtle moves
3. User tries to execute a previous command
4. User changes a variable via the variable display display table
5. User types in a syntax error and an alert is displayed.


