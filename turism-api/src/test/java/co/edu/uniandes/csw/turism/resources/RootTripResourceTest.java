package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.tests.Utils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static co.edu.uniandes.csw.turism.tests.Utils.aLong;
import static co.edu.uniandes.csw.turism.tests.Utils.aTripEntityList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 14/11/2016.
 */
public class RootTripResourceTest {

    @Mock
    private ITripLogic tripLogic;

    @InjectMocks
    private RootTripResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(tripLogic.getTrips(null, null, null)).thenReturn(aTripEntityList());
    }

    @Test
    public void shouldNotReturnNullWhenGetTrips() throws Exception {
        assertNotNull(subject.getTrips());
    }

    @Test
    public void shouldNotReturnNullWhenGetTripsByCategoryId() throws Exception {
        Long categoryId = aLong();
        when(tripLogic.getTrips(null, null, categoryId)).thenReturn(aTripEntityList());
        assertNotNull(subject.getTripByCategory(categoryId));
    }

    @Test
    public void shouldNotReturnNullWhenGettingTripsDetail() throws Exception {
        Long tripId = aLong();
        when(tripLogic.getTrip(tripId)).thenReturn(Utils.aTripEntity());
        assertNotNull(subject.getTripDetail(tripId));
    }
}