package com.simplywallstreet.swsbe.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
public class SwsCompanyPriceClosePK implements Serializable {

    @Column(name = "date")
    private String date;

    @Column(name = "company_id")
    private String companyId;

    public SwsCompanyPriceClosePK(String date, String companyId) {
        this.date = date;
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwsCompanyPriceClosePK that = (SwsCompanyPriceClosePK) o;
        return Objects.equals(date, that.date) && Objects.equals(companyId, that.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, companyId);
    }
}
