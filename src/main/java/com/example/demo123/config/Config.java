package com.example.demo123.config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

}
