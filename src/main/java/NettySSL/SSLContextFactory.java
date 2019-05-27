package NettySSL;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

public class SSLContextFactory {
    private static final SSLContext SSL_CONTEXT_S;

    private static final SSLContext SSL_CONTEXT_C;

    static {
        SSLContext sslContextS = null;
        SSLContext sslContextC = null;
        try {
            sslContextS = SSLContext.getInstance("SSLv3");
            sslContextC = SSLContext.getInstance("SSLv3");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        try {
            if (getKeyManagersServer() != null && getTrustManagersServer() != null) {
                sslContextS.init(getKeyManagersServer(), getTrustManagersServer(), null);
            }
            if (getKeyManagersClient() != null && getTrustManagersClient() != null) {
                sslContextC.init(getKeyManagersClient(), getTrustManagersClient(), null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        sslContextS.createSSLEngine().getSupportedCipherSuites();
        sslContextC.createSSLEngine().getSupportedCipherSuites();
        SSL_CONTEXT_S = sslContextS;
        SSL_CONTEXT_C = sslContextC;
    }

    public static SSLContext getSslContext() {
        return SSL_CONTEXT_S;
    }

    public static SSLContext getSslContext2() {
        return SSL_CONTEXT_C;
    }

    private static TrustManager[] getTrustManagersServer() {
        FileInputStream is = null;
        KeyStore ks = null;
        TrustManagerFactory keyFac = null;
        TrustManager[] kms = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = TrustManagerFactory.getInstance("SunX509");
            is = new FileInputStream("D:\\CodeRepository\\secure\\src\\main\\resources\\kserver.keystore");
            ks = KeyStore.getInstance("JKS");
            String keyStorePass = "sNetty";
            ks.load(is, keyStorePass.toCharArray());
            keyFac.init(ks);
            kms = keyFac.getTrustManagers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return kms;
    }

    private static TrustManager[] getTrustManagersClient() {
        FileInputStream is = null;
        KeyStore ks = null;
        TrustManagerFactory keyFac = null;

        TrustManager[] kms = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = TrustManagerFactory.getInstance("SunX509");
            is = new FileInputStream("main/java/conf/cChat.jks");
            ks = KeyStore.getInstance("JKS");
            String keyStorePass = "sNetty";
            ks.load(is, keyStorePass.toCharArray());
            keyFac.init(ks);
            kms = keyFac.getTrustManagers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return kms;
    }

    private static KeyManager[] getKeyManagersServer() {
        FileInputStream is = null;
        KeyStore ks = null;
        KeyManagerFactory keyFac = null;
        KeyManager[] kms = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = KeyManagerFactory.getInstance("SunX509");
            is = new FileInputStream("main/java/conf/sChat.jks");
            ks = KeyStore.getInstance("JKS");
            String keyStorePass = "sNetty";
            ks.load(is, keyStorePass.toCharArray());
            keyFac.init(ks, keyStorePass.toCharArray());
            kms = keyFac.getKeyManagers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return kms;
    }

    private static KeyManager[] getKeyManagersClient() {
        FileInputStream is = null;
        KeyStore ks = null;
        KeyManagerFactory keyFac = null;

        KeyManager[] kms = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = KeyManagerFactory.getInstance("SunX509");
            is = new FileInputStream("main/java/conf/cChat.jks");
            ks = KeyStore.getInstance("JKS");
            String keyStorePass = "sNetty";
            ks.load(is, keyStorePass.toCharArray());
            keyFac.init(ks, keyStorePass.toCharArray());
            kms = keyFac.getKeyManagers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return kms;
    }

    private static KeyManager[] getKeyManagers(InputStream is, String keyStorePass) {
        if (is == null) {
            return null;
        }
        KeyStore ks = null;
        KeyManagerFactory keyFac = null;
        KeyManager[] km = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = KeyManagerFactory.getInstance("SunX509");
            ks = KeyStore.getInstance("JKS");
            ks.load(is, keyStorePass.toCharArray());
            keyFac.init(ks, keyStorePass.toCharArray());
            km = keyFac.getKeyManagers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return km;
    }

    private static TrustManager[] getTrustManagers(InputStream is, String keyStorePass) {
        if (is == null) {
            return null;
        }
        KeyStore ks = null;
        TrustManagerFactory keyFac = null;

        TrustManager[] tm = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = TrustManagerFactory.getInstance("SunX509");
            ks = KeyStore.getInstance("JKS");
            ks.load(is, keyStorePass.toCharArray());
            keyFac.init(ks);
            tm = keyFac.getTrustManagers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tm;
    }
}