package nbp;
import java.awt.Color;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;

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

public class BaxterLOL extends AdvancedRobot {

  Map<String, EnemyBot> enemies = new HashMap<String, EnemyBot>();
	String target = null;

	public void run() {

			// body, gun, radar, bullet, scanarc
		setColors(Color.red,Color.black,Color.gray,Color.white,Color.red);
			// make gun, radar, and body rotation independent
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustGunForRobotTurn(true);

		while(true) {
			setTurnRadarRight(Rules.RADAR_TURN_RATE);

      // turn gun to currently targeted enemy, if there is a target
      if( target != null ) {

        System.out.print("Heading: ");
        System.out.print(getHeadingRadians());
        System.out.print(", Gun Heading: ");
        System.out.print(getGunHeadingRadians());
        System.out.print(", Bearing: ");
        System.out.print(enemies.get(target).getBearingRadians());
        System.out.print(", Target: ");
        System.out.println(target);
      }

      execute();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
    String scanned = e.getName();
    // this instantiates and copies (I think?) an object on each scan; definitely
    // viable for an optimization!
    enemies.put(scanned, new EnemyBot(e));
    if( target == null ) {
      target = scanned;
    } else {
      if( enemies.get(target).getDistance() > enemies.get(scanned).getDistance() ) {
        target = scanned;
      }
    }
    /*
    System.out.print("Scanned ");
    System.out.print(scanned);
    System.out.print(", distance: ");
    System.out.print(enemies.get(scanned).getDistance());
    System.out.print(", target: ");
    System.out.println(target);
    */
	}

  // remove all traces of a dead robot
  public void onRobotDeath(RobotDeathEvent e) {
    if( e.getName() == target ) {
      target = null;
    }
    enemies.remove(e.getName());
  }
}
