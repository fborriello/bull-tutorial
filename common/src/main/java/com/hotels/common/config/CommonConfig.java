package com.hotels.common.config;

import com.hotels.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
