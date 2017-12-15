package com.fengye.net.crawler.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2017/11/28.
 */
public class FileTest {
    public static final Logger logger = LoggerFactory.getLogger(FileTest.class);

    public void testReader() throws Exception{
        ArrayList<String> data = new ArrayList<>();
        File file = new File("E:\\json1.txt");
        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = "";
        while((s = bufferedReader.readLine()) != null){
            data.add(s);
            logger.info(s);
        }

        bufferedReader.close();
        fileReader.close();
    }

    public void testWriter() throws Exception{
        File file1 = new File("E:\\json2.txt");
        FileOutputStream fos = new FileOutputStream(file1);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter writer = new BufferedWriter(osw);

        for (int i = 0; i < 10; i++) {
            System.out.println("key" + i + "=" + "value" + i + "\r\n");
            writer.append("key" + i + "=" + "value" + i + "\r\n");
        }
        writer.close();
    }

    public void testQueryRunner() throws Exception{
        
    }

    public static void main(String[] args) {
        FileTest fileTest = new FileTest();
        try {
            fileTest.testReader();
            fileTest.testWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
