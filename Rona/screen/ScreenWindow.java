package screen;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ScreenWindow {

	private JFrame frame;
	private Canvas canvas;
	private int w, h;
	
	public ScreenWindow(int w, int h) {
		this.w = w;
		this.h = h;
		
		//createScreenWindow();
		frame = new JFrame();		
		frame.setSize(this.w, this.h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(this.w, this.h));
		canvas.setMaximumSize(new Dimension(this.w, this.h));
		canvas.setMinimumSize(new Dimension(this.w, this.h));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
}
