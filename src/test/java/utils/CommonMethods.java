package utils;

import base.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static base.BaseClass.*;

/**
 * Note: This is our BANK, for reusing the methods elsewhere in the framework when we need it.
 *   We store all common methods for usability here. This will help us to avoid DRY principle of programming/coding.
 */


public class CommonMethods extends PageInitializer {
    /**
     * Method will switch focus to next window/tab based on the window title/name
     * @param windowTitle String
     */
    public static void switchToWindow(String windowTitle) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowOrTab : windows) {
            String title = driver.switchTo().window(windowOrTab).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Window is found! Page Title: " + driver.getTitle() + " URL: " + driver.getCurrentUrl());
                break;
            }
        }
    }

    /**
     * This method will clear and then send value to input field(s).
     *
     * @param element WebElement
     * @param value   String
     */
    public static void sendText(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Simple click method
     *
     * @param element WebElement
     */
    public static void click(WebElement element) {
        element.click();
    }

    public static WebDriverWait waitForElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        waitForElement().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method will click but first waits for the clickability of the element
     *
     * @param element WebElement
     */
    public static void clickButWaitForClickability(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static void waitForVisibility(WebElement element) {
        waitForElement().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Method will wait for the given element based on the visibility of Element
     *
     * @param by By locator
     */
    public static void waitForVisibilityOfElement(By by) {
        waitForElement().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForPresenceOfElement(By by) {
        waitForElement().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void clickButWaitForVisibility(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    /**
     * Method selects radio button or checkbox is enabled and is not already selected, then clicks on it.
     *
     * @param radioOrCheckbox List of WebElements for dropdown or radio buttons
     * @param expectedValue   String
     */
    public static void clickRadioOrCheckbox(List<WebElement> radioOrCheckbox, String expectedValue) {
        for (WebElement element : radioOrCheckbox) {
            String actualValue = element.getAttribute("value");
            if (element.isEnabled() && !element.isSelected() && actualValue.equals(expectedValue)) {
                element.click();
                break;
            }
        }
    }

    public static void clickRadioOrCheckbox(WebElement element) {
        if (element.isEnabled() && !element.isSelected()) {
            element.click();
        }
    }


    /**
     * Method selects from dropdown or multi-select by text value.
     *
     * @param dropdownList  list of WebElement
     * @param expectedValue String
     */
    public static void selectDdValue(List<WebElement> dropdownList, String expectedValue) {
        for (WebElement element : dropdownList) {
            String actualValue = element.getText();
            if (actualValue.equals(expectedValue)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Method selects from a dropdown by visible text.
     *
     * @param element       WebElement
     * @param expectedValue String
     */
    public static void selectDdValue(WebElement element, String expectedValue) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(expectedValue)) {
                select.selectByVisibleText(expectedValue);
                break;
            }
        }
    }

    /**
     * Method will select a dropdown or multi-select by index.
     *
     * @param element WebElement
     * @param index   int
     */
    public static void selectDdValue(WebElement element, int index) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        if (index < options.size()) {
            select.selectByIndex(index);
        } else {
            try {
                throw new IndexOutOfBoundsException(index);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("Incorrect Index used. Please use a number between 1 and " + options.size());
            }
        }
    }


    public static void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            System.out.println("Alert is not present.");
        }
    }

    public static void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public static void sendAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public static String getAlertText() {
        String alertText = null;
        try {
            alertText = driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
        return alertText;
    }


    /**
     * Method will wait for an element, but this is a static wait, please use with caution.
     *
     * @param second int
     */
    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method will scroll down to paragraph(s) given by number of paragraph(s) as index and locator of <p> as second argument
     *
     * @param index number of paragraph(s) to scroll down to. How many paragraphs you want to scroll to, enter it here.
     * @param by    web element locator of paragraph goes here.
     */
    public static void scrollToParagraph(int index, By by) {
        String script = "window.scrollTo(0, document.body.scrollHeight)";
        while (getNumberOfParagraphs(by) < index) {
            jsExecutor().executeScript(script); // scroll down by one <p>
        }
        System.out.println("Total paragraphs: " + getNumberOfParagraphs(by));
    }

    public static int getNumberOfParagraphs(By by) {
        //List<WebElement> paragraphs = driver.findElements(By.className("jscroll-added"));
        return driver.findElements(by).size();
    }

    public static JavascriptExecutor jsExecutor() {
        return (JavascriptExecutor) driver;
    }

    /**
     * Method performs simple click based on Javascript. Use this if regular Selenium click fails.
     *
     * @param element WebElement that needs to be clicked on.
     */
    public static void jsClick(WebElement element) {
        jsExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * Method will scroll to the given element
     *
     * @param element WebElement to get scrolled to
     */
    public static void scrollToElement(WebElement element) {
        jsExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Method will scroll both vertically (left & right) and horizontally (up & down) based on given pixels.
     *
     * @param horizontalPixel int
     * @param verticalPixel   int
     */
    public static void scrollToElement(int horizontalPixel, int verticalPixel) {
        jsExecutor().executeScript("window.scrollBy(" + horizontalPixel + "," + verticalPixel + ")");
    }

    /**
     * Method will scroll down in accordance with the passed pixel as parameter
     *
     * @param pixel int
     */
    public static void scrollDown(int pixel) {
        jsExecutor().executeScript("window.scrollBy(0," + pixel + ")");  // "window.scrollBy(0,500)"
    }

    /**
     * Method will scroll up based on given pixel
     *
     * @param pixel int
     */
    public static void scrollUp(int pixel) {
        jsExecutor().executeScript("window.scrollBy(0,-" + pixel + ")"); // "window.scrollBy(0,-500)"
    }

    /**
     * This method will take a screenshot using older version of Selenium (v3). Extension defined as .png (You can change to .jpeg from CommonMethods when needed)
     * @param fileName String as screenshot name
     */
    public static String takeScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File("screenshots/" + fileName + "_" + getTimeStamp() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Screenshot is not taken");
        }
        return fileName;
    }

    /**
     * This method will take a screenshot based on the given web element
     * @param element which its screenshot needs to be taken
     * @param fileName screenshot file name, Defaulted to .png format (can be changed to .jpeg within the method if needed).
     * @return .png file as a String
     */
    public static String takeScreenshot(WebElement element, String fileName) {
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File("screenshots/" + fileName + "_" + getTimeStamp() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static String randomStrongPassWord() {
        String passWord = "";
        Random rnd = new Random();
        String lowerLetter = "abcdefghijklmnoprstuwxyz";
        String capitalLetters = "ABCDEFGHIJKLMNOPRSTUWXYZ";
        String specialChar = "!#$%&()*+,-.:;<=>?@[]^_{|}~";
        while (passWord.length() < 12) {
            passWord += lowerLetter.charAt(rnd.nextInt(lowerLetter.length()));
            passWord += capitalLetters.charAt(rnd.nextInt(capitalLetters.length()));
            passWord += specialChar.charAt(rnd.nextInt(specialChar.length()));
            passWord += rnd.nextInt(10);
        }
        return passWord;
    }

    public static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SS"); // YYYY-MM-DD_Hour_Min_Sec_Milliseconds
        return simpleDateFormat.format(date.getTime());
    }
}
