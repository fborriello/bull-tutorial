package com.hotels.dao.movie.config;

import static org.apache.commons.lang3.StringUtils.SPACE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotels.beans.BeanUtils;
import com.hotels.beans.transformer.BeanTransformer;
import com.hotels.transformer.model.FieldTransformer;

@Configuration
public class MovieDaoTransformerConfig {
    @Bean
    public BeanTransformer movieDaoResponseTransformer(final BeanUtils beanUtils) {
        return beanUtils.getTransformer()
                .setFlatFieldNameTransformation(true)
                .withFieldTransformer(new FieldTransformer<String, String>("name", fullName -> fullName.split(SPACE)[0]))
                .withFieldTransformer(new FieldTransformer<String, String>("surname", fullName -> fullName.split(SPACE)[1]));
    }
}
