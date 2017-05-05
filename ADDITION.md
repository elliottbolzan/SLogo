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
2. The `CurrentTurtleImages` class: I will need to create this class to display the list of turtles and their images. 
3. The `Turtle` class: I'm not sure I will need to edit this one. It depends on whether the getters and setters we declared were `protected` or `public`: the former might require a one-line change. 

*After you have completed the code, answer the following questions at the end of your ADDITION.md file as another separate commit.*

### Review

**How long did it take you to complete this new feature?**

It only took me **1 hour** to implement this feature (1 hour less than I planned).  

**How many files did you need to add or update? Why?**

In the end, I needed to add or update 5 files (4 of them had changes of 3 lines or less):

1. The `Panel` class: I added two lines to display the new view in our panel, on the right of the application. This change was expected. 
2. The `CurrentTurtleImages` class: I created this class to display the list of turtles and their images. This was the most substantial change I made: I had to add a `TableView` and account for the user clicking on it. This change was expected.
3. The `Turtle` class: I edited this class to change the `ImageView getView()` to be `public` instead of `protected`. I expected this change.
4. The `TurtleManager` class: I added an instance variable and getter to make an `ObservableList<Turtle>`. This was done so that the images of the Turtles in the new view would update automatically. I had not planned for this change.
5. The `UserInterface.properties` file: I added one line to specify the title of the new view. I had not thought of this change, but in hindsight, it makes since.

**Did you get it completely right on the first try?**

I did get it completely right on the first try! 

### Analysis

**What do you feel this exercise reveals about your project's design and documentation?**

Overall, I think this exercise reveals that our project was built with the concepts of flexibility and extensibility in mind. Our `Panel` class and `ResourceBundle` made for simple and modular extension. 

More specifically, I think this exercise reveals the following positive elements about our project's design and documentation:

1. There is very **little overhead** in adding features: most of the work went into creating the new class itself, `CurrentTurtleImages`.
2. The process of **adding views** to the application is extremely simple: it consists in adding two lines of code to the `Panel` class.
3. The documentation was up to par: it was clear, from `Panel`'s comments, where changes needed to be made to add a new view.

The exercise also reveals two less positive elements about our project's design and implementation:

1. We used `protected` too much. Using `public` instead would have let me edit one less class while adding this feature (`Turtle`).
2. We should have provided an `ObservableList<Turtle>`. I had to add this to `TurtleManager` during this addition, even though the data structure could have been used earlier in the project.

**Was it as good (or bad) as you remembered?**

It was as good as I remembered!

When I wrote my analysis, I recognized that the `Panel` class satisfied most of the principles we learn about in this class: modularity, flexibility, and the open-closed principle. 

In adding code to the project, I was not disappointed by the class. To add my new view to the project, I had to add a total of two lines of code to the `Panel`, letting me focus on code that actually pertained to the new functionality. I was impressed by how little overhead there was in adding GUI elements.

**What could be improved?**

As I mentioned above, there are two minor situations I would fix in the front-end to make the project easier to maintain:

1. I would use `public` instead of `protected`. `protected` doesn't seem like a good method modifier: it will cause problems if the class it is used is moved to a new package, for instance. In addition, using `public` lets a coder focus on new features instead of on previous code.
2. The data structures contained in `TurtleManager` could use some reorganizing. We ended up with three different data structures: `activeTurtles`, `turtles`, and `observableTurtles`. In hindsight, it seems the last two could be combined to simplify the logic. 

**What would it have been like if you were not familiar with the code at all?**

If I were not familiar with the code, I think the addition would have been easy.

If I had looked at the application and wished to add a view to the panel, I would have gone to the `Panel` class. Then, it would have been clear, from documentation, where I needed to add two new lines of code. Finally, I would have created a class to house the view I was creating.

Furthermore, referring to the `.properties` file from the new class would have been easy (and optional, in fact). 

In reality, the only difficulty would have been in obtaining an `ObservableList<Turtle>`. While the code to create one is short (5 lines), I can understand why someone with no knowledge of the project would have more trouble with that part. 

Overall, I think a Java developer with JavaFX experience would not have problems adding the required user interface feature.
