import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.awt.Point;

public class GraphSolver {
	private ArrayList<LinkedList<Integer>> flowGraph;
	private HashMap<Point,String> edges;
	private int size;
	private ArrayList<String> paths = new ArrayList<String>();;
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
    	calculatePaths();
    }
    
    private void calculatePaths() {
    	HashSet<Integer> set = new HashSet<Integer>();
    	set.add(0);
    	Cpaths("*",0,set);
    }
    
    private void Cpaths(String s,int i,HashSet<Integer> visited) {
    	if(i == size-1) {
    		StringBuilder sb = new StringBuilder();
    		sb.append(s);
    		sb.deleteCharAt(0);
    		sb.deleteCharAt(0);
    		paths.add(sb.toString());
    		return;
    	}
    	for(Integer j:flowGraph.get(i)) {
    		if(!visited.contains(j)) {
    		HashSet<Integer> set = new HashSet<Integer>(visited);
    		set.add(j);
    		StringBuilder sb = new StringBuilder();
    		sb.append(s);
    		sb.append("*");
    		sb.append(edges.get(new Point(i,j)));
    		Cpaths(sb.toString(),j,set);
    		}
    	}
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
    public void printPaths() {
    	for(String s: paths)
    		System.out.println(s);
    }
    
}
