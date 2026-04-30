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
import ru.iteco.fmhandroid.ui.pages.NewsFilterPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
@Epic("Раздел Новости")
@Feature("Функционал списка новостей")
@DisplayName("Тестирование раздела новостей")
public class NewsSectionTest extends BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final NewsFilterPage newsFilterPage = new NewsFilterPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
    }

    @Test
    @DisplayName("Сортировка новостей")
    @Description("Проверка изменения порядка новостей при нажатии на кнопку сортировки")
    public void sortButtonInNews_31() {
        String initialTitle = newsSectionPage.getFirstNewsTitle();
        newsSectionPage.clickSort();
        newsSectionPage.checkFirstNewsTitleChanged(initialTitle);
        newsSectionPage.clickSort();
        newsSectionPage.checkFirstNewsTitle(initialTitle);
    }

    @Test
    @DisplayName("Открытие фильтра")
    @Description("Проверка перехода на экран фильтрации из раздела новостей")
    public void filterButtonInNews_32() {
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
    }

    @Test
    @DisplayName("Переход в панель управления")
    @Description("Проверка открытия Control Panel для редактирования новостей")
    public void editButtonInNews_33() {
        newsSectionPage.clickEdit();
        newsSectionPage.checkControlPanelIsLoaded();
    }

    @Test
    @DisplayName("Развертывание описания новости")
    @Description("Проверка видимости подробного текста новости при нажатии на кнопку развертывания")
    public void disclosureOfTheNewsDescriptionInNews_30() {
        newsSectionPage.expandFirstNews();
        newsSectionPage.checkDescriptionVisible();
    }

    @Test
    @DisplayName("Кнопка обновления при пустом списке")
    @Description("Проверка отображения кнопки Refresh после применения фильтра без результатов")
    public void refreshButtonInNews_34() {
        newsSectionPage.clickFilter();
        newsFilterPage.enterStartDate("01.01.2020");
        newsFilterPage.enterEndDate("02.01.2020");
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkEmptyListAndRefreshButtonVisible();
        newsSectionPage.clickRefresh();
        newsSectionPage.checkEmptyListAndRefreshButtonVisible();
    }
}
