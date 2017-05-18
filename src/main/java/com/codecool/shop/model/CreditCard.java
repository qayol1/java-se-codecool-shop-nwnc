package com.codecool.shop.model;

import java.util.Date;

/**
 * Created by peter on 2017.04.27..
 * base model for credit card paying
 */
public class CreditCard {

    private Integer cardNumber;
    private Integer cardCode;
    private String cardHolder;
    private Date expiryDate;

    /**
     * @return gives back the credit card number
     */
    public Integer getCardNumber() {
        return cardNumber;
    }

    /**
     *
     * @param cardNumber sets the user credit card number
     */
    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     *
     * @return gives back the credit card security number
     */
    public Integer getCardCode() {
        return cardCode;
    }

    /**
     *
     * @param cardCode sets the credit card security number
     */
    public void setCardCode(Integer cardCode) {
        this.cardCode = cardCode;
    }

    /**
     *
     * @return gives back the credit card's owner name
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     *
     * @param cardHolder sets the credit card's owner name
     */

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     *
     * @return gives back the credit card expiry date
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     *
     * @param expiryDate sets the credit card expiry date
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
