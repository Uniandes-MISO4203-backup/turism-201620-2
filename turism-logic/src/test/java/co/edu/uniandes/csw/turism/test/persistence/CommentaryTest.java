/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;


import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import co.edu.uniandes.csw.turism.persistence.CommentaryPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author fe.ruiz
 */

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommentaryTest {
    
    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CommentaryEntity.class.getPackage())
                .addPackage(CommentaryPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */

    /**
     * @generated
     */
    @Inject
    private CommentaryPersistence commentaryPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Configuración inicial de la prueba.
     *
     * @generated
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
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from CommentaryEntity").executeUpdate();
    }
    
    /**
     * @generated
     */
    private List<CommentaryEntity> data = new ArrayList<CommentaryEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CommentaryEntity entity = factory.manufacturePojo(CommentaryEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Agency.
     *
     * @generated
     */
    @Test
    public void createCommentaryTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CommentaryEntity newEntity = factory.manufacturePojo(CommentaryEntity.class);
        CommentaryEntity result =    commentaryPersistence.create(newEntity);
        Assert.assertNotNull(result);
        CommentaryEntity  entity = em.find(CommentaryEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de Commentarys .
     *
     * @generated
     */
    @Test
    public void getCommentaryTest() {
        List<CommentaryEntity> list =  commentaryPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CommentaryEntity ent : list) {
            boolean found = false;
            for (CommentaryEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Commentary.
     *
     * @generated
     */
    @Test
    public void getAgencyTest() {
        CommentaryEntity entity = data.get(0);
        CommentaryEntity newEntity = commentaryPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }
    
    /**
     * Prueba para eliminar un Commentary.
     *
     * @generated
     */
    @Test
    public void deleteCommentaryTest() {
        CommentaryEntity entity = data.get(0);
        commentaryPersistence.delete(entity.getId());
        CommentaryEntity deleted = em.find(CommentaryEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Commentary.
     *
     * @generated
     */
    @Test
    public void updateCommentaryTest() {
        CommentaryEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CommentaryEntity newEntity = factory.manufacturePojo(CommentaryEntity.class);

        newEntity.setId(entity.getId());
        commentaryPersistence.update(newEntity);
        CommentaryEntity resp = em.find(CommentaryEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
