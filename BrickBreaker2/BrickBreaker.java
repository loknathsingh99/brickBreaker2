import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JFrame;

class PDemo extends JPanel implements ActionListener , KeyListener
{
	ImageIcon ic1,ic2,ic3,ic4,reset,plusIc,minusIc,ck1,ck2;
	Image ball,back,bricks,block,craker1,craker2;
	int height;
	int width;
	int time;
	int height_of_box = 40;
	int width_of_box = 80;
	int x,y,xspeed,yspeed;
	int x1,y1,xspeed1,yspeed1;
	int x2,y2,xspeed2,yspeed2;
	int startx = 70;
	int starty = 40;
	int boxes =22;
	int hits = 0;
	int distx =150;
	int disty =0;
	int boxx[] = new int[50];
	int boxy[] = new int[50];
	int xb; // x position of brick
	int yb ;	// y position of brick 
	int wb ;  // width of brick
	int hb ;  // height of brick
	int size_of_ball;
	Timer timer;
	boolean start_game;
	JLabel label1,label2,label3,plus,minus;
	
	PDemo( int width, int height)
	{
		this.height = height;
		this.width = width;
		ic1 = new ImageIcon("ball.png");
		ic2 = new ImageIcon("b2.png");
		ic3 = new ImageIcon("back.jpg");
		ic4 = new ImageIcon("block.png");
		ck1 = new ImageIcon("ck2.gif");
		ck2 = new ImageIcon("ck3.gif");
		craker1 = ck1.getImage();
		craker2 = ck2.getImage();
		reset = new ImageIcon("reset.png");
		plusIc = new ImageIcon("plus.png");
		minusIc = new ImageIcon("minus.png");
		ball = ic1.getImage();
		bricks = ic2.getImage();
		back = ic3.getImage();
		block = ic4.getImage();
		start_game = false;
		int r1 = (int)Math.round(Math.random()*600 + 200);
		int r2 = (int)Math.round(Math.random()*200+360);
		r1 -= r1%10;
		r2 -= r2%10;
		x = r1;
		y = r2;
		// System.out.println(r1+"\t"+r2);
		
		xspeed = 10;
		yspeed = 10;
		// time = 25;
		time = 35;
		xb = width/2 -60 ; // x position of brick
		yb = height -70;	// y position of brick 
		wb = 120;  // width of brick
		hb = 30;  // height of brick
		size_of_ball =30;

		Font f = new Font("stencil",Font.BOLD, 55);
		setFont(f);
		// setBackground(Color.white);
		setBackground(Color.black);
		// setBackground(new Color(174, 214, 241));
		timer = new Timer(time,this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		
		setLayout(null);
		label1 = new JLabel(reset);
		label1.setSize(62,62);
		label1.setLocation(900,530);
		add(label1);
		
		label1.addMouseListener(new MouseListener(){
			public void mouseExited(MouseEvent e)
			{}
			public void mouseEntered(MouseEvent e)
			{}
			public void mouseReleased(MouseEvent e)
			{}
			public void mousePressed(MouseEvent e)
			{}
			public void mouseClicked(MouseEvent e)
			{
				System.out.println("mouse pressed");
				reset();
			}
		});
		
		Font f1 = new Font("courier",Font.BOLD,21);
		label2 = new JLabel("Reset");
		label2.setSize(68,30);
		label2.setFont(f1);
		label2.setLocation(900,585);
		add(label2);

		plus = new JLabel(plusIc);
		plus.setSize(60,64);
		plus.setFont(f1);
		plus.setLocation(900,365);
		add(plus);

		label3 = new JLabel("Speed:"+(135 - time));
		label3.setSize(120,30);
		label3.setFont(f1);
		// label3.setOpaque(true);
		// label3.setBackground(Color.black);
		label3.setForeground(Color.white);
		label3.setLocation(870,430);
		add(label3);

		minus = new JLabel(minusIc);
		minus.setSize(60,23);
		minus.setFont(f1);
		minus.setLocation(900,465);
		add(minus);

		plus.addMouseListener(new MouseListener(){
			public void mouseExited(MouseEvent e)
			{}
			public void mouseEntered(MouseEvent e)
			{}
			public void mouseReleased(MouseEvent e)
			{}
			public void mousePressed(MouseEvent e)
			{}
			public void mouseClicked(MouseEvent e)
			{
				faster();
			}
		});
		
			minus.addMouseListener(new MouseListener(){
			public void mouseExited(MouseEvent e)
			{}
			public void mouseEntered(MouseEvent e)
			{}
			public void mouseReleased(MouseEvent e)
			{}
			public void mousePressed(MouseEvent e)
			{}
			public void mouseClicked(MouseEvent e)
			{
				slower();
			}
		});



		int k =0;
		for(int i =0; i< boxes; i++)
		{
			
			boxx[i] = startx + k*distx;
			boxy[i] = starty + disty;
			k++;
			if( i==5 || i == 16)
			{
				disty +=80;
				startx = 130;
				k=0;
			}
			else if(i==10 )
			{	
				disty +=80;
				startx = 70;
				k=0;
			}
		}
		
		
	}
	public void faster()
	{
		timer.stop();
		time -=2;
		timer = new Timer(time,this);
		timer.start();
		label3.setText("Speed:"+(135-time));
	}

	public void slower()
	{
		timer.stop();
		time +=2;
		timer = new Timer(time,this);
		timer.start();
		label3.setText("Speed:"+(135-time));
	}
	public void reset()
	{
		int r1 = (int)Math.round(Math.random()*600 + 200);
		int r2 = (int)Math.round(Math.random()*200+360);
		r1 -= r1%10;
		r2 -= r2%10;
		x = r1;
		y = r2;
		xspeed = 10;
		yspeed = 10;

		startx = 70;
		starty = 40;
		boxes =22;
		hits = 0;
		distx =150;
		disty =0;
		time =35;
		timer.stop();
		timer = new Timer(time,this);
		timer.start();
		label3.setText("Speed:"+(135-time));

		start_game = false;
		
			int k =0;
		for(int i =0; i< boxes; i++)
		{
			
			boxx[i] = startx + k*distx;
			boxy[i] = starty + disty;
			k++;
			if( i==5 || i == 16)
			{
				disty +=80;
				startx = 130;
				k=0;
			}
			else if(i==10 )
			{	
				disty +=80;
				startx = 70;
				k=0;
			}
		}

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(back,0,0,this);
		g.setColor(Color.green);
		
		
		// g.fillRect(xb,yb,wb,hb);
		g.drawImage(block,xb,yb,this);
		
		// int k=0,i1;
		g.setColor(Color.red);
		g.drawImage(ball,x,y,this);
		// g.fillOval(x,y,size_of_ball,size_of_ball);
		
		
		// System.out.println("repaint");
		// g.setColor(Color.gray);
		g.setColor(Color.black);
		for(int i = 0; i< boxes; i++)
		{
			// g.fillRect(boxx[i],boxy[i],width_of_box,height_of_box);
			g.drawImage(bricks,boxx[i],boxy[i],this);
		}
		g.setColor(Color.black);
		if(hits == 22)
		{
			timer.stop();
			g.drawString("You Win ....",250,400);
			g.drawImage(craker1,100,200,this);
			g.drawImage(craker2,500,50,this);
			// g.drawString("abhi maja aaya na beedu",10,500);
		}			
		else if(y> height -40 )
			{
				timer.stop();
				g.drawString("you loose, try again",250,400);
				
			}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		
			
		if(start_game)
		{

		if( (y == yb - size_of_ball ) && ( x >= xb - size_of_ball && x<= xb + wb )) 
			{
				// System.out.println("condition 1  \tx: "+x+"\ty: "+y);
				yspeed *= -1;
			}

			x += xspeed;
			if(y< height )
			y += yspeed;
			if( y< 5 )
				yspeed *= -1;
			if(x> width -size_of_ball - 25 || x< 5)
				xspeed *= -1;
			
		// if( (y == yb - size_of_ball || y == yb + hb ) && ( x >= xb - size_of_ball && x<= xb + wb )) 
			
			
			for(int i = 0; i< boxes; i++)
			{
				if( (x == boxx[i]- size_of_ball  || x== boxx[i] + width_of_box ) && (y >= boxy[i] -size_of_ball && y <= boxy[i] +height_of_box ))
				{	
					if( x - xspeed  < boxx[i]- size_of_ball || x - xspeed >  boxx[i] + width_of_box )
					{	
				xspeed *= -1;
				hits++;
					boxx[i]= 1200;
					}	
				// System.out.println("condition a  \tx: "+x+"\ty: "+y+" box x:"+boxx[i]+"  box y:"+boxy[i]);
				}
				if(( y == boxy[i]- size_of_ball || y== boxy[i] +height_of_box ) && ( x>= boxx[i]-size_of_ball && x<=boxx[i]+width_of_box ))
				{
					yspeed *= -1;
					hits++;
				// System.out.println("condition b  \tx: "+x+"\ty: "+y+" box x:"+boxx[i]+"  box y:"+boxy[i]);
					boxy[i]= 1200;

				}
				
			}
			
	
		}
			repaint();
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_P)
		{
			start_game = !start_game;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE )
			start_game = true;
		if(e.getKeyCode() == KeyEvent.VK_R )
			reset();
		
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(xb + wb < width -20)
			{
				xb += 40;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(xb > 0)
			{
				xb -= 40;
			}
		}
		
	}
	public void keyReleased(KeyEvent e)
	{
	
	}
	
	public void keyTyped(KeyEvent e)
	{
	}
}

class FDemo extends JFrame
{
	PDemo p;
	FDemo(int width, int height)
	{
		
		p = new PDemo(width,height);
		add(p);
	}
}
class BrickBreaker
{
	public static void main(String []arfdaf)
	{
		int height = 730;
		int width = 1000;
		FDemo f = new FDemo(width,height-30);
		f.setVisible(true);
		//the screen will show 17 pixcel less in right x direction 
		//the screen will show 40 pixcel less in down y direction 
		f.setBounds(0,0,width,height);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setResizable(false);
		
	}
}