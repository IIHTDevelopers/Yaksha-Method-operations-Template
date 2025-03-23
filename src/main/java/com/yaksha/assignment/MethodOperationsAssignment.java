package com.yaksha.assignment;

class Calculator { // Task 1: Create a Class Method
	// Method to display a welcome message
	public String welcomeMessage() {
		return "Welcome to the Calculator!";
	}

	// Task 2: Method with Parameters
	// Method to add two numbers
	public int addNumbers(int a, int b) {
		return a + b;
	}

	// Task 3: Method with Return Value
	// Method to multiply two numbers
	public int multiplyNumbers(int a, int b) {
		return a * b;
	}
}

public class MethodOperationsAssignment {
	public static void main(String[] args) {
		// Task 1: Create a Class Method
		Calculator calculator = new Calculator(); // Creating an object of Calculator class
		String message = calculator.welcomeMessage(); // Calling the method
		System.out.println(message); // Printing the welcome message

		// Task 2: Method with Parameters
		int sum = calculator.addNumbers(10, 20); // Passing parameters to addNumbers method
		System.out.println("Sum: " + sum); // Printing the sum

		// Task 3: Method with Return Value
		int product = calculator.multiplyNumbers(5, 6); // Passing parameters to multiplyNumbers method
		System.out.println("Product: " + product); // Printing the product
	}
}
