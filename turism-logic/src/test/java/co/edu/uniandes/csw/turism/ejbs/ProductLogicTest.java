package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IBuyLogic;
import co.edu.uniandes.csw.turism.entities.ProductEntity;
import co.edu.uniandes.csw.turism.persistence.ProductPersistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * Created by TOSHIBA on 14/11/2016.
 */
public class ProductLogicTest {

    @Mock
    private ProductPersistence persistence;

    @Mock
    private IBuyLogic buyLogic;

    @InjectMocks
    private ProductLogic subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCountProducts() throws Exception {
        subject.countProducts();
        verify(persistence).count();
    }

    @Test
    public void shouldGetProductsFindAll() throws Exception {
        subject.getProducts();
        verify(persistence).findAll();
    }

    @Test
    public void shouldGetProductsByPageAndMaxRecords() throws Exception {
        Integer page = 1;
        Integer maxRecords = 1;
        subject.getProducts(page, maxRecords);
        verify(persistence).findAll(page, maxRecords);
    }

    @Test
    public void shouldGetProduct() throws Exception {
        Long productId = 1L;
        subject.getProduct(productId);
        verify(persistence).find(productId);
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        subject.createProduct(productEntity);
        verify(persistence).create(productEntity);
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        subject.updateProduct(productEntity);
        verify(persistence).update(productEntity);
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        Long productId = 1L;
        subject.deleteProduct(productId);
        verify(persistence).delete(productId);
    }

}