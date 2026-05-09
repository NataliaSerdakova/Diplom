package ru.iteco.fmhandroid.ui.tests;

import android.graphics.Bitmap;
import dagger.hilt.android.testing.HiltAndroidRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import androidx.test.platform.app.InstrumentationRegistry;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public abstract class BaseTest {

    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    private final TestWatcher screenshotWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName());
        }
    };

    @Rule
    public RuleChain chain = RuleChain
            .outerRule(hiltRule)
            .around(screenshotWatcher);

    private void captureScreenshot(String methodName) {
        try {
            Bitmap screenshotBitmap = InstrumentationRegistry.getInstrumentation()
                    .getUiAutomation()
                    .takeScreenshot();

            if (screenshotBitmap != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                screenshotBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] screenshotBytes = outputStream.toByteArray();
                Allure.addAttachment("Скриншот при падении в: " + methodName,
                        "image/png",
                        new ByteArrayInputStream(screenshotBytes),
                        ".png");
            }
        } catch (Exception e) {
            System.err.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }
}
