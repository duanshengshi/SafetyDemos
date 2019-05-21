package RSA;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by dss on 2019/3/28
 **/
public class RsaFactory {
    public static void main(String args[])  throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
//        keyPair keyPair1 = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        byte[] bytes = cipher.doFinal("message".getBytes());
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        byte[] by = cipher.doFinal(bytes);
        String ss = new String(by);

    }
}
