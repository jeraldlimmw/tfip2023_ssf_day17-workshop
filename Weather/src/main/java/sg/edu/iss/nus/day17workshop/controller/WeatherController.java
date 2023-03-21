package sg.edu.iss.nus.day17workshop.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.nus.day17workshop.model.Weather;
import sg.edu.iss.nus.day17workshop.service.WeatherService;

@Controller
@RequestMapping(path="/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService wSvc;

    @GetMapping
    public String getWeather(@RequestParam(required=true) String city,
            @RequestParam(defaultValue="metric", required=false) String units, 
            Model m) throws IOException {
        
        Optional<Weather> w = wSvc.getWeather(city, units);
        m.addAttribute("weather", w.get());

        return "weather";
    }

}
