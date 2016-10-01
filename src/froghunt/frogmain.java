package froghunt;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class frogmain extends JFrame {

	private static final long serialVersionUID = 1L;
	public static int level = 0;
	private static threadFrog mt;
	private static JLabel levelLabel = new JLabel();
	private static JLabel l = null;
	private static ArrayList<threadFrog> threadButtons = new ArrayList<>();
	public static frogmain mf;
	
	public frogmain() throws HeadlessException {
		Icon emptyicon = new ImageIcon("src/images/empty.gif");
		this.setTitle("Frog Hunter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		JPanel pointPanel = new JPanel();
		l = new JLabel("0");
		JLabel points = new JLabel("Points: ");
		levelLabel.setText("Level "+level);
		GridLayout grid5 = new GridLayout(5,5);
		p.setLayout(grid5);
		this.add(p, BorderLayout.CENTER);
		pointPanel.add(points);
		pointPanel.add(l);
		this.add(pointPanel, BorderLayout.SOUTH);
		this.add(levelLabel,BorderLayout.NORTH);
		
		for (int i=0;i<25;i++){
			JButton b = new JButton();
			b.setIcon(emptyicon);
			p.add(b);
			mt = new threadFrog(b, l);
			threadButtons.add(mt);
		}
		for (threadFrog t : threadButtons){
			t.start();
		}
		this.pack();
		this.setVisible(true);
		
	}

	public frogmain(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public frogmain(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public frogmain(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		mf = new frogmain();
		mf.setSize(new Dimension(600, 600));
		mf.hammerMouse();
		Thread timer = new Thread();
		timer.start();
		do{
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			levelLabel.setText("Level "+ ++level);			
		}while(level<4);
		timer.interrupt();
		for (threadFrog t : threadButtons){
			try {
				t.interrupt();					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		endMsg();
	}

	public void hammerMouse() {
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Image image = toolkit.getImage("src/images/hammerup.gif");
	    Point hotspot = new Point(0,0);
	    
	    Cursor hammerMouse = toolkit.createCustomCursor(image, hotspot, "up");
        this.getContentPane().setCursor(hammerMouse);
	}

	public static void endMsg(){
		JOptionPane.showMessageDialog(mf, "Bravo. You made "+l.getText()+" Points.");
	}	
}