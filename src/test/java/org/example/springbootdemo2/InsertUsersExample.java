package org.example.springbootdemo2;

import org.example.springbootdemo2.entity.Users;
import org.example.springbootdemo2.service.UsersService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

public class InsertUsersExample {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringbootDemo2Application.class)
                .web(WebApplicationType.NONE)
                .run(args)) {

            UsersService usersService = context.getBean(UsersService.class);

            long now = System.currentTimeMillis();
            Users user = new Users();
            user.setName("test-user-" + now);
            user.setEmail("test-user-" + now + "@example.com");
            user.setCreatedAt(LocalDateTime.now());

            boolean saved = usersService.save(user);
            System.out.println("saved = " + saved);
            System.out.println("inserted id = " + user.getId());
        }
    }

}
