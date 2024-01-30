package com.imaginaryproducts.lib.product.entity;

import com.imaginaryproducts.shared.entity.Stringify;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;
    @Column(name="name", unique = true)
    @NotNull
    @Size(min = 3, max = 50, message = "The name of the product should be 3 to 50 characters long.")
    private String name;
    @Column(name="description", columnDefinition = "TEXT")
    @Size(max = 300, message = "The name of the product should be 0 to 300 characters long.")
    private String description;
    @Column(name = "price")
    @NotNull
    @DecimalMin(value = "0.1", message = "The product should to cost at least 1$.")
    private Double price;
    @Column(name = "quantity")
    @NotNull
    @Min(value = 0, message = "The amount should not reach the negative.")
    private Long quantity;
    @Column(name="manufacturer", nullable = false)
    @NotNull
    @Size(min = 3, max = 50, message = "The name of the manufacturer should be 3 to 50 characters long.")
    private String manufacturer;
    @Column(name = "manufacturing_date", nullable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date manufacturingDate;
    @Column(name = "acquisition_date", nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp acquisitionDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull
    private Status status;

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Timestamp getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Timestamp acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Stringify.build("Product", new LinkedHashMap<>() {{
            put("Id", id);
            put("Name", name);
            put("Description", description);
            put("Price", price);
            put("Quantity", quantity);
            put("Manufacturer", manufacturer);
            put("Manufacturing Date", manufacturingDate);
            put("Acquisition Date (Timestamp)", acquisitionDate);
            put("Status", status);
        }});
    }
}
