package Day1_Setup;

import com.microsoft.playwright.CLI;

import java.io.IOException;
import java.net.URISyntaxException;

public class Setup {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        CLI.main(new String[]{"install"});
    }
}
