package nbp;

import java.util.ArrayList;

import robocode.ScannedRobotEvent;

public class EnemyBots
{
	public EnemyBots()	{
		reset();
	}
		// initialize variable-length arrays
	ArrayList<Double> bearing = new ArrayList<>();
	ArrayList<Double> distance = new ArrayList<>();
	ArrayList<Double> energy = new ArrayList<>();
	ArrayList<Double> heading = new ArrayList<>();
	ArrayList<Double> velocity = new ArrayList<>();
	ArrayList<String> name = new ArrayList<>();

	// overloaded functions provide a specific bot's parameter
	// or a list of parameter's for all bots
	
	public ArrayList<Double> getBearing()	{
		return bearing;
	}
	
	public ArrayList<Double> getDistance()	{
		return distance;
	}
	
	public ArrayList<Double> getEnergy()	{
		return energy;
	}
	
	public ArrayList<Double> getHeading()	{
		return heading;
	}
	
	public ArrayList<Double> getVelocity()	{
		return velocity;
	}
	
	public ArrayList<String> getName()	{
		return name;
	}

	public double getBearing(int index)	{
		return bearing.get(index);
	}
	
	public double getDistance(int index)	{
		return distance.get(index);
	}
	
	public double getEnergy(int index)	{
		return energy.get(index);
	}
	
	public double getHeading(int index)	{
		return heading.get(index);
	}
	
	public double getVelocity(int index)	{
		return velocity.get(index);
	}
	
	public String getName(int index)	{
		return name.get(index);
	}
	
		// updates data for existing enemy or adds new enemy
	public void update(ScannedRobotEvent e)	{
		if(name.contains(e.getName()))	{
			int i = name.indexOf(e.getName());
			bearing.set(i, e.getBearing());
			distance.set(i, e.getDistance());
			energy.set(i, e.getEnergy());
			heading.set(i, e.getHeading());
			velocity.set(i, e.getVelocity());
			name.set(i, e.getName());
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
	
		// delete a robot from the database - probably because we killed it!
	public void remove(int index)	{
		bearing.remove(index);
		distance.remove(index);
		energy.remove(index);
		heading.remove(index);
		velocity.remove(index);
		name.remove(index);
	}
}