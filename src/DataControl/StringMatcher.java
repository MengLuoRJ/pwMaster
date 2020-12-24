package DataControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {

    // 账号字符合法检测：字母开头，允许 5-32 字节，允许字母数字下划线
    public boolean AccountMatchCheck(String account) {
        String pattern = "^[a-zA-Z][a-zA-Z0-9_]{5,32}$";
        Pattern pr = Pattern.compile(pattern);
        Matcher mc = pr.matcher(account);
        return mc.matches();
    }

    // 强密码检测：必须包含大小写字母和数字的组合，可以使用特殊字符，长度在 8-32 之间
    public boolean PasswordMatchCheck(String password) {
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$";
        Pattern pr = Pattern.compile(pattern);
        Matcher mc = pr.matcher(password);
        return mc.matches();
    }

}
