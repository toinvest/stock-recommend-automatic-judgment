# stock-recommend-automatic-judgment

## TODO:

- [ ] 15 点 30 自动比对数据
- [ ] 添加 git 自动提交结果功能

## 数据来源

[股票历史记录](http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600071.phtml)

[当日成交明细](http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradedetail.php?symbol=sh600071)

```java
private static final String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600071.phtml";

public static void main(String[] args) throws Exception{
    Document document = Jsoup.connect(url).get();
    Element element = document.getElementById("FundHoldSharesTable");
    System.out.println(element.child(1).child(1));
}
```

## 依赖

```xml
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.8.3</version>
</dependency>
```

## LICENSE

![](LICENSE.png)