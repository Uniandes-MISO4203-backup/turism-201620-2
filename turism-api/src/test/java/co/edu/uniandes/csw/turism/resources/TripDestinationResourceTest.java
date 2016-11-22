package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IDestinationLogic;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 20/11/2016.
 */
public class TripDestinationResourceTest {
    @Mock
    private IDestinationLogic destinationLogic;

    @InjectMocks
    private TripDestinationsResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotReturnNullWhenGettingCommentaries() throws Exception {
        when(destinationLogic.getDestinations(anyLong())).thenReturn(new ArrayList<>());
        assertNotNull(subject.getDestinations());
    }
}