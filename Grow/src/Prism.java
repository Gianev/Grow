import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class Prism {
//setting up my variables
	private Image imgPrism;

	private int xPos,yPos,width,height;
private Direction dir;
	
	public Prism()
	{
		/**
		 * Creates the prism object with default direction, x position, height, width and image
		 */
		imgPrism = new ImageIcon("images/Prism.png").getImage();
		dir = Direction.WEST;
		width = imgPrism.getWidth(null);
		height = imgPrism.getHeight(null);	
		xPos = 950;
	}
	
	/**
	 * 
	 * @return returns the image of the prism
	 */
	public Image getImage() {
		return imgPrism;
	}
	
	/**
	 * 
	 * @return an int value that is equal to the prism's x position
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return an int value that is equal to the prism's y position
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return an int value equal to the prism's width
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return an int value equal to the prism's height
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Allows user to set the prism's x position
	 * 
	 * @param x an int value that the x position of the prism will become
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to set the prism's y position
	 * 
	 * @param x an int value that the y position of the prism will become
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
	/**
	 * Allows the prism to move 7 pixels to the left if facing west or 7 pixels to the right if facing east
	 */
	public void move() {
		
		if(dir == Direction.WEST) {
		xPos = xPos - 7;
		}
		
		if(dir == Direction.EAST) {
			xPos = xPos + 7;
		}
	}
	
	/**
	 * Allows user to set prism direction
	 * 
	 * @param d the direction you want the prism to face
	 */
	public void setDirection(Direction d)
	{
		dir = d;
	}
	
}