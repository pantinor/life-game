package org.antinori.game.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

//used to make a life wheel graphic
class Wheel extends JComponent {

	public Color[] colors = null;
	public static Color offwhite = new Color(240,240,240);


	Wheel() {
		this.colors = createColorCodeTable();
	}

	public void paint(Graphics g) {

		drawPie((Graphics2D) g);

		BufferedImage output = new BufferedImage(510, 510, BufferedImage.TYPE_INT_ARGB);
		Graphics2D outg = output.createGraphics();

		drawPie(outg);

		try {
			ImageIO.write(output, "PNG", new File("mywheel.png"));
		} catch (Exception e) {

		}

	}

	void drawPie(Graphics2D g) {
		

		BufferedImage output = new BufferedImage(510, 510, BufferedImage.TYPE_INT_ARGB);
		Graphics2D outg = output.createGraphics();

		Shape circle = new Ellipse2D.Float(0, 0, 510, 510);
		g.setPaint(Color.gray);
		g.draw(circle);
		g.fill(circle);

		int total = colors.length;
		int startAngle = 90 - 36;
		for (int i = 0; i < 10; i++) {
			int arcAngle = (int) (total * 360 / (total * total));

			g.setColor(colors[i]);
			g.fillArc(5, 5, 500, 500, startAngle, arcAngle);

			startAngle -= arcAngle;
		}

		Shape inner_circle1 = new Ellipse2D.Float(255 - 103, 255 - 103, 206, 206);
		g.setPaint(Color.gray);
		g.draw(inner_circle1);
		g.fill(inner_circle1);

		Shape inner_circle2 = new Ellipse2D.Float(255 - 100, 255 - 100, 200, 200);
		g.setPaint(offwhite);
		g.draw(inner_circle2);
		g.fill(inner_circle2);
		
		drawSpokes(g, 255, 255);
		
		Shape inner_circle3 = new Ellipse2D.Float(255 - 30, 255 - 30, 60, 60);
		g.setPaint(offwhite);
		g.draw(inner_circle3);
		g.fill(inner_circle3);

		Shape inner_circle4 = new Ellipse2D.Float(255 - 22, 255 - 22, 44, 44);
		g.setPaint(Color.gray);
		g.draw(inner_circle4);
		g.fill(inner_circle4);
		
		Shape inner_circle5 = new Ellipse2D.Float(255 - 20, 255 - 20, 40, 40);
		g.setPaint(Color.lightGray);
		g.draw(inner_circle5);
		g.fill(inner_circle5);
		
		drawCircleText(g,"1",255,255,175,0.3);
		drawCircleText(g,"2",255,255,175,0.95);
		drawCircleText(g,"3",255,255,175,1.55);
		drawCircleText(g,"4",255,255,175,2.2);
		drawCircleText(g,"5",255,255,175,2.8);
		drawCircleText(g,"6",255,255,175,3.45);
		drawCircleText(g,"7",255,255,175,4.1);
		drawCircleText(g,"8",255,255,175,4.7);
		drawCircleText(g,"9",255,255,175,5.35);
		
		drawCircleText(g,"1",255,255,175,5.85);
		drawCircleText(g,"0",255,255,175,6.05);




	}

	static Color[] createColorCodeTable() {

		Color[] colors = { new Color(14, 80, 155), new Color(0, 114, 191), new Color(9, 155, 101), new Color(116, 202, 95), new Color(229, 234, 46), new Color(233, 186, 54), new Color(201, 158, 49), new Color(234, 77, 58), new Color(180, 81, 110),
				new Color(101, 84, 154) };
		return colors;

	}

	public static void main(String[] argv) {

		Wheel wheel = new Wheel();
		JFrame frame = new JFrame();
		frame.getContentPane().add(wheel);
		frame.setSize(520, 520);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

	}

	public void drawSpokes(Graphics2D g2, int xcenter, int ycenter) {
		
		g2.setStroke(new BasicStroke(10));

		int i = 1;
		int numberSlices = 10;
		double deltatheta = (2.0 * Math.PI) / ((double) numberSlices);
		double theta = deltatheta;
		
		for (int j = 0; j < numberSlices; j++) {
			
			switch(j){
			case 0:g2.setColor(this.colors[4]);break;
			case 1:g2.setColor(this.colors[5]);break;
			case 2:g2.setColor(this.colors[6]);break;
			case 3:g2.setColor(this.colors[7]);break;
			case 4:g2.setColor(this.colors[8]);break;
			case 5:g2.setColor(this.colors[9]);break;
			case 6:g2.setColor(this.colors[0]);break;
			case 7:g2.setColor(this.colors[1]);break;
			case 8:g2.setColor(this.colors[2]);break;
			case 9:g2.setColor(this.colors[3]);break;
			}
			
			double x1, y1, x2, y2;
			theta += deltatheta;
			x1 = Math.cos(theta) * 85 * (i - 1) + xcenter;
			x2 = Math.cos(theta) * 85 * i + xcenter;
			y1 = Math.sin(theta) * 85 * (i - 1) + ycenter;
			y2 = Math.sin(theta) * 85 * i + ycenter;
			g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		}

	}
	
	

	void drawCircleText(Graphics2D g, String text, int cx, int cy, double radius, double angle) {
		
		//Font font = new Font("Britannic Bold", Font.PLAIN, 72);
		//Font font = new Font("Cambria", Font.PLAIN, 72);
		//Font font = new Font("Bookman Old Style", Font.PLAIN, 72);
		Font font = new Font("Century", Font.PLAIN, 72);

		g.setFont(font);
		
		FontMetrics fm = g.getFontMetrics();
		
		AffineTransform cxform = AffineTransform.getTranslateInstance(cx, cy);
		cxform.rotate(angle, 0.0, 0.0);

		char[] letters = text.toCharArray();
		char letter = letters[0];
		
		int cwid = fm.charWidth(letter);
		g.setTransform(cxform);
		
		g.setColor(Color.lightGray);
		g.drawString(text, (float) (-cwid / 2), (float) (-radius));
		
		g.setColor(offwhite);
		g.drawString(text, (float) (-cwid / 2)-2, (float) (-radius));


		
	}
	


}