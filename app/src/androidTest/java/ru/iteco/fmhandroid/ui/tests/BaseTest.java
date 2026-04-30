package ru.iteco.fmhandroid.ui.tests;

import io.qameta.allure.Allure;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import android.graphics.Bitmap;
import androidx.test.runner.screenshot.Screenshot;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BaseTest {

    @Rule
    public TestWatcher screenshotRule = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName());
        }

        private void captureScreenshot(String name) {
            try {
                Bitmap bitmap = Screenshot.capture().getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                Allure.addAttachment(name, "image/png", new ByteArrayInputStream(outputStream.toByteArray()), "png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
