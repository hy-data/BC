package com.bc.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.media.jai.JAI;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.SeekableStream;

/** 
 * 类名称：ImageUtil
 * 创建人：ruhe
 * 图片处理工具类
 */
public class ImageUtil {
	
	/** 
     * 图片裁切 
     * @param x1 选择区域左上角的x坐标 
     * @param y1 选择区域左上角的y坐标 
     * @param width 选择区域的宽度 
     * @param height 选择区域的高度 
     * @param sourcePath 源图片路径 
     * @param descpath 裁切后图片的保存路径 
     */  
	public void cut(int x1, int y1, int width, int height,String sourcePath, String descpath) {
		
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			File sourceFile = new File(sourcePath);
			String newSouFile = sourcePath.substring(0,sourcePath.lastIndexOf("."))+".jpg";
			RenderedImage img = null;
			
			try {
				img = ImageIO.read(sourceFile);
			} catch (Exception e) {
				SeekableStream seekableStream = new FileSeekableStream(sourceFile);
				ParameterBlock pb = new ParameterBlock();
				pb.add(seekableStream);
				img = JAI.create("jpeg", pb).getAsBufferedImage();
			}
			
			ImageIO.write(img, "jpg", new File(newSouFile));
			is = new FileInputStream(newSouFile);
			String fileSuffix = newSouFile.substring(newSouFile.lastIndexOf(".") + 1);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileSuffix);
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x1, y1, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, fileSuffix, new File(descpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				iis = null;
			}
		}

	}

	/**
	 * 图片压缩
	 * @param src
	 * @param outFile
	 * @param newWidth
	 * @param newHeight
	 */
	public void zoomImage(String src, String outFile, int newWidth, int newHeight) {

		try {
			File srcfile = new File(src);
			if (!srcfile.exists()) {
				System.out.println("文件不存在");
			}
			BufferedImage im = null;
			
			try {
				im = ImageIO.read(srcfile);
			} catch (Exception e) {
				SeekableStream seekableStream = new FileSeekableStream(srcfile);
				ParameterBlock pb = new ParameterBlock();
				pb.add(seekableStream);
				im = JAI.create("jpeg", pb).getAsBufferedImage();
			}

			/* 新生成结果图片 */
			BufferedImage result = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);

			result.getGraphics().drawImage(im.getScaledInstance(newWidth, newHeight,java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			ImageIO.write(result, "JPEG", new File(outFile));// 输出到文件流

		} catch (Exception e) {
			System.out.println("创建缩略图发生异常" + e.getMessage());
		}
	}
	
	/**
	 * 艺术品瀑布流
	 * @param src
	 * @param outFile
	 * @throws IOException
	 */
	public int artworkList(String src, String outFile) throws IOException{
		File srcfile = new File(src);
		if (!srcfile.exists()) {
			System.out.println("文件不存在");
		}
		BufferedImage im = null;
		
		
		try {
			im = ImageIO.read(srcfile);
		} catch (Exception e) {
			SeekableStream seekableStream = new FileSeekableStream(srcfile);
			ParameterBlock pb = new ParameterBlock();
			pb.add(seekableStream);
			im = JAI.create("jpeg", pb).getAsBufferedImage();
		}
		
		
		/* 原始图像的宽度和高度 */
		int width = im.getWidth();
		int height = im.getHeight();
		int newWidth = Const.PBL_WIDTH;
		int newHeight = height*newWidth/width;
		//压缩
		zoomImage(src, outFile, newWidth, newHeight);
		//如果长度过高，进行裁切
		if(newHeight>Const.MAX_HEIGHT){
			//文件重命名
			File file = new File(outFile);
			String subfix = srcfile.getName().substring(srcfile.getName().lastIndexOf("."));
			String tempFile = srcfile.getParent()+"/pultemp"+subfix;
			file.renameTo(new File(tempFile));
			int pointY = (newHeight-Const.MAX_HEIGHT)/2;
			cut(0, pointY, Const.PBL_WIDTH, Const.MAX_HEIGHT, tempFile, outFile);
			newHeight = Const.MAX_HEIGHT;
		}
		return newHeight;
	}
	
	/**
	 * 艺术品分类
	 * @param src
	 * @param outFile
	 * @throws IOException
	 */
	public void artworkType(String src, String outFile) throws IOException{
		File srcfile = new File(src);
		if (!srcfile.exists()) {
			System.out.println("文件不存在");
		}
		BufferedImage im = null;
		
		
		try {
			im = ImageIO.read(srcfile);
		} catch (Exception e) {
			SeekableStream seekableStream = new FileSeekableStream(srcfile);
			ParameterBlock pb = new ParameterBlock();
			pb.add(seekableStream);
			im = JAI.create("jpeg", pb).getAsBufferedImage();
		}
		
		/* 原始图像的宽度和高度 */
		int width = im.getWidth();
		int height = im.getHeight();
		double zipW = Const.TYPE_WIDTH/(double)width;
		double zipH = Const.TYPE_HEIGHT/(double)height;
		int newWidth;
		int newHeight;
		int pointX = 0;
		int pointY = 0;
		if(zipW>zipH){
			newWidth = Const.TYPE_WIDTH;
			newHeight = (int) (height*zipW);
			pointY = (newHeight-Const.TYPE_HEIGHT)/2;
		}else{
			newHeight = Const.TYPE_HEIGHT;
			newWidth = (int) (width*zipH);
			pointX = (newWidth-Const.TYPE_WIDTH)/2;
		}
		//压缩文件名（临时）
		String subfix = srcfile.getName().substring(srcfile.getName().lastIndexOf("."));
		String tempFile = srcfile.getParent()+"/temp"+subfix;
		//压缩
		zoomImage(src, tempFile, newWidth, newHeight);
		//裁切
		cut(pointX, pointY, Const.TYPE_WIDTH, Const.TYPE_HEIGHT, tempFile, outFile);
		new File(tempFile).deleteOnExit();
	}
	
	public static void main(String[] args) throws IOException {
		String inputFoler = "D:\\001.jpg";
		String outputFolder = "D:\\003.jpg";
		ImageUtil iu = new ImageUtil();
		iu.artworkList(inputFoler, outputFolder);
//		iu.zoomImage(inputFoler, outputFolder, 301, 144);

	}

}
