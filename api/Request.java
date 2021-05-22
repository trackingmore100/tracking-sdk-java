package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Request {

    public boolean sandbox=false;
    /**
     * String string The base URL to be used for requests.
     */
    String apiBaseUrl = "api.trackingmore.com";

    /**
     * Integer The port to be used for requests.
     */
    Integer apiPort = 443;

    /**
     * String The version to be used for requests.
     */
    String apiVersion = "v3";

    /**
     * String string The path to be used for requests.
     */
    String apiPath;

    /**
     * String string The header key.
     */
    String headerKey = "Tracking-Api-Key";

    /**
     * String
     */
    String apiKey;

    /**
     * String string The url to be used for requests.
     */
    String url;


    /**
     * ArrayList String The params to be used for requests.
     */
    List<String> params;

    /**
     * ArrayList  String The header to be used for requests.
     */
    Map<String, String> header = new HashMap<>();


    /**
     */
    protected void setBaseUrl(String path) {
        String port = apiPort == 443 ? "https" : "http";
        this.url = port + "://" + apiBaseUrl + "/" + apiVersion;
        if (this.sandbox) {
            this.url += "/sandbox" ;
        }
        if (path != null) this.url += "/" + path;
    }

    public String jsonToGet(String body){
        return body.replace("{", "").replace("}", "").
                replace("\"", "").replace("'", "").
                replace(":", "=").replace(",", "&");
    }

    /**
     * send api request.
     */
    protected String sendApiRequest(String params,String method) {
        setBaseUrl(this.apiPath);
        setRequestHeader();
        setParams(params);
        return send(method);
    }

    /**
     * send api request.
     */
    protected String sendApiRequest() {
        setBaseUrl(this.apiPath);
        System.out.println(this.url);
        setRequestHeader();
        return send("GET");
    }


    public void setParams(String params) {
        List<String> data = new ArrayList<>();
        data.add(params);
        this.params = data;
    }

    protected void setRequestHeader() {
        Map<String, String> header = new HashMap<>();
        header.put(this.headerKey, this.apiKey);
        header.put("Content-Type", "application/json");
        this.header = header;
    }

    /**
     * send api request.
     * <p>
     * String  method
     */
    protected String send(String method) {
        method = method.toUpperCase();
        if (method.equals("POST")) {
            return sendPost(method);
        }
        if (method.equals("PUT")) {
            return sendPost(method);
        }
        if (method.equals("GET")) {
            return sendGet(method);
        }
        if (method.equals("DELETE")) {
            return sendPost(method);
        }
        return sendGet(method);
    }

    private String sendPost(String method) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        System.out.println(this.url);
        try {
            URL realUrl = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(method);

            for (Map.Entry<String, String> entry : this.header.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.connect();

            out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);

            StringBuilder sbBody = new StringBuilder();
            for (String str : this.params) {
                sbBody.append(str);
            }
            out.write(sbBody.toString());

            out.flush();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public String sendGet(String method) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(this.url);

            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setRequestMethod(method);

            for (Map.Entry<String, String> entry : this.header.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();

            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }
}
