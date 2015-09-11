package nbp;
import robocode.*;
import java.awt.Color;

/**
 * BaxterLOL - a robot by Nick P
 * 
 * Target Behaivior:
 * 	- Radar tracking
 * 		- spin the radar, track the gun around when temp is less than a certain value
 * 		- chase one robot and destroy; move on to a second, etc.
 * 	-
 * 	-
 */

public class BaxterLOL extends Robot
{

	EnemyBot enemy = new EnemyBot();
	
	public void run() {
		setColors(Color.red,Color.black,Color.gray); // body,gun,radar
		setAdjustRadarForRobotTurn(true);

		// Robot main loop
		while(true) {
				// radar track as described above
			
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		enemy.update(e);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}

	public void onHitWall(HitWallEvent e) {
		back(20);
	}	
}
