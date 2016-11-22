package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.CommentaryPersistence;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import javax.mail.search.IntegerComparisonTerm;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 21/11/2016.
 */
public class CommentaryLogicTest {

    @Mock
    private CommentaryPersistence persistence;

    @Mock
    private ITripLogic tripLogic;

    @InjectMocks
    private CommentaryLogic subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCountComments() throws Exception {
        subject.countComments();
        verify(persistence).count();
    }

    @Test
    public void shouldGetCommentsWithTripId() throws Exception {
        Integer page = Integer.valueOf(1);
        Integer maxrecords = Integer.valueOf(1);
        Long tripid = Long.valueOf("1");
        subject.getComments(page, maxrecords, tripid);
        verify(persistence).findAll(page, maxrecords, tripid);
    }

    @Test
    public void shouldGetCommentsWithoutTripId() throws Exception {
        Integer page = Integer.valueOf(1);
        Integer maxrecords = Integer.valueOf(1);
        subject.getComments(page, maxrecords, null);
        verify(persistence).findAll(page, maxrecords);
    }

    @Test
    public void shouldGetCommentById() throws Exception {

        Long id = Long.valueOf("1");
        subject.getComment(id);
        verify(persistence).find(id);
    }

    @Test
    public void shouldCreateComment() throws Exception {

        Long id = Long.valueOf("1");
        CommentaryEntity entity = new CommentaryEntity();
        TripEntity tripEntity = new TripEntity();
        entity.setTrip(tripEntity);
        when(tripLogic.getTrip(id)).thenReturn(tripEntity);
        subject.createComment(id, entity);
        verify(persistence).create(entity);
    }

    @Test
    public void shouldUpdateComment() throws Exception {

        Long id = Long.valueOf("1");
        CommentaryEntity entity = new CommentaryEntity();
        TripEntity tripEntity = new TripEntity();
        entity.setTrip(tripEntity);
        when(tripLogic.getTrip(id)).thenReturn(tripEntity);
        subject.updateComment(id, entity);
        verify(persistence).update(entity);
    }

    @Test
    public void shouldDeleteCommentbyId() throws Exception {

        Long id = Long.valueOf("1");
        CommentaryEntity entity = new CommentaryEntity();
        entity.setId(id);
        when(persistence.find(id)).thenReturn(entity);
        subject.deleteComment(id);
        verify(persistence).delete(id);
    }
}