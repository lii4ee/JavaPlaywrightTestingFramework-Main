package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExecutor extends AppTest{

    @Test
    public void example2s() throws InterruptedException {
        System.out.println(
                System.currentTimeMillis() + " : Thread 1 STARTED"
        );
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
