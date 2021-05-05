package com.algonquincollege.cst8284.assignment2.util;

import java.util.Random;

/**
 * This class generates different sided-dice using an enum type and one roll method.
 * It is part of the bonus for Assignment 2
 * @author Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @since javac 1.8.0_271
 */
public class BonusDiceBag {

	/**
	 * This enum represents the different kinds of dice as constants
	 * The dice are D3, D6, D8, D10, D10, and D100
	 * The number is the number of faces on the dice
	 * @author Daniel Pereira Castillo
	 */
	public enum Dice{
		FOUR (4),
		SIX (6),
		EIGHT (8),
		TEN (10),
		TWELVE (12),
		TWENTY (20),
		ONEHUNDRED (100);

		private final int sides;

		/**
		 * This  is the constructor for the dice enum
		 * @param sides are the number of sides on the dice
		 */
		private Dice(int sides){
			this.sides = sides;
		}
	}
	
	/**
	 * This creates a new Random number generator
	 */
	private static Random random = new Random();

	/**
	 * This method rolls the dice and provides a random number
	 * @param dice is the kind of dice that is rolled (D4,D6,D8, etc.)
	 * @return the method returns a randomized integer value within the dice limits 
	 * a D4 can only return a number from 1 to 4, for example
	 */
	public static int roll(Dice dice) {
		return random.nextInt(dice.sides) + 1;

	}
}


