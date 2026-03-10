package com.devsuperior.dsmeta.dto;

public class SummaryDTO {
    private String name;
    private Double amount;

    public SummaryDTO() {
    }

    public SummaryDTO(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }
}
