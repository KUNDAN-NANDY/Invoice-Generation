package com.kundannandy.jasperreport.invoicegeneration.repository;

import com.kundannandy.jasperreport.invoicegeneration.entity.InvoiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRecordRepository extends JpaRepository<InvoiceRecord, Long> {
}