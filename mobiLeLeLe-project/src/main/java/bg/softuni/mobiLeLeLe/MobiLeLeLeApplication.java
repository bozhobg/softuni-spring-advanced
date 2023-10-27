package bg.softuni.mobiLeLeLe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MobiLeLeLeApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(MobiLeLeLeApplication.class, args);
        //		closes app:
        //		((AbstractApplicationContext) context).close();
    }

//  can't use in this way because Test uses DI and requires ctor parameter
//	@Bean(initMethod = "init", destroyMethod = "destroy")
//	public Test getTest() {
//		return new Test();
//	}

}
