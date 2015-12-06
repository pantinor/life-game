package org.antinori.game.utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * A demo class that illustrates drawing text along the outside of a circle.
 */
public class CircleTextDemo extends Canvas {

    Frame myframe;
    TextField text;
    Button printBtn;
    Font myfont;
    Color textcolor;
    Color circlecolor;

    /**
     * Create a CircleTextDemo canvas and frame with default settings.
     */
    public CircleTextDemo() {
        this("Serif", Font.PLAIN, 48, Color.white, Color.black);
    }

    /**
     * Create a CircleTextDemo canvas and frame with supplied settings.
     *
     * @param ff Font family (usually "Serif")
     * @param fs Font style (usually Font.PLAIN)
     * @param fz Font size (usually 18)
     * @param bg Background color for this canvas (usually pink)
     * @param fg Foreground color for text (usually black)
     */
    public CircleTextDemo(String ff, int fs, int fz, Color bg, Color fg) {
        setBackground(bg);
        circlecolor = bg.brighter();
        textcolor = fg;
        myfont = new Font(ff, fs, fz);

        text = new TextField("Text on a circle using Java 2D Graphics!");
        myframe = new Frame("CircleTextDemo");
        printBtn = new Button("Print");
        myframe.add(text, BorderLayout.NORTH);
        myframe.add(this, BorderLayout.CENTER);
        myframe.add(printBtn, BorderLayout.SOUTH);
        myframe.setSize(new Dimension(300, 340));
        myframe.setLocation(150, 140);
        myframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        });
        printBtn.addActionListener(new FramePrinter(myframe));
        myframe.setVisible(true);
    }

    /**
     * Paint the contents of the CircleDemoText canvas.
     *
     * @param g - a Graphics, hopefully a Graphics2D
     */
    public void paint(Graphics g) {
        String st = text.getText();
        if (st.length() == 0) {
            return;
        }
        if (g instanceof Graphics2D) {
            Dimension cd = getSize();
            Point pt = new Point(cd.width / 2, cd.height / 2);
            int radius = (int) (pt.x * 0.84);
            g.setColor(circlecolor);
            g.drawArc(pt.x - radius, pt.y - radius, radius * 2 - 1, radius * 2 - 1, 0, 360);
            g.setColor(textcolor);
            g.setFont(myfont);
            drawCircleText((Graphics2D) g, st, pt, radius, -Math.PI / 2, 1.0);
        } else {
            System.out.println("Cannot draw curved text without a Graphics2D");
        }
    }

    /**
     * Draw a piece of text on a circular curve, one character at a time. This
     * is harder than it looks...
     *
     * This method accepts many arguments: g - a Graphics2D ready to be used to
     * draw, st - the string to draw, center - the center point of the circle
     * (Point), r - the radius of the circle, a1 - the beginning angle on the
     * circle to start, in radians, af - the angle advance factor (usually 1.0)
     */
    static void drawCircleText(Graphics2D g, String st, Point center, double r, double a1, double af) {
        double curangle = a1;
        double curangleSin;
        Point2D c = new Point2D.Double(center.x, center.y);
        char ch[] = st.toCharArray();
        FontMetrics fm = g.getFontMetrics();
        AffineTransform xform1, cxform;
        xform1 = AffineTransform.getTranslateInstance(c.getX(), c.getY());
        for (int i = 0; i < ch.length; i++) {
            double cwid = (double) (getWidth(ch[i], fm));
            if (!(ch[i] == ' ' || Character.isSpaceChar(ch[i]))) {
                cwid = (double) (fm.charWidth(ch[i]));
                cxform = new AffineTransform(xform1);
                cxform.rotate(curangle, 0.0, 0.0);
                String chstr = new String(ch, i, 1);
                g.setTransform(cxform);
                g.drawString(chstr, (float) (-cwid / 2), (float) (-r));
            }

            // compute advance of angle assuming cwid<<radius
            if (i < (ch.length - 1)) {
                double adv = cwid / 2.0 + fm.getLeading() + getWidth(ch[i + 1], fm) / 2.0;
                // Use of atan() suggested by Michael Moradzadeh
                curangle += Math.atan(adv / r);
                // Original code was:
                // curangle += Math.sin(adv / r);

            }
        }
    }

    /**
     * Get the width of a given character under the specified FontMetrics,
     * interpreting all spaces as en-spaces.
     */
    static int getWidth(char c, FontMetrics fm) {
        if (c == ' ' || Character.isSpaceChar(c)) {
            return fm.charWidth('n');
        } else {
            return fm.charWidth(c);
        }
    }

    public static void main(String args[]) {
        CircleTextDemo ctd;
        ctd = new CircleTextDemo();
    }

    class FramePrinter implements ActionListener {

        private Frame fr;

        public FramePrinter(Frame f) {
            fr = f;
        }

        public void actionPerformed(ActionEvent ae) {
            PrintJob pjob;
            pjob = fr.getToolkit().getPrintJob(fr, "Printing Circle Demo", null, null);
            if (pjob != null) {
                Graphics g = pjob.getGraphics();
                if (g != null) {
                    g.translate(100, 100);
                    fr.printAll(g);
                    g.dispose();
                }
                pjob.end();
            }
        }
    }

}
