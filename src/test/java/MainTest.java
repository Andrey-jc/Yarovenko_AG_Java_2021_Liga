import org.junit.jupiter.api.Test;

import java.io.*;

public class MainTest {


    @Test
    public void testMain() throws FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File("build.gradle"));
        System.setIn(fips);
        Main.main(args);
        System.setIn(original);
    }
}
