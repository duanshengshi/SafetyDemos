import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;

public class Des {
    public static void main(String args[]) throws Exception{
       //获取密文
        FileInputStream f=new FileInputStream("SEnc.dat");
        int num=f.available();
        byte[ ] ctext=new byte[num];
        f.read(ctext);
        //获取密钥
        FileInputStream fileInputStream=new FileInputStream("key1.dat");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        SecretKey k=(SecretKey)objectInputStream.readObject( );
        //创建密码器
        Cipher cp=Cipher.getInstance("DESede");
        cp.init(Cipher.DECRYPT_MODE,k);
        //解密
        byte []ptext=cp.doFinal(ctext);
        // 显示明文
        String p=new String(ptext,"UTF8");
        System.out.println(p);
    }

}
