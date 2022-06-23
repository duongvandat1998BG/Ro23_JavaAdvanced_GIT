package com.vti.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", length = 50, unique = true, nullable = false)
    private String productName;

    private String price;

//    @Temporal(TemporalType.DATE) // DD/MM/YYYY
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

//    @Temporal(TemporalType.TIME) // HH:mm
    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedDate;

    @Temporal(TemporalType.TIMESTAMP) // DD/MM/YYYY HH:mm
    @Column(name = "register_date")
    private Date registerDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
