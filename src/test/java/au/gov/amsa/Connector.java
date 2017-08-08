package au.gov.amsa;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.concurrency.DefaultExecutors;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.concurrency.IExecutors;
import com.microsoft.graph.concurrency.IProgressCallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.DefaultClientConfig;
import com.microsoft.graph.core.IClientConfig;
import com.microsoft.graph.extensions.GraphServiceClient;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.http.IHttpRequest;

public class Connector {

    private IAuthenticationProvider authenticationProvider() {
        return new IAuthenticationProvider() {

            @Override
            public void authenticateRequest(IHttpRequest request) {
                request.addHeader("Authorization", "Bearer " + getAccessToken());
            }

            private String getAccessToken() {
                
                //use the 
                // TODO implement
                return "boo";
            }

        };
    }

    public void connect() {
        DefaultExecutors.set(createExecutors());
        final IClientConfig clientConfig = DefaultClientConfig
                .createWithAuthenticationProvider(authenticationProvider());

        final IGraphServiceClient client = new GraphServiceClient.Builder() //
                .fromConfig(clientConfig) //
                .buildClient();
        System.out.println("page size=" + client.getMe() //
                .getMailFolders() //
                .buildRequest() //
                .get().getCurrentPage().size());
    }

    private static IExecutors createExecutors() {
        return new IExecutors() {

            @Override
            public void performOnBackground(Runnable runnable) {
                runnable.run();

            }

            @Override
            public <Result> void performOnForeground(Result result, ICallback<Result> callback) {
                callback.success(result);
            }

            @Override
            public <Result> void performOnForeground(int progress, int progressMax,
                    IProgressCallback<Result> callback) {
                callback.progress(progress, progressMax);
            }

            @Override
            public <Result> void performOnForeground(ClientException exception, ICallback<Result> callback) {
                callback.failure(exception);
            }
        };
    }
    
    public static void main(String[] args) {
        new Connector().connect();
    }
    

}
