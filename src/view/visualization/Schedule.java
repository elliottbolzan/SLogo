package view.visualization;

import java.util.LinkedList;
import java.util.Queue;

import utils.Point;

/**
 * @author Jay Doherty
 * This class encapsulates the part of the turtle that keeps track of where the turtle is going
 * and future destinations that are queued up for the turtle to move to once it arrives at its
 * current destination.
 */
public class Schedule {

	private Point myDestination;
	private Queue<Point> myFutureDestinations;
	
	protected Schedule() {
		myDestination = new Point(0, 0);
		myFutureDestinations = new LinkedList<Point>();
	}

	protected Point getDestination() {
		return myDestination;
	}
	
	protected void setDestination(Point destination) {
		myDestination = destination;
	}
	
	protected void addFutureDestination(Point destination) {
		myFutureDestinations.add(destination);
		myDestination = destination;
	}
	
	protected boolean hasAnotherDestination() {
		return !myFutureDestinations.isEmpty();
	}
	
	protected Point pollFutureDestination() {
		return myFutureDestinations.poll();
	}
}
