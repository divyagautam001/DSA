/*

DFS O(n)
Define a class Employee with properties salary, left and right.
The left and right properties represent the left and right subordinates of an employee in the binary tree.
Create a recursive function isManagerSalaryHigher that takes an Employee object as input.
In this function, first check if the employee is a leaf node (i.e., has no subordinates).
If so, return true since this employee is not a manager.
Calculate the total salary of the subordinates by recursively calling isManagerSalaryHigher for the left and right subordinates 
    if they exist, and summing their salaries.
If the employee’s salary is higher than the total salary of their subordinates, return true. 
Otherwise, return false.
*/

class Employee {
    int salary;
    Employee left;
    Employee right;

    // constructor
    Employee(int salary) {
        this.salary = salary;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    public static boolean isManagerSalaryHigher(Employee employee) {
        if (employee.left == null && employee.right == null) {
            return true; // This employee is not a manager
        }

        int totalSubordinateSalary = 0;
        if (employee.left != null) {
            totalSubordinateSalary += employee.left.salary;
            if (!isManagerSalaryHigher(employee.left)) {
                return false;
            }
        }
        if (employee.right != null) {
            totalSubordinateSalary += employee.right.salary;
            if (!isManagerSalaryHigher(employee.right)) {
                return false;
            }
        }

        return employee.salary > totalSubordinateSalary;
    }

    public static void main(String[] args) {
        // create employees and hierarchy
        Employee emp1 = new Employee(85000);
        Employee emp2 = new Employee(90000);
        Employee emp3 = new Employee(170000);
        Employee emp4 = new Employee(60000);
        Employee emp5 = new Employee(70000);
        Employee emp6 = new Employee(50000);

        emp1.left = emp2;
        emp1.right = emp5;
        emp2.left = emp3;
        emp2.right = emp4;
        emp5.left = emp6;

        // check if every manager's salary is higher than the sum of their employees' salaries
        boolean result = isManagerSalaryHigher(emp1);

        System.out.println("Is every manager's salary higher than the sum of their employees' salaries? " + result);
    }
}
