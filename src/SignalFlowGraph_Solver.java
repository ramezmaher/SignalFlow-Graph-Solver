import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class SignalFlowGraph_Solver {

	private JFrame frame;
	private int Type=0;
	private int X1,X2,Y1,Y2,node1,node2;
	private boolean makingEdge=false;
	private boolean newDraw=false;
	private Engine eng = new Engine();
	private JTextField textField;
	
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
		JToggleButton edgeBtn = new JToggleButton("Edge");
		JToggleButton nodeBtn = new JToggleButton("Node");
		textField = new JTextField();
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
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(newDraw) {
					panel.repaint();
					newDraw=false;
				}
				Graphics canvas = panel.getGraphics();
				X1=e.getX();
				Y1=e.getY();
				if(Type == 1) {
					eng.newNode(canvas, X1, Y1);
					eng.refresh();
					panel.paintComponents(canvas);
				}
			}
			
			public void mousePressed(MouseEvent e) {
				if(newDraw) {
					panel.repaint();
					newDraw=false;
				}
				X1=e.getX();
				Y1=e.getY();
				if(Type == 2) {
					node1=eng.ThisNode(X1, Y1);
					if(node1 >= 0) {
						makingEdge=true;
					}
			}
			}
			
			public void mouseReleased(MouseEvent e) {
				if(newDraw) {
					panel.repaint();
					newDraw=false;
				}
				Graphics canvas = panel.getGraphics();
				X2=e.getX();
				Y2=e.getY();
				if(makingEdge && Type==2 ) {
					node2=eng.ThisNode(X1, Y1);
					if(node2 >= 0) {
						String s=JOptionPane.showInputDialog("Insert relation between nodes");
						eng.newEdge(canvas, X1, Y1, X2, Y2, node1, node2, s);
						eng.refresh();
						panel.paintComponents(canvas);
						makingEdge=false;
					}
				}
			}
			
		});
		panel.setBackground(Color.WHITE);
		panel.setBounds(42, 150, 850, 322);
		frame.getContentPane().add(panel);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eng.isEmpty()) {
				String s = eng.solve();
				textField.setText(s);
				Type=0;
				edgeBtn.setEnabled(true);
				nodeBtn.setEnabled(true);
				eng.clear();
				newDraw=true;
				}
			}
		});
		btnSolve.setBounds(795, 112, 97, 25);
		frame.getContentPane().add(btnSolve);
		
		nodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nodeBtn.setEnabled(false);
				edgeBtn.setEnabled(true);
				Type = 1;
			}
		});
		nodeBtn.setBounds(42, 112, 137, 25);
		frame.getContentPane().add(nodeBtn);
		
		edgeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeBtn.setEnabled(false);
				nodeBtn.setEnabled(true);
				Type  = 2;
			}
		});
		edgeBtn.setBounds(190, 112, 137, 25);
		frame.getContentPane().add(edgeBtn);
		
		
		textField.setBounds(190, 520, 702, 62);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnswer.setBounds(42, 534, 114, 25);
		frame.getContentPane().add(lblAnswer);
	}
}
