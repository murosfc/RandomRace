package display;

import java.util.List;

import javax.swing.JFrame;

import model.Car;

public class Container extends JFrame{	
	private static final long serialVersionUID = 1L;
	private int WIDTH = 1280, HEIGHT= 653;
	
	public Container(List<Car> cars) {
		add(new Track(cars));		
		setTitle("Random Race");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public void repaint() {
		this.repaint();
	}
}
