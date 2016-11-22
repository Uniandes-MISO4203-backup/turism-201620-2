package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.ICommentaryLogic;
import co.edu.uniandes.csw.turism.dtos.detail.CommentaryDetailDTO;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
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
public class CommentaryResourceTest {

    @Mock
    private ICommentaryLogic commentaryLogic;

    @InjectMocks
    private CommentaryResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotReturnNullWhenGettingComments() throws Exception {

        when(commentaryLogic.getComments(anyLong())).thenReturn(new ArrayList<>());
        assertNotNull(subject.getComments());
    }

    @Test
    public void shouldNotReturnNullWhenGettingCommentById() throws Exception {

        Long id = Long.valueOf("1");
        when(commentaryLogic.getComment(id)).thenReturn(new CommentaryEntity());
        assertNotNull(subject.getComment(id));
    }

    @Test
    public void shouldNotReturnNullWhenCreatingComment() throws Exception {

        CommentaryDetailDTO dto = new CommentaryDetailDTO();
        when(commentaryLogic.createComment(anyLong(), any(CommentaryEntity.class))).thenReturn(new CommentaryEntity());
        assertNotNull(subject.createCommentary(dto));
    }

    @Test
    public void shouldNotReturnNullWhenUpdatingComment() throws Exception {

        Long id = Long.valueOf("1");
        CommentaryDetailDTO dto = new CommentaryDetailDTO();
        when(commentaryLogic.updateComment(anyLong(), any(CommentaryEntity.class))).thenReturn(new CommentaryEntity());
        assertNotNull(subject.updateComment(id, dto));
    }

    @Test
    public void shouldDeleteCommmentsById() throws Exception {
        Long id = Long.valueOf("1");
        subject.deletComment(id);
        verify(commentaryLogic).deleteComment(id);
    }
}