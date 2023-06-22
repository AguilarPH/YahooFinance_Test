package org.example;

import org.openqa.selenium.WebDriver;
import steps.BaseSteps;
import tests.BaseTest;

public class Main {
    public static void main(String[] args) {
        WebDriver driver;

        BaseTest baseTest = new BaseTest();
        BaseSteps baseSteps = new BaseSteps();

        baseSteps.getHtmlPage();

    }
}