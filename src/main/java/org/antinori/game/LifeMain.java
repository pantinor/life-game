package org.antinori.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

@Deprecated
public class LifeMain {

    public static LifeMap map;
    public static MapView mapView;
    public static LifeMain demo;
    public static PlayerInfoPanel playerInfoPanel;
    public static StatusProgressPanel statusPanel;
    public static WheelPanel wheelPanel;

    public static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    public static JFrame frame;

    public static Dice dice = new Dice(1, 10);

    public static Font FONT_14 = new Font("Franklin Gothic Heavy", Font.PLAIN, 14);
    public static Font FONT_18 = new Font("Franklin Gothic Heavy", Font.PLAIN, 18);
    public static Font FONT_24 = new Font("Franklin Gothic Heavy", Font.PLAIN, 24);

    public static ArrayList<Player> players = null;
    public static Player currentTurnPlayer = null;
    public static Player yourPlayer = null;

    public static boolean difficult_setting = false;

    public static final String formatter = "%s suggests\n%s\ncommitted the crime\nwith the %s\nin the %s.";
    public static final String accusationFormatter = "%s makes\nan accusation that\n%s\ncommitted the crime\nwith the %s\nin the %s.";

    int winModeWidth = 0;
    int winModeHeight = 0;
    int winModeX = 0;
    int winModeY = 0;
    boolean inFullScreenMode = true;

    public LifeMain() {
        try {

            map = new LifeMap(readMapTemplate());

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            SoundEffect.init();

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            winModeWidth = (int) dim.getWidth() - 200;
            winModeHeight = (int) dim.getHeight() - 100; // minus task bar
            winModeX = 20;
            winModeY = 20;

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    @Deprecated
    public static void main(String[] args) {
        demo = new LifeMain();
        demo.initComponents();

        try {
            NewGame ng = new NewGame();
            ng.createDecks();

            ng.addPlayer("Paul", Color.blue, false);
            ng.addPlayer("Jessika", Color.pink, true);
            ng.addPlayer("Brandon", Color.green, true);

            ng.initPlayers();
            mapView.setGame(ng);

            //ng.setDebugStartPositions(map.getLocation(2, 8));
            ng.startGame();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setLocationInCenter(JDialog d, int offset_x, int offset_y) {

        d.setLocationRelativeTo(null);
        d.setLocation(d.getX() + offset_x, d.getY() + offset_y);

    }

    public void initComponents() {

        frame = new JFrame("Life");
        JPanel main = new JPanel();

        mapView = new MapView(map);
        playerInfoPanel = new PlayerInfoPanel();
        statusPanel = new StatusProgressPanel();
        wheelPanel = new WheelPanel();

        JPanel jPanel1 = mapView;
        JPanel jPanel3 = playerInfoPanel;
        JPanel jPanel2 = statusPanel;
        JPanel jPanel4 = wheelPanel;

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 837, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 858, Short.MAX_VALUE));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 298, Short.MAX_VALUE));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 112, Short.MAX_VALUE));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 88, Short.MAX_VALUE));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(main);
        main.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(
                                layout.createSequentialGroup().addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));

        frame.add(main);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice defaultScreen = env.getDefaultScreenDevice();
                    if (!inFullScreenMode) {
                        // Switch to fullscreen mode
                        frame.setVisible(false);
                        frame.setResizable(false);
                        frame.dispose();
                        frame.setUndecorated(true);
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                    } else {
                        // Switch to windowed mode
                        frame.setVisible(false);
                        frame.dispose();
                        frame.setUndecorated(false);
                        frame.setResizable(true);
                        frame.setBounds(winModeX, winModeY, winModeWidth, winModeHeight);
                        frame.setVisible(true);
                    }
                    inFullScreenMode = !inFullScreenMode;
                }
            }
        });

        frame.setResizable(false);

        //frame.setUndecorated(true);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBounds(winModeX, winModeY, winModeWidth, winModeHeight);

        frame.setVisible(true);

    }

    public static void setCurrentPlayer(Player player) {
        currentTurnPlayer = player;
    }

    public String readMapTemplate() {
        String mapTemplate = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/map.template")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                mapTemplate += line + "\n";
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapTemplate;

    }

    public static BufferedImage loadIcon(String file, int x, int y, int width, int height) {
        BufferedImage image = null;
        try {
            BufferedImage sheet = ImageIO.read(LifeMain.class.getResourceAsStream("/" + file));
            image = sheet.getSubimage(x, y, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage loadIcon(String file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(LifeMain.class.getResourceAsStream("/" + file));
        } catch (Exception e) {
        }
        return image;
    }

    public static ImageIcon getImageIcon(String file) {
        ClassLoader cldr = LifeMain.class.getClassLoader();
        java.net.URL imageURL = cldr.getResource(file);
        return new ImageIcon(imageURL);
    }

    public static BufferedImage overlayImages(BufferedImage bgImage, BufferedImage fgImage, int overlay_x, int overlay_y) {
        if (fgImage.getHeight() > bgImage.getHeight() || fgImage.getWidth() > fgImage.getWidth()) {
            System.err.println("Foreground Image Is Bigger In One or Both Dimensions" + "\nCannot proceed with overlay.\nPlease use smaller Image for foreground");
            return null;
        }

        Graphics2D g = bgImage.createGraphics();
        // g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a rescale filter op that makes the image 50% opaque.
        // float[] scales = { 1f, 1f, 1f, 0.5f };
        // float[] offsets = new float[4];
        // RescaleOp rop = new RescaleOp(scales, offsets, null);
        g.drawImage(bgImage, 0, 0, null);

        // g.drawImage(fgImage, rop, overlay_x, overlay_y);
        g.drawImage(fgImage, overlay_x, overlay_y, null);

        g.dispose();
        return bgImage;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, double zoom) {

        double zoomPercentage = zoom / 100;
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        double width = originalImage.getWidth() * zoomPercentage;
        double height = originalImage.getHeight() * zoomPercentage;

        BufferedImage resizedImage = new BufferedImage((int) width, (int) height, type);

        Graphics2D g = resizedImage.createGraphics();
        // g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(originalImage, 0, 0, resizedImage.getWidth(), resizedImage.getHeight(), null);
        g.dispose();

        return resizedImage;
    }

    /**
     * Convenience method that returns a scaled instance of the provided
     * {@code BufferedImage}.
     *
     * @param img the original image to be scaled
     * @param targetWidth the desired width of the scaled instance, in pixels
     * @param targetHeight the desired height of the scaled instance, in pixels
     * @param hint one of the rendering hints that corresponds to
     * {@code RenderingHints.KEY_INTERPOLATION} (e.g.
     * {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     * {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     * {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     * @param higherQuality if true, this method will use a multi-step scaling
     * technique that provides higher quality than the usual one-step technique
     * (only useful in down-scaling cases, where {@code targetWidth} or
     * {@code targetHeight} is smaller than the original dimensions, and
     * generally only when the {@code BILINEAR} hint is specified)
     * @return a scaled version of the original {
     * @codey BufferedImage}
     */
    public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }

    public static void drawSquareOnImage(Graphics2D g2D, BufferedImage baseImage) {

        String[] coordsText = new String[]{"10,10", "10,20", "20,20", "20,10"};

        Path2D.Float regionOfInterest = new Path2D.Float();
        // We must store the first X,Y coordinates so we can close the path, by
        // creating a line
        // to the last point to the first one.
        boolean isFirst = true;
        double firstX = 0, firstY = 0;
        // For each of the X,Y coordinates, parse and store them on the
        // Path2D.Float.
        for (String s : coordsText) {
            String[] xy = s.split(",");
            double x = Double.parseDouble(xy[0]);
            double y = Double.parseDouble(xy[1]);
            if (isFirst) {
                regionOfInterest.moveTo(x, y);
                firstX = x;
                firstY = y;
                isFirst = false;
            } else {
                regionOfInterest.lineTo(x, y);
            }
        }
        // Close the path.
        regionOfInterest.lineTo(firstX, firstY);
        // We have the path that define the region of interest. In order to dim
        // the image regions
        // outside of this path we must create another path that contains
        // everything but the
        // region of interest.
        // First we create a path for the whole image -- a rectangle with the
        // image's coordinates.
        Path2D.Float pathForWholeImage = new Path2D.Float();
        pathForWholeImage.moveTo(0, 0);
        pathForWholeImage.lineTo(baseImage.getWidth(), 0);
        pathForWholeImage.lineTo(baseImage.getWidth(), baseImage.getHeight());
        pathForWholeImage.lineTo(0, baseImage.getHeight());
        pathForWholeImage.lineTo(0, 0);
        // In order to use Constructive Area Geometry (CAG) operations we must
        // use the Area class.
        // First we create an Area with the path for the whole image...
        Area wholeImage = new Area(pathForWholeImage);
        // .. then we subtract the region of interest from this Area.
        wholeImage.subtract(new Area(regionOfInterest));
        // Now we have a Path2D.Float for the region of interest and an Area for
        // the rest of the image.
        // To draw and paint them we need a graphic context, which we will get
        // from the image itself.
        Graphics2D g2d = (Graphics2D) baseImage.getGraphics();
        // We want antialiasing!
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Fill the rest of the image with a transparent (100/255) white.
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.fill(wholeImage);
        // Draw the region of interest with a thick, almost opaque red line.
        g2d.setStroke(new BasicStroke(5f));
        g2d.setColor(new Color(255, 0, 0, 200));
        g2d.draw(regionOfInterest);
    }

}
