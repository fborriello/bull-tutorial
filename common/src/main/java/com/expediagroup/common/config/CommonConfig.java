package com.expediagroup.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.expediagroup.beans.BeanUtils;

/**
 * Common configurations.
 * @author borriello.fabio
 */
@Configuration
public class CommonConfig {

    /**
     * BULL library instance.
     * @return BULL instance.
     */
    @Bean
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }
}
