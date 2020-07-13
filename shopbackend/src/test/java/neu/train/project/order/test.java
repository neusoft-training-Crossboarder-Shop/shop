package neu.train.project.order;

import neu.train.project.order.service.OrderService;
import neu.train.project.order.vo.GetAStoOrderQueryByTime;
import neu.train.project.order.vo.SendASimpleSto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class test {

    @Autowired
    OrderService orderService;

    @Test
    public void test1(){
        GetAStoOrderQueryByTime getAStoOrderQueryByTime=new GetAStoOrderQueryByTime(1,1,null,null);
        List<SendASimpleSto> sendASimpleStos= orderService.selectStoStrProByTime(getAStoOrderQueryByTime);
        System.out.println(sendASimpleStos.size());
        System.out.println(sendASimpleStos.isEmpty());
        System.out.println(sendASimpleStos.get(0));
        System.out.println(sendASimpleStos.get(0).toString());
    }
}
