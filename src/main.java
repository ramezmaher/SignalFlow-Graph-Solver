
public class main {

	public static void main(String[] args) {
		int l = 7;
		String[][] s = new String[l][l];
		for(int i=0 ; i<l ; i++) {
			for (int j=0 ; j<l ;j++)
				s[i][j] =null;
		}
		s[0][1] = "G1";
		s[1][2] = "G2";
		s[2][1] = "H1";
		s[2][3] = "G3";
		s[3][4] = "G4";
		s[4][5] = "G5";
		s[4][2] = "H3";
		s[4][3] = "H2";
		s[5][6] = "G6";
		
		GraphSolver g = new GraphSolver(s);
		System.out.println("Graph");
		g.printGraph();
		System.out.println(" ");
		System.out.println("paths");
		System.out.println(" ");
		g.printPaths();
		System.out.println(" ");
		System.out.println("loops");
		System.out.println(" ");
		g.printloops();
		
	}
}
