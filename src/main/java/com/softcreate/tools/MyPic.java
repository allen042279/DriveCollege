package com.softcreate.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

//缩略图类，
//本java类能将jpg图片文件，进行等比或非等比的大小转换。
//具体使用方法
//s_pic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
@SuppressWarnings("restriction")
public class MyPic {

	public static boolean scalePic(String InputFileName, String OutputFileName) {

		boolean retFlag = false;
		
		int OutputWidth = 90; // 默认输出图片宽
		int OutputHeight = 80; // 默认输出图片高
		boolean proportion = false; // 是否等比缩放标记(默认为等比缩放)

		// BufferedImage image;
		// String NewFileName;
		
		FileOutputStream tempout = null;
		
		try {
			
			tempout = new FileOutputStream(new File(OutputFileName));
			Image img = ImageIO.read(new File(InputFileName));
			
			if (img.getWidth(null) != -1) {

				int new_w;
				int new_h;
				
				if (proportion == true) { // 判断是否是等比缩放.
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null)) / (double) OutputWidth + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) OutputHeight + 0.1;
					
					double rate = rate1 > rate2 ? rate1 : rate2;
					
					new_w = (int) (((double) img.getWidth(null)) / rate);
					new_h = (int) (((double) img.getHeight(null)) / rate);
					
				} else {
					new_w = OutputWidth; // 输出的图片宽度
					new_h = OutputHeight; // 输出的图片高度
				}
				
				BufferedImage buffImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
	
				Graphics graphics = buffImg.createGraphics();
	
				graphics.setColor(Color.white);
				graphics.fillRect(0, 0, new_w, new_h);
	
				graphics.drawImage(img, 0, 0, new_w, new_h, null);
				graphics.dispose();
	
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
				encoder.encode(buffImg);
				
				retFlag = true;
			}
			
		} catch (Exception ex) {
			
			System.out.println(ex.toString());
			
		} finally{
			
			try {
				
				if(tempout!=null){
					tempout.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 建立输出文件对象
//		File file = new File(OutputFileName);
//		FileOutputStream tempout = null;
//		
//		try {
//			tempout = new FileOutputStream(file);
//			
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//		}
//		
//		 System.out.println("Toolkit.getDefaultToolkit()!" + "<BR>");
//		 
//		Image img = null;
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		
//		Applet app = new Applet();
//		
//		MediaTracker mt = new MediaTracker(app);
//		
//		
//		try {
//			img = tk.getImage(InputFileName);
//			mt.addImage(img, 0);
//			mt.waitForID(0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (img.getWidth(null) == -1) {
//			 System.out.println(" can't read,retry!" + "<BR>");
//			return false;
//		} else {
//			int new_w;
//			int new_h;
//			if (proportion == true) { // 判断是否是等比缩放.
//				// 为等比缩放计算输出的图片宽度及高度
//				double rate1 = ((double) img.getWidth(null))
//						/ (double) OutputWidth + 0.1;
//				double rate2 = ((double) img.getHeight(null))
//						/ (double) OutputHeight + 0.1;
//				double rate = rate1 > rate2 ? rate1 : rate2;
//				new_w = (int) (((double) img.getWidth(null)) / rate);
//				new_h = (int) (((double) img.getHeight(null)) / rate);
//			} else {
//				new_w = OutputWidth; // 输出的图片宽度
//				new_h = OutputHeight; // 输出的图片高度
//			}
//			BufferedImage buffImg = new BufferedImage(new_w, new_h,
//					BufferedImage.TYPE_INT_RGB);
//
//			Graphics g = buffImg.createGraphics();
//
//			g.setColor(Color.white);
//			g.fillRect(0, 0, new_w, new_h);
//
//			g.drawImage(img, 0, 0, new_w, new_h, null);
//			g.dispose();
//
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
//			try {
//				encoder.encode(buffImg);
//				tempout.close();
//			} catch (IOException ex) {
//				System.out.println(ex.toString());
//			}
//		}
		
		return retFlag;
	}

}