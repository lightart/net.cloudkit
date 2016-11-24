import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OcrTest {

    public static int getColorBright(int rgb) {
        Color color = new Color(rgb);
        return color.getRed() + color.getGreen() + color.getBlue();
    }

    public static int isBlack(int colorInt, int blackThreshold) {
        final Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= blackThreshold) {
            return 1;
        }
        return 0;
    }

    public static int isWhite(int colorInt, int whiteThreshold) {
        final Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() >= whiteThreshold) {
            return 1;
        }
        return 0;
    }

    public static BufferedImage removeBackgroud(File imageFile) throws Exception {

        BufferedImage bufferedImage = ImageIO.read(imageFile);
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();

        // int blackThreshold = 300;
        // bufferedImage.getMinX() bufferedImage.getMinY()
        for (int x = bufferedImage.getMinX(); x < width; x++) {
            for (int y = bufferedImage.getMinY(); y < height; y++) {

                Color color = new Color(bufferedImage.getRGB(x, y));
                if((color.getBlue() < 120) || ((color.getRed() + color.getGreen() + color.getBlue()) < 50)) { //
                    bufferedImage.setRGB(x, y, Color.WHITE.getRGB());
                } else if((color.getRed() + color.getGreen() + color.getBlue()) < 400) {
                    bufferedImage.setRGB(x, y, Color.BLACK.getRGB());
                }

                int nearly = 0;
                // int vertical = 0;
                // int horizontal = 0;

                if(x > 0) {
                    Color leftColor = new Color(bufferedImage.getRGB(x - 1, y));
                    if((leftColor.getRed() + leftColor.getGreen() + leftColor.getBlue()) < 400) {
                        nearly ++;
                        // horizontal ++;
                    }
                } else if(x < width - 1) {
                    Color rightColor = new Color(bufferedImage.getRGB(x + 1, y));
                    if((rightColor.getRed() + rightColor.getGreen() + rightColor.getBlue()) < 400) {
                        nearly ++;
                        // horizontal ++;
                    }
                } else if(y > 0) {
                    Color topColor = new Color(bufferedImage.getRGB(x, y - 1));
                    if((topColor.getRed() + topColor.getGreen() + topColor.getBlue()) < 400) {
                        nearly ++;
                        // vertical ++;
                    }
                } else if(y < height - 1) {
                    Color bottomColor = new Color(bufferedImage.getRGB(x, y + 1));
                    if((bottomColor.getRed() + bottomColor.getGreen() + bottomColor.getBlue()) < 400) {
                        nearly ++;
                        // vertical ++;
                    }
                } else if(x > 0 && y > 0) {
                    Color leftTopColor = new Color(bufferedImage.getRGB(x - 1, y - 1));
                    if((leftTopColor.getRed() + leftTopColor.getGreen() + leftTopColor.getBlue()) < 400) {
                        nearly ++;
                    }
                } else if(x < width - 1 && y < height - 1) {
                    Color rightBottomColor = new Color(bufferedImage.getRGB(x + 1, y + 1));
                    if((rightBottomColor.getRed() + rightBottomColor.getGreen() + rightBottomColor.getBlue()) < 400) {
                        nearly ++;
                    }
                } else if(x < width - 1 && y > 0) {
                    Color rightTopColor = new Color(bufferedImage.getRGB(x + 1, y - 1));
                    if((rightTopColor.getRed() + rightTopColor.getGreen() + rightTopColor.getBlue()) < 400) {
                        nearly ++;
                    }
                } else if(x > 0 && y < height - 1) {
                    Color leftBottomColor = new Color(bufferedImage.getRGB(x - 1, y + 1));
                    if((leftBottomColor.getRed() + leftBottomColor.getGreen() + leftBottomColor.getBlue()) < 400) {
                        nearly ++;
                    }
                }

                if(nearly < 1) {
                    // bufferedImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        // bufferedImage = bufferedImage.getSubimage(1, 1, bufferedImage.getWidth() - 2, bufferedImage.getHeight() - 2);
        return bufferedImage;
    }

    public static void main(String[] args) throws Exception {
        // Path target = Paths.get("http://credit.customs.gov.cn/ccppCopAction/createImage.action?0.920763862762519");
        // Files.getFileStore()
        BufferedImage bufferedImage = removeBackgroud(new File("F:\\nginx-1.10.2\\html\\createImage.jpg"));
        FileOutputStream fos = new FileOutputStream("F:\\nginx-1.10.2\\html\\createImage_bak.jpg");
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
            encoder.encode(bufferedImage);
            fos.close();
    }
}
