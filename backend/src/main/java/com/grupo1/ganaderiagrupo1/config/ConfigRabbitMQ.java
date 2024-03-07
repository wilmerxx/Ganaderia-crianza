package com.grupo1.ganaderiagrupo1.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbitMQ {
    public static final String QUEUE_ALIMENTACION_GANADO= "queue.ALIMENTACION_GANADO";
    public static final String QUEUE_ALIMENTACION_INVENTARIO= "queue.ALIMENTACION_INVENTARIO";

    public static final String QUEUE_DEAD_LETTER = "queue.dead-letter";


    @Bean
    public DirectExchange deadLetterExchange() { return new DirectExchange("dead-letter-exchange");}

    @Bean
    public FanoutExchange fanoutExchangeGanado() {
        return new FanoutExchange("fanout-exchange-ganado");
    }

    @Bean
    public FanoutExchange fanoutExchangeInventario() {
        return new FanoutExchange("fanout-exchange-inventario");
    }

    // Define la cola principal con argumentos para la cola de cartas muertas
    @Bean
    Queue queueAlimentacionGanado(){
        return QueueBuilder.durable(QUEUE_ALIMENTACION_GANADO)
        .withArgument("x-dead-letter-exchange", " dead-letter-exchange")
        .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_LETTER)
        .build();
    }

    @Bean
    Queue queueAlimentacionInventario(){
        return QueueBuilder.durable(QUEUE_ALIMENTACION_INVENTARIO)
        .withArgument("x-dead-letter-exchange", " dead-letter-exchange")
        .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_LETTER)
        .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_LETTER).build();
    }

    @Bean
    public Binding bindingDeadLetter(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(QUEUE_DEAD_LETTER);
    }

    @Bean
    public Binding bindingAlimentacionGanadoDeead(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(QUEUE_ALIMENTACION_GANADO);
    }

    @Bean
    public Binding bindingAlimentacionInventarioDead(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(QUEUE_ALIMENTACION_INVENTARIO);
    }

    @Bean
    public Binding bindingAlimentacionInventarioDeadLetter(){
        return BindingBuilder.bind(queueAlimentacionInventario()).to(fanoutExchangeInventario());
    }

    @Bean
    public Binding bindingAlimentacionGanadoDeadLetter(){
        return BindingBuilder.bind(queueAlimentacionGanado()).to(fanoutExchangeGanado());
    }

    @Bean
    public Binding bindingFanoutGanado(){
        return BindingBuilder.bind(deadLetterQueue()).to(fanoutExchangeGanado());
    }

    @Bean
    public Binding bindingFanoutInventario(){
        return BindingBuilder.bind(deadLetterQueue()).to(fanoutExchangeInventario());
    }


    @Bean
    public MessageConverter messageConverter(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Línea para registrar el módulo
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
