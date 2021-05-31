package com.simplywallstreet.swsbe.validator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class QueryParameters{
    private int companyScore;
    private String exchangeSymbol;
}
