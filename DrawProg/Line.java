import java.awt.*;
import java.util.ArrayList;
public class Line extends Item {
  private Point point1;
  private Point point2;
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Line(Point point1) {
    this.point1 = point1;
  }
  public Line() {
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void render() {
    uiContext.draw(this);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }
@Override
public void move(Point point) {
	int diffX = (int) (point.getX() - point1.getX());
	int diffY = (int) (point.getY() - point1.getY());

	point1.setLocation(point1.getX() + diffX, point1.getY() + diffY);
	point2.setLocation(point2.getX() + diffX, point2.getY() + diffY);
	
}
@Override
public boolean pointClickedOnItem(Point point) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public ArrayList<Point> addControlPoints() {
	// TODO Auto-generated method stub
	return null;
}
}

