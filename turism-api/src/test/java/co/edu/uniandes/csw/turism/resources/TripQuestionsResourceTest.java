package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 20/11/2016.
 */
public class TripQuestionsResourceTest {

    @Mock
    private IQuestionLogic questionLogic;

    @InjectMocks
    private TripQuestionsResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotReturnNullWhenGettingQuestions() throws Exception {
        when(questionLogic.getQuestions(anyLong())).thenReturn(new ArrayList<>());
        assertNotNull(subject.getQuestions());
    }
}