package backend.sumnail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SumnailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SumnailApplication.class, args);
    }

}
