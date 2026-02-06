package com.gaurav.journalApp.services;

import com.gaurav.journalApp.api.response.WeatherResponce;
import com.gaurav.journalApp.cache.AppCache;
import com.gaurav.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private  String key;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponce getWeather(String city){
        String finalApi = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", key);


        ResponseEntity<WeatherResponce> res = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponce.class);
        return res.getBody();
    }

//    public WeatherResponce postWeather(String city){
//        String finalApi = API.replace("CITY", city).replace("API_KEY", key);
////        if it`s a post call
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key" , "value");
//
//        User user = User.builder().username("TestUser").password("csgsdhdh").build();
//
//        HttpEntity httpEntity = new HttpEntity<>(user,httpHeaders);
//
//        ResponseEntity<WeatherResponce> res = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponce.class);
//        return res.getBody();
//    }

}
