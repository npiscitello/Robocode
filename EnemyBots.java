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
	ArrayList<String> names = new ArrayList<String>();
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
		return names;
	}
	
		// updates data for existing enemy or adds new enemy
	public void update(ScannedRobotEvent e)	{
		if(names.contains(e.getName()))	{
			index = names.indexOf(e.getName());
			bearing.set(index, e.getBearing());
			distance.set(index, e.getDistance());
			energy.set(index, e.getEnergy());
			heading.set(index, e.getHeading());
			velocity.set(index, e.getVelocity());
			names.set(index, e.getName());
		}	else	{
			bearing.add(e.getBearing());
			distance.add(e.getDistance());
			energy.add(e.getEnergy());
			heading.add(e.getHeading());
			velocity.add(e.getVelocity());
			names.add(e.getName());
		}
	}
	
		// clears all lists
	public void reset()	{
		bearing.clear();
		distance.clear();
		energy.clear();
		heading.clear();
		velocity.clear();
		names.clear();
	}
}