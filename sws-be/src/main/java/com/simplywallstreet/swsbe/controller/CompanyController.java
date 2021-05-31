package com.simplywallstreet.swsbe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplywallstreet.swsbe.jpa.SwsCompany;
import com.simplywallstreet.swsbe.service.CompanyService;
import com.simplywallstreet.swsbe.validator.QueryParameters;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
@CrossOrigin // NOTE: This is a bad idea but for the purpose of running locally this is required
public class CompanyController {

    public static final String BASE_URL = "/company";
    public static final String ENDPOINT_GET_COMPANY = BASE_URL + "/{id}";

    private CompanyService companyService;
    private ObjectMapper objectMapper;

    @GetMapping(value = BASE_URL)
    public Page<SwsCompany> getAllCompaniesFiltered(@RequestParam Map<String, String> queryParametersMap, Pageable pageable) {
        QueryParameters queryParameters = objectMapper.convertValue(queryParametersMap, QueryParameters.class);
        return this.companyService.getSwsCompanyOnFilter(queryParameters, pageable);
    }

    @GetMapping(value = ENDPOINT_GET_COMPANY)
    public SwsCompany getOneCompany(@PathVariable String id) {
        return this.companyService.getOneCompany(id);
    }


}
