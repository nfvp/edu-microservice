package pt.nos.ms.something.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class HttpInterceptor implements ClientHttpRequestInterceptor {
    
    private final Logger log = Logger.getLogger("Interceptor");
    
    
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }
    
    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.println("========================= request =========================");
        System.out.println("URI         : " + request.getURI());
        System.out.println("Method      : " + request.getMethod());
        System.out.println("Headers     : " + request.getHeaders());
        System.out.println("Request body: " + new String(body, "UTF-8"));
    }
    
    private void logResponse(ClientHttpResponse response) throws IOException {
        System.out.println("========================= response ========================");
        System.out.println("Status code  : " + response.getStatusCode());
        System.out.println("Status text  : " + response.getStatusText());
        System.out.println("Headers      : " + response.getHeaders());
        System.out.println("Response body: " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        System.out.println("=========================== end ===========================\n");
    }

}
