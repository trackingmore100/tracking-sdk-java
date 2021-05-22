Trackingmore-JAVA
=================

The PHP SDK of Trackingmore API
## Official document

[Document](https://www.trackingmore.com/v3/api-index)

##Init
```java
import api.Api;

    //in you class
    public class Test {
      ...
      static Api api = new Api("you api Key");
      ...
    }
```


Quick Start
--------------
- Put your ApiKey in the constructor of the Api class
- All returns are in Json format String.
- After instantiating the Api class, you can use its interface methods
- You can set the sandbox of the Api instance to true to turn on the sandbox mode: <code>api.sandbox=true;</code>
- Most Api params receive multiple tracking numbers

**Get a list of the couriers in Trackingmore**

    String response = api->courier();

**Detect which couriers defined in your account match a tracking number**

    String data = "{\"tracking_number\":\"UB209300714LV\"}"
    String response = api->detect(data);


**Post trackings to your account**

    //Create single tracking numbers
    String data = "{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}";
    //Create multiple tracking numbers
    String data = "[{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}",
        "{\"tracking_number\" => \"LZ448865302CN\", \"carrier_code\" => \"china-ems\"}]";
    String response =api->create(data);

**Summary of Connection API Methods with all the api and Methods**

    #sandbox model
    api->sandbox = true;
    # Get a tracking number of real-time query result data
    //String data = "{\"tracking_number\" => \"UB209300714LV\", \"carrier_code\" => \"cainiao\"}";
    //String response = api->realtime(data);
    
    # archive
    String data = "{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}";
    String response = api->archive(data);
    
    # Get a list of all carriers
    String response = api->courier();
    
    # Create a tracking number
    String data = "{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}";
    String response = api->create(data);
    
    # Create multiple tracking numbers
    String data = "[{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}",
    "{\"tracking_number\" => \"LZ448865302CN\", \"carrier_code\" => \"china-ems\"}]";
    String response =api->create(data);

    # Get logistics information for multiple tracking numbers
    String data = "{\"tracking_number\" => \"RP325552475CN,LZ448865302CN\"}";
    String response = api->get(data);
    
    # Modify other information of a tracking number
    String data = ['num'=>\"RP325552475CN\",'carrier_code'=>\"china-post\",\"order_id\" => \"#1234\"];
    String response = api->modifyinfo(data);
    
    # Modify the information of multiple tracking numbers
    String data = "[{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\", \"order_id\" => \"#1234\",}{\"tracking_number\" => \"LZ448865302CN\", \"carrier_code\" => \"china-ems\", \"order_id\" => \"#5678\",},]";
    String response =  api->modifyinfo(data);
    
    # Modify the carrier code of a tracking number
    String data = "{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\", \"new_carrier_code\" => \"china-ems\"}";
    String response =  api->modifyCourier(data);
    
    # Delete a tracking number
    String response = api->delete("{\"tracking_number\":\"RP325552475CN\"}");
    
    # Delete multiple tracking numbers
    String data = "[{\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}",
    "{\"tracking_number\" => \"LZ448865302CN\", \"carrier_code\" => \"china-ems\"}]";
    String response = api->delete(data);
    
    # Set multiple tracking numbers no longer update
    String data ="[
    {\"tracking_number\" => \"RP325552475CN\", \"carrier_code\" => \"china-post\"}",
    "{\"tracking_number\" => \"LZ448865302CN\", \"carrier_code\" => \"china-ems\"},
    ]";
    String response = api->notUpdate(data);
    
    # Get status statistics of tracking ticket number
    String data = "{\"created_at_min\" => "1621663220", \"created_at_max\" => "1621664220"}";
    String response = api->status(data);
    
    # Get user information
    String response =  api->user();
    
    # Query whether remote
    String data = "[
    {\"country\" => \"Japan\", \"postcode\" => \"7621094\"},
    {\"country\" => \"NZ\", \"postcode\" => \"Papaaroha\"},
    ]";
    String response = api->remote(data);
    
    # Get the timeliness of multiple carriers
    String data = "[
    {\"original\" => \"CN\", \"destination\" => \"US\", \"carrier_code\" => \"dhl\"},
    {\"original\" => \"CN\", \"destination\" => \"RU\", \"carrier_code\" => \"dhl\"},
    ]";
    String response = api->transitTime(data);

## Typical Server Responses

We will respond with one of the following status codes.

Code|Type | Message
----|--------------|-------------------------------
200    | <code>Success</code>|    Request response is successful
203    | <code>PaymentRequired</code>|  API service is only available for paid account Please subscribe paid plan to unlock API services                                                             ul
204    | <code>No Content</code>|    Request was successful, but no data returned Tracking NO. or target data possibly do not exist
400    | <code>Bad Request</code>| Request type error Please check the API documentation for the request type of this API
401    | <code>Unauthorized</code>|    Authentication failed or has no permission Please check and ensure your API Key is correct
403    | <code>Bad Request</code>|    Page does not exist Please check and ensure your link is correct                                                                                             ul
404    | <code>Not Found</code>|    Page does not exist Please check and ensure your link is correct
408    | <code>Time Out</code>|    Request timeout The official website did not return data, please try again later
411    | <code>Bad Request</code>|    Specified request parameter length exceeds length limit Please check and ensure that the request parameters are of the required length
412    | <code>Bad Request</code>|    Specified request parameter format doesn't meet requirements Please check and ensure that the request parameters are in the required format
413    | <code>Out limited</code>|    The number of request parameters exceeds the limit Please check the API documentation for the limit of this API
417    | <code>Bad Request</code>|    Missing request parameters or request parameters cannot be parsed Please check and ensure that the request parameters are complete and correctly formatted
421    | <code>Bad Request</code>|    Some of required parameters are empty Some couriers need special parameters to track logistics information (Special Couriers)
422    | <code>Bad Request</code>|    Unidentifiable courier code Please check and ensure that the courier code are correct(Courier code)
423    | <code>Bad Request</code>|    Tracking No. already exists
424    | <code>Bad Request</code>|    Tracking No. no exists Please use 「Create trckings」 API first to create trackings
429    | <code>Bad Request</code>|    Exceeded API request limits, please try again later Please check the API documentation for the limit of this API
511    | <code>Server Error</code>|    Server error Please contact us: service@trackingmore.org.
512    | <code>Server Error</code>|    Server error Please contact us: service@trackingmore.org.
513    | <code>Server Error</code>|    Server error Please contact us: service@trackingmore.org.        