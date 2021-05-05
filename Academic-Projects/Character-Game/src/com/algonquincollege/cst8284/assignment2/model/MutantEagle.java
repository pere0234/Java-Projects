package com.algonquincollege.cst8284.assignment2.model;

import com.algonquincollege.cst8284.assignment2.util.BonusDiceBag;
import com.algonquincollege.cst8284.assignment2.util.DiceBag;

/**
 * This class represents a Mutant Eagle as combat character
 * It is a subclass of the character class
 * @author Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @since javac 1.8.0_271
 * @see Character
 */
public class MutantEagle extends Character {
	
	/**
	 * The instance variables represent the battle stats of the Mutant Eagle
	 * They are calculated via dice rolls from the BonusDiceBag class based on the character set guide
	 * mutantEagleHealth represents the current health of the Mutant Eagle
	 * mutantEagleMaxHealth represents the maximum health of the Mutant Eagle on creation
	 * mutantEagleStrength represents the combat strength
	 * attackChance represents a random number which is compared against
	 * to calculate the chances of a given attack being generated
	 */
	private int mutantEagleHealth = (1 * BonusDiceBag.roll(BonusDiceBag.Dice.TWENTY));
	private int mutantEagleMaxHealth = mutantEagleHealth;
	private int mutantEagleStrength = (5 * BonusDiceBag.roll(BonusDiceBag.Dice.FOUR));
	private int attackChance = BonusDiceBag.roll(BonusDiceBag.Dice.ONEHUNDRED);
	
	
	/**
	 * This is the no-arg constructor of the Mutant Eagle class
	 * It is chained to MutantEagle (int maxHealth, int currentHealth, int strength)
	 */
	public MutantEagle() {
		this(0,0,0);
	}
	
	/**
	 * This is the parameterized constructor of the MutantEagle class
	 * it invokes the parameterized constructor of the superclass
	 * The constructor sets the values to those of the Mutant Eagle
	 * @param maxHealth is the character's maximum health upon creation
	 * @param currentHealth is the character's current health
	 * @param strength is the combat strength of the character
	 */
	public MutantEagle(int maxHealth, int currentHealth, int strength) {
		super(maxHealth, currentHealth, strength);
		setMaxHealth(mutantEagleHealth);
		setCurrentHealth(mutantEagleMaxHealth);
		setStrength(mutantEagleStrength);
	
	}
	
	/**
	 * This method overrides the attack() method from the Character class
	 * It creates an attack from the Mutant Eagle
	 * An attack damage amount myDamage represents the damage dealt by the Mutant Eagle
	 * Two attacks are instantiated: a Fire attack and a Basic attack
	 * If attackChance is less than or equal to 75, a Basic attack is rendered
	 * Else, a Fire attack is rendered 
	 */
	@Override
	public Attack attack() {
		int myDamage = getStrength() + DiceBag.rollTwelveSidedDice();
		Attack fireAttack = new FireAttack(myDamage);
		Attack basicAttack = new BasicAttack(myDamage);
		
		if (attackChance <= 75) {
			return basicAttack;
		}
		else {
			return fireAttack;
		}
		
	}
	
	/**
	 * This method overrides the defend() method from the Character class
	 * It processes what to do when the Mutant Eagle is attacked
	 * A new AttackReport is instantiated and altered depending on the kind of attack
	 * Frost and Basic attacks are processed differently inflict different damage
	 * @param attack the Attack against this character
	 * @return an AttackReport on how this character defended
	 */
	@Override
	public AttackReport defend(Attack attack) {
		AttackReport result = new AttackReport();
		
		/**
		 * This checks to process whether or not the attack taken is
		 * a Frost attack 
		 */
		if(attack instanceof FrostAttack) {
			int damage = attack.getDamage();
			
			/**
			 * the damage taken is halved per the character set guide and
			 * resolved to a frostDamage variable
			 */
			int frostDamage = (damage/2);
			
			/**
			 * The damage is subtracted from the Mutant Eagle's current health
			 */
			int newHealth = (super.getCurrentHealth() - frostDamage);
			
			/**
			 * The current health is changed to the new health after the damage is calculated
			 */
			super.setCurrentHealth(newHealth);
			
			/**
			 * The attack report is altered to reflect the kind of damage taken
			 * as well as to report the actual damage taken
			 */
			result.setReport(super.getTypeClassName() + " Took FROST damage, damage is halved,");
			result.setActualDamage(frostDamage);
		}
		
		/**
		 * This checks to see if the attack taken is a Basic attack
		 */
		else if(attack instanceof BasicAttack) {
			
			/**
			 * If attackChance is less than or equal to 75, the attack is dodged
			 * A report is generated stating that the attack was dodged
			 * The actual damage taken is set to 0
			 */
			if(attackChance <= 75) {
				result.setReport(super.getTypeClassName() + " Took no damage, dodged an attack,");
				result.setActualDamage(0);
			}
			
			/**
			 * Else if attackChance is more than 75, the attack was not dodged and full damage is taken
			 * The damage is subtracted from the Mutant Eagle's current health
			 * The current health is set to the new health after the damage is calculated
			 * The attack report is modified to reflect the kind of damage taken
			 * as well as to report the actual damage taken
			 */
			else {
				int damage = attack.getDamage();
				int newHealth = (super.getCurrentHealth() - damage);
				
				setCurrentHealth(newHealth);
				
				result.setReport(super.getTypeClassName() + " Took full BASIC damage,");
				result.setActualDamage(damage);
			}
			
		}
		
		return result;
	}

}