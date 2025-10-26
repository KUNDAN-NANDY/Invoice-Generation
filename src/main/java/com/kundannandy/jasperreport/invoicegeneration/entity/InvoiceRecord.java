package com.kundannandy.jasperreport.invoicegeneration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoice_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;

    @Lob
    private byte[] pdfData;  // To store PDF file bytes

    private LocalDateTime createdAt;
}
