package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.turism.tests.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static co.edu.uniandes.csw.turism.tests.Utils.aQuestionDTO;
import static co.edu.uniandes.csw.turism.tests.Utils.aQuestionDetailDTO;
import static co.edu.uniandes.csw.turism.tests.Utils.aQuestionEntity;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TOSHIBA on 14/11/2016.
 */
public class QuestionResourceTest {

    @Mock
    private IQuestionLogic questionLogic = mock(IQuestionLogic.class);

    @InjectMocks
    private QuestionResource subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(questionLogic.getQuestion(anyLong())).thenReturn(aQuestionEntity());
    }

    @Test
    public void shouldNotReturnNullWhenGetQuestions() throws Exception {
        Assert.assertNotNull(subject.getQuestions());
    }

    @Test
    public void shouldNotReturnNullWhenGetQuestionById() throws Exception {
        Assert.assertNotNull(subject.getQuestion(Long.valueOf("0")));
    }

    @Test
    public void shouldNotReturnNullWhenCreatingQuestion() throws Exception {

        when(questionLogic.createQuestion(anyLong(), any(QuestionEntity.class))).thenReturn(aQuestionEntity());
        Assert.assertNotNull(subject.createQuestion(aQuestionDetailDTO()));
    }

    @Test
    public void shouldNotReturnNullWhenupdatingQuestion() throws Exception {

        when(questionLogic.updateQuestion(anyLong(), any(QuestionEntity.class))).thenReturn(aQuestionEntity());
        Assert.assertNotNull(subject.updateQuestion(Long.valueOf("1"), aQuestionDetailDTO()));
    }

    @Test
    public void shouldDeleteQuestionById() throws Exception {
        Long questionId = Long.valueOf("1");
        subject.deleteQuestion(questionId);
        verify(questionLogic).deleteQuestion(questionId);
    }
}