package org.antinori.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class MapView extends JPanel implements MouseListener {

	private static final int WIDTH = 48;
	private LifeMap map = null;
	public Image logo = null;
	private NewGame game = null;

	public MapView(LifeMap map) {
		this.map = map;
		
		addMouseListener(this);

		try {
			logo = LifeMain.loadIcon("logo.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled) {
			addMouseListener(this);
		} else {
			removeMouseListener(this);
		}
	}

	protected double getCellWidth() {
		return (double) getWidth() / (double) map.getXSize();
	}

	protected double getCellHeight() {
		return (double) getHeight() / (double) map.getYSize();
	}

	public void mouseClicked(MouseEvent e) {

		int x = (int) (e.getX() / getCellWidth());
		int y = (int) (e.getY() / getCellHeight());

		Location loc = map.getLocation(x, y);
		
		System.out.println("x=" + e.getX() + " y=" + e.getY() + " " + loc.toString());

		repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public void setGame(NewGame game) {
		this.game = game;
	}

	public void setMap(LifeMap map) {
		this.map = map;
		repaint();
	}



	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH * map.getXSize(), WIDTH * map.getYSize());
	}

	private Ellipse2D buildCircle(int x, int y, double radius) {
		double a = x * getCellWidth() + (getCellWidth() - radius) / 2.0;
		double b = y * getCellHeight() + (getCellHeight() - radius) / 2.0;
		return new Ellipse2D.Double(a, b, radius, radius);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Tile.LIGHT_BLUE);
		Shape bounds = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
		g2.fill(bounds);
		
		for (int i = 0; i < map.getXSize(); i++) {
			for (int j = 0; j < map.getYSize(); j++) {
				Location loc = map.getLocation(i, j);
				loc.setPixelX(i*getCellWidth());
				loc.setPixelY(j*getCellHeight()); //position at lower left of cell
			}
		}

		for (int i = 0; i < map.getXSize(); i++) {
			for (int j = 0; j < map.getYSize(); j++) {
				Location loc = map.getLocation(i, j);
				
				if (loc.getBlocked()) {
					continue;
				}

				Color tileColor = loc.getTile().getColor();
				Color borderColor = Tile.MED_GREY;

				// larger tile
				g2.setColor(borderColor);
				Shape c1 = new Rectangle2D.Double(i * getCellWidth(), j * getCellHeight(), getCellWidth(), getCellHeight());
				g2.fill(c1);

				// smaller tile
				g2.setColor(tileColor);
				Shape c2 = new Rectangle2D.Double(i * getCellWidth() + 1, j * getCellHeight() + 1, getCellWidth() - 2, getCellHeight() - 2);
				g2.fill(c2);

			}
		}

		// dice rolled
		//drawString(g2, String.valueOf(lastRolledValue), map.getLocation(13,5).getPixelX()+20, map.getLocation(13,5).getPixelY()-20, 48, Tile.MED_GREY);

		//logo
		//g2.drawImage(logo, map.getLocation(17,8).getPixelX()+15, map.getLocation(17,8).getPixelY()+15, 190, 80, null);
		
		paintPlayerLocations(g2);

	}


	void paintPlayerLocations(Graphics2D g2) {
		
		if (this.game == null || this.game.getPlayers() == null || this.game.getPlayers().size() < 1) return;
		
		for (Player player : game.getPlayers()) {
			Location location = player.getPlayerLocation();
			if (location == null) continue;
			
			if (player == game.getCurrentPlayer()) {
				g2.setColor(Color.pink);
				double circleWidth = Math.min(getCellWidth() / 1.5, getCellHeight() / 1.5);
				Shape sCircle = buildCircle(location.getX(), location.getY(), circleWidth);
				g2.fill(sCircle);
			}
			Color color = player.getPlayerColor();
			g2.setColor(color);
			double circleWidth = Math.min(getCellWidth() / 2.0, getCellHeight() / 2.0);
			Shape sCircle = buildCircle(location.getX(), location.getY(), circleWidth);
			g2.fill(sCircle);
		}

	}


	public void drawString(Graphics2D g, String label, int x, int y, int font_size, Color color) {

		Font font = new Font("Berlin Sans FB Demi", Font.PLAIN, font_size);
		g.setFont(font);

		g.setColor(Color.black);
		g.drawString(label, x + 2, y + 2);

		g.setColor(color);
		g.drawString(label, x, y);

	}

}
