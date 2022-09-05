package com.yournews.newsdatasetting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrawlWithJSoup2 {

    private String url;

    public CrawlWithJSoup2(){
        url = "https://www.bigkinds.or.kr/v2/news/index.do";
    }

    public void activateBot() {
        try {
            // 웹 document get
            Document doc = Jsoup.connect(url)
//                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .get();
//            doc.outputSettings().prettyPrint(false);

            // dom select
            // title
            Elements contents = doc.select("body");

            //제목 get
            String text = contents.text();;

            // 테스트 파일 작성 -> 추후에는 JSON으로 가공
            File file = new File("D:\\IT\\test\\writeFile_jsoup2.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            //테스트단계이므로 쓰기 효율 고려하지 않음.
            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);

            writer.write(text);

            writer.println(text);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CrawlWithJSoup2 bot1 = new CrawlWithJSoup2();
        bot1.activateBot();
    }
}
