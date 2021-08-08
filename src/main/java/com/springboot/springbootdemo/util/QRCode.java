package com.springboot.springbootdemo.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

public class QRCode {
    //编码格式
    private static final String CHARSET = "utf-8";
    //生成的文件类型，这里为jpg格式
    private static final String FORMAT_NAME = "jpg";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    //宽度
    private static final int WIDTH = 60;
    //高度
    private static final int HEIGHT = 60;

    /**
     * 生成图片
     * @param content 具体内容 比如这里是网址
     * @param imgPath 图片的路径
     * @param needCompress 是否需要压缩图片，当图片的尺寸大于二维码尺寸时对其进行压缩
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 调用插入图片的方法插入自定义图片
        QRCode.insertImage(image, imgPath, needCompress);
        return image;
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        //首先需要判断图片是否存在
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        //读取图片
        Image src = ImageIO.read(new File(imgPath));
        //获取图片的宽度和高度
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 当图片大小过大时对其进行压缩
        if (needCompress) {
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            //绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        //插入图片
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    //真正的生成二维码的方法
    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCode.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        String file = System.currentTimeMillis()+".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));

    }

//    public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {
//        BufferedImage image = QRCode.createImage(content, imgPath, needCompress);
//        return image;
//    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

//    public static void encode(String content, String imgPath, String destPath) throws Exception {
//        QRCode.encode(content, imgPath, destPath, false);
//    }


    public static void encode(String content, String destPath) throws Exception {
        QRCode.encode(content, null, destPath, false);
    }

//    public static void encode(String content, OutputStream output) throws Exception {
//        QRCode.encode(content, null, output, false);
//    }

    /**
     * 对二维码进行解析的具体方法
     * @param file
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }
    /**
     * 对二维码进行解析
     * @param path
     * @return
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCode.decode(new File(path));
    }
    

    public static void main(String[] args) throws Exception {
        //自定义的网站
        String text = "http://www.baidu.com";
        //二维码包含的图片
        String logoPath = "C:\\Users\\Administrator\\Desktop\\1de5159fb88f492a666a1976f5495f43.jpg";
        //二维码保存的地址
        String destPath = "C:\\Users\\Administrator\\Desktop\\test";
        //生成二维码
        QRCode.encode(text, logoPath, destPath, true);
        // 解析二维码
        String str = QRCode.decode("C:\\Users\\Administrator\\Desktop\\test\\1628420962518.jpg");
        // 打印出解析出的内容
        System.out.println(str);
    }
}