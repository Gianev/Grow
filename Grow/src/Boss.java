import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class Boss {

	private Image imgBoss;
	private Direction dir;
	private int xPos,yPos,width,height;

	
	public Boss()
	{
		imgBoss = new ImageIcon("images/Golsipod.png").getImage();
		width = imgBoss.getWidth(null);
		height = imgBoss.getHeight(null);	
		xPos = 9000;
		dir = Direction.SOUTH;
		
	}
	
	
	public Image getImage() {
		return imgBoss;
	}
	
	public int getX() 
	{
		return xPos;
	}
	
	public int getY() 
	{
		return yPos;
	}
	
	public int getWidth() 
	{
		return width;
	}
	
	public int getHeight() 
	{
		return height;
	}
	
	public void setX(int x) 
	{
		xPos = x;
	}
	
	public void setY(int y) 
	{
		yPos = y;
	}
	
	public void setDirection(Direction d) 
	{
		dir = d;
	}
	
	public void move() {
		
		if (dir == Direction.SOUTH) { 
		yPos = yPos + 8;
		}
		else if (dir == Direction.NORTH) {
			yPos = yPos - 8;
		}
		
	}
	
}