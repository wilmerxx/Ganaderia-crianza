package org.wilmer.colamuertamongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wilmer.colamuertamongo.model.Logs;

public interface RepositoryLogs extends MongoRepository<Logs, String> {
}
