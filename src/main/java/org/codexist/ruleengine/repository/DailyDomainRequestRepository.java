package org.codexist.ruleengine.repository;

import org.codexist.ruleengine.ruleentity.DailyDomainRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyDomainRequestRepository extends JpaRepository<DailyDomainRequest, Long> {
    List<DailyDomainRequest> findByRequestDate(String requestDate);
}

