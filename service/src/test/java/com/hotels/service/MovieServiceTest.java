package com.hotels.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest("service.message=Hello")
public class MovieServiceTest {

//    @Autowired
    private MovieService movieService;

//    @Test
//    public void contextLoads() {
//        assertThat(movieService.message()).isNotNull();
//    }

//    @SpringBootApplication
    static class TestConfiguration {
    }

}
