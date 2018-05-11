package cmeplaza.com.webviewtest.bean;

/**
 * Created by klx on 2018/3/1.
 */

public class GpBean {
    private String code;  // 编码
    private String name;  // 名称
    private int number;  // 数量
    private float price; // 成本
    private float result; // 结果
    private float nowPrice;  // 现在
    private float minPrice;
    private float maxPrice;

    public GpBean() {
    }

    public GpBean(String code, String name, int number, float price) {
        this.code = code;
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(float nowPrice) {
        this.nowPrice = nowPrice;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public float getChange() {
        return nowPrice - price;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "GpBean{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", result=" + result +
                ", nowPrice=" + nowPrice +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
