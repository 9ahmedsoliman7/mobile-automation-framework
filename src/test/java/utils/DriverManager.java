package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
public class DriverManager {

    // Must match the APK file name inside src/test/resources/apps/
    private static final String APK_NAME = "Android-MyDemoAppRN.1.3.0.build-244.apk";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void initDriver() throws Exception {
        String apkPath = Paths.get("src", "test", "resources", "apps", APK_NAME)
                .toAbsolutePath().toString();

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setUdid("emulator-5554")
                .setApp(apkPath)
                .setAdbExecTimeout(Duration.ofSeconds(60))
                .setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(60))
                .setAndroidInstallTimeout(Duration.ofSeconds(120))
                .setAutoGrantPermissions(true);

        DRIVER.set(new AndroidDriver(URI.create(APPIUM_URL).toURL(), options));
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialised. Did the @Before hook run?");
        }
        return driver;
    }

    public static void quitDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}