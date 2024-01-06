import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;


public class Perch {
//setting up my variables
	private Image imgPerch,imgRight,imgLeft;
	
	private int xPos,yPos,width,height,speed;
	private Random rnd;


	private Direction dir;
	
	/**
	 * Creates a perch object with default image, width, and height as well as randomized speed x position and y position
	 */
	public Perch()
	{
		Random rnd = new Random ();
		
		xPos = rnd.nextInt(670);
		yPos = rnd.nextInt(600) + 50;
		
		imgRight = new ImageIcon("images/PerchRight.png").getImage();
		imgLeft = new ImageIcon("images/PerchLeft.png").getImage();
		
		speed = rnd.nextInt(7) + 1;
		
		int rando = rnd.nextInt(2);
		if (rando == 0)
		{
			dir = Direction.EAST;
			imgPerch = imgRight;
		}
		else {
			dir = Direction.WEST;
			imgPerch = imgLeft;
		}
		width = imgPerch.getWidth(null);
		height = imgPerch.getHeight(null);
	}
	
	/**
	 * 
	 * @return perch's image
	 */
	public Image getImage() {
		return imgPerch;
	}
	
	/**
	 * 
	 * @return perch's x position
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return perch's y position
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return perch's width
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return perch's height
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Allows the perch to move based on what the direction is and what the speed iss
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
	 * Tells the class where the perch is facing
	 * @param b boolean that tells the class if the perch is facing right or left
	 */
	public void isRight(boolean b) 
	{
		if (b == true) {			
			imgPerch = imgRight;
			dir = Direction.EAST;
		}
		else if (b == false) {
			imgPerch = imgLeft;
			dir = Direction.WEST;
		}
	}
	
	/**
	 * Allows user to change the perch's x position
	 * @param x an int that the x position will be equal to 
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to change the perch's y position
	 * @param y an int that the y position will be equal to 
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
}