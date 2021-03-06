import java.awt.*;
import java.util.ArrayList;
public class Label extends Item {
  private Point startingPoint;
  private String text = "";
  public Label(Point point) {
    startingPoint = point;
  }
  public void addCharacter(char character) {
    text += character;
  }
  public void removeCharacter() {
    if (text.length() > 0) {
      text = text.substring(0, text.length() - 1);
    }
  }
  public boolean includes(Point point) {
    return distance(point, startingPoint) < 10.0;
  }
  public void render() {
    uiContext.draw(this);
  }
  public String getText() {
    return text;
  }
  public Point getStartingPoint() {
    return startingPoint;
  }
@Override
public void move(Point point) {
	// TODO Auto-generated method stub
	
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