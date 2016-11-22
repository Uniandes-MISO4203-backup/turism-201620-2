/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jd.cepeda
 */
@XmlRootElement
public class PaymentMethodDTO implements Serializable {

    private Long id;
    private String name;
    private String cardType;
    private Long cardNumber;
    private int securityCode;
    private int expirationMonth;
    private int expirationYear;

    public PaymentMethodDTO() {
        // Método auto-generado
    }

    public PaymentMethodDTO(PaymentMethodEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.cardType = entity.getCardType();
            this.cardNumber = entity.getCardNumber();
            this.securityCode = entity.getSecurityCode();
            this.expirationMonth = entity.getExpirationMonth();
            this.expirationYear = entity.getExpirationYear();
        }
    }

    public PaymentMethodEntity toEntity() {
        PaymentMethodEntity entity = new PaymentMethodEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setCardType(this.getCardType());
        entity.setCardNumber(this.getCardNumber());
        entity.setSecurityCode(this.getSecurityCode());
        entity.setExpirationMonth(this.getExpirationMonth());
        entity.setExpirationYear(this.getExpirationYear());
        return entity;
    }

    /**
     * getId
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * setId
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getName
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getCardType
     *
     * @return
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * setCardType
     *
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * getCardNumber
     *
     * @return
     */
    public Long getCardNumber() {
        return cardNumber;
    }

    /**
     * setCardNumber
     *
     * @param cardNumber
     */
    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * getSecurityCode
     *
     * @return
     */
    public int getSecurityCode() {
        return securityCode;
    }

    /**
     * setSecurityCode
     *
     * @param securityCode
     */
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * getExpirationMonth
     *
     * @return
     */
    public int getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * setExpirationMonth
     *
     * @param expirationMonth
     */
    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    /**
     * getExpirationYear
     *
     * @return
     */
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * setExpirationYear
     *
     * @param expirationYear
     */
    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

}
