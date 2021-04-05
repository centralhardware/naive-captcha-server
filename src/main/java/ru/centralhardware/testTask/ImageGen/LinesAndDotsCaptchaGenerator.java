package ru.centralhardware.testTask.ImageGen;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * implementation of a captcha generator based on random points and lines
 */
@Component
public class LinesAndDotsCaptchaGenerator implements CaptchaImageGenerator {

    private static final int HEIGHT = 240;
    private static final int WIDTH = 540;
    private static final int FONT_SIZE = 60;
    private static final int DOTS_COUNT = 50;
    private static final int LINES_COUNT = 3;

    @Override
    public File generate(String randomText) {
        File res = null;
        try {
            res = Files.createTempFile("captcha_server", ".png").toFile();
            res.deleteOnExit();
            BufferedImage captcha = generateImageWithText(randomText);
            addNoise(captcha);
            addLines(captcha);
            ImageIO.write(captcha, "png", res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * create image with giving text
     *
     * @param text text that places on image
     * @return image
     */
    private BufferedImage generateImageWithText(String text) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));
        g2d.drawString(text, 180, 160);
        return image;
    }

    private final SecureRandom random = new SecureRandom();

    /**
     * add 50 randomly placed dots
     *
     * @param bufferedImage image to ad noise
     */
    private void addNoise(BufferedImage bufferedImage) {
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < DOTS_COUNT; i++) {
            g2d.fillRect(random.nextInt(WIDTH + 1), random.nextInt(HEIGHT + 1), 8, 8);
        }
    }

    /**
     * add tree random lines that stretch from the left edge to the right
     *
     * @param bufferedImage image to add Lines
     */
    private void addLines(BufferedImage bufferedImage) {
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < LINES_COUNT; i++) {
            g2d.drawLine(0, random.nextInt(WIDTH + 1), WIDTH, random.nextInt(HEIGHT + 1));
        }
    }

}
