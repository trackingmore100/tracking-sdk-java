import api.Api;

public class Test {

    static Api api = new Api("you api Key");

    public static void main(String[] args) {
        //api.sandbox = true;
        //see api.Api
        String res = api.get();
        System.out.println(res);
        res = api.create("[{\"tracking_number\":\"UB209300714LV\",\"courier_code\":\"cainiao\",\"order_number\":\"#1234\",\"destination_code\":\"LV\",\"logistics_channel\":\"4px channel\",\"customer_name\":\"test\",\"customer_email\":\"example@abc.com\",\"customer_phone\":\"+1123456789\",\"note\":\"check\",\"title\":\"title\",\"lang\":\"en\"}]");
        System.out.println(res);
    }
}
