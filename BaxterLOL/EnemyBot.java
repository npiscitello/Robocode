package nbp;

import robocode.ScannedRobotEvent;

public class EnemyBot
{

  // variables to hold data about the scanned bot
  private Double bearing;
  private Double distance;
  private Double energy;
  private Double heading;
  private Double velocity;
  private String name;

  // overloaded to provide an empty constructor or a declare&define style constructor
  // Are we ever going to be constructing one wihtout an event?
	//public EnemyBot() {}

  public EnemyBot(ScannedRobotEvent e) {
    update(e);
  }
	
	// updates data for existing enemy or adds new enemy
	public void update(ScannedRobotEvent e)	{
    bearing = e.getBearingRadians();
    distance = e.getDistance();
    energy = e.getEnergy();
    heading = e.getHeadingRadians();
    velocity = e.getVelocity();
    name = e.getName();
	}

  // getters, b/c we're only going to need to read data from this class
  // direction of the robot from you
  public Double getBearingRadians() {
    return bearing;
  }

  public Double getDistance() {
    return distance;
  }

  public Double getEnergy() {
    return energy;
  }

  // which way the robot is pointing
  public Double getHeadingRadians() {
    return heading;
  }

  public Double getVelocity() {
    return velocity;
  }

  public String getName() {
    return name;
  }
}
