/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IClientLogic;
import co.edu.uniandes.csw.turism.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.turism.entities.ClientEntity;
import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
import co.edu.uniandes.csw.turism.persistence.PaymentMethodPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author jd.cepeda
 */
@Stateless
public class PaymentMethodLogic implements IPaymentMethodLogic {

    @Inject
    private PaymentMethodPersistence persistence;

    @Inject
    private IClientLogic clientLogic;

    /**
     * Obtiene el número de registros de PaymentMethod.
     *
     * @return Número de registros de PaymentMethod.
     * @generated
     */
    @Override
    public int countPaymentMethods() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de PaymentMethod que pertenecen a un
     * Client.
     *
     * @param clientId id del Client el cual es padre de los PaymentMethods.
     * @return Colección de objetos de PaymentMethodEntity.
     * @generated
     */
    @Override
    public List<PaymentMethodEntity> getPaymentMethods(Long clientId) {
        ClientEntity client = clientLogic.getClient(clientId);
        return client.getPaymentMethods();
    }

    /**
     * Obtiene la lista de los registros de PaymentMethod que pertenecen a un
     * Client indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param clientId id del Client el cual es padre de los PaymentMethods.
     * @return Colección de objetos de PaymentMethodEntity.
     * @generated
     */
    @Override
    public List<PaymentMethodEntity> getPaymentMethods(Integer page, Integer maxRecords, Long clientId) {
        return persistence.findAll(page, maxRecords, clientId);
    }

    /**
     * Obtiene los datos de una instancia de PaymentMethod a partir de su ID.
     *
     * @pre La existencia del elemento padre Client se debe garantizar.
     * @param paymentMethodId Identificador del PaymentMethod a consultar
     * @return Instancia de PaymentMethodEntity con los datos del PaymentMethod
     * consultado.
     * @generated
     */
    @Override
    public PaymentMethodEntity getPaymentMethod(Long paymentMethodId) {
        try {
            return persistence.find(paymentMethodId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El PaymentMethod no existe", e);
        }
    }

    /**
     * Se encarga de crear un PaymentMethod en la base de datos.
     *
     * @param entity Objeto de PaymentMethodEntity con los datos nuevos
     * @param clientId id del Client el cual sera padre del nuevo PaymentMethod.
     * @return Objeto de PaymentMethodEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public PaymentMethodEntity createPaymentMethod(Long clientId, PaymentMethodEntity entity) {
        ClientEntity client = clientLogic.getClient(clientId);
        entity.setClient(client);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de PaymentMethod.
     *
     * @param entity Instancia de PaymentMethodEntity con los nuevos datos.
     * @param clientId id del Client el cual sera padre del PaymentMethod
     * actualizado.
     * @return Instancia de PaymentMethodEntity con los datos actualizados.
     * @generated
     */
    @Override
    public PaymentMethodEntity updatePaymentMethod(Long clientId, PaymentMethodEntity entity) {
        ClientEntity client = clientLogic.getClient(clientId);
        entity.setClient(client);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de PaymentMethod de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deletePaymentMethod(Long id) {
        PaymentMethodEntity old = getPaymentMethod(id);
        persistence.delete(old.getId());
    }

}
