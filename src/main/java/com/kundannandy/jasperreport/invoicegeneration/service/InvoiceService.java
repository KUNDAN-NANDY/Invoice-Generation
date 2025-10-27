package com.kundannandy.jasperreport.invoicegeneration.service;

import com.kundannandy.jasperreport.invoicegeneration.entity.InvoiceRecord;
import com.kundannandy.jasperreport.invoicegeneration.model.Invoice;
import com.kundannandy.jasperreport.invoicegeneration.repository.InvoiceRecordRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRecordRepository invoiceRecordRepository;

    public byte[] generateInvoice(String customerName, String customerPhone, List<Invoice> items) throws Exception {

        // Compile the report
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/templates/invoiceTemplate.jrxml");

        // Create the DataSource for the table
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

        // Calculate grand total
        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        // Pass parameters to the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CustomerName", customerName);
        parameters.put("CustomerPhone", customerPhone);
        parameters.put("Total", total);
        parameters.put("invoiceDetailsDataset", dataSource);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        // Return PDF bytes
        byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);

        // Save to DB
        InvoiceRecord record = new InvoiceRecord();
        record.setCustomerName(customerName);
        record.setCustomerPhone(customerPhone);
        record.setPdfData(pdfData);
        record.setCreatedAt(LocalDateTime.now());

        invoiceRecordRepository.save(record);

        return pdfData;
    }

    public List<InvoiceRecord> getAllInvoices() {
        return invoiceRecordRepository.findAll();
    }

    public InvoiceRecord getInvoiceById(Long id) {
        return invoiceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
    }

    public byte[] getInvoicePdfById(Long id) {
        InvoiceRecord record = invoiceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));

        return record.getPdfData();
    }


}
