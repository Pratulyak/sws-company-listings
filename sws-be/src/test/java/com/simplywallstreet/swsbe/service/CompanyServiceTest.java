package com.simplywallstreet.swsbe.service;

import com.simplywallstreet.swsbe.jpa.SwsCompany;
import com.simplywallstreet.swsbe.jpa.SwsCompanyScore;
import com.simplywallstreet.swsbe.repository.SwsCompanyStore;
import com.simplywallstreet.swsbe.validator.QueryParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private SwsCompanyStore swsCompanyStore;

    private List<SwsCompany> companies;
    private QueryParameters queryParameters;
    private Pageable request;

    @BeforeEach
    public void before() {
        SwsCompany swsCompanyA = new SwsCompany();
        swsCompanyA.setName("companyA");
        SwsCompany swsCompanyB = new SwsCompany();
        swsCompanyB.setName("companyB");
        SwsCompany swsCompanyC = new SwsCompany();
        swsCompanyC.setName("companyC");

        companies = new ArrayList<>();
        companies.add(swsCompanyA);
        companies.add(swsCompanyB);
        companies.add(swsCompanyC);

        request = PageRequest.of(1, 10);

        queryParameters = new QueryParameters();
    }


    @Test
    public void testGetAllCompanies() {
        given(swsCompanyStore.getSwsCompanyOnFilter(eq(queryParameters), any(Pageable.class))).willReturn(new PageImpl<>(companies));
        Page<SwsCompany> response = this.companyService.getSwsCompanyOnFilter(queryParameters, request);
        assertNotNull(response);
        assertEquals(response.getTotalElements(), 3);
        assertEquals(response.getContent().get(0).getName(), companies.get(0).getName());
    }

    @Test
    public void testGetCompaniesOnFilter() {
        queryParameters.setCompanyScore(12);
        queryParameters.setExchangeSymbol("ASX");

        List<SwsCompany> filteredCompanies = new ArrayList<>();

        SwsCompanyScore score = new SwsCompanyScore();
        score.setTotal(12);

        SwsCompany company = new SwsCompany();
        company.setName("companyD");
        company.setExchangeSymbol("ASX");
        company.setCompanyScore(score);

        filteredCompanies.add(company);
        given(swsCompanyStore.getSwsCompanyOnFilter(eq(queryParameters), any(Pageable.class))).willReturn(new PageImpl<>(filteredCompanies));
        Page<SwsCompany> response = this.companyService.getSwsCompanyOnFilter(queryParameters, request);
        assertNotNull(response);
        assertEquals(response.getTotalElements(), 1);
        assertEquals(response.getContent().get(0), filteredCompanies.get(0));

    }

    @Test
    public void testGetOneCompany() {
        String id = UUID.randomUUID().toString();

        SwsCompany company = new SwsCompany();
        company.setName("companyE");
        company.setExchangeSymbol("ASX");
        company.setId(id);

        given(swsCompanyStore.findById(eq(id))).willReturn(Optional.of(company));

        SwsCompany response = this.companyService.getOneCompany(id);

        assertNotNull(response);
        assertEquals(response, company);
    }

    @Test
    public void testGetCompanyWithNoResponse() {
        String id = UUID.randomUUID().toString();
        given(swsCompanyStore.findById(eq(id))).willReturn(Optional.empty());
        assertNull(this.companyService.getOneCompany(id));
    }
}
