package neu.train.project.mvo.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import neu.train.common.exception.job.TaskException;
import neu.train.framework.redis.RedisCache;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.mvo.mapper.ManManufacturerMapper;
import neu.train.project.mvo.pojo.ManManufacturer;
import neu.train.project.mvo.pojo.ManManufacturerExample;
import neu.train.project.mvo.service.TTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/swagger-resources/")

public class TTTController extends BaseController {
    //分页查询功能都在BaseController里，不得不继承
    @Autowired
    private ManManufacturerMapper manManufacturerMapper;

    @Autowired
    private TTTService tttService;

    //普通消息OK，mybatis-generatorOK，懒加载，分页查询OK，事务OK，redis，exception全局异常处理OK，validate@notNull??有用吗，springscurity？？？haspermi???
    @ApiOperation(value = "post请求返回一个成功普通消息,一般来说返回的是AjaxResult")
    @PostMapping("/test1")
    public AjaxResult test() {
        return AjaxResult.success("你好骚啊");
    }

    @ApiOperation(value = "测试一下mybatis-generator直接生成，这里实体类生成后还没继承BaseEntity，一会redis看看不继承会怎么羊")
    @PostMapping("/test2")
    public AjaxResult test2(@RequestBody HashMap<String, Integer> id) {
        {
        ManManufacturerExample manManufacturerExample = new ManManufacturerExample();
        ManManufacturerExample.Criteria manManufacturerExampleCriteria=manManufacturerExample.createCriteria();
        manManufacturerExampleCriteria.andManIdEqualTo(id.get("man_id"));
        return AjaxResult.success(manManufacturerMapper.selectByExampleWithBLOBs(manManufacturerExample));
        }
    }

    @ApiOperation(value="测试一下分页查询")
    @GetMapping("/test3")
    public TableDataInfo test3(){
        ManManufacturerExample manManufacturerExample = new ManManufacturerExample();
        ManManufacturerExample.Criteria manManufacturerExampleCriteria=manManufacturerExample.createCriteria();
        manManufacturerExampleCriteria.andManIdGreaterThan(3);
        //可以在这里直接set
        PageHelper.startPage(1, 3);
        startPage();
        List<ManManufacturer> manManufacturers=manManufacturerMapper.selectByExampleWithBLOBs(manManufacturerExample);
        //这里getDataTable是BaseController自带的返回TableDataInfo的方法
        return getDataTable(manManufacturers);
    }

    @Transactional
    @ApiOperation(value="测试transactional,顺便测试插入方法")
    @GetMapping("/test4")
    public void test4(){
        //伪造点数据
        ManManufacturerExample manManufacturerExample=new ManManufacturerExample();
        ManManufacturerExample.Criteria manManufacturerExampleCriteria=manManufacturerExample.createCriteria();
        manManufacturerExampleCriteria.andManIdGreaterThan(3);
        List<ManManufacturer> manManufacturers=manManufacturerMapper.selectByExampleWithBLOBs(manManufacturerExample);
        ManManufacturer m1=manManufacturers.get(0);
        m1.setManId(null);
        ManManufacturer m2=manManufacturers.get(1);
        m2.setManId(null);
        //这个地方你要想拿到插入的主键，你得在mapper里insert方法的上一行@Select里面改，改Before=false
        //LastInsertId是按照sql connection来的，也就是说挂机时间长了，直接select它也会变成0的
        manManufacturerMapper.insert(m1);
        System.out.println(m1.getManId());
        manManufacturerMapper.insert(m2);
        System.out.println(m2.getManId());
        //Spring的默认的事务规则是遇到运行异常（RuntimeException）和程序错误（Error）才会回滚。如果想针对非检查异常进行事务回滚，可以在@Transactional 注解里使用 rollbackFor 属性明确指定异常。
        //@Transactional(rollbackFor = Exception.class)
        throw new RuntimeException();
        //确实通过事务回滚掉了奥
    }

    @ApiOperation(value="懒加载不测了")
    @RequestMapping("/test5")
    public void test5(){

    }

    @ApiOperation(value="测试全局异常处理")
    @RequestMapping("/test6")
    public void test6() throws TaskException {
       throw new TaskException("这是测试exception",TaskException.Code.NO_TASK_EXISTS);
       //exception在common/exception中，也可以自己建
    }

    @ApiOperation(value="测试redis")
    @GetMapping("/test7")
    public AjaxResult test7(){
               return AjaxResult.success(tttService.test7(4));
    }

    @ApiOperation(value="测试redis")
    @GetMapping("/test8")
    //@PathVariable String deleteCache
    public AjaxResult test8(){
        RedisCache redisCache=new RedisCache();
        redisCache.getCacheObject("shab2::4");
        return AjaxResult.success();

    }


}