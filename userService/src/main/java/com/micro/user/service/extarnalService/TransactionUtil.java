package com.micro.user.service.extarnalService;

import jakarta.ws.rs.HttpMethod;
import lombok.SneakyThrows;
import okhttp3.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionUtil {

    private static OkHttpClient okHttpClient;
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTHORIZATION = "Authorization";
    private static final Logger log=LoggerFactory.getLogger(TransactionUtil.class);



    public TransactionUtil() {
        okHttpClient = getOkHttpClientBuilder().readTimeout(Duration.ZERO).build();
    }
//    readTimeout:
//        The maximum time in milliseconds to wait for data from the server after a connection is established.
//        Applies to both the TCP socket and individual read IO operations.
//        The default value is 10 seconds.
//    writeTimeout:
//        The maximum time in milliseconds to wait while sending data to the server.
//        Applies to individual write IO operations.
//        The default value is 10 seconds.

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }



    public static <T> T query(String url, String method, Object payload, String token, Class<T> returnType){
        try(var response =getResponse(url,method,payload,token)){
            assert Objects.requireNonNull(response).body() != null;
            var stringResponse = response.body().string();
            if (stringResponse.isEmpty()) {
                return null;
            }
            return JsonUtils.convertToClass(stringResponse, returnType);
        }catch (Exception ex){
            return null;
        }
    }

    @SneakyThrows
    @Deprecated
    public static <T> List<T> queryList(String url, String httpMethod, String token, Object payload, Class<T> returnClass) {
        try (var response = getResponse(url, httpMethod, payload,token)) {
            assert response != null;
            var output = Objects.requireNonNull(response.body()).string();
            return JsonUtils.getObjectMapper().readValue(output,JsonUtils.getObjectMapper().getTypeFactory().constructCollectionType(List.class,returnClass));
        } catch (IOException e) {
            log.error("Error converting {},{}, {}", url, payload, ExceptionUtils.getRootCause(e).getMessage());
            throw new RuntimeException("TXN-UTIL-02 (URL : "+url+")");
        }
    }

//    public static Response queryForResponse(String url,String httpMethod, String token, Object payload) {
//        return getResponse(url, httpMethod, payload,token);
//    }

//    public static Response queryForResponse2(String url, String httpMethod, String token, Object payload)  {
////        String finalUrl = buildUrl(url);
//        var requestBuilder = new Request.Builder().url(url).
//                addHeader("Content-Type", APPLICATION_JSON);
//        if (token != null) {
//            requestBuilder.addHeader(AUTHORIZATION, token);
//            requestBuilder.addHeader("X-hguyt-sdfds", EncryptionUtil.getEncryptedHeaderValue());
//        }
//        return callApi(url,httpMethod,requestBuilder,payload);
//    }

    private static Response getResponse(String url, String method,Object payload, String token){
//        String url= url; //buildUrl(url);
        var requestBuilder = new Request.Builder().url(url)
                .addHeader("Content-Type", APPLICATION_JSON);
        if (token != null) {
            requestBuilder.addHeader(AUTHORIZATION, token);
        }
        return callApi(url,method,requestBuilder,payload);
    }

    private static Response callApi(String url, String httpMethod, Request.Builder requestBuilder, Object payload) {
        try {
            switch (httpMethod) {
                case HttpMethod.POST -> {
                    var body = RequestBody.create(Objects.requireNonNull(JsonUtils.toJsonString(payload)), MediaType.parse(APPLICATION_JSON));
                    requestBuilder = requestBuilder.post(body);
                }
                case HttpMethod.PUT -> {
                    var body = RequestBody.create(Objects.requireNonNull(JsonUtils.toJsonString(payload)), MediaType.parse(APPLICATION_JSON));
                    requestBuilder = requestBuilder.put(body);
                }
                case HttpMethod.DELETE -> requestBuilder = requestBuilder.delete();
                default -> requestBuilder = requestBuilder.get();
            }
            return okHttpClient.newCall(requestBuilder.build()).execute();
        } catch (Exception e) {
            log.error("Error executing {},{},{}", url, payload, ExceptionUtils.getRootCause(e).getMessage());
        }
        return null;
    }

//    private static String buildUrl(String url) {
//        try {
//            if(url.indexOf('?')<0) return url;
//            URL aURL = new URL(url);
//            Matcher matcher = PATTERN_QUERY_PARAM.matcher(url);
//            var params = new ArrayList<Triple<String,Integer,Integer>>();
//            String name = null;
//            var start = 0;
//            var end = 0;
//            while (matcher.find()) {
//                if(name!=null){
//                    end=matcher.start();
//                    params.add(Triple.of(name,start,end));
//                }
//                start=matcher.end();
//                name= matcher.group().substring(1,matcher.group().length()-1);
//            }
//            params.add(Triple.of(name,start,url.length()));
//            HttpUrl.Builder urlBuilder
//                    = Objects.requireNonNull(HttpUrl.parse(String.format("%s://%s%s%s", aURL.getProtocol(), aURL.getHost(), aURL.getPort() <= 0 ? "" : ":" + aURL.getPort(), aURL.getPath()))).newBuilder();
//            for (var param:params){
//                urlBuilder.addQueryParameter(param.getLeft(), url.substring(param.getMiddle(),param.getRight()));
//            }
//            return urlBuilder.build().toString();
//        } catch (Exception e) {
//            return url;
//        }
//    }

    private static Request getRequest(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }




}
