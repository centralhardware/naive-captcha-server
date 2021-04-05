package ru.centralhardware.testTask;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaptchaRestController {

    @GetMapping("get-captcha")
    public ResponseEntity<?> getCaptcha(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.set();
    }

}
