package objects;
import java.util.*;

import position.PositionVector;
import world.World;
import be.kuleuven.cs.som.annotate.*;
import faction.Faction;

/**
 * @invar  The name of each unit must be a valid name for any
 *         unit.
 *       | isValidName(getName())
 * @invar  The strength of each unit must be a valid strength for any
 *         unit.
 *       | isValidStrength(getStrength())
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | isValidWeight(getWeight())
 * @invar  The maxHP of each unit must be a valid maxHP for any
 *         unit.
 *       | isValidMaxHP(getMaxHP())
 * @invar  The maxStamina of each unit must be a valid maxStamina for any
 *         unit.
 *       | isValidMaxStamina(getMaxStamina())
 * @invar  The currentHP of each unit must be a valid currentHP for any
 *         unit.
 *       | isValidCurrentHP(getCurrentHP())
 * @invar  The currentStamina of each unit must be a valid currentStamina for any
 *         unit.
 *       | isValidCurrentStamina(getCurrentStamina())
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 * @invar  The activityStatus of each unit must be a valid activityStatus for any
 *         unit.
 *       | isValidActivityStatus(getActivityStatus())
 * @invar  The movementstatus of each unit must be a valid movementstatus for any
 *         unit.
 *       | isValidMovementStatus(getMovementStatus())
 * @invar  The current velocity of each unit must be a valid current velocity for any
 *         unit.
 *       | isValidCurrentVelocity(getCurrentVelocity())
 * @invar  The targeted adjacent position of each unit must be a valid targeted adjacent position for any
 *         unit.
 *       | isValidNextPosition(getNextPosition())
 * @invar  The destination of each unit must be a valid destination for any
 *         unit.
 *       | isValidDestination(getDestination())
 * @invar  The decimal stamina of each unit must be a valid decimal stamina for any
 *         unit.
 *       | isValidDoubleStamina(getDoubleStamina())
 * @invar  The work time of each unit must be a valid work time for any
 *         unit.
 *       | isValidWorkTime(getWorkTime())
 * @invar  The attack time of each unit must be a valid attack time for any
 *         unit.
 *       | isValidAttackTime(getAttackTime())
 * @invar  The double type hitpoints of each unit must be a valid double type hitpoints for any
 *         unit.
 *       | isValidDoubleHP(getDoubleHP())
 * @invar  The minimum rest counter of each unit must be a valid minimum rest counter for any
 *         unit.
 *       | isValidMinRestCounter(getMinRestCounter())
 * @invar  The automatic rest counter of each unit must be a valid automatic rest counter for any
 *         unit.
 *       | isValidAutRestCounter(getAutRestCounter())
 * @invar  The faction of each unit must be a valid faction for any
 *         unit.
 *       | isValidFaction(getFaction())
 * @invar  The experience of each unit must be a valid experience for any
 *         unit.
 *       | isValidExp(getExp())
 * @invar  The path queue of each unit must be a valid path queue for any
 *         unit.
 *       | isValidQueue(getQueue())
 * @invar  The inventory of each unit must be a valid inventory for any
 *         unit.
 *       | isValidInventory(getInventory())
 * @invar  The work position of each unit must be a valid work position for any
 *         unit.
 *       | isValidWorkPosition(getWorkPosition())
 * @author Micha�l Dooreman
 * @version	0.23
 */
public class Unit extends GameObject {
	
	/**
	 * Initialize this new Unit with given position, name, weight, strength, agility, toughness and faction.
	 * 
	 * @param  position		The position for this new unit.
	 * @param  name			The name for this new unit.
	 * @param  strength		The strength for this new unit.
	 * @param  agility		The agility for this new unit.
	 * @param  toughness	The toughness for this new unit.
	 * @param  weight		The weight for this new unit.
	 * @param  faction 		The faction for this new unit.
	 * @effect	The position of this new unit is set to the cube centre of the given position.
	 * 			| this.setUnitPosition(centrePosition(position))
	 * @post    The name of this new unit equals the given name.
	 * 			| new.getName().equals(name)
	 * @effect	The strength of this new unit is set to the given strength.
	 * 			| this.setStrength(makeInitialAttValue(strength))
	 * @effect	The agility of this new unit is set to the given agility.
	 * 			| this.setAgility(makeInitialAttValue(agility))
	 * @effect	The toughness of this new unit is set to the given toughness.
	 * 			| this.setToughness(makeInitialAttValue(toughness))
	 * @effect	The weight of this new unit is set to the given weight.
	 * 			| this.setWeight(makeInitialAttValue(weight))
	 * @effect  The maximum hitpoints of this new unit are calculated and set.
	 * 			| setMaxHP(calcMaxHPStam(getWeight(), getToughness()))
	 * @effect  The maximum stamina of this new unit is set.
	 * 			| setMaxStamina(calcMaxHPStam(getWeight(), getToughness()))
	 * @effect	The double type hitpoints of this new unit are set to the maximum amount of hitpoints this unit can have.
	 *       	| this.setDoubleHP(this.getMaxHP())
	 * @effect  The double type stamina of this new unit are set to the maximum stamina this unit can have
	 *       	| this.setDoubleStamina(this.getMaxStamina())
	 * @post    The orientation of this new unit is Pi/2.
	 * 			| new.getOrientation().fuzzyEquals(Math.PI/2.0)
	 * @effect  The activityStatus of this new unit is set to default.
	 *       	| this.setActivityStatus("default")
	 * @effect  The current velocity of this new unit is set to the the zero vector.
	 *          | this.setCurrentVelocity(new PositionVector(0, 0, 0))
	 * @effect  The targeted next position of this new unit is set to it's current position.
	 *          | this.setNextPosition(new PositionVector(this.getUnitPosition().getXArgument(),this.getUnitPosition().getYArgument(),
	 *		    | 			this.getUnitPosition().getZArgument()));
	 * @effect  The destination of this new unit is set to it's current position..
	 *       	| this.setDestination(this.getUnitPosition().getXArgument(),this.getUnitPosition().getYArgument(),
	 *       	|		this.getUnitPosition().getZArgument()))
	 * @effect 	The sprinting status of this new unit is set to false by default.
	 *       	| this.setSprint(false)
	 * @effect  The work time of this new unit is reseted.
	 *       	| this.resetWorkTime()
	 * @effect  The attack time of this new unit is set to 0.
	 *       	| this.setAttackTime(0)
	 * @effect  The minimum rest counter of this new unit is set to 0.
	 *       	| this.setMinRestCounter(0)
	 * @effect  The automatic rest counter of this new unit is set to 0.
	 *       	| this.setAutRestCounter(0)
	 * @effect  The default behaviour of this new unit is set false.
	 *       	| this.setDefaultBehaviour(false)
	 * @effect 	The faction of this new unit is set to the given faction.
	 *       	| this.setFaction(faction)
	 * @effect 	The experience of this new unit is set to 0.
	 *       	| this.setExp(0)
	 * @effect 	The path queue of this new unit is set to an empty hash map.
	 *       	| this.setQueue(new HashMap<PositionVector, Integer>())
	 * @effect 	The inventory of this new unit is set to an empty hash set.
	 *       	| this.setInventory(new HashSet<Material>())
	 * @effect 	The work position of this new unit is set to null.
	 *       	| this.setWorkPosition(null)
	 * @throws  IllegalArgumentException
	 * 		    The given name is not a valid name.
	 * 			| ! isValidName(name)
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this unit.
	 * 			| !isValidUnitPosition(position)
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	 */
	public Unit(PositionVector position, String name, int strength, int agility, int toughness,int weight, Faction faction) 
													throws IllegalArgumentException, NullPointerException {
		super(centrePosition(position));
		this.setName(name);
		this.setStrength(makeInitialAttValue(strength));
		this.setAgility(makeInitialAttValue(agility));
		this.setToughness(makeInitialAttValue(toughness));
		this.setWeight(makeInitialAttValue(weight));
		this.setMaxHP(calcMaxHPStam(getWeight(), getToughness()));
		this.setMaxStamina(calcMaxHPStam(getWeight(), getToughness()));
		this.setDoubleHP(this.getMaxHP());
		this.setDoubleStamina(this.getMaxStamina());
		this.setOrientation(Math.PI/2.0);
		this.setActivityStatus("default");
		this.setCurrentVelocity(new PositionVector(0, 0, 0));
		this.setNextPosition(new PositionVector(this.getUnitPosition().getXArgument(),this.getUnitPosition().getYArgument(),
				this.getUnitPosition().getZArgument()));
		this.setDestination(new PositionVector(this.getUnitPosition().getXArgument(),this.getUnitPosition().getYArgument(),
				this.getUnitPosition().getZArgument()));
		this.setSprint(false);
		this.resetWorkTime();
		this.setAttackTime(0);
		this.setMinRestCounter(0);
		this.setAutRestCounter(0);
		this.setDefaultBehaviour(false);
		this.setFaction(faction);
		this.setExp(0);
		this.setQueue(new HashMap<PositionVector, Integer>());
		this.setInventory(new HashSet<Material>());
		this.setWorkPosition(null);
	}
	
	/**
	 * Initialize this new Unit with given position, name and random attribute values.
	 * @param  position		The position for this new unit.
	 * @param  name			The name for this new unit.
	 * @param  faction		The faction for this new unit.
	 * @effect	A new unit is initialized with the given position, name and random initial attribute values and the given faction.
	 * 			| this(position, name, randomInitialAttValue(), randomInitialAttValue(),
	 * 										 randomInitialAttValue(), randomInitialAttValue(), faction)
	 * @throws  IllegalArgumentException
	 * 		    The given name is not a valid name.
	 * 			| ! isValidName(name)
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this unit.
	 * 			| !isValidUnitPosition(position)
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	 */
	public Unit(PositionVector position, String name, Faction faction) throws IllegalArgumentException, NullPointerException {
		this(position, name, randomInitialAttValue(), randomInitialAttValue(), randomInitialAttValue(), randomInitialAttValue(),
				faction);
	}
	
	
	/**
	 * Return the name of this unit.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}
	
	//do with regex!
	/**
	 * Check whether the given name is a valid name for
	 * any unit.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return	True if and only if, the name is longer than 2 character, starts with an uppercase letter and only contains valid
	 * 			characters and the given name is not null.
	 *       	| result == ((name != null) && (name.length() >= 2) && (Character.isLetter(name.charAt(0))) && (Character.isUpperCase(name.charAt(0)))
	 *       	| 																						&& (validCharInName(name))
	 * @throws	NullPointerException
	 * 			The name is not effective.
	 * 			| name == null
	*/
	private static boolean isValidName(String name) throws NullPointerException {
		return ((name != null) && (name.length() >= 2) && (Character.isLetter(name.charAt(0))) && (Character.isUpperCase(name.charAt(0)))
				&& (validCharInName(name)));
	}
	
	/**
	 * Checks whether the given name consists of valid characters.
	 * @param name	The given name.
	 * @return	Return true if and only if the name consists of  allowed characters.
	 * 			| ArrayList<Character> validCharactersList = new ArrayList<Character>()
	 *			| char[] validCharacters = getValidCharacters()
	 *			| for(char character: validCharacters){
	 *		  	|	validCharactersList.add(character)
	 *	  		|  }
	 *			| List<Character> characters = new ArrayList<>()
	 *			| int k = 0
	 *			| while(k < name.length()) {
	 *			|	characters.add(name.charAt(k))
	 *			|	k++
	 *			| }
	 *			| int i = 0
	 *			| boolean result = true
	 *			| while ((i < characters.size()) && (result == true)) {
	 *			|	if (! validCharactersList.contains(Character.toLowerCase(characters.get(i)))) {
	 *			|		result = false
	 *			|	}
	 *			|	i++
	 *			| }
	 * 			| result == result 
	 * @throws	NullPointerException
	 * 			The name is not effective.
	 * 			| name == null
	 */
	private static boolean validCharInName(String name) throws NullPointerException {
		ArrayList<Character> validCharactersList = new ArrayList<Character>();
		char[] validCharacters = getValidCharacters();
		for(char character: validCharacters){
			validCharactersList.add(character);
		}
		List<Character> characters = new ArrayList<>();
		int k = 0;
		while(k < name.length()) {
			characters.add(name.charAt(k));
			k++;
		}
		int i = 0;
		boolean result = true;
		while ((i < characters.size()) && (result == true)) {
			if (! validCharactersList.contains(Character.toLowerCase(characters.get(i)))) {
				result = false;
			}
			i++;
		}
		return result;
	}
	
	private static char[] validCharacters = {' ','"','\'','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	/**
	 * Return the array containing the legal characters for names.
	 */
	@Basic @Immutable
	private static char[] getValidCharacters() {
		return validCharacters;
	}
	
	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param  name
	 *         The new name for this unit.
	 * @post   The name of this new unit is equal to
	 *         the given name.
	 *       | new.getName().equals(name)
	 * @throws IllegalArgumentException
	 *         The given name is not a valid name for any
	 *         unit.
	 *       | ! isValidName(getName())
	 * @throws	NullPointerException
	 * 			The name is not effective.
	 * 			| name == null
	 */
	@Raw @Model
	private void setName(String name) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * Variable registering the name of this unit.
	 */
	private String name;
	
	/**
	 * Rename this unit to a given name.
	 * @param  name
	 * 		   The new name for this unit.
	 * @effect This unit's name is set to the given name
	 * 			| this.setName(name)
	 * @throws	IllegalArgumentException
	 * 			The given name is not a valid name.
	 * 			| ! isValidName(name)
	 * @throws	NullPointerException	
	 * 			The given name is not effective.
	 * 			| name == null
	 */
	@Raw
	public void changeName(String name) throws IllegalArgumentException, NullPointerException{
		this.setName(name);
	}

	
	/**
	 * Check whether the given weight is a valid weight for
	 * any unit.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return 
	 *       | result == ((weight >= 1) && (weight <= 200) && (weight >= ((this.getStrength()+this.getAgility())/2)))
	*/
	@Raw
	protected boolean isValidWeight(int weight) {
		int strength = this.getStrength();
		int agility = this.getAgility();
		int minBorder = (int) ((strength + agility)/2.0);
		return ((weight >= 1) && (weight <= 200) && (weight >= minBorder));
	}
	
	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param  weight
	 *         The new weight for this unit.
	 * @post    If the given weight is a valid weight for any unit,
	 *          the weight of this new unit is equal to the given
	 *          weight.
	 *       	| if (isValidWeight(weight))
	 *       	|   then new.getWeight() == weight
	 * @post	If the given weight is more than the maximum attribute value for any unit, this unit's weight is set to 
	 * 			the maximum attribute value for any unit..
	 * 			| if (weight > maxAttValue)
	 * 			| 	this.weight = maxAttValue
	 * @post	If the given weight is smaller than half of the sum of this unit's agility and strength,
	 * 			this unit's weight is set to half of the sum of this unit's agility and strength.
	 * 			| if (weight < (this.getStrength()+this.getAgility())/2)
	 * 			| 	this.weight = (this.getStrength()+this.getAgility())/2
	 */
	@Override @Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight))
			this.weight = weight;
		if (weight > maxAttValue) {
			this.weight = maxAttValue;
		}
		if (weight < (this.getStrength()+this.getAgility())/2) {
			this.weight = (this.getStrength()+this.getAgility())/2;
		}
	}
	
	
	/**
	 * Return the strength of this unit.
	 */
	@Basic @Raw
	public int getStrength() {
		return this.strength;
	}
	
	/**
	 * Check whether the given strength is a valid strength for
	 * any unit.
	 *  
	 * @param  strength
	 *         The strength to check.
	 * @return 
	 *       | result == ((strength >= 1) && (strength <= 200))
	*/
	private static boolean isValidStrength(int strength) {
		return ((strength >= 1) && (strength <= 200));
	}
	
	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param  strength
	 *         The new strength for this unit.
	 * @post    If the given strength is a valid strength for any unit,
	 *          the strength of this new unit is equal to the given
	 *          strength.
	 *       	| if (isValidStrength(strength))
	 *       	|   then new.getStrength() == strength
	 * @post	If the given strength is bigger than the maximum attribute value for any unit, this unit's strength is set to 
	 * 			the maximum attribute value for any unit.
	 * 			| if (strength > maxAttValue)
	 * 			| 	this.strength = maxAttValue
	 * @post	If the given strength is smaller than 1, this unit's strength is set to 1.
	 * 			| if (strength < 1)	
	 * 			| 	this.strength = 1
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
		if (strength > maxAttValue) {
			this.strength = maxAttValue;
		}
		if (strength < 1) {
			this.strength = 1;
		}
	}
	
	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength;

	/**
	 * Return the agility of this unit.
	 */
	@Basic @Raw
	public int getAgility() {
		return this.agility;
	}
	
	/**
	 * Check whether the given agility is a valid agility for
	 * any unit.
	 *  
	 * @param  agility
	 *         The agility to check.
	 * @return 
	 *       | result == ((agility >= 1) && (agility <= 200))
	*/
	private static boolean isValidAgility(int agility) {
		return ((agility >= 1) && (agility <= 200));
	}
	
	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param  agility
	 *         The new agility for this unit.
	 * @post    If the given agility is a valid agility for any unit,
	 *          the agility of this new unit is equal to the given
	 *          agility.
	 *       	| if (isValidAgility(agility))
	 *       	|   then new.getAgility() == agility
	 * @post	If the given agility is bigger than the maximum attribute value, this unit's agility is set to 200.
	 * 			| if (agility > maxAttValue) 
	 *			|	this.agility = maxAttValue
	 * @post	If the given agility is smaller than 1, this unit's agility is set to 1.
	 * 			| if (agility < 1)
	 * 			| 	this.agility = 1
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		if (agility > maxAttValue) {
			this.agility = maxAttValue;
		}
		if (agility < 1) {
			this.agility = 1;
		}
	}
	
	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;

	/**
	 * Return the toughness of this unit.
	 */
	@Basic @Raw
	public int getToughness() {
		return this.toughness;
	}
	
	/**
	 * Check whether the given toughness is a valid toughness for
	 * any unit.
	 *  
	 * @param  toughness
	 *         The toughness to check.
	 * @return 
	 *       | result == ((toughness >=1) && (toughness <= 200))
	*/
	private static boolean isValidToughness(int toughness) {
		return ((toughness >=1) && (toughness <= 200));
	}
	
	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param  toughness
	 *         The new toughness for this unit.
	 * @post    If the given toughness is a valid toughness for any unit,
	 *          the toughness of this new unit is equal to the given
	 *          toughness.
	 *       	| if (isValidToughness(toughness))
	 *       	|   then new.getToughness() == toughness
	 * @post	If the given toughness is bigger than the maximum attribute value for any unit, this unit's toughness is set to 
	 * 			the maximum attribute value for any unit.
	 * 			| if (toughness > maxAttValue)
	 * 			| 	this.toughness = maxAttValue
	 * @post	If the given toughness is smaller than 1, this unit's toughness is set to 1.
	 * 			| if (toughness < 1)
	 * 			| 	this.toughness = 1
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
		if (toughness > maxAttValue) {
			this.toughness = maxAttValue;
		}
		if (toughness < 1) {
			this.toughness = 1;
		}
	}
	
	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;
	
	/**
	 * Return the maxHP of this unit.
	 */
	@Basic @Raw
	public int getMaxHP() {
		return this.maxHP;
	}
	
	/**
	 * Check whether the given maxHP is a valid maxHP for
	 * any unit.
	 *  
	 * @param  maxHP
	 *         The maxHP to check.
	 * @return 
	 *       | result == (maxHP == calcMaxHPStam(getWeight(), getToughness()))
	*/
	@Raw
	private boolean isValidMaxHP(int maxHP) {
		return (maxHP == calcMaxHPStam(this.getWeight(), this.getToughness()));
	}
	
	/**
	 * Set the maxHP of this unit to the given maxHP.
	 * 
	 * @param  maxHP
	 *         The new maxHP for this unit.
	 * @pre    The given maxHP must be a valid maxHP for any
	 *         unit.
	 *       | isValidMaxHP(maxHP)
	 * @post   The maxHP of this unit is equal to the given
	 *         maxHP.
	 *       | new.getMaxHP() == maxHP
	 */
	@Raw @Model
	private void setMaxHP(int maxHP) {
		assert isValidMaxHP(maxHP);
		this.maxHP = maxHP;
	}
	
	/**
	 * Variable registering the maxHP of this unit.
	 */
	private int maxHP;

	
	/**
	 * Return the maxStamina of this unit.
	 */
	@Basic @Raw
	public int getMaxStamina() {
		return this.maxStamina;
	}
	
	/**
	 * Check whether the given maxStamina is a valid maxStamina for
	 * any unit.
	 *  
	 * @param  maxStamina
	 *         The maxStamina to check.
	 * @return 
	 *       | result == (maxStamina == calcMaxHPStam(this.getWeight(), this.getToughness()))
	*/
	@Raw
	private boolean isValidMaxStamina(int maxStamina) {
		return (maxStamina == calcMaxHPStam(this.getWeight(), this.getToughness()));
	}
	
	/**
	 * Set the maxStamina of this unit to the given maxStamina.
	 * 
	 * @param  maxStamina
	 *         The new maxStamina for this unit.
	 * @pre    The given maxStamina must be a valid maxStamina for any
	 *         unit.
	 *       	| isValidMaxStamina(maxStamina)
	 * @post   The maxStamina of this unit is equal to the given
	 *         maxStamina.
	 *       	| new.getMaxStamina() == maxStamina
	 */
	@Raw @Model
	private void setMaxStamina(int maxStamina) {
		assert isValidMaxStamina(maxStamina);
		this.maxStamina = maxStamina;
	}
	
	/**
	 * Variable registering the maxStamina of this unit.
	 */
	private int maxStamina;
	
	/**
	 * Calculates the value for maxHP and maxStamina (hence they are calculated with the same formula) for a given weight and toughness.
	 * @param   weight
	 * 		    The weight.
	 * @param   toughness
	 * 		    The toughness.
	 * @pre     The weight has to be a valid weight.
	 * 			| isValidWeight(weight)
	 * @pre     The toughness has to be a valid toughness.
	 * 			| isValidToughness(toughness)
	 * @return	Return the maximum hitpoints calculated by the given formula.  
	 * 			| result == 200*((int) Math.ceil(weight()/100.0))*((int) Math.ceil(toughness()/100.0))
	 */
	private int calcMaxHPStam(int weight, int toughness) {
		assert ((isValidWeight(weight)) && (isValidToughness(toughness)));
		
		return 200*((int) Math.ceil(weight/100.0))*((int) Math.ceil(toughness/100.0));
	}
	
	/**
	 * Return the currentHP of this unit.
	 */
	@Basic @Raw
	public int getCurrentHP() {
		return this.currentHP;
	}
	
	/**
	 * Check whether the given currentHP is a valid currentHP for
	 * any unit.
	 *  
	 * @param  currentHP
	 *         The currentHP to check.
	 * @return 
	 *       | result == ((currentHP <= this.getMaxHP()) && (currentHP >= 0))
	*/
	@Raw
	private boolean isValidCurrentHP(int currentHP) {
		return ((currentHP <= this.getMaxHP()) && (currentHP >= 0));
	}
	
	/**
	 * Set the currentHP of this unit to the given currentHP.
	 * 
	 * @param  currentHP
	 *         The new currentHP for this unit.
	 * @pre    The given currentHP must be a valid currentHP for any
	 *         unit.
	 *       | isValidCurrentHP(currentHP)
	 * @post   The currentHP of this unit is equal to the given
	 *         currentHP.
	 *       | new.getCurrentHP() == currentHP
	 */
	@Raw
	private void setCurrentHP(int currentHP) {
		assert isValidCurrentHP(currentHP);
		this.currentHP = currentHP;
	}
	
	/**
	 * Variable registering the currentHP of this unit.
	 */
	private int currentHP;
	
	
	
	/**
	 * Return the currentStamina of this unit.
	 */
	@Basic @Raw
	public int getCurrentStamina() {
		return this.currentStamina;
	}
	
	/**
	 * Check whether the given currentStamina is a valid currentStamina for
	 * any unit.
	 *  
	 * @param  currentStamina
	 *         The currentStamina to check.
	 * @return 
	 *       | result == ((currentStamina >= 0) && (currentStamina <= this.getMaxStamina()))
	*/
	@Raw
	private boolean isValidCurrentStamina(int currentStamina) {
		return ((currentStamina >= 0) && (currentStamina <= this.getMaxStamina()));
	}
	
	/**
	 * Set the currentStamina of this unit to the given currentStamina.
	 * 
	 * @param  currentStamina
	 *         The new currentStamina for this unit.
	 * @pre    The given currentStamina must be a valid currentStamina for any
	 *         unit.
	 *       | isValidCurrentStamina(currentStamina)
	 * @post   The currentStamina of this unit is equal to the given
	 *         currentStamina.
	 *       | new.getCurrentStamina() == currentStamina
	 */
	@Raw
	private void setCurrentStamina(int currentStamina) {
		assert isValidCurrentStamina(currentStamina);
		this.currentStamina = currentStamina;
	}
	
	/**
	 * Variable registering the currentStamina of this unit.
	 */
	private int currentStamina;
	
	/**
	 * Return the orientation of this unit.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	
	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this unit.
	 * @post   The orientation of this unit is equal to the given orientation modulo 2*PI.
	 *       | if (isValidOrientation(orientation))
	 *       |   then new.getOrientation() == orientation %(2*Math.PI)
	 */
	@Raw @Model
	private void setOrientation(double orientation) {
			this.orientation =  orientation %(2*Math.PI);
	}
	
	/**
	 * Variable registering the orientation of this unit.
	 */
	private double orientation;
	
	/**
	 * Let time advance for this unit for a given amount of time.
	 * @param time	The given amount of time.
	 * @effect	This unit's activity status is checked.
	 * 			If this unit is falling, and it reached it's next position and destination, it's hitpoints are decreased by 10 and
	 * 			it either continues falling if it can, else it's activity status is set to default and it's velocity 
	 * 			to the zero vector.
	 * 			If this unit is falling and has not yet reached it's next position and destination, it moves in the fall direction.
	 * 			If this unit should fall, it falls and time advances for the given time.
	 * 			If this unit is doing nothing and it's default behaviour is activated, it'll do a random action.
	 * 			If the unit is attacking, it continues it's attack.
	 * 			If it's minimum rest counter isn't zero yet, it'll continue to rest until the counter reaches zero, then it'll 
	 * 			continue or start with it's planned activity.
	 * 			If it  has gone more than 3 minutes without resting, it'll start to rest and the automatic rest counter is reset.
	 * 			If it didn't reach it's destination or next position yet or it's activity status is move, it moves.
	 * 			If it's activity status is work, it works.
	 * 			If it's activity status is rest, it rests.
	 * 			If it's activity status is default and default behaviour is not activated, is starts doing a random activity and it's 
	 * 			automatic rest counter increases by the given time.
	 * @throws	IllegalArgumentException
	 * 			Time is either negative or equal to or greater then 0.2s.
	 */
	@Override
	public void advanceTime(double time) 
								throws IllegalArgumentException {
		if ((time < 0) || (time >= 0.2)) {
			throw new IllegalArgumentException();
		}
		String status = this.getActivityStatus();
		if(status.equals("fall"))
			//Unit fell 1 cube
			if((this.getUnitPosition().equals(this.getNextPosition())) && (this.getUnitPosition().equals(this.getDestination()))){
				this.decreaseHP(10);
				if(this.fallCheck()){
					this.fall();
					this.advanceTime(time);
				}
				else{
					this.setActivityStatus("default");
					this.setCurrentVelocity(new PositionVector(0, 0, 0));
				}
			}
			//Unit has not yet fallen down a whole cube.
			else{
				this.miniMove(time, 1);
			}
		else if(this.fallCheck()){
				this.fall();
				this.advanceTime(time);
		}
		else if ((status.equals("default") && (this.getDefaultBehaviour() == true) && ((this.getUnitPosition()).equals(this.getNextPosition())
				&& (this.getUnitPosition()).equals(this.getDestination())))){
				this.randomBehaviour();
				status = this.getActivityStatus(); 
			}
		else if (status.equals("attack")) {
				this.doAttack(time);
			}
		else if (this.getMinRestCounter() != 0){
				if (time < this.getMinRestCounter()){
					this.doRest(time);
				}
				else {
					double restingTime = time - this.getMinRestCounter();
					this.doRest(this.getMinRestCounter());
					this.advanceTime(restingTime);
				}
			}
		else if((this.getAutRestCounter() >= 180)) {
			this.rest();
			if(this.getActivityStatus().equals("rest")) {
				this.resetAutRestCounter();
			}
		}
		else if ((status.equals("move")) || ((this.getActivityStatus().equals("default")) && ((!(this.getUnitPosition()).equals(this.getNextPosition()))
				|| (!(this.getUnitPosition()).equals(this.getDestination()))))) {
				if (this.getUnitPosition().equals(this.getDestination())){
					this.setActivityStatus("default");
					this.setCurrentVelocity(new PositionVector(0, 0, 0));
					this.advanceTime(time);
				}
				else {
					if (this.getUnitPosition().equals(this.getNextPosition())) {
						this.moveTo(this.getDestination());
						this.move(time);
						}
					else {
						this.move(time);
					}
				}
			}
		else if (status.equals("work")) {
			this.doWork(time);
			}
		else if (status.equals("rest")) {
			this.doRest(time);
		}
		else if(status.equals("default")) {
			this.increaseAutRestCounter(time);
		}
	}
	
	// algorithm was given, so no documentation
	/**
	 * Gives this unit a given destination and determines which adjacent cube he has to move to start it's journey.
	 * @param destination	The given destination.
	 * @throws	IllegalArgumentException
	 * 			The given destination is not a valid position for this unit in its world.
	 * 			| ! this.getWorld().isValidStandingPosition(destination)
	 * @throws	NullPointerException
	 * 			The destination is not effective.
	 * 			| destination == null
	 */
	public void moveTo(PositionVector destination) throws IllegalArgumentException, NullPointerException {
		if(! this.getWorld().isValidStandingPosition(destination))
			throw new IllegalArgumentException();
		this.setDestination(centrePosition(destination));
		Map<PositionVector, Integer> path = this.getQueue();
		if(! this.getUnitPosition().equals(destination)){
			if(! path.containsKey(destination))
				path.put(destination, 0);
			int i = -1;
			PositionVector cubePosition = new PositionVector(this.getCubePosition()[0], this.getCubePosition()[1],
					this.getCubePosition()[2]);
			ArrayList<PositionVector> subQueue = new ArrayList<PositionVector>();
			if(path.size() == 1)
				this.searchPath(destination, 0, subQueue);	
			
			while((! path.containsKey(cubePosition)) && (i < path.size()-1)){
				i++;
				PositionVector position = subQueue.get(i);
				this.searchPath(position, i, subQueue);
			}
			if(path.containsKey(this.getCubePosition())){
				Set<PositionVector> adjacents = this.getWorld().getAllAdjacentPositions(cubePosition);
				PositionVector best = cubePosition;
				int distance = path.get(best);
				for(PositionVector adjacent : adjacents){
					if((path.get(adjacent) != null) && (path.get(adjacent) < distance)){
						distance = path.get(adjacent);
						best = adjacent;
					}
				}
				this.moveToAdjacent(PositionVector.calcDifferenceVector(cubePosition, best));	
			}
			else
				path.clear();
		}
		path.clear();
	}
	
	@Model
	private void searchPath(PositionVector position, int n, ArrayList<PositionVector> subQueue){
		for(PositionVector adjacent : this.getWorld().getAllAdjacentPositions(position)){
			if((this.getWorld().isValidStandingPosition(adjacent)) && (! this.getQueue().containsKey(adjacent))){
				this.getQueue().put(adjacent, n+1);
				subQueue.add(adjacent);
			}
		}
	}
		
	
	/**
	 * Return the base speed of this unit.
	 * @return	The base speed of this unit calculated with a formula using the strength, agility and weight of this unit.
	 * 			| result == 1.5*(this.getStrength() + this.getAgility())/(200*(this.getWeight()/100.0))
	 */
	public double getBaseSpeed() {
		return (1.5*(this.getStrength() + this.getAgility())/(200*(this.getWeight()/100.0)));
	}
	
	/**
	 * Calculate the walking speed of this unit for a given position in an adjacent cube.
	 * @param targetPosition	The target position, which is in an adjacent cube of this unit's position
	 * @return	If the z difference between the target position and this unit's current position is -1, half of this unit's
	 * 			base speed is returned as walking speed.
	 * 			| if ((targetPosition.getZArgument() - this.getUnitPosition().getZArgument()) == -1) {
	 * 			|		result == 0.5*(this.getBaseSpeed())
	 * @return	If the z difference between the target position and this unit's current position is +1, 1.2 times this unit's
	 * 			base speed is returned as walking speed.
	 * 			| if((targetPosition.getZArgument() - this.getUnitPosition().getZArgument()) == 1) {
	 * 			|			result == 1.2*(this.getBaseSpeed())
	 * @return	Else this unit's base speed is it's walking speed.
	 * 			| return this.getBaseSpeed()
	 * @throws	IllegalArgumentException
	 * 			The given target position is not in an adjacent cube of the unit's position.
	 * 			| !isAdjacent(targetPosition)
	 * @throws	NullPointerException
	 * 			The target position is not effective.
	 * 			| targetPosition == null
	 */
	private double calcWalkingSpeed(PositionVector targetPosition) throws IllegalArgumentException, NullPointerException {
		if (! isValidAdjacent(targetPosition)) {
			throw new IllegalArgumentException();
		}
		double zDifference = targetPosition.getZArgument() - this.getUnitPosition().getZArgument();
		double baseSpeed = this.getBaseSpeed();
		if (zDifference == -1) {
			return 0.5*baseSpeed;
		}
		if(zDifference == 1) {
				return 1.2*(baseSpeed);
		}
		return baseSpeed;
	}
	
	//what if it's outside the game world?
	/**
	 * Check whether a given position is located in an adjacent cube of the position of this unit.
	 * @param position	The position to check.
	 * @return	True if and only if the given position is in an adjacent cube of this unit's position and is a valid unit position.
	 * 			| result == (((Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == 1) 
	 * 			|				|| (Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == 0) 
	 * 			|					|| (Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == -1))
	 *			|		&& (((Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == 1) 
	 *			|				|| (Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == 0) 
	 *			|					|| (Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == -1))
	 *			|			&& (((Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 1) 
	 *			|				|| (Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 0) 
	 *			|					|| (Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 1))
	 *			|				&& (this.isValidUnitPosition(position)))))
	 */
	public boolean isValidAdjacent(PositionVector position) {
		if(! this.isValidUnitPosition(position))
			return false;
		int positionX = (int) position.getXArgument();
		int positionY = (int) position.getYArgument();
		int positionZ = (int) position.getZArgument();
		int unitX = this.getCubePosition()[0];
		int unitY = this.getCubePosition()[1];
		int unitZ = this.getCubePosition()[2];
		int[] difference = {Math.abs(unitX-positionX), Math.abs(unitY-positionY), Math.abs(unitZ-positionZ)};
		if (((difference[0] == -1) || (difference[0] == 0) || (difference[0] == 1))
				&& ((difference[1] == -1) || (difference[1] == 0) || (difference[1] == 1))
				&& ((difference[2] == -1) || (difference[2] == 0) || (difference[2] == 1))){
			return true;
		}
		return false;
	}
	
	/**
	 * Let's this unit move to the center of the adjacent cube of which a position in it is given, if the unit is not already moving
	 * and the given position does not equal the unit's position.
	 * @param position	A combination of an x,y, and z unit PositionVector.
	 * @effect	This unit's activity status is set to 'move'.
	 * 			| this.setActivityStatus("move")
	 * @effect	The current velocity of this unit is set by using the given position.
	 * 			| this.setCurrentVelocity(calcVelocity(this.calcWalkingSpeed(PositionVector.sum(this.getUnitPosition(),position)),
	 * 			|						this.getUnitPosition(),centrePosition(PositionVector.sum(this.getUnitPosition(), position))))
	 * @effect	The next position of this unit is set by using the given position.
	 * 			| this.setNextPosition(centrePosition(PositionVector.sum(this.getUnitPosition(), position)))
	 * @effect	If the given position is the final destination of this unit, than this unit's destination is set to the given position.
	 * 			| this.setDestination(this.getNextPosition());
	 * @throws	IllegalArgumentException
	 * 			The the sum of the given position and the unit's current position is out of bounds.
	 * 			| ! isValidUnitPosition(PositionVector.sum(this.getUnitPosition(),position))
	 * @throws	IllegalArgumentException
	 * 			The sum of the given position and the unit's current position is not in an adjacent cube.
	 * 			| ! isValidAdjacent(PositionVector.sum(this.getUnitPosition(),position))
	 * @throws	IllegalArgumentException
	 * 			The given position refers to a solid cube.
	 * 			| (this.getWorld().isSolidPosition(PositionVector.sum(this.getUnitPosition(),position))
	 * @throws	IllegalArgumentException
	 * 			The adjacent cube is not a valid standing position in this unit's world.
	 * 			| (! this.getWorld().isValidStandingPosition(PositionVector.sum(this.getUnitPosition(), position)))
	 * @throws	IllegalStateException
	 * 			This unit is already moving to an adjacent cube.
	 * 			| (this.getActivityStatus() == "move") &&(! this.getUnitPosition().equals(this.getNextPosition()))
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 * 			| position == null
	 */
	public void moveToAdjacent(PositionVector position) 
			throws IllegalArgumentException, IllegalStateException, NullPointerException {
		if(! position.equals(this.getUnitPosition())){
			if((!isValidUnitPosition(PositionVector.sum(this.getUnitPosition(),position))) || 
								(!isValidAdjacent(PositionVector.sum(this.getUnitPosition(),position)))
								|| (this.getWorld().isSolidPosition(PositionVector.sum(this.getUnitPosition(),position))
										|| (! this.getWorld().isValidStandingPosition(PositionVector.sum(this.getUnitPosition(),
												position))))) {
				throw new IllegalArgumentException();
			}
			if((this.getActivityStatus() == "move") && (! this.getUnitPosition().equals(this.getNextPosition()))) {
				throw new IllegalStateException();
			}
			if(this.getActivityStatus() != "move") {
				this.setActivityStatus("move");
				PositionVector destination = centrePosition(PositionVector.sum(this.getUnitPosition(), position));
				PositionVector velocity = calcVelocity(this.calcWalkingSpeed(PositionVector.sum(this.getUnitPosition(),position)),
						destination, this.getUnitPosition());
				this.setCurrentVelocity(velocity);
				this.setNextPosition(destination);	
			}
			if(this.getUnitPosition().equals(this.getDestination())){
				this.setDestination(this.getNextPosition());
			}
		}
	}
	
	
	/**
	 * Calculates the velocity for a given speed, the target position vector and the start position vector.
	 * @param speed	The speed.
	 * @param targetPosition	The first position.
	 * @param startPosition	The second position.
	 * @return	The velocity vector for the given speed.
	 * 			| result == new PositionVector(speed*calcDifferenceVector(position1, position2).getXArgument()/calcDistance(position1,position2),
	 * 			|					speed*calcDifferenceVector(position1, position2).getYArgument()/calcDistance(position1,position2),
	 * 			|							speed*calcDifferenceVector(position1, position2).getZArgument()/calcDistance(position1,position2))
	 * @throws	NullPointerException
	 * 			The target position and/or the start position is not effective.
	 * 			| (targetPosition == null) || (startPosition == null)
	 */
	private static PositionVector calcVelocity(double speed, PositionVector targetPosition, PositionVector startPosition) 
			throws NullPointerException {
		PositionVector difference = PositionVector.calcDifferenceVector(targetPosition, startPosition);
		double xDifference = difference.getXArgument();
		double yDifference = difference.getYArgument();
		double zDifference = difference.getZArgument();
		double distance = PositionVector.calcDistance(targetPosition, startPosition);
		return (new PositionVector(speed*xDifference/distance, speed*yDifference/distance, speed*zDifference/distance));
	}
	
	/**
	 * Calculates the center position of the cube that contains the given position.
	 * @param position	The given position.
	 * @return	The center position as a vector.
	 * 			| result == new PositionVector(Math.floor(position.getXArgument()) + (cubeLength/2),
	 * 			|								Math.floor(position.getYArgument()) + (cubeLength/2),
	 * 			|									Math.floor(position.getZArgument()) + (cubeLength/2))
	 * @throws	NullPointerException
	 * 			The given position is not an effective.
	 * 			| position == null
	 */
	public static PositionVector centrePosition(PositionVector position) throws NullPointerException{
		double x = Math.floor(position.getXArgument()) + (cubeLength/2);
		double y = Math.floor(position.getYArgument()) + (cubeLength/2);
		double z = Math.floor(position.getZArgument()) + (cubeLength/2);
		return (new PositionVector(x,y,z));
	}
	
	/**
	 * A class variable, defining the length of one cube.
	 */
	private final static double cubeLength = 1.0;
	
	
	/**
	 * Return the activityStatus of this unit.
	 */
	@Basic @Raw
	public String getActivityStatus() {
		return this.activityStatus;
	}
	
	/**
	 * Check whether the given activityStatus is a valid activityStatus for
	 * any unit.
	 *  
	 * @param  activityStatus
	 *         The activityStatus to check.
	 * @return 
	 *       | result == ((activityStatus.equals("move") || (activityStatus.equals("work")) || 
	 *		 |					(activityStatus.equals("rest")) || (activityStatus.equals("attack")) ||
	 *		 |						 (activityStatus.equals("default"))))
	 */
	private static boolean isValidActivityStatus(String activityStatus) {
		return ((activityStatus.equals("move") || (activityStatus.equals("work")) || 
					(activityStatus.equals("rest")) || (activityStatus.equals("attack")) ||
						(activityStatus.equals("default")) || (activityStatus.equals("fall"))));
	}
	
	/**
	 * Set the activityStatus of this unit to the given activityStatus.
	 * 
	 * @param  activityStatus
	 *         The new activityStatus for this unit.
	 * @post   The activityStatus of this new unit is equal to
	 *         the given activityStatus.
	 *       | new.getActivityStatus() == activityStatus
	 * @throws IllegalArgumentException
	 *         The given activityStatus is not a valid activityStatus for any
	 *         unit.
	 *       | ! isValidActivityStatus(getActivityStatus())
	 * @throws	NullPointerException
	 * 			The activity status is not effective.
	 * 			| activityStatus == null
	 */
	@Raw @Model
	private void setActivityStatus(String activityStatus) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidActivityStatus(activityStatus))
			throw new IllegalArgumentException();
		this.activityStatus = activityStatus;
	}
	
	/**
	 * Variable registering the activityStatus of this unit.
	 */
	private String activityStatus;
	
	
	/**
	 * Return the current velocity of this unit, according to it's sprint status.
	 * @return	Return double the normal velocity if the unit is sprinting.
	 * 			| if(this.getSprint() == true)
	 * 			| 	result == PositionVector.multiplyBy(2, this.currentVelocity)
	 * @return	Return the normal velocity is this unit is not sprinting.
	 * 			| result == this.currentVelocity
	 */
	public PositionVector getCurrentVelocity() {
		if(this.getSprint() == true){
			return PositionVector.multiplyBy(2, this.currentVelocity);
		}
		else{
			return this.currentVelocity;
		}
	}
	
	/**
	 * Return the current velocity of this unit.
	 */
	@Basic @Raw
	public PositionVector getCurrentVelocityBasic() {
		return this.currentVelocity;
	}
	
	/**
	 * Check whether the given current velocity is a valid current velocity for
	 * any unit.
	 *  
	 * @param  current velocity
	 *         The current velocity to check.
	 * @return 
	 *       | result == true
	*/
	private static boolean isValidCurrentVelocity(PositionVector currentVelocity) {
		return true;
	}
	
	/**
	 * Set the current velocity of this unit to the given current velocity.
	 * 
	 * @param  currentVelocity
	 *         The new current velocity for this unit.
	 * @post   The current velocity of this new unit is equal to
	 *         the given current velocity.
	 *       | new.getCurrentVelocity() == currentVelocity
	 * @throws IllegalArgumentException
	 *         The given current velocity is not a valid current velocity for any
	 *         unit.
	 *       | ! isValidCurrentVelocity(getCurrentVelocity())
	 * @throws	NullPointerException
	 * 			The given velocity is not effective.
	 * 			| currentVelocity == null
	 */
	@Raw @Model
	private void setCurrentVelocity(PositionVector currentVelocity) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidCurrentVelocity(currentVelocity))
			throw new IllegalArgumentException();
		this.currentVelocity = currentVelocity;
	}
	
	/**
	 * Variable registering the current velocity of this unit.
	 */
	private PositionVector currentVelocity;
	
	
	/**
	 * Return the targeted adjacent position of this unit.
	 */
	@Basic @Raw
	public PositionVector getNextPosition() {
		return this.nextPosition;
	}
	
	/**
	 * Check whether the given targeted adjacent position is a valid targeted adjacent position for
	 * any unit.
	 *  
	 * @param  targeted adjacent position
	 *         The targeted adjacent position to check.
	 * @return 
	 *       | result == (this.isValidAdjacent(nextPosition)) ||  (this.getUnitPosition().equals(nextPosition))
	*/
	private boolean isValidNextPosition(PositionVector nextPosition) {
		return ((this.isValidAdjacent(nextPosition)) ||  (this.getUnitPosition().equals(nextPosition)));
	}
	
	/**
	 * Set the targeted adjacent position of this unit to the given targeted adjacent position.
	 * 
	 * @param  nextPosition
	 *         The new targeted adjacent position for this unit.
	 * @post   The targeted adjacent position of this new unit is equal to
	 *         the given targeted adjacent position.
	 *       | new.getNextPosition() == new PositionVector(nextPosition.getXArgument(), nextPosition.getYArgument(),
	 *       | 																					 nextPosition.getZArgument())
	 * @throws IllegalArgumentException
	 *         The given targeted adjacent position is not a valid targeted adjacent position for any
	 *         unit.
	 *       | (! isValidNextPosition(getNextPosition()))
	 * @throws	NullPointerException
	 * 			The next position is not effective.
	 * 			| nextPosition == null
	 */
	@Raw @Model
	private void setNextPosition(PositionVector nextPosition) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidNextPosition(nextPosition)) {
			throw new IllegalArgumentException();
		}
		this.nextPosition = new PositionVector(nextPosition.getXArgument(), nextPosition.getYArgument(), nextPosition.getZArgument());
	}
	
	/**
	 * Variable registering the targeted adjacent position of this unit.
	 */
	private PositionVector nextPosition;
	
	
	/**
	 * Return the destination of this unit.
	 */
	@Basic @Raw
	public PositionVector getDestination() {
		return this.destination;
	}
	
	/**
	 * Check whether the given destination is a valid destination for
	 * any unit.
	 *  
	 * @param  destination
	 *         The destination to check.
	 * @return 
	 *       | result == isValidUnitPosition(destination)
	 * @throws	NullPointerException
	 * 			The destination is not effective.
	 * 			| destination == null
	*/
	private boolean isValidDestination(PositionVector destination) throws NullPointerException{
		return this.isValidUnitPosition(destination);
	}
	
	/**
	 * Set the destination of this unit to the given destination.
	 * 
	 * @param  destination
	 *         The new destination for this unit.
	 * @post   The destination of this new unit is equal to
	 *         the given destination.
	 *       | new.getDestination() == destination
	 * @throws IllegalArgumentException
	 *         The given destination is not a valid destination for any
	 *         unit.
	 *       | ! isValidDestination(getDestination())
	 * @throws	NullPointerException
	 * 			The destination is not effective.
	 * 			| destination == null
	 */
	@Raw @Model
	private void setDestination(PositionVector destination) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidDestination(destination))
			throw new IllegalArgumentException();
		this.destination = destination;
	}
	
	/**
	 * Variable registering the destination of this unit.
	 */
	private PositionVector destination;


	/**
	 * Return the sprinting status of this unit.
	 */
	@Basic @Raw
	public boolean getSprint() {
		return this.sprintStatus;
	}
	
	
	/**
	 * Set the sprinting status of this unit to the given sprinting status.
	 * 
	 * @param  sprintStatus
	 *         The new sprinting status for this unit.
	 * @post   The sprinting status of this new unit is equal to
	 *         the given sprinting status.
	 *       | new.getSprint() == sprintStatus
	 * @throws	IllegalStateException
	 * 			This unit has no stamina left and is trying to turn on sprint mode.
	 * 			| (this.getCurrentStamina() == 0)
	 */
	@Raw
	public void setSprint(boolean sprintStatus) throws IllegalStateException {
		if((this.getCurrentStamina() == 0) && (sprintStatus == true)) {
			throw new IllegalStateException("0 stamina!");
		}
		this.sprintStatus = sprintStatus;
	}
	
	/**
	 * Variable registering the sprinting status of this unit.
	 */
	private boolean sprintStatus;
	
	/**
	 * Moves this unit for a given amount of time.
	 * @param time	The given amount of time.
	 * @effect	The unit either walks or sprints depending on the sprint status (on or off).
	 * 			| if(this.getSprint() == true) {
	 * 			| 	this.sprint(time) }
	 * 			| else {this.walk(time)}
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 * 			| time < 0
	 */
	@Model
	private void move(double time) throws IllegalArgumentException {
		if (time < 0){
			throw new IllegalArgumentException();
		}
		if(this.getSprint() == true) {
			this.sprint(time);
		} else {
			this.walk(time);
		}
	}
	/**
	 * Lets this unit sprint for a given amount of time, as long as his stamina suffices, else it continues to walk.
	 * @param time	the given amount of time.
	 * @effect	The unit moves at double speed until it's stamina is depleted.
	 * 			| this.miniMove(sprintTime, 2)
	 * @effect	The unit's stamina is decreased for the amount of time that it sprints.
	 * 			| this.decreaseStamina(sprintTime)
	 * @effect	If the unit runs out of stamina, it continues by walking for the resting amount of time and sprint mode is turned off.
	 * 			| this.walk(walkTime)
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 * 			| time < 0
	 */
	private void sprint(double time) throws IllegalArgumentException {
		if (time < 0)
			throw new IllegalArgumentException();
		double sprintTime = this.getSprintTime(time);
		double walkTime = time - sprintTime;
		if (sprintTime > 0) {
			this.miniMove(sprintTime, 2);
			this.decreaseStam(sprintTime);
		}
		if (walkTime > 0){
			this.walk(walkTime);
		}	
	}
	/**
	 * Calculates the amount of time this unit is able to sprint of a given amount of time
	 * @param time	The given amount of time
	 * @return	If this unit can sprint longer than or equal to the given time, the given time is returned.
	 * 			| if(this.getCurrentStamina()*0.1 >= time)
	 * 			| 	result == time
	 * @return	If this unit can't sprint for the whole given time, the amount that he can sprint is returned.
	 * 			| result ==  (this.getCurrentStam()*0.1) %(time + 0.00001)
	 */
	@Raw
	public double getSprintTime(double time){
		double availableSprint = (this.getCurrentStamina()*0.1);
		if(availableSprint >= time){
			return time;
		}
		else {
			return availableSprint;
		}
	}
	
	/**
	 * Decreases the stamina of this unit for a given sprint time.
	 * @param sprintTime	The given sprint time.
	 * @pre 	The sprint time has to be bigger or equals to 0.
	 * 			| sprintTime >= 0
	 * @effect	The stamina in double type of this unit is set to the difference of the old stamina and the lost stamina.
	 * 			| this.setDoubleStamina(this.getDoubleStamina() - sprintTime/0.1)
	 * @effect	If this unit's stamina is depleted, the sprint mode is set off.
	 * 			| this.setSprint(false)
	 */
	private void decreaseStam(double sprintTime) {
		assert(sprintTime >= 0);
		
		double lostStamina = sprintTime/0.1;
		this.setDoubleStamina(this.getDoubleStamina() - lostStamina);
		if (this.getCurrentStamina() == 0) {
			this.setSprint(false);
		}
	}
	
	/**
	 * Makes this unit walk for a given amount of time.
	 * @param walkTime The given amount of time.
	 * @effect	This unit moves for the given time at normal walking speed.
	 * 			| this.miniMove(walkTime, 1)
	 * @throws	IllegalArgumentException
	 * 			The walk time is negative.
	 * 			| walkTime < 0
	 */
	private void walk(double walkTime) throws IllegalArgumentException {
		if(walkTime < 0)
			throw new IllegalArgumentException();
		this.miniMove(walkTime, 1);
	}
	
	/**
	 * Makes this unit move for a given amount of time at a given amount time it's speed.
	 * @param dt	The amount of time.
	 * @param multiplyer How many times the unit's speed.
	 * @effect	This unit's orientation is updated according to the direction it'll move.
	 * 			| this.setOrientation( Math.atan2(this.getCurrentVelocity().getYArgument(),
	 * 			|												 this.getCurrentVelocity().getXArgument()))
	 * @effect	This unit covers a distance by moving at it's speed times multiplier for the given time, 
	 * 			when it doesn't reach it's next position within the given time.
	 * 			| this.setUnitPosition(PositionVector.sum(this.getUnitPosition(), PositionVector.multiplyBy(dt, this.getCurrentVelocity())))
	 * @effect	This unit has reached it's next position if time was sufficient to reach it, it gains 1xp if it wasn't falling,
	 * 			it's activity status, is set to default and it's current velocity to the zero vector. 
	 * 			| this.setUnitPosition(new PositionVector(this.getNextPosition().getXArgument(),this.getNextPosition().getYArgument(),
	 *			|	this.getNextPosition().getZArgument())))
	 *			| if(! this.getActivityStatus().equals("fall"))
	 *			| 	this.gainExp(1)
	 *			| this.setActivityStatus("default")
	 *			| this.setCurrentVelocity(new PositionVector(0, 0, 0))
	 * @effect	In case the unit has time left after reaching it's next position, time advances.
	 * 			| this.advanceTime(restingTime)
	 * @effect	The automatic rest counter is increased with the given amount of time.
	 * 			| this.increaseAutRestCounter(dt)
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 * 			| time < 0
	 */
	private void miniMove(double dt, int multiplier) throws IllegalArgumentException {
		if (dt <0)
			throw new IllegalArgumentException();
		double distance = PositionVector.calcDistance(this.getUnitPosition(), this.getNextPosition());
		double speed = PositionVector.calcDistance((new PositionVector(0,0,0)), this.getCurrentVelocity());
		double travelTime = distance/speed;
		this.setOrientation( Math.atan2(this.getCurrentVelocity().getYArgument(), this.getCurrentVelocity().getXArgument()));
		if (travelTime <= dt) {
			this.setUnitPosition(new PositionVector(this.getNextPosition().getXArgument(),this.getNextPosition().getYArgument(),
					this.getNextPosition().getZArgument()));
			if(! this.getActivityStatus().equals("fall"))
				this.gainExp(1);
			this.setActivityStatus("default");
			this.setCurrentVelocity(new PositionVector(0, 0, 0));
			double restingTime = dt-travelTime;
			if (restingTime > 0) {
				this.advanceTime(restingTime);
			}
		}
		else {
			this.setUnitPosition(PositionVector.sum(this.getUnitPosition(), PositionVector.multiplyBy(dt, this.getCurrentVelocity())));
		}
		this.increaseAutRestCounter(dt);
	}
	
	/**
	 * Return the double type stamina of this unit.
	 */
	@Basic @Raw
	private double getDoubleStamina() {
		return this.doubleStamina;
	}
	
	/**
	 * Check whether the given decimal stamina is a valid decimal stamina for
	 * any unit.
	 * @param  decimal stamina
	 *         The decimal stamina to check.
	 * @return 
	 *       | result == (doubleStamina > 0) && (doubleStamina <= this.getMaxStamina())
	*/
	@Raw
	private boolean isValidDoubleStamina(double doubleStamina) {
		return ((doubleStamina > 0) && (doubleStamina <= this.getMaxStamina()));
	}
	
	/**
	 * Set the double type stamina and current stamina of this unit to respectively the given double type stamina 
	 * and it's integer form (rounded up).
	 * 
	 * @param  doubleStamina
	 *         The new decimal stamina for this unit.
	 * @pre    The given decimal stamina must be a valid decimal stamina for any
	 *         unit.
	 *       | isValidDoubleStamina(doubleStamina)
	 * @effect   The double type stamina of this unit is equal to the given
	 *         	 double type stamina and the stamina is set to the rounded up integer version of the double type stamina.
	 *       | new.getDoubleStamina() == doubleStamina
	 *       | this.setCurrentStamina((int) (Math.ceil(doubleStamina)))
	 */
	@Model @Raw
	private void setDoubleStamina(double doubleStamina) {
		assert isValidDoubleStamina(doubleStamina);
		this.doubleStamina = doubleStamina;
		this.setCurrentStamina((int) (Math.ceil(doubleStamina)));
	}
	
	/**
	 * Variable registering the decimal stamina of this unit.
	 */
	private double doubleStamina;
	
	
	/**
	 * Return the work time of this unit.
	 */
	@Basic @Raw
	private double getWorkTime() {
		return this.workTime;
	}
	
	/**
	 * Check whether the given work time is a valid work time for
	 * any unit.
	 *  
	 * @param  work time
	 *         The work time to check.
	 * @return 
	 *       | result == (workTime >= 0) && (workTime <= (500/this.getStrength())
	*/
	private boolean isValidWorkTime(double workTime) {
		return ((workTime >= 0) && (workTime <= (500/this.getStrength())));
	}
	
	/**
	 * Set the work time of this unit to the given work time.
	 * 
	 * @param  workTime
	 *         The new work time for this unit.
	 * @post   The work time of this new unit is equal to
	 *         the given work time.
	 *       | new.getWorkTime() == workTime
	 * @throws IllegalArgumentException
	 *         The given work time is not a valid work time for any
	 *         unit.
	 *       | ! isValidWorkTime(getWorkTime())
	 */
	@Raw
	private void setWorkTime(double workTime) 
			throws IllegalArgumentException {
		if (! isValidWorkTime(workTime))
			throw new IllegalArgumentException();
		this.workTime = workTime;
	}
	
	/**
	 * Reset the work time of this unit to it's maximum.
	 * @effect	The work time of this unit is set to it's maximum.
	 * 			| this.setWorkTime(500/this.getStrength())
	 */
	@Model
	private void resetWorkTime() {
		this.setWorkTime(500/this.getStrength());
	}
	
	/**
	 * Variable registering the work time of this unit.
	 */
	private double workTime;
	
	/**
	 * Command this unit to work.
	 * @param	targetPosition	The given target position.
	 * @effect	The activity status of this unit is set to work mode.
	 * 			| this.setActivityStatus("work")
	 * @effect	This unit's work position is set to the given target position.
	 * 			| this.setWorkPosition(centrePosition(targetPosition))
	 * @effect	This unit's work time is reset.
	 * 			| this.resetWorkTime()
	 * @effect	This unit's orientation is set to face the cube of the target position.
	 * 			| this.setOrientation( Math.atan2(differenceVector.getYArgument(), differenceVector.getXArgument()))
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid adjacent position of this unit.
	 * 			| (! this.isValidAdjacent(targetPosition))
	 * @throws	IllegalStateException
	 * 			This unit is attacking or falling.
	 * 			| this.getActivityStaus().equals("attack") || (this.getActivityStatus().equals("fall"))
	 */
	@Raw
	public void work(PositionVector targetPosition) throws IllegalArgumentException, IllegalStateException {
		if(! this.isValidAdjacent(targetPosition))
			throw new IllegalArgumentException("Target cube is not an adjacent!");
		if((this.getActivityStatus().equals("attack")) || (this.getActivityStatus().equals("fall")))	
				throw new IllegalStateException();
		this.setActivityStatus("work");
		this.setWorkPosition(centrePosition(targetPosition));
		this.resetWorkTime();
		PositionVector differenceVector = PositionVector.calcDifferenceVector(this.getUnitPosition(), targetPosition);
		this.setOrientation( Math.atan2(differenceVector.getYArgument(), differenceVector.getXArgument()));
	}
	
	/**
	 * Makes this unit work for a given amount of time.
	 * @param time	The given time.
	 * @effect	This unit's work time is reduced by the given amount of time, if the amount of time is smaller than or equal to 
	 * 			the work time.
	 * 			| if (time < this.getWorkTime()) {
	 * 			| 	this.setWorkTime(this.getWorkTime - time) }
	 * @effect	This unit's work time is set to 0 and it's activity status to default, if the given time equals the unit's work time,
	 * 			the effect of the work performed takes effect and this unit's work position is set to null.
	 * 			| if (time == this.getWorkTime()) {
	 * 			| 	this.setWorkTime(0)
	 * 			| 	this.setActivityStatus("default")
	 * 			| 	this.workEffect()
	 * 			| 	this.setWorkPosition(null)
	 * @effect	This unit's work time is depleted and time advances if there's time left, activity status is set to default,
	 * 			the effect of the work performed takes effect and this unit's work position is set to null.
	 * 			| if (this.getWorkTime() < time) {
	 * 			| 	double restingTime = time - this.getWorkTime()
	 * 			| 	this.setWorkTime(0)
	 * 			| 	this.setActivityStatus("default")
	 * 			| 	this.workEffect()
	 * 			| 	this.setWorkPosition(null)
	 * 			| 	this.advanceTime(restingTime)}
	 * @effect	The automatic rest counter is increased with the given amount of time.
	 * 			| this.increaseAutRestCounter(time)
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 * 			| time < 0
	 */
	@Model
	private void doWork(double time) throws IllegalArgumentException {
		if (time < 0)
			throw new IllegalArgumentException();
		if (this.getWorkTime() < time) {
			 double restingTime = time - this.getWorkTime();
			 this.setWorkTime(0);
			 this.workEffect();
			 this.setWorkPosition(null);
			 this.setActivityStatus("default");
			 this.advanceTime(restingTime);
		}
		if (time == this.getWorkTime()) {
			 this.setWorkTime(0);
			 this.workEffect();
			 this.setWorkPosition(null);
			 this.setActivityStatus("default");
		}
		if (time < this.getWorkTime()) {
			 this.setWorkTime(this.getWorkTime() - time);
		}
		this.increaseAutRestCounter(time);
	}
	
	/**
	 * Invoke the effects caused by a labor of this unit.
	 * @effect	If this unit's working position is not solid and its inventory is not empty, this unit empties its inventory.
	 * 			| this.emptyInventory();
	 * @effect	If this unit's working position refers to a workshop cube that contains a log and a boulder, this unit's equipment is 
	 * 			improved.
	 * 			| this.improveEquipment()
	 * @effect	If this unit's working position contains a boulder and this unit's inventory is not full, it picks up the boulder.
	 * 			| this.pickUpBoulder(this.getWorkPosition())
	 * @effect	If this unit's working position contains a log and this unit's inventory is not full, it picks up the log.
	 * 			| this.pickUpLog(this.getWorkPosition())
	 * @effect	If this unit's working position refers to a wood cube or a rock cube, the cube collapses.
	 * 			| this.getWorld().collapse(this.getWorkPosition())
	 * @throws	IllegalStateException
	 * 			This unit's work time is 0 or it's activity status is not 'work'.
	 * 			| (this.getWorkTime() != 0) || (! this.getActivityStatus().equals("work"))
	 */
	private void workEffect() throws IllegalStateException {
		if((this.getWorkTime() != 0) || (! this.getActivityStatus().equals("work")))
			throw new IllegalStateException();
		
		if((! this.getWorld().isSolidPosition(this.getWorkPosition())) && (! this.getInventory().isEmpty()))
			this.emptyInventory();
		else if((this.getWorld().isWorkshop(this.getWorkPosition())) && (this.getWorld().containsLog(this.getWorkPosition()))
				&& (this.getWorld().containsBoulder(this.getWorkPosition())))
			this.improveEquipment();
		else if((this.getWorld().containsBoulder(this.getWorkPosition())) && (this.getInventory().size() < inventoryCapacity))
			this.pickUpMaterial(this.getWorld().getABoulder(this.getWorkPosition()));
		else if((this.getWorld().containsLog(this.getWorkPosition())) && (this.getInventory().size() < inventoryCapacity))
			this.pickUpMaterial(this.getWorld().getALog(this.getWorkPosition()));
		else if((this.getWorld().isWood(this.getWorkPosition())) || (this.getWorld().isRock(this.getWorkPosition())))
			this.getWorld().collapse(this.getWorkPosition());
	}
	
	/**
	 * Improve this unit's equipment.
	 * @effect	The boulder and log at this unit's work position, which is a workshop, are consumed. This unit's weight is increaded
	 * 			by 5 and it's toughness by 10.
	 * 			| this.getWorld().removeMaterial(this.getWorld().getABoulder(this.getWorkPosition()))
	 * 			| this.getWorld().removeMaterial(this.getWorld().getALog(this.getWorkPosition()))
	 * 			| this.setWeight(this.getWeight() + 5)
	 * 			| this.setToughness(this.getToughness() + 10)
	 * @throws	IllegalStateException
	 * 			This unit's work position does not refer to a workshop and does not contain a log and a boulder.
	 * 			(!this.getWorld().isWorkshop(this.getWorkPosition())) || (! this.getWorld().containsBoulder(this.getWorkPosition()))
	 *			| 	|| (this.getWorld().containsLog(this.getWorkPosition()))
	 */
	private void improveEquipment() throws IllegalStateException{
		if((!this.getWorld().isWorkshop(this.getWorkPosition())) || (! this.getWorld().containsBoulder(this.getWorkPosition()))
				|| (this.getWorld().containsLog(this.getWorkPosition())))
			throw new IllegalStateException();
		Boulder boulder = this.getWorld().getABoulder(this.getWorkPosition());
		this.getWorld().removeMaterial(boulder);
		Log log = this.getWorld().getALog(this.getWorkPosition());
		this.getWorld().removeMaterial(log);
		this.setWeight(this.getWeight() + 5);
		this.setToughness(this.getToughness() + 10);
	}
	
	
	/**
	 * Make this unit pick up a given material.
	 * @param material	The given material.
	 * @effect	The given material is added to this unit's inventory, the given material is removed from this unit's world.
	 * 			| this.addMaterialToInventory(material)
	 * 			| this.getWorld().removeMaterial(material)
	 * @throws NullPointerException
	 * 			The given material is not effective.
	 * 			| material == null
	 * @throws IllegalArgumentException
	 * 			This unit's activity status is not 'work' or this unit's world does have the given material or
	 * 			the given material is not in the cube to which this unit's work position refers or this unit's inventory is full.
	 * 			| (! this.getActivityStatus().equals("work")) 
	 * 			| 	|| (! this.getWorld().hasAsMaterial(material))
	 * 			| 		|| (! material.getCubePositionVector().equals(new PositionVector((int)this.getWorkPosition().getXArgument(), 
	 * 			| 						(int)this.getWorkPosition().getYArgument(), (int)this.getWorkPosition().getZArgument())))
	 * 			| 			|| (this.getInventory().size() == inventoryCapacity))
	 */
	public void pickUpMaterial(Material material) throws NullPointerException, IllegalArgumentException {
		if(material == null)
			throw new NullPointerException();
		if((! this.getActivityStatus().equals("work")) 
				|| (! this.getWorld().hasAsMaterial(material))
					|| (! material.getCubePositionVector().equals(new PositionVector((int)this.getWorkPosition().getXArgument(), 
						(int)this.getWorkPosition().getYArgument(), (int)this.getWorkPosition().getZArgument())))
						|| (this.getInventory().size() == inventoryCapacity))
			throw new IllegalArgumentException();
		this.addMaterialToInventory(material);
		this.getWorld().removeMaterial(material);
		
	}
	
	/**
	 * Make this unit attack another unit that's occupying this unit's cube or an adjacent cube when it's not already fighting and does
	 * nothing when already fighting.
	 * @param target	The target unit.
	 * @effect	This unit's minimum rest time is set to zero.
	 * 			| this.setMinRestCounter(0)
	 * @effect	This unit's activity status is set to "attack".
	 * 			| this.setActivityStatus("attack")
	 * @effect	This unit's attack time is reset if it's zero
	 * 			| this.resetAttackTime();
	 * @effect	This unit's orientation is updated to face it's target.
	 * 			| this.setOrientation(Math.atan2((target.getUnitPosition().getYArgument() - this.getUnitPosition().getYArgument()),
	 * 			| 	target.getUnitPosition().getXArgument() - this.getUnitPosition().getXArgument()))
	 * @throws	IllegalStateException
	 * 			This unit is already attacking or is falling or the target is falling.
	 * 			| (this.getActivityStatus().equals("attack")) || (this.getActivityStatus().equals("fall")))
	 * 			| 	|| (target.getActivityStatus().equals("fall"))
	 * @throws	IllegalArgumentException
	 * 			The target is not in an adjacent cube of this unit or is an ally.
	 * 			| this.isValidAdjacent(target.getUnitPosition()) || (target.getFaction().equals(this.getFaction()))
	 * @throws	NullPointerException
	 * 			The target is not effective.
	 * 			| target == null
	 */
	public void attack(Unit target) throws IllegalStateException, IllegalArgumentException, NullPointerException {
		if ((this.getActivityStatus().equals("attack")) || (this.getActivityStatus().equals("fall"))
				|| (target.getActivityStatus().equals("fall"))) {
			throw new IllegalStateException();
		}
		if ((! this.isValidAdjacent(target.getUnitPosition())) || (target.getFaction().equals(this.getFaction()))) {
			throw new IllegalArgumentException();
		}
		this.setMinRestCounter(0);
		this.setActivityStatus("attack");
		this.resetAttackTime();
		this.setOrientation(Math.atan2((target.getUnitPosition().getYArgument() - this.getUnitPosition().getYArgument()),
		target.getUnitPosition().getXArgument() - this.getUnitPosition().getXArgument()));
	}
	
	/**
	 * Makes this unit conduct it's attack for a given amount of time and when the attack is over advance time.
	 * @param time	The given amount of time.
	 * @effect	If this unit's attack time is less then the given time, activity status is set default, attack time 0
	 * 			and time advances for the resting time.
	 * 			| if (this.getAttackTime() < time) {
	 * 			| 	double restingTime = time - this.getAttackTime()
	 * 			| 	this.setAttackTime(0)
	 * 			| 	this.setActivityStatus("default")
	 * 			| 	this.advanceTime(restingTime) }
	 * @effect	If this unit's attack time equals the given time, attack time is set 0and activity status to default.
	 * 			| if (this.getAttackTime() == time) {
	 * 			| 	this.setAttackTime(0)
	 * 			| 	this.setActivityStatus("default")}
	 * @effect	When this unit's attack time is larger then the given time, the given time is subtracted from it's attack time.
	 * 			| if (this.getAttackTime > time) {
	 * 			| 	this.setAttackTime(this.getAttackTime() - time)}
	 * @effect	The automatic rest counter is increased with the given amount of time.
	 * 			| this.increaseAutRestCounter(time)
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 * 			| time < 0
	 */
	@Model
	private void doAttack(double time) throws IllegalArgumentException {
		if (time < 0)
			throw new IllegalArgumentException();
		if (this.getAttackTime() < time) {
			 double restingTime = time - this.getAttackTime();
			 this.setAttackTime(0);
			 this.setActivityStatus("default");
			 this.advanceTime(restingTime);
			 }
		else if (this.getAttackTime() == time) {
			 this.setAttackTime(0);
			 this.setActivityStatus("default");
		}
		else {
			this.setAttackTime(this.getAttackTime() - time);
		}
		this.increaseAutRestCounter(time);
	}
	
	/**
	 * Return the attack time of this unit.
	 */
	@Basic @Raw
	private double getAttackTime() {
		return this.attackTime;
	}
	
	/**
	 * Check whether the given attack time is a valid attack time for
	 * any unit.
	 *  
	 * @param  attack time
	 *         The attack time to check.
	 * @return 
	 *       | result == (attackTime >= 0) && (attackTime <= 1)
	*/
	private static boolean isValidAttackTime(double attackTime) {
		return ((attackTime >= 0) && (attackTime <= 1));
	}
	
	/**
	 * Set the attack time of this unit to the given attack time.
	 * @param  attackTime
	 *         The new attack time for this unit.
	 * @post   The attack time of this new unit is equal to
	 *         the given attack time.
	 *       | new.getAttackTime() == attackTime
	 * @throws IllegalArgumentException
	 *         The given attack time is not a valid attack time for any
	 *         unit.
	 *       | ! isValidAttackTime(getAttackTime())
	 */
	@Raw @Model
	private void setAttackTime(double attackTime) 
			throws IllegalArgumentException {
		if (! isValidAttackTime(attackTime))
			throw new IllegalArgumentException();
		this.attackTime = attackTime;
	}
	
	/**
	 * Set this unit's attack time to 1 second.
	 * @effect	This unit's attack time is set to 1.
	 * 			| this.setAttackTime(1)
	 */
	@Raw @Model
	private void resetAttackTime() {
		this.setAttackTime(1);
	}
	
	/**
	 * Variable registering the attack time of this unit.
	 */
	private double attackTime;
	
	/**
	 * Makes this unit react to an attack of an enemy by either dodging the attack, blocking it or simply taking damage.
	 * @param enemy	The enemy attacking.
	 * @effect	This unit's minimum rest time is set to zero.
	 * 			| this.setMinRestCounter(0)
	 * @effect	This unit's activity status is set to default.
	 * 			| this.setActivityStatus("default")
	 * @effect	This unit's orientation is set to face it's enemy.
	 * 			| this.setOrientation(Math.atan2((enemy.getUnitPosition().getYArgument() - this.getUnitPosition().getYArgument()),
	 *			|	enemy.getUnitPosition().getXArgument() - this.getUnitPosition().getXArgument()))
	 * @effect	This unit's next position and final destination are set to it's current position, it's velocity to zero.
	 * 			| this.setNextPosition(this.getUnitPosition())
	 * 			| this.setDestination(this.getUnitPosition())
	 * 			| this.setCurrentVelocity(new PositionVector(0,0,0))
	 * @effect	If by chance this unit is able to dodge, it moves to an adjacent cube.
	 * 			| if(this.dodge() == true) {
	 * 			| 	this.moveToAdjacent(this.randomAdjacent())}
	 * @effect	If dodging failed, this unit tries to block the incoming attack by chance, resulting in no damage if successful,
	 * 			else this unit's hitpoints are decreased by the enemy's strength level.
	 * 			| if(this.block() == false) {
	 * 			| 	if(enemy.getStrength() > this.getCurrentHP()) {
	 *			|		this.decreaseHP(this.getCurrentHP())}
	 *			| else{ this.decreaseHP(enemy.getStrength())}
	 *@throws	NullPointerException
	 *			The enemy is not effective.
	 *			| enemy == null
	 */
	public void defend(Unit enemy) throws NullPointerException {
		this.setMinRestCounter(0);
		this.setActivityStatus("default");
		this.setOrientation(Math.atan2((enemy.getUnitPosition().getYArgument() - this.getUnitPosition().getYArgument()),
				enemy.getUnitPosition().getXArgument() - this.getUnitPosition().getXArgument()));
		this.setNextPosition(this.getUnitPosition());
		this.setDestination(this.getUnitPosition());
		this.setCurrentVelocity(new PositionVector(0,0,0));
		if(this.dodge(enemy) == true) {
			this.moveToAdjacent(this.randomAdjacent());
		}
		else if(this.block(enemy) == false) {
			if(enemy.getStrength() > this.getCurrentHP()) {
				this.decreaseHP(this.getCurrentHP());
			}
			else {
				this.decreaseHP(enemy.getStrength());
			}
		}
	}
	
	/**
	 * Return if by chance this unit is able to dodge an attack of the given enemy at this moment.
	 * @param enemy	The given enemy.
	 * @return	True if and only if this unit made it by chance (according to given formula).
	 * 		| result == (new Random()).nextDouble() <= (0.20*(this.getAgility()/enemy.getAgility()))
	 * @throws	NullPointerException
	 * 			The enemy is not effective.
	 * 			| enemy == null
	 */
	@Model
	private boolean dodge(Unit enemy) throws NullPointerException {
		double chance = 0.20*(this.getAgility()/enemy.getAgility());
		Random generator = new Random();
		double random = generator.nextDouble();
		if (random <= chance) {
			return true;
		}
		else
			return false;
	}
	
	//let produce also diagonal adjacent positions
	/**
	 * Return a random unit vector, giving the direction to a random adjacent cube of this unit.
	 * @return	A random unit vector that is a valid adjacent position.
	 * 			| result == new PositionVector(Random.nextInt(3)-1, Random.nextInt(3)-1, Random.nextInt(3)-1)
	 */
	private PositionVector randomAdjacent() {
//		Random generator = new Random();
//		PositionVector adjacent = new PositionVector(generator.nextInt(3)-1, generator.nextInt(3)-1, generator.nextInt(3)-1);
//		while (! this.isValidAdjacent(adjacent))
//			adjacent = new PositionVector(generator.nextInt(3)-1, generator.nextInt(3)-1, generator.nextInt(3)-1);
//		return adjacent;
		PositionVector zero = new PositionVector(0, 0, 0);
		PositionVector xPlus = new PositionVector(1, 0, 0);
		PositionVector yPlus = new PositionVector(0, 1, 0);
		PositionVector zPlus = new PositionVector(0, 0, 1);
		PositionVector xMin = new PositionVector(-1, 0, 0);
		PositionVector yMin = new PositionVector(0, -1, 0);
		PositionVector zMin = new PositionVector(0, 0, -1);
		PositionVector[] possibilities = {zero,xPlus,yPlus,zPlus,xMin,yMin,zMin};
		Random generator = new Random();
		int random = generator.nextInt(possibilities.length - 1);
		if (this.isValidAdjacent(PositionVector.sum(this.getUnitPosition(), possibilities[random]))) {
			return possibilities[random];
		}
		else {
			return randomAdjacent();
		}
	}
	
	/**
	 * Return if this unit is able to block an attack of the given enemy at this moment.
	 * @param enemy	The given enemy.
	 * @return	True if and only if this unit made it by chance (according to given formula).
	 * 			| result == (new Random()).nextDouble() <= 0.25*((this.getStrength() + this.getAgility())/(enemy.getStrength() + enemy.getAgility()))
	 * @throws	NullPointerException
	 * 			The enemy is not effective.
	 * 			| enemy == null
	 */
	@Model
	private boolean block(Unit enemy) throws NullPointerException {
		double chance = 0.25*((this.getStrength() + this.getAgility())/(enemy.getStrength() + enemy.getAgility()));
		Random generator = new Random();
		double random = generator.nextDouble();
		return (random <= chance);
	}
	
	/**
	 * Decrease this unit's hitpoints with a given amount until it has no hitpoints left.
	 * @param amount	The given amount of hitpoints.
	 * @pre 	The given amount has to be greater than or equal to 0.
	 * 			| amount >= 0
	 * @effect	This unit's hitpoints are set to the difference of it's old hitpoints and the given amount.
	 * 			| this.setDoubleHP(this.getDoubleHP() - amount)
	 * @effect	This unit terminates if the difference between it's current hitpoints and the amount is smaller than or equal to
	 * 			zero.
	 * 			| this.terminate() 
	 */
	@Model
	private void decreaseHP(int amount) {
		assert (amount >= 0);
		if((this.getCurrentHP() - amount) <= 0)
			this.terminate();
		else
			this.setDoubleHP(this.getDoubleHP() - amount);
	}
	
	/**
	 * Command this unit to rest if it's not already fully rested or attacking.
	 * @effect	This unit's activity status is set to 'rest'.
	 * 			| this.setActivityStatus("rest")
	 * @effect	This unit's minimum rest counter is reset.
	 * 			| this.resetMinRestCounter()
	 * @effect	The automatic rest counter is reset.
	 * 			| this.resetAutRestCounter()
	 * @throws	IllegalStateException
	 * 			The unit is in combat or is falling to an adjacent cube.
	 * 			| (this.getActivityStatus() == "attack") || (this.getActivityStatus().equals("fall"))
	 */
	@Raw
	public void rest() throws IllegalStateException{
		if((this.getActivityStatus().equals("attack")) || (this.getActivityStatus().equals("fall")))
			throw new IllegalStateException();
			
		if((this.getDoubleHP() != this.getMaxHP()) || (this.getDoubleStamina() != this.getMaxStamina())){
			this.setActivityStatus("rest");
			this.resetMinRestCounter();
			this.resetAutRestCounter();
		}
	}
	/**
	 * Makes this unit rest for a given amount of time, resulting in recovering hitpoints and then stamina when all hitpoints 
	 * are recovered.
	 * @param time	The given amount of time.
	 * 			| this.resetRecoveryTime();
	 * @effect	The minimum rest counter of this unit is decreased by the given amount of time.
	 * 			| this.decreaseMinRestCounter(time)
	 * @effect	Hitpoints are recovered.
	 * 			| this.recoverHP(time)
	 * @effect	If after recovering hitpoints for the given time, there's time left, stamina is recovered.
	 * 			| this.recoverStamina(this.recoverHP(time))
	 * @effect	If there's still time left after recovering stamina, this unit's activity status is set to default and time advances
	 * 			for the resting time.
	 * 			| this.setActivityStatus("default")
	 * 			| this.advanceTime(this.recoverStamina(this.recoverHP(time)))
	 * @throws	IllegalStateException
	 * 			This unit's activity status is not 'rest'.
	 * 			| this.getActivityStatus() != "rest"
	 * @throws	IllegalArgumentException
	 *			The given amount of time is negative.
	 *			| time < 0
	 * @effect	The automatic rest counter is increased with the given amount of time.
	 * 			| this.increaseAutRestCounter(time)
	 */
	@Model
	private void doRest(double time) throws IllegalArgumentException, IllegalStateException {
		if(this.getActivityStatus() != "rest") {
			throw new IllegalStateException();
		}
		if(time < 0){
			throw new IllegalArgumentException();
		}
		double leftoverTime1 = this.recoverHP(time);
		if(this.getMinRestCounter() != 0){
			this.decreaseMinRestCounter(time-leftoverTime1);
		}
		if(leftoverTime1 > 0){
			double leftoverTime2 = this.recoverStamina(leftoverTime1);
			if(this.getMinRestCounter() != 0){
			this.decreaseMinRestCounter(leftoverTime1-leftoverTime2);
			}
			if(leftoverTime2 > 0){
				this.setActivityStatus("default");
				this.advanceTime(leftoverTime2);
			}
		}
	}
	
	/**
	 * Regenerate this unit's hitpoints for a given amount of time and give back the unused time.
	 * @param time	The given amount of time.
	 * @pre 	The given time needs to be bigger than or equal to 0.
	 * 			| time >= 0
	 * @return	If this unit already has all of it's hitpoints, the given time is returned.
	 * 			| if(this.getMaxHP() == this.getDoubleHP())
	 * 			|		result == time
	 * @return	If this unit needs less time than the given time to fully recover it's hitpoints, the resting amount of time
	 * 			is returned.
	 * 			If this unit needs all the given time to (partially) recover hitpoints, 0 is returned.
	 *			| if((this.getMaxHP() - this.getCurrentDoubleHP())/double recoveryRate = (this.getToughness()/200.0)/0.2 <= time)
	 *			|	this.setCurrentDoubleHP(this.getMaxHP())
	 *			|	result == (time - (this.getMaxHP() - this.getCurrentDoubleHP())/double recoveryRate = (this.getToughness()/200.0)/0.2)
	 *			| this.setCurrentDoubleHP(this.getDoubleHP()+time*double recoveryRate = (this.getToughness()/200.0)/0.2)
	 *			| result == 0
	 *@throws	IllegalArgumentException
	 *			The given amount of time is negative.
	 *			| time < 0
	 */
	@Raw
	private double recoverHP(double time)throws IllegalArgumentException {
		assert(time >= 0);
		
		if(this.getMaxHP() == this.getDoubleHP()) {
			return time;
			}
		double recoveryRate = (this.getToughness()/200.0)/0.2;
		double neededTime = (this.getMaxHP() - this.getDoubleHP())/recoveryRate;
		if(neededTime <= time) {
			this.setDoubleHP(this.getMaxHP()); 
			return (time - neededTime);
		}
		this.setDoubleHP(this.getDoubleHP()+time*recoveryRate);
		return 0;
	}
	
	/**
	 * Return the double type hitpoints of this unit.
	 */
	@Basic @Raw
	private double getDoubleHP() {
		return this.doubleHP;
	}
	
	/**
	 * Check whether the given double type hitpoints is a valid double type hitpoints for
	 * any unit.
	 *  
	 * @param  double type hitpoints
	 *         The double type hitpoints to check.
	 * @return 
	 *       | result == (doubleHP >= 0) && (doubleHP <= this.getMaxHP())
	*/
	@Raw
	private boolean isValidDoubleHP(double doubleHP) {
		return ((doubleHP >= 0) && (doubleHP <= this.getMaxHP()));
	}
	
	/**
	 * Set the double type hitpoints and also the current hitpoints of this unit respectively to the given double type hitpoints and
	 * the integer amount (rounded up) of the given double type hitpoints.
	 * 
	 * @param  doubleHP
	 *         The new double type hitpoints for this unit.
	 * @pre    The given double type hitpoints must be a valid double type hitpoints for any
	 *         unit.
	 *       | isValidDoubleHP(doubleHP)
	 * @effect   The double type hitpoints and current hitpoints of this unit are respectively equal to the given
	 *         double type hitpoints and it's integer form rounded up.
	 *       | new.getDoubleHP() == doubleHP
	 *       | this.setDoubleHP((int) (Math.ceil(doubleHP)))
	 */
	@Model @Raw
	private void setDoubleHP(double doubleHP) {
		assert isValidDoubleHP(doubleHP);
		this.doubleHP = doubleHP;
		this.setCurrentHP((int) (Math.ceil(doubleHP)));
	}
	
	/**
	 * Variable registering the double type hitpoints of this unit.
	 */
	private double doubleHP;
	
	
	/**
	 * Regenerate this unit's stamina for a given amount of time and give back the unused time.
	 * @param time	The given amount of time.
	 * @pre 	The given time needs to be bigger than or equal to 0.
	 * 			| time >= 0
	 * @return	If this unit already has all of it's stamina, the given time is returned.
	 * 			| if(this.getMaxStamina() == this.getDoubleStamina())
	 * 			|		result == time
	 * @return	If this unit needs less time than the given time to fully recover it's stamina, the resting amount of time
	 * 			is returned.
	 * 			If this unit needs all the given time to (partially) recover stamina, 0 is returned.
	 *			| if((this.getMaxStamina() - this.getCurrentDoubleStamina())/(this.getToughness()/100)/0.2 <= time)
	 *			|	this.setCurrentDoubleStamina(this.getMaxStamina())
	 *			|	result == (time - (this.getMaxStamina() - this.getCurrentDoubleStamina())/(this.getToughness()/100)/0.2)
	 *			| this.setCurrentDoubleStamina(this.getDoubleStamina()+time*(this.getToughness()/100)/0.2)
	 *			| result == 0
	 *@throws	IllegalArgumentException
	 *			The given amount of time is negative.
	 *			| time < 0
	 */
	@Raw
	private double recoverStamina(double time)throws IllegalArgumentException {
		assert(time >= 0);
		
		if(this.getMaxStamina() == this.getDoubleStamina()) {
			return time;
			}
		double recoveryRate = (this.getToughness()/100.0)/0.2;
		double neededTime = (this.getMaxStamina() - this.getDoubleStamina())/recoveryRate;
		if(neededTime <= time) {
			this.setDoubleStamina(this.getMaxStamina()); 
			return (time - neededTime);
		}
		this.setDoubleStamina(this.getDoubleStamina()+time*recoveryRate);
		return 0;
	}
	
	
	/**
	 * Return the minimum rest counter of this unit.
	 */
	@Basic @Raw
	public double getMinRestCounter() {
		return this.minRestCounter;
	}
	
	/**
	 * Check whether the given minimum rest counter is a valid minimum rest counter for
	 * any unit.
	 *  
	 * @param  minimum rest counter
	 *         The minimum rest counter to check.
	 * @return 
	 *       | result == minRestCounter <= 0.2/(this.getToughness()/200)) || (minRestCounter >= 0)
	*/
	@Raw
	private boolean isValidMinRestCounter(double minRestCounter) {
		return ((minRestCounter <= 0.2/(this.getToughness()/200.0)) || (minRestCounter >= 0));
	}
	
	/**
	 * Set the minimum rest counter of this unit to the given minimum rest counter.
	 * 
	 * @param  minRestCounter
	 *         The new minimum rest counter for this unit.
	 * @post   The minimum rest counter of this new unit is equal to
	 *         the given minimum rest counter.
	 *       | new.getMinRestCounter() == minRestCounter
	 * @throws IllegalArgumentException
	 *         The given minimum rest counter is not a valid minimum rest counter for any
	 *         unit.
	 *       | ! isValidMinRestCounter(getMinRestCounter())
	 */
	@Model @Raw
	private void setMinRestCounter(double minRestCounter) 
			throws IllegalArgumentException {
		if (! isValidMinRestCounter(minRestCounter))
			throw new IllegalArgumentException();
		this.minRestCounter = minRestCounter;
	}
	
	/**
	 * Reset the minimum rest counter.
	 * @post	The minimum rest counter is set to the amount of time it takes this unit to recover 1 hitpoint.
	 * 			| this.setMinRestCounter(0.2/(this.getToughness()/200.0))
	 */
	@Model @Raw
	private void resetMinRestCounter() {
		this.setMinRestCounter(0.2/(this.getToughness()/200.0));
	}
	
	/**
	 * Decreases the minimum rest counter of this unit by the given amount of time, until the counter reaches 0.
	 * @param time	The given amount of time.
	 * @effect	The minimum rest counter is decreased by the given amount of time.
	 * 			| this.setMinRestCounter(this.getMinRestCounter() - time)
	 * @throws IllegalArgumentException
	 * 			The given time is negative.
	 * 			| (time < 0)
	 * @throws IllegalArgumentException
	 * 			The given time is greater than the minimum rest counter.
	 * 			| ((this.getMinRestCounter() - time) < 0)
	 */
	@Raw
	private void decreaseMinRestCounter(double time) throws IllegalArgumentException {
		if(time < 0) {
			throw new IllegalArgumentException();
		}
		try {
			this.setMinRestCounter(this.getMinRestCounter() - time);
		}
		catch (IllegalArgumentException exc) {
			this.setMinRestCounter(0);
		}
	}
	
	/**
	 * Variable registering the minimum rest counter of this unit.
	 */
	private double minRestCounter;
	
	/**
	 * Return the automatic rest counter of this unit.
	 */
	@Basic @Raw
	private double getAutRestCounter() {
		return this.autRestCounter;
	}
	
	/**
	 * Check whether the given automatic rest counter is a valid automatic rest counter for
	 * any unit.
	 *  
	 * @param  automatic rest counter
	 *         The automatic rest counter to check.
	 * @return 
	 *       | result == (autRestCounter >= 0)
	*/
	private static boolean isValidAutRestCounter(double autRestCounter) {
		return (autRestCounter >= 0);
	}
	
	/**
	 * Set the automatic rest counter of this unit to the given automatic rest counter.
	 * 
	 * @param  autRestCounter
	 *         The new automatic rest counter for this unit.
	 * @post   The automatic rest counter of this new unit is equal to
	 *         the given automatic rest counter.
	 *       | new.getAutRestCounter() == autRestCounter
	 * @throws IllegalArgumentException
	 *         The given automatic rest counter is not a valid automatic rest counter for any
	 *         unit.
	 *       | ! isValidAutRestCounter(getAutRestCounter())
	 */
	@Raw @Model
	private void setAutRestCounter(double autRestCounter) 
			throws IllegalArgumentException {
		if (! isValidAutRestCounter(autRestCounter)) {
			throw new IllegalArgumentException();
		}
		this.autRestCounter = autRestCounter;
	}
	
	/**
	 * Resets the automatic rest counter to zero.
	 * @effect	The automatic rest counter is set to zero.
	 * 			| this.setAutRestCounter(0)
	 */
	@Model @Raw
	private void resetAutRestCounter() {
		this.setAutRestCounter(0);
	}
	
	/**
	 * Increases the automatic rest counter by a given amount of time, when it reaches 3 minutes, this unit automatically rests.
	 * @param time	The given amount of time.
	 * @effect	The automatic rest counter is set at the sum of the old time and the given time.
	 * 			| this.setAutRestCounter(this.getAutRestCounter() + time)
	 * @throws 	IllegalArgumentException
	 * 			The given time is negative.
	 * 			| (time < 0)
	*/
	@Model @Raw
	private void increaseAutRestCounter(double time) throws IllegalArgumentException {
		if(time < 0) {
			throw new IllegalArgumentException();
		}
		this.setAutRestCounter(this.getAutRestCounter() + time);
	}
	/**
	 * Variable registering the automatic rest counter of this unit.
	 */
	private double autRestCounter;


	/**
	 * Return the default behaviour of this unit.
	 */
	@Basic @Raw
	public boolean getDefaultBehaviour() {
		return this.defaultBehaviour;
	}
	
	/**
	 * Set the default behaviour of this unit to the given default behaviour.
	 * 
	 * @param  defaultBehaviour
	 *         The new default behaviour for this unit.
	 * @post   The default behaviour of this new unit is equal to
	 *         the given default behaviour.
	 *       | new.getDefaultBehaviour() == defaultBehaviour
	 * @throws IllegalArgumentException
	 *         The given default behaviour is not a valid default behaviour for any
	 *         unit.
	 *       | ! isValidDefaultBehaviour(getDefaultBehaviour())
	 */
	@Raw @Model
	private void setDefaultBehaviour(boolean defaultBehaviour) {
		this.defaultBehaviour = defaultBehaviour;
	}
	
	/**
	 * Variable registering the default behaviour of this unit.
	 */
	private boolean defaultBehaviour;
	
	/**
	 * Start the default behaviour of this unit.
	 * @effect	The default behaviour is set true.
	 * 			| this.setDefaultBehaviour(true)
	 */
	@Raw
	public void startDefaultBehaviour() {
		this.setDefaultBehaviour(true);
	}
	
	/**
	 * Stop the default behaviour of this unit.
	 * @effect	The default behaviour is set false.
	 * 			| this.setDefaultBehaviour(false)
	 */
	@Raw
	public void stopDefaultBehaviour() {
		this.setDefaultBehaviour(false);
	}
	
	/**
	 * Make this unit do a random action of either walking, sprinting, working, resting or moving.
	 * 
	 * @effect 	This unit either moves to a random position, starts to work at a random adjacent position or starts to rest.
	 * 			| Random generator = new Random()
	 *	 		|  int action = generator.nextInt(3)
	 *			|  if (action == 0)
	 *			|  	int sprint = generator.nextInt(2)
	 *			|  	this.moveTo(new PositionVector(generator.nextDouble()*49.99, generator.nextDouble()*49.99, generator.nextDouble()*49.99));
	 *			|  	this.setSprint(sprint == 1)
	 *			|  if (action == 1)
	 *			|  	this.work(this.randomAdjacent())
	 *			|  if (action == 2) 
	 *			|  	this.rest()
	 */
	@Raw
	private void randomBehaviour() throws IllegalArgumentException {
		Random generator = new Random();
		int action = generator.nextInt(3);
		if (action == 0){
			int sprint = generator.nextInt(2);
			this.moveTo(new PositionVector(generator.nextDouble()*49.99, generator.nextDouble()*49.99, generator.nextDouble()*49.99));
			this.setSprint(sprint == 1);
		}
		if (action == 1){
			this.work(this.randomAdjacent());
		}
		if (action == 2) {
			this.rest();
		}
	}
	
	/**
	 * Return a given attribute value, transformed to be a legal initial attribute value.
	 * @param attributeValue	The given attribute value.
	 * @return	If the given attribute value is bigger than 100, 100 is returned.
	 * 			| result == 100
	 * @return	If the given attribute value is smaller than 25, 25 is returned.
	 * 			| result == 25
	 * @return	If the given attribute value is in the range of 25 and 100 (both inclusively), the original value is returned.
	 * 			| result == attributeValue
	 */
	private static int makeInitialAttValue(int attributeValue){
		if (attributeValue > maxInitialAttValue)
			return 100;
		else if (attributeValue < minInitialAttValue)
			return 25;
		else 
			return attributeValue;
	}
	
	/**
	 * Generate a valid random initial value for any attribute of any unit.
	 * @return	A random value between the maxInitialAttValue and the minInitialAttValue.
	 * 			| new Random().nextInt(maxInitialAttValue-minInitialAttValue+1)+minInitialAttValue
	 */
	private static int randomInitialAttValue() {
		Random generator = new Random();
		return generator.nextInt(maxInitialAttValue-minInitialAttValue+1)+minInitialAttValue;
	}
	
	/**
	 * A variable registering the maximum value a unit's attributes can have upon initialization.
	 */
	private static int maxInitialAttValue = 100;
	
	/**
	 * A variable registering the minimum value a unit's attributes can have upon initialization.
	 */
	private static int minInitialAttValue = 25;
	
	/**
	 * Return the faction of this unit.
	 */
	@Basic @Raw
	public Faction getFaction() {
		return this.faction;
	}
	
	/**
	 * Check whether the given faction is a valid faction for
	 * any unit.
	 *  
	 * @param  faction
	 *         The faction to check.
	 * @return 
	 *       | result == (faction != null)
	*/
	public static boolean isValidFaction(Faction faction) {
		return (faction != null);
	}
	
	/**
	 * Set the faction of this unit to the given faction.
	 * 
	 * @param  faction
	 *         The new faction for this unit.
	 * @post   The faction of this new unit is equal to
	 *         the given faction.
	 *       | new.getFaction() == faction
	 * @throws NullPointerException
	 *         The given faction is not a valid faction for any
	 *         unit.
	 *       | ! isValidFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) 
			throws NullPointerException {
		if (! isValidFaction(faction))
			throw new NullPointerException();
		this.faction = faction;
	}
	
	/**
	 * Variable registering the faction of this unit.
	 */
	private Faction faction;
		

	/**
	 * Return the experience of this unit.
	 */
	@Basic @Raw
	public int getExp() {
		return this.exp;
	}
	
	/**
	 * Check whether the given experience is a valid experience for
	 * any unit.
	 *  
	 * @param  experience
	 *         The experience to check.
	 * @return 
	 *       | result == ((exp >= 0) && (exp <= maxExp))
	*/
	public static boolean isValidExp(int exp) {
		return ((exp >= 0) && (exp <= maxExp));
	}
	
	/**
	 * Set the experience of this unit to the given experience.
	 * 
	 * @param  exp
	 *         The new experience for this unit.
	 * @post   The experience of this new unit is equal to
	 *         the given experience.
	 *       | new.getExp() == exp
	 * @throws IllegalArgumentException
	 *         The given experience is not a valid experience for any
	 *         unit.
	 *       | ! isValidExp(getExp())
	 */
	@Raw
	public void setExp(int exp) 
			throws IllegalArgumentException {
		if (! isValidExp(exp))
			throw new IllegalArgumentException();
		this.exp = exp;
	}
	
	/**
	 * Variable registering the experience of this unit.
	 */
	private int exp;
	
	/**
	 * Variable registering the maximum amount of experience any unit can have.
	 */
	private static int maxExp = 10;
	
	/**
	 * Let this unit gain a given amount of experience.
	 * @param exp	The given amount of experience.
	 * @effect	If the sum of this unit's current experience and the given experience exceeds the maximum experience for any unit,
	 * 			This unit levels up until until the sum  of it's current experience and the given experience minus the maximum
	 * 			experience for any unit times the times this unit leveled up is smaller than the maximum experience for any unit.
	 * 			This unit's experience is set to the sum of it's current experience and the given experience modulo the maximum	
	 * 			experience for any unit.
	 * 			| int newExp = this.getExp() + exp
	 *			| while(newExp >= maxExp)
	 *			| 	newExp = newExp - maxExp
	 *			|	this.levelUp()
	 *			| this.setExp(this.getExp() + exp)
	 * @effect	This unit's experience is set to the sum of it's current experience and the given experience.
	 * 			| this.setExp(newExp)
	 * @throws	IllegalArgumentException
	 * 			The given experience is negative.
	 * 			| exp < 0
	 */
	private void gainExp(int exp){
		int newExp = this.getExp() + exp;
		while(newExp >= maxExp)
			newExp = newExp - maxExp;
			this.levelUp();
		this.setExp(newExp);
	}
	
	/**
	 * Levels up this unit.
	 * @effect	A random attribute (agility, strength or toughness) is picked. When the attribute has already reached it's
	 * 			maximum value, level up is invoked recursively, else the attribute is increased by 1. This unit's attributes are
	 * 			refreshed.
	 * 			| if(attribute == 0/1/2)
	 *			| 	if(this.getAgility/Strength/Toughness() == maxAttValue)
	 *			|		this.levelUp()
	 *			| 	else
	 *			|		this.setAgility/Strength/Toughness(this.getAgility/Strength/Toughness() + 1)
	 *			| this.refreshAttributes()
	 * @throws	IllegalStateException
	 *			This unit's agility, strength and toughness have already reached their maximum value.
	 *			| (this.getAgility() == maxAttValue) && (this.getStrength() == maxAttValue) && (this.getToughness() == maxAttValue)
	 * @note	When this unit's agility, strength and toughness have already reached their maximum value, the level up request
	 * 			is silently rejected.
	 */
	private void levelUp() throws IllegalStateException {
		try{
			if((this.getAgility() == maxAttValue) && (this.getStrength() == maxAttValue) && (this.getToughness() == maxAttValue))
				throw new IllegalStateException();
			Random generator = new Random();
			int attribute = generator.nextInt(3);
			if(attribute == 0)
				if(this.getAgility() == maxAttValue)
					this.levelUp();
				else
					this.setAgility(this.getAgility() + 1);
			if(attribute == 1)
				if(this.getStrength() == maxAttValue)
					this.levelUp();
				else
					this.setStrength(this.getStrength() + 1);
			if(attribute == 2)
				if(this.getToughness() == maxAttValue)
					this.levelUp();
				else
					this.setToughness(this.getToughness() + 1);
			this.refreshAttributes();
		}
		catch (IllegalStateException exc){
			
		}
	}
	
	/**
	 * Variable registering the maximum value any attribute of any unit can have.
	 */
	private static int maxAttValue = 200;
	
	/**
	 * Refreshes this unit's derived attributes.
	 * @effect	This unit's maximum hitpoints and stamina are recalculated and set.
	 * 			| this.setMaxHP(calcMaxHPStam(getWeight(), getToughness()))
	 * 			| this.setMaxStamina(calcMaxHPStam(getWeight(), getToughness()))
	 */
	private void refreshAttributes(){
		this.setMaxHP(calcMaxHPStam(getWeight(), getToughness()));
		this.setMaxStamina(calcMaxHPStam(getWeight(), getToughness()));
	}
	
	/**
	 * Check whether the given world is a valid world for
	 * any unit.
	 *  
	 * @param  world
	 *         The world to check.
	 * @return 
	 *       | result == (world == null) || (world.canHaveAsUnit(this)
	*/
	@Override
	protected boolean isValidWorld(World world) {
		return ((world == null) || (world.canHaveAsUnit(this)));
	}
	
	
	/**
	 * Check whether this unit should fall.
	 * @return	True if and only if this unit does not have any solid adjacent cube.
	 * 			| Set<PositionVector> adjacents = this.getWorld().getAllAdjacentPositions(this.getUnitPosition())
	 * 			| for(PositionVector adjacent : adjacents)
	 *			| 	(! this.getWorld().isSolidPosition(adjacent))
	 */
	private boolean fallCheck(){
		if(this.getCubePosition()[2] == 0)
			return false;
		Set<PositionVector> adjacents = this.getWorld().getAllAdjacentPositions(this.getUnitPosition());
		for(PositionVector adjacent : adjacents){
			if(this.getWorld().isSolidPosition(adjacent))
				return false;
		}
		return true;
	}
	
	/**
	 * Makes this unit fall.
	 * @effect	This unit's activity status is set to 'fall', its minumum rest counter to zero, its next position and destination are 
	 * 			set to the center of the cube underneath the cube this unit is occupying.
	 * 			| this.setActivityStatus("fall")
	 * 			| this.setMinRestCounter(0)
	 * 			| this.setNextPosition(this.centrePosition(this.getWorld().getPositionUnderneath(this.getCubePosition())))
	 * 			| this.setDestination(this.centrePosition(this.getWorld().getPositionUnderneath(this.getCubePosition())))
	 */
	private void fall() {
		this.setActivityStatus("fall");
		this.setMinRestCounter(0);
		PositionVector cubePosition = new PositionVector(this.getCubePosition()[0], 
									this.getCubePosition()[1], this.getCubePosition()[2]);
		PositionVector fallToPosition = centrePosition(this.getWorld().getPositionUnderneath(cubePosition));
		this.setNextPosition(fallToPosition);
		this.setDestination(fallToPosition);
		this.setCurrentVelocity(fallVelocity);
	}
	
	/**
	 * Variable registering the falling velocity of any unit.
	 */
	private static PositionVector fallVelocity = new PositionVector(0, 0, -3);
	
	/**
	 * Terminate this unit.
	 * @effect	This unit is removed from it's faction and it's world. It's activity status, velocity, destination, faction,
	 * 			name, next position and world are set to null.
	 * 			| this.getFaction().removeUnit(this)
	 * 			| this.getWorld().removeUnit(this)
	 * 			| this.activityStatus = null
	 *			| this.currentVelocity = null
	 *			| this.destination = null
	 *			| this.faction = null;
	 *			| this.name = null;
	 *			| this.nextPosition = null;
	 *			| this.world = null;
	 * @throws	IllegalStateException
	 * 			This unit is already terminated.
	 * 			| this.isTerminated()
	 */
	private void terminate() throws IllegalStateException {
		if(this.isTerminated())
			throw new IllegalStateException("Already terminated.");
		this.activityStatus = null;
		this.currentVelocity = null;
		this.destination = null;
		this.getFaction().removeUnit(this);
		this.faction = null;
		this.name = null;
		this.nextPosition = null;
		this.getWorld().removeUnit(this);
		this.world = null;
	}
	
	/**
	 * Check whether this unit is terminated.
	 * @return	True is and only if this unit no longer belongs to it's world, nor faction and it's activity status, velocity
	 * 			destination, faction, name, next position and world equal null.
	 * 			| (this.getFaction().hasAsUnit(this)) && (this.getWorld().hasAsUnit(this))
	 * 			| && (this.activityStatus == null) && (this.currentVelocity == null) && (this.destination == null)
	 * 			| && (this.faction == null) && (this.name == null) && (this.nextPosition == null) && (this.world == null)
	 */
	public boolean isTerminated(){
		return ((this.getFaction().hasAsUnit(this)) && (this.getWorld().hasAsUnit(this)) && (this.activityStatus == null)
				&& (this.currentVelocity == null) && (this.destination == null) && (this.faction == null)
				&& (this.name == null) && (this.nextPosition == null) && (this.world == null));
	}
	
	/**
	 * Return the path queue of this unit.
	 */
	@Basic @Raw
	public Map<PositionVector, Integer> getQueue() {
		return this.path;
	}
	
	/**
	 * Check whether the given path queue is a valid path queue for
	 * any unit.
	 *  
	 * @param  path queue
	 *         The path queue to check.
	 * @return 
	 *       | result == (queue != null)
	*/
	public static boolean isValidQueue(Map<PositionVector, Integer> queue) {
		return (queue != null);
	}
	
	/**
	 * Set the path queue of this unit to the given path queue.
	 * 
	 * @param  queue
	 *         The new path queue for this unit.
	 * @post   The path queue of this new unit is equal to
	 *         the given path queue.
	 *       | new.getQueue() == queue
	 * @throws IllegalArgumentException
	 *         The given path queue is not a valid path queue for any
	 *         unit.
	 *       | ! isValidQueue(getQueue())
	 */
	@Raw
	public void setQueue(Map<PositionVector, Integer> queue) 
			throws IllegalArgumentException {
		if (! isValidQueue(queue))
			throw new IllegalArgumentException();
		this.path = queue;
	}
	
	/**
	 * Variable registering the path queue of this unit.
	 */
	private Map<PositionVector, Integer> path;
	
	/**
	 * Variable registering any unit's inventory capacity.
	 */
	private static int inventoryCapacity = 1;
	
	/**
	 * Return the inventory of this unit.
	 */
	@Basic @Raw
	public Set<Material> getInventory() {
		return this.inventory;
	}
	
	/**
	 * Check whether the given inventory is a valid inventory for
	 * any unit.
	 *  
	 * @param  inventory
	 *         The inventory to check.
	 * @return 
	 *       | result == (inventory != null)
	*/
	public static boolean isValidInventory(Set<Material> inventory) {
		return (inventory != null);
	}
	
	/**
	 * Set the inventory of this unit to the given inventory.
	 * 
	 * @param  inventory
	 *         The new inventory for this unit.
	 * @post   The inventory of this new unit is equal to
	 *         the given inventory.
	 *       | new.getInventory() == inventory
	 * @throws NullPointerException
	 *         The given inventory is not a valid inventory for any
	 *         unit.
	 *       | ! isValidInventory(getInventory())
	 */
	@Raw
	public void setInventory(Set<Material> inventory) 
			throws NullPointerException {
		if (! isValidInventory(inventory))
			throw new NullPointerException();
		this.inventory = inventory;
	}
	
	/**
	 * Variable registering the inventory of this unit.
	 */
	private Set<Material> inventory;

	/**
	 * Add a given material to this unit's inventory.
	 * @param material	The given material.
	 * @effect	The given material is added to this unit's inventory and removed from this unit's world.
	 * 			| this.getInventory().add(material)
	 * 			| this.getWorld().removeMaterial(material)
	 * @throws IllegalArgumentException
	 */
	public void addMaterialToInventory(Material material) throws IllegalArgumentException{
		if(! this.canHaveAsMaterial(material))
			throw new IllegalArgumentException();
		this.getInventory().add(material);
		this.getWorld().removeMaterial(material);
	}
	
	/**
	 * Check whether this unit can add a given material to it's inventory.
	 * @param material	The given material.
	 * @return	True if and only if the given material is effective, from the same world as this unit, has a position that is
	 * 			an adjacent position for this unit and this unit's inventory is not full.
	 * 			| result == (! material == null) && (material.getWorld().equals(this.getWorld()))
	 * 			| 			&& (this.isValidAdjacent(PositionVector.calcDifferenceVector(this.getCubePositionVector(), 
	 * 			| 																		material.getCubePositionVector())))
	 * 			| 				&& (this.getInventory().size() < inventoryCapacity)
	 */
	public boolean canHaveAsMaterial(Material material){
		if(material == null)
			return false;
		if(! material.getWorld().equals(this.getWorld()))
			return false;
		if(! this.isValidAdjacent(PositionVector.calcDifferenceVector(this.getCubePositionVector(), material.getCubePositionVector())))
			return false;
		if(this.getInventory().size() == inventoryCapacity)
			return false;
		return true;			
	}
	
	/**
	 * Remove a given material from this unit's inventory.
	 * @param material	The given material.
	 * @effect	The given material is removed from his unit's inventory, the material's position is set 
	 * 			to this unit's current position and is added to this unit's world.
	 * 			| this.getInventory().remove(material)
	 * 			| material.setUnitPosition(this.getUnitPosition())
	 * 			| this.getWorld().addMaterial(material)
	 * @throws IllegalArgumentException
	 * 			The given material is not in this unit's inventory.
	 * 			| ! this.inInventory(material)
	 */
	@Model
	private void removeMaterial(Material material) throws IllegalArgumentException {
		if(! this.inInventory(material))
			throw new IllegalArgumentException("The given material is not in this unit's inventory");
		this.getInventory().remove(material);
		material.setUnitPosition(this.getUnitPosition());
		this.getWorld().addMaterial(material);
	}
	
	/**
	 * Empty this unit's inventory.
	 * @effect	All materials are removed from this unit's inventory.
	 * 			| for(Material material : this.getInventory())
	 * 			| 	this.removeMaterial(material)
	 */
	public void emptyInventory(){
		for(Material material : this.getInventory())
			this.removeMaterial(material);
	}
	
	/**
	 * Check whether a given material is in this unit's inventory.
	 * @param material	The given material.
	 * @return	True if and only if this unit's inventory contains the given material.
	 * 			| result == this.getInventory().contains(material)
	 */
	public boolean inInventory(Material material){
		if(material == null)
			return false;
		return this.getInventory().contains(material);
	}
	
	/**
	 * Return the work position of this unit.
	 */
	@Basic @Raw
	public PositionVector getWorkPosition() {
		return this.workPosition;
	}
	
	/**
	 * Check whether the given work position is a valid work position for
	 * any unit.
	 *  
	 * @param  work position
	 *         The work position to check.
	 * @return True if and only if the given work position is null or a valid adjacent position for this unit.
	 *       | result == ((workPosition == null) || (this.isValidAdjacent(PositionVector.calcDifferenceVector(this.getCubePositionVector(), 
	 *       | 																								workPosition)))
	*/
	public boolean isValidWorkPosition(PositionVector workPosition) {
		return ((workPosition == null) || (this.isValidAdjacent(PositionVector.calcDifferenceVector(this.getCubePositionVector(),
				workPosition))));
	}
	
	/**
	 * Set the work position of this unit to the given work position.
	 * 
	 * @param  workPosition
	 *         The new work position for this unit.
	 * @post   The work position of this new unit is equal to
	 *         the given work position.
	 *       | new.getWorkPosition() == workPosition
	 * @throws IllegalArgumentException
	 *         The given work position is not a valid work position for any
	 *         unit.
	 *       | ! isValidWorkPosition(getWorkPosition())
	 */
	@Raw
	public void setWorkPosition(PositionVector workPosition) 
			throws IllegalArgumentException {
		if (! isValidWorkPosition(workPosition))
			throw new IllegalArgumentException();
		this.workPosition = workPosition;
	}
	
	/**
	 * Variable registering the work position of this unit.
	 */
	private PositionVector workPosition;

}
