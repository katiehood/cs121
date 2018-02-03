import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a summer evening in the front yard with traffic going by.
 *
 * @author BSU CS 121 Instructors
 * @author katiehood779
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = Color.black;

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(Color .GREEN);
		g.fillRect(0, 0, width, height);
		
		//Fill the top of the background with blue for the sky
		g.setColor(Color .BLUE);
		g.fillRect(0, 0, width, height/6);
		
		//Fill the mountains section
		g.setColor(Color .magenta);
		g.fillRect(0, height/6, width, height/3);
		
		//Fill the road, 2 lanes
		g.setColor(Color .darkGray);
		g.fillRect(0, height/3, width, (height*2)/6);
		
		//Fill the grass yard
		//g.setColor(Color .GREEN);
		//g.fillRect(0, (height*2)/6, width, height);
		
		//white picket fence line between grass and road
		g.setColor(Color .WHITE);
		g.fillRect(0, (height*2/3), width, height*(2/3)+10);
		
		
		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % width;

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen

		// This draws a red square.
		int squareSide = height / 5;
		int squareY = height / 2 - squareSide / 2;
		g.setColor(Color.RED);
		g.fillRect(xOffset, squareY, squareSide, squareSide);
		
		// This draws a red oval on top of the square.
		g.setColor(Color.RED);
		int circleDiameter = squareSide;
		int circleX = xOffset; 
		int circleY = squareY - circleDiameter/2; // we need to move it above the square.
		g.fillOval(circleX, circleY, circleDiameter, circleDiameter);
		Toolkit.getDefaultToolkit().sync();
		
		// This makes a stick guy.
		g.setColor(Color .orange);
		int circleM = height; 
		int circleN = width / 2;
		g.fillOval(circleM, circleN, circleDiameter / 3, circleDiameter / 3);
		Toolkit.getDefaultToolkit().sync();
		
		//ImageIcon avatar = new ImageIcon("myAvatar.png");
		//g.drawImage(avatar.getImage(), 350, 300, 30, 30, null);
		
		//This prints the title
		String str = "A Summer Evening";
		g.setFont(new Font("Serif", Font.BOLD, 24));
		
		// Get Font's metrics to allows us to figure out it's size
		FontMetrics metrics = g.getFontMetrics();
		
		//Title position
		int x = (10);
		int y = (height + metrics.getHeight())/10;

		g.setColor(Color.white);
		g.drawString(str, x, y);
		
		
		
		
		
		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
