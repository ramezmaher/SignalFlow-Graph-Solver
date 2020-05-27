import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

public class GraphSolver {
	private ArrayList<LinkedList<Integer>> flowGraph;
	private HashMap<Point,String> edges;
	private int size;
	private ArrayList<String> paths = new ArrayList<String>();
	private ArrayList<String> loops = new ArrayList<String>();
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
    	calculateLoops();
    	removeDuplicates();
    	
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
    
    private void calculateLoops() {
    	for(int i=0 ; i<size ; i++) {
    		for(Integer j:flowGraph.get(i)) {
    			Queue<Integer> q = new LinkedList<Integer>();
        		HashSet<Integer> set = new HashSet<Integer>();
        		q.add(i);
        		q.add(j);
    		    Cloops(q,set,j);
    		}
    	}
    }
    
    private void Cloops(Queue<Integer> q,HashSet<Integer> s,int i) {
    	if(q.peek() == i) {
    		StringBuilder sb = new StringBuilder();
    		while(q.size() > 1) {
    		int k = q.poll();
    		int j = q.peek();
    		sb.append(edges.get(new Point(k,j)));
    		sb.append("*");
    	    }
    		sb.deleteCharAt(sb.length()-1);
    		loops.add(sb.toString());
    		return;
        } 
    	if(s.contains(i)) {
    		return;
    	}
    	s.add(i);
    	for(Integer j:flowGraph.get(i)) {
    		Queue<Integer> q1 = new LinkedList<Integer>(q);
    		HashSet<Integer> set = new HashSet<Integer>(s);
    		q1.add(j);
    		Cloops(q1,set,j);
    	}
    }
    
    public  int size() {
    	return size;
    }
    
    private void removeDuplicates() {
    	HashSet<String> set = new HashSet<String>();
    	for(int i=0; i<loops.size() ; i++) {
    		char[] c = loops.get(i).toCharArray();
    		Arrays.sort(c);
    	    String s= new String(c);
    		if(set.contains(s)) {
    			loops.remove(i);
    			i--;
    		}
    		else set.add(s);
    	}
    }
    
    public void printGraph() {
    	for(int i=0 ; i<size ; i++) {
    		for(Integer j:flowGraph.get(i)) {
    			Point p = new Point(i,j);
    			System.out.println(i+"--"+edges.get(p)+"-->"+j);
    		}
    	}
    }
    
    public void printloops() {
    	for(String s: loops)
    		System.out.println(s);
    }
    
    public void printPaths() {
    	for(String s: paths)
    		System.out.println(s);
    }
    
}
