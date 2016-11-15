package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IProductLogic;
import co.edu.uniandes.csw.turism.dtos.detail.BuyDetailDTO;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import co.edu.uniandes.csw.turism.tests.Utils;
import org.apache.derby.impl.jdbc.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 14/11/2016.
 */
public class ProductBuysResourceTest {

    @Mock
    private IProductLogic productLogic;

    @InjectMocks
    private ProductBuysResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotReturnNullWhenListingBuys() throws Exception {

        Long productsId = Long.valueOf(1);
        when(productLogic.listBuys(productsId)).thenReturn(Utils.aBuysEntityList());
        assertNotNull(subject.listBuys(productsId));
    }

    @Test
    public void shouldNotReturnNullWhenGettingBuys() throws Exception {

        Long productsId = Long.valueOf("1");
        Long buysId = Long.valueOf("1");
        when(productLogic.getBuys(productsId, buysId)).thenReturn(Utils.aButEntity());
        assertNotNull(subject.getBuys(productsId, buysId));
    }

    @Test
    public void shouldNotReturnNullWhenAddingBuys() throws Exception {

        Long productsId = Long.valueOf("1");
        Long buysId = Long.valueOf("1");
        when(productLogic.addBuys(productsId, buysId)).thenReturn(Utils.aButEntity());
        assertNotNull(subject.addBuys(productsId, buysId));
    }

    @Test
    public void shouldNotReturnNullWhenReplacingBuys() throws Exception {

        Long productsId = Long.valueOf("1");
        List<BuyDetailDTO> buyDetailDTOs = Utils.aBuyDetailDTOList();
        when(productLogic.replaceBuys(anyLong(), anyListOf(BuyEntity.class))).thenReturn(Utils.aButEntityList());
        assertNotNull(subject.replaceBuys(productsId, buyDetailDTOs));
    }

    @Test
    public void shouldRemoveBuysWithTheGivenIds() throws Exception {
        Long productsId = Long.valueOf("1");
        Long buysId = Long.valueOf("1");
        subject.removeBuys(productsId, buysId);
        Mockito.verify(productLogic).removeBuys(productsId, buysId);
    }
}