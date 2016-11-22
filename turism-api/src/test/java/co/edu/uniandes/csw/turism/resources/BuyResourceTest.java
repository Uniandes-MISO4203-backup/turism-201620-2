package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IBuyLogic;
import co.edu.uniandes.csw.turism.dtos.detail.BuyDetailDTO;
import co.edu.uniandes.csw.turism.dtos.detail.ProductDetailDTO;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 21/11/2016.
 */
public class BuyResourceTest {

    @Mock
    private IBuyLogic buyLogic;

    @InjectMocks
    private BuyResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNotReturnNullWhenGettingBuys() throws Exception {

        Long id = Long.valueOf("1");
        when(buyLogic.getBuys()).thenReturn(new ArrayList<>());
        assertNotNull(subject.getBuys());
    }

    @Test
    public void testNotReturnNullWhenGettingBuyById() throws Exception {

        Long id = Long.valueOf("1");
        when(buyLogic.getBuy(id)).thenReturn(new BuyEntity());
        assertNotNull(subject.getBuy(id));
    }

    @Test
    public void testNotReturnNullWhenCreatingBuy() throws Exception {

        BuyDetailDTO dto = new BuyDetailDTO();
        when(buyLogic.createBuy(any(BuyEntity.class))).thenReturn(new BuyEntity());
        assertNotNull(subject.createBuy(dto));
    }

    @Test
    public void testNotReturnNullWhenUpdatingBuy() throws Exception {

        BuyDetailDTO dto = new BuyDetailDTO();
        dto.setProduct(new ProductDetailDTO());
        Long id = Long.valueOf("1");
        when(buyLogic.updateBuy(any(BuyEntity.class))).thenReturn(new BuyEntity());
        when(buyLogic.getBuy(id)).thenReturn(new BuyEntity());
        assertNotNull(subject.updateBuy(id, dto));
    }

    @Test
    public void shouldDeleteBuyById() throws Exception {
        Long id = Long.valueOf("1");
        subject.deleteBuy(id);
        verify(buyLogic).deleteBuy(id);
    }
}