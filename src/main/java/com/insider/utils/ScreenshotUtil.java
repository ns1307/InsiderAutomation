package com.insider.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
        String fileName = "screenshots_test_fails/" + testName + "_" + timestamp + ".png";

        try {
            Files.createDirectories(Paths.get("screenshots_test_fails"));
            Files.copy(screenshot.toPath(), Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
