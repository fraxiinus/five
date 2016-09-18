package five;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 7851847865177905267L;
	private JPanel contentPane;

	// Launch the application.
	public static void main(String[] args) {
		Board game = new Board();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(game);
					frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	// Create the frame.
	public MainWindow(Board game) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		splitPane.setDividerSize(0);
		splitPane.setEnabled(false);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panelGame = new JPanel();

		splitPane.setRightComponent(panelGame);
		panelGame.setLayout(new GridLayout(0, 9, 1, 1));

		JPanel panelButton = new JPanel();
		splitPane.setLeftComponent(panelButton);
		GridBagLayout gbl_panelButton = new GridBagLayout();
		gbl_panelButton.columnWidths = new int[]{0, 0};
		gbl_panelButton.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelButton.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButton.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButton.setLayout(gbl_panelButton);

		JLabel turnLabel = new JLabel("BLACK'S TURN");
		GridBagConstraints gbc_turnLabel = new GridBagConstraints();
		gbc_turnLabel.gridx = 0;
		gbc_turnLabel.gridy = 2;
		panelButton.add(turnLabel, gbc_turnLabel);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String coor = e.getActionCommand();
				String coorx = coor.substring(0, 1);
				String coory = coor.substring(1,2);
				if (!game.gameWon()) {
					boolean placed = game.placeTile(Integer.parseInt(coorx), Integer.parseInt(coory));
					JButton pressedButton = (JButton)e.getSource();
					if (game.turn() == Turn.BLACK && placed) {
						turnLabel.setText("WHITE'S TURN");
						pressedButton.setBackground(Color.black);
					} else if (game.turn() == Turn.WHITE && placed) {
						turnLabel.setText("BLACK'S TURN");
						pressedButton.setBackground(Color.white);
					}
				} turnLabel.setText(game.turn() + " WON");
			}
		};

		JButton buttonArray[][] = new JButton[9][9]; 
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				buttonArray[i][j] = new JButton("");
				buttonArray[i][j].setActionCommand(i + "" + j);
				buttonArray[i][j].setBackground(Color.gray);
				panelGame.add(buttonArray[i][j]);
				buttonArray[i][j].addActionListener(actionListener);
			}
		}

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				turnLabel.setText("BLACK'S TURN");
				for (JButton[] b : buttonArray) {
					for (JButton b2 : b) b2.setBackground(Color.gray);
				} game.reset();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		};

		JButton startButton = new JButton("NEW GAME");
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.insets = new Insets(0, 0, 5, 0);
		gbc_startButton.gridx = 0;
		gbc_startButton.gridy = 0;
		startButton.addMouseListener(mouseListener);
		panelButton.add(startButton, gbc_startButton);

	}

}
