package org.antinori.game.utils;

//http://www.reinerstilesets.de/2d-grafiken/2d-humans/

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.antinori.game.LifeMain;

public class ExpandSprites {
	
	public static String dir = "C:\\Users\\Paul\\Desktop\\boy red pants bitmaps\\";
	
	private static void expand(String in, String out) throws IOException {
		
		ArrayList<String> files = new ArrayList<String>();

		files.add("walking e0000.bmp");
		files.add("walking e0001.bmp");
		files.add("walking e0002.bmp");
		files.add("walking e0003.bmp");
		files.add("walking e0004.bmp");
		files.add("walking e0005.bmp");
		files.add("walking e0006.bmp");
		files.add("walking e0007.bmp");
		files.add("walking n0000.bmp");
		files.add("walking n0001.bmp");
		files.add("walking n0002.bmp");
		files.add("walking n0003.bmp");
		files.add("walking n0004.bmp");
		files.add("walking n0005.bmp");
		files.add("walking n0006.bmp");
		files.add("walking n0007.bmp");
		files.add("walking ne0000.bmp");
		files.add("walking ne0001.bmp");
		files.add("walking ne0002.bmp");
		files.add("walking ne0003.bmp");
		files.add("walking ne0004.bmp");
		files.add("walking ne0005.bmp");
		files.add("walking ne0006.bmp");
		files.add("walking ne0007.bmp");
		files.add("walking nw0000.bmp");
		files.add("walking nw0001.bmp");
		files.add("walking nw0002.bmp");
		files.add("walking nw0003.bmp");
		files.add("walking nw0004.bmp");
		files.add("walking nw0005.bmp");
		files.add("walking nw0006.bmp");
		files.add("walking nw0007.bmp");
		files.add("walking s0000.bmp");
		files.add("walking s0001.bmp");
		files.add("walking s0002.bmp");
		files.add("walking s0003.bmp");
		files.add("walking s0004.bmp");
		files.add("walking s0005.bmp");
		files.add("walking s0006.bmp");
		files.add("walking s0007.bmp");
		files.add("walking se0000.bmp");
		files.add("walking se0001.bmp");
		files.add("walking se0002.bmp");
		files.add("walking se0003.bmp");
		files.add("walking se0004.bmp");
		files.add("walking se0005.bmp");
		files.add("walking se0006.bmp");
		files.add("walking se0007.bmp");
		files.add("walking sw0000.bmp");
		files.add("walking sw0001.bmp");
		files.add("walking sw0002.bmp");
		files.add("walking sw0003.bmp");
		files.add("walking sw0004.bmp");
		files.add("walking sw0005.bmp");
		files.add("walking sw0006.bmp");
		files.add("walking sw0007.bmp");
		files.add("walking w0000.bmp");
		files.add("walking w0001.bmp");
		files.add("walking w0002.bmp");
		files.add("walking w0003.bmp");
		files.add("walking w0004.bmp");
		files.add("walking w0005.bmp");
		files.add("walking w0006.bmp");
		files.add("walking w0007.bmp");
		
		int newTileSize = 96;
		int newTilesX = 8;
		int newTilesY = 8;
				
		BufferedImage output = new BufferedImage(newTileSize*newTilesX, newTileSize*newTilesY, BufferedImage.TYPE_INT_ARGB);
		
							
		int index = 0;
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				
				//BufferedImage input = LifeMain.loadIcon(dir + files.get(index));
				BufferedImage input = ImageIO.read(new File(dir + files.get(index)));
	
				System.out.println("i="+i+" j="+j +" index="+ index+" file=" +dir + files.get(index));
				
				output.getGraphics().drawImage(input,i*newTileSize, j*newTileSize, null);
				index ++;
			}
		}
		
		System.out.println("Writing: "+out);
		ImageIO.write(output, "PNG", new File(out));
	}
	
	public static void main(String[] argv) throws IOException {
		expand("./sprites-people.png", "expanded.bmp");
	}

}
