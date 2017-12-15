package com.fengye.net.crawler.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by fengye on 2017/11/28.
 */
public class TestJsoup {
    public void basicTest() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";

        Document document = Jsoup.parse(html);
        System.out.println(document);
        parseHtml1();
        parseHtml2();
    }

    public void parseHtml2(){
        String url = "http://www.baidu.com";

        try {
            Document document = Jsoup.connect(url).get();

            Elements ele = document.getElementsByTag("a");

            ele.forEach(e -> {
                String href = e.attr("href");
                String name = e.attr("name");
                String text = e.text();
                System.out.println("href=" + href + ", name=" + name + ", text=" + text);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseHtml1() {
//        String url = "http://www.tripadvisor.com/SearchForums?q=airbnb&x=18&y=10&pid=34633&s=+";
        String url = "http://www.baidu.com";
        try {
//            Document doc = Jsoup.connect(url).userAgent("bbb").timeout(50000).get();
            Document doc = Jsoup.connect(url).get();
//            Elements ele = doc.select("[id=form]");
            //所有input
            Elements ele = doc.select("input");
            System.out.println(ele.size());

            ele.forEach(e -> {
                String type = e.attr("type");
                String name = e.attr("name");
                String value = e.attr("value");
                System.out.println("type=" + type + ", name=" + name + ", value=" + value);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TestJsoup testJsoup = new TestJsoup();
        testJsoup.basicTest();
    }
}
