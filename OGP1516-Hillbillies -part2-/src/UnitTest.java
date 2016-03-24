import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class UnitTest {
	
	private Unit tester;
	private Unit target;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tester = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 50, 50, 50);
		target = new Unit(new PositionVector(2.0,1.0,1.0), "Ikke", 50, 50, 50, 50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() throws Exception {
		Unit man = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 51, 52, 55);
		assertEquals((man.getUnitPosition()).equals((new PositionVector(1.5,1.5,1.5))), true);
		assertEquals(((man.getName()).equals("Ikke")), true);
		assertEquals(man.getStrength(),50);
		assertEquals(man.getAgility(),51);
		assertEquals(man.getToughness(),52);
		assertEquals(man.getWeight(),55);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_IllegalPosition() throws Exception {
		new Unit(new PositionVector(-1.0,1.0,1.0), "Ikke", 50, 50, 50, 50);
	}
	@Test (expected = IllegalArgumentException.class)
	public void constructor_IllegalName() throws Exception {
		new Unit(new PositionVector(1.0,1.0,1.0), "ikke", 50, 50, 50, 50);
	}
	
	@Test
	public void contructor_IllegalStrength() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 101, 50, 50, 50);
		assertEquals(temp.getStrength(),100);
	}
	
	@Test
	public void contructor_IllegalAgility() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 20, 50, 50);
		assertEquals(temp.getAgility(),25);
	}
	
	@Test
	public void contructor_IllegalToughness() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 50, 300, 50);
		assertEquals(temp.getToughness(),100);
	}
	
	@Test
	public void contructor_IllegalWeight() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 50, 50, ((50+50)/2)-1);
		assertEquals(temp.getWeight(),(temp.getStrength()+temp.getAgility())/2);
	}
	
	@Test
	public void sprintOn() {
		tester.setSprint(true);
		assertEquals(tester.getSprint(),true);
		tester.setSprint(false);
		assertEquals(tester.getSprint(),false);
	}
	
	
	@Test
	public void startRest_LegalCase() throws Exception {
		tester.setSprint(true);
		tester.moveToAdjacent(new PositionVector(1, 1, 1));
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.rest();
		assertEquals(tester.getActivityStatus(),"rest");	
		assertEquals(tester.getMinRestCounter(),0.2/(tester.getToughness()/200.0),0.000001);
	}
	
	@Test
	public void moveToAdjacent_LegalCase() throws Exception {
		tester.moveToAdjacent(new PositionVector(1,0,0));
		assertEquals(tester.getActivityStatus().equals("move"),true);
		assertEquals(tester.getNextPosition().equals(new PositionVector(2.5,1.5,1.5)),true);
		
		double baseSpeed = 1.5;
		assertEquals(baseSpeed,tester.getBaseSpeed(),0.0000001);
		double distance = Math.sqrt(2.25+0.25+0.25);
		PositionVector velocity = new PositionVector(baseSpeed*1.5/distance, baseSpeed*0.5/distance, baseSpeed*0.5/distance);
		assertEquals(velocity.getXArgument(),baseSpeed*1.5/distance,0.0001);
		assertEquals(velocity.getYArgument(),baseSpeed*0.5/distance,0.0001);
		assertEquals(velocity.getZArgument(),baseSpeed*0.5/distance,0.0001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void moveToAdjacent_IllegalAdjacent() throws Exception {
		tester.moveToAdjacent(new PositionVector(1,2,0));
	}
	
	@Test (expected = IllegalStateException.class)
	public void moveToAdjacent_AlreadyMovingToAdjacent() throws Exception {
		tester.moveToAdjacent(new PositionVector(1,0,0));
		tester.moveToAdjacent(new PositionVector(1,0,0));
	}
	
	@Test
	public void getBaseSpeed() {
		assertEquals(1.5,tester.getBaseSpeed(),0.00001);
	}
	
	@Test
	public void startWork_LegalCase() throws Exception {
		tester.work();
		assertEquals(tester.getActivityStatus(),"work");
	}
	
	@Test
	public void work_LegalCase() {
		tester.work();
		double i = 500/tester.getStrength();
		while(i > 0){
			tester.advanceTime(0.1);
			i = i-0.1;
		}
		assertEquals((tester.getActivityStatus()).equals("default"), true);
	}
	
	@Test
	public void advanceTime_LegalMoveToAdjacent() {
		tester.moveToAdjacent(new PositionVector(1, 0, 0));
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		assertEquals(tester.getUnitPosition().equals(new PositionVector(2.5, 1.5, 1.5)),true);
	}
	
	@Test
	public void moveTo_LegalCase() {
		PositionVector destination = new PositionVector(3, 1.5, 1.5);
		tester.moveTo(destination);
		PositionVector unitDestination = tester.getDestination();
		PositionVector unitNextPosition = tester.getNextPosition();
		assertEquals(unitDestination.equals(new PositionVector(3.5,1.5,1.5)), true);
		assertEquals(unitNextPosition.equals(new PositionVector(2.5,1.5,1.5)), true);
	}
	
	@Test
	public void advanceTime_LegalMoveTo() {
		PositionVector destination = new PositionVector(3, 1.5, 1.5);
		tester.moveTo(destination);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		tester.advanceTime(0.19);
		PositionVector unitDestination = tester.getDestination();
		PositionVector unitNextPosition = tester.getNextPosition();
		PositionVector unitPosition = tester.getUnitPosition();
		assertEquals(unitDestination.equals(new PositionVector(3.5,1.5,1.5)), true);
		assertEquals(unitNextPosition.equals(new PositionVector(3.5,1.5,1.5)), true);
		assertEquals(unitPosition.equals(new PositionVector(3.5,1.5,1.5)), true);
	}
	
	@Test
	public void calDistance_LegalCase() {
		PositionVector position1 = new PositionVector(0, 0, 0);
		PositionVector position2 = new PositionVector(1, 1, 1);
		double distance = PositionVector.calcDistance(position1, position2);
		assertEquals(distance,Math.sqrt(3),0.00001);
	}
	
	@Test
	public void sprintTime_LegalCase() {
		double time1 = tester.getSprintTime(50);
		double time2 = tester.getSprintTime(20);
		double time3 = tester.getSprintTime(15);
		assertEquals(time1,20,0.0000001);
		assertEquals(time2,20,0.0000001);
		assertEquals(time3,15,0.0000001);
	}
	
	
	@Test
	public void attack_LegalCase() {
		tester.attack(target);
		assertEquals(tester.getActivityStatus().equals("attack"),true);
	}
	
	@Test (expected = IllegalStateException.class)
	public void attack_IllegalCase() {
		tester.attack(target);
		tester.attack(target);
	}
	
	@Test
	public void defend_LegalCase() {
		int i = 0;
		while(i <= 1000){
		PositionVector targetPosition = target.getUnitPosition();
		double hp = target.getCurrentHP();
		target.defend(tester);
		boolean dodge = (target.isValidAdjacent(PositionVector.calcDifferenceVector(target.getUnitPosition(),targetPosition))
				&& !(target.getUnitPosition().equals(targetPosition)));
		boolean block = (target.getCurrentHP() == hp) && (target.getUnitPosition().equals(targetPosition));
		boolean damage = (hp - tester.getStrength() == target.getCurrentHP());
		assertEquals((dodge || block || damage), true);
		i++;
		}
	}
	
	@Test
	public void advanceTime_LegalRestForStam() {
		PositionVector destination = new PositionVector(25, 25, 25);
		tester.setSprint(true);
		tester.moveTo(destination);
		while(!(tester.getUnitPosition().equals(tester.getDestination()))) {
		tester.advanceTime(0.19);
		}
		tester.rest();
		while(tester.getCurrentStamina() != tester.getMaxStamina()) {
			tester.advanceTime(0.19);
		}
		assertEquals(tester.getCurrentStamina(),tester.getMaxStamina());
	}
	
	@Test
	public void advanceTime_LegalRestForHP() {
		while(target.getCurrentHP() == target.getMaxHP()){
			if(!(tester.getUnitPosition().equals(target.getUnitPosition()))) {
				tester.moveTo(target.getUnitPosition());
				while((tester.getUnitPosition().equals(target.getUnitPosition())) == false){
					tester.advanceTime(0.19);
				}
			}
			tester.attack(target);
			target.defend(tester);
			double i = 0;
			while (i <= 1) {
				tester.advanceTime(0.1);
				target.advanceTime(0.1);
				i = i + 0.1;
			}
		}
		target.rest();
		while(target.getCurrentHP() < target.getMaxHP()){
			target.advanceTime(0.19);
		}
		assertEquals(target.getCurrentHP(), target.getMaxHP());
		
	}
	
	@Test
	public void autoRest_LegalCase() {
		PositionVector destination = new PositionVector(25, 25, 25);
		double time = 0;
		tester.setSprint(true);
		tester.moveTo(destination);
		while(!(tester.getUnitPosition().equals(tester.getDestination()))) {
		tester.advanceTime(0.19);
		time = time + 0.19;
		}
		while(time < 180){
			tester.advanceTime(0.19);
			time = time + 0.19;
		}
		assertEquals(tester.getActivityStatus().equals("rest"),true);
	}
	
	// change actions to defensive
}
