
package DataControl;

public class DCB {
    private String table; // 表单名
    private String title; // 标题或用途
    private String account; // 账号
    private String password; // 密码
    private String mark; // 备注

    public DCB() { }

    public DCB(String table, String title, String account, String password, String mark) {
        this.table = table;
        this.title = title;
        this.account = account;
        this.password = password;
        this.mark = mark;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
