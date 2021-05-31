package com.simplywallstreet.swsbe.repository;

import com.simplywallstreet.swsbe.jpa.SwsCompany;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SwsCompanyRepository extends PagingAndSortingRepository<SwsCompany, String> {

    @NotNull
    @Transactional
    List<SwsCompany> findAll();
}
