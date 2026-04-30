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
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsEditorPage;
import ru.iteco.fmhandroid.ui.pages.NewsFilterPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
@Epic("Раздел Новости")
@Feature("Панель управления (Control Panel)")
@DisplayName("Тестирование панели управления новостями")
public class ControlPanelTest extends BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final NewsFilterPage newsFilterPage = new NewsFilterPage();
    private final NewsEditorPage newsEditorPage = new NewsEditorPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickEdit();
        controlPanelPage.checkControlPanelLoaded();
    }

    @Test
    @DisplayName("Сортировка в панели управления")
    @Description("Проверка изменения порядка новостей по дате в Control Panel")
    public void sortButtonInControlPanel_47() {
        String initialDate = controlPanelPage.getFirstNewsDate();
        controlPanelPage.clickSort();
        controlPanelPage.checkFirstNewsDateChanged(initialDate);
        controlPanelPage.clickSort();
        controlPanelPage.checkFirstNewsDate(initialDate);
    }

    @Test
    @DisplayName("Кнопка фильтра в панели управления")
    @Description("Проверка перехода к форме фильтрации из Control Panel")
    public void filterButtonInControlPanel_48() {
        controlPanelPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
    }

    @Test
    @DisplayName("Переход к созданию новости")
    @Description("Проверка открытия формы создания новой новости")
    public void addingButonInControlPanel_49() {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
    }

    @Test
    @DisplayName("Удаление новости")
    @Description("Удаление новости и проверка уменьшения общего количества в списке")
    public void deleteButtonInControlPanel_50() {
        String title = controlPanelPage.getFirstNewsTitle();
        int countBefore = controlPanelPage.getNewsCount(title);
        controlPanelPage.clickDeleteNews(0);
        controlPanelPage.confirmDeletion();
        controlPanelPage.checkNewsCountDecreased(title, countBefore);
    }

    @Test
    @DisplayName("Отмена удаления новости")
    @Description("Проверка, что новость остается в списке после нажатия кнопки отмены в диалоге")
    public void cancelingDeletionInControlPanel_51() {
        String titleToKeep = controlPanelPage.getFirstNewsTitle();
        controlPanelPage.clickDeleteNews(0);
        controlPanelPage.checkDeleteDialogDisplayed();
        controlPanelPage.cancelDeletion();
        controlPanelPage.checkNewsWithTitleExists(titleToKeep);
    }

    @Test
    @DisplayName("Переход к редактированию новости")
    @Description("Проверка открытия формы редактирования существующей новости")
    public void editButonInControlPanel_52() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
    }

    @Test
    @DisplayName("Развертывание описания новости")
    @Description("Проверка видимости текста описания новости в Control Panel")
    public void disclosureOfTheNewsDescriptionInControlPanel_53() {
        controlPanelPage.clickExpandNews(0);
        controlPanelPage.checkDescriptionVisible(0);
    }
}