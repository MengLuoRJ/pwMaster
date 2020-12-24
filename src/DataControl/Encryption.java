package DataControl;

import org.apache.commons.codec.digest.DigestUtils;

public class Encryption {

    // SHA512Hex 消息摘要
    public String encodeSHA512Hex(String data) {
        return DigestUtils.sha512Hex(data);
    }

}
