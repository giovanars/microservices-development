package br.com.menu.services;

import br.com.menu.models.dtos.MenuDto;
import br.com.menu.models.entities.Menu;
import br.com.menu.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getById(String id) {
        return menuRepository.findById(id).get();
    }

    @Override
    public Menu create(MenuDto menuDto) {
        return menuRepository.save(new Menu(menuDto));
    }

    @Override
    public Menu update(String id, MenuDto menuDto) {
        Menu menu = new Menu(menuDto);
        menu.setId(id);
        return menuRepository.save(menu);
    }

    @Override
    public void delete(String id) {
        menuRepository.delete(menuRepository.findById(id).get());
    }
}
