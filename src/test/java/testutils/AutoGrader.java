package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class AutoGrader {

	// Test if the code defines class methods, accepts parameters, and returns
	// values correctly
	public boolean testMethodOperations(String filePath) throws IOException {
		System.out.println("Starting testMethodOperations with file: " + filePath);

		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		boolean hasMethodOperations = false;

		// Check for class creation (Calculator class)
		System.out.println("------ Class Creation ------");
		boolean calculatorClassFound = false;
		for (TypeDeclaration<?> typeDecl : cu.findAll(TypeDeclaration.class)) {
			if (typeDecl.getNameAsString().equals("Calculator")) {
				System.out.println("Class 'Calculator' found: " + typeDecl.getName());
				hasMethodOperations = true;
				calculatorClassFound = true;
			}
		}

		if (!calculatorClassFound) {
			System.out.println("Error: 'Calculator' class not found.");
			return false; // Early exit if class creation is missing
		}

		// Check for method invocations (welcomeMessage, addNumbers, multiplyNumbers)
		System.out.println("------ Method Invocation ------");
		boolean welcomeMessageCalled = false;
		boolean addNumbersCalled = false;
		boolean multiplyNumbersCalled = false;

		// Look for all MethodCallExpr expressions
		for (MethodCallExpr methodCall : cu.findAll(MethodCallExpr.class)) {
			System.out.println("Found method call: " + methodCall.getNameAsString()); // Log method name

			if (methodCall.getNameAsString().trim().equals("welcomeMessage")) {
				System.out.println("Method 'welcomeMessage' called: " + methodCall);
				hasMethodOperations = true;
				welcomeMessageCalled = true;
			}
			if (methodCall.getNameAsString().trim().equals("addNumbers")) {
				System.out.println("Method 'addNumbers' called: " + methodCall);
				addNumbersCalled = true;
			}
			if (methodCall.getNameAsString().trim().equals("multiplyNumbers")) {
				System.out.println("Method 'multiplyNumbers' called: " + methodCall);
				multiplyNumbersCalled = true;
			}
		}

		if (!welcomeMessageCalled) {
			System.out.println("Error: 'welcomeMessage' method is not called.");
			return false; // Early exit if method invocation is missing
		}
		if (!addNumbersCalled) {
			System.out.println("Error: 'addNumbers' method is not called.");
			return false;
		}
		if (!multiplyNumbersCalled) {
			System.out.println("Error: 'multiplyNumbers' method is not called.");
			return false;
		}

		// If all operations were found, return true
		System.out.println("Test passed: All required class, method, and invocation operations were found.");
		return true;
	}
}
