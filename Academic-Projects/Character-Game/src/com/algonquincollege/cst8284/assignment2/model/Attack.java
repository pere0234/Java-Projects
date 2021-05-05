/*
 * Copyright(2021) Algonquin College
 * CST8284 (21W) Assignment 2 Starter Code
 */
package com.algonquincollege.cst8284.assignment2.model;

/**
 * This abstract class represents an Attack a that character generates and
 * contains a damage score.
 * @author Piedas, modified by Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @since javac 1.8.0_271
 * @see FireAttack
 * @see FrostAttack
 * @see BasicAttack
 */
public abstract class Attack {
	
	/**
	 * the damage of this attack
	 */
	private int damage;
	
	/**
	 * This constructor sets the damage to zero (i.e. 0)
	 */
	public Attack() {
		this(0);
	}
	
	/**
	 * This constructor sets the value specified into the damage
	 * field, no data validation is performed.
	 * @param damage the damage score for this attack
	 */
	public Attack(int damage) {
		this.damage = damage;
	}

	/**
	 * Gets the damage for this attack
	 * @return the damage score for this attack
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the damage score for this attack, no
	 * data validation is performed.
	 * @param damage The amount of damage for this attack.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * This overridden toString() returns the name of the class,
	 * removing any package information with formatted text:
	 * <i>ClassName</i> with <i>%d</i> damage
	 * A newline character is not added to the end of the String.
	 */
	@Override
	public String toString() {
		String className = getClass().getName();
		int index = className.lastIndexOf("."); // where is last . in packages
		if(index != -1) {
			className = className.substring(index + 1); // +1 to skip the . char
		}
		return String.format("%s with %d damage", className,
				getDamage());
	}

}
