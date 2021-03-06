import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Engine {
	
	private ArrayList<DrawShapes> shapesArr = new ArrayList<DrawShapes>();
	private int NumberOfNodes=0;
	
	public void newNode(Graphics g,int x,int y) {
		shapesArr.add(new DrawNode(g,x,y,NumberOfNodes));
		NumberOfNodes++;
	}
	public void newEdge(Graphics g,int x1,int y1,int x2,int y2,int node1,int node2,String val) {
		Point p = new Point(node1,node2);
		shapesArr.add(new DrawEdge(g,x1,y1,x2,y2,p,val));
	}
	public void refresh() {
		for(DrawShapes d: shapesArr) {
			d.draw();
		}
	}
	public void clear() {
		shapesArr.clear();
		NumberOfNodes=0;
	}
	public int ThisNode(int x,int y) {
		for(DrawShapes s: shapesArr) {
			if(s.getType() == 1) {
				if(s.isInside(x, y)) 
					return s.getID();
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return shapesArr.isEmpty();
	}
	
	public String solve() {
		String[][] str = new String[NumberOfNodes][NumberOfNodes];
		
		for(int i=0;i<NumberOfNodes;i++) {
			for(int j=0;j<NumberOfNodes;j++) {
				str[i][j]=null;
			}
		}
		
		for(DrawShapes d: shapesArr) {
			if(d.getType()==0) {
				Point p=d.getPoints();
				str[p.x][p.y] = d.getWeight();
			}
		}
		
		GraphSolver g = new GraphSolver(str);
		String s = new String();
		s=g.solveGraph();
		return s;
	}
	
}
