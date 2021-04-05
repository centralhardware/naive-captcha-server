package ru.centralhardware.testTask;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class CaptchaRestController {

    private static final String CAPTCHA_TEXT_HEADER_NAME = "X-captcha_string";
    private static final String CACHE_CONTROL_HEADER_NAME = "Cache-Control";
    public static final String IMAGE_PATH_HEADER_NAME  = "image-file-path";

    private final TextGenerator textGenerator;
    private final CaptchaImageGenerator imageGenerator;

    public CaptchaRestController(TextGenerator textGenerator, CaptchaImageGenerator imageGenerator) {
        this.textGenerator = textGenerator;
        this.imageGenerator = imageGenerator;
    }


    /**
     * не имеет смыслы возращать правильный текст капчи, так как такая система будет обсолютна не устойчива к взлому
     * достаточно будет использовать curl или аналогичный инструмент для просмотра правильного ответа, Однако можно
     * добиться устойчивости к данной уязыимости если отсылать не оригинальный текст а его криптостокимй хеш
     * @param request need to pass value (Path to captcha image file) to interceptor
     */
    @GetMapping("/get-captcha")
    public ResponseEntity<InputStreamResource> getCaptcha(HttpServletRequest request) throws IOException {
        String captchaText = textGenerator.generate(Config.CAPTCHA_STRING_LENGTH);
        File image = imageGenerator.generate(captchaText);
        HttpHeaders headers = new HttpHeaders();
        headers.set(CAPTCHA_TEXT_HEADER_NAME, captchaText);
        headers.set(CACHE_CONTROL_HEADER_NAME, "no-cache");
        request.getSession().setAttribute(IMAGE_PATH_HEADER_NAME, image.getAbsolutePath());
        return ResponseEntity.ok().
                headers(headers).
                contentLength(image.length()).
                contentType(MediaType.IMAGE_PNG).
                body(new InputStreamResource(new FileInputStream(image)));
    }

}