package com.example.demo.dto;

public class TourFilter {
    private String country;
    private String type;
    private Integer durationMin;
    private Integer durationMax;
    private Double priceMin;
    private Double priceMax;
    private Boolean is_hot;

    public TourFilter(String country, String type, Integer durationMin, Integer durationMax, Double priceMin, Double priceMax, Boolean is_hot) {
        this.country = country;
        this.type = type;
        this.durationMin = durationMin;
        this.durationMax = durationMax;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.is_hot = is_hot;
    }

    // Getters and setters
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getDurationMin() { return durationMin; }
    public void setDurationMin(Integer durationMin) { this.durationMin = durationMin; }

    public Integer getDurationMax() { return durationMax; }
    public void setDurationMax(Integer durationMax) { this.durationMax = durationMax; }

    public Double getPriceMin() { return priceMin; }
    public void setPriceMin(Double priceMin) { this.priceMin = priceMin; }

    public Double getPriceMax() { return priceMax; }
    public void setPriceMax(Double priceMax) { this.priceMax = priceMax; }

    public Boolean getIs_hot() { return is_hot; }
    public void setIs_hot(Boolean is_hot) { this.is_hot = is_hot; }
}
