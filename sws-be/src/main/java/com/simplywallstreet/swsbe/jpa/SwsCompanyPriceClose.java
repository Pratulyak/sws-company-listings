package com.simplywallstreet.swsbe.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name="SWSCOMPANYPRICECLOSE")
@Data
public class SwsCompanyPriceClose {

    @EmbeddedId
    @JsonUnwrapped
    private SwsCompanyPriceClosePK priceClosePK;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("companyId")
    @JsonBackReference
    private SwsCompany swsCompany;

    @Column(name = "price")
    private Float price;
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwsCompanyPriceClose that = (SwsCompanyPriceClose) o;
        return  Objects.equals(price, that.price) && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, dateCreated);
    }
}
