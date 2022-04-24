package br.com.menu.controllers;

import br.com.menu.models.Menu;
import br.com.menu.services.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class AbcController {

    private final MenuService menuService;

    public AbcController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(menuService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus(){
        return ResponseEntity.ok(menuService.getAll());
    }

    @PostMapping
    public ResponseEntity<Menu> addMenu(@RequestBody Menu request){
        return ResponseEntity.ok(menuService.add(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<Menu> updateById(@PathVariable("id") Long id, @RequestBody Menu menu){
        return ResponseEntity.ok(menuService.update(id, menu));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> updateById(@PathVariable("id") Long id){
        menuService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
