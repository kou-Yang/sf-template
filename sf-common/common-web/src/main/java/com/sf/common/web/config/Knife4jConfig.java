package com.sf.common.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Knife4j 接口文档配置
 * 访问：http://localhost:8080/api/doc.html
 *
 * @author kouyang
 */
@Configuration
@EnableSwagger2
public class Knife4jConfig {

    private final String SPLIT = ";";

    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                .title("sf-template")
                .description("sf-template")
                .contact(new Contact("KouYang", "", ""))
                .version("1.0")
                .build())
                .select()
                // 指定 Controller 扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.sf.controller"))
                .apis(basePackage("com.sf"))
                .paths(PathSelectors.any())
                .build();
    }

    public Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)).orElse(true);
    }

    private Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable(input.declaringClass());
    }

    private Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLIT)) {
                assert input != null;
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }
}