package com.company.gamestore.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Fee {
    //primary key
    //column name product_type
    private String productType;

    //not null
    //8 numbers total, 2 after decimal
    //000000.00
    private BigDecimal fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fee fee1 = (Fee) o;
        return productType.equals(fee1.productType) && fee.equals(fee1.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, fee);
    }

    @Override
    public String toString() {
        return "Fee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
