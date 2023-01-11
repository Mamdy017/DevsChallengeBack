package DevsChallenge.example.DevsChallenge.Models;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@SpringBootApplication
public class ConfigSwagger {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("Mon Api de gestion des regions").version("1.0").license("(C) Copyright par Mamady camara et Sekou Diakite")
                .description("Cette api permet la gestion des regions dans son integralité " +
                        "pour le soucis d'ajout de region en fonction d'un pays et de la population, la liste des regions" +
                        ", supprimer une region " +
                        " modifier une region , un pays ou une population");
        return builder.build();
    }

}
