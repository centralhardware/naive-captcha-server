package ru.centralhardware.testTask;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

@Component
public class RandomImageGenerator implements ImageGenerator {
    @Override
    public File generate(String randomText) {
        File res = null;
        try {
            res = Files.createTempFile("", ".png").toFile();
            res.deleteOnExit();
            BufferedImage captcha = generateImageWithText(randomText);
            captcha = addNoise(captcha);
            captcha = addLines(captcha);
            ImageIO.write(captcha, "png", res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private BufferedImage generateImageWithText(String text){
        BufferedImage image = new BufferedImage(540, 240, TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g2d.drawString(text, 180, 160);
        return image;
    }

    private final SecureRandom random = new SecureRandom();

    private BufferedImage addNoise(BufferedImage bufferedImage){
        BufferedImage res = bufferedImage;
        Graphics2D g2d = res.createGraphics();
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            g2d.fillRect(random.nextInt(541),random.nextInt(241), 8, 8);
        }
        return res;
    }

    private BufferedImage addLines(BufferedImage bufferedImage){
        BufferedImage res = bufferedImage;
        Graphics2D g2d = res.createGraphics();
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            g2d.drawLine(0,random.nextInt(241), 540, random.nextInt(241));
        }
        return bufferedImage;
    }

}
