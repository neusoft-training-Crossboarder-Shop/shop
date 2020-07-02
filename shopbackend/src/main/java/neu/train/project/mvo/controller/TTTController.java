package neu.train.project.mvo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import neu.train.framework.web.domain.AjaxResult;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/swagger-resources/")
public class TTTController {
   //普通消息，mybatis，分页查询，redis，exception，validate，aspectJ
    @ApiOperation(value="post请求返回一个成功普通消息")
    @PostMapping("/test1")
    public AjaxResult test(){
        return AjaxResult.success("你好骚啊");
    }

    @ApiOperation(value="")
    @PostMapping("/test2")
    public String test2(){
     return "bv";
    }
}
