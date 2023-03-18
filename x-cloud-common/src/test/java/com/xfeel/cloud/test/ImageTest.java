package com.xfeel.cloud.test;

import org.junit.Test;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

	@Test
	public void drawString() throws IOException {
		String text = "测试设么ddddd";
		draw(text, new File("/Users/dyfeelme/Workspace/Backup/font.png"));

	}

	public void draw(String text, File file) throws IOException {
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 24);
		FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);

		int width = getWidth(text, metrics);
		int height = getHeight(metrics);

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

		g.setColor(Color.WHITE);
		g.fillRect(0,0,width,height);
		g.setFont(font);
		g.setColor(Color.BLACK);

		g.drawString(text,0,metrics.getAscent());
		g.dispose();


		ImageIO.write(image, "png", file);
	}

	private int getHeight(FontDesignMetrics fontDesignMetrics){
		return fontDesignMetrics.getHeight();
	}

	private int getWidth(String content, FontDesignMetrics fontDesignMetrics){

		int width = 0;
		if (content == null){
			return width;
		}
		for (int i = 0; i < content.length(); i++) {
			width += fontDesignMetrics.charWidth(content.charAt(i));
		}
		return width;

	}
}
