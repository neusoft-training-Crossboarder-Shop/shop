package neu.train.project.bvo.service;


import neu.train.CrossBoarderShopApplication;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrossBoarderShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishListServiceTest {
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

    @Test
    public void test1() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity(
                        this.base.toString() + "/wishlist/1",
                        String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
        //      测试结果为：{"msg":"Your wishlist is Empty","code":500}
    }
}
