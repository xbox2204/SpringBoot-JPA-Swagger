package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.Contact;

import java.util.concurrent.Executor;

@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		Contact contact =new Contact(
				"Vineet Mishra",
				"https://github.com/xbox2204",
				"whatwillyoudo@withmyemail.com"
		);
		ApiInfo apiInfo= new ApiInfoBuilder().title("VINEET SPRING-BOOT API")
				.description("Spring-Boot for all")
				.termsOfServiceUrl("jUST CHILL!!!")
				.contact(contact)
				.licenseUrl("something@something.com").version("1.0").build();

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.any())
				.build();
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

	}
	@Bean(name="thPlExectr")
	public Executor getExecutor(){
		return new ThreadPoolTaskExecutor();
	}


}
