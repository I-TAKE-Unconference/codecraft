package com.itakeunconf.codecraft.web;

import com.itakeunconf.codecraft.CodeCraftApplication;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CodeCraftApplication.class)
@WebIntegrationTest(value = "server.port=0")
public class HomeControllerIntegrationTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void getHome() throws Exception {
        when().
                get("/").
        then()
                .statusCode(HttpStatus.OK.value());

    }
}
