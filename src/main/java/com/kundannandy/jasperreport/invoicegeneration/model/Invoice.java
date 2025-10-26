package com.kundannandy.jasperreport.invoicegeneration.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {
    private String itemName;
    private int quantity;
    private String itemType;
    private double price;
}
