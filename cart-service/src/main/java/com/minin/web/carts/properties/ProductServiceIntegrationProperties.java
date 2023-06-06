package com.minin.web.carts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@Data
@ConfigurationProperties(prefix = "integrations.product-service")
public class ProductServiceIntegrationProperties {

    private String url;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;
}
