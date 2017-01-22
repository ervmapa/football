package uppgift2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import java.util.List;

/**
 * Klassen representerar en fotboll
 * 
 */

public class Football {

	private int x; // x position
	private int y; // y position
	private int vx; // hastighet i xled
	private int vy; // hastighet i yled
	private int size; // storlek (0..9)
	private List<Image> football_images; // En lista innehållande biler med
											// fotbollar i olika storlekar
	private long creation_time; // Tidpunkt då objektet skapades
	private FootballsField field; // Fotbollsplanen som bollarna skall ritas på

	/**
	 * Konstruktor
	 * 
	 * @param x
	 *            x-position
	 * @param y
	 *            y-position
	 * @param field
	 *            fotbollsplanen
	 */
	public Football(int x, int y, FootballsField field) {

		Random r = new Random();

		/*
		 * Slumpa initial storlek på bollen och hastighet i x och y led
		 */
		this.field = field;
		size = r.nextInt(10);
		vx = -10 + r.nextInt(20);
		vy = -10 + r.nextInt(20);

		// Nedanstående lite trista logik är för att förhindra att bollarna
		// studsar felaktigt då de skapas för nära någon av sidorna
		if (x <= size * 10) {
			x = size * 11;
		}

		if (y <= size * 10) {
			y = size * 11;
		}

		if (x + size * 10 >= field.getWidth()) {
			x = x - size * 11;
		}

		if (y + size * 10 >= field.getHeight()) {
			y = y - size * 11;
		}

		this.x = x;
		this.y = y;

		// Spara tid då vi skapar objektet
		creation_time = System.currentTimeMillis() / 1000l;

		// Skapa en lista med bilderna för de olika storlekarna som en boll kan
		// ha
		football_images = new java.util.ArrayList<Image>();
		for (int i = 0; i <= 9; i++) {
			// System.out.println(i);
			URL url = this.getClass().getResource("/fotball" + (i + 1) + ".png");
			football_images.add(new javax.swing.ImageIcon(url).getImage());
		}

	}

	/**
	 * Returnera objektets skapandetid
	 * 
	 * @return creation_time tidpunkt då objketet skapades
	 */
	public long get_creation_time() {
		return creation_time;
	}

	/**
	 * Returnera objektets storlek
	 * 
	 * @return size storlek
	 */
	public int get_size() {
		return size;
	}

	/**
	 * Rita upp objektet Kolla om man träffat någon av kanterna
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {

		// Uppdatera objektet i x och y led
		x += vx;
		y += vy;

		// Rita bilden
		g.drawImage(football_images.get(size), x - (size + 1) * 10 / 2, y - (size + 1) * 10 / 2, (size + 1) * 10,
				(size + 1) * 10, null);

		// Träffa vänster kant?
		if (x - (size + 1) * 10 / 2 <= 0) {
			vx = -vx;
			size--;
		}
		// träffa övre kanten?
		else if ((y - (size + 1) * 10 / 2) <= 0) {
			vy = -vy;
			size--;
		}

		// Träffa höger kant?
		else if (x + (size + 1) * 10 / 2 > field.getWidth()) {
			vx = -vx;
			size--;

		}
		// Träffa nedre kant?
		else if (y + (size + 1) * 10 / 2 > field.getHeight()) {
			vy = -vy;
			size--;

		}
	}
}
