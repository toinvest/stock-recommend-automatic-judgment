package edu.mask.collection.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.Objects;

public final class StockDataUtil {

    private StockDataUtil() {
    }

    private static final String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/{0}.phtml";

    public static String catchData(String code, String date) {
        try {
            Document document = Jsoup.connect(MessageFormat.format(url, code)).get();
            Elements elements = document.getElementById("FundHoldSharesTable").child(1).children();
            for (Element e : elements) {
                if (Objects.equals(e.child(0).getElementsByTag("a").html(), date)) {
                    return e.child(3).getElementsByAttribute("align").html();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
