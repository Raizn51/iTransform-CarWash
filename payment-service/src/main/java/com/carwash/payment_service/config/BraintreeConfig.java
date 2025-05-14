package com.carwash.payment_service.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BraintreeConfig {

    @Value("${braintree.merchant-id}")
    private String merchantId;

    @Value("${braintree.public-key}")
    private String publicKey;

    @Value("${braintree.private-key}")
    private String privateKey;

    @Value("${braintree.environment}")
    private String environment;

    @Bean
    public BraintreeGateway braintreeGateway() {
        Environment env = environment.equals("production")
                ? Environment.PRODUCTION
                : Environment.SANDBOX;
        return new BraintreeGateway(env, merchantId, publicKey, privateKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}