# Mobile Automation Framework (Appium + Cucumber + Java)

A BDD-style mobile test automation framework for Android, built with **Appium**, **Cucumber**, **TestNG** and **Java**, using the **Page Object Model**.

The tests run against the [Sauce Labs My Demo App (React Native)](https://github.com/saucelabs/my-demo-app-rn), a public demo shopping app.

## Tech stack

| Area | Tool |
|---|---|
| Language | Java (compiled at Java 17 level) |
| Mobile automation | Appium Java Client 10.1.1, UiAutomator2 driver |
| BDD | Cucumber 7.34.6 (Gherkin) |
| Test runner | TestNG 7.11.0 |
| Build tool | Maven |
| Selenium (pinned) | 4.43.0 |

Tested with Appium server 3.7.0 and the UiAutomator2 driver 8.7.0 on an Android emulator.

## Project structure

```
src
└── test
    ├── java
    │   ├── hooks/     Cucumber hooks (start driver, screenshot on failure, quit)
    │   ├── pages/     Page Objects (BasePage, LoginPage)
    │   ├── steps/     Step definitions
    │   ├── runner/    TestNG + Cucumber runner
    │   └── utils/     DriverManager (driver setup)
    └── resources
        ├── features/  Gherkin feature files
        └── apps/      Put the APK here (not committed)
```

## Current test coverage

Feature: **Login** (`src/test/resources/features/login.feature`)

- Successful login with valid credentials
- Login attempt by a locked-out user shows an error message

## Design notes

- **Page Object Model:** locators and screen actions live in page classes, so step definitions stay readable.
- **Accessibility ids** are used as locators wherever possible, because they are the most stable option.
- **Explicit waits** (`WebDriverWait`) are used instead of fixed sleeps.
- **Hooks:** a fresh Appium session is started for each scenario, and a screenshot is attached to the report when a scenario fails.
- **ThreadLocal driver** in `DriverManager`, which keeps the setup ready for parallel execution later.

## Prerequisites

- JDK 17 or newer
- Maven
- Node.js and **Appium 3** (`npm i -g appium`)
- UiAutomator2 driver (`appium driver install uiautomator2`)
- Android Studio with an Android emulator (AVD)
- `ANDROID_HOME` and `JAVA_HOME` environment variables set

Check your setup with:

```
appium driver doctor uiautomator2
adb devices
```

## How to run

1. **Get the app.** Download the Android APK from the [My Demo App releases](https://github.com/saucelabs/my-demo-app-rn/releases) and save it as:

   ```
   src/test/resources/apps/apk.apk
   ```

   (The file name is set in `utils/DriverManager.java`.)

2. **Start the emulator** and confirm it appears as `emulator-5554` in `adb devices`. If your device id is different, change it in `DriverManager.java`.

3. **Start the Appium server** in a separate terminal:

   ```
   appium
   ```

4. **Run the tests.** In IntelliJ, right-click `runner/TestRunner.java` and choose Run, or from the terminal:

   ```
   mvn test -Dtest=TestRunner
   ```

5. **View the report** at `target/cucumber-report.html`.

## Test data

The demo app shows its own test users on the login screen:

| User | Password | Note |
|---|---|---|
| bob@example.com | 10203040 | Valid user |
| alice@example.com | 10203040 | Locked out |

## Planned improvements

- Add-to-cart, remove-from-cart and checkout scenarios
- `Scenario Outline` with multiple data sets, and `@smoke` / `@regression` tags
- Allure reporting
- Configuration file instead of hard-coded device and APK values
- CI with GitHub Actions

## Author

**Ahmed Soliman**, Junior QA Engineer (ISTQB CTFL)

- LinkedIn: [linkedin.com/in/ahmed-soliman-qa](https://linkedin.com/in/ahmed-soliman-qa)
- GitHub: [github.com/9ahmedsoliman7](https://github.com/9ahmedsoliman7)