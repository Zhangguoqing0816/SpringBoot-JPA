package com.me.testjpa.jpa.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Auther:
 * @Description:
 * @Date: 2019/10/11 11:15
 */
public class MyTestIo {

    private String path = "C:\\Users\\zhiwang\\Desktop\\pic.png";

    public static void main(String[] args) {
        byte[] bytes = fileToByte("C:\\Users\\zhiwang\\Desktop\\pic.png");
        ByteToFile(bytes);
    }

    public static byte[] fileToByte(String imgPath) {
        byte[] bytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            BufferedImage bi = ImageIO.read(new File(imgPath));
            ImageIO.write(bi, "png", out);
            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static void ByteToFile(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            File file = new File("G:\\haha.png");
            BufferedImage bi = ImageIO.read(bis);
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取图片化为 byte 数组
     *
     * @param string
     * @return
     */
    public byte[] getByte(String string) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = bis.read(temp)) != -1) {
                bos.write(temp, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte[] content = bos.toByteArray();
        return content;
    }
}
