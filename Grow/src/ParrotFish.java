import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;


public class ParrotFish {
//setting up variables
	private Image imgParrotFish,imgRight,imgLeft;
	
	private int xPos,yPos,width,height, speed;
	private Random rnd;


	private Direction dir;
	
	/**
	 * Creates a parrot fish object with a default image width, and height as well as randomized x position y position direction and speed
	 */
	public ParrotFish()
	{
		 
		Random rnd = new Random ();
		speed = rnd.nextInt(7) + 1;
		xPos = rnd.nextInt(700);
		yPos = rnd.nextInt(600);
		
		imgRight = new ImageIcon("images/ParrotFishRight.gif").getImage();
		imgLeft = new ImageIcon("images/ParrotFish.gif").getImage();
		
	
		
		int rando = rnd.nextInt(2);
		if (rando == 0)
		{
			dir = Direction.EAST;
			imgParrotFish = imgRight;
		}
		else {
			dir = Direction.WEST;
			imgParrotFish = imgLeft;
		}
		width = imgParrotFish.getWidth(null);
		height = imgParrotFish.getHeight(null);
	}
	
	/**
	 * 
	 * @return the image of the parrot fish
	 */
	public Image getImage() {
		return imgParrotFish;
	}
	
	/**
	 * 
	 * @return the x position of the parrot fish
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return the y position of the parrot fish
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return the width of the parrot fish
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return the height of the parrot fish
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Allows the parrot fish to move either left or right depending on the speed and direction
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
	 * 
	 * @param b a boolean that tells the class where the paroot fish is facing
	 */
	public void isRight(boolean b) 
	{
		if (b == true) {			
			imgParrotFish = imgRight;
			dir = Direction.EAST;
		}
		else if (b == false) {
			imgParrotFish = imgLeft;
			dir = Direction.WEST;
		}
	}
	
	/**
	 * Allows user to change the x position of the parrot fish 
	 * @param x an int that the x position will be equal to 
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to change the y position of the parrot fish 
	 * @param y an int that the y position will be equal to 
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
}