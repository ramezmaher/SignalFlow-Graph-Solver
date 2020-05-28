import java.awt.Color;
import java.awt.Graphics;

public class DrawNode implements DrawShapes {
	
	private Graphics g; 
	private int x,y;
	private int NodeID;
	
	public DrawNode(Graphics canvas,int X,int Y,int id) {
		this.g = canvas;
		this.x = X;
		this.y = Y;
		this.NodeID=id;
	}

	@Override
	public void draw() {
		g.fillOval(x, y, 20, 20);
	}
	
	public int getID() {
		return NodeID;
	}
	
	public boolean isInside(int px,int py) {
		double d = Math.sqrt(Math.pow(px-x,2)+Math.pow(py-y,2)); 
		if(d>20)
			return false;
		else return true;
	}

	@Override
	public int getType() {
		return 1;
	}
	

}
