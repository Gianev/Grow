import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class TheBeast {
//setting up my variables
	private Image imgTheBeast;

	private int xPos,yPos,width,height;

	/**
	 * Creates a beast object with a default x position width height and image
	 */
	public TheBeast()
	{
		imgTheBeast = new ImageIcon("images/TheBeast.gif").getImage();
		width = imgTheBeast.getWidth(null);
		height = imgTheBeast.getHeight(null);	
		xPos = 9000;
		xPos = xPos - width;
	}
	
	/**
	 * 
	 * @return the image of the beast
	 */
	public Image getImage() {
		return imgTheBeast;
	}
	
	/**
	 * 
	 * @return a int value that equals the x position of the beast
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return a int value that equals the y position of the beast
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return a int value that equals the width of the beast
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return a int value that equals the height of the beast
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Allows for the beast to move 12 pixels to the right each time
	 */
	public void move() {
		xPos = xPos + 12;
	
	}
	
	/**
	 * 
	 * @param x - int value that the xPos will equal to
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * 
	 * @param y - int value that the yPos will equal to
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
	
}