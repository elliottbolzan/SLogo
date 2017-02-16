# API Critique – Cell Society

## Elliott Bolzan (eab91), Jay Doherty (jld60), Dennis Ling (dl186), Alex Zapata (az73)

  

**Visualization**

  

The SceneBuilder, Setup and Simulator superclasses contain public methods that are part of the visualization APIs.

  

1\. Internal API. 
  

Internally, the SceneBuilder superclass’s constructor and its getters are available.  The Setup class creates the Scene and the Stage and provides `getScene()` and `getStage()` to access them. The Simulator superclass contains the method `handle(ActionEvent event)`, which handles what each button will do when clicked. 

2\. External API. 
  

Externally, there was no API for the visualization. The simulation and configuration did not need knowledge of what took place in the visualization. 

  

**Configuration**

  

The GameData and XMLParser classes contain public methods that are part of the configuration APIs.

  

1\. Internal API. 
  

Internally, the XMLParser class provides a `getData()` method that returns the parameters for a simulation.

  

2\. External API. 
  

Externally, the GameData class provides getters for all the keys in the XML file: `getTitle()`, `getAuthor()`, `getDimensions()`, etc.

  

**Simulation**

  

The Game, Cell, and Grid superclasses all contain public methods that are part of the simulation APIs. 

  

1\. Internal API. 
  

The Game superclass contains the following important internal API methods: `gameLogic(Cell cell)` determines the cell’s next state and `setInitialPositions()` is self-explanatory. In Cell, we have `setPossibleNeighbors()` and `updateNeighbors()`. In Grid, we have `updateGrid()`.

  

2\. External API. 
  

The Game superclass contains the following important external API methods: the `getGrid()`. In Cell, `setColor()` and `getShape()` are available to external callers. In Grid, we have the `getNumberOfRows()` and `getNumberOfColumns()` to help the visualization determine how big the grid should be.
