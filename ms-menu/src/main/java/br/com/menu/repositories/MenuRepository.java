package br.com.menu.repositories;

import br.com.menu.models.entities.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<Menu, String> {
}
