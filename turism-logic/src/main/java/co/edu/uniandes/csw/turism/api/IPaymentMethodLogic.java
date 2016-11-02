/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
import java.util.List;

/**
 *
 * @author jd.cepeda
 */
public interface IPaymentMethodLogic {
    public int countPaymentMethods();
    public List<PaymentMethodEntity> getPaymentMethods(Long clientId);
    public List<PaymentMethodEntity> getPaymentMethods(Integer page, Integer maxRecords, Long clientId);
    public PaymentMethodEntity getPaymentMethod(Long paymentMethodId);
    public PaymentMethodEntity createPaymentMethod(Long clientId, PaymentMethodEntity entity);
    public PaymentMethodEntity updatePaymentMethod(Long clientId, PaymentMethodEntity entity);
    public void deletePaymentMethod(Long id);
}
