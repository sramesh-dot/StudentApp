package com.example.studentApp.config;

import org.springframework.stereotype.Component;

@Component
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "college")

public class ConfigurationProperties {

    private String name;
    private String city;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
