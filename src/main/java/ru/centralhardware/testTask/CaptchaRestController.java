package ru.centralhardware.testTask;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class CaptchaRestController {

    private static final String CAPTCHA_TEXT_HEADER_NAME = "X-captcha_string";
    private static final String CACHE_CONTROL_HEADER_NAME = "Cache-Control";

    private final TextGenerator textGenerator;
    private final ImageGenerator imageGenerator;

    public CaptchaRestController(TextGenerator textGenerator, ImageGenerator imageGenerator) {
        this.textGenerator = textGenerator;
        this.imageGenerator = imageGenerator;
    }


    /**
     * не имеет смыслы возращать правильный текст капчи, так как такая система будет обсолютна не устойчива к взлому
     * достаточно будет использовать curl или аналогичный инструмент для просмотра правильного ответа, Однако можно
     * добиться устойчивости к данной уязыимости если отсылать не оригинальный текст а его криптостокимй хеш
     */
    @GetMapping("/get-captcha")
    public ResponseEntity<InputStreamResource> getCaptcha() throws IOException {
        String captchaText = textGenerator.generate(Config.CAPTCHA_STRING_LENGTH);
        File image = imageGenerator.generate(captchaText);
        HttpHeaders headers = new HttpHeaders();
        headers.set(CAPTCHA_TEXT_HEADER_NAME, captchaText);
        headers.set(CACHE_CONTROL_HEADER_NAME, "no-cache");
        return ResponseEntity.ok().
                headers(headers).
                contentLength(image.length()).
                contentType(MediaType.IMAGE_PNG).
                body(new InputStreamResource(new FileInputStream(image)));
    }

}