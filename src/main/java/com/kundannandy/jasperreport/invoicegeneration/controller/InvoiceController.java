package com.kundannandy.jasperreport.invoicegeneration.controller;

import com.kundannandy.jasperreport.invoicegeneration.dto.InvoiceRequest;
import com.kundannandy.jasperreport.invoicegeneration.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateInvoice(@RequestBody InvoiceRequest request) {
        try {
            byte[] pdf = invoiceService.generateInvoice(
                    request.getCustomerName(),
                    request.getCustomerPhone(),
                    request.getItems());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Invoice.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/records")
    public ResponseEntity<?> getAllInvoices() {
        try {
            return ResponseEntity.ok(invoiceService.getAllInvoices());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch invoices");
        }
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(invoiceService.getInvoiceById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found with ID: " + id);
        }
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadInvoicePdf(@PathVariable Long id) {
        try {
            byte[] pdfData = invoiceService.getInvoicePdfById(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=Invoice_" + id + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
