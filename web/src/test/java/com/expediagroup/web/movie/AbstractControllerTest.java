package com.expediagroup.web.movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * Common testing object.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class AbstractControllerTest {
    private static final int PORT = 9999;
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
