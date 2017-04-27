package com.codecool.shop.model;

import java.util.Date;

/**
 * Created by peter on 2017.04.27..
 */
public class CreditCard {

    private Integer cardNumber;
    private Integer cardCode;
    private String cardHolder;
    private Date expiryDate;

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardCode() {
        return cardCode;
    }

    public void setCardCode(Integer cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
