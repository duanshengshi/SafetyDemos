import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;

/**
 * Created by dss on 2019/3/28
 **/
public class DesTest {
    public static void main(String arg[]) throws Exception{
            //获取密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            //初始化密钥生成器
            keyGenerator.init(168);
            //生成密钥
            SecretKey key = keyGenerator.generateKey();
            FileOutputStream f = new FileOutputStream("key1.dat");
            ObjectOutputStream b = new  ObjectOutputStream(f);
            b.writeObject(key);
//            FileUtils.writeByteArrayToFile(new File("D://keyFiles/key.dat"),key.getEncoded());
        FileInputStream fileInputStream=new FileInputStream("key1.dat");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        SecretKey k=(SecretKey)objectInputStream.readObject( );
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE,k);
        String s = "hello word";
        byte ptext[] = s.getBytes("UTF8");
        for(int i=0;i<ptext.length;i++){
            System.out.print(ptext[i]+",");
        }
        System.out.println("");
//        执行加密
        byte ctext[]=cipher.doFinal(ptext);
        for(int i=0;i<ctext.length;i++){
            System.out.print(ctext[i] +",");
        }
        FileOutputStream f2=new FileOutputStream("SEnc.dat");
        f2.write(ctext);
    }
}
