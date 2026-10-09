package at.fhv.devops_projekt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    private final HelloController controller = new HelloController();

    @Test
    void helloReturnsHelloWorld() {
        assertEquals("Hello World", controller.hello());
    }

    @Test
    void helloNameReturnsGreeting() {
        assertEquals("Hello Emanuel", controller.helloName("Emanuel"));
    }
}