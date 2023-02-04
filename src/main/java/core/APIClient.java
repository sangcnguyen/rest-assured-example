package core;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIClient {
    private String BASE_URL = "https://jsonplaceholder.typicode.com";

    public <T, K> ResponseWrapper<T[]> getEntityArray(Class<T[]> clazz, RequestWrapper<K> requestWrapper, String path) {
        ResponseWrapper<T[]> responseWrapper = new ResponseWrapper<>();

        Response response = configureRequest(requestWrapper).when().get(BASE_URL.concat(path));

        responseWrapper.setBody(response.as(clazz));
        responseWrapper.setResponseRaw(response);

        configureResponse(responseWrapper);

        return responseWrapper;
    }

    public <T, K> ResponseWrapper<T> getEntity(Class<T> clazz, RequestWrapper<K> requestWrapper, String path) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        Response response = configureRequest(requestWrapper).when().get(BASE_URL.concat(path));

        responseWrapper.setBody(response.getBody().as(clazz));
        responseWrapper.setResponseRaw(response);

        configureResponse(responseWrapper);

        return responseWrapper;
    }

    public <T, K> ResponseWrapper<T> postEntity(Class<T> t, RequestWrapper<K> requestWrapper, String path) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        Response response = configureRequest(requestWrapper).when().post(BASE_URL.concat(path));

        responseWrapper.setBody(response.as(t));
        responseWrapper.setResponseRaw(response);

        configureResponse(responseWrapper);

        return responseWrapper;
    }

    public <T, K> ResponseWrapper<T> putEntity(Class<T> t, RequestWrapper<K> requestWrapper, String path) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        Response response = configureRequest(requestWrapper).when().put(BASE_URL.concat(path));

        responseWrapper.setBody(response.as(t));
        responseWrapper.setResponseRaw(response);

        configureResponse(responseWrapper);

        return responseWrapper;
    }

    public <T, K> ResponseWrapper<T> patchEntity(Class<T> t, RequestWrapper<K> requestWrapper, String path) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        Response response = configureRequest(requestWrapper).when().patch(BASE_URL.concat(path));

        responseWrapper.setBody(response.as(t));
        responseWrapper.setResponseRaw(response);

        configureResponse(responseWrapper);

        return responseWrapper;
    }

    public <T, K> ResponseWrapper<T> deleteEntity(Class<T> t, RequestWrapper<K> requestWrapper, String path) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        Response response = configureRequest(requestWrapper).when().delete(BASE_URL.concat(path));

        responseWrapper.setBody(response.as(t));
        responseWrapper.setResponseRaw(response);

        configureResponse(responseWrapper);

        return responseWrapper;
    }


    private <T> RequestSpecification configureRequest(RequestWrapper<T> requestWrapper) {
        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.log().parameters();
        requestSpecification.log().method();

        if (requestWrapper.getHeaders() != null) {
            for (String key : requestWrapper.getHeaders().keySet()) {
                requestSpecification.header(key, requestWrapper.getHeaders().get(key));
            }
        }

        if (requestWrapper.getQueryParameters() != null) {
            for (String key : requestWrapper.getQueryParameters().keySet()) {
                requestSpecification.queryParam(key, requestWrapper.getQueryParameters().get(key));
            }
        }

        return requestSpecification;
    }

    private <T> void configureResponse(ResponseWrapper<T> responseWrapper) {
        responseWrapper.getResponseRaw().then().log().body();
        responseWrapper.getResponseRaw().then().log().status();
    }
}
