import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class GreatWhiteShark {
// setting up the variables and objects and images i will be using
	private Image imgGreatWhiteShark,imgLeft,imgRight;

	private int xPos,yPos,width,height,speed;
	private Random rnd;
	
	private Direction dir;
	
	//Creates a shark with a default image, width, height, direction and randomized x position and y position
	public GreatWhiteShark()
	{
		Random rnd = new Random ();
		
	xPos = rnd.nextInt(700);
	yPos = rnd.nextInt(500);
	
	
		imgRight = new ImageIcon("images/GreatWhiteShark.gif").getImage();
		imgLeft = new ImageIcon("images/GreatWhiteSharkLeft.gif").getImage();
		imgGreatWhiteShark = new ImageIcon("images/GreatWhiteShark.gif").getImage();
		width = imgGreatWhiteShark.getWidth(null);
		height = imgGreatWhiteShark.getHeight(null);
		
		int rando = rnd.nextInt(2);
		if (rando == 0)
		{
			dir = Direction.EAST;
			imgGreatWhiteShark = imgRight;
		}
		else {
			dir = Direction.WEST;
			imgGreatWhiteShark = imgLeft;
		}
		speed = rnd.nextInt(1)+ 3;
	}
	
	/**
	 * 
	 * @return Returns the image of the shark
	 */
	public Image getImage() {
		return imgGreatWhiteShark;
	}
	
	/**
	 * 
	 * @return Returns the x position of the shark in ints
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return Returns the y position of the shark in ints
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return Returns the width of the shark in ints
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return Returns the height of the shark in ints
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Moves the shark 5 pixels to the right if the direction it is facing is east, but if it faces west the shark will move 5 pixels left
	 */
	public void move() {
		if(dir == Direction.EAST) {
			xPos = xPos + speed;
		}
		else if(dir == Direction.WEST) {
			xPos = xPos - speed;
		}
	
	}
	
	/**
	 * Allows user to change the x position of the shark
	 * 
	 * @param x - int value that the x position will be equal to 
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to change the y position of the shark
	 * 
	 * @param y - int value that the y position will be equal to 
	 */
	public void setY(int y) 
	{
		yPos = y;
	}

	/**
	 * Allows user to send the class information about what directiopn the shark is facing
	 * 
	 * @param b - boolean that determines what direction the shark is facing and determines what image of the shark to display
	 */
	public void isRight(boolean b) 
	{
		if (b == true) {			
			imgGreatWhiteShark = imgRight;
			dir = Direction.EAST;
		}
		else if (b == false) {
			imgGreatWhiteShark = imgLeft;
			dir = Direction.WEST;
		}
	}
	
	
	
	
}