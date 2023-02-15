package com.example.quotation.model.dto;

import com.example.quotation.model.entity.QuotationEntity;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subscription {
    private Long id;
    private Quotation quotation;
    private LocalDateTime startDate;
    private LocalDateTime validUntil;
}
