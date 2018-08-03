package edu.mask.collection.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.MessageFormat;

public final class StockDataUtil {

    private StockDataUtil() {
    }

    private static final String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/{0}.phtml";

    public static String catchData(String code, int dateIndex) throws Exception {
        Document document = Jsoup.connect(MessageFormat.format(url, code)).get();
        Element element = document.getElementById("FundHoldSharesTable");
        return element.child(1).child(dateIndex).child(3).getElementsByAttribute("align").html();
    }

}
