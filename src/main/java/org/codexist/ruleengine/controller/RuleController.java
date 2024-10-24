package org.codexist.ruleengine.controller;

import org.codexist.ruleengine.ruleentity.DailyDomainRequest;
import org.codexist.ruleengine.service.DomainRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleController {

    private final DomainRequestService service;

    public RuleController(DomainRequestService service) {
        this.service = service;
    }

    @GetMapping("/firstRule")
    public void getLast3HoursStatistics() {
        service.get();
    }
}
