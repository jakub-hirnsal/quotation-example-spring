package com.example.quotation.model.entity;

import com.example.quotation.model.dto.Quotation;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QuotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE")
    private LocalDate beginningOfInsurance;
    private Double insuredAmount;
    @Column(columnDefinition = "DATE")
    private LocalDate dateOfSigningMortgage;
    @OneToOne(cascade = CascadeType.MERGE)
    private CustomerEntity customer;

    public static QuotationEntity fromQuotation(Quotation quotation) {
        return QuotationEntity.builder()
                .beginningOfInsurance(quotation.getBeginningOfInsurance())
                .insuredAmount(quotation.getInsuredAmount())
                .dateOfSigningMortgage(quotation.getDateOfSigningMortgage())
                .build();
    }

    public Quotation toQuotation() {
        return Quotation.builder()
                .id(id)
                .beginningOfInsurance(beginningOfInsurance)
                .insuredAmount(insuredAmount)
                .dateOfSigningMortgage(dateOfSigningMortgage)
                .customer(customer.toCustomer())
                .build();
    }
}
