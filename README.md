Game of Life

Overview: 
<br>
This project is an implementation of Conway's Game of Life in Java. The Game of Life is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input.
</br>
<br>
Features: <br>
•	Interactive Grid: Users can create initial configurations by toggling cells on the grid. <br>
•	Start/Stop Functionality: Users can start and stop the simulation at any time.<br>
•	Customizable Grid Size: The grid size can be adjusted to explore different patterns and behaviors.<br>
•	Generation Tracking: Keeps track of the number of generations that have passed.<br>
•	Pattern Loading: Users can load predefined patterns into the grid.<br>
<br>
Concepts Used: <br>
•	Object-Oriented Programming (OOP): The project is structured using OOP principles with classes such as Board, GameOfLife, Button, and Page.<br>
•	Graphical User Interface (GUI): The GUI is implemented using custom drawing libraries (StdDraw) to render the game grid and control buttons.<br>
•	File I/O: The project includes functionality to read initial grid configurations from text files.<br>
•	Algorithms and Data Structures: Utilizes data structures like arrays and classes for managing the grid and game logic.<br>
<br>
Files:<br>
•	Board.java: Manages the game board and game logic.<br>
•	GameOfLife.java: Implements the main logic for the Game of Life.<br>
•	Button.java: Handles button functionalities for starting, stopping, and resetting the game.<br>
•	Page.java: Manages the layout and rendering of the game page.<br>
•	Rectangle.java, Text.java: Utilities for drawing shapes and text on the screen.<br>
•	StdDraw.java, StdIn.java, StdOut.java: Custom standard libraries for drawing and input/output operations.<br>
•	WeightedQuickUnionUF.java: Data structure for managing connected components (not directly used in Game of Life but useful for related algorithms).<br>


