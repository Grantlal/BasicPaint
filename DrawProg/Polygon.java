//Jeremy Nelson-Stalmer
//Polgyon class 
//move function needs work, will move but manipulates shape.

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Polygon extends Item {
	private Point origin = null; // initial click point
	private Point lastPoint; // the final click Point 
	private Stack<Line> sides; // Holds the many sides of polygon
	private boolean isComplete; // flag to determine if complete/incomplete
								
	private Point currentlyClicked;
	
	private ArrayList<Point> controlPoints = new ArrayList<Point>();

	//Polygon contains origin, flag, and stack structure for sides
	public Polygon(Point origin) {
		this.origin = origin;
		isComplete = false;
		sides = new Stack<Line>();
	}

	/**
	 * // Copy constructor public Polygon(Point origin, LinkedList<Line>
	 * listofPoints){ this(origin); sides.addAll(listofPoints); }
	 */

	public boolean isDrawingComplete() {
		return isComplete;
	}

	// Get the first point
	public Point getOrigin() {
		return origin;
	}

	public Line getLastLineDrawn() {
		return sides.peek();
	}

	public void setCurrentlyClicked(Point currentlyClicked) {
		this.currentlyClicked = currentlyClicked;
		controlPoints.add(currentlyClicked);
	}

	public Point getCurrentlyClicked() {
		return currentlyClicked;
	}

	public boolean removeLastLineDrawn() {
		if (isPolygonEmpty()) {
			return false;
		} else {
			sides.pop();
			return true;
		}
	}

	public boolean isPolygonEmpty() {
		return sides.isEmpty();
	}

	public boolean isOriginEmpty() {
		return origin == null;
	}

	public Point getLastPoint() {
		if (isPolygonEmpty())
			return origin;
		else
			return getLastLineDrawn().getPoint2();
	}

	private boolean pointsAreClose(Point point1, Point point2) {
		double x_difference = point1.getX() - point2.getX();
		double y_difference = point1.getY() - point2.getY();

		if (x_difference < 0)
			x_difference *= -1;
		if (y_difference < 0)
			y_difference *= -1;

		return x_difference <= 10 && y_difference <= 10;
	}

	public void addPoint(Point point) {
		if (isPolygonEmpty() && isOriginEmpty()) {
			origin = point;
			return;
		}
		if (isPolygonEmpty() && !isOriginEmpty()) {
			sides.push(new Line(origin, point));
			return;
		} else if (!isPolygonEmpty()) {
			sides.push(new Line(getLastPoint(), point));
		}
		if (pointsAreClose(origin, getLastPoint())) {
			isComplete = true;
		}
	}

	public Iterator<Line> getAllLinesDrawn() {
		return sides.iterator();
	}

	public String toString() {
		return "Polygon from " + sides.toString();
	}

	@Override
	public void move(Point point) {

		int diffX = (int) (point.getX() - origin.getX());
		int diffY = (int) (point.getY() - origin.getY());

		Iterator iter = getAllLinesDrawn();

		while (iter.hasNext()) {

			Line side = (Line) iter.next();
			side.getPoint1().setLocation(side.getPoint1().getX() + diffX,
					side.getPoint1().getY() + diffY);
			side.getPoint2().setLocation(side.getPoint2().getX() + diffX,
					side.getPoint2().getY() + diffY);

		side.move(point);

		
			
			
		}

	}

	@Override
	public boolean pointClickedOnItem(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean includes(Point point) {
		Iterator<Line> iter = getAllLinesDrawn();
		while (iter.hasNext()) {
			Line line = iter.next();
			if (line.includes(point))
				return true;
		}
		return false;
	}

	public void render() {
		uiContext.draw(this);
	}

	@Override
	public ArrayList<Point> addControlPoints() {

		for (Line line : sides) {
			
			controlPoints.add(line.getPoint1());

		}
		return controlPoints;
	}
}
