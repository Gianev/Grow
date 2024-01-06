import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class JellyFish {
//setting up my variables
	private Image imgJelly;
	private int xPos,yPos,width,height;
	private Random rnd;
	

	private Direction dir;
	
	/**
	 * Creates a jelly fish object with a default x position y position width height and image
	 */
	public JellyFish()
	{
//		Random rnd = new Random ();
		
	xPos = 500;
	yPos = 50;
		
		imgJelly = new ImageIcon("images/JellyFish2.gif").getImage();
	
		width = imgJelly.getWidth(null);
		height = imgJelly.getHeight(null);
	}
	
	
//	public void respawn() {
//		rnd = new Random();
//		yPos = 0;
//		xPos = rnd.nextInt(700);
//		
//	}
	
	/**
	 * 
	 * @return the image of the jelly fish
	 */
	public Image getImage() {
		return imgJelly;
	}
	
	/**
	 * 
	 * @return an int value that is the xposition of the jelly fish
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return an int value that is the y position of the jelly fish
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return an int value that is the width of the jelly fish
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return an int value that is the height of the jelly fish
	 */
	public int getHeight() 
	{
		return height;
	}
	
	
//	public void move() {
//	yPos = yPos + 10;
//	
//	}
	
	/**
	 * Allows user to set x position
	 * @param x an int that the x position will be equal to
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to set y position
	 * @param y an int that the y position will be equal to
	 */
	public void setY(int y) 
	{
		yPos = y;
	}


	
	
}