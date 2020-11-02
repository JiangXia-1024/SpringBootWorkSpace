//package com.springboot.springbootdemo.service;
//
//import org.springframework.stereotype.Service;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@Service
//public class ImageWatermarkService {
//
//    /**
//     * imgFile 图像文件
//     * imageFileName 图像文件名
//     * uploadPath 服务器上上传文件的相对路径
//     * realUploadPath 服务器上上传文件的物理路径
//     */
//    public String watermarkAdd(File imgFile, String imageFileName, String uploadPath, String realUploadPath ) {
//
//        String imgWithWatermarkFileName = "watermark_" + imageFileName;
//        OutputStream os = null;
//
//        try {
//            Image image = ImageIO.read(imgFile);
//
//            int width = image.getWidth(null);
//            int height = image.getHeight(null);
//
//            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  // ①
//            Graphics2D g = bufferedImage.createGraphics();  // ②
//            g.drawImage(image, 0, 0, width,height,null);  // ③
//
//            String logoPath = realUploadPath + "/" + Const.LOGO_FILE_NAME;  // 水印图片地址
//            File logo = new File(logoPath);        // 读取水印图片
//            Image imageLogo = ImageIO.read(logo);
//
//            int markWidth = imageLogo.getWidth(null);    // 水印图片的宽度和高度
//            int markHeight = imageLogo.getHeight(null);
//
//            g.setComposite( AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, Const.ALPHA) );  // 设置水印透明度
//            g.rotate(Math.toRadians(-10), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);  // 设置水印图片的旋转度
//
//           // int x = Const.X;
//           // int y = Const.Y;
//
////            int xInterval = Const.X_INTERVAL;
////            int yInterval = Const.Y_INTERVAL;
//
//            double count = 1.5;
//            while ( x < width*count ) {  // 循环添加多个水印logo
//                y = -height / 2;
//                while( y < height*count ) {
//                    g.drawImage(imageLogo, x, y, null);  // ④
//                    y += markHeight + yInterval;
//                }
//                x += markWidth + xInterval;
//            }
//
//            g.dispose();
//
//            os = new FileOutputStream(realUploadPath + "/" + imgWithWatermarkFileName);
//            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os); // ⑤
//            en.encode(bufferedImage); // ⑥
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(os!=null){
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return uploadPath + "/" + imgWithWatermarkFileName;
//    }
//
//}
//
