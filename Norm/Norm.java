package nbp;

import java.awt.Color;

import robocode.Robot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/*
 * Norm - a robot by  Nick P.
 * 
 * Inspired by the Shockwave Robotics Corporation's (FRC #4546) rookie year Ultimate Ascent robot, known colloquially as "Norm".
 */

public class Norm extends Robot
{
	private String enemyName = "none";													// variable declaration
	@SuppressWarnings("unused")
	private double x1, x2, x3, y1, y2, y3, dist1, dist2, head, head1, head2, theta, turn;
	
	@Override
	public void run()	{																// default run (called upon creation)
		setColors(Color.green,Color.white,Color.green,Color.green,Color.green);			// color assignment
		setAdjustRadarForGunTurn(true);													// radar turns independently of gun
		if(enemyName.equals("none"))	{
			turnRadarRight(Double.POSITIVE_INFINITY);									// turn gun if no robot is scanned...
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {									// called when a robot is scanned
		//out.println(enemyName);														// print enemy name to console, for debugging
		if(enemyName.equals(e.getName()))	{}											// if it scans a new target...
		else	{
			enemyName = e.getName();													// obtain name of scanned 'bot
			out.println("Target acquired: " + enemyName);								// tell the console there's a new target
		}
		double radarTurn = getHeading() + e.getBearing() - getRadarHeading();			// radar lock
		turnRadarRight(1.2 * Utils.normalRelativeAngleDegrees(radarTurn));
		
		shootGun();
		
		/*if(getGunHeat()==0)	{															// test if ready to fire
	//cos and sin are reversed because Robocode's directions are 0 at the top and clockwise-positive.  Draw it out; it works.
			x1 = dist1 * Math.sin(head1);		y1 = dist1 * Math.cos(head1);			// calc previous position in rect. coordinates
			dist2 = e.getDistance();			head2 = e.getHeadingRadians();			// get current distance and heading
			x2 = dist2 * Math.sin(head2);		y2 = dist2 * Math.cos(head2);			// calc current position in rect. coordinates
			x3 = (dist2 / 17) * (2 * x2 - x1);	y3 = (dist2 / 17) * (2 * y2 - y1);		// predict next position, bullet velocity = 17
			theta = Math.atan(y3 / x3);													// calc. raw gun angle
			if(x3 <0)																	// if theta is in the wrong quadrant...
				theta = theta + Math.PI;												// ...fix theta
			theta = Utils.normalRelativeAngleDegrees(Math.toDegrees(theta));			// convert to degrees and normalize
			turnGunLeft(theta - Utils.normalRelativeAngleDegrees(getGunHeading()));		// turn gun
			
		}
		dist1 = e.getDistance();				head1 = e.getHeadingRadians();			// store values for next iteration
		out.println("dist1 = " + dist1 + "  dist2 = " + dist2);*/
	}
	
	@Override
	public void onRobotDeath(RobotDeathEvent e)	{										// called when a robot dies
		if(enemyName.equals(e.getName()));	{											// if the target died...
			out.println("Target eliminated: " + enemyName);								// tell the console the target just died
			enemyName = "none";															// reset enemy name variable
		}
	}
	
	public void shootGun(){
		fire(2);
	}
	
}