package nbp;
import robocode.*;
import java.awt.Color;
import java.util.Collections;

/**
 * BaxterLOL - a robot by Nick P
 * 	I just needed a name for the robot; Baxter came to mind, then I laughed because
 * 		Baxter is not a very good robot.
 * 
 * Target Behaivior:
 * 	- Radar tracking
 * 		- constant 360* scan, keep table of opponent info (name, energy, bearing, distance, etc.)
 * 		- choose closest robot, target until death
 * 	- Movement
 * 		- Always point at and move towards the targeted bot
 * 		- if the bot is closer than a certain threshhold, move in a circle around it
 * 			- once outside the threshhold, resume normal motion
 * 			- looks like this:  <Enemy>	|cst	|mrt	<myself>
 * 				- where cst = circle start threshhold and mrt = movement resume threshhold
 * 	- Attack
 * 		- Always point gun at the targeted bot
 * 		- Fire only when on target heading
 * 			- don't fire if heat is above heat threshhold; resume firing when under cool threshhold
 */

public class BaxterLOL extends Robot
{

	EnemyBots enemies = new EnemyBots();
		// contains the index value of the targeted enemy; -1 indicates no target
	int target = -1;
	

	public void run() {

			// body, gun, radar, bullet, scanarc
		setColors(Color.red,Color.black,Color.gray,Color.white,Color.red);
			// make gun, radar, and body rotation independent
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustGunForRobotTurn(true);

		while(true) {
			turnRadarRight(Rules.RADAR_TURN_RATE);
			if(target != -1)	{
				aimGun(enemies.getBearing(target));
				if(getGunHeat() == 0)	{
					fire(1);
				}
			}
		}
	}
	
	public void aimGun(double bearing)	{
		turnGunRight(getHeading() - getGunHeading() + enemies.getBearing(target));
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		enemies.update(e);
		System.out.print("Registered Enemies: ");
		System.out.print(enemies.getName());
		System.out.print("		Updated: ");
		System.out.println(e.getName());
		if(target != -1)	{
			System.out.print("Targeted: ");
			System.out.println(enemies.getName(target));
		}

	}
	
	public void onStatus(StatusEvent e)	{
			// if Baxter knows about all the robots but hasn't targeted one, target closest robot
		if(e.getStatus().getOthers() == enemies.getName().size() && target == -1)	{
			target = enemies.getDistance().indexOf(Collections.min(enemies.getDistance()));
		}
	}
	
	public void onRobotDeath(RobotDeathEvent e)	{
			// delete dead robot from database
		enemies.remove(enemies.getName().indexOf(e.getName()));
		if(enemies.getName(target).equals(e.getName()))	{
			target = -1;
		}
	}	
}
