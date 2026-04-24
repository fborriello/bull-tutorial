package com.expediagroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void mainMethodStartsApplication() {
        DemoApplication.main(new String[] {});
    }

}
