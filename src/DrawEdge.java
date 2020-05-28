import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DrawEdge implements DrawShapes {
	private Graphics g; 
	private int x1,y1,x2,y2;
	private Point p;
	private String weight;
	
	public DrawEdge(Graphics canvas,int X,int Y,int x,int y,Point nodes,String val) {
		this.g = canvas;
		this.x1 = X;
		this.y1 = Y;
		this.x2 = x;
		this.y2 = y;
		this.p = nodes;
		this.weight = val;
		g.setColor(Color.BLACK);
	}
	@Override
	public void draw() {
		g.drawLine(x1, y1, x2, y2);
	}
	@Override
	public int getType() {
		return 0;
	}
	@Override
	public boolean isInside(int x, int y) {
		return false;
	}
	@Override
	public int getID() {
		return -1;
	}

}
