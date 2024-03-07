package org.wilmer.colamuertamongo.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wilmer.colamuertamongo.model.Logs;
import org.wilmer.colamuertamongo.repository.RepositoryLogs;
import org.wilmer.colamuertamongo.service.ServiceLogsInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ServiceLogs implements ServiceLogsInterface {

    @Autowired
    private RepositoryLogs repositoryLogs;

    @RabbitListener(queues = "deadLetter.queue")
    public void recibirMensaje(Message message) {
        log.info("Mensaje recibido: " + Arrays.toString(message.getBody()));
        Logs logs = new Logs();
        logs.setMessage(new String(message.getBody()));
        logs.setHeaders(message.getMessageProperties().getHeaders());
        logs.setContentType(message.getMessageProperties().getContentType());
        logs.setReceivedExchange(message.getMessageProperties().getReceivedExchange());
        logs.setReceivedRoutingKey(message.getMessageProperties().getReceivedRoutingKey());
        logs.setConsumerQueue(message.getMessageProperties().getConsumerQueue());
        repositoryLogs.save(logs);
    }


    @Override
    public void deleteLog(String id) {
        repositoryLogs.deleteById(id);
    }

    @Override
    public List<Logs> getLogs() {
        return  repositoryLogs.findAll();
    }
}
