package br.com.menu.services;

import br.com.menu.models.dtos.MenuDto;
import br.com.menu.models.entities.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();
    Menu getById(String id);
    Menu create(MenuDto menu);
    Menu update(String id, MenuDto menu);
    void delete(String id);
}
