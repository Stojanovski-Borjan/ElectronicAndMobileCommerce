package mk.ukim.finki.rentscoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RentScootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentScootApplication.class, args);
    }

}
