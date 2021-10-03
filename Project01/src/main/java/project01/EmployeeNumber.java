package project01;

public class EmployeeNumber {
	private int emp_num;
	
	public EmployeeNumber() {}
	
	public EmployeeNumber(int emp_num) {
		this.setEmp_num(emp_num);
	}

	public int getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}
}
