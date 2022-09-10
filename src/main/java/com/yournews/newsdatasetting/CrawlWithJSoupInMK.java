package com.yournews.newsdatasetting;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrawlWithJSoupInMK {

    private String url;

    public CrawlWithJSoupInMK() {
        url = "https://www.mk.co.kr/news/world/view/2022/08/760000/";
    }

    public void activateBot() {
        try {
            // 웹 document get
            Document doc = Jsoup.connect(url)
//                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .get();
//            doc.outputSettings().prettyPrint(false);

            File csv = new File("D:\\IT\\test\\test.csv");

            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));


            // dom select
            // title
            Elements contents = doc.select("#top_header > div > div > h1");
            //제목 get
            String text = contents.text();

            // json 전처리
            JSONObject obj = new JSONObject();
            obj.put("title", text.trim());

            String aData = text + ",";

            //기자 이름
            contents = doc.select("#top_header > div > div > div.news_title_author > ul > li.author");
            obj.put("author", contents.text().trim());

            aData += contents.text() + ",";

            //중앙일보
            contents = doc.select("#container > section > article > header > div.datetime > span");
            obj.put("press", contents.text().trim());

            aData += contents.text() + ",";

            //기고 시간
            contents = doc.select("#top_header > div > div > div.news_title_author > ul > li.lasttime");
            obj.put("date", contents.text().replace("입력","").trim());

            aData += contents.text().replace("입력","").trim() + ",";

            //기사 전문
            contents = doc.select("#article_body");
            //text 구조 유지하도록 wholeText 사용 Jsoup v1.11.2부터 추가
            obj.put("contents", contents.first().wholeText().trim());

            aData += contents.first().wholeText() + ",";
//            System.out.println(contents.first().wholeText().trim());

            //이미지 url get
            contents = doc.select("#article_body > div:nth-child(4) > div > img");
            obj.put("imageUrl", contents.attr("src"));

            aData += contents.attr("src");


            // JSON파일 저장
            try {
                FileWriter file = new FileWriter("D:\\IT\\test\\test.json");
                file.write(obj.toJSONString());

                String x = obj.toJSONString().replace("{\"date\":\": ","").replace(" 수정 : 2022.08.29 07:58:47\"","").replace("\"contents\":\"","").replace("\",\"author\":\"",",").replace("\",\"imageUrl\":\"\",\"title\":\"",",").replace("\",\"press\":\"\"}","");

                bw.write(x);
                System.out.println(x);
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            bw.newLine();
            if (bw != null) {
                bw.flush(); // 남아있는 데이터까지 보내 준다
                bw.close(); // 사용한 BufferedWriter를 닫아 준다
            }
//            if (!file.exists()) {
//                file.createNewFile();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CrawlWithJSoupInMK bot1 = new CrawlWithJSoupInMK();
        bot1.activateBot();
    }
}
