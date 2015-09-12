package nbp;

import robocode.*;
import java.util.*;

public class EnemyBots
{
	public EnemyBots()	{
		reset();
	}
		// initialize variable-length arrays
	ArrayList<Double> bearing = new ArrayList<Double>();
	ArrayList<Double> distance = new ArrayList<Double>();
	ArrayList<Double> energy = new ArrayList<Double>();
	ArrayList<Double> heading = new ArrayList<Double>();
	ArrayList<Double> velocity = new ArrayList<Double>();
	ArrayList<String> name = new ArrayList<String>();
	private int index;

	public double getBearing(String name)	{
		return bearing.get(index);
	}
	
	public double getDistance(String name)	{
		return distance.get(index);
	}
	
	public double getEnergy(String name)	{
		return energy.get(index);
	}
	
	public double getHeading(String name)	{
		return heading.get(index);
	}
	
	public double getVelocity(String name)	{
		return velocity.get(index);
	}
	
	public ArrayList<String> getNames()	{
		return name;
	}
	
		// updates data for existing enemy or adds new enemy
	public void update(ScannedRobotEvent e)	{
		if(name.contains(e.getName()))	{
			index = name.indexOf(e.getName());
			bearing.add(index, e.getBearing());
			distance.add(index, e.getDistance());
			energy.add(index, e.getEnergy());
			heading.add(index, e.getHeading());
			velocity.add(index, e.getVelocity());
			name.add(index, e.getName());
		}	else	{
			bearing.add(e.getBearing());
			distance.add(e.getDistance());
			energy.add(e.getEnergy());
			heading.add(e.getHeading());
			velocity.add(e.getVelocity());
			name.add(e.getName());
		}
	}
		// clears all lists
	public void reset()	{
		bearing.clear();
		distance.clear();
		energy.clear();
		heading.clear();
		velocity.clear();
		name.clear();
	}
}