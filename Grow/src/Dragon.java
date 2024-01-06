import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class Dragon {
//setting up necessary variables and image
	private Image imgDragon;

	private int xPos,yPos,width,height;

	/**
	 * Creates dragon object with a default image width and height
	 */
	public Dragon()
	{
		imgDragon = new ImageIcon("images/Dragon.gif").getImage();
		width = imgDragon.getWidth(null);
		height = imgDragon.getHeight(null);	
		
	}
	
	/**
	 * 
	 * @return Returns the image of the dragon
	 */
	public Image getImage() {
		return imgDragon;
	}
	
	/**
	 * 
	 * @return Returns x coordinate of the dragon
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return Returns the y coordinate of the dragon 
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return Returns the width of the dragon
	 */
	public int getWidth() 
	{
		return width;
	}
	
	
	/**
	 * 
	 * @return Returns the height of the dragon
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * 
	 * Moves the dragon 6 pixels to the left
	 */
	public void move() {
xPos = xPos - 6;
	
	}
	
	/**
	 * Allows you to set the x position of the dragon
	 * 
	 * @param x - int value that will become the x position of the dragon
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows you to set the y position of the dragon
	 * 
	 * @param y - int value that will become the y position of the dragon
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
	
}