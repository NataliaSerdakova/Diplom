package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
@Epic("Навигация")
@Feature("Переходы между разделами")
@DisplayName("Тесты навигации по приложению")
public class NavigationTest extends BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final NavigationPage navigationPage = new NavigationPage();
    private final LoginPage loginPage = new LoginPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
    }

    @Test
    @DisplayName("Переход в раздел Новости из Главной")
    @Description("Открытие бокового меню и выбор пункта News")
    public void goToTheNewsSectionFromTheMain_19() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
    }

    @Test
    @DisplayName("Переход в раздел О приложении из Главной")
    @Description("Открытие бокового меню и выбор пункта About")
    public void goToTheAboutSectionFromTheMain_20() {
        navigationPage.openNavigationMenu();
        navigationPage.clickAbout();
        navigationPage.checkIsAboutPage();
    }

    @Test
    @DisplayName("Возврат из раздела О приложении")
    @Description("Переход в About и нажатие кнопки 'Назад'")
    public void returnFromTheSectionAbout_21() {
        navigationPage.openNavigationMenu();
        navigationPage.clickAbout();
        navigationPage.checkIsAboutPage();
        navigationPage.clickBack();
        navigationPage.checkIsNewsPage();
    }

    @Test
    @DisplayName("Переход в Главный раздел из Новостей")
    @Description("Переход в новости, открытие меню и возврат на Main")
    public void goToTheMainSectionFromTheNews_22() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
        navigationPage.openNavigationMenu();
        navigationPage.checkMenuElementsDisplayed();
        navigationPage.clickMain();
        navigationPage.checkIsMainPage();
    }

    @Test
    @DisplayName("Переход в раздел О приложении из Новостей")
    @Description("Переход в новости, открытие меню и выбор пункта About")
    public void goToTheAboutSectionFromTheNews_23() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
        navigationPage.openNavigationMenu();
        navigationPage.checkMenuElementsDisplayed();
        navigationPage.clickAbout();
        navigationPage.checkIsAboutPage();
    }

    @Test
    @DisplayName("Переход в раздел Цитаты")
    @Description("Нажатие на иконку бабочки на главном экране")
    public void goToTheQuotesSection_24() {
        navigationPage.clickButterfly();
        navigationPage.checkIsQuotesPage();
    }

    @Test
    @DisplayName("Развертывание описания новости на Главной")
    @Description("Нажатие на карточку новости для просмотра подробного текста")
    public void disclosureOfTheNewsDescriptionInMain_28() {
        newsSectionPage.expandFirstNews();
        newsSectionPage.checkDescriptionVisible();
    }

    @Test
    @DisplayName("Переход в Новости через ссылку All News")
    @Description("Нажатие на текстовую ссылку 'All News' в блоке новостей на главном экране")
    public void goToTheNewsSectionOnAllNews_29() {
        navigationPage.clickAllNewsLink();
        navigationPage.checkIsNewsPage();
    }
}


