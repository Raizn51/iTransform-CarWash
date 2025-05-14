package com.carwash.order_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchanges
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String PAYMENT_EXCHANGE = "payment.exchange";
    public static final String USER_EXCHANGE = "user.exchange";

    // Queues
    public static final String NEW_ORDERS_QUEUE = "new.orders.queue";
    public static final String ORDER_UPDATES_QUEUE = "order.updates.queue";
    public static final String PLAN_UPDATES_QUEUE = "plan.updates.queue";
    public static final String CUSTOMER_NOTIFICATIONS_QUEUE = "customer.notifications.queue";
    public static final String WASHER_NOTIFICATIONS_QUEUE = "washer.notifications.queue";
    public static final String PAYMENT_PROCESSED_QUEUE = "payment.processed.queue";
    public static final String USER_UPDATES_QUEUE = "user.updates.queue";

    // === Exchanges ===

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public FanoutExchange notificationExchange() {
        return new FanoutExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(PAYMENT_EXCHANGE);
    }

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    // === Queues ===

    @Bean
    public Queue newOrdersQueue() {
        return new Queue(NEW_ORDERS_QUEUE);
    }

    @Bean
    public Queue orderUpdatesQueue() {
        return new Queue(ORDER_UPDATES_QUEUE);
    }

    @Bean
    public Queue planUpdatesQueue() {
        return new Queue(PLAN_UPDATES_QUEUE);
    }

    @Bean
    public Queue customerNotificationsQueue() {
        return new Queue(CUSTOMER_NOTIFICATIONS_QUEUE);
    }

    @Bean
    public Queue washerNotificationsQueue() {
        return new Queue(WASHER_NOTIFICATIONS_QUEUE);
    }

    @Bean
    public Queue paymentProcessedQueue() {
        return new Queue(PAYMENT_PROCESSED_QUEUE);
    }

    @Bean
    public Queue userUpdatesQueue() {
        return new Queue(USER_UPDATES_QUEUE);
    }

    // === Bindings ===

    @Bean
    public Binding newOrdersBinding() {
        return BindingBuilder.bind(newOrdersQueue())
                .to(orderExchange())
                .with("order.created");
    }

    @Bean
    public Binding orderUpdatesBinding() {
        return BindingBuilder.bind(orderUpdatesQueue())
                .to(orderExchange())
                .with("order.status.update");
    }

    @Bean
    public Binding planUpdatesBinding() {
        return BindingBuilder.bind(planUpdatesQueue())
                .to(orderExchange())
                .with("plan.created");
    }

    @Bean
    public Binding paymentProcessedBinding() {
        return BindingBuilder.bind(paymentProcessedQueue())
                .to(paymentExchange())
                .with("payment.processed");
    }

    @Bean
    public Binding customerNotificationsBinding() {
        return BindingBuilder.bind(customerNotificationsQueue())
                .to(notificationExchange());
    }

    @Bean
    public Binding washerNotificationsBinding() {
        return BindingBuilder.bind(washerNotificationsQueue())
                .to(notificationExchange());
    }

    @Bean
    public Binding userUpdatesBinding() {
        return BindingBuilder.bind(userUpdatesQueue())
                .to(userExchange())
                .with("user.updated");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
