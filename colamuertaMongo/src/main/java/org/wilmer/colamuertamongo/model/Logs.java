package org.wilmer.colamuertamongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs")
public class Logs {
    @Id
    private String id;
    private String message;
    private Map<String, Object> headers;
    private String contentType;
    private String receivedExchange;
    private String receivedRoutingKey;
    private String consumerQueue;
    @CreatedDate
    private LocalDateTime fecha_creacion;
}