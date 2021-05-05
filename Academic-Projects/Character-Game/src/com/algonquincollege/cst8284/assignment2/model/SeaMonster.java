package com.algonquincollege.cst8284.assignment2.model;

import com.algonquincollege.cst8284.assignment2.util.BonusDiceBag;
import com.algonquincollege.cst8284.assignment2.util.DiceBag;

/**
 * This class represents a Sea Monster as combat character
 * It is a subclass of the Character class
 * @author Daniel Pereira Castillo
 * Student number: 041009960
 * Lab Section 302
 * @since javac 1.8.0_271
 * @see Character
 */
public class SeaMonster extends Character {
	
	/**
	 * The instance variables represent the battle stats of the Sea Monster
	 * They are calculated via dice rolls from the BonusDiceBag class based on the character set guide
	 * seaMonsterHealth represents the current health of the Sea Monster
	 * seaMonsterMaxHealth represents the maximum health of the Sea Monster on creation
	 * seaMonsterStrength represents the combat strength
	 * attackChance represents a random number which is compared against
	 * to calculate the chances of a given attack being generated
	 */
	private int seaMonsterHealth = (5 * BonusDiceBag.roll(BonusDiceBag.Dice.TWENTY));
	private int seaMonsterMaxHealth = seaMonsterHealth;
	private int seaMonsterStrength = (5 * BonusDiceBag.roll(BonusDiceBag.Dice.TWENTY));
	private int attackChance = BonusDiceBag.roll(BonusDiceBag.Dice.ONEHUNDRED);

	
	/**
	 * This is the no-arg constructor of the SeaMonster class
	 * It is chained to SeaMonster (int maxHealth, int currentHealth, int strength)
	 */
	public SeaMonster() {
		this(0,0,0);
	}
	
	/**
	 * This is the parameterized constructor of the SeaMonster class
	 * it invokes the parameterized constructor of the superclass
	 * The constructor sets the values to those of the Sea Monster
	 * @param maxHealth is the character's maximum health upon creation
	 * @param currentHealth is the character's current health
	 * @param strength is the combat strength of the character
	 */
	public SeaMonster(int maxHealth, int currentHealth, int strength) {
		super(maxHealth, currentHealth, strength);
		setMaxHealth(seaMonsterHealth);
		setCurrentHealth(seaMonsterMaxHealth);
		setStrength(seaMonsterStrength);
	
	}
	
	/**
	 * This method overrides the attack() method from the Character class
	 * It creates an attack from the Sea Monster
	 * An attack damage amount myDamage represents the damage dealt by the SeaMonster
	 * Two attacks are instantiated: a Frost attack and a Basic attack
	 * If attackChance is less than or equal to 50, a Basic attack is rendered
	 * else, a Frost attack is rendered 
	 */
	@Override
	public Attack attack() {
		int myDamage = getStrength() + DiceBag.rollFourSidedDice();
		FrostAttack frostAttack = new FrostAttack(myDamage);
		BasicAttack basicAttack = new BasicAttack(myDamage);
		
		if (attackChance <= 50) {
			return basicAttack;
		}
		
		else {
			return frostAttack;
		}
		
	}

	/**
	 * This method overrides the defend() method from the Character class
	 * It processes what to do when the Sea Monster is attacked by another character
	 * A new AttackReport is instantiated and modified depending on the kind of attack
	 * Fire, Basic, and Frost attacks are processed differently and inflict different damage
	 * @param attack the Attack against this character
	 * @return an AttackReport on how this character defended
	 */
	@Override
	public AttackReport defend(Attack attack) {
		AttackReport result = new AttackReport();
		
		/**
		 * This checks whether or not the attack taken is a Fire attack
		 */
		if(attack instanceof FireAttack) {
			int damage = attack.getDamage();
			
			/**
			 * The amount of damage inflicted is set to a value per the character set guide
			 * and resolved to a fireDamage variable
			 */
			int fireDamage = (damage + DiceBag.rollTwentySidedDice());
			
			/**
			 * The damage is subtracted from the Sea Monster's current health and the 
			 * difference resolved to a newHealth variable
			 */
			int newHealth = (super.getCurrentHealth() - fireDamage);
			
			/**
			 * The current health is changed to the new health after the damage is calculated
			 */
			super.setCurrentHealth(newHealth);
			
			/**
			 * The attack report is altered to reflect the kind of damage taken
			 * as well as to report the actual damage taken
			 */
			result.setReport(super.getTypeClassName() + " Took additional FIRE damage,");
			result.setActualDamage(fireDamage);
		}
		
		/**
		 * This checks whether or not the attack taken is a Basic attack 
		 */
		else if(attack instanceof BasicAttack) {
			int damage = attack.getDamage();
			
			/**
			 * The amount of damage inflicted is set to a value per the character set guide
			 * and resolved to a newHealth variable
			 * Basic attacks inflict full damage
			 */
			int newHealth = (super.getCurrentHealth() - damage);
			
			/**
			 * The current health is changed to the new health after the damage is calculated
			 */
			super.setCurrentHealth(newHealth);
			
			/**
			 * The attack report is altered to reflect the kind of damage taken
			 * as well as to report the actual damage taken
			 */
			result.setReport(super.getTypeClassName() + " Took full BASIC damage,");
			result.setActualDamage(damage);
		}
		
		/**
		 * This checks whether or not the attack taken is a Frost attack 
		 */
		else if(attack instanceof FrostAttack) {
			
			/**
			 * Frost attacks inflict 0 damage the attack report is altered to reflect the kind of damage taken
			 * as well as to report the actual damage taken
			 */
			result.setReport(getTypeClassName() + " Took no damage, immune to FROST attacks,");
			result.setActualDamage(0);
		}
		
		return result;
	}

}
