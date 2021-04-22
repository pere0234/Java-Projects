package com.algonquincollege.cst8284.section302.simulation;

/**
 * A class to create Actors on class SpaceExplorationSimulator and verify their movements. 
 * An Actor is defined as either a Ship or an Asteroid
 * @author Stanley Pieda, modified by Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @version 1.1
 * @since javac 1.8.0_271
 * @see SpaceExplorationSimulator 
 * @see SpaceProgram  
 */
public class Actor {
	/**This variable represent the character which represent the actor A for asteroid or S for ship*/
	private char icon;
	/**This variable represents whether or not an actor is moved*/
	private boolean isMoved;
	/**
	 * This is the no-argument constructor chained to Actor(char icon)
	 * This constructor has no parameters
	 */
	public Actor() {
		this('A');

	}
	/**
	 * This constructor accepts a char argument and is chained to Actor(char, boolean)
	 * @param icon is the icon that represents an Actor in SpaceExplorationSimulator class
	 */
	public Actor(char icon) {
		this(icon,false);
	}
	/**
	 * This constructor accepts char and boolean arguments
	 * @param icon is the character that represents an Actor in SpaceExplorationSimulator class
	 * @param isMoved is the boolean value representing whether or not an Actor has moved
	 */
	public Actor(char icon, boolean isMoved) {
		this.icon = icon;
		this.isMoved = isMoved;
	}
	/**
	 * This method accesses the icon created by the class
	 * @return icon is the character that represents an Actor in SpaceExplorationSimulator class 
	 */
	public char getIcon() {
		return icon;
	}
	/**
	 * this method changes the icon to represent the appropriate actor, either an asteroid or a ship
	 * @param icon is the character that represents an Actor in SpaceExplorationSimulator class
	 */
	public void setIcon(char icon) {
		this.icon = icon;
	}
	/**
	 * This method verifies whether or not a an actor has moved
	 * @return isMoved returns a boolean value representing whether or not the actor has moved
	 */
	public boolean isMoved() {
		return isMoved;
	}
	/**
	 * This method changes the value of isMoved
	 * @param isMoved returns a boolean value representing whether or not the actor has moved
	 */
	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

}
