package uppgift2;

/**
 * @author Mats Palm
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.List;
import javax.swing.*;

/**
 * Klassen ritar upp en fotbollsplan och fotbollar som studsar mot planens
 * kanter För varje kant bollen stöter mot så minskas storleken. När storleken
 * är 0 eller att bollen har existerat i 10 sekunder så tas bollen bort
 */

public class FootballsField extends JPanel implements ActionListener, MouseListener {
	private Image field_image; // Bilden med fotbollsplanen
	private List<Football> list_of_footballs; // En lista med fotbollsobjekt
	private final int fotball_TTL = 10; // Time to live för en fotboll

	/**
	 * Konstruktor
	 * 
	 * Skapar en fotbollsplans image och en tom lista som kan innehålla
	 * fotbollsobjekt Startar en timer som slår va 30e millisekund
	 */
	public FootballsField() {

		// Läs in filen med fotbollsplanen
		URL url = this.getClass().getResource("/fotballfield.png");
		field_image = new javax.swing.ImageIcon(url).getImage();

		// Skapa en tom forbollslista
		list_of_footballs = new java.util.ArrayList<Football>();

		// Skapa en timer som slår var 30e ms
		Timer t = new Timer(30, this);
		t.start();

		// Lyssna på musklick
		addMouseListener(this);

	}

	/**
	 * Rensa bort gamla fotbollsobjekt som inte skall ritas ut längre pga att
	 * deras TTL är > 10 sekunder eller att deras storlek är för liten
	 */
	public void remove_old_footballs() {
		List<Football> footballs_todraw;
		footballs_todraw = new java.util.ArrayList<Football>();
		for (Football f : list_of_footballs) {
			if ((System.currentTimeMillis() / 1000l - f.get_creation_time() < fotball_TTL) && (f.get_size() >= 0)) {
				footballs_todraw.add(f);
			}
		}

		list_of_footballs = footballs_todraw;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Rita planen
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 935, 667);
		g.drawImage(field_image, 0, 0, this.getWidth(), this.getHeight(), null);

		// Rita fotbollar
		for (Football f : list_of_footballs) {
			f.draw(g);
		}

		// Rita texten med hur många fotbollar som finns i listan
		g.setColor(Color.BLACK);
		int style = Font.BOLD;
		Font font = new Font("Arial", style, 20);
		g.setFont(font);
		g.drawString("Antal fotbollar: " + list_of_footballs.size(), 10, this.getHeight() - 20);

	}

	/**
	 * Uppdaterar listan med vilka fotbollar som fortfarande är aktuellt och
	 * ritar om planen med bollarna
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		remove_old_footballs();
		repaint();
	}

	/**
	 * Skapa en ny boll
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		// Skapa en ny fotboll
		list_of_footballs.add(new Football(me.getX(), me.getY(), this));

	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// ignore
	}

	@Override
	public void mouseExited(MouseEvent me) {
		// ignore
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// ignore
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// ignore
	}
}