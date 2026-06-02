package org.example.springbootdemo2.controller;

import org.example.springbootdemo2.entity.Users;
import org.example.springbootdemo2.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户前端控制器。
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> list() {
        return usersService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable Integer id) {
        Users user = usersService.getById(id);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @PostMapping
    public Users create(@RequestBody Users user) {
        usersService.save(user);
        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Integer id, @RequestBody Users user) {
        if (usersService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        usersService.updateById(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return usersService.removeById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
