package com.example.quotation.controller;

import com.example.quotation.model.dto.Customer;
import com.example.quotation.service.QuotationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final QuotationService quotationService;

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{id}"
    )
    public ResponseEntity<Customer> putCustomer(
            @RequestBody Customer customer,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
            quotationService.updateCustomer(id, customer)
        );
    }
}
