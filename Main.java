import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;


public class Main extends JFrame implements WindowListener {
    // Declare and initialize components
    // main panel for the main menu
    JPanel mainPanel = new JPanel();
    // Add the instructions and play buttons
    JButton instructions = new JButton("INSTRUCTIONS");
    JButton play = new JButton("PLAY");
    // text for which mode the user wants
    JLabel whatmode = new JLabel("Mode:");
    // imageicon and jlabel for background image
    ImageIcon img1 = new ImageIcon("img1.jpg");
    JLabel background = new JLabel();
    
    //main frame we will add the mainPanel to
    JFrame mainFrame = new JFrame("Lights Out");
    // JComboBox for user to select which mode they want
	JComboBox<String> modes;


    public static void main(String [] args)
    {
        //Creating a Main object
        new Main();
    }

    //Main Constructor
    public Main() {
        // set background image and add it
        background.setIcon(img1);
        this.add(background);
        // String array with all the modes a user can choose
		String modesString[] = {"4x4", "5x5", "6x6", "9x9"};
        // Set the string array to the modes JComboBox
		modes = new JComboBox(modesString);
		// Make the JCombobox default value the first index of the array
        modes.setSelectedIndex(1);
        //Setting bounds to change the x,y,width,height of J objects
        play.setBounds(80,120,250,70);
        instructions.setBounds(370,120,250,70);
        whatmode.setBounds(250, 190, 250, 70);
        modes.setBounds(325, 200, 50, 50);
        background.setBounds(40, 0, 600, 200);
        //Set fonts and font sizes of the J Objects
        play.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        instructions.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        whatmode.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        

        mainPanel.setLayout(null);

        // Set the button colour and text color for the buttons
        instructions.setBackground(Color.black);
        instructions.setForeground(Color.yellow);
        play.setBackground(Color.black);
        play.setForeground(Color.yellow);
        
        //Add action listeners to J Objects
        play.addActionListener(new ButtonListener());
        instructions.addActionListener(new ButtonListener());
        modes.addActionListener(new ButtonListener());

        //Add labels, buttons, textfields to panel
        mainPanel.add(instructions);
        mainPanel.add(play);
        mainPanel.add(background);
		mainPanel.add(modes);
        mainPanel.add(whatmode);
        
		// add a windowlistener to this frame 
        // so we can see when actions are done to the window
        mainFrame.addWindowListener(this);

        //Customize frame
        mainFrame.setContentPane(mainPanel);
        mainFrame.setSize(700, 300);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public class ButtonListener implements ActionListener
    {

        //Whenever an action is performed
        public void actionPerformed(ActionEvent e)
        {
            // if the user presses the play button
            if(play ==e.getSource()) {
                // Launch the lights out game in a seperate file
                // Pass the current index of the JCombobox to the lights out game
                // ex if user is on 3x3, pass '3x3'
                LightsOut lightsOut = new LightsOut((String) modes.getSelectedItem());
                // make the main menu invisible
                mainFrame.setVisible(false);
            }

            //Otherwise if the instructions button is used
            else if(instructions ==e.getSource()){
                // Show a JOptionPane with instructions on how to play the game
                JOptionPane.showMessageDialog(null,"Based on Tiger Electronics 1995 Game." +
                        "\nYou will be greated with a grid of squares some yellow, others black." +
                        "\nWhen you click a square, the square and its adjacent squares (not corners) will turn to the opposite color." +
                        "\nThe goal of the game is to turn all the squares black","Instructions", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    // If the user closes the window
    public void windowClosing(WindowEvent e)
    {
        // JOptionPane with yes/no options
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        //if yes
        if(option == 0)
        {
            //close the program
            System.exit(0);
        }

        //if no
        else
        {
            // Don't do anything
            mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}

