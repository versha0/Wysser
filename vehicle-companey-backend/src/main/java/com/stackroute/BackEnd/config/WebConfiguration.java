package com.stackroute.BackEnd.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
@EnableAutoConfiguration
//@Profile("prod")
public class WebConfiguration {

    //  @Bean
    //ServletRegistrationBean h2ServletRegistration(){
    //  ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebdavServlet());
    //registrationBean.addUrlMappings("/console/*");
    // return registrationBean;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackrote.Muzix.Controller"))
                .paths(regex("/api.*"))
                .build();
    }


}
