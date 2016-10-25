/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.turism.dtos.minimum.PaymentMethodDTO;
import co.edu.uniandes.csw.turism.entities.ClientEntity;
import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
import co.edu.uniandes.csw.turism.resources.PaymentMethodResource;
import co.edu.uniandes.csw.turism.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
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
public class PaymentMethodTest {

    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int ok = Response.Status.OK.getStatusCode();
    private final int created = Response.Status.CREATED.getStatusCode();
    private final int okWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<PaymentMethodEntity> ORACULO = new ArrayList<>();

    private final String clientPath = "clients";
    private final String paymentMethodPath = "paymentMethods";

    ClientEntity fatherClientEntity;

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
                .addPackage(PaymentMethodResource.class.getPackage())
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
        em.createQuery("delete from PaymentMethodEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
        ORACULO.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        fatherClientEntity = factory.manufacturePojo(ClientEntity.class);
        fatherClientEntity.setId(1L);
        em.persist(fatherClientEntity);

        for (int i = 0; i < 3; i++) {
            PaymentMethodEntity paymentMethod = factory.manufacturePojo(PaymentMethodEntity.class);
            paymentMethod.setId(i + 1L);
            paymentMethod.setClient(fatherClientEntity);
            em.persist(paymentMethod);
            ORACULO.add(paymentMethod);
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
                .path(clientPath)
                .path(fatherClientEntity.getId().toString())
                .path(paymentMethodPath);
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
        if (response.getStatus() == ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     * Prueba para crear un PaymentMethod
     *
     * @throws java.io.IOException
     * @generated
     */
    @Test
    public void createPaymentMethodTest() throws IOException {
        PaymentMethodDTO paymentMethod = factory.manufacturePojo(PaymentMethodDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
                .request().cookie(cookieSessionId)
                .post(Entity.entity(paymentMethod, MediaType.APPLICATION_JSON));

        PaymentMethodDTO paymentMethodTest = (PaymentMethodDTO) response.readEntity(PaymentMethodDTO.class);

        Assert.assertEquals(created, response.getStatus());

        Assert.assertEquals(paymentMethod.getName(), paymentMethodTest.getName());
        Assert.assertEquals(paymentMethod.getCardType(), paymentMethodTest.getCardType());
        Assert.assertEquals(paymentMethod.getCardNumber(), paymentMethodTest.getCardNumber());
        Assert.assertEquals(paymentMethod.getSecurityCode(), paymentMethodTest.getSecurityCode());
        Assert.assertEquals(paymentMethod.getExpirationMonth(), paymentMethodTest.getExpirationMonth());
        Assert.assertEquals(paymentMethod.getExpirationYear(), paymentMethodTest.getExpirationYear());

        PaymentMethodEntity entity = em.find(PaymentMethodEntity.class, paymentMethodTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un PaymentMethod
     *
     * @generated
     */
    @Test
    public void getPaymentMethodByIdTest() {
        Cookie cookieSessionId = login(username, password);

        PaymentMethodDTO paymentMethodTest = target
                .path(ORACULO.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(PaymentMethodDTO.class);

        Assert.assertEquals(paymentMethodTest.getId(), ORACULO.get(0).getId());
        Assert.assertEquals(paymentMethodTest.getName(), ORACULO.get(0).getName());
        Assert.assertEquals(paymentMethodTest.getCardType(), ORACULO.get(0).getCardType());
        Assert.assertEquals(paymentMethodTest.getCardNumber(), ORACULO.get(0).getCardNumber());
        Assert.assertEquals(paymentMethodTest.getSecurityCode(), ORACULO.get(0).getSecurityCode());
        Assert.assertEquals(paymentMethodTest.getExpirationMonth(), ORACULO.get(0).getExpirationMonth());
        Assert.assertEquals(paymentMethodTest.getExpirationYear(), ORACULO.get(0).getExpirationYear());
    }

    /**
     * Prueba para consultar la lista de PaymentMethods
     *
     * @throws java.io.IOException
     * @generated
     */
    @Test
    public void listPaymentMethodTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target.request().cookie(cookieSessionId).get();

        String listPaymentMethod = response.readEntity(String.class);
        List<PaymentMethodDTO> listPaymentMethodTest = new ObjectMapper().readValue(listPaymentMethod, List.class);
        Assert.assertEquals(ok, response.getStatus());
        Assert.assertEquals(3, listPaymentMethodTest.size());
    }

    /**
     * Prueba para actualizar un PaymentMethod
     *
     * @generated
     */
    @Test
    public void updatePaymentMethodTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        PaymentMethodDTO paymentMethod = new PaymentMethodDTO(ORACULO.get(0));

        PaymentMethodDTO paymentMethodChanged = factory.manufacturePojo(PaymentMethodDTO.class);

        paymentMethod.setName(paymentMethodChanged.getName());
        paymentMethod.setCardType(paymentMethodChanged.getCardType());
        paymentMethod.setCardNumber(paymentMethodChanged.getCardNumber());
        paymentMethod.setSecurityCode(paymentMethodChanged.getSecurityCode());
        paymentMethod.setExpirationMonth(paymentMethodChanged.getExpirationMonth());
        paymentMethod.setExpirationYear(paymentMethodChanged.getExpirationYear());

        Response response = target
                .path(paymentMethod.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(paymentMethod, MediaType.APPLICATION_JSON));

        PaymentMethodDTO paymentMethodTest = (PaymentMethodDTO) response.readEntity(PaymentMethodDTO.class);

        Assert.assertEquals(ok, response.getStatus());
        Assert.assertEquals(paymentMethod.getName(), paymentMethodTest.getName());
        Assert.assertEquals(paymentMethod.getCardType(), paymentMethodTest.getCardType());
        Assert.assertEquals(paymentMethod.getCardNumber(), paymentMethodTest.getCardNumber());
        Assert.assertEquals(paymentMethod.getSecurityCode(), paymentMethodTest.getSecurityCode());
        Assert.assertEquals(paymentMethod.getExpirationMonth(), paymentMethodTest.getExpirationMonth());
        Assert.assertEquals(paymentMethod.getExpirationYear(), paymentMethodTest.getExpirationYear());
    }

    /**
     * Prueba para eliminar un PaymentMethod
     *
     * @generated
     */
    @Test
    public void deletePaymentMethodTest() {
        Cookie cookieSessionId = login(username, password);
        PaymentMethodDTO PaymentMethod = new PaymentMethodDTO(ORACULO.get(0));
        Response response = target
                .path(PaymentMethod.getId().toString())
                .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(okWithoutContent, response.getStatus());
    }

}
