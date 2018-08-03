package edu.mask.collection.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.util.Arrays;
import java.util.List;

public final class CSVUtil {

    private CSVUtil csvUtil;

    public static List<String> readHead(String path) throws Exception {
        CsvReader csvReader = new CsvReader(path);
        csvReader.readHeaders();
        List<String> list = Arrays.asList(csvReader.getHeaders());
        return list;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(CSVUtil.readHead(System.getProperty("user.dir") + "/data/2018-08-02.csv"));
        //[600359, 603988, 002400, 000301, 601518, 600653, 002761, 600070, 002659]
        CSVUtil.writerValue(System.getProperty("user.dir") + "/data/2018-08-02.csv", new String[]{"600359", "603988", "002400", "000301", "601518", "600653", "002761", "600070", "002659"});
    }

    public static void writerValue(String path, String[] contents) throws Exception {
        CsvWriter csvWriter = new CsvWriter(path);
        csvWriter.writeRecord(contents);
        csvWriter.writeRecord(contents);
        csvWriter.close();
    }

}
