package api;

public class Api extends Request implements ApiInterface {

    public Api(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String realtime(String params) {
        this.apiPath = "trackings/realtime";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String count(String params) {
        this.apiPath = "trackings/count";
        return this.sendApiRequest(params,"GET");
    }
    @Override
    public String count() {
        this.apiPath = "trackings/count";
        return this.sendApiRequest();
    }

    @Override
    public String get() {
        this.apiPath = "trackings/get";
        return this.sendApiRequest();
    }

    @Override
    public String get(String params) {
        this.apiPath = "trackings/get";
        return this.sendApiRequest(params,"GET");
    }

    @Override
    public String modifyinfo(String params) {
        this.apiPath = "trackings/modifyinfo";
        return this.sendApiRequest(params,"PUT");
    }

    @Override
    public String archive(String params) {
        this.apiPath = "trackings/archive";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String delete(String params) {
        this.apiPath = "trackings/delete";
        return this.sendApiRequest(params,"DELETE");
    }

    @Override
    public String create(String params) {
        this.apiPath = "trackings/create";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String manualUpdate(String params) {
        this.apiPath = "trackings/manualupdate";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String remote(String params) {
        this.apiPath = "trackings/remote";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String transitTime(String params) {
        this.apiPath = "trackings/transittime";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String detect(String params) {
        this.apiPath = "trackings/detect";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String courier() {
        this.apiPath = "trackings/courier";
        return this.sendApiRequest();
    }

    @Override
    public String status() {
        this.apiPath = "trackings/status";
        return this.sendApiRequest();
    }

    @Override
    public String status(String params) {
        this.apiPath = "trackings/status";
        return this.sendApiRequest(params,"GET");
    }

    @Override
    public String notUpdate(String params) {
        this.apiPath = "trackings/notupdate";
        return this.sendApiRequest(params,"POST");
    }

    @Override
    public String modifyCourier(String params) {
        this.apiPath = "trackings/modifycourier";
        return this.sendApiRequest(params,"PUT");
    }

    @Override
    public String user() {
        this.apiPath = "trackings/getuserinfo";
        return this.sendApiRequest();
    }

}
