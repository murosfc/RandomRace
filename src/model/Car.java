package model;

import java.awt.Image;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

import display.Track;

public class Car extends Thread {
	private int TRACK_FINISH_LINE = 1140;	
	protected int x, y;
	private int MILI_SEC_TO_SLEEP = 5;
	protected Semaphore semaforo, nextSem;
	private Image image;
	private Track track;
	private int finalPosition;

	public Car(Semaphore semaforo, Semaphore nextSem, int initialY, String name, String imagePath) {
		this.semaforo = semaforo;
		this.nextSem = nextSem;
		this.x = 0;
		this.y = initialY;
		this.load(imagePath);
		this.setName(name);
		this.finalPosition = 0;
	}

	public void load(String imagePath) {
		ImageIcon ref = new ImageIcon(imagePath);
		this.image = ref.getImage();
	}

	public void update() {
		int dx = (int) (Math.random() * 5) + 1;
		if (this.x >= TRACK_FINISH_LINE) 
			this.x = TRACK_FINISH_LINE;
		else
			this.x += dx;
	}

	public void run() {		
		while (!this.areAllFinished()) {			
			// Espera pelo semáforo
			try {
				semaforo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.update();

			// Espera um tempo para simular a movimentação do carro
			try {
				Thread.sleep(MILI_SEC_TO_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Libera o semáforo para a próxima Thread
			nextSem.release();

			track.repaint();
			
			if(this.x >= TRACK_FINISH_LINE && this.finalPosition == 0) {
				this.finalPosition = getPosition();
			}
		}		
	}
	
	public boolean areAllFinished() {
	    return this.track.getCars().stream()
	            .allMatch(car -> car.getFinalPosition() != 0);
	}

	private int getPosition() {
	    return this.track.getCars().stream()
	            .mapToInt(Car::getFinalPosition)
	            .max()
	            .orElse(0) + 1;
	}

	public Image getImage() {
		return image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public int getFinalPosition() {
		return finalPosition;
	}
	
	

}
