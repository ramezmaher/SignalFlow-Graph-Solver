import java.awt.Point;

public interface DrawShapes {
    public void draw();
    public int getType();
    public boolean isInside(int x,int y);
    public int getID();
    public Point getPoints();
    public String getWeight();
}
