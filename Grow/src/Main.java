
//import all necessary packages
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;

//implement listeners so i can control the game
public class Main extends JPanel implements MouseMotionListener, MouseListener, KeyListener, ActionListener{

	//initialize objects, arrays, variables publically
	private ParrotFish[] PF;
	private ImageIcon imgBackground;
	private Timer t, dragonspawn, startup;
	private Player P;
	private GreatWhiteShark[] GWS;
	private Perch[] Perch;
	private Bird b;
	private Random rnd;
	private JellyFish j;
	private String killer;
	private boolean isRight, summonbeast,beastalive = false,jellyfishdead = false,showmessage, badeggalive,dragonalive = false;
	private boolean stinging = false, showdeath = false, showwin;
	private boolean[] PFalive, Perchalive, eggalive;
	private int PFdead,Perchdead,numtimes,rando = 6,eggseaten = 0, deaths = 0,startmsg=0,sharkseaten = 0, showconclusion = 0;
	private TheBeast tb;
	private Egg[] egg;
	private Egg badegg;
	private Dragon drag;
	private Boss boss;
	private Prism prism;
	
	public static void main(String[] args) {

		new Main();

	}
	
	public Main() {
	
		//Setting up an array of 5 parrot fish as well as a corresponding array to tell if each parrot fish is alive
	PF = new ParrotFish[5];
	PFalive = new boolean[5];
	
	//initializing them
	for(int i = 0; i < PF.length; i++)
	{
		PF[i] = new ParrotFish();
		PFalive[i] = true;
	}
	
	//setting up an array of perches and their corresponding booleans to tell if they are alive or not 
	Perchalive = new boolean[10];
	Perch = new Perch[10];
	//initializing them
	for(int i = 0; i < Perch.length; i++)
	{
		Perch[i] = new Perch();
		Perchalive[i] = true;
	}
	
	//initializing bird, jellyfish,random,beast,dragon and boss
	b = new Bird();
	j = new JellyFish();
	rnd = new Random();
	tb = new TheBeast();
	drag = new Dragon();
	boss = new Boss();
	
	//setting up and initializing my array of sharks
	GWS = new GreatWhiteShark[4];
	for(int i = 0; i < GWS.length; i++)
	{
		GWS[i] = new GreatWhiteShark();
	}
	//setting up and initializing array of eggs and their corresponding boolean array
	egg = new Egg[4];
	eggalive = new boolean[4];
	for(int i = 0; i < egg.length; i++)
	{
		egg[i] = new Egg();
		eggalive[i] = true;
	}
	
	//initialize the bad egg or the dragon's egg and the prism
	badegg = new Egg();
	prism = new Prism();
	//initializing player 
	P = new Player();
	//initializing focusable and listeners otherwise i cant use keyboard timer or mouse
	this.addKeyListener(this);
	this.addMouseMotionListener(this);
	this.addMouseListener(this);
	this.setFocusable(true);
	
	//making my background
	imgBackground = new ImageIcon("images/Background.jpg");
	
	//creating jframe, it's title, it's size, making it exitable, unresizablem centred on screen, and visible
	JFrame frame = new JFrame();
	frame.setContentPane(this);
	frame.setTitle("Grow");
	frame.setSize(1280,750);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
		
	//setting up timers 
	t = new Timer(100, this);
	startup = new Timer(1, this);

	dragonspawn = new Timer(5000, this);
	//initializing booleans and start up timer which displays the welcoming j panels
	showmessage = false;
	badeggalive = true;
	startup.start();
	}
	
	public void paintComponent(Graphics g) {
	//setting up the graphics 2 d
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//putting up my background
		g2.drawImage(imgBackground.getImage(), 0, 0, this);
		//when show message is true (which is when the alert that mysterious eggs have spawned) then cycle through the array of eggs and the bad egg aka the dragon egg and put them in their x and y coordinates
		if(showmessage == true) {
		for(int i = 0; i< egg.length; i ++) {
			if(eggalive[i] == true) {
			g2.drawImage(egg[i].getImage(),egg[i].getX(), egg[i].getY(),  this);
			}
		}
		//as long as the bad egg is still existant and not all the regular eggs have been eaten, draw the badegg
		if(badeggalive == true && eggseaten < 4) {
		g2.drawImage(badegg.getImage(),badegg.getX(),badegg.getY(), this);
		
		}
		}
		
		//when the summonbeast boolean is true, draw the beast, this is activated with space bar
		if(summonbeast == true) {
		g2.drawImage(tb.getImage(),tb.getX(), tb.getY() - tb.getHeight()/2, this);
		}
		//draw the bird 
		g2.drawImage(b.getImage(),b.getX(), b.getY() - b.getHeight(),b.getWidth() , b.getHeight() , this);
		//while the jelly fish is alive draw it
		if(jellyfishdead == false) {
		g2.drawImage(j.getImage(),j.getX(), j.getY(),j.getWidth(), j.getHeight(), this);
		}
		//cycle throught the array of sharks and draw them and their x and y coordinates
		for(int i = 0; i< GWS.length; i ++) {
			
		g2.drawImage(GWS[i].getImage(),GWS[i].getX(), GWS[i].getY(),GWS[i].getWidth() , GWS[i].getHeight(), this);
			
		}
		
		//cycle throughout the perch array it's array and draw the perches that are still alive
		for(int i = 0; i< Perch.length; i ++) {
			if(Perchalive[i] == true) {
			g2.drawImage(Perch[i].getImage(),Perch[i].getX(), Perch[i].getY(),Perch[i].getWidth() , Perch[i].getHeight(), this);
			} 
			}
		
		//this loop checks if a perch has reached the ends of the screen, if so it changes the direction to the opposite way it was previously facing so that it starts moving the other way
		for(int i = 0; i < Perch.length; i++) {
			if (Perch[i].getX() + Perch[i].getWidth() > this.getWidth())
			{
				Perch[i].setX(this.getWidth() - Perch[i].getWidth());
				Perch[i].isRight(false);
			}
			if(Perch[i].getX() < 0) {
				Perch[i].setX(0);
				Perch[i].isRight(true);
			}
			}
		//draw the player at its x coordinate and y coordinate
		g2.drawImage(P.getImage(),P.getX(), P.getY(),P.getWidth() , P.getHeight(), this);
		
		//if the dragon is alive then draw the image at the x and y coordinate
		if(dragonalive == true) {
		g2.drawImage(drag.getImage(),drag.getX(), drag.getY(), this);
		}
		//this loop checks if any of the sharks has reached the end of the screen and then if it did, then the direction is swapped so that the shark starts moving the other way
		for(int i = 0; i < GWS.length; i++) {
		if (GWS[i].getX() + GWS[i].getWidth() > this.getWidth())
		{
			GWS[i].setX(this.getWidth() - GWS[i].getWidth());
			GWS[i].isRight(false);
		}
		if(GWS[i].getX() < 0) {
			GWS[i].setX(0);
			GWS[i].isRight(true);
		}
		}
		
		//using he perch array and it's corresponding array pf alive, it draws the perches at their x and y coordinates as long as theyre alive
		for(int i = 0; i< PF.length; i ++) {
			if(PFalive[i] == true) {
			g2.drawImage(PF[i].getImage(),PF[i].getX(), PF[i].getY(),PF[i].getWidth() , PF[i].getHeight(), this);
			} 
			}
		//this loop checks if any of the perches has reached the end of the screen and then if it did, then the direction is swapped so that the perch starts moving the other way
		for(int i = 0; i < PF.length; i++) {
			if (PF[i].getX() + PF[i].getWidth() > this.getWidth())
			{
				PF[i].setX(this.getWidth() - PF[i].getWidth());
				PF[i].isRight(false);
			}
			if(PF[i].getX() < 0) {
				PF[i].setX(0);
				PF[i].isRight(true);
			}
			}

		//if the fish collides with any shark (this deals with when the player is facing right)
	for(int i = 0; i < GWS.length; i ++)
	{
			
		if(P.getX() + P.getWidth() > GWS[i].getX() + GWS[i].getWidth() / 3 &&
				P.getX() + P.getWidth() < GWS[i].getX() + GWS[i].getWidth() - (GWS[i].getWidth()/3) &&
				P.getY() + P.getHeight() / 2 > GWS[i].getY() + GWS[i].getWidth()/3 &&
				P.getY() + P.getHeight() / 2 < GWS[i].getY() + GWS[i].getHeight() - (GWS[i].getHeight()/3) && isRight == true ) 
		
		{
			// if the user has eaten all the regular eggs, that means the player is big enough to eat sharks so the shark is moved somewhere else to avoid collision checking more than once
			//this also adds to the sharks eaten
			if( eggseaten > 3)
			{

			GWS[i].setY(9000);
			sharkseaten++;
			}
			//if there are still regular eggs existing, that means the player is not big enough to eat sharks meaning they die which triggers showdeath and
			// which displays the game over panel. Killer is = to shark so that the panel can tell the user the shark killed them
			else if( eggseaten < 4) 
			{
				showdeath = true;
				killer = "shark";
			}
		}
		
		//same thing as previous code, except this deals with when the user is facing left
		if(P.getX() > GWS[i].getX() + GWS[i].getWidth() / 3 &&
				P.getX()  < GWS[i].getX() + GWS[i].getWidth() - (GWS[i].getWidth()/3) &&
				P.getY() + P.getHeight() / 2 > GWS[i].getY() + GWS[i].getWidth()/3 &&
				P.getY() + P.getHeight() / 2 < GWS[i].getY() + GWS[i].getHeight() - (GWS[i].getHeight()/3) && isRight == false) 
		{
			if(eggseaten > 3)
				{
		sharkseaten ++;
				GWS[i].setY(9000);
				}
			else if (eggseaten < 4) 
				{
				showdeath = true;
				killer = "shark";
				
				}
		}
		
	}
	
	
	for(int i = 0; i < Perch.length; i ++)
	{
			//checks collision of player with any perch and when the player is facing right
		if(P.getX() + P.getWidth() > Perch[i].getX() && P.getX() + P.getWidth() < Perch[i].getX() + Perch[i].getWidth() &&
				P.getY() + P.getHeight() / 2 > Perch[i].getY() + Perch[i].getHeight() / 4 && P.getY() + P.getHeight() / 2 < Perch[i].getY() + Perch[i].getHeight() &&isRight == true ) 
	
		{
			//if the amount of parrot fishes dead is 5 meaning player ate all 5 and is big enough to eat perches
			//then add to perchdead add to player size and set the direction to east so that the player image remained facing right and move perch somewhere else so it only checks the collision once
			if(PFdead == 5) {
				Perchdead++;
		Perchalive[i] = false;
		P.setSize(4, 4, Direction.EAST);
		Perch[i].setY(1000);
			}
			//if the amount of perches dead or eaten is less than 5 then the player is not big enough to eat perches
			//so show death is true so game over panel is shown and killer = perch so the panel could tell the player a perch killed them
			else if (PFdead < 5) {
				killer = "Perch";
				showdeath = true;
			}
		}
			
		//same as last code except this deals with when the player is facing left
		if(P.getX()  > Perch[i].getX() && P.getX() < Perch[i].getX() + Perch[i].getWidth() &&
				P.getY() + P.getHeight() / 2 > Perch[i].getY() + Perch[i].getHeight() / 4 && P.getY() + P.getHeight() / 2 < Perch[i].getY() + Perch[i].getHeight() &&isRight == false ) 
		{
			if(PFdead == 5) {
				Perchdead++;
		Perchalive[i] = false;
		//only different code, this sets the direction west as it was facing left so that the image would remain facing left
		P.setSize(4 , 4, Direction.WEST);
		Perch[i].setY(1000);
			}
			else if (PFdead < 5) {
				killer = "Perch";
				showdeath = true;
			}
		}
		
	}
	
	
	//cycle through parrot fish array
	for(int i = 0; i < PF.length; i ++)
	{
			//checks collision of parrot fish and player when player is facing right
		if(P.getX() + P.getWidth() > PF[i].getX() + PF[i].getWidth() / 3 &&
				P.getX() + P.getWidth() < PF[i].getX() + PF[i].getWidth() - (PF[i].getWidth()/3) &&
			P.getY() + P.getHeight() / 2 > PF[i].getY() + PF[i].getWidth()/3 &&
				P.getY() + P.getHeight() / 2 < PF[i].getY() + PF[i].getHeight() - (PF[i].getHeight()/3) && isRight == true) 
		{
			//sets the pfalive to it's corresponding parrotfish to false so that it is not drawn anymore
			//increases size and tells the player class direction east so that the player image remains facing right
			//moves parrot fish away to prevent the collision check happening more than once and add to PFdead
		PFalive[i] = false;
		P.setSize(4, 4, Direction.EAST);
		PF[i].setY(1000);
		PFdead = PFdead + 1;
		}
		
		//same code as above except dealing with when player was facing left
		if(P.getX() > PF[i].getX() + PF[i].getWidth() / 3 &&
				P.getX()  < PF[i].getX() + PF[i].getWidth() - (PF[i].getWidth()/3) &&
				P.getY() + P.getHeight() / 2 > PF[i].getY() + PF[i].getWidth()/3 &&
				P.getY() + P.getHeight() / 2 < PF[i].getY() + PF[i].getHeight() - (PF[i].getHeight()/3) && isRight == false) 
		{
		PFalive[i] = false;
		//west because the player was facing left
		P.setSize(4 , 4, Direction.WEST);
		PF[i].setY(1000);
		PFdead = PFdead + 1;
		}
		
	}

		//checks collision between the beast and the jelly fish, if they collide jelly fish is moved away to prevent multiple collision checks and jellyfish dead is true bc it is dead
//	if(j.getX() + j.getWidth() /2 > tb.getX() && j.getX() + j.getWidth() /2 < tb.getX() + tb.getWidth() &&
//			j.getY() + j.getHeight() / 2 > tb.getY() && j.getY() + j.getHeight() / 2 < tb.getY() + tb.getWidth()) {

	if(tb.getX() + tb.getWidth() >  j.getX() && tb.getX() + tb.getWidth() < j.getX() + j.getWidth() &&
			tb.getY() + tb.getHeight() / 3  > j.getY () && tb.getY() + tb.getHeight() / 3  < j.getY() + j.getHeight() ) {
		j.setX(9000);
		jellyfishdead = true;
	}
	
	//checks if the beast has left the room
	if(tb.getX() > this.getWidth() )
	{//if the jelly fish was not caught, this puts the beast back at the end of the room on the left, makes the beast not alive and summonbeast false to allow the user to reclick the space bar and try to catch the jelly fish
		if(jellyfishdead == false)
		{
		tb.setX(0 - tb.getWidth());
		beastalive = false;
		summonbeast = false;
		}
		//if it was caught, show message is true to show a jpane on guidance on what to do next
		else if (jellyfishdead== true)
		{	
			showmessage = true;	
		}
	}
	
	//if the dragonleaves the room, the dragonalive boolean becomes false so that the game no it doesnt exist anymore and it is moved else where to prevent it from being collided with player
	if(drag.getX() + drag.getWidth() < 0) {
		dragonalive = false;
		drag.setX(9000);
	}
	//checks if the user has collided with any of the regular egg when the player is facing right
	for(int i = 0; i < egg.length; i++) {
		
		if(P.getX() + P.getWidth() > egg[i].getX() && P.getX() + P.getWidth() < egg[i].getX() + egg[i].getWidth() 
				&& P.getY() + P.getHeight() / 2 > egg[i].getY() && P.getY() + P.getHeight() / 2 <egg[i].getY() + egg[i].getHeight() && isRight == true && showmessage == true) {
			//eggseaten goes up, the egg is moved elsewhere, it's corresponding boolean is set false so the game knows it is dead and the player grows, direction.east is given as the player was facing right so that the player remians facing right
			eggseaten++;
			egg[i].setX(9000);
			eggalive[i] = false;
			P.setSize(4, 4, Direction.EAST);
			
		}
		//same as previous code except this deals with when the player was facing left
		if(P.getX() > egg[i].getX() && P.getX()  < egg[i].getWidth() + egg[i].getX() 
			&&	P.getY() + P.getHeight() / 2 > egg[i].getY() && P.getY() + P.getHeight() / 2 <egg[i].getY() + egg[i].getHeight() && isRight == false && showmessage == true) {
			
			eggseaten++;
			egg[i].setX(9000);
			eggalive[i] = false;
			P.setSize(4, 4, Direction.WEST);
		}
	}
		
//checks if the user has collided with the bad egg or dragons egg while the player is facing right
	if(P.getX() + P.getWidth() > badegg.getX() && P.getX() + P.getWidth() < badegg.getX() + badegg.getWidth() 
			&& P.getY() + P.getHeight() / 2 > badegg.getY() && P.getY() + P.getHeight() / 2 < badegg.getY() + badegg.getHeight() && isRight == true && showmessage == true) {
		//moves egg away to prevent multi collision detecting, the badeggalive is false to let the game know its not existant anymore
		//play roar sound effect to warn player the dragon is coming, put dragon at the end of the screen plus width so it smoothly enters the screen and dragon alive is true so the game knows the dragon is in the room
		badegg.setX(9000);
		badeggalive = false;
		File Roar = new File("Roar.WAV");
		PlaySound(Roar);
		drag.setX(this.getWidth() + drag.getWidth());
		dragonalive = true;
	}
	//same as previous code but it deals with when the player is facing left
	if(P.getX() > badegg.getX() && P.getX()  < badegg.getX() + badegg.getWidth() 
	&& P.getY() + P.getHeight() / 2 > badegg.getY() && P.getY() + P.getHeight() / 2 < badegg.getY() + badegg.getHeight() && isRight == false && showmessage == true) {

		badegg.setX(9000);
		badeggalive = false;
		File Roar = new File("Roar.WAV");
		PlaySound(Roar);
		dragonalive = true;
		drag.setX(this.getWidth() + drag.getWidth());
}
	//checks the collision between player and jelly fish
	if(P.getX() + P.getWidth() / 2 > j.getX() && P.getX() + P.getWidth() / 2 < j.getX() + j.getWidth()
		&& P.getY() + P.getHeight()/ 2 >j.getY() && P.getY() + P.getHeight()/ 2  < j.getY() + j.getHeight()) {
		//if it collided, stinging was true( importance of stinging will be in later code)
		stinging = true;
		
		}
	else {
		//if it isnt colliding, the stinging is false
		stinging = false;
	}
	
	//if the player and bird collide, showdeath becomes true so the game over panel is shown and the killer is = bird to tell player that a bird killed them
	if(P.getX() + P.getWidth() / 2 > b.getX() && P.getX() + P.getWidth() / 2 < b.getX() + b.getWidth() &&
			P.getY() + P.getHeight() / 2 < b.getY() && P.getY() + P.getHeight() / 2 > b.getY() - b.getHeight()) {
		
		showdeath = true;
		killer = "bird";
	}
	
	//if the player and dragon collide, showdeath becomes true so the game over panel is shown and the killer is = bird to tell player that a dragon killed them
	if(P.getX() + P.getWidth() / 2 > drag.getX()  && P.getX() + P.getWidth() / 2 < drag.getX() + drag.getWidth() && P.getY() + P.getHeight() / 2 > drag.getY() && P.getY() + P.getHeight() / 2 < drag.getY()+ drag.getHeight() && dragonalive == true) {
		showdeath = true;
		killer = "dragon";
	}
	
	//if the dragon is not existant and the sharks eaten is bigger than 3 meaning all sharks were eaten then the
	//boss and prism are drawn
	if(dragonalive == false && sharkseaten > 3)
	{
		g2.drawImage(boss.getImage(), 930, boss.getY(), this);
	
		g2.drawImage(prism.getImage(), prism.getX(), boss.getY() + boss.getHeight() / 3, this);
		prism.setY(boss.getY() + boss.getHeight() / 3);
		//if the prism reaches the left end of the screen then the direction of the prism is set to east so the prism starts moving right
		if(prism.getX() < 0) {
			prism.setDirection(Direction.EAST);
			
		}
		//if the prism reaches the right end of the screen then the direction of the prism is set to west so the prism starts moving left
		if(prism.getX() + prism.getWidth() > this.getWidth()) {
			prism.setDirection(Direction.WEST);
			
		}
		
	
	}
	//when the boss reaches the top of the screen then the direction is set to south to it moves down
	if(boss.getY() < 0) {
		boss.setDirection(Direction.SOUTH);
	}
	//if the boss reaches the bottom of the screen then the direction is set to north so that it moves up
	if(boss.getY() + boss.getHeight() > this.getHeight()) {
		boss.setDirection(Direction.NORTH);
	}
	//if the prism and player collide then showwin is true to display the jpanel that will congratulate the player on their win
	if(prism.getX() + prism.getWidth()/2 > P.getX() && prism.getX() + prism.getWidth()/2 < P.getX() + P.getWidth()
	&& prism.getY() + prism.getHeight() / 2 > P.getY() &&  prism.getY() + prism.getHeight() / 2 < P.getY() + P.getHeight() && sharkseaten > 3 && dragonalive == false ) {
		showwin = true;
	}
	//if the player has eaten 4 eggs then the bad egg if it still exists is moved else where
	if(eggseaten == 4) {
		badegg.setX(9000);
	}
	
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	//checks source of timer
	if (e.getSource() == t) {
	//moves the parrot fish, bird, sharks,perches
		for(int i = 0; i < PF.length; i++) {
			PF[i].move();
			}
		b.move();
		for(int i = 0; i < GWS.length; i++) {
		GWS[i].move();
		}
		
		for(int i = 0; i < Perch.length; i++) {
			Perch[i].move();
			}
		//lines 516 - 528 make jelly fish follow player, if the player. x is more right, the jelly fish moves more right, and vice versa
		//and if it is higher up then the jelly fish moves higher up and vice versa
		if(j.getX() + (j.getWidth() / 2) < P.getX() + (P.getWidth() / 2)) {
			j.setX(j.getX() + 2);
		}
		else {
			j.setX(j.getX()-2); 
		}
		
		if(j.getY() + (j.getHeight() / 2) < P.getY() + (P.getHeight() / 2)) {
			j.setY(j.getY() + 2);
		}
		else {
			j.setY(j.getY()-2); 
		}
		//randomly picks if the bird should respawn
		int respawn = rnd.nextInt(1000);
		//if respawn == 0 and the bird is off the scren then respawn bird (they randomly get an x pos and art put at the top of the screen - bird's height
	if(respawn == 0 && b.getY() > this.getHeight())
		{
	b.respawn();
		}
	
	//if all the perches are dead so 10, inofrmation and guidance is given to the player through jpanes
	if(Perchdead == 10) {		
		JOptionPane.showMessageDialog(null, "You have eaten all the Perches!  ", "Uh oh", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "However, the the beast has awoken to join the party"
				+ "\n Luckily for you, you can call it by pressing space bar, as the beast only eats jelly fish. Make sure you aim the beast's snout to hit the jelly fish", "Good news", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "If the beast disappears into the abyss, call another one in"
				+ "\n You'll need to get rid of the jelly fish before advancing to make things easier", "Good news", JOptionPane.INFORMATION_MESSAGE);
	
		// add to perch dead so that the messages dont pop up again
		Perchdead++;
		
	}
	
	//this shows when the beast is no longer on screen and the jelly fish is gone... this is where show message comes to use as it is activated when the criteria i listed are satisfyed
	if(showmessage == true && numtimes < 1) {
		JOptionPane.showMessageDialog(null, "Mysterious eggs! It appears eggs of the hawks have dropped from the sky"
				+ " you may be able to put these to use, eat them so you can become bigger and eat the sharks ", "Eggs unlocked", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Be careful, one of these eggs belongs to a vicious dragon!\n"
				+ "If you disturb her egg, you will hear her roar and she'll appear from the east ", "Heads up", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "She is locked onto your scent, similar to sharks who smell blood, she can sense you!\n"
				+ "Block your presence by spamming your right click on your mouse, this will help you blend your presence into the surrounding/n"
				+ "if she no longer senses you, she will stop following you! ", "Advice", JOptionPane.INFORMATION_MESSAGE);
		//this goes up so that the messages dont show multiple time
		numtimes++;
	
	}
	//when you die, this shows up and it tells the user they died and what killed them then the game ends
	if(showdeath== true) {
		JOptionPane.showMessageDialog(null, "It looks like you've been  by " + killer 
			, "Game Over", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	//this is when the player wins, they are congratulated and the game ends
	if(showwin== true) {
		JOptionPane.showMessageDialog(null, "Im impressed you made it through the game... Congrajulachins\n I mean congratulations! Thank you for playing Giancarlo's weird game called Grow!  "
			, "You win", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	

	//when summon beast is true that means the player clicked the space bar then make the beast move
	if(summonbeast == true) {
	tb.move();
	}
	//when the dragon is existant on the screen then make the dragon move
	if(dragonalive == true) {
		
		drag.move();
	}
	//when all the sharks are dead and the dragon no longer exists then make the boss and prism move as well
	if(sharkseaten > 3 && dragonalive == false) {
		boss.move();
		prism.move();		

		//show the second last j pane
		if(showconclusion == 0) {
			JOptionPane.showMessageDialog(null, "The anceient golisopod has awoken... Dont worry though, its just here to give you the ocean prism as reward for your win.\n No traps involved, just retrieve it and you win! "	, "Almost there!", JOptionPane.INFORMATION_MESSAGE);
			//make this go up so the message doesnt appear more than once
			showconclusion++;

		}
		
	}
	//rando is explained later on in the code, but when this does not = 0 then the dragon's y is locked onto the player's y
	if(rando != 0) {
	drag.setY(P.getY());
	}	
		repaint();
		
	}
	
	//this makes it so that theres a delay for the dragon to spawn so that users have time to decide their position
	 if (e.getSource() == dragonspawn) {
			dragonalive = true;
		}
	 //these are the start up messages, meaning the opening messages
	 if(e.getSource() == startup) {
		
		 if(startmsg == 0) {
			 
			 ImageIcon imgIcon = new ImageIcon("images/ParrotFish.gif");
			 ImageIcon imgIcon2 = new ImageIcon("images/PerchLeft.png");
			 ImageIcon imgIcon3 = new ImageIcon("images/JellyFish.gif");
			 ImageIcon imgIcon4 = new ImageIcon("images/Bird2.gif");
			 
			JOptionPane.showMessageDialog(null, "Hello! Welcome to grow! Here your goal is to eat everything inside this reef"
					+ " and make sure you dont get eaten along the way", "Start up message", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "To start off try and eat the blue fish called parrot fish", "Dialog", JOptionPane.PLAIN_MESSAGE, imgIcon);
			JOptionPane.showMessageDialog(null, "Once you eat all of those go and eat the perches which are these green fish", "Dialog", JOptionPane.PLAIN_MESSAGE, imgIcon2);
			JOptionPane.showMessageDialog(null, "You may want to stay away from the jelly fish, though it wont harm you, it will slow you down significantly, making it easier for you to die", "Dialog", JOptionPane.PLAIN_MESSAGE, imgIcon3);
			JOptionPane.showMessageDialog(null, "Also stay away from the huntsman hawks, they will kill you as well", "Dialog", JOptionPane.PLAIN_MESSAGE, imgIcon4);
			//starts timer for everything to move 
			t.start();
			//make this go up so that the j panes dont show more than once
			startmsg ++;
		 }
	 }
	 
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//if they enter the d key
		if (e.getKeyCode() == KeyEvent.VK_D) {
		//set direction to east, make P.move however if the player is stinging or in contact with jellyfish send the boolean true to player class to tell the class player is getting stun, this makes player move slower
			P.setDirection(Direction.EAST);
			if(stinging == true) {
				P.move(true);
			}
			//if they arent being stung then send the class false in move to let the class know it is not being stung and it can move normally
			else if (stinging == false) {
				P.move(false);
			}
		
			isRight = true;
			if (P.getX() + P.getWidth() >= this.getWidth()) {
				P.setX(this.getWidth() - P.getWidth());
			}
		}
		
		//same as d key except it is oriented to go down
		if (e.getKeyCode() == KeyEvent.VK_S) {
		//makes direction south
			P.setDirection(Direction.SOUTH);
			if(stinging == true) {
				P.move(true);
			}
			else if (stinging == false) {
				P.move(false);
			}
			if (P.getY() + P.getHeight() >= this.getHeight()) {
				P.setY(this.getHeight() - P.getHeight());
			}

		}
		//same as other keys but oriented to go left
		if (e.getKeyCode() == KeyEvent.VK_A) {
			P.setDirection(Direction.WEST);
			if(stinging == true) {
				P.move(true);
			}
			else if (stinging == false) {
				P.move(false);
			}
			isRight = false;
			if (P.getX() <= 0) {
				P.setX(0);
			}
			
			//same as other keys but oriented to go up
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			P.setDirection(Direction.NORTH);
			if(stinging == true) {
				P.move(true);
			}
			else if (stinging == false) {
				P.move(false);
			}
		
			if (P.getY() <= 0) {
				P.setY(0);
			}
		}
		//only usable when all perches are dead and the beast isnt currently inside the game
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
	//if they click space the beast is summoned and tells the room beast is alive and the coordinates are set on where the player is on the y axis
			if (Perchdead > 9 && beastalive == false)
			{
				summonbeast = true;
				beastalive = true;
				tb.setY((P.getY() + P.getHeight() / 2) - tb.getHeight() / 2);
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
	//this only happens when the dragon is existant
	if(dragonalive == true && rando != 0) {
		//everytime the user clicks right click a rando number is generated, when the number isnt 0 then the dragon continues to lock onto the player, once 0 is achieved they can no longer press right click
		rando = rnd.nextInt(5);
	}

	}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	//this is helpful for testing so i left it hear if you need it 
		P.setX(e.getX() - P.getWidth() / 2);
		P.setY(e.getY() - P.getHeight() / 2);
		
		if (P.getY() + P.getHeight() >= this.getHeight()) {
			P.setY(this.getHeight() - P.getHeight());
		}
		if (P.getX() + P.getWidth() >= this.getWidth()) {
			P.setX(this.getWidth() - P.getWidth());
		}
		if (P.getY() <= 0) {
			P.setY(0);
		}
		if (P.getX() <= 0) {
			P.setX(0);
		}
		repaint();
	
	}

	static void PlaySound(File Sound) {
		//this allows for the playing of sound effects
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		}
		catch(Exception e){
			
		}
	}
}
