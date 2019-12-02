package com.hotels.service.movie.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotels.beans.BeanUtils;
import com.hotels.beans.transformer.BeanTransformer;
import com.hotels.transformer.model.FieldMapping;
import com.hotels.transformer.model.FieldTransformer;

@Configuration
public class MovieSvcTransformerConfig {
    @Value("${movie.vat}")
    private int vat;

    @Bean
    public BeanTransformer movieSvcResponseTransformer(final BeanUtils beanUtils) {
        return beanUtils.getTransformer()
                .withFieldMapping(new FieldMapping<>("price", "price.netPrice"))
                .withFieldMapping(new FieldMapping<>("price", "price.grossPrice"))
                .withFieldTransformer(new FieldTransformer<>("price.grossPrice", addVat()));
    }

    /**
     * Adds the vat to the net price.
     * @return the price including vat.
     */
    private Function<Float, Float> addVat() {
        return val -> {
            float taxes = (val * vat) / 100;
            return val + taxes;
        };
    }

}
