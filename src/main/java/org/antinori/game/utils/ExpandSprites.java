package org.antinori.game.utils;

//http://www.reinerstilesets.de/2d-grafiken/2d-humans/
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ExpandSprites {

    public static String dir = "C:\\Users\\Paul\\Desktop\\sprites\\queen\\";

    private static void expand(String out) throws IOException {

        ArrayList<String> files = new ArrayList<String>();

        files.add("looking e0000.bmp");
        files.add("looking e0001.bmp");
        files.add("looking e0002.bmp");
        files.add("looking e0003.bmp");
        files.add("looking e0004.bmp");
        files.add("looking e0005.bmp");
        files.add("looking e0006.bmp");
        files.add("looking e0007.bmp");
        files.add("looking n0000.bmp");
        files.add("looking n0001.bmp");
        files.add("looking n0002.bmp");
        files.add("looking n0003.bmp");
        files.add("looking n0004.bmp");
        files.add("looking n0005.bmp");
        files.add("looking n0006.bmp");
        files.add("looking n0007.bmp");
        files.add("looking ne0000.bmp");
        files.add("looking ne0001.bmp");
        files.add("looking ne0002.bmp");
        files.add("looking ne0003.bmp");
        files.add("looking ne0004.bmp");
        files.add("looking ne0005.bmp");
        files.add("looking ne0006.bmp");
        files.add("looking ne0007.bmp");
        files.add("looking nw0000.bmp");
        files.add("looking nw0001.bmp");
        files.add("looking nw0002.bmp");
        files.add("looking nw0003.bmp");
        files.add("looking nw0004.bmp");
        files.add("looking nw0005.bmp");
        files.add("looking nw0006.bmp");
        files.add("looking nw0007.bmp");
        files.add("looking s0000.bmp");
        files.add("looking s0001.bmp");
        files.add("looking s0002.bmp");
        files.add("looking s0003.bmp");
        files.add("looking s0004.bmp");
        files.add("looking s0005.bmp");
        files.add("looking s0006.bmp");
        files.add("looking s0007.bmp");
        files.add("looking se0000.bmp");
        files.add("looking se0001.bmp");
        files.add("looking se0002.bmp");
        files.add("looking se0003.bmp");
        files.add("looking se0004.bmp");
        files.add("looking se0005.bmp");
        files.add("looking se0006.bmp");
        files.add("looking se0007.bmp");
        files.add("looking sw0000.bmp");
        files.add("looking sw0001.bmp");
        files.add("looking sw0002.bmp");
        files.add("looking sw0003.bmp");
        files.add("looking sw0004.bmp");
        files.add("looking sw0005.bmp");
        files.add("looking sw0006.bmp");
        files.add("looking sw0007.bmp");
        files.add("looking w0000.bmp");
        files.add("looking w0001.bmp");
        files.add("looking w0002.bmp");
        files.add("looking w0003.bmp");
        files.add("looking w0004.bmp");
        files.add("looking w0005.bmp");
        files.add("looking w0006.bmp");
        files.add("looking w0007.bmp");

        int newTileSize = 96;
        int newTilesX = 8;
        int newTilesY = 8;

        BufferedImage output = new BufferedImage(newTileSize * newTilesX, newTileSize * newTilesY, BufferedImage.TYPE_INT_ARGB);

        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                //BufferedImage input = LifeMain.loadIcon(dir + files.get(index));
                BufferedImage input = ImageIO.read(new File(dir + files.get(index)));

                System.out.println("i=" + i + " j=" + j + " index=" + index + " file=" + dir + files.get(index));

                output.getGraphics().drawImage(input, i * newTileSize, j * newTileSize, null);
                index++;
            }
        }

        System.out.println("Writing: " + out);
        ImageIO.write(output, "PNG", new File(out));
    }

    public static void main(String[] argv) throws IOException {
        expand("./sprites-people.png");
    }

}
