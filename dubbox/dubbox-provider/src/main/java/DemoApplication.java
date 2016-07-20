import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;


@SpringBootApplication
@ComponentScan("com.example")
@MapperScan("com.example")
@ImportResource(value = "classpath:dubbo.xml")
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
	}

}
