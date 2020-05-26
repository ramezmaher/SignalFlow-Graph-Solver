
public class main {

	public static void main(String[] args) {
		String[][] s = new String[4][4];
		for(int i=0 ; i<4 ; i++) {
			for (int j=0 ; j<4 ;j++)
				s[i][j] =null;
		}
		s[0][1] = "A10";
		s[1][2] = "A21";
		s[1][3] = "A31";
		s[2][2] = "A22";
		s[2][1] = "A12";
		s[2][3] = "A32";
		
		GraphSolver g = new GraphSolver(s);
		g.printGraph();
		
	}

}
