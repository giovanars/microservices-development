package br.com.menu.controllers;

import br.com.menu.models.dtos.MenuDto;
import br.com.menu.models.entities.Menu;
import br.com.menu.services.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAl() {
        return ResponseEntity.ok(menuService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody MenuDto request) {
        return ResponseEntity.ok(menuService.create(request));
    }


    @PutMapping("{id}")
    public ResponseEntity<Menu> update(@PathVariable("id") String id, @RequestBody MenuDto request) {
        return ResponseEntity.ok(menuService.update(id, request));
    }

    @DeleteMapping("{id}")
        public ResponseEntity<String> delete(@PathVariable String id) {
        menuService.delete(id);
        return ResponseEntity.ok("Menu deletado");
    }


}
