/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.turism.ejbs.PaymentMethodLogic;
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
public class PaymentMethodLogicTest {

    ClientEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IPaymentMethodLogic paymentMethodLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PaymentMethodEntity> data = new ArrayList<PaymentMethodEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaymentMethodEntity.class.getPackage())
                .addPackage(PaymentMethodLogic.class.getPackage())
                .addPackage(IPaymentMethodLogic.class.getPackage())
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
     * Prueba para crear un PaymentMethod
     */
    @Test
    public void createPaymentMethodTest() {
        PaymentMethodEntity newEntity = factory.manufacturePojo(PaymentMethodEntity.class);
        PaymentMethodEntity result = paymentMethodLogic.createPaymentMethod(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        PaymentMethodEntity entity = em.find(PaymentMethodEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCardType(), entity.getCardType());
        Assert.assertEquals(newEntity.getCardNumber(), entity.getCardNumber());
        Assert.assertEquals(newEntity.getSecurityCode(), entity.getSecurityCode());
        Assert.assertEquals(newEntity.getExpirationMonth(), entity.getExpirationMonth());
        Assert.assertEquals(newEntity.getExpirationYear(), entity.getExpirationYear());
    }

    /**
     * Prueba para consultar la lista de PaymentMethods
     */
    @Test
    public void getPaymentMethodsTest() {
        List<PaymentMethodEntity> list = paymentMethodLogic.getPaymentMethods(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PaymentMethodEntity entity : list) {
            boolean found = false;
            for (PaymentMethodEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PaymentMethod
     */
    @Test
    public void getPaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        PaymentMethodEntity resultEntity = paymentMethodLogic.getPaymentMethod(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getCardType(), resultEntity.getCardType());
        Assert.assertEquals(entity.getCardNumber(), resultEntity.getCardNumber());
        Assert.assertEquals(entity.getSecurityCode(), resultEntity.getSecurityCode());
        Assert.assertEquals(entity.getExpirationMonth(), resultEntity.getExpirationMonth());
        Assert.assertEquals(entity.getExpirationYear(), resultEntity.getExpirationYear());
    }

    /**
     * Prueba para eliminar un PaymentMethod
     */
    @Test
    public void deletePaymentMethodTest() {
        PaymentMethodEntity entity = data.get(1);
        paymentMethodLogic.deletePaymentMethod(entity.getId());
        PaymentMethodEntity deleted = em.find(PaymentMethodEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PaymentMethod
     */
    @Test
    public void updatePaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        PaymentMethodEntity pojoEntity = factory.manufacturePojo(PaymentMethodEntity.class);

        pojoEntity.setId(entity.getId());

        paymentMethodLogic.updatePaymentMethod(fatherEntity.getId(), pojoEntity);

        PaymentMethodEntity resp = em.find(PaymentMethodEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getCardType(), resp.getCardType());
        Assert.assertEquals(pojoEntity.getCardNumber(), resp.getCardNumber());
        Assert.assertEquals(pojoEntity.getSecurityCode(), resp.getSecurityCode());
        Assert.assertEquals(pojoEntity.getExpirationMonth(), resp.getExpirationMonth());
        Assert.assertEquals(pojoEntity.getExpirationYear(), resp.getExpirationYear());
    }

}
