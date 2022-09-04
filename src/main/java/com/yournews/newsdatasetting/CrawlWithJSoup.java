package com.yournews.newsdatasetting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrawlWithJSoup {

    private String url;

    public CrawlWithJSoup(){
        url = "https://star.mbn.co.kr/view.php?year=2022&no=781556";
    }

    public void activateBot() {
        try {
            // 웹 document get
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .get();
            doc.outputSettings().prettyPrint(false);

            // dom select
            Elements contents = doc.select("#article");

            // 내부 데이터 파싱
//            String text = contents.text();

            //text 구조 유지하도록 wholeText 사용 Jsoup v1.11.2부터 추가
            String text = contents.first().wholeText();;

            System.out.println(text);


            // 테스트 파일 작성 -> 추후에는 JSON으로 가공
            File file = new File("D:\\IT\\test\\writeFile_jsoup.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            //테스트단계이므로 쓰기 효율 고려하지 않음.
            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);

            writer.write(text);

            writer.println("--------------------------------------------------------------------------");
            writer.println("--------------------------------------------------------------------------");
            writer.println("--------------------------------------------------------------------------");


            contents = doc.select(".center_image img");

            //이미지 url get
            text = contents.attr("src");
            System.out.println(text);

            writer.println();
            writer.println(text);
            writer.println("안녕하세요");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CrawlWithJSoup bot1 = new CrawlWithJSoup();
        bot1.activateBot();
    }
}
