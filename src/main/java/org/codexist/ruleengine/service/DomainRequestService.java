package org.codexist.ruleengine.service;

import org.codexist.ruleengine.repository.DailyDomainRequestRepository;
import org.codexist.ruleengine.ruleentity.DailyDomainRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DomainRequestService {
    private final DailyDomainRequestRepository dailyDomainRequestRepository;
    private final KieContainer kieContainer;
    private final EmailService emailService;

    public DomainRequestService(DailyDomainRequestRepository dailyDomainRequestRepository, KieContainer kieContainer, EmailService emailService) {
        this.dailyDomainRequestRepository = dailyDomainRequestRepository;
        this.kieContainer = kieContainer;
        this.emailService = emailService;
    }

    public void get() {
        List<DailyDomainRequest> domainRequests = dailyDomainRequestRepository.findAll();

        KieSession kieSession = kieContainer.newKieSession();

        for (DailyDomainRequest domainRequest : domainRequests) {
            kieSession.insert(domainRequest);
        }

        kieSession.setGlobal("emailService", emailService);

        kieSession.fireAllRules();

        kieSession.dispose();
    }
}

