package nbp;
import robocode.*;
import java.awt.Color;
/**
 * BaxterLOL - a robot by Nick P
 */
public class BaxterLOL extends Robot
{
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red,Color.black,Color.gray); // body,gun,radar

		// Robot main loop
		while(true) {
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}

	public void onHitWall(HitWallEvent e) {
		back(20);
	}	
}
