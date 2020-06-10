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
	private HashMap<String,HashSet<Integer>> loopNodes = new HashMap<String,HashSet<Integer>>();
	private ArrayList<TwoString> bs = new ArrayList<TwoString>();
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
    	CalcLoopNodes();
    	calcNoTouch();
    	
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
    
    private void CalcLoopNodes() {
    	for(String s: loops) {
    		HashSet<Integer> set = new HashSet<Integer>();
    		int i=0;
    		StringBuilder st = new StringBuilder();
    		while(i<s.length()) {
    			if(s.charAt(i) == '*') {
    				i++;
    				String str = st.toString();
    				for(Point p : edges.keySet()) {
    					if(edges.get(p).compareTo(str) == 0) {
    						if(!set.contains(p.x))
    							set.add(p.x);
    						if(!set.contains(p.y))
    							set.add(p.y);
    					}
    				}
    				st.delete(0, st.length());
    			}
    			else {
    				st.append(s.charAt(i));
    				i++;
    			}
    		}
    		String str = st.toString();
			for(Point p : edges.keySet()) {
				if(edges.get(p) == str) {
					if(!set.contains(p.x))
						set.add(p.x);
					if(!set.contains(p.y))
						set.add(p.y);
				}
			}
    		loopNodes.put(s, set);
    	}
    }
    
    private void calcNoTouch() {
    	for(int i=0 ; i<loops.size() ;i++) {
    		HashSet<Integer> set1 = loopNodes.get(loops.get(i));
    		for(int j=i+1; j<loops.size() ; j++) {
    			LinkedList<Integer> set2 = new LinkedList<Integer>(loopNodes.get(loops.get(j)));
    			boolean flag =false;
    			for(Integer k: set2) {
    				if(set1.contains(k)) {
    					flag = true;
    					break;
    				}
    			}
    			if(!flag) {
    				TwoString ts = new TwoString(loops.get(i),loops.get(j));
    				bs.add(ts);
    			}
    		}
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
    
    public void printNums() {
    	for(String s:loopNodes.keySet()) {
    		System.out.print(s+" ");
    		for(Integer i:loopNodes.get(s)) {
    			System.out.print(i+" ");
    		}
    		System.out.println(" ");
    	}
    }
    
    private String getDenominator()
	{
		StringBuilder denominator = new StringBuilder();
		denominator.append("(1-(");
		for (int i = 0; i < loops.size() ; i++)
		{
			denominator.append("(");
			denominator.append(loops.get(i));
			denominator.append(")+");
		}
		if (denominator.charAt(denominator.length()-1) == '(')
			denominator.delete(denominator.length()-1, denominator.length());
		denominator.setCharAt(denominator.length()-1,')');
		denominator.append("+(");
		for (TwoString s : bs)
		{
			denominator.append("(");
			denominator.append(s.getS1());
			denominator.append("*");
			denominator.append(s.getS2());
			denominator.append(")+");
		}
		if (denominator.charAt(denominator.length()-1) == '(')
			denominator.delete(denominator.length()-1, denominator.length());
		denominator.setCharAt(denominator.length()-1, ')');
		denominator.append(")");
		
		return denominator.toString();
	}
    
    private ArrayList<String> getLoopEdges(String edge) {
    	StringBuilder st = new StringBuilder();
    	ArrayList<String> arr = new ArrayList<String>();
    	int i=0;
    	while(i<edge.length()) {
    		if(edge.charAt(i) == '*') {
    			i++;
				String str = st.toString();
				arr.add(str);
				st.delete(0, st.length());
    		}
    		else {
    			st.append(edge.charAt(i));
    			i++;
    		}
    	}
    	String str = st.toString();
		arr.add(str);
		return arr;
    }
    
    private ArrayList<String> getLoopsNonTouchingWith(String s){
    	ArrayList<String> list1 =  getLoopEdges(s);
    	ArrayList<String> as = new ArrayList<String>();
    	for(String str:loops) {
    		boolean flag = true;
    		ArrayList<String> list2 =  getLoopEdges(str);
    		for(String st:list2) {
    			if(list1.contains(st)) {
    				flag=false;
    				break;
    			}
    		}
    		if(flag) {
    			as.add(str);
    		}
    	}
    	return as;
    }
    
    private String getNumerator()
	{
		StringBuilder numerator = new StringBuilder();
		
		numerator.append("(");
		for (int i = 0; i<paths.size(); i++)
		{
			numerator.append("(");
			numerator.append(paths.get(i));
			numerator.append(")(1-(");
			for (String deltaK : getLoopsNonTouchingWith(paths.get(i)))
			{
				numerator.append(deltaK);
				numerator.append("+");
			}
			if (numerator.charAt(numerator.length()-1) == '(')
				numerator.delete(numerator.length()-4, numerator.length());
			else
				numerator.setCharAt(numerator.length()-1, ')');
			numerator.append(")+");
		}
		numerator.setCharAt(numerator.length()-1, ')');
		
		return numerator.toString();
	}
    
    public void printPaths() {
    	for(String s: paths)
    		System.out.println(s);
    }
    
    public void printNoTLoop() {
    	for(TwoString s: bs) {
    		System.out.println(s.getS1()+" "+s.getS2());
    	}
    }
    
    public String solveGraph() {
    	String denominator = getDenominator();
    	String numerator = getNumerator();
    	StringBuilder st = new StringBuilder();
    	st.append(numerator);
    	st.append("/");
    	st.append(denominator); 
    	return st.toString();
    }
    
}
