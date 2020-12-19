package DataControl;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.commons.codec.binary.Hex;

public class Encryption {
    // 密钥生成方案
    public void generateKey() {
        try {
            // 生成 key，返回生成指定算法密钥的 KeyGenerator 对象
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(64);// 初始化此密钥生成器，确定密钥大小
            SecretKey sk = kg.generateKey();// 生成密钥
            byte[] bs = sk.getEncoded();

            // 转换 key 面向 DES
            DESKeySpec desKSpec = new DESKeySpec(bs); // 实例化 DES 密钥规则
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES"); // 实例化密钥工厂

            // 密钥全局贮存
            GlobalValue.ENCRYPTION_KEY = factory.generateSecret(desKSpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public String codeDES(String str) {
        // 加密
        Key KEY = GlobalValue.ENCRYPTION_KEY;
        String codeString = null;
        try{
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, KEY);
            byte[] result = cipher.doFinal(str.getBytes());
            codeString = Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return codeString;
    }

    public String decodeDES(String str) {
        Key KEY = GlobalValue.ENCRYPTION_KEY;
        String decodeString = null;
        try{
            // 解密
            Cipher cpr = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cpr.init(Cipher.DECRYPT_MODE, KEY);
            byte[] result = cpr.doFinal(str.getBytes());
            decodeString = new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return decodeString;
    }
}
