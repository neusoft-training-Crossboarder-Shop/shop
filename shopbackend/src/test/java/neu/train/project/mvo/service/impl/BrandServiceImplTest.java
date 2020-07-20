//package neu.train.project.mvo.service.impl;
//
//
//import eu.bitwalker.useragentutils.Application;
//import neu.train.project.mvo.domain.mvoBrand;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class BrandServiceImplTest {
//    @Autowired
//    private BrandServiceImpl brandService;
//    private mvoBrand brand;
//
//    @Before
//    @Transactional
//    public void before() throws Exception{
//        brand = new mvoBrand();
//        brand.setManId(111);
//        brand.setBrdId(222);
//        brand.setNameCn("北京滔博");
//        brand.setNameEn("TES");
//        brand.setBrdDesc("昌平北苑一号店");
//        brand.setStsCd("NORMAL");
//    }
//    @After
//    public void after() throws Exception {
//    }
//
//    @Test
//    public void insertBrand() throws Exception {
//        String num = brandService.insertBrand(brand);
//        System.out.println(brand);
//    }
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void selectBrandByBrandId() {
//    }
//
//    @Test
//    public void getBrandList() {
//    }
//
//    @Test
//    public void getBrandList1() {
//    }
//
//    @Test
//    public void checkExistByNameCn() {
//    }
//
//    @Test
//    public void checkExistByNameEn() {
//    }
//
//    @Test
//    public void insertBrand() {
//    }
//
//    @Test
//    public void updateBrand() {
//    }
//
//    @Test
//    public void deleteBrandByIds() {
//    }
//
//    @Test
//    public void clearCache() {
//    }
//
//    @Test
//    public void updateBrandImage() {
//    }
//}