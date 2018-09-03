package jenkinsx.example.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.nio.channels.Pipe;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootWebApplication.class, args);
		ctx.getBean(PipelineWatcher.class).start();
	}

}
