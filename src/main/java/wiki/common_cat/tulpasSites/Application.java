package wiki.common_cat.tulpasSites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    static Commander commander;
    public static void main(String[] args){
        commander=new Commander();
    }
}
