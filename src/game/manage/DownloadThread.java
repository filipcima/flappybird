package game.manage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DownloadThread extends Thread {
    String urlPath;
    Path file;

    public DownloadThread(String urlPath, Path file){
        this.urlPath = urlPath;
        this.file = file;
    }

    @Override
    public void run() {
        super.run();
        try (InputStream is = new URL(urlPath).openConnection().getInputStream()) {
            Files.copy(is, file, StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {
            System.err.println("bad URL" + urlPath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
