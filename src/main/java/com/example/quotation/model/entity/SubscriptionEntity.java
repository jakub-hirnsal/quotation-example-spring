package com.example.quotation.model.entity;

import com.example.quotation.model.dto.Subscription;
import java.time.LocalDateTime;
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
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    private QuotationEntity quotation;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime validUntil;

    public static SubscriptionEntity fromSubscription(Subscription subscription) {
        return SubscriptionEntity.builder()
                .startDate(subscription.getStartDate())
                .validUntil(subscription.getValidUntil())
                .build();
    }

    public Subscription toSubscription() {
        return Subscription.builder()
                .id(id)
                .quotation(quotation.toQuotation())
                .startDate(startDate)
                .validUntil(validUntil)
                .build();
    }
}
