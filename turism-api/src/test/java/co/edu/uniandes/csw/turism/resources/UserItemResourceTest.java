package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IItemLogic;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 14/11/2016.
 */
public class UserItemResourceTest {

    @Mock
    private IItemLogic itemLogic;

    @Mock
    private HttpServletRequest req;

    @InjectMocks
    private UserItemResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = WebApplicationException.class)
    public void shouldThrowWebApplicationExceptionWhenNotAuthorizedToGetItems() throws Exception {
        when(req.getRemoteUser()).thenReturn(null);
        subject.getItems();
    }

    @Test(expected = WebApplicationException.class)
    public void shouldThrowWebApplicationExceptionWhenNotAuthorizedToCreatetems() throws Exception {
        when(req.getRemoteUser()).thenReturn(null);
        subject.createItem(null);
    }
}