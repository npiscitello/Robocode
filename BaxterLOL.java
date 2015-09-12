package nbp;
import robocode.*;
import java.awt.Color;

/**
 * BaxterLOL - a robot by Nick P
 * 	I just needed a name for the robot; Baxter came to mind, then I laughed because
 * 		Baxter is not a very good robot.
 * 
 * Target Behaivior:
 * 	- Radar tracking
 * 		- constant 360* scan, keep table of opponent info (name, energy, bearing, distance, etc.)
 * 		- choose closest robot, attack until death
 * 	- Movement
 * 		- Always point at and move towards the targeted bot
 * 		- if the bot is closer than a certain threshhold, move in a circle around it
 * 			- once outside the threshhold, resume normal motion
 * 			- looks like this:  <Enemy>	|cst	|mrt	<myself>
 * 				- where cst = circle start threshhold and mrt = movement resume threshhold
 * 	- Attack
 * 		- Always point gun at the targeted bot
 * 			- Only turn when the gun will be cool enough to shoot when it gets to the target heading
 * 		- Fire only when on target heading
 */

public class BaxterLOL extends Robot
{

	EnemyBots enemies = new EnemyBots();
	
	

	public void run() {

			// body,gun,radar,bullet,scanarc
		setColors(Color.red,Color.black,Color.gray,Color.white,Color.red);
			// make gun, radar, and body rotation independent
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustGunForRobotTurn(true);

		// Robot main loop
		while(true) {
				// radar track as described above
			turnRadarRight(Rules.RADAR_TURN_RATE);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		enemies.update(e);
		System.out.print("Registered Enemies: ");
		System.out.print(enemies.getNames());
		System.out.print("		Updated: ");
		System.out.println(e.getName());

	}

	public void onHitByBullet(HitByBulletEvent e) {
	}

	public void onHitWall(HitWallEvent e) {
	}	
}
