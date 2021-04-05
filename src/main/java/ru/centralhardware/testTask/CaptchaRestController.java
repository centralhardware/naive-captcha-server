package ru.centralhardware.testTask;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class CaptchaRestController {

    private static final String CAPTCHA_TEXT_HEADER_NAME = "X-captcha_string";

    private final TextGenerator textGenerator;
    private final ImageGenerator imageGenerator;

    public CaptchaRestController(TextGenerator textGenerator, ImageGenerator imageGenerator) {
        this.textGenerator = textGenerator;
        this.imageGenerator = imageGenerator;
    }

    @GetMapping("get-captcha")
    public ResponseEntity<byte[]> getCaptcha() throws IOException {
        String captchaText = textGenerator.generate(Config.CAPTCHA_STRING_LENGTH);
        File image = imageGenerator.generate(captchaText);
        HttpHeaders headers = new HttpHeaders();
        headers.set(CAPTCHA_TEXT_HEADER_NAME, captchaText);
        return ResponseEntity.ok().
                headers(headers).
                body(Files.readAllBytes(image.toPath()));
    }

}