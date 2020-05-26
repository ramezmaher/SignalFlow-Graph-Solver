import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.Point;

public class GraphSolver {
	private ArrayList<LinkedList<Integer>> flowGraph;
	private HashMap<Point,String> edges;
	private int size;
	private ArrayList<String> paths;
	private ArrayList<String> loops;
    public GraphSolver(String[][] graph) {
    	size = graph.length;
    	flowGraph = new ArrayList<LinkedList<Integer>>();
    	edges = new HashMap<Point,String>();
    	for(int i=0 ; i<size ;i++) {
    		flowGraph.add(new LinkedList<Integer>());
    		for(int j=0; j<size;j++){
    			if(graph[i][j] != null) {
    				flowGraph.get(i).add(j);
    				edges.put(new Point(i,j),graph[i][j]);
    			}
    		}
    	}
    }
    
    private void calculatePaths() {
    	paths = new ArrayList<String>();
    }
    
    public  int size() {
    	return size;
    }
     
    
    public void printGraph() {
    	for(int i=0 ; i<size ; i++) {
    		for(Integer j:flowGraph.get(i)) {
    			Point p = new Point(i,j);
    			System.out.println(i+"--"+edges.get(p)+"-->"+j);
    		}
    	}
    }
    
}
