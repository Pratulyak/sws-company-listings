package com.simplywallstreet.swsbe.jpa;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name ="SWSCOMPANYSCORE")
@NoArgsConstructor
@Data
public class SwsCompanyScore {
    @Id
    private Long id;

    @Column(name = "date_generated")
    private Date dateGenerated;

    @Column(name = "dividend")
    private Integer dividend;

    @Column(name = "future")
    private Integer future;

    @Column(name = "health")
    private Integer health;

    @Column(name = "management")
    private Integer management;

    @Column(name = "past")
    private Integer past;

    @Column(name = "value")
    private Integer value;

    @Column(name = "misc")
    private Integer misc;

    @Column(name = "total")
    private Integer total;

    @Column(name = "sentence")
    private String sentence;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwsCompanyScore that = (SwsCompanyScore) o;
        return Objects.equals(id, that.id) && Objects.equals(dateGenerated, that.dateGenerated) && Objects.equals(dividend, that.dividend) && Objects.equals(future, that.future) && Objects.equals(health, that.health) && Objects.equals(management, that.management) && Objects.equals(past, that.past) && Objects.equals(value, that.value) && Objects.equals(misc, that.misc) && Objects.equals(total, that.total) && Objects.equals(sentence, that.sentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateGenerated, dividend, future, health, management, past, value, misc, total, sentence);
    }
}
