package com.example.quotation.controller;

import com.example.quotation.model.dto.Quotation;
import com.example.quotation.model.dto.Subscription;
import com.example.quotation.service.QuotationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/quotation")
public class QuotationController {

    private final QuotationService quotationService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Quotation> postQuotation(
            @RequestBody Quotation quotation
    ) {
        return ResponseEntity.ok(
                quotationService.createQuotation(quotation)
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "{id}/subscription"
    )
    public ResponseEntity<Subscription> postSubscription(
            @RequestBody Subscription subscription,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                quotationService.createSubscription(id, subscription)
        );
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/subscription/{id}"
    )
    public ResponseEntity<Subscription> getSubscription(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                quotationService.retrieveSubscription(id)
        );
    }
}
