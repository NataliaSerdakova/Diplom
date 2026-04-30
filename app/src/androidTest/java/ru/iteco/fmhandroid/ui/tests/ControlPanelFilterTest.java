package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.time.LocalDate;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.pages.CalendarPage;
import ru.iteco.fmhandroid.ui.pages.ControlPanelFilterPage;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
@Epic("Раздел Новости")
@Feature("Фильтрация в панели управления")
@DisplayName("Тестирование расширенного фильтра новостей (Control Panel)")
public class ControlPanelFilterTest extends BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final ControlPanelFilterPage filterPage = new ControlPanelFilterPage();
    private final CalendarPage calendarPage = new CalendarPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickEdit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickFilter();
    }

    @Test
    @DisplayName("Фильтрация по всем полям")
    @Description("Установка категории, дат и проверка состояний чекбоксов")
    public void filteringNewsByAllFieldsInNews_84() {
        String category = DataHelper.getRandomCategory();
        String startDate = DataHelper.getDate(-1);
        String endDate = DataHelper.getDate(0);
        filterPage.checkFilterFormIsLoaded();
        filterPage.selectCategory(category);
        filterPage.enterStartDate(startDate);
        filterPage.enterEndDate(endDate);
        filterPage.checkActiveCheckboxChecked();
        filterPage.checkNotActiveCheckboxChecked();
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    @DisplayName("Отмена фильтрации")
    @Description("Заполнение данных и нажатие кнопки отмены")
    public void cancelingNewsFilteringByAllFieldsInNews_85() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.selectCategory(DataHelper.getRandomCategory());
        filterPage.clickCancel();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }


    @Test
    @DisplayName("Фильтрация по категории")
    @Description("Выбор случайной категории из списка")
    public void filteringNewsByCategoryInNews_86() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.selectCategory(DataHelper.getRandomCategory());
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    @DisplayName("Фильтрация по кастомной категории")
    @Description("Ручной ввод категории 'Заявление'")
    public void filteringNewsByAnotherCategoryInNews_87() {
        filterPage.enterCustomCategory("Заявление");
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    @DisplayName("Фильтрация по валидному периоду")
    @Description("Установка интервала дат за последнюю неделю")
    public void filteringNewsByValidPeriodInNews_88() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.enterStartDate(DataHelper.getDate(-7));
        filterPage.enterEndDate(DataHelper.getDate(0));
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    @DisplayName("Ошибка при отсутствии даты окончания")
    @Description("Проверка валидации: нельзя применить фильтр только с одной датой")
    public void filteringNewsByTheBeginningOfThePeriodInNews_89() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.enterStartDate(DataHelper.getDate(0));
        filterPage.clickFilterSubmit();
        filterPage.checkWrongPeriodError();
    }

    @Test
    @DisplayName("Фильтрация по инвертированному периоду")
    @Description("Дата начала позже даты окончания - ожидается пустой список")
    public void filteringNewsByInvalidPeriodInNews_91() {
        String startDate = "06.03.2026";
        String endDate = "01.03.2026";
        filterPage.checkFilterFormIsLoaded();
        filterPage.enterStartDate(startDate);
        filterPage.enterEndDate(endDate);
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkEmptyListAndRefreshButtonVisible();
    }

    @Test
    @DisplayName("Выбор даты через календарь")
    @Description("Проверка выбора даты в системном календаре и отображения текста в поле")
    public void choiceStartDateSelectionInCalendar_92() throws Exception {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        filterPage.checkFilterFormIsLoaded();
        filterPage.openStartDateCalendar();
        calendarPage.setDate(tomorrow);
        filterPage.checkStartDateFieldHasText(String.valueOf(tomorrow.getDayOfMonth()));
    }

    @Test
    @DisplayName("Отмена выбора даты в календаре")
    @Description("Проверка, что поле остается пустым после отмены в календаре")
    public void cancelStartDateSelectionInCalendar_93() throws Exception {
        filterPage.checkFilterFormIsLoaded();
        filterPage.openStartDateCalendar();
        calendarPage.cancelDate(LocalDate.now().plusDays(2));
        filterPage.checkStartDateFieldIsEmpty();
    }

    @Test
    @DisplayName("Фильтрация по статусу: ACTIVE")
    @Description("Снятие чекбокса Not Active и проверка результата")
    public void checkboxFilteringIsActive_96() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.clickNotActiveCheckbox();
        filterPage.checkActiveCheckboxChecked();
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFirstNewsStatusIfPresent("ACTIVE");
    }

    @Test
    @DisplayName("Фильтрация по статусу: NOT ACTIVE")
    @Description("Снятие чекбокса Active и проверка результата")
    public void checkboxFilteringIsNotActive_97() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.clickActiveCheckbox();
        filterPage.checkNotActiveCheckboxChecked();
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFirstNewsStatusIfPresent("NOT ACTIVE");
    }
}
