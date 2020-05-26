import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

public class SignalFlowGraph_Solver {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignalFlowGraph_Solver window = new SignalFlowGraph_Solver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignalFlowGraph_Solver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSignalFlowGraph = new JLabel("Signal Flow Graph Solver");
		lblSignalFlowGraph.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignalFlowGraph.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 29));
		lblSignalFlowGraph.setBounds(261, 13, 396, 62);
		frame.getContentPane().add(lblSignalFlowGraph);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(42, 150, 850, 322);
		frame.getContentPane().add(panel);
		
		JButton btnNode = new JButton("Node");
		btnNode.setBounds(201, 112, 97, 25);
		frame.getContentPane().add(btnNode);
		
		JButton btnEdge = new JButton("Edge");
		btnEdge.setBounds(629, 112, 97, 25);
		frame.getContentPane().add(btnEdge);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.setBounds(417, 112, 97, 25);
		frame.getContentPane().add(btnSolve);
	}
}
