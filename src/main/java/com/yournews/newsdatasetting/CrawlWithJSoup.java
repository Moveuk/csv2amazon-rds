package com.yournews.newsdatasetting;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class CrawlWithJSoup {

    private String url;

    public CrawlWithJSoup() {
        url = "https://www.joongang.co.kr/article/25099580#home";
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
            Elements contents = doc.select("#container > section > article > header > h1");

            //제목 get
            String text = contents.text();

            // json 전처리
            JSONObject obj = new JSONObject();
            obj.put("title", text);

            //중앙일보
            contents = doc.select("#container > section > article > header > div.datetime > span");
            obj.put("press", contents.text());

            //기고 시간
            contents = doc.select("#container > section > article > header > div.datetime > div > p");
            obj.put("date", contents.text().replace("입력","").trim());

            //기사 전문
            contents = doc.select("#article_body");
            //text 구조 유지하도록 wholeText 사용 Jsoup v1.11.2부터 추가
            obj.put("contents", contents.first().wholeText());

            //이미지 url get
            contents = doc.select("#article_body > div:nth-child(4) > div > img");
            obj.put("imageUrl", contents.attr("src"));


            // JSON파일 저장
            try {
                FileWriter file = new FileWriter("D:\\IT\\test\\test.json");
                file.write(obj.toJSONString());
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            if (!file.exists()) {
//                file.createNewFile();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CrawlWithJSoup bot1 = new CrawlWithJSoup();
        bot1.activateBot();
    }
}
