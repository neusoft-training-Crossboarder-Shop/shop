package neu.train.project.mvo.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.mvo.mapper.ManManufacturerMapper;
import neu.train.project.mvo.pojo.ManManufacturerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/swagger-resources/")
public class TTTController {
    @Autowired
    private ManManufacturerMapper manManufacturerMapper;

    //普通消息，mybatis-generator，懒加载，分页查询，redis，exception全局异常处理，validate@notNull，aspectJ权限？详见自动生成的sysControllers
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
}