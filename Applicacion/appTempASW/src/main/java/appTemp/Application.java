package appTemp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Medicion medicion = restTemplate.getForObject("http://192.168.0.200/temperatura", Medicion.class);
        log.info(medicion.toString());
    }
}
