package neu.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 * 
 * @author
 */
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CrossBoarderShopApplication
{

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CrossBoarderShopApplication.class, args);
        System.out.println("启动成功");
    }
}
