package uppgift2;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App extends JFrame {
	private FootballsField panel;

	// Konstruktorn i vilken vi skapar komponenterna
	public App() {
		setTitle("Fotbollstest");

		// Anger vad som ska hända när vi stänger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sätter size och placering av fönstret
		setSize(935, 667);
		setLocationRelativeTo(null);

		// Skapar komponenten (panelen)
		panel = new FootballsField();


		// Placerar ut komponenterna i mitten av fönstret
		add(panel, BorderLayout.CENTER);

		// Gör fönstret synligt
		setVisible(true);
	}

	public static void main(String[] args) {
		// Skapar ett objekt av den egna klassen

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new App();

			}
		});

	}
}