package com.softcreate.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用
 */
public class QRCodeUtil {
	
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private QRCodeUtil() {
		
	}

	/**
	 * @param contents  二维码内容
	 * @param width   二维码图片宽度
	 * @param height  二维码图片高度
	 * @param fileString
	 * @throws Exception
	 */
	public static void makeQRCode(String contents, int width, int height, String fileString) throws Exception {

		String format = "png";// 二维码的图片格式
		
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");	// 内容所使用字符集编码
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
		
		// 生成二维码
		File outputFile = new File(fileString + ".png");
		writeToFile(bitMatrix, format, outputFile);
	}

	
	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

//	private static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
//		BufferedImage image = toBufferedImage(matrix);
//		if (!ImageIO.write(image, format, stream)) {
//			throw new IOException("Could not write an image of format " + format);
//		}
//	}
}
