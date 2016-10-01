package froghunt;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class threadFrog extends Thread implements ActionListener{

	private JButton jb = new JButton();
	private JLabel jl = new JLabel();
	private Icon frogicon = new ImageIcon("src/images/frog.gif");;
	private Icon emptyicon =  new ImageIcon("src/images/empty.gif");
	private Icon deadicon = new ImageIcon("src/images/frogdead.gif");
	
	public threadFrog(JButton b, JLabel l) {
		jb = b;
		jl = l;
		jb.addActionListener(this);
	}

	@Override
	public void run() {
		int sleepTime = 6000;
		switch (frogmain.level){
		case 1:
			sleepTime = 3000;
			break;
		case 2:
			sleepTime = 1500;
			break;
		case 3:
			sleepTime = 750;
			break;
		case 4:
			sleepTime = 200;
			break;	
		}
		while(frogmain.level < 4){
			jb.setIcon(frogicon);
			try {
				Thread.sleep((int) (Math.random() * sleepTime/2));
			} catch (InterruptedException e) {
				//nop
			}
			jb.setIcon(emptyicon);
			try {
				Thread.sleep((int) (Math.random() * sleepTime*2));
			} catch (InterruptedException e) {
				//nop
			}		
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		hammerDownMouse();
		if (jb.getIcon().equals(frogicon)){
			int c= Integer.parseInt(jl.getText());
			jl.setText(""+(++c));
			jb.setIcon(deadicon);
		}else{
			java.awt.Toolkit.getDefaultToolkit().beep();
			int c= Integer.parseInt(jl.getText());
			jl.setText(""+(--c));
		}
	}
	
	public void hammerDownMouse() {
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Image down = toolkit.getImage("src/images/hammerdown.gif");
	    Image up = toolkit.getImage("src/images/hammerup.gif");
	    Point hotspot = new Point(0,0);
	    Cursor hammerDownMouse = toolkit.createCustomCursor(down, hotspot, "down");
	    Cursor hammerUpMouse = toolkit.createCustomCursor(up, hotspot, "up");
        frogmain.mf.getContentPane().setCursor(hammerDownMouse);
        try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        frogmain.mf.getContentPane().setCursor(hammerUpMouse);
	}
}