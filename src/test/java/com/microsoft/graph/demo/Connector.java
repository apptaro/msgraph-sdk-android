package com.microsoft.graph.demo;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.concurrency.DefaultExecutors;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.concurrency.IExecutors;
import com.microsoft.graph.concurrency.IProgressCallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.DefaultClientConfig;
import com.microsoft.graph.core.GraphErrorCodes;
import com.microsoft.graph.core.IClientConfig;
import com.microsoft.graph.extensions.GraphServiceClient;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.http.IHttpRequest;

public class Connector {

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
    
    private IAuthenticationProvider authenticationProvider() {
        final String code = "somecode";
        try {
            final OAuthClientRequest request = OAuthClientRequest //
                    .tokenProvider(OAuthProviderType.MICROSOFT) //
                    .setGrantType(GrantType.AUTHORIZATION_CODE) //
                    .setClientId("your-msgraph-application-client-id") //
                    .setClientSecret("your-msgraph-application-client-secret") //
                    .setCode(code) //
                    .buildQueryMessage();

            final OAuthClient client = new OAuthClient(new URLConnectionClient());
            final String accessToken = client.accessToken(request).getAccessToken();
            return new IAuthenticationProvider() {

                @Override
                public void authenticateRequest(IHttpRequest request) {
                    request.addHeader("Authorization", "Bearer " + accessToken);
                }
            };
        } catch (final OAuthSystemException | OAuthProblemException e) {
            throw new ClientException(e.getMessage(), e, GraphErrorCodes.AuthenticationFailure);
        }
    }

    public static void main(String[] args) {
        new Connector().connect();
    }

}
