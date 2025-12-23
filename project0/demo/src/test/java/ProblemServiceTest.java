import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.SolutionService;
import org.leetrepository.service.TopicService;
import org.leetrepository.service.model.Problem;
import org.leetrepository.service.model.Solution;
import org.leetrepository.service.model.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProblemServiceTest {
    @Mock
    private ProblemDAO problemDAOMock;

    @InjectMocks
    private ProblemService problemServiceMock;

    private ProblemEntity problemEntityTest;
    private Problem problemModelTest;
    private Set<Solution> solutionListTest;
    private Set<SolutionEntity> solutionEntityListTest;
    private TopicEntity topicEntity;
    private Set<Topic> topicListTest;
    private Set<TopicEntity> topicEntityListTest;

    @BeforeEach
    void setup() {
        //setup Problem Entity
        problemEntityTest = new ProblemEntity();
        problemEntityTest.setId(24);
        problemEntityTest.setName("test problem name");
        problemEntityTest.setDescription("test problem description");
        //set difficulty omitted: test if it'll be set to default or one of the allowed difficulties
        problemEntityTest.setUrl("test url");

        //setup solution list associated with a problem
        Solution solutionTest = new Solution();
        solutionTest.setId(1);
        solutionTest.setName("test solution name");
        //omit set problem id, test if problem id matches problemEntity.id
        solutionTest.setDescription("test solution description");
        solutionTest.setSolutionCode(createByteBuffer(createByteArray()));
        solutionListTest = new HashSet<>();
        solutionListTest.add(solutionTest);

        //setup topic list associated with a problem
        Topic topicTest = new Topic();
        topicTest.setId(1);
        topicTest.setName("test topic name");
        topicListTest = new HashSet<>();
        topicListTest.add(topicTest);

        topicEntity = new TopicEntity();
        topicEntity.setId(1);
        topicEntity.setName("test solution name");

        //setup problem model
        problemModelTest = new Problem();
        problemModelTest.setId(problemEntityTest.getId());
        problemModelTest.setName(problemEntityTest.getName());
        problemModelTest.setDescription(problemEntityTest.getDescription());
        //omit difficulty
        problemModelTest.setUrl(problemEntityTest.getDescription());
        problemModelTest.setSolutions(solutionListTest);
        problemModelTest.setTopics(topicListTest);

    }
    ByteBuffer createByteBuffer(byte[] array) {
        return ByteBuffer.wrap(array);
    }
    byte[] createByteArray() {
        byte[] array = {(byte) 0xde, (byte) 0xad, (byte) 0xbe, (byte) 0xef};
        return array;
    }
    byte[] createByteArray(int[] array) {
        byte[] arr = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = (byte) i;
        }
        return arr;
    }

    @Test
    void createEntity_Success_ReturnsNewId() throws SQLException {
        when(problemDAOMock.create(problemEntityTest)).thenReturn(problemEntityTest.getId());

        int result = problemServiceMock.createEntity(problemEntityTest);

        assertEquals(problemEntityTest.getId(), result);
        //verify DAO.create() was called only once. in verify, if no times() parameter specified, it defaults to 1
        verify(problemDAOMock, times(1)).create(problemEntityTest);
    }

    @Test
    void createEntity_Failure_ReturnsNegativeOne() throws SQLException {
        when(problemDAOMock.create(problemEntityTest))
                .thenReturn(problemEntityTest.getId())
                .thenReturn(-1);

        problemServiceMock.createEntity(problemEntityTest);
        int result = problemServiceMock.createEntity(problemEntityTest);

        assertEquals(-1, result);
        verify(problemDAOMock, times(2)).create(problemEntityTest);
    }

    @Test
    void getEntityById_Success_ReturnsEntity() throws SQLException {
        when(problemDAOMock.findById(problemEntityTest.getId())).thenReturn(Optional.of(problemEntityTest));

        Optional<ProblemEntity> result = problemServiceMock.getEntityById(problemEntityTest.getId());

        assertEquals(problemEntityTest, result.get());
        verify(problemDAOMock).findById(problemEntityTest.getId());
    }

    @Test
    void updateEntity_Success_ReturnsEntity() throws SQLException {
        problemEntityTest.setDescription("new Description");
        when(problemDAOMock.updateById(problemEntityTest)).thenReturn(Optional.of(problemEntityTest));

        ProblemEntity result = problemServiceMock.updateEntity(problemEntityTest);

        assertEquals(problemEntityTest, result);
        assertEquals(problemEntityTest.getDescription(), result.getDescription());
        verify(problemDAOMock).updateById(problemEntityTest);
    }

    @Test
    void updateEntity_Failure_ReturnsNull() throws SQLException {
        problemEntityTest.setId(999999);
        when(problemDAOMock.updateById(problemEntityTest)).thenReturn(Optional.empty());

        ProblemEntity result = problemServiceMock.updateEntity((problemEntityTest));

        assertNull(result);
    }

    @Test
    void deleteEntity_SuccessAndFailure_ReturnsTrueFalse() throws SQLException {
        when(problemDAOMock.deleteById(problemEntityTest.getId())).thenReturn(Optional.of(problemEntityTest));
        when(problemDAOMock.deleteById(9876543)).thenReturn(Optional.empty());

        boolean resultHappy = problemServiceMock.deleteEntity(problemEntityTest.getId());
        boolean resultSad = problemServiceMock.deleteEntity(9876543);

        assertTrue(resultHappy);
        assertFalse(resultSad);
    }

    @Test
    void getModelById_Success_ReturnsModel() throws SQLException {
        when(problemDAOMock.findById(problemEntityTest.getId())).thenReturn(Optional.of(problemEntityTest));

        Optional<Problem> result = problemServiceMock.getModelById(problemEntityTest.getId());

        assertEquals(problemServiceMock.convertEntityToModel(problemEntityTest), result);
    }

    @Test
    void getModelById_Failure_ReturnsEmpty() throws SQLException {
        when(problemDAOMock.findById(123456)).thenReturn(Optional.empty());

        Optional<Problem> result = problemServiceMock.getModelById(123456);

        assertEquals(Optional.empty(), result);
    }
}
