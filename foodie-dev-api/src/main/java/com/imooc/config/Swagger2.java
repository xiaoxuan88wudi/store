package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author GodXuan
 * @date 2019/11/29 0029 - 下午 3:06
 */
@Configuration
@EnableSwagger2
public class Swagger2 {




    //核心配置
    @Bean
    public Docket creatRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).//指定api类型
                apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                .paths(PathSelectors.any()).build();//api文档汇总信息

    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("天天吃货电商平台接口api")
                                    .contact(new Contact("xuan","https:www.imooc.com","929305049@qq.com"))
                                    .description("api文档").version("1.0.1").termsOfServiceUrl("https:www.imooc.com").build();
    }
}
