package game.manage;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;


public class FTPManager {
    private static FTPManager ftpManager = new FTPManager();
    public static FTPManager getInstance() { return ftpManager; }

    private FTPManager() {

    }
    public void uploadFile(String filename) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect("slonice.endora.cz", 21);
            ftp.login("atavarecek", "testovaciheslo");
            ftp.setFileType(FTP.LOCAL_FILE_TYPE);

            InputStream is = new FileInputStream(new File(filename));

            ftp.storeFile("/atavarecek.8u.cz/web/scores.txt", is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void downloadFile(String urlPath, Path file) {
        new DownloadThread(urlPath, file).run();
    }
}
