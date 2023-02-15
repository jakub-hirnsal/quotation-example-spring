package com.example.quotation.model.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Quotation {
    private Long id;
    private LocalDate beginningOfInsurance;
    private Double insuredAmount;
    private LocalDate dateOfSigningMortgage;
    private Customer customer;
}
