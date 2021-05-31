package com.simplywallstreet.swsbe.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity()
@Table(name = "SWSCOMPANY")
@NoArgsConstructor
@Data
public class SwsCompany {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "ticker_symbol")
    private String tickerSymbol;

    @Column(name = "exchange_symbol")
    private String exchangeSymbol;

    @Column(name = "unique_symbol")
    private String uniqueSymbol;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_generated")
    private Date dateGenerated;

    @Column(name = "security_name")
    private String securityName;

    @Column(name = "exchange_country_iso")
    private String exchangeCountryIso;

    @Column(name = "listing_currency_iso")
    private String listingCurrencyIso;

    @Column(name = "canonical_url")
    private String canonicalUrl;

    @Column(name = "unique_symbol_slug")
    private String uniqueSymbolSlug;

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="score_id", referencedColumnName = "id")
    private SwsCompanyScore companyScore;

    @OneToMany(mappedBy = "swsCompany", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SwsCompanyPriceClose> swsCompanyPriceCloses = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwsCompany that = (SwsCompany) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(tickerSymbol, that.tickerSymbol) && Objects.equals(exchangeSymbol, that.exchangeSymbol) && Objects.equals(uniqueSymbol, that.uniqueSymbol) && Objects.equals(dateGenerated, that.dateGenerated) && Objects.equals(securityName, that.securityName) && Objects.equals(exchangeCountryIso, that.exchangeCountryIso) && Objects.equals(listingCurrencyIso, that.listingCurrencyIso) && Objects.equals(canonicalUrl, that.canonicalUrl) && Objects.equals(uniqueSymbolSlug, that.uniqueSymbolSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tickerSymbol, exchangeSymbol, uniqueSymbol, dateGenerated, securityName, exchangeCountryIso, listingCurrencyIso, canonicalUrl, uniqueSymbolSlug);
    }
}

