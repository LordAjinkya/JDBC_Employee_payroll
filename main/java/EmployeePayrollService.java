import java.util.List;

public class EmployeePayrollService {
    public enum IOService{DB_IO,REST_IO}

    private List<EmployeePayrollData> employeePayrollList;
    private EmployeePayrollDBService employeePayrollDBService;

    public EmployeePayrollService(){
        employeePayrollDBService = EmployeePayrollDBService.getInstance();

    }
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this();
        this.employeePayrollList=employeePayrollList;
    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService){
        if(ioService.equals( IOService.DB_IO ))
            this.employeePayrollList = new EmployeePayrollDBService().readData();
        return this.employeePayrollList;
    }
    public void updateEmployeeSalary(String name, double salary) {
        //EmployeePayrollDBService employeePayrollDBService = EmployeePayrollDBService.getInstance();
        int result = employeePayrollDBService.updateEmployeeData(name,salary);
        if(result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if(employeePayrollData!=null)
            employeePayrollData.salary = salary;
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        EmployeePayrollData employeePayrollData;
        employeePayrollData = this.employeePayrollList.stream()
                .filter( employeepayrollDataItem -> employeepayrollDataItem.name.equals( name ))
                .findFirst().orElse( null );
        return employeePayrollData;
    }

    public boolean checkEmployeePayrollInsyncWithDB(String name) {
        //EmployeePayrollDBService employeePayrollDBService = EmployeePayrollDBService.getInstance();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }
}
