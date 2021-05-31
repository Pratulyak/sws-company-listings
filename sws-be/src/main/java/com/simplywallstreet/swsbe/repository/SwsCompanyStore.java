package com.simplywallstreet.swsbe.repository;

import com.simplywallstreet.swsbe.jpa.SwsCompany;
import com.simplywallstreet.swsbe.jpa.SwsCompanyScore;
import com.simplywallstreet.swsbe.validator.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SwsCompanyStore {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SwsCompanyRepository repository;

    public Page<SwsCompany> getSwsCompanyOnFilter(QueryParameters queryParameters, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SwsCompany> query = cb.createQuery(SwsCompany.class);
        Root<SwsCompany> companies = query.from(SwsCompany.class);

        List<Predicate> predicates = new ArrayList<>();

        if ( queryParameters.getCompanyScore() >0 ) {
            Path<SwsCompanyScore> path = companies.get("companyScore");
            predicates.add(cb.equal(path.get("total"), queryParameters.getCompanyScore()));
        }
        if ( queryParameters.getExchangeSymbol() != null ) {
            Path<String> path = companies.get("exchangeSymbol");
            predicates.add(cb.like(cb.lower(path), queryParameters.getExchangeSymbol()));
        }

        return new PageImpl<>(entityManager.createQuery(query.select(companies).where(cb.and(predicates.toArray(new Predicate[predicates.size()]))))
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList());
    }

    public Optional<SwsCompany> findById(String id) {
       return this.repository.findById(id);
    }
}
