package com.example.quotation.service;

import com.example.quotation.exception.ResourceNotFoundException;
import com.example.quotation.model.dto.Customer;
import com.example.quotation.model.dto.Quotation;
import com.example.quotation.model.dto.Subscription;
import com.example.quotation.model.entity.CustomerEntity;
import com.example.quotation.model.entity.QuotationEntity;
import com.example.quotation.model.entity.SubscriptionEntity;
import com.example.quotation.repository.CustomerRepository;
import com.example.quotation.repository.QuotationRepository;
import com.example.quotation.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Quotation createQuotation(Quotation quotation) {
        CustomerEntity customerEntity = createOrUpdateCustomer(quotation.getCustomer());
        QuotationEntity quotationEntity = QuotationEntity.fromQuotation(quotation);
        quotationEntity.setCustomer(customerEntity);

        quotationEntity = quotationRepository.save(quotationEntity);

        return quotationEntity.toQuotation();
    }

    public CustomerEntity createOrUpdateCustomer(Customer customer) {
        return customerRepository.save(
                CustomerEntity.fromCustomer(customer)
        );
    }


    public Customer updateCustomer(Long id, Customer customer) {
        var persistedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id " + id));

        persistedCustomer.setFirstName(customer.getFirstName());
        persistedCustomer.setLastName(customer.getLastName());
        persistedCustomer.setMiddleName(customer.getMiddleName());
        persistedCustomer.setEmail(customer.getEmail());
        persistedCustomer.setPhoneNumber(customer.getPhoneNumber());
        persistedCustomer.setBirthDate(customer.getBirthDate());

        return customerRepository.save(persistedCustomer).toCustomer();
    }

    public Subscription createSubscription(Long idOfQuotation, Subscription subscription) {
        var quotationEntity = findQuotationById(idOfQuotation);

        SubscriptionEntity subscriptionEntity = SubscriptionEntity.fromSubscription(subscription);
        subscriptionEntity.setQuotation(quotationEntity);

        return subscriptionRepository.save(subscriptionEntity).toSubscription();
    }

    public Subscription retrieveSubscription(Long id) {
        return findSubscriptionById(id).toSubscription();
    }

    private QuotationEntity findQuotationById(Long id) {
        return quotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quotation not exist with id " + id));
    }

    private SubscriptionEntity findSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not exist with id " + id));
    }
}
