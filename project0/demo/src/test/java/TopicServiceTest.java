import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.TopicService;
import org.leetrepository.service.model.Problem;
import org.leetrepository.service.model.Topic;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {
    @Mock
    TopicDAO topicDAOMock;

    @InjectMocks
    TopicService topicServiceMock;

    Set<TopicEntity> topicEntitySet = new HashSet<>();
    Set<Topic> topicSet = new HashSet<>();

    @Test
    void getTopicsGivenProblemEntity_Success_ReturnSet() throws SQLException {
        //setup
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(1);
        TopicEntity topicEntity1 = new TopicEntity();
        TopicEntity topicEntity2 = new TopicEntity();
        Topic topic1 = new Topic();
        Topic topic2 = new Topic();
        topicEntity1.setId(1);
        topicEntity1.setName("test topic");
        topicEntity2.setId(2);
        topicEntity2.setName("test topic 2");
        topic1.setId(topicEntity1.getId());
        topic1.setName(topicEntity1.getName());
        topic2.setId(topicEntity2.getId());
        topic2.setName(topicEntity2.getName());
        topicEntitySet.add(topicEntity1);
        topicEntitySet.add(topicEntity2);
        topicSet.add(topic1);
        topicSet.add(topic2);
        when(topicDAOMock.findTopicsGivenProblem(problemEntity)).thenReturn(topicEntitySet);

        Set<Topic> result = topicServiceMock.getTopicsGivenProblemEntity(problemEntity);

        assertEquals(topicSet, result);
    }

    @Test
    void getTopicsGivenProblemEntity_Failure_ReturnsEmpty() throws SQLException {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(99999);
        when(topicDAOMock.findTopicsGivenProblem(problemEntity)).thenReturn(topicEntitySet); //empty set

        Set<Topic> result = topicServiceMock.getTopicsGivenProblemEntity(problemEntity);

        assertEquals(topicSet, result);
    }
}
