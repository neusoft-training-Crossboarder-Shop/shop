package neu.train.project.test;

import neu.train.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {


    @RequestMapping("/testEx")
    public int testEx() throws Exception {
        throw new Exception();
    }

}
