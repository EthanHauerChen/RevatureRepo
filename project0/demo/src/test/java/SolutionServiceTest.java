import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.SolutionService;
import org.leetrepository.service.model.Problem;
import org.leetrepository.service.model.Solution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SolutionServiceTest {
    @Mock
    SolutionDAO solutionDAOMock;

    @Mock
    ProblemService problemServiceMock;

    @InjectMocks
    SolutionService solutionServiceMock;

    Set<SolutionEntity> solutionEntitySet = new HashSet<>();
    Set<Solution> solutionSet = new HashSet<>();
    ProblemEntity problemEntity;
    Problem problem;

    @Test
    void getSolutionsGivenProblemId_Success_ReturnsSet() throws SQLException {
        //setup solution entities to go instide of solutionEntitySet
        problemEntity = new ProblemEntity();
        problem = new Problem();
        problem.setId(1);
        problem.setName("Test problem");
        SolutionEntity s1 = new SolutionEntity();
        SolutionEntity s2 = new SolutionEntity();
        s1.setProblemId(1);
        s2.setProblemId(1);
        s1.setId(1);
        s2.setId(2);
        solutionEntitySet.add(s1);
        solutionEntitySet.add(s2);
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        solution1.setProblem(problem);
        solution2.setProblem(problem);
        solution1.setId(1);
        solution2.setId(2);
        solutionSet.add(solution1);
        solutionSet.add(solution2);
        when(problemServiceMock.getEntityById(1)).thenReturn(Optional.of(problemEntity));
        when(problemServiceMock.convertEntityToModel(problemEntity)).thenReturn(Optional.of(problem));
        when(solutionDAOMock.findSolutionsGivenProblemId(1)).thenReturn(solutionEntitySet);

        Set<Solution> result = solutionServiceMock.getSolutionsGivenProblemId(1);

        assertEquals(solutionSet, result);
    }

    @Test
    void getSolutionsGivenProblemId_Failure_ReturnsEmpty() throws SQLException {
        when(solutionDAOMock.findSolutionsGivenProblemId(1)).thenReturn(solutionEntitySet); //empty set

        Set<Solution> result = solutionServiceMock.getSolutionsGivenProblemId(1);

        assertEquals(solutionSet, result);
    }
}
