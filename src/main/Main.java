package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import display.Container;
import model.Car;
import service.ColorChooserDialog;

public class Main {

	public static void main(String[] args) {
		Semaphore semGreen = new Semaphore(1);
		Semaphore semRed = new Semaphore(0);
		Semaphore semYellow = new Semaphore(0);
		Semaphore semBlue = new Semaphore(0);

		Car green = new Car(semGreen, semRed, 25, "Green", "images\\green.png");
		Car red = new Car(semRed, semYellow, 175, "Red", "images\\red.png");
		Car yellow = new Car(semYellow, semBlue, 325, "Yellow", "images\\yellow.png");
		Car blue = new Car(semBlue, semGreen, 475, "Blue", "images\\blue.png");

		List<Car> cars = new ArrayList<>(List.of(green, red, yellow, blue));
		Container container = new Container(cars);

		String userOption = ColorChooserDialog.showColorChooserDialog();

		green.start();
		red.start();
		yellow.start();
		blue.start();

		// Espera todas as Threads terminar
		try {
			green.join();
			red.join();
			yellow.join();
			blue.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Collections.sort(cars, Comparator.comparingInt(Car::getFinalPosition));

		// Exibe os resultados
		StringBuilder message = new StringBuilder();
		for (int i = 0; i < cars.size(); i++) {
			message.append(i + 1).append("º - ").append(cars.get(i).getName()).append("\n");
		}
		boolean didWin = userOption.equals(cars.get(0).getName());
		message.append("\nYou chose ").append(userOption).append(". ");
		String resultMessage = didWin ? "Congratulations, you won!" : "Sorry, you didn't win.";
		message.append("\n").append(resultMessage);
		JOptionPane.showMessageDialog(null, message.toString(), "Final Ranking", JOptionPane.INFORMATION_MESSAGE);
		container.dispose();
	}

}
