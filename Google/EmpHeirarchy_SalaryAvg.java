/*
Like in DFS, we iterate children of a parent node, similarly here
  we can see managers as parent node, employees as child nodes.
DFS uses Stack data structure.

Initialize a stack and push the root employee onto the stack.
While the stack is not empty, repeat the following steps:
Pop an employee from the stack and assign it to a variable current.
If current has any subordinates, calculate the total and average salary of the subordinates.
For each subordinate of current, push the subordinate onto the stack.
If the salary of current is less than the average salary of its subordinates, add current to underpaidManagers.
After the stack is empty, return underpaidManagers.

O(n) time & space complexity
*/

import java.util.*;

// Define the Employee class
class Employee {
    int employeeNumber; // Unique identifier for the employee
    int salary; // Salary of the employee
    List<Employee> subordinates; // List of subordinates of the employee

    // Constructor for the Employee class
    Employee(int employeeNumber, int salary) {
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.subordinates = new ArrayList<>();
    }
}

public class Main {
    // Function to find underpaid managers
    public static List<Employee> findUnderpaidManagers(Employee root) {
        List<Employee> underpaidManagers = new ArrayList<>(); // List to store underpaid managers
        Deque<Employee> stack = new ArrayDeque<>(); // Stack for DFS
        stack.push(root); // Push the root employee onto the stack

        // While the stack is not empty
        while (!stack.isEmpty()) {
            Employee current = stack.pop(); // Pop an employee from the stack

            // If the current employee has any subordinates
            if (!current.subordinates.isEmpty()) {
                int totalSubordinateSalary = 0; // Total salary of the subordinates

                // Calculate the total salary of the subordinates
                for (Employee subordinate : current.subordinates) {
                    totalSubordinateSalary += subordinate.salary;
                    stack.push(subordinate); // Push the subordinate onto the stack
                }

                // Calculate the average salary of the subordinates
                double averageSubordinateSalary = (double) totalSubordinateSalary / current.subordinates.size();

                // If the current employee's salary is less than the average salary of their subordinates
                if (current.salary < averageSubordinateSalary) {
                    underpaidManagers.add(current); // Add the current employee to the list of underpaid managers
                }
            }
        }

        return underpaidManagers; // Return the list of underpaid managers
    }

    public static void main(String[] args) {
        // Create employees and hierarchy
        Employee emp1 = new Employee(1, 85000);
        Employee emp2 = new Employee(2, 90000);
        Employee emp3 = new Employee(3, 170000);
        Employee emp4 = new Employee(4, 60000);
        Employee emp5 = new Employee(5, 70000);
        Employee emp6 = new Employee(6, 50000);

        // Define the hierarchy of employees
        emp2.subordinates.add(emp3);
        emp2.subordinates.add(emp4);
        emp5.subordinates.add(emp6);
        emp1.subordinates.add(emp2);
        emp1.subordinates.add(emp5);

        // Find underpaid managers
        List<Employee> underpaidManagers = findUnderpaidManagers(emp1);

        // Print underpaid managers
        for (Employee manager : underpaidManagers) {
            System.out.println("Manager with employee number " + manager.employeeNumber + " and salary " + manager.salary + " is underpaid.");
        }
    }
}
