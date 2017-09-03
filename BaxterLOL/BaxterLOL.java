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

        double robot_heading = getHeadingRadians();
        double gun_heading = getGunHeadingRadians();
        double enemy_bearing = enemies.get(target).getBearingRadians();
        double turn_right = 0;

        // robot_heading + enemy_bearing = gun_heading
        turn_right = (robot_heading + enemy_bearing) - gun_heading;
        if( turn_right > Rules.GUN_TURN_RATE_RADIANS ) {
          turn_right = Rules.GUN_TURN_RATE_RADIANS;
        }

        setTurnGunRightRadians(turn_right);

        System.out.print("T: ");
        System.out.print(target);
        System.out.print(", H: ");
        System.out.print(robot_heading);
        System.out.print(", B: ");
        System.out.print(enemy_bearing);
        System.out.print(", H+B: ");
        System.out.print(robot_heading + enemy_bearing);
        System.out.print(", G: ");
        System.out.print(gun_heading);
        System.out.print(", R: ");
        System.out.println(turn_right);
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
	}

  // remove all traces of a dead robot
  public void onRobotDeath(RobotDeathEvent e) {
    if( e.getName() == target ) {
      target = null;
    }
    enemies.remove(e.getName());
  }
}
