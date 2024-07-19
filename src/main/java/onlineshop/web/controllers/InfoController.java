package onlineshop.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/")
    public ResponseEntity<String> getInfoShop() {
        return new ResponseEntity<>("Orion Shop! Welcome...",
                HttpStatus.OK);
    }
}
