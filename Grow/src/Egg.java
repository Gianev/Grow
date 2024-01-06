import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Egg {

	//setting up all the variables and objects that will be used
	private int xPos, yPos, width, height;
	private Image imgEgg;
	private Random rnd;
	
	/**
	 * Creates the egg object with a default egg image, width, height and randomized x coordinate and y coordinate
	 */
	public Egg() {
		rnd = new Random();
		imgEgg = new ImageIcon("images/egg.png").getImage();
		width = imgEgg.getWidth(null);
		height = imgEgg.getHeight(null);
		xPos = rnd.nextInt(900) + 100;
		yPos = rnd.nextInt(570) + 50;
		
	}
	
	/**
	 * 
	 * @return Returns the egg image
	 */
	public Image getImage() {
		return imgEgg;
	}
	
	/**
	 * 
	 * @return Returns the x coordinate of the egg
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return Returns the y coordinate of the egg
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * Allows user to set the x coordinate of the egg
	 * 
	 * @param x - int value that the x position of the egg will be equal to 
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to set the y coordinate of the egg
	 * 
	 * @param y - int value that the y position of the egg will be equal to 
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
	/**
	 * 
	 * @return Returns the height of the egg
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * 
	 * @return Returns the width of the egg
	 */
	public int getWidth() 
	{
		return width;
	}
}
