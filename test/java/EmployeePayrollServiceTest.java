import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;



public class EmployeePayrollServiceTest {

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData( EmployeePayrollService.IOService.DB_IO);
        Assertions.assertEquals(3,employeePayrollData.size());
    }
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDataBase() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData( EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalary("Terissa",3000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInsyncWithDB("Terissa");
        Assertions.assertTrue( result );
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData( EmployeePayrollService.IOService.DB_IO);
        LocalDate startDate = LocalDate.of( 2018,01,01 );
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollForDateRange(EmployeePayrollService.IOService.DB_IO,startDate,endDate );
        Assertions.assertEquals( 4,employeePayrollData.size() );
    }
}
