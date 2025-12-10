import org.example.repository.DAO.EmployeeDAO;
import org.example.repository.entities.DepartmentEntity;
import org.example.repository.entities.EmployeeEntity;
import org.example.repository.entities.LocationEntity;
import org.example.service.DepartmentService;
import org.example.service.EmployeeService;
import org.example.service.LocationService;
import org.example.service.model.Department;
import org.example.service.model.Employee;
import org.example.service.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private LocationService locationService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeService employeeService;


    private EmployeeEntity testEmployeeEntity;
    private Employee testEmployeeModel;
    private LocationEntity testLocationEntity;
    private Location testLocationModel;
    private DepartmentEntity testDepartmentEntity;
    private Department testDepartmentModel;

    @BeforeEach
    void setup(){
        // Setup Test EmployeeEntity
        testEmployeeEntity = new EmployeeEntity();
        testEmployeeEntity.setId(1);
        testEmployeeEntity.setFullName("John Doe");
        testEmployeeEntity.setLocationId(10);
        testEmployeeEntity.setDepartmentId(20);

        // Setup Test EmployeeModel
        testEmployeeModel = new Employee();
        testEmployeeModel.setId(1);
        testEmployeeModel.setFullName("John Doe");


        testLocationEntity = new LocationEntity();
        testLocationEntity.setId(10);
        testLocationEntity.setLocation("New York");

        testLocationModel = new Location();
        testLocationModel.setId(10);
        testLocationModel.setLocation("New York");


        testDepartmentEntity = new DepartmentEntity();
        testDepartmentEntity.setId(20);
        testDepartmentEntity.setDepartment("Engineering");

        testDepartmentModel = new Department();
        testDepartmentModel.setId(20);
        testDepartmentModel.setDepartment("Engineering");


    }

//    @Override
//    public Integer createEntity(EmployeeEntity entity) {
//        try{
//            Integer newId = employeeDAO.create(entity);
//            return newId;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return -1;
//        }
//    }

    @Test
    void createEntity_Success_ReturnsNewId() throws SQLException {
        // AAA
        // Arrange
        // We have to prepare the test for the actual scenario we want to test for
        when(employeeDAO.create(testEmployeeEntity)).thenReturn(100);

        // Act
        // We use the method as we have mocked
        Integer result = employeeService.createEntity(testEmployeeEntity);


        // Assert
        // We verify the result of the method call
        assertEquals(100, result);
        // We also verify the behavior of the service function by how it calls its mocks
        verify(employeeDAO, times(1)).create(testEmployeeEntity);
    }

    @Test
    void createEntity_ThrowsSQLException_ReturnsNegativeOne() throws SQLException{
        // Arrange
        when(employeeDAO.create(testEmployeeEntity)).thenThrow(new SQLException("Database error"));

        // Act
        Integer result = employeeService.createEntity(testEmployeeEntity);

        // Assert
        assertEquals(-1, result);
        verify(employeeDAO, times(1)).create(testEmployeeEntity);
    }

//    @Override
//    public Optional<Employee> convertEntityToModel(EmployeeEntity entity) {
//        try{
//            Optional<Location> location = locationService.getModelById(entity.getLocationId());
//
//            if(location.isEmpty()){
//                throw new RuntimeException("Invalid location id");
//            }
//
//            Optional<Department> department = departmentService.getModelById(entity.getDepartmentId());
//
//            if(department.isEmpty()){
//                throw new RuntimeException("Invalid department id");
//            }
//
//            Employee employee = new Employee();
//            employee.setId(entity.getId());
//            employee.setFullName(entity.getFullName());
//            employee.setLocation(location.get());
//            employee.setDepartment(department.get());
//
//            return Optional.of(employee);
//
//        }catch(RuntimeException e){
//            e.printStackTrace();
//            return Optional.empty();
//        }
//    }


    @Test
    void convertEntityToModel_Success_ReturnsEmployeeModel() {
        // Arrange
        when(locationService.getModelById(testEmployeeEntity.getLocationId())).thenReturn(Optional.of(testLocationModel));
        when(departmentService.getModelById(testEmployeeEntity.getDepartmentId())).thenReturn(Optional.of(testDepartmentModel));

        // Act
        Optional<Employee> result = employeeService.convertEntityToModel(testEmployeeEntity);

        // Assert
        assertTrue(result.isPresent());
        Employee employee = result.get();
        assertEquals("John Doe", employee.getFullName());
        assertEquals(testDepartmentModel, employee.getDepartment());
        assertEquals(testLocationModel, employee.getLocation());

        verify(locationService, times(1)).getModelById(10);
        verify(departmentService, times(1)).getModelById(20);
    }

    @Test
    void convertEntityToModel_LocationNotFound_ReturnsEmpty() {
        // Arrange
        when(locationService.getModelById(testEmployeeEntity.getLocationId())).thenReturn(Optional.empty());

        // Act
        Optional<Employee> result = employeeService.convertEntityToModel(testEmployeeEntity);

        // Assert
        assertFalse(result.isPresent());
        verify(locationService, times(1)).getModelById(10);
        verify(departmentService, never()).getModelById(20);
    }


}
