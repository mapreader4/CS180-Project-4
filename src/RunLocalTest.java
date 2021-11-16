import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Purdue CS
 * @version August 23, 2021
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Fall 2021</p>
     *
     * @author Purdue CS
     * @version August 23, 2021
     */
    public static class TestCase {
        private final InputStream originalInput = System.in;
        private final PrintStream originalOutput = System.out;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;
        private final String TA_ERROR_MESSAGE = "You bumped into an error! Please contact a TA immediately.";
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @Test(timeout = 1000)
        public void testMenuExit() {
            String input = "3\n ";
            receiveInput(input);
            try {
                Menu.main(new String[0]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("There was an error Starting your program!");
            }
            String out = getOutput().replaceAll("\r","");
            String expected = "Welcome to the Online Quiz Navigator!\n" +
                    "Would you like to log in or create an account?\n" +
                    "1. Log in\n" +
                    "2. Create account\n" +
                    "3. Quit\n"+"Thank you for using the Online Quiz Navigator!\n";
            assertEquals("There was an assertion equals error ",expected,out);
        }

        /**
         * UTILITY METHODS BELOW
         */

        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }
    }
}

