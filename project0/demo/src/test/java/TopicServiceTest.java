import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.TopicService;
import org.leetrepository.service.model.Topic;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {
    @Mock
    TopicDAO topicDAOMock;

    @Mock
    ProblemService problemServiceMock;

    @InjectMocks
    TopicService topicServiceMock;

    Set<TopicEntity> topicEntitySet = new HashSet<>();
    Set<Topic> topicSet = new HashSet<>();

    @Test
    void TopicsGivenProblemEntity_Success_ReturnSet
}
