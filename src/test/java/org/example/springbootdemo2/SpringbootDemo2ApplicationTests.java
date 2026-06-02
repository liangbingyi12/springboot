package org.example.springbootdemo2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration")
class SpringbootDemo2ApplicationTests {

    @Test
    void contextLoads() {
    }

}
