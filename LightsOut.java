import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LightsOut implements ActionListener{
	// Declare and initialize components
	// Random object to get random number
	Random random = new Random();
	//make game frame
	JFrame frame = new JFrame();
	// Panel that we will add game frame to
	JPanel title_panel = new JPanel();
	// Button panel to add all the squares to
	JPanel button_panel = new JPanel();
	// Title textfield
	JLabel textfield = new JLabel("Turn the lights black!");
	// Array of JButtons
	JButton[][] buttons;
	// set godmode boolean to false
	boolean god = false;
	//godmode button to turn godmode on
	JButton godMode = new JButton();
	// clicked integer
	int clicked = 0;
	// level integer that takes the int from the mode 
	int level;

	LightsOut(String mode){
		// level is equal to the integer at the first index of the mode string
		// ex if string is '3x3', level would be 3
		level = Integer.parseInt( "" + mode.charAt(0));
		// clicked is equal to level^2
		// we are assuming all buttons are clicekd
		clicked = level*level;
		// Buttons array initialized using the level int
		buttons = new JButton[level][level];

		// customize frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// customize textfield colour, fontsize and alighnment
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Serif",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setOpaque(true);
		// Customize title panel
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		// customize button panel
		button_panel.setLayout(new GridLayout(level,level));
		button_panel.setBackground(new Color(150,150,150));

		// For each button row
		for (int i = 0; i < buttons.length; i++) {
			// for each button column
			for (int j = 0; j < buttons[0].length; j++) {
				// set current column to a new jbutton
				buttons[i][j] = new JButton();
				// add this button to the panel of buttons
				button_panel.add(buttons[i][j]);
				// set focusable and add actionlistener
				buttons[i][j].setFocusable(false);
				buttons[i][j].addActionListener(this);
				// get random integer between 0-1 inclusive
				int lighton = random.nextInt(2);
				// if number is 1
				if (lighton == 1) {
					// light is on so set the color to yellow
					buttons[i][j].setBackground(Color.yellow);
				}
				else{
					// otherwise the light is off so set it to black
					buttons[i][j].setBackground(Color.black);
					// minus 1 from clicked since one of them is not yellow/clicked
					clicked--;
				}
			}
		}
		
		// add the textfield to the titlepanel
		title_panel.add(textfield);
		// add the titlepanel and button panel to the frame
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		// make it so user can't resize frame
		frame.setResizable(false);
		// customize godMode text, fontsize, and alignment
		godMode.setBounds(0,0,10,50);
		godMode.setBackground(Color.red);
		godMode.setText("God Mode");
		godMode.setFont(new Font("MV Boli",Font.BOLD,40));
		// add action listener to godmode
		godMode.addActionListener(this);
		// add godmode to the frame
		frame.add(godMode,BorderLayout.SOUTH);

	}
	// click method
	public void click(JButton button){
		// if the button is yellow
		if (button.getBackground() == Color.yellow){
			//make it black
			button.setBackground(Color.black);
			// minus one from clicked
			clicked--;
		// otherwise it must be black
		} else{
			// so make it yellow
			button.setBackground(Color.yellow);
			// add one to clicked
			clicked++;
		}
	}
	// click helper method
	//takes in the grid of buttons and the position of the button being clicked
	public void clickHelper(JButton [][] matrix, int row, int col){
		// click the current button
		click(matrix[row][col]);
		// if god mode has not been actiavted
		if(!god){
			// click the row above if its in the grid
			if(row-1 >=0){
				click(matrix[row-1][col]);
			}
			// click the row below if its in the grid
			if(row+1 <=level-1){
				click(matrix[row+1][col]);
			}
			// click the column to the left if its in the grid
			if(col-1 >=0){
				click(matrix[row][col-1]);
			}
			// click the column to the right if its in the grid
			if(col+1 <=level-1){
				click(matrix[row][col+1]);
			}
		}

	}
	// check if user has won
	public void check() {
		// if all the butons have been clicked/ are black
		if(clicked == 0){
			// JOptionPane with win message
			JOptionPane.showMessageDialog(null, "You have won! Press OK to return to Start Menu" 
                    , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
			// Make the game frame invisible
			frame.setVisible(false);
			// Send the user back to start menu
			Main startmenu = new Main();
		}
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the user clicks the godmode button
		if(godMode == e.getSource()){
			// if godmode was off/false
			if(!god){
				// make the background green
				godMode.setBackground(Color.GREEN);
				// make the boolean true (so it is turned on)
				god = true;
				// show a JOptionPane of information on how godmode wrks
				JOptionPane.showMessageDialog(null, "God Mode has been turned on." +
				"\n Now only each square you click will change colour. To turn it off click the button again" 
                    , "God Mode", JOptionPane.INFORMATION_MESSAGE);
			// otherwise if god mode was on
			} else {
				// change the background from green to red
				godMode.setBackground(Color.red);
				// make the boolean false (off)
				god = false;
			}
		// otherwise the user clicked one of the buttons on the grid
		} else {
			// for each row in the buttons grid
			for (int i = 0; i < buttons.length; i++) {
				// for each col in each row
				for (int j = 0; j < buttons[0].length; j++) {
					// when we find the button that was clicked
				if(e.getSource()==buttons[i][j]) {
					// send the buttons grid, and the position of the button that was clicked to the clickhelper method
					clickHelper(buttons, i, j);
					// check if user has won
					check();
					}
				}			
			}
		}
		
	}

}