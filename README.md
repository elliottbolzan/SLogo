Slogo
=====
##### By: Elliott Bolzan (eab91), Jay Doherty (jld60), Dennis Ling (dl186), Alex Zapata (az73).

**Start**: Thursday, February 16.

**Finish**: Friday, March 10.

**Total Hours**:

- Elliott Bolzan: 70 hours.
- Alex Zapata: 45 hours.
- Jay Doherty: 55 hours.
- Dennis Ling: 60 hours.
- Total: 230 hours.

### Team Roles
##### Front-End

- Elliott Bolzan: main responsibilities included the building of the shell, the script view, the workspace browser, the workspaces, and the view layout. In addition, I rewrote the parser in the back-end to use a `Tree` structure between both sprints, which satisfied two of the back-end challenging extensions. 
- Jay Doherty: Main responsibilities were handling the turtle display and animation for the front end, including providing the turtle interface to the back-end. Also helped implement the side panels for images, colors, and turtle information. Also assisted with turtle and display-related commands in the back-end.

##### Back-End
- Dennis Ling: Main responsibilities were coding all of the commands, including the math, logic, turtle, turtle queries, control, multiple, and display commands. Also worked as a tester to ensure all of the commands and aspects of the project worked throughout the whole process. Also handled refactoring of several back-end classes, including the grouping extension (groupnode) and error handling using properties files in the parser.

- Alex Zapata: Wrote the original parser, and control commands, which was later switched to a Tree implementation. Main responsibilities included extending/re-factoring the parser and some of the commands in back-end while deciding the areas in which error-checking needed to be done. Further was tasked with implementing user made commands in the back-end with the Tree structure. For this, I created the UserCommand node and its basic usage within the parser (refined by team-mates).

### Program Files
* `Main.java`: contains main() and is used to start the program.
* `resources/images`: contains turtle images.
* `resources/languages`: contains language files specifying all of the Slogo commands.
* `resources/style.css`: contains the CSS code used to style our program.
* `resources/UserInterface.properties`: contains resources for user-facing text, including error messages.
* `resources/WorkspaceSettings.properties`: defines a workspace's initial configuration settings.
* `data/examples`: contains scripts that can be used to test the program.

### Impressions


* Elliott Bolzan: this project was a stimulating challenge. Its modularity helped me better understand how to divide up the user interface - both visually and in terms of code. I also found the process of implementing the parser using recursion to be an interesting challenge. Overall, I preferred this project to CellSociety and Breakout: it felt like we were tasked with creating a complete, usable program, while the previous projects felt more gimmicky.
* 

* Alex Zapata: this project was very interesting, but also very stressing. The interestingness of this project is in the fact that it is our first real chunk of code that really feels like a solidified product. Further, the challenges associated with understanding user-input made us focus on UI. The stressing component of this project was that, because of circumstances outside of this class both academic and personal and the ludicrous amount of time that such a project calls for, I was not able to put in as much time as some of my team-mates which effected my own personal production. Altogether a good experience in which I learned a lot and genuinely enjoyed the final product.
*

* Jay Doherty: This project was challenging but I think I liked it more than the previous two. It definitely helped me understand the importance of making interfaces when you are going to divide up into teams and try to work simultaneously to produce something functional. I think that in some ways the number of extensions and the complexity of some of them discouraged me from spending much time thinking about refactoring and encouraged me to spend time just getting stuff to work. That being said, I do feel like I learned a lot about bindings, reflection, and lambdas so it was still a useful project.

* Dennis Ling: This project was more fulfilling than the last two. It was awesome creating an IDE and seeing how all the pieces could fit together with both the front-end and back-end. Overall I enjoyed the whole process, but it was a lot of work to say the least. I'm happy with our final product and glad that it's over, but I learned a lot about design and working together as a team!

