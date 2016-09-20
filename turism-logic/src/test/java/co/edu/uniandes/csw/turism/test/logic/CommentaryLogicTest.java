/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.ejbs.CommentaryLogic;
import co.edu.uniandes.csw.turism.api.ICommentaryLogic;
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
public class CommentaryLogicTest {
    
    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICommentaryLogic comentaryLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<CommentaryEntity> data = new ArrayList<CommentaryEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CommentaryEntity.class.getPackage())
                .addPackage(CommentaryLogic.class.getPackage())
                .addPackage(ICommentaryLogic.class.getPackage())
                .addPackage(CommentaryPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
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
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from CommentaryEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CommentaryEntity entity = factory.manufacturePojo(CommentaryEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Commentary
     *
     * @generated
     */
    @Test
    public void createCommentaryTest() {
        CommentaryEntity newEntity = factory.manufacturePojo(CommentaryEntity.class);
        CommentaryEntity result = comentaryLogic.createComment(newEntity);
        Assert.assertNotNull(result);
        CommentaryEntity entity = em.find(CommentaryEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getScore(), entity.getScore());
    }

    /**
     * Prueba para consultar la lista de Commentarys
     *
     * @generated
     */
    @Test
    public void getCommentarysTest() {
        List<CommentaryEntity> list = comentaryLogic.getComments();
        Assert.assertEquals(data.size(), list.size());
        for (CommentaryEntity entity : list) {
            boolean found = false;
            for (CommentaryEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Commentary
     *
     * @generated
     */
    @Test
    public void getCommentaryTest() {
        CommentaryEntity entity = data.get(0);
        CommentaryEntity resultEntity = comentaryLogic.getComment(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
     
    }

    /**
     * Prueba para eliminar un Commentary
     *
     * @generated
     */
    @Test
    public void deleteCommentaryTest() {
        CommentaryEntity entity = data.get(1);
        comentaryLogic.deleteComment(entity.getId());
        CommentaryEntity deleted = em.find(CommentaryEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Product
     *
     * @generated
     */
    @Test
    public void updateProductTest() {
        CommentaryEntity entity = data.get(0);
        CommentaryEntity pojoEntity = factory.manufacturePojo(CommentaryEntity.class);

        pojoEntity.setId(entity.getId());
        comentaryLogic.updateComment(pojoEntity);

        CommentaryEntity resp = em.find(CommentaryEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getScore(), resp.getScore());
    }
}
