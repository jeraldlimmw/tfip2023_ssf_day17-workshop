package sg.edu.iss.nus.day17workshop.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.iss.nus.day17workshop.model.Weather;

@Service
public class WeatherService {
    @Value("${workshop17.openweather.url}")
    private String openWeatherUrl;

    @Value("${workshop17.openweather.api.key}")
    private String openWeatherApiKey;

    public Optional<Weather> getWeather(String city, String unitMeasurement) 
            throws IOException {
        // generate the URL of the GET
        String weatherUrl = UriComponentsBuilder
                                .fromUriString(openWeatherUrl)
                                .queryParam("q", city.replaceAll(" ", "+"))
                                .queryParam("units", unitMeasurement)
                                .queryParam("appId", openWeatherApiKey)
                                .toUriString();
        
        // create new RestTemplate and ResponseEntity
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> r = template.getForEntity(weatherUrl, String.class);

        // use ResponseEntity body (String) to create weather object
        Weather w = Weather.create(r.getBody());
        // if w was created, return w, else return empty
        if(w != null) {
            return Optional.of(w);
        }
        return Optional.empty();
    }
}
