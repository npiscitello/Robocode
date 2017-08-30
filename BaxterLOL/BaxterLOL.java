package nbp;
import java.awt.Color;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

import nbp.EnemyBot;

/**
 * BaxterLOL - a robot by Nick P
 * 	I just needed a name for the robot; Baxter came to mind, then I laughed because
 * 		Baxter is not a very good robot.
 * 
 * Target Behavior:
 *  - Stage 1:
 * 		- constant 360* scan, keep table of opponent info (name, energy, bearing, distance, etc.)
 * 		- choose closest robot, target until death
 * 		- Always point gun towards the targeted bot
 *  - Stage 2:
 * 		- Use distance and current velocity of targeted bot to lead the shot (aim slightly ahead)
 * 		- don't fire if heat is above heat threshold; resume firing when under cool threshold
 *  - Stage 3:
 * 		- Always move towards the targeted bot
 * 		- if the bot is closer than a certain threshold, move in a circle around it
 * 			- once outside the threshold, resume normal motion
 * 			- looks like this:  <Enemy>	|cst	|mrt	<myself>
 * 				- where cst = circle start threshold and mrt = movement resume threshold
 */

public class BaxterLOL extends AdvancedRobot
{

  Map<String, EnemyBot> enemies = new HashMap<String, EnemyBot>();
	String target = "";
	

	@Override
	public void run() {

			// body, gun, radar, bullet, scanarc
		setColors(Color.red,Color.black,Color.gray,Color.white,Color.red);
			// make gun, radar, and body rotation independent
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustGunForRobotTurn(true);

		while(true) {
			turnRadarRight(Rules.RADAR_TURN_RATE);
		}
	}

  @Override
	public void onScannedRobot(ScannedRobotEvent e) {
    String enemyname = e.getName();
    // this instantiates and copies (I think?) an object on each scan; definitely
    // viable for an optimization!
    enemies.put(enemyname, new EnemyBot(e));
    System.out.print("Scanned ");
    System.out.print(enemyname);
    System.out.print(", distance: ");
    System.out.println(enemies.get(enemyname).getDistance());
    /*
		System.out.print("Registered Enemies: ");
		System.out.print(enemies.getName());
		System.out.print("		Updated: ");
		System.out.println(e.getName());
		if(target != -1)	{
			System.out.print("Targeted: ");
			System.out.println(enemies.getName(target));
		}
    */
	}
	
  /*
	@Override
	public void onStatus(StatusEvent e)	{
			// if Baxter knows about all the robots but hasn't targeted one, target closest robot
		if(e.getStatus().getOthers() == enemies.getName().size() && target == -1)	{
			target = enemies.getDistance().indexOf(Collections.min(enemies.getDistance()));
		}
	}
	
	@Override
	public void onRobotDeath(RobotDeathEvent e)	{
			// delete dead robot from database
		enemies.remove(enemies.getName().indexOf(e.getName()));
		if(enemies.getName(target).equals(e.getName()))	{
			target = -1;
		}
	}	
  */
}
