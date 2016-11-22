package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.TripDetailDTO;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 21/11/2016.
 */
public class TripResourceTest {

    @Mock
    private ITripLogic tripLogic;

    @InjectMocks
    private TripResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotReturnNullWhenGettingTrips() throws Exception {

        when(tripLogic.getTrips(anyLong())).thenReturn(new ArrayList<>());
        assertNotNull(subject.getTrips());
    }

    @Test
    public void shouldNotReturnNullWhenGettingTripById() throws Exception {

        Long id = Long.valueOf("1");
        when(tripLogic.getTrip(id)).thenReturn(new TripEntity());
        assertNotNull(subject.getTrip(id));
    }

    @Test
    public void shouldNotReturnNullWhenCreatingQuestion() throws Exception {

        when(tripLogic.createTrip(anyLong(), any(TripEntity.class))).thenReturn(new TripEntity());
        Assert.assertNotNull(subject.createTrip(new TripDetailDTO()));
    }

    @Test
    public void shouldNotReturnNullWhenupdatingQuestion() throws Exception {

        when(tripLogic.getTrip(anyLong())).thenReturn(new TripEntity());
        when(tripLogic.updateTrip(anyLong(), any(TripEntity.class))).thenReturn(new TripEntity());
        Assert.assertNotNull(subject.updateTrip(Long.valueOf("1"), new TripDetailDTO()));
    }

    @Test
    public void shouldDeleteQuestionById() throws Exception {
        Long tripId = Long.valueOf("1");
        subject.deleteTrip(tripId);
        verify(tripLogic).deleteTrip(tripId);
    }
}