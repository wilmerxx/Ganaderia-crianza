package org.wilmer.colamuertamongo.service;

import org.wilmer.colamuertamongo.model.Logs;

import java.util.List;

public interface ServiceLogsInterface {
    public void deleteLog(String id);

    public List<Logs> getLogs();

}
