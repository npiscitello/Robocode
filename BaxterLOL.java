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
 * 		- choose lowest energy robot, attack until death
 * 			- if all robots same energy, choose closest one
 */

public class BaxterLOL extends Robot
{

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
		enemy.update();
	}

	public void onHitByBullet(HitByBulletEvent e) {
	}

	public void onHitWall(HitWallEvent e) {
	}	
}
