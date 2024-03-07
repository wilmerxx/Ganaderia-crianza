package org.wilmer.colamuertamongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wilmer.colamuertamongo.service.serviceImpl.ServiceLogs;

@RestController
public class ControllerLogs {
    @Autowired
    private ServiceLogs serviceLogs;

   @GetMapping("/logs")
    public ResponseEntity<?> getLogs(){
        return ResponseEntity.ok(serviceLogs.getLogs());
    }
}
