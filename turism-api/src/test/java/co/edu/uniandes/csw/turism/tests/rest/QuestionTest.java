/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.turism.dtos.minimum.QuestionDTO;
import co.edu.uniandes.csw.turism.resources.QuestionResource;
import co.edu.uniandes.csw.turism.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: trips/{questionsId: \\d+}/questions/
 */
@RunWith(Arquillian.class)
public class QuestionTest {
    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<QuestionEntity> oraculo = new ArrayList<>();

    private final String tripPath = "trips";
    private final String questionPath = "questions";

    TripEntity fatherTripEntity;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(QuestionResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "TurismPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        em.createQuery("delete from QuestionEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        fatherTripEntity = factory.manufacturePojo(TripEntity.class);
        fatherTripEntity.setId(1L);
        em.persist(fatherTripEntity);

        for (int i = 0; i < 3; i++) {            
            QuestionEntity question = factory.manufacturePojo(QuestionEntity.class);
            question.setId(i + 1L);
            question.setTrip(fatherTripEntity);
            em.persist(question);
            oraculo.add(question);
        }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
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
        target = createWebTarget()
                .path(tripPath)
                .path(fatherTripEntity.getId().toString())
                .path(questionPath);
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = createWebTarget().path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     * Prueba para crear un FAQ
     *
     * @generated
     */
    @Test
    public void createQuestionTest() throws IOException {
        QuestionDTO question = factory.manufacturePojo(QuestionDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(question, MediaType.APPLICATION_JSON));

        QuestionDTO  questionTest = (QuestionDTO) response.readEntity(QuestionDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(question.getName(), questionTest.getName());
        Assert.assertEquals(question.getRespuesta(), questionTest.getRespuesta());

        QuestionEntity entity = em.find(QuestionEntity.class, questionTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un FAQ
     *
     * @generated
     */
    @Test
    public void getQuestionByIdTest() {
        Cookie cookieSessionId = login(username, password);

        QuestionDTO questionTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(QuestionDTO.class);
        
        Assert.assertEquals(questionTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(questionTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(questionTest.getRespuesta(), oraculo.get(0).getRespuesta());
    }

    /**
     * Prueba para consultar la lista de Questions
     *
     * @generated
     */
    @Test
    public void listQuestionTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listQuestion = response.readEntity(String.class);
        List<QuestionDTO> listQuestionTest = new ObjectMapper().readValue(listQuestion, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listQuestionTest.size());
    }

    /**
     * Prueba para actualizar un Question
     *
     * @generated
     */
    @Test
    public void updateQuestionTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        QuestionDTO question = new QuestionDTO(oraculo.get(0));

        QuestionDTO questionChanged = factory.manufacturePojo(QuestionDTO.class);

        question.setName(questionChanged.getName());
        question.setRespuesta(questionChanged.getRespuesta());

        Response response = target
            .path(question.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(question, MediaType.APPLICATION_JSON));

        QuestionDTO questionTest = (QuestionDTO) response.readEntity(QuestionDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(question.getName(), questionTest.getName());
        Assert.assertEquals(question.getRespuesta(), questionTest.getRespuesta());
    }

    /**
     * Prueba para eliminar un Question
     *
     * @generated
     */
    @Test
    public void deleteQuestionTest() {
        Cookie cookieSessionId = login(username, password);
        QuestionDTO question = new QuestionDTO(oraculo.get(0));
        Response response = target
            .path(question.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
