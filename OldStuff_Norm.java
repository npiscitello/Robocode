/*

if(getGunHeat()==0)	{															// test if ready to fire
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
		out.println("dist1 = " + dist1 + "  dist2 = " + dist2);


*/