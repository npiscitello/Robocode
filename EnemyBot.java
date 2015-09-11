package nbp;

import robocode.*;

public class EnemyBot
{
	public EnemyBot()	{
		reset();
	}

	private double bearing, distance, energy, heading, velocity;
	private String name;
	
	public double getBearing()	{
		return bearing;
	}
	
	public double getDistance()	{
		return distance;
	}
	
	public double getEnergy()	{
		return energy;
	}
	
	public double getHeading()	{
		return heading;
	}
	
	public double getVelocity()	{
		return velocity;
	}
	
	public String getName()	{
		return name;
	}
	
	public void update(ScannedRobotEvent e)	{
		bearing = e.getBearing();
		distance = e.getDistance();
		energy = e.getEnergy();
		heading = e.getHeading();
		velocity = e.getVelocity();
		name = e.getName();
	}
	
	public void reset()	{
		bearing = 0;
		distance = 0;
		energy = 0;
		heading = 0;
		velocity = 0;
		name = "";
	}
	
	public boolean none()	{
		if(name.equals(""))	{
			return true;
		}	else	{
			return false;
		}
	}
}