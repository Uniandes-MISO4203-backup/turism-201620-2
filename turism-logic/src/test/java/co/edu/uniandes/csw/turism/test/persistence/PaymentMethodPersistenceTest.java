/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.ClientEntity;
import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
import co.edu.uniandes.csw.turism.persistence.PaymentMethodPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.cepeda
 */
@RunWith(Arquillian.class)
public class PaymentMethodPersistenceTest {

    ClientEntity fatherEntity;

    @Inject
    private PaymentMethodPersistence paymentMethodPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PaymentMethodEntity> data = new ArrayList<PaymentMethodEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaymentMethodEntity.class.getPackage())
                .addPackage(PaymentMethodPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PaymentMethodEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(ClientEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            PaymentMethodEntity entity = factory.manufacturePojo(PaymentMethodEntity.class);

            entity.setClient(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un PaymentMethod.
     *
     * @generated
     */
    @Test
    public void createPaymentMethodTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PaymentMethodEntity newEntity = factory.manufacturePojo(PaymentMethodEntity.class);
        newEntity.setClient(fatherEntity);
        PaymentMethodEntity result = paymentMethodPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PaymentMethodEntity entity = em.find(PaymentMethodEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCardType(), entity.getCardType());
        Assert.assertEquals(newEntity.getCardNumber(), entity.getCardNumber());
        Assert.assertEquals(newEntity.getSecurityCode(), entity.getSecurityCode());
        Assert.assertEquals(newEntity.getExpirationMonth(), entity.getExpirationMonth());
        Assert.assertEquals(newEntity.getExpirationYear(), entity.getExpirationYear());
    }

    /**
     * Prueba para consultar la lista de PaymentMethods.
     */
    @Test
    public void getPaymentMethodsTest() {
        List<PaymentMethodEntity> list = paymentMethodPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PaymentMethodEntity ent : list) {
            boolean found = false;
            for (PaymentMethodEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PaymentMethod.
     */
    @Test
    public void getPaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        PaymentMethodEntity newEntity = paymentMethodPersistence.find(entity.getClient().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCardType(), newEntity.getCardType());
        Assert.assertEquals(entity.getCardNumber(), newEntity.getCardNumber());
        Assert.assertEquals(entity.getSecurityCode(), newEntity.getSecurityCode());
        Assert.assertEquals(entity.getExpirationMonth(), newEntity.getExpirationMonth());
        Assert.assertEquals(entity.getExpirationYear(), newEntity.getExpirationYear());
    }

    /**
     * Prueba para eliminar un PaymentMethod.
     */
    @Test
    public void deletePaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        paymentMethodPersistence.delete(entity.getId());
        PaymentMethodEntity deleted = em.find(PaymentMethodEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PaymentMethod.
     */
    @Test
    public void updatePaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaymentMethodEntity newEntity = factory.manufacturePojo(PaymentMethodEntity.class);

        newEntity.setId(entity.getId());

        paymentMethodPersistence.update(newEntity);

        PaymentMethodEntity resp = em.find(PaymentMethodEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCardType(), resp.getCardType());
        Assert.assertEquals(newEntity.getCardNumber(), resp.getCardNumber());
        Assert.assertEquals(newEntity.getSecurityCode(), resp.getSecurityCode());
        Assert.assertEquals(newEntity.getExpirationMonth(), resp.getExpirationMonth());
        Assert.assertEquals(newEntity.getExpirationYear(), resp.getExpirationYear());
    }

}
