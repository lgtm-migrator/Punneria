package punneria;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Dimension;
import java.awt.Font;
import java.awt .Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
public class PunnPoint extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{ 
	boolean missionComplete = false;
 textBox textbox = new textBox();
 Random rand = new Random();
	boolean start = false;
	public boolean fileRead(String path, Boolean mode, Color Sky, Color cGrass, String Words, String delete){
	try{
		List<String> str = Files.readAllLines(Paths.get(path));
		if(str != null){
			day = false;
			grass = cGrass;
			sky = Sky;
			try{
				Files.delete(Paths.get(delete));
			}catch(Exception e){
			}
		words = Words;
			return true;
		}else{
			return false;
		}
	}catch(Exception e){
		return false;
	}
}
	Font font = new Font("", 0, 20);
	boolean menu = false;
	String name = ("");
	static boolean tunnelSave = false;
	static boolean saveChecked = saveChecker();
	static boolean saveChecker(){
		if(tunnelSave == true){
			try{
			Files.write(Paths.get("tunnelSave.txt"), Arrays.asList("tunnels have been saved"));
			}catch(Exception e){
				
			}
			return true;
		}else{
			return false;
		}

	}

	int second = 0;	
	void mouseMove(int X, int Y){
		Robot robot;
		try{
			robot = new Robot();
			robot.mouseMove(X, Y);
		} catch (AWTException e){
			e.printStackTrace();
		}
	}
	Pattern pattern = new Pattern("a c a c a f a d a a a b aw");
	static boolean tunnels = false;
	int size = 100;
	final Color orange = new Color(255, 100, 0);
	static boolean day = true;
	static boolean selector = false;
	static boolean night = false;
	Pattern bang = new Pattern("dw");
	boolean bangs = true;
	boolean moon = false;
	static String words   = new String("press space to start");
	Player player = new Player();
	int acl = 0;
	boolean slug = false;
	int sun = 20;
	void mouseHide(){
		Robot robot;
		try{
			robot = new Robot();
			robot.mouseMove(-100000, -1000000);
		} catch (AWTException e){
			e.printStackTrace();
		}
	}
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	void mouseShow(){
		Robot robot;
		try{
			robot = new Robot();
			robot.mouseMove(screenSize.width/2,screenSize.height/2);
		} catch (AWTException e){
			e.printStackTrace();
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(screenSize.width, screenSize.height);
	}
	Player play = new Player();
	Polygon poly = new Polygon(new int[]{screenSize.width/2,screenSize.width/2-10,screenSize.width/2+10}, new int[]{screenSize.height/2-10, screenSize.height/2-20, screenSize.height/2+10}, 3);
	void close(){
		Robot robot;
		try{
			robot = new Robot();
			//robot.mouseRelease();
		} catch (AWTException e){
			e.printStackTrace();
		}		
	}
	int footx = screenSize.width/2;
	int x = screenSize.height/3;
	int footy = screenSize.height-x;
	int lavaX = screenSize.width/2+size;
	int y = 20;
	Color no = Color.WHITE;
	static Color sky = new Color(0, 0, 255);
	static Color grass = new Color(0, 100, 0);
	{
		javax.swing.Timer timer = new Timer(10, e -> {
			this.repaint();
			if(lavaX == footx){
				lavaX = screenSize.width/2+100;
			}
			if(textbox.box == "call" && moon == true){
				acl = 20;
				textbox.box = "";
			}
			if(acl == 20){
				sky = new Color(200, 100, 0);
				night = false;
				acl = 1000;
				textbox.box = "";
			}
			if(night){
				day = false;
			}
			if(y > screenSize.height/3){
				x = screenSize.height/3;
				moon = true;
				grass = new Color(50, 50, 50);
				night = false;
			}
			if(moon){
				words = "type call to contact";
			}
			if(slug){
				words = "press the completely normal purple ball to reach the completely open secret underground caves.";
			}
		});
		timer.start();
	}
	Timer times = new Timer(100, e ->{
		if(night){
			words = "go up to progress, (use the arrow keys to move)";
		}
		sky = new Color(0, 0, sky.getBlue()-3);
		if(sky.getBlue()<1){
			night = true;
		}

	}); 
	Timer time = new Timer(100, e ->{
		sun = sun+3;
		if(slug){
			sky = new Color(sky.getRed()-2, sky.getGreen()-1, 0);
		}
		if(sky.getRed() < 3){
		}
		if(sun > 150){
			times.start();
		}
	});
	@Override
	public synchronized void addMouseMotionListener(MouseMotionListener l) {
		super.addMouseMotionListener(l);
	}
	public static void main(String[] args){
		JFrame punn = new JFrame("punneria");

		punn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		PunnPoint punnpoint = new PunnPoint();
		punn.getContentPane().add(punnpoint);
		punn.pack();
		punn.setVisible(true);
	}
	public PunnPoint() {
		try{
			List<String> fileMissionComplete = Files.readAllLines(Paths.get("missionCompleteSave.txt"));
			if(fileMissionComplete != null){
				day = false;
				missionComplete = true;
				grass = new Color(250, 250, 250);
				sky = new Color(0, 0, 0);
				try{
					Files.delete(Paths.get("tunnelSave.txt"));
				}catch(Exception e){
					
				}
			}
		}catch(Exception e){
			
		}
		
		tunnels = fileRead("tunnelSave.txt", tunnels, Color.BLACK, new Color(150, 150, 150), "Find the green artifact but beware, orange ground is lava and you will die if you touch it", "");
		addKeyListener(this);
		addKeyListener(textbox);
		addMouseListener	(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		setFocusable(true);
		requestFocusInWindow();
	}


	@Override
	public void mouseDragged(MouseEvent t){
		System.out.println("testing");
		if(tunnels){
			mouseMove(25, 40);
		}
	}
	@Override
	public void mouseMoved(MouseEvent t){
		if(tunnels){
			mouseMove(25, 40);
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent t){

	}
	@Override
	public synchronized void addMouseWheelListener(MouseWheelListener l) {
		super.addMouseWheelListener(l);
	}	
	@Override
	public void keyTyped(KeyEvent t) {

	}
	void xDown(int num, int speed){
		x = x+speed;
		if(x < num){
    		x = num;
		}
	}
	void xUp(int num, int speed){
		x = x+speed;
		if(x > num){
			x = num;
		}
	}
	void lavaLeft(int num, int speed){
		lavaX = lavaX+speed;
		if(lavaX < num){
			lavaX = num;
		}
	}
	void lavaRight(int num, int speed){
		lavaX = lavaX+speed;
		if(lavaX > num){
			lavaX = num;
		}
	}
	@Override
	public void keyPressed(KeyEvent t) {

		if(selector){
			if(t.getKeyCode()==KeyEvent.VK_MINUS){
				size = size-1;
				textbox.box = "";
			}
			if(size < 0){
				size = 0;
			}
			if(size > 200){
				size = 200;
			}
			if(t.getKeyCode()==KeyEvent.VK_EQUALS){
				size = size+1;
				textbox.box = "";
			}
		}
		if(t.getKeyCode () == KeyEvent.VK_UP){
			if(night){
				x = x-1;
				y = y+1;
			}
		}
		if(tunnels){
			if(t.getKeyCode()==KeyEvent.VK_UP){
			xDown(100, -3);
			}
			if(t.getKeyCode()==KeyEvent.VK_DOWN){
				xUp(screenSize.height/3, 3);
			}
			if(t.getKeyCode()==KeyEvent.VK_RIGHT){
				lavaLeft(0, -3);
			}
			if(t.getKeyCode()==KeyEvent.VK_LEFT){
				lavaRight(screenSize.width/2+100, 3);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent t) {

		if(t.getKeyCode ()==KeyEvent.VK_SPACE){
			time.start();
		}
		if(t.getKeyCode ()==KeyEvent.VK_T){
			start = !start;
		}
		if(moon){
			if(t.getKeyCode ()==KeyEvent.VK_C){
				acl = acl+5;
			}
			if(t.getKeyCode ()==KeyEvent.VK_A){
				acl = acl+5;
			}
			if(t.getKeyCode()==KeyEvent.VK_L){
				acl = acl+5;
			}
		}
		if(acl == 20){
			slug = true;
			moon = false;
		}
		if(selector){
			if(t.getKeyCode()==KeyEvent.VK_P){
				size = rand.nextInt(200);
				textbox.box = "";
			}
			if(t.getKeyCode()==KeyEvent.VK_C){
				no = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
				textbox.box = "";
			}
			if(t.getKeyCode()==KeyEvent.VK_G){
				no = Color.GREEN;
				textbox.box = "";
			}
			if(t.getKeyCode()==KeyEvent.VK_B){
				no = Color.BLUE;
				textbox.box = "";
			}
			if(t.getKeyCode()==KeyEvent.VK_R){
				no = Color.RED;
				textbox.box = "";
			}
		}
	}
	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		graphics.setColor(sky);	
		graphics.fillRect(0,  0,  screenSize.width, screenSize.height);
		if(moon){
			graphics.setColor(Color.WHITE);
			graphics.drawString("Call", 15, 25);
			graphics.drawRect(0, 0, 50, 50);
		}
		graphics.setColor(new Color(240, 200, 20));
		if(day){
			graphics.fillOval(screenSize.width/2-50, sun, 100, 100);
		}
		graphics.setColor(no);
		graphics.fillPolygon(new int[]{footx,screenSize.width/2+size,screenSize.width/2-size}, new int[]{footy,screenSize.height/2,screenSize.height/2}, 3);
		graphics.drawString("" + textbox.box + "", screenSize.width/2-100, screenSize.height/2-50);
		graphics.setColor(grass);
		graphics.fillRect(0, screenSize.height-x, screenSize.width, screenSize.height);
		if(night){
			graphics.setColor(new Color(50, 50, 50));
			graphics.fillOval(screenSize.width/2-50, y, 100, 100);

		}
		if(moon){
			graphics.setColor(new Color(0, 100, 0));
			graphics.fillOval(screenSize.width/2-50, y, 100, 100);
			y = 20;
		}
		graphics.drawString("Quest: "+ words +"", 100, 100);
		if(slug){
			graphics.setColor(new Color(200, 0, 200));
			graphics.fillOval(0, 0, 50, 50);
			graphics.drawString("hello, my name is Punnum, I will show you to the secret underground caves. Note: Visiters are always welcome!", 0, 80);
			graphics.setColor(Color.BLACK);
			graphics.fillRect(screenSize.width/2-100, screenSize.height-x, 200, screenSize.height);
		}
		if(selector){
			graphics.setColor(Color.BLACK);
			graphics.drawString("press R to select Red, B to select Blue, G to select Green, equals to increase size, minus to shrink, p to randomize size, c to randomize color", 100, 100);
			graphics.drawRect(screenSize.width-50, 0, 50, 50);
			graphics.drawString("next", screenSize.width-45, 25);
		}
		if(tunnels){
			graphics.setColor(new Color(150, 150, 150));
			sky = Color.BLACK;
			grass = new Color(150, 150, 150);
			graphics.setColor(no);
			graphics.setColor(orange);
			graphics.fillRect(lavaX, screenSize.height-x, screenSize.width/2-size, 50);
			graphics.setColor(new Color(0, 255, 0));
			graphics.fillRect(lavaX+900, screenSize.height-x-200, 100, 100);
		}
		if(lavaX < 1){
			missionComplete = true;tunnels = false; x = screenSize.height/3; lavaX = screenSize.width/2+size;
			saveChecked = false;
		}
		if(missionComplete){
			graphics.setColor(new Color(200, 0, 200));
				graphics.fillOval(0, 0, 50, 50);
				graphics.drawString("Well done, you completed my mission, press continue to progress, or press save to save your work.", 100, 20);
				graphics.setColor(Color.WHITE);
				graphics.drawRect(screenSize.width-50, 0, 50, 50);
				graphics.drawString("save", screenSize.width-50, 25);
		}
		graphics.setColor(Color.RED);
		graphics.drawString("Activity time: " + second + "seconds", 100, 150);
		if(tunnels == true){
			graphics.setColor(Color.RED);
			graphics.drawRect(0, 0, 50, 50);
			graphics.drawString("save", 0, 25);
		}
		if(saveChecked == true){
			graphics.setColor(Color.BLUE);
			graphics.drawString("saved", 100, 250);
			System.out.println("saved");
		}
	}
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
	}
	@Override
	public void mouseClicked(MouseEvent t){

	}
	@Override
	public void mouseReleased(MouseEvent t){
		if(t.getX() < 50){
			if(t.getY() < 50){
				if(slug){
					selector = true;
					sky = Color.WHITE;
					words = "";
					grass = Color.WHITE;
					slug = false;
				}
			}
		}
		if(selector){
			if(t.getX()>screenSize.width-50){
				if(t.getY()<50){
					tunnels = true;
					selector = false;
					words = "find the green artifact but beware, orange ground is lava and you will die if you touch it";
					mouseMove(25, 40);
				}
			}
		}
		if(tunnels == true){
			if(t.getY() < 51){
				if(t.getX() < 51){
					if(tunnels != tunnelSave){
						tunnelSave = true;
					}else{
						saveChecked = false;

					}
					saveChecker();
				}
			}
		}
if(t.getY() < 51){
	if(t.getX() > screenSize.width-51){
		if(missionComplete){
			saveChecked = true;
			try{
				Files.write(Paths.get("missionCompleteSave.txt"), Arrays.asList("you have completed the mission!"));
			} catch(Exception E){
				
			}
		}
	}
}
	}
	@Override
	public void mouseEntered(MouseEvent t) {

	}
	@Override
	public void mouseExited(MouseEvent t) {

	}
	@Override
	public void mousePressed(MouseEvent t) {

	}
	{		
		new Thread(()->{
			Timer count = new Timer(1000, e ->{
				second = second+1;
			});
			count.start();
		}).start();

		new Thread(()->{
			while(true){
				if(night){
					pattern = new Pattern("f f f, dw, fw");
				}
				if(moon){
					pattern = new Pattern("awawaw d d d d d d d dq dh dw dw");
					pattern.setInstrument("ACOUSTIC_BASS");
				}
				if(slug){
					if(bangs){
						bang.setInstrument("GUNSHOT");
						play.play(bang);
						bangs = false;
					}
				}
				if(tunnels){
					pattern = new Pattern("c d cw a ah");
				}
				if(missionComplete){
					pattern = new Pattern("e a d g b Ew");
				}
				player.play(pattern);
			}	
		}).start();
	}
}