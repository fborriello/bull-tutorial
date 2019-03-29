package com.hotels.common.config;

import com.hotels.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }
}
