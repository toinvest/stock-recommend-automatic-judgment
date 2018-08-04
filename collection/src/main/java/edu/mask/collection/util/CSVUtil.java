package edu.mask.collection.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.util.Collection;
import java.util.List;

public final class CSVUtil {

    private CSVUtil() {
    }

    private CsvReader csvReader = null;

    private CsvWriter csvWriter = null;

    public static CSVUtil getInstance(String path) {
        try {
            CSVUtil util = new CSVUtil();
            util.csvReader = new CsvReader(path);
            util.csvWriter = new CsvWriter(path);
            return util;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] readHeader() {
        try {
            csvReader.readHeaders();
            return csvReader.getHeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writerValue(String[] contents) {
        try {
            csvWriter.writeRecord(contents);
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writerValue(Collection<List<String>> contents) {
        try {
            for (List<String> list : contents) {
                csvWriter.writeRecord(list.toArray(new String[0]));
            }
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
