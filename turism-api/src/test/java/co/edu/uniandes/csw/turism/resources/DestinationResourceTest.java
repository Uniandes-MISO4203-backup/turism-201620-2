package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IDestinationLogic;
import co.edu.uniandes.csw.turism.dtos.detail.DestinationDetailDTO;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import co.edu.uniandes.csw.turism.tests.Utils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static co.edu.uniandes.csw.turism.tests.Utils.aDestinationDetailDTO;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 14/11/2016.
 */
public class DestinationResourceTest {

    @Mock
    private IDestinationLogic destinationLogic;

    @InjectMocks
    private DestinationResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotReturnNullWhenGetDestinations() throws Exception {

        when(destinationLogic.getDestinations(anyLong())).thenReturn(Utils.aDestinationEntityList());
        assertNotNull(subject.getDestinations());
    }

    @Test
    public void shouldNotReturnNullWhenCreatingDestinations() throws Exception {

        DestinationDetailDTO destinationDetailDTO = aDestinationDetailDTO();
        when(destinationLogic.createDestination(anyLong(), any(DestinationEntity.class))).thenReturn(Utils.aDestinationEntity());
        assertNotNull(subject.createDestination(destinationDetailDTO));
    }

    @Test
    public void shouldNotReturnNullWhenUpdatingDestinations() throws Exception {

        DestinationDetailDTO destinationDetailDTO = aDestinationDetailDTO();
        when(destinationLogic.updateDestination(anyLong(), any(DestinationEntity.class))).thenReturn(Utils.aDestinationEntity());
        assertNotNull(subject.updateDestination(Long.valueOf(1), destinationDetailDTO));
    }

    @Test
    public void shouldDeleteTheDestinationByTheGivenId() throws Exception {
        Long destinationId = Long.valueOf(0);
        subject.deleteDestination(destinationId);
        verify(destinationLogic).deleteDestination(destinationId);
    }
}