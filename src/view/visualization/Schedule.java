package view.visualization;

import java.util.LinkedList;
import java.util.Queue;

import utils.Point;

/**
 * @author Jay Doherty
 *
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
