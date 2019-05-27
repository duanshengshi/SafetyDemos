package NettySSL;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

public class Utils {

    private static KeyManager[] getKeyManagers(InputStream is, String keyStorePass,String algorithm,String keyStoreType) {
        if (is == null) {
            return null;
        }
        KeyStore ks = null;
        KeyManagerFactory keyFac = null;
        KeyManager[] km = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = KeyManagerFactory.getInstance(algorithm);
            ks = KeyStore.getInstance(keyStoreType);
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

    private static TrustManager[] getTrustManagers(InputStream is, String keyStorePass,String algorithm,String keyStoreType) {
        if (is == null) {
            return null;
        }
        KeyStore ks = null;
        TrustManagerFactory keyFac = null;

        TrustManager[] tm = null;
        try {
            // 获得KeyManagerFactory对象. 初始化位默认算法
            keyFac = TrustManagerFactory.getInstance(algorithm);
            ks = KeyStore.getInstance(keyStoreType);
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
