package com.yournews.newsdatasetting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CrawlWithSelenium {

    private WebDriver driver;
    private WebElement element;
    private String url;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "D:\\IT\\Project\\NewsCrawling\\NewsDataSetting\\chromedriver.exe";

    public CrawlWithSelenium(){
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        url = "https://star.mbn.co.kr/view.php?year=2022&no=781556";
    }

    public void activateBot() {
        try {
            System.out.println("시작");
            driver.get(url);
            System.out.println("---------------------------------");
            System.out.println("2초 대기");
            Thread.sleep(2000);

            // 제목 파트 크롤
            element = driver.findElement(By.xpath("//*[@id=\"article\"]/h2"));
            System.out.println(element.getText());;

            // 테스트 파일 작성 -> 추후에는 JSON으로 가공
            File file = new File("D:\\IT\\test\\writeFile.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);

            writer.write(element.getText());


//            Thread.sleep(1000);

            // 기사 전문 크롤
            element = driver.findElement(By.xpath("//*[@id=\"artText\"]"));
            String text = element.getText();
            System.out.println(text);


            writer.println();
            writer.write(element.getText());

            writer.close();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 브라우저 종료
            System.out.println("브라우저 종료");
        }
    }

    public static void main(String[] args) {
        CrawlWithSelenium bot1 = new CrawlWithSelenium();
        bot1.activateBot();
    }
}
