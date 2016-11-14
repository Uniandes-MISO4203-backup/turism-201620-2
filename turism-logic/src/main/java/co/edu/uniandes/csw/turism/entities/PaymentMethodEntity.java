/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.cepeda
 */
@Entity
public class PaymentMethodEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private ClientEntity client;

    private String cardType;

    private Long cardNumber;

    private int securityCode;

    private int expirationMonth;

    private int expirationYear;

    /**
     * Obtiene el atributo client
     * 
     * @return atributo client
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Establece el valor del atributo client
     * 
     * @param client 
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    /**
     * Obtiene el atributo cardType
     * 
     * @return atributo cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Establece el valor del atributo cardType
     * 
     * @param cardType 
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * Obtiene el atributo cardNumber
     * 
     * @return atributo cardNumber
     */
    public Long getCardNumber() {
        return cardNumber;
    }

    /**
     * Establece el valor del atributo cardNumber
     * 
     * @param cardNumber 
     */
    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Obtiene el atributo securityCode
     * 
     * @return atributo securityCode
     */
    public int getSecurityCode() {
        return securityCode;
    }

    /**
     * Establece el valor del atributo securityCode
     * 
     * @param securityCode 
     */
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Obtiene el atributo expirationMonth
     * 
     * @return atributo expirationMonth
     */
    public int getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * Establece el valor del atributo expirationMonth
     * 
     * @param expirationMonth 
     */
    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    /**
     * Obtiene el atributo expirationYear
     * 
     * @return atributo expirationYear
     */
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * Establece el valor del atributo expirationYear
     * 
     * @param expirationYear 
     */
    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
