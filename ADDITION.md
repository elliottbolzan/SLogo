## SLogo Addition

**Written by** Elliott Bolzan (eab91).

### Estimation

**Before looking at the old code:
how long do you think it will take you to complete this new feature?**

I do not think this feature should take more than 2 hours. Here are some reasons why:

- We already have **similar functionality**. As of now, the user can already set the image for all active turtles. This extension should just be a matter of applying that logic to individual turtles.
- As far as I can remember, most of the properties I will be making use of in this extension are already defined on my objects. For instance, I need to set an image for the `Turtle` object: it already has a `setImage(String path)` function, I believe.

**How many files will you need to add or update? Why?**

I think I will need to add or edit 3 files:

1. The `Panel` class: I will need to edit this to specify which view I am adding to the project. This should be a change of a few lines.
2. The `TurtleImageViewer` class: I will need to create this class to display the list of turtles and their images. 
3. The `Turtle` class: I'm not sure I will need to edit this one. It depends on whether the getters and setters we declared were `protected` or `public`: the former might require a one-line change. 

*After you have completed the code, answer the following questions at the end of your ADDITION.md file as another separate commit.*

### Review

**How long did it take you to complete this new feature?**

**How many files did you need to add or update? Why?**

**Did you get it completely right on the first try?**

### Analysis

**What do you feel this exercise reveals about your project's design and documentation?**

**Was it as good (or bad) as you remembered?**

**What could be improved?**

**What would it have been like if you were not familiar with the code at all?**
