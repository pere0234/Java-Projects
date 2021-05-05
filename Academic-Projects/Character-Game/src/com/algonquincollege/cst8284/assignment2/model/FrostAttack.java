package com.algonquincollege.cst8284.assignment2.model;
/**
 * This class represents a Frost attack that a character generates. 
 * It is a subclass of the Attack class
 * @author Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @since javac 1.8.0_271
 * @see Attack
 */
public class FrostAttack extends Attack {
	/**
	 * No-argument constructor of the class.
	 * It is chained to the overloaded constructor FrostAttack(int damage)
	 */
	public FrostAttack() {
		this(0);
	}

	/**
	 * The parameterized constructor of the class which invokes the 
	 * parameterized constructor of the Attack superclass
	 * @param damage is the damage dealt by the character 
	 */
	public FrostAttack(int damage) {
		super(damage);
	
	}

}
