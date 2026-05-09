package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import android.graphics.Bitmap;
import io.qameta.allure.android.rules.ScreenshotRule;

import android.graphics.Bitmap;

@HiltAndroidTest
public class BaseTest {

    // 1. Правило Hilt - инициализирует зависимости
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    // 2. Правило скриншотов - сработает только при падении
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "failure_screenshots");

    // 3. Цепочка правил: строго задаем порядок
    // Сначала отрабатывает outerRule (Hilt), затем всё, что внутри (скриншоты)
    @Rule
    public RuleChain chain = RuleChain
            .outerRule(hiltRule)
            .around(screenshotRule);
}
