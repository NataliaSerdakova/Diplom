package ru.iteco.fmhandroid.ui;

import android.app.Application;
import android.content.Context;
// ИСПРАВЛЕННЫЙ ИМПОРТ:
import io.qameta.allure.android.runners.AllureAndroidJUnitRunner;
import dagger.hilt.android.testing.HiltTestApplication;

// НАСЛЕДУЕМСЯ ОТ AllureAndroidJUnitRunner
public class CustomTestRunner extends AllureAndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // Оставляем HiltTestApplication, чтобы Hilt продолжал работать в тестах
        return super.newApplication(cl, HiltTestApplication.class.getName(), context);
    }
}