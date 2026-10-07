package smoke;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.nio.file.Paths;

public class FirstTest {

    // CHANGE THIS to the exact name of your APK file inside src/test/resources/apps/
    private static final String APK_NAME = "Android-MyDemoAppRN.1.3.0.build-244.apk";

    @Test
    public void appShouldLaunch() throws Exception {
        String apkPath = Paths.get("src", "test", "resources", "apps", APK_NAME)
                .toAbsolutePath().toString();

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setUdid("emulator-5554")
                .setApp(apkPath);

        AndroidDriver driver = new AndroidDriver(
                URI.create("http://127.0.0.1:4723").toURL(), options);

        try {
            // Just so you can see the app on screen (we will remove sleeps later)
            Thread.sleep(3000);

            String currentPackage = driver.getCurrentPackage();
            System.out.println("Current package: " + currentPackage);

            Assert.assertEquals(currentPackage, "com.saucelabs.mydemoapp.rn");
        } finally {
            driver.quit();
        }
    }
}