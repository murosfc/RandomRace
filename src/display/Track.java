package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Car;

public class Track extends JPanel{	
	private static final long serialVersionUID = 1L;
	List<Car> cars;
	
	private Image background;

	public Track(List<Car> cars) {
		this.cars = cars;
		//Adiciona a Track para cada carro
		for (Car c : this.cars)
			c.setTrack(this);
		
		//Parâmentros para melhora de desempenho
		setFocusable(true);
		setDoubleBuffered(true);
		
		//Adiciona a pista de fundo
		ImageIcon ref = new ImageIcon ("images\\pista.jpg");
		this.background = ref.getImage();	
		
	}
	
	//Desenha o mapa e os carros
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(background, 0, 0, null);
		for (Car c : cars) {
			graphics.drawImage(c.getImage(), c.getX(), c.getY(), this);
		}		
		graphics.dispose();
	}

	public List<Car> getCars() {
		return cars;
	}
	
	
}
