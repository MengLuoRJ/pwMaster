package UserCenter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecoverFile {
    private static String recoverKey;
    public RecoverFile(String reKey) {
        recoverKey = reKey;
    }

    public void generateRecoverFile() throws IOException {
        if(recoverKey==null) return;
        File fl = new File("RecoverKey.txt");
        boolean status = fl.createNewFile();
        FileWriter writer = new FileWriter(fl);
        if(status){ writer.write(recoverKey); }
        writer.flush();
        writer.close();
    }
}
