package com.github.thesilentpro.hangarapi.client.builder;

import com.github.thesilentpro.hangarapi.auth.AuthProvider;
import com.github.thesilentpro.hangarapi.client.HangarClient;
import com.github.thesilentpro.hangarapi.client.HangarClientImpl;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

public class HangarClientBuilderImpl implements HangarClientBuilder {

    private HttpClient client;
    private AuthProvider authProvider;
    private String userAgent;

    public HangarClientBuilderImpl() {
        this.client = HttpClient.newBuilder()
                .executor(Executors.newSingleThreadExecutor(r -> new Thread(r, "hangar-client")))
                .build();
        this.userAgent = "hangar-api/1.0.0" + " (" + System.getProperty("os.name") +
                "; " + System.getProperty("os.version") +
                "; " + System.getProperty("os.arch") +
                ") " + System.getProperty("java.vendor") + "/" + System.getProperty("java.version");
    }

    @Override
    public HangarClientBuilder client(@NotNull HttpClient client) {
        this.client = client;
        return this;
    }

    @Override
    public HangarClientBuilder auth(@NotNull AuthProvider authProvider) {
        this.authProvider = authProvider;
        return this;
    }

    @Override
    public HangarClientBuilder userAgent(@NotNull String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public HangarClient build() {
        return new HangarClientImpl(this.client, this.authProvider, this.userAgent);
    }

}