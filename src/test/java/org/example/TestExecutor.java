package org.example;

import org.testng.annotations.Test;

import methodCaller.MethodCaller;
import utils.ExtentTestListener;

public class TestExecutor extends AppTest{
    ExtentTestListener extentTestListener = new ExtentTestListener();

    @Test
    public void example2s() throws InterruptedException {
        System.out.println(
                System.currentTimeMillis() + " : Thread 1 STARTED"
        );
        MethodCaller.methodCallerMain(page.get());
        Thread.sleep(5000);
        System.out.println(
                System.currentTimeMillis() + " : Thread 1 FINISHED"
        );
    }

    @Test
    public void example1() throws InterruptedException {
        System.out.println(
                System.currentTimeMillis() + " : Thread 2 STARTED"
        );
        Thread.sleep(5000);
        System.out.println(
                System.currentTimeMillis() + " : Thread 2 FINISHED"
        );
    }
}
