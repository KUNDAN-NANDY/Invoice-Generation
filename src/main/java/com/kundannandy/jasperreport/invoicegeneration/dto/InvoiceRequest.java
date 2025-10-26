package com.kundannandy.jasperreport.invoicegeneration.dto;

import com.kundannandy.jasperreport.invoicegeneration.model.Invoice;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
    private String customerName;
    private String customerPhone;
    private List<Invoice> items;
}
