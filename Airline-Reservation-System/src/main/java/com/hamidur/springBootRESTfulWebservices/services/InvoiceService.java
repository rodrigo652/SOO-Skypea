package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Invoice;
import com.hamidur.springBootRESTfulWebservices.repos.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Future;
import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService (InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> findByCustomerId (Integer id){
        return invoiceRepository.findAllByCustomerId(id);
    }

    public  Invoice addInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }
}
