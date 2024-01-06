//import all necessary packs
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Player {
//setting up my variables
	private Image imgPlayer,imgRight,imgLeft;
	private int xPos,yPos,width,height, cheight,cwidth;
	private Direction dir;
	private boolean isRight, stinging;


	/**
	 * Creates the fish object with default width, height, direction, x position, y position, images
	 */
	public Player()
	{
		cwidth = 70;
		cheight = 50;
		dir = Direction.EAST;
		yPos = 650;
		xPos= 1000;
		Image scaledImage = new ImageIcon("images/PlayerFish2.png").getImage().getScaledInstance(cwidth,cheight, Image.SCALE_SMOOTH);
//i used cheight and xwidth as the scales because i want the fish to change sizes as the game progresses
		imgLeft = new ImageIcon(scaledImage).getImage();
		imgPlayer =  new ImageIcon(scaledImage).getImage();
		
		Image scaledImageRight = new ImageIcon("images/PlayerFishRight2.png").getImage().getScaledInstance(cwidth, cheight, Image.SCALE_SMOOTH);
		imgRight = new ImageIcon(scaledImageRight).getImage();
		
		width = imgPlayer.getWidth(null);
		height = imgPlayer.getHeight(null);
	}
	
	/**
	 * 
	 * @return the player's image
	 */
	public Image getImage() {
		return imgPlayer;
	}
	
	/**
	 * 
	 * @return the player's x position
	 */
	public int getX() 
	{
		return xPos;
	}
	
	/**
	 * 
	 * @return the player's y position
	 */
	public int getY() 
	{
		return yPos;
	}
	
	/**
	 * 
	 * @return the player's width 
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * 
	 * @return the player's height 
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Allows user to change the x position of the player
	 * 
	 * @param x an int value that the x position will be equal to
	 */
	public void setX(int x) 
	{
		xPos = x;
	}
	
	/**
	 * Allows user to change the y position of the player
	 * 
	 * @param x an int value that the y position will be equal to
	 */
	public void setY(int y) 
	{
		yPos = y;
	}
	
	/**
	 * Allows user to alter the size of the player
	 * 
	 * @param x an int value that will be added onto the player's width
	 * @param y an int value that will be added onto the player's height
	 * @param d the direction that the player will face
	 */
	public void setSize(int x, int y, Direction d)
			{
		cwidth = cwidth +x;
		cheight = cheight + y;
		dir = d;
		Image scaledImage = new ImageIcon("images/PlayerFish2.png").getImage().getScaledInstance(cwidth,
				cheight, Image.SCALE_SMOOTH);
		imgLeft = new ImageIcon(scaledImage).getImage();
//		imgPlayer =  new ImageIcon(scaledImage).getImage();
		
		Image scaledImageRight = new ImageIcon("images/PlayerFishRight2.png").getImage().getScaledInstance(cwidth, cheight, Image.SCALE_SMOOTH);
		imgRight = new ImageIcon(scaledImageRight).getImage();
		
		if (dir == Direction.EAST) {
			imgPlayer = imgRight;
		}
		else if (dir == Direction.WEST) {
			imgPlayer = imgLeft;
		}
		width = imgPlayer.getWidth(null);
		height = imgPlayer.getHeight(null);
			}
	
	/**
	 * Allows player to move in different directions
	 * 
	 * @param s a boolean to tell the user whether it's movement is being affected 
	 */
	public void move(boolean s) {
		stinging = s;
		if (dir == Direction.EAST) {
			imgPlayer = imgRight;
			if(stinging == false) {
			xPos += 10;
			}
			else if (stinging == true) {
				xPos += 5;
			}
		}
		else if (dir == Direction.NORTH) {
			
			if(stinging == false) {
				yPos -= 10;
				}
				else if (stinging == true) {
					yPos -= 5;
				}
			
		}
		else if (dir == Direction.SOUTH) {
			
			if(stinging == false) {
				yPos += 10;
				}
				else if (stinging == true) {
					yPos += 5;
				}
			
		}
		else {
			imgPlayer = imgLeft;
			if(stinging == false) {
				xPos -= 10;
				}
				else if (stinging == true) {
					xPos -= 5;
				}
		}
	}
	
	/**
	 * Allows user to set the direction of the player
	 * @param d the direction the player will face
	 */
	public void setDirection(Direction d) {
		dir = d;
		if (dir == Direction.EAST) {
			imgPlayer = imgRight;
		}
		else if (dir == Direction.WEST) {
			imgPlayer = imgLeft;
		}
	
	}
}