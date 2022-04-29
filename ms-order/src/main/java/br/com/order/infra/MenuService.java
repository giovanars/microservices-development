package br.com.order.infra;

import br.com.order.models.dtos.MenuResponse;


import java.io.IOException;


public interface MenuService {
    MenuResponse GetMenuById(String id) throws IOException;
}
