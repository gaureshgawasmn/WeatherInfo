package com.techlab.weatherinfo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Configuration
@Profile("dev")
public class SSLConfiguration {

    public SSLConfiguration() {
        // added temporarily to avoid SSL certificate error not recommended for production
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllCertificates()}, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class TrustAllCertificates implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] cert, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] cert, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
