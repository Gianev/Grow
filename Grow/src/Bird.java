import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class Bird {

	//setting up necessary variables and objects and image
	private Image imgBird;
	private int xPos,yPos,width,height;
	private Random rnd;
	

	private Direction dir;
	
	
	/**
	 * Creates bird object with default values such as the y position, width, and height. Also creates a random x position
	 */
	public Bird()
	{
		Random rnd = new Random ();
		
	xPos = rnd.nextInt(700);
	yPos = 0;
		
		imgBird = new ImageIcon("images/Bird2.gif").getImage();
		width = imgBird.getWidth(null);
		height = imgBird.getHeight(null);
	}
	
	/**
	 * Places the bird object at y = 0 and assigns it a new random x coordinate
	 */
	public void respawn() {
		rnd = new Random();
		yPos = 0;
		xPos = rnd.nextInt(700);
		
	}
	
	/**
	 * 
	 * @return Returns the image of the bird
	 */
	public Image getImage() {
		return imgBird;
	}
	
	/**
	 * 
	 * @return Returns the x coordinate of the bird
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return Returns the y coordinate of the bird
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return Returns the width of the bird
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return Returns the height of the bird
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * 
	 *Moves the bird object pixels down 6 pixels
	 */
	public void move() {
	yPos = yPos + 3;
	
	}
	
	/**
	 * Allows you to change the x coordinate of the bird
	 * 
	 * @param x = int value which will become the x position
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows you to change the y coordinate of the bird
	 * 
	 * @param y = int value which will become the y position
	 */
	public void setY(int y) 
	{
		yPos = y;
	}


	
	
}