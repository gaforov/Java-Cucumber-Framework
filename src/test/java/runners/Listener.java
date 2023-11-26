package runners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.Date;

public class Listener extends RunListener {

    @Override
    public void testRunStarted(Description description) {
        Date date = new Date();
        System.out.println("Test Run started on: " + date);
    }

    @Override
    public void testRunFinished(Result result) {
        Date date = new Date();
        System.out.println("Test Run ended on: " + date);
        System.out.println("\n*********************************************" +
                           "\nEnd of All Test Runs. " +
                           "\nTotal Test Cases Executed: " + result.getRunCount() +
                           "\nTotal Test Cases Failed: " + result.getFailureCount() +
                           "\nTotal Test Cases Ignored: " + result.getIgnoreCount() +
                           "\n*********************************************");
    }

    @Override
    public void testSuiteStarted(Description description) {
        System.out.println("Test Suite Started: " + description.getDisplayName());
    }

    @Override
    public void testSuiteFinished(Description description) {
        System.out.println("\nTest Suite Finished: " + description.getDisplayName());
    }

    @Override
    public void testStarted(Description description) {
        System.out.println("\nExecution Started: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) {
        System.out.println("\nExecution Finished: " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) {
        System.out.println("\nExecution Failed: " + failure.getException());
        System.out.println("\nFailure message: " + failure.getMessage() +
                "\nFailure description: " + failure.getDescription());

    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        System.out.println("Assumption Failure: " + failure.getMessage());
    }

    @Override
    public void testIgnored(Description description) {
        System.out.println("Execution Ignored: " + description.getMethodName());
    }
}
