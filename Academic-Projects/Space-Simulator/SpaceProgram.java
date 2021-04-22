package com.algonquincollege.cst8284.section302.simulation;

/**
 * A class to run and test class SpaceExplorationSimulator.
 * @author Stanley Pieda, modified by Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @version 1.1
 * @since javac 1.8.0_271
 * @see SpaceExplorationSimulator 
 * @see Actor  
 */
public class SpaceProgram {

	/**
	 * This is the main method and entry point for running the program.
	 * @param args is the variable name of the String array.
	 */
	public static void main(String[] args) {

		/**
		 * Creating a new SpaceExplorationSimulator object called simulator
		 */
        SpaceExplorationSimulator simulator = new SpaceExplorationSimulator();
        /**Runs the gameTitle() method from the simulator object.*/
        simulator.gameTitle();
        /**Runs the userDisplayPreference() method from the simulator object.*/
        simulator.userDisplayPreference();
        /**Runs the runSimulation() method from the simulator object.*/
        simulator.runSimulation();
        
	}
	

}
