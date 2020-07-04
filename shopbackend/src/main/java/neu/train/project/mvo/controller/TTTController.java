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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    @Autowired
    private RedisCache redisCache;

    @Autowired RedisTemplate redisTemplate;

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
       ManManufacturer answer=redisCache.getCacheObject("shabbb::4");
        System.out.println(answer.getDescription());
        return AjaxResult.success();
}

    @ApiOperation(value="测试redis")
    @GetMapping("/test9")
    //@PathVariable String deleteCache
    public AjaxResult test9(){
        redisCache.setCacheObject("shabbb::wobushishabi","youshigeshabi");
        return AjaxResult.success();
    }
}



/*
* 1、@CacheConfig
主要用于配置该类中会用到的一些共用的缓存配置。示例：

@CacheConfig(cacheNames = "users")
public interface UserService {。。。}
配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中，我们也可以不使用该注解，直接通过@Cacheable自己配置缓存集的名字来定义。

2、@Cacheable
应用到读取数据的方法上，即可缓存的方法，如查找方法，先从缓存中读取，如果没有再调用相应方法获取数据，然后把数据添加到缓存中。

该注解主要有下面几个参数：

value、cacheNames：两个等同的参数（cacheNames为Spring 4新增，作为value的别名），用于指定缓存存储的集合名。由于Spring 4中新增了@CacheConfig，因此在Spring 3中原本必须有的value属性，也成为非必需项了
key：缓存对象存储在Map集合中的key值，非必需，缺省按照函数的所有参数组合作为key值，若自己配置需使用SpEL表达式，比如：@Cacheable(key = "#p0")：使用函数第一个参数作为缓存的key值，更多关于SpEL表达式的详细内容可参考官方文档
condition：缓存对象的条件，非必需，也需使用SpEL表达式，只有满足表达式条件的内容才会被缓存，比如：@Cacheable(key = "#p0", condition = "#p0.length() < 3")，表示只有当第一个参数的长度小于3的时候才会被缓存。
unless：另外一个缓存条件参数，非必需，需使用SpEL表达式。它不同于condition参数的地方在于它的判断时机，该条件是在函数被调用之后才做判断的，所以它可以通过对result进行判断。
keyGenerator：用于指定key生成器，非必需。若需要指定一个自定义的key生成器，我们需要去实现org.springframework.cache.interceptor.KeyGenerator接口，并使用该参数来指定。需要注意的是：该参数与key是互斥的
cacheManager：用于指定使用哪个缓存管理器，非必需。只有当有多个时才需要使用
cacheResolver：用于指定使用那个缓存解析器，非必需。需通过org.springframework.cache.interceptor.CacheResolver接口来实现自己的缓存解析器，并用该参数指定。
示例如下：

@Cacheable(value = "user", key = "#id")
User selectUserById(final Integer id);
 3、@CachePut
应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存，示例如下：

@CachePut(value = "user", key = "#user.id")
public User save(User user) {
    users.add(user);
    return user;
}
此时会以user.id做为缓存key,返回结果user做为值，测试过程中发现在修改对象后只将缓存结果移除但key未移除，导致查询依然使用了缓存而结果为空（不知道啥情况）

@CachePut的参数与@Cacheable类似

4、@CacheEvict
应用到移除数据的方法上，如删除方法，调用方法时会从缓存中移除相应的数据，示例如下：

@CacheEvict(value = "user", key = "#id")
void delete(final Integer id);
除了同@Cacheable一样的参数之外，@CacheEvict还有下面两个参数：

allEntries：非必需，默认为false。当为true时，会移除所有数据
beforeInvocation：非必需，默认为false，会在调用方法之后移除数据。当为true时，会在调用方法之前移除数据。
5、@Caching
组合多个Cache注解使用。示例：

复制代码
@Caching(
    put = {
        @CachePut(value = "user", key = "#user.id"),
        @CachePut(value = "user", key = "#user.username"),
        @CachePut(value = "user", key = "#user.age")
   }
}
复制代码
以上是将id-->user；username--->user；age--->user进行缓存。

6、缓存策略
如果缓存满了，从缓存中移除数据的策略，常见的有FIFO， LRU 、LFU

FIFO (First in First Out) 先进先出策略，即先放入缓存的数据先被移除
LRU (Least Recently Used) 最久未使用策略， 即使用时间距离现在最久的那个数据被移除
LFU (Least Frequently Used)  最少使用策略，即一定时间内使用次数（频率）最少的那个数据被移除
TTL（Time To Live）存活期，即从缓存中创建时间点开始至到期的一个时间段（不管在这个时间段内有没被访问过都将过期）
TTI （Time To Idle）空闲期，即一个数据多久没有被访问就从缓存中移除的时间。
7、附注
通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），Spring Boot根据下面的顺序去侦测缓存提供者：

 Generic
JCache (JSR-107)
 EhCache 2.x
 Hazelcast
 Infinispan
 Redis
 Guava
 Simple
可以通过配置属性spring.cache.type来强制指定，即

spring.cache.type = xxx
另外可通过注入cacheManager来调试查看使用哪种类型,进一步熟悉cache

@Autowired
private CacheManager cacheManager;*/