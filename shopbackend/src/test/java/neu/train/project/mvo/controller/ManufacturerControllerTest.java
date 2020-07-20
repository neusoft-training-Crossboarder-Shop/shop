package neu.train.project.mvo.controller;


import neu.train.CrossBoarderShopApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrossBoarderShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ManufacturerControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {

        String url = String.format("http://localhost:%d/swagger-resources", port);
        System.out.println(String.format("port is : [%d]", port));

        this.base = new URL(url);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getManufacturer() {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity(
                        this.base.toString() + "//profile",
                        String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));

    }

    @Test
    public void insertManufacturer() {
    }

    @Test
    public void updateManufacturer() {
    }
}