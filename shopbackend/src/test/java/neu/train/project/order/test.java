package neu.train.project.order;

import neu.train.project.order.pojo.StoStoreOrder;
import neu.train.project.order.service.OrderService;
import neu.train.project.order.vo.*;
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

    @Test
    public void test2(){
        GetASaoQueryByTime getASaoQueryByTime=new GetASaoQueryByTime(1,1,null,null);
        List<SendComplexSao> sendComplexSaos=orderService.selectSaoStrProByTime(getASaoQueryByTime);
        System.out.println(sendComplexSaos.size());
        System.out.println(sendComplexSaos.isEmpty());
        System.out.println(sendComplexSaos.get(0));
        System.out.println(sendComplexSaos.get(0).toString());

    }

    @Test
    public void test3(){
        Integer[] x={1};
        GetPurchaseMessage getPurchaseMessage=new GetPurchaseMessage(1,1,x);
        orderService.insertStos(1,getPurchaseMessage);
    }

    @Test
    public void test4(){
        GetStiQuery getStiQuery=new GetStiQuery(1,1,null,null);
        List<SendSti> sendStis=orderService.selectSTIs(getStiQuery);
        System.out.println(sendStis.size());
        System.out.println(sendStis.isEmpty());
        System.out.println(sendStis.get(0));
        System.out.println(sendStis.get(0).toString());
    }
    @Test
    public void test5(){
        StoStoreOrder stoStoreOrder=orderService.selectStoById(12);
        System.out.println(stoStoreOrder);
    }
}
