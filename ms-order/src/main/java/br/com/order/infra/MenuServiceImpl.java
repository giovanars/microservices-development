package br.com.order.infra;

import br.com.order.constants.OrderConstant;
import br.com.order.models.dtos.MenuResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public MenuResponse GetMenuById(String id) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(OrderConstant.MenuUrl + "/menu/" + id)
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MenuResponse menuResponse = mapper.readValue(response.body().byteStream(), MenuResponse.class);
        return menuResponse;

    }
}
