package com.hotels.service.movie.config;

import com.hotels.beans.BeanUtils;
import com.hotels.beans.model.FieldMapping;
import com.hotels.beans.model.FieldTransformer;
import com.hotels.beans.transformer.Transformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Configuration
public class MovieSvcTransformerConfig {
    @Value("${movie.vat}")
    private int vat;

    @Bean
    public Transformer movieSvcResponseTransformer(final BeanUtils beanUtils) {
        return beanUtils.getTransformer()
                .withFieldMapping(new FieldMapping("price", "price.netPrice"))
                .withFieldMapping(new FieldMapping("price", "price.grossPrice"))
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
