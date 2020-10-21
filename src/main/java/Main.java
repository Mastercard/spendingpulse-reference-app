import com.mastercard.developer.interceptors.OkHttp2OAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.GasWeeklyApi;
import org.openapitools.client.api.ParametersApi;
import org.openapitools.client.api.SpendingPulseReportApi;
import org.openapitools.client.api.SubscriptionApi;
import org.openapitools.client.model.GasWeeklyListResponse;
import org.openapitools.client.model.ParameterListResponse;
import org.openapitools.client.model.SpendingPulseListResponse;
import org.openapitools.client.model.SubscriptionListResponse;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String consumerKey = "Your consumer key"; // You should copy this from "My Keys" on your project page e.g. UTfbhDCSeNYvJpLL5l028sWL9it739PYh6LU5lZja15xcRpY!fd209e6c579dc9d7be52da93d35ae6b6c167c174690b72fa
        String signingKeyAlias = "your key alias";
        String signingKeyFilePath = "path to your.p12 private key file"; // e.g. /Users/yourname/project/sandbox.p12
        String signingKeyPassword = "your password";
        PrivateKey signingKey = AuthenticationUtils.loadSigningKey(signingKeyFilePath, signingKeyAlias, signingKeyPassword);

        ApiClient client = new ApiClient();
        client.setBasePath("https://sandbox.api.mastercard.com/spendingpulse/v1/spulse.svc");
        client.setDebugging(true);

        List<Interceptor> interceptors = client.getHttpClient().networkInterceptors();
        interceptors.add(new ForceJsonResponseInterceptor());
        interceptors.add(new OkHttp2OAuth1Interceptor(consumerKey, signingKey));


        GasWeeklyApi gasWeeklyApi = new GasWeeklyApi(client);
        GasWeeklyListResponse gasweeklyGet = gasWeeklyApi.gasweeklyGet("1","25");

        System.out.println(gasweeklyGet.toString());

        SubscriptionApi subscriptionApi = new SubscriptionApi(client);
        SubscriptionListResponse subscriptionGet = subscriptionApi.subscriptionGet("1","25");

        System.out.println(subscriptionGet.toString());

        SpendingPulseReportApi spendingPulseReportApi = new SpendingPulseReportApi(client);
        SpendingPulseListResponse spendingpulseGet = spendingPulseReportApi.spendingpulseGet("1","25","Weekly Sales","Week","US","reportA","Weekly","sectorA","No");

        System.out.println(spendingpulseGet.toString());

        ParametersApi parametersApi = new ParametersApi(client);
        ParameterListResponse parametersGet = parametersApi.parametersGet("1","25");

        System.out.println(parametersGet.toString());
    }

    private static class ForceJsonResponseInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            System.out.println("===========" + originalRequest + "\n");

            String withJsonFormatUrl = withJsonFormat(originalRequest.uri().toString());
            Request newRequest = originalRequest.newBuilder().url(withJsonFormatUrl).build();
            return chain.proceed(newRequest);
        }

        private String withJsonFormat(String uri) {
            StringBuilder newUri = new StringBuilder(uri);
            newUri.append(uri.contains("?") ? "&" : "?");
            newUri.append("Format=JSON");
            return newUri.toString();
        }
    }
}
