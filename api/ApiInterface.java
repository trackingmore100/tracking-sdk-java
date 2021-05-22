package api;

public interface ApiInterface {
    String realtime(String params);

    String count(String params);

    String count();

    String get();

    String get(String params);


    String modifyinfo(String params);


    String archive(String params);


    String delete(String params);


    String create(String params);


    String manualUpdate(String params);


    String remote(String params);


    String transitTime(String params);


    String detect(String params);


    String courier();

    String status();

    String status(String params);


    String notUpdate(String params);


    String modifyCourier(String params);

    String user();

    String airRealtime(String params);
}
