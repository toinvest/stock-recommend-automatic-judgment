package edu.mask.collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CatchData {

    private static final String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600071.phtml";

    public static void main(String[] args) throws Exception{
        Document document = Jsoup.connect(url).get();
        Element element = document.getElementById("FundHoldSharesTable");
        System.out.println(element.child(1).child(1));
    }

}
