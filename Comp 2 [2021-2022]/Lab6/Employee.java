
public class Employee extends Person {
    private double payRate;
    private double hoursWorked;
    private String department;

    public final int HOURS = 40;
    public final double OVERTIME = 1.5;

    public Employee() {
        super();
        payRate = 15;
        hoursWorked = 0;
        department = "General";

    }

    public Employee(String f, String l, double pR, double hW, String d) {
        super(f, l);
        payRate = pR;
        hoursWorked = hW;
        department = d;
    }

    public String toString() {
        return ("The wages for " + lastName + ", " + firstName + " from the " + department + " department are: $"
                + String.format("% .2f", calculatePay()));
    }

    public void print() {
        System.out.print("The employee, " + firstName + " " + lastName + " from the " + department
                + " department, worked for " + hoursWorked + " hours with a pay rate of " + payRate
                + ". Their wages are: $" + String.format("% .2f", calculatePay()));
    }

    public double calculatePay() {
        if (hoursWorked > HOURS) {
            double pay = HOURS * payRate;
            double overtime = (hoursWorked - HOURS);

            payRate = (payRate * OVERTIME);
            pay += (overtime * payRate);

            return pay;
        } else {
            double pay = hoursWorked * payRate;

            return pay;
        }
    }

    public void setAll(String f, String l, double pR, double hW, String d) {
        super.setName(f, l);
        payRate = pR;
        hoursWorked = hW;
        department = d;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public String getDepartment() {
        return department;
    }

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public void copy(Employee e) {
        super.copy(e);
        payRate = e.payRate;
        hoursWorked = e.hoursWorked;
        department = e.department;
    }

    public Employee getCopy() {
        return new Employee(firstName, lastName, payRate, hoursWorked, department);
    }

}
