package edu.mask.collection.task;

import edu.mask.collection.util.CSVUtil;
import edu.mask.collection.util.DateUtil;
import edu.mask.collection.util.StockDataUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.util.*;

public class RenewStockData {
    //date\code,600359,603988,002400,000301,601518,600653,002761,600070,002659
    private static final int DAY_COUNT = 20;

    private static final String outHeader = "date\\code";

    private static final String gaFlag = ".GA";

    public static File[] findOriginalFile() {
        String dirPath = System.getProperty("user.dir") + "/data";
        File file = new File(dirPath);
        return file.listFiles();
    }

    public static void init(File file) {
        boolean isGA = true;
        String name = file.getName().split("\\.")[0];
        int diff = DateUtil.diffDate(new Date(), DateUtil.resolve(name));
        Map<String, List<String>> map = new LinkedHashMap<>();
        List<String> dateList = new LinkedList<>();
        CSVUtil csvUtil = CSVUtil.getInstance(file.getPath());
        String[] headers = csvUtil.readHeader();
        for (int i = 0; i <= diff; i++) {
            for (String code : headers) {
                String date = DateUtil.getDay(i);
                if (!dateList.contains(code)) {
                    dateList.add(code);
                }
                if (!Objects.equals(outHeader, code)) {
                    String data = StockDataUtil.catchData(code, date);
                    if (null == data) {
                        dateList.remove(code);
                        break;
                    }
                    List<String> list = map.get(date);
                    if (null == list) {
                        list = new LinkedList<>();
                        map.put(date, list);
                    }
                    if (!list.contains(date)) {
                        list.add(date);
                    }
                    list.add(data);
                }
            }
        }
        Collection<List<String>> collection = map.values();
        if (collection.size() == DAY_COUNT) {
            isGA = true;
        }
        List<List<String>> l = new ArrayList<>();
        l.addAll(collection);
        l.add(0, dateList);
        System.out.println(l);
        csvUtil.writerValue(l);
        if (isGA) {
            String path = file.getPath().replace("data", "data_ga");
            copyfile(file.getPath(), path);
        }

    }

    public static void copyfile(String from, String to) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(from));
            BufferedWriter out = new BufferedWriter(new FileWriter(to));
            String line = null;
            while ((line = in.readLine()) != null) {
                out.write(line + "\n");
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        File[] fs = findOriginalFile();
        for (File f : fs) {
            if (f.getName().contains(gaFlag)) {
                continue;
            }
            init(f);
        }
    }

}
