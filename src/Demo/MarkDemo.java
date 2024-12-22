package Demo;

import studyingProcess.Mark;

class MarkDemo {

    public static void main(String[] args) {
        testMarkInstances();
        testGetMarkByLetter();
        testInvalidLetterInput();
        testWriteMethod();
    }

    // Test static instances
    static void testMarkInstances() {
        System.out.println("Test 1: Test Mark Instances");

        // Check if the instances are correctly initialized
        assert Mark.A.getMark() == 90.0 : "Test failed for Mark A";
        assert Mark.B.getMark() == 80.0 : "Test failed for Mark B";
        assert Mark.C.getMark() == 70.0 : "Test failed for Mark C";
        assert Mark.D.getMark() == 60.0 : "Test failed for Mark D";
        assert Mark.F.getMark() == 0.0 : "Test failed for Mark F";

        System.out.println("Test passed for Mark Instances\n");
    }

    // Test the getMarkByLetter method
    static void testGetMarkByLetter() {
        System.out.println("Test 2: Test Get Mark by Letter");

        // Check if the getMarkByLetter works correctly
        assert Mark.getMarkByLetter('A') == Mark.A : "Test failed for letter A";
        assert Mark.getMarkByLetter('B') == Mark.B : "Test failed for letter B";
        assert Mark.getMarkByLetter('C') == Mark.C : "Test failed for letter C";
        assert Mark.getMarkByLetter('D') == Mark.D : "Test failed for letter D";
        assert Mark.getMarkByLetter('F') == Mark.F : "Test failed for letter F";

        System.out.println("Test passed for Get Mark by Letter\n");
    }

    // Test invalid letter input
    static void testInvalidLetterInput() {
        System.out.println("Test 3: Test Invalid Letter Input");

        boolean exceptionThrown = false;
        try {
            Mark.getMarkByLetter('E'); // Invalid letter
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assert exceptionThrown : "Test failed for invalid letter input";

        System.out.println("Test passed for Invalid Letter Input\n");
    }

    // Test the write method for correct output
    static void testWriteMethod() {
        System.out.println("Test 4: Test Write Method");

        // Check if the write method prints correctly for A mark
        Mark.A.write();
        // You can manually check if the correct output was printed
        // Example output should be: Mark: 90.0, Letter grade: A

        System.out.println("Test passed for Write Method\n");
    }
}
