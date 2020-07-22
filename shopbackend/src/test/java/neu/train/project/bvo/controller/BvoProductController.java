package neu.train.project.bvo.controller;

import neu.train.CrossBoarderShopApplication;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.bvo.domain.productDomain.Product;
import org.assertj.core.api.IntegerAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.HashMap;

/**
 *  测试之前 需要取消 @Requestmapping("swagger-resources")的注解
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrossBoarderShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BvoProductController {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setUp() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));
        String url = String.format("http://localhost:%d/swagger-resources/bvo", port);
        this.base = new URL(url);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetProductDetail1() throws Exception {
        String response = restTemplate.getForObject(base + "/good/1", String.class, "");
        System.out.println(String.format("测试结果为：%s", response));
    }

    /**
     *
     *
     * @throws Exception
     */
    @Test
    public void Test_getProductDetail2() throws Exception {
        AjaxResult response = restTemplate.getForObject(base + "/good/null", AjaxResult.class, "");
        System.out.println(String.format("测试结果为：%s", response));
        Assert.assertEquals(1, java.util.Optional.ofNullable(((Product) response.get("data")).getProId()));
    }

    @Test
    public void Test_getBrowseList() {
        AjaxResult response = restTemplate.getForObject(base + "/good/browse", AjaxResult.class, "");
        System.out.println(String.format("测试结果为：%s", response));
        Assert.assertNotEquals(0, java.util.Optional.ofNullable(((Product) response.get("total"))));
    }

    @Test
    public void Test_getBrowseList_WithCondition(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("categoryName", "Air");
        AjaxResult response = restTemplate.getForObject(base + "/good/browse", AjaxResult.class, params);
        System.out.println(String.format("测试结果为：%s", response));
        Assert.assertNotEquals(0, java.util.Optional.ofNullable(((Product) response.get("total"))));
    }

    @Test
    public void Test_addToWishList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", "1");
        params.put("dsrId", "1");
        AjaxResult response = restTemplate.postForObject(base + "/wishlist",params, AjaxResult.class);
        System.out.println(String.format("测试结果为：%s", response));
        Assert.assertNotNull(java.util.Optional.ofNullable(((Product) response.get("data"))));
    }
}
