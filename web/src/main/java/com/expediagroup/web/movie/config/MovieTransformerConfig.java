package com.expediagroup.web.movie.config;

import static org.apache.commons.lang3.StringUtils.SPACE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.transformer.model.FieldMapping;
import com.expediagroup.transformer.model.FieldTransformer;


@Configuration
public class MovieTransformerConfig {
    @Bean
    public BeanTransformer movieResponseTransformer(final BeanUtils beanUtils) {
        return beanUtils.getTransformer()
                .setFlatFieldNameTransformation(true)
                .withFieldMapping(new FieldMapping<>("fullName", "name"))
                .withFieldMapping(new FieldMapping<>("fullName", "surname"))
                .withFieldTransformer(new FieldTransformer<String, String>("name", fullName -> fullName.split(SPACE)[0]))
                .withFieldTransformer(new FieldTransformer<String, String>("surname", fullName -> fullName.split(SPACE)[1]));
    }
}
