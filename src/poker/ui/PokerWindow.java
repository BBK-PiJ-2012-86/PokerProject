package poker.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

public class PokerWindow {

	private JFrame frmPlayPoker;
	@SuppressWarnings("unused")	//not done yet..
	private PokerWindowModel model = new PokerWindowModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerWindow window = new PokerWindow();
					window.frmPlayPoker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlayPoker = new JFrame();
		frmPlayPoker.setTitle("Play Poker!");
		frmPlayPoker.setBounds(100, 100, 550, 400);
		frmPlayPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlayPoker.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 25, 503, 115);
		frmPlayPoker.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 231, 514, 120);
		frmPlayPoker.getContentPane().add(panel_1);
		
		JButton btnAnalyse = new JButton("Analyse");
		btnAnalyse.setBounds(110, 182, 89, 23);
		frmPlayPoker.getContentPane().add(btnAnalyse);
		
		JButton btnSwap = new JButton("Swap");
		btnSwap.setBounds(220, 182, 89, 23);
		frmPlayPoker.getContentPane().add(btnSwap);
		
		JButton btnGetResult = new JButton("Get Result");
		btnGetResult.setBounds(333, 182, 89, 23);
		frmPlayPoker.getContentPane().add(btnGetResult);
		
		JLabel lblInstruction = new JLabel("Instruction");
		lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction.setBounds(10, 161, 503, 14);
		frmPlayPoker.getContentPane().add(lblInstruction);
		
		JLabel lblDealersHand = new JLabel("Dealer's hand:");
		lblDealersHand.setBounds(10, 206, 81, 14);
		frmPlayPoker.getContentPane().add(lblDealersHand);
		
		JLabel lblYourHand = new JLabel("Your hand:");
		lblYourHand.setBounds(10, 11, 68, 14);
		frmPlayPoker.getContentPane().add(lblYourHand);
		
		JLabel lblCheckResult = new JLabel("Check Result");
		lblCheckResult.setBounds(10, 146, 189, 14);
		frmPlayPoker.getContentPane().add(lblCheckResult);
	}
}
