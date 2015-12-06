package org.antinori.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class WheelPanel extends javax.swing.JPanel {

    Image[] icons = new Image[10];
    Image currentIcon = null;

    public WheelPanel() {

        setPreferredSize(new java.awt.Dimension(175, 175));

        icons[0] = LifeMain.loadIcon("wheel-1.png");
        icons[1] = LifeMain.loadIcon("wheel-2.png");
        icons[2] = LifeMain.loadIcon("wheel-3.png");
        icons[3] = LifeMain.loadIcon("wheel-4.png");
        icons[4] = LifeMain.loadIcon("wheel-5.png");
        icons[5] = LifeMain.loadIcon("wheel-6.png");
        icons[6] = LifeMain.loadIcon("wheel-7.png");
        icons[7] = LifeMain.loadIcon("wheel-8.png");
        icons[8] = LifeMain.loadIcon("wheel-9.png");
        icons[9] = LifeMain.loadIcon("wheel-10.png");

    }

    public void setSpin(final int spin) {

        int index = spin - 1;
        for (int i = 1; i <= 31; i++) {
            if (index > 9) {
                index = 0;
            }
            currentIcon = icons[index];
            //System.out.print(index + ",");

            repaint();
            index++;
            double delay = compoundInterest(50, 0.17, i * 50);

            //SoundEffect.CLICK.play();
            try {
                Thread.sleep((int) delay);
            } catch (Exception e) {
            }
        }

    }

    public static double compoundInterest(double start, double interest, int periods) {
        return start * Math.pow(1 + interest / 100, periods);
    }

    public static float circOut(float t) {
        float ret = (float) Math.sqrt(1 - (t - 1) * (t - 1));
        //System.out.println(" ret ---> " + ret);

        return ret;
    }

    public static float cubeOut(float t) {
        return 1 + (--t) * t * t;
    }

    private static double PI2 = Math.PI / 2;

    public static float sineOut(float t) {
        return (float) Math.sin(PI2 * t);
    }

    public static float expoOut(float t) {
        return (float) (Math.pow(2, -10 * t) + 1);
    }

    public void paint(Graphics screen) {
        super.paint(screen);
        Graphics2D g2 = (Graphics2D) screen;

        g2.setColor(Tile.PANEL_BLUE);
        Shape bounds = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
        g2.fill(bounds);
        if (currentIcon != null) {
            g2.drawImage(currentIcon, 10, 10, null);
        }

        g2.setColor(Tile.MED_GREY);
        g2.drawLine(84, 2, 84, 15);
        g2.drawLine(83, 2, 83, 15);

    }

//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		int w = getWidth();
//		int h = getHeight();
//		// Draw ordinate.
//		g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
//		// Draw abcissa.
//		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
//		double xInc = (double) (w - 2 * PAD) / (data.length - 1);
//		double scale = (double) (h - 2 * PAD) / getMax();
//		// Mark data points.
//		g2.setPaint(Color.red);
//		for (int i = 0; i < data.length; i++) {
//			double x = PAD + i * xInc;
//			double y = h - PAD - scale * data[i];
//			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
//		}
//	}
//
//	private int getMax() {
//		int max = -Integer.MAX_VALUE;
//		for (int i = 0; i < data.length; i++) {
//			if (data[i] > max)
//				max = data[i];
//		}
//		return max;
//	}
    static int[] data = new int[32];
    final int PAD = 20;

    public static void main(String[] args) {

        for (int i = 40; i > 15; i--) {
            float ret = cubeOut(i) / 100000;
            System.out.println(i + " ---> " + ret);
        }

//		data[0] = 0;
//		double sum = 0;
//		for (int i = 1; i <= 31; i++) {
//			//double ret = compoundInterest(50, 0.17, i * 50);
//			double ret = cubeOut(i);
//			data[i] = (int) ret ;
//			System.out.println(i + " ---> " + ret);
//			sum = sum + ret;
//		}
//		System.out.println(" sum ---> " + sum);
//
//		JFrame f = new JFrame();
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		WheelPanel panel = new WheelPanel();
//		f.add(panel);
//		f.setSize(400, 400);
//		f.setLocation(200, 200);
//		f.setVisible(true);
//		panel.setSpin(5);
    }
}
