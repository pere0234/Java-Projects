package com.algonquincollege.cst8284.section302.simulation;

import java.util.Scanner;

/**
 * A turn-based simulation that displays randomized Space Ships and Asteroids along with their movements and interactions on a grid.
 * @author Stanley Pieda, modified by Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @version 1.1
 * @since javac 1.8.0_271
 * @see SpaceProgram
 * @see Actor
 */
public class SpaceExplorationSimulator {
	/**Establishes 5 rows for the playing area.*/
	private static final int MAX_ROWS = 5; 
	/**Establishes 20 columns for the playing area.*/
	private static final int MAX_COLUMNS = 20; 
	/**Establishes 5 maximum ships for the playing area.*/
	private static final int MAX_SHIPS = 5; 
	/**Establishes only 3 cardinal directions for ship movement.*/
	private static final int MAX_SHIP_DIRECTIONS = 3; 
	/**For moving either ships or asteroids one space.*/
	private static final int ONE_SPACE = 1; 
	/**Used for counting number of ships that are destroyed.*/
	private int shipsDestroyed = 0;
	/**Used for counting number of ships that have escaped.*/
	private int shipsEscaped = 0;
	/**Used to count the number of turns in the simulation.*/
	private int turnCount = 0;
	/**Value for determining user display settings in setUserDisplayPreference().*/
	private boolean displayBars = true;
	/**Used to gather user program input.*/
	private Scanner input = new Scanner(System.in);
	/**Creates a 2-D array which represents the playing area.*/
	private Actor[][] actors = new Actor[MAX_ROWS][MAX_COLUMNS];

	/**
	 * This is the no argument constructor for SpaceExplorationSimulator
	 * It contains no parameters
	 * It is chained to SpaceExplorationSimulator(int,int,int,boolean)
	 */
	public SpaceExplorationSimulator() {
		this(0,0,0,true);
		}
	/**
	 * This is a parameterized constructor which receives three int arguments and a boolean value
	 * @param shipsDestroyed refers to the ships that are destroyed when colliding with asteroids
	 * @param shipsEscaped refers to the ships that successfully exit the game without collisions
	 * @param turnCount refers to the number of turns taken in the game
	 * @param displayBars refers to the user display settings for displaying vertical bars onscreen
	 */
	public SpaceExplorationSimulator(int shipsDestroyed, int shipsEscaped, int turnCount, boolean displayBars) {
		this.shipsDestroyed = shipsDestroyed;
		this.shipsEscaped = shipsEscaped;
		this.turnCount = turnCount;
		this.displayBars = displayBars;
	}
	/**
	 * IMPORTANT: This method was verified and should not be changed without approval from the Senior programmer.
	 * This method runs the simulation asking the user to continue for each turn,
	 * or until the end of the simulation is detected.
	 * Instructions are given on how to run the simulation and how to stop.
	 * It initializes the playing area and draws the visual grid on the screen.
	 * A new asteroid is added using the addAsteriod() method at a probability of 10% per turn.
	 * The actors are prepared for movement using prepareActorsForMovement().
	 * The actors are moved using the moveActors() method.
	 * A turn count is maintained to keep track of the number of turns in the simulation.
	 * Once the simulation ends the program shuts down.
	 */
	public void runSimulation() {

		turnCount = 0;
		boolean continueSimulation = true;

		System.out.println("Welcome to Space Simulation");
		System.out.println("Use enter key to run next turn");
		System.out.println("Typing anything other than return will end program");

		initializeArray();
		drawSpaceSimulation();

		do {
			addAsteroid();
			prepareActorsForMovement();
			moveActors();
			drawSpaceSimulation();
			turnCount++;
			System.out.println("Use enter key to run next turn");
			System.out.println("Typing anything other than return will end program");
			String userInput = input.nextLine();

			/**
			 * This statement ends the simulation if user presses anything other than ENTER.
			 * It sets the continueSimulation variable to false.
			 */
			if(userInput.length() > 0) {
				continueSimulation = false;
			}
		}while(continueSimulation && ! isEndOfSimulation());

		System.out.println("Thanks for using the simulation");
	}

	/**
	 * This method sets up for start of simulation, placing the game actors
	 * It places 5 ships at the far left in each of the rows in column 0,
	 * and one randomized asteroid suing method addAsteroid().
	 */
	private void initializeArray() {

		for(int row = 0; row < actors.length; row++) {
			actors[row][0] = new Actor('S');
		}
		addAsteroid();
	}
	/**
	 * This method adds asteroids the the playing screen.
	 * There is a 10% chance that an asteroid will be added to the play field when this 
	 * method is called.
	 * Because the generation of asteroids is randomized, sometimes
	 * no asteroids will be created upon startup
	 */
	private void addAsteroid() {
		
		/**Establishes a 10% value for asteroid generation*/
		final int CHANCE = 10;
		/**Multiplies a  Math.random() value by 100 to resolve the value to an integer*/
		final int GET_INTEGER_VALUE = 100;
		/**Used to limit the number of columns an asteroid may 'jump' to*/
		final int UP_TO_FIVE_SPACES = 5;
		/**Used to store a randomized integer value to decide whether or not an asteroid will be generated*/
		int result = (int)(Math.random() * GET_INTEGER_VALUE) + 1;

		/**
		 * This loop places one asteroid in a target column, but in a row randomly
		 * selected from 0 to 4.
		 * if there is a ship in the target column when the asteroid is
		 * added, it should destroy the ship and increment the shipsDestroyed
		 * variable.
		 * If there is an asteroid in the target column, the asteroid will be removed.
		 */
		if(result < CHANCE) {
			int row = (int)(Math.random() * UP_TO_FIVE_SPACES);
			int col = actors[row].length - 1;
			if(targetSpaceIsFree(row, col)) {
				actors[row][col] = new Actor('A');
			}
			else if(actors[row][col].getIcon() == 'S') {
				actors[row][col] = new Actor('A');
				shipsDestroyed++;
			}
			else {
				removeFromBoard(row, col);
			}
		}
	}

	/**
	 * This method loops over the 2-D array and move each actor, depending
	 * on the icon, S for ship, A for asteroid.
	 * Ships move up, right, down at random.
	 * Asteroids move left only.
	 * After each actor is moved, it is flagged so that it is not moved ore than once per call of this method. 
	 * prepareActorsForMovement() is used to flag the actors as movable before this method is called again.  
	 * Ships are not allowed to leave the top or bottom of the play area.
	 * Ships that are destroyed are counted using shipsDestroyed counter.  
	 * Ships that attempt to move past the right-most column are   
	 * considered safe, are removed from the simulation, and counted using shipsEscaped counter 
	 */
	private void moveActors() {

		for(int row = 0; row < actors.length; row++) {
			for(int col = 0; col < actors[row].length; col++) {
				/**
				 * If the element has an actor, and they have not already moved.
				 */
				if(actors[row][col] != null && ! actors[row][col].isMoved() ) {
					int newRow = 0;
					int newCol = 0;
					actors[row][col].setMoved(true);
					/**
					 * The elements of this nested loop define the movement of the ships
					 * Ship to Asteroid: Ship is destroyed (set element to null)  
					 * Ship to Ship: The ship skips it's turn and does not move. 
					 */
					if(actors[row][col].getIcon() == 'S') { 
						int direction = (int)(Math.random() * MAX_SHIP_DIRECTIONS);
						switch(direction) {
						/**
						 * The first case moves the ship UP by deducting one space
						 * The movement is created by setting a target space and moving
						 * the ship to a new row
						 * This case calls on to the moveUpOrDown method
						 */
						case 0: 
							newRow = row - ONE_SPACE; // deduct 1 to move 'up'
							moveUpOrDown(row, col, newRow);
							break;
							/**
							 * The second case moves the ship RIGHT by adding one space
							 * The movement is created by setting a target space and moving the ship to a new column
							 * The outer loop handles whether or not the ship has moved off the board (escaped)
							 * The inner loop handles any instances of ship movement, 
							 * non-movement because of obstruction by other ships, or destruction by asteroid
							 */	
						case 1:
							newCol = col + ONE_SPACE;
							if(! isMoveOffBoard(row, newCol)) {
								/**
								 *  If the target space is free, the ship moves right to a new column
								 */
								if(targetSpaceIsFree(row, newCol)) {
									moveRightOrLeft(row, col, newCol);
								}
								/**
								 * If there is an asteroid (char 'A') in the new column, the ship
								 * is destroyed and the shipsDestroyed counter is updated
								 */
								else if(actors[row][newCol].getIcon() == 'A') {
									removeFromBoard(row, col);
									shipsDestroyed++;
								}
								/**
								 * If there is a different ship to right, the ship cannot move
								 * The obstruction means the ship cannot do anything so
								 * the body of the else statement is left blank
								 */
								else {
								}
							}
							/**
							 * If the ship cannot move right, the ship has escaped
							 * The shipsEscaped counter is updated to reflect this
							 */
							else {
								removeFromBoard(row, col);
								shipsEscaped++;
							}
							break;
							/**
							 * The third case moves the ship DOWN by adding one space
							 * The movement is created by setting a target space and moving
							 * the ship to a new row
							 * This case calls on to the moveUpOrDown method
							 */
						case 2: 
							newRow = row + ONE_SPACE;
							moveUpOrDown(row, col, newRow);
							break;
						}
					}
					/**
					 * This part of the outer loop determines the movement of Asteroids
					 * it moves the asteroid LEFT by deducting one space
					 * The movement is created by setting a target space and moving the asteroid to a new column
					 * The outer part of the loop handles whether or not the asteroid has moved off the board (escaped)
					 * The inner loop handles any instances of asteroid movement 
					 */	
					else {
						newCol = col - ONE_SPACE;
						/**
						 *  If the target space is free, the asteroid moves right to a new column
						 *  This part uses method moveRightOrLeft
						 */
						if(! isMoveOffBoard(row, newCol)) {
							if(targetSpaceIsFree(row, newCol)) { 
								moveRightOrLeft(row, col, newCol);
							}
							/**
							 * If there is an ship (char 'S') in the new column, the ship
							 * is destroyed and the shipsDestroyed counter is updated
							 */
							else if(actors[row][newCol].getIcon() == 'S') {
								moveRightOrLeft(row, col, newCol);
								shipsDestroyed++;
							}
							/**
							 * If there is an asteroid to the left, remove both
							 * Asteroids in the current and target column are removed
							 */
							else { 
								removeFromBoard(row, newCol);
								removeFromBoard(row, col);
							}
						}
						/**
						 * If the asteroid cannot move left, it is removed
						 * Asteroid has escaped the playing area.
						 */
						else { 
							removeFromBoard(row, col);
						}
					}
				}
			}
		}
	}
	/**
	 * This method verifies whether or not the target space is available and contains no actors.
	 * Target space is the space in which the actor is meant to be moved.
	 * It is used within moveActors to modify the movement potential of the actors. 
	 * @param row refers to the row in which the actor is located.
	 * @param newCol refers to new column in which the actor is targeted to be placed.
	 * @return the return statement compares the target space using an equality operator
	 * to null(there is no actor in the target space) and resolving it to a boolean value.
	 */
	private boolean targetSpaceIsFree(int row, int newCol) {
		return actors[row][newCol] == null;
	}
	/**
	 * This method removes an actor from the board.
	 * It assigns the actor's current row and column to null.
	 * Removal can be due to collision with another actor or escape.
	 * @param row refers to the row in which the actor is located.
	 * @param col refers to the column in which the actor is located.
	 */
	private void removeFromBoard(int row, int col) {
		actors[row][col] = null;
	}
	/**
	 * This method moves an actor right or left.
	 * It takes the actor's current position and assigns it to a new column.
	 * It removes the actor from its previous column with removeFromBoard().
	 * @param row refers to the row in which the actor is located.
	 * @param col refers to the column in which the actor is located.
	 * @param newCol refers to the new Column in which the actor is to be placed.
	 */
	private void moveRightOrLeft(int row, int col, int newCol) {
		actors[row][newCol] = actors[row][col];
		removeFromBoard(row, col);
	}
	/**
	 * This method moves an actor up or down if the actor is still on the board.
	 * It consists of a nested loop.
	 * It takes the actor's new position and assigns it to a new row.
	 * It removes the actor from its previous row using removeFromBoard().
	 * It removes the actor from the board if it collides with an asteroid and updates shipsDestroyed counter.
	 * If the target space is occupied by another ship, the actor does not move.
	 * @param row refers to the row in which the actor is located.
	 * @param col refers to the column in which the actor is located.
	 * @param newRow refers to the new row in which the actor is to be placed.
	 */
	private void moveUpOrDown(int row, int col, int newRow) {
		if(! isMoveOffBoard(newRow, col)) {
			if(targetSpaceIsFree(newRow, col)) {
				actors[newRow][col] = actors[row][col];
				removeFromBoard(row, col);
			}
			else if(actors[newRow][col].getIcon() == 'A') {
				removeFromBoard(row, col);
				shipsDestroyed++;
			}
			else {

			}
		}
	}
	/**
	 * This method prompts the user to select their preference for either having or not having vertical bars 
	 * separating the grid on the play screen.
	 * It resolves the user's input (either Y for yes or N for no) to a boolean value prefersBars.
	 * It then passes the boolean value to method setUserDisplayPreference.
	 */
	public void userDisplayPreference() {
		System.out.println("Before starting the simulation, do you prefer vertical bars on the display? (Y/N) ");
		System.out.println("Y =   |S| | | | |A|");
		System.out.println("N =    S         A" );
		String userInput = input.nextLine();
		boolean prefersBars = (userInput.equalsIgnoreCase("Y"));
		setUserDisplayPreference(prefersBars);
	}

	/**
	 * This method sets the user display preference to that which is entered 
	 * by the user from method userDisplayPreference().
	 * @param displayPreference is the boolean value resolved from the user input.
	 */
	private void setUserDisplayPreference(boolean displayPreference) {
		this.displayBars = displayPreference;
	}

	/**
	 * This method displays the grid for the simulation onto the screen.
	 * It takes the user display preference and resolves it to two options for grid separator, either vertical bars "|" 
	 * or none " " using an if/else loop.
	 * It uses a nested for loop to draw the Actor array onto the screen and use the appropriate separator.
	 * It calls method printResults() to display the outcome of each turn.
	 */
	private void drawSpaceSimulation() {

		String separator = "|";

		if(displayBars) {

		}
		else if (!displayBars) {
			separator = " ";
		}

		String rowText = "";
		for(Actor[] row: actors) {
			rowText += separator;
			for(Actor actor: row) {
				if(actor == null) {
					rowText += " ";
				}
				else {
					rowText += actor.getIcon();
				}
				rowText += separator;
			}
			rowText += "\n";
		}
		printResults(rowText);
	}			
	/**
	 * This method displays the result of each simulator turn.
	 * It includes the following data:
	 * Name of the programmer
	 * Number of ships destroyed
	 * Number of ships that escaped
	 * Number of turns that have been taken
	 * @param rowText is the state of the playing area: the actors and the grid separator.
	 */
	private void printResults(String rowText) {
		System.out.print(rowText);
		System.out.println("Simulation by Daniel Pereira Castillo");
		System.out.println("Ships destroyed: " + shipsDestroyed);
		System.out.println("Ships escaped: " + shipsEscaped);
		System.out.println("Turn number: " + turnCount);
		System.out.println();
	}
	/**
	 * This method checks the number of ships at start against 
	 * the number of ships destroyed + number of ships that have escaped
	 * for the program to decide whether or not the simulation should end
	 * @return result if there are no move active ships, the method returns true 
	 * to indicate the end of the simulation
	 */
	private boolean isEndOfSimulation() {

		boolean result = false;
		if(shipsDestroyed + shipsEscaped >= MAX_SHIPS) {
			result = true; // it is end of simulation
		}
		return result;
	}
	/**
	 * This method checks if a proposed move would be outside the bounds of the 2-D array, 
	 * if it is outside the bounds it reports true.
	 * @param newRow is the target new row for a move
	 * @param newCol is the target new column for a move
	 * @return isOffBoard returns a boolean value 
	 * either true if a move would be out of bounds or false if otherwise
	 */
	private boolean isMoveOffBoard(int newRow, int newCol) {
		boolean isOffBoard = true;
		if((newRow >= 0 && newRow < MAX_ROWS)&&(newCol >= 0 && newCol < MAX_COLUMNS)) {
			isOffBoard = false; // attempt to move too far left or right
		}
		return isOffBoard;
	}
	/**
	 * This methods loops over the 2D array and re-activates all of the actors
	 * so that when the moveActors() methods loops over the array each actor
	 * will be moved at least once.
	 */
	private void prepareActorsForMovement() {
		for(int row = 0; row < actors.length; row++) {
			for(int col = 0; col < actors[row].length; col++) {
				if(actors[row][col] != null) {
					actors[row][col].setMoved(false);
				}
			}
		}
	}
	/**
	 * This method displays an ASCII art title on the screen with the title "Space Simulation"
	 * It is a proposed title for design purposes only, pending approval from Senior programmer.
	 */
	public void gameTitle() {
		System.out.println(" _____                    _____ _           _     _   _         ");
		System.out.println("|   __|___ ___ ___ ___   |   __|_|_____ _ _| |___| |_|_|___ ___	");  
		System.out.println("|__   | . | .'|  _| -_|  |__   | |     | | | | .'|  _| | . |   |");
		System.out.println("|_____|  _|__,|___|___|  |_____|_|_|_|_|___|_|__,|_| |_|___|_|_|");
		System.out.println("      |_|  														");
	}
}
