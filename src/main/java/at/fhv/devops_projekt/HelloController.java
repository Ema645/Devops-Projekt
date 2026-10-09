package at.fhv.devops_projekt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        if (name == null || name.isBlank()) {
            return "Hello Unknown";
        }
        return "Hello " + name;
    }
}