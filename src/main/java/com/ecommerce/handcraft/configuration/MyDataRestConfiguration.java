package com.ecommerce.handcraft.configuration;

import com.ecommerce.handcraft.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfiguration implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {
        HttpMethod[] theUnsupportedAActions = {HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT};
        config.exposeIdsFor(Product.class);
        config.exposeIdsFor(Image.class);
        config.exposeIdsFor(Homepage.class);
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Color.class);
        config.exposeIdsFor(Message.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Roles.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Checkout.class);



        disableHttpMethods(Product.class, config, theUnsupportedAActions);
        disableHttpMethods(Color.class, config, theUnsupportedAActions);
        disableHttpMethods(Image.class, config, theUnsupportedAActions);
        disableHttpMethods(Category.class, config, theUnsupportedAActions);
        disableHttpMethods(Message.class, config, theUnsupportedAActions);
        disableHttpMethods(User.class, config, theUnsupportedAActions);
        disableHttpMethods(Roles.class, config, theUnsupportedAActions);
        disableHttpMethods(Review.class, config, theUnsupportedAActions);
        disableHttpMethods(Homepage.class, config, theUnsupportedAActions);
        disableHttpMethods(Checkout.class, config, theUnsupportedAActions);


        /* Configure CORS Mapping */
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration().forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}

