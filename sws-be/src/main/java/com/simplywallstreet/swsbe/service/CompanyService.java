package com.simplywallstreet.swsbe.service;

import com.simplywallstreet.swsbe.jpa.SwsCompany;
import com.simplywallstreet.swsbe.repository.SwsCompanyStore;
import com.simplywallstreet.swsbe.validator.QueryParameters;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class CompanyService {

    private SwsCompanyStore swsCompanyStore;

    public Page<SwsCompany> getSwsCompanyOnFilter(QueryParameters queryParameters, Pageable pageable) {
        log.info("{} - Requesting companies for a filter", "getSwsCompanyOnFilter");
        return this.swsCompanyStore.getSwsCompanyOnFilter(queryParameters, pageable);
    }

    public SwsCompany getOneCompany(String id) {
        log.info("{} - Requesting companies for a specific id", "getSwsCompanyOnFilter");
        return this.swsCompanyStore.findById(id).orElse(null);
    }

}
