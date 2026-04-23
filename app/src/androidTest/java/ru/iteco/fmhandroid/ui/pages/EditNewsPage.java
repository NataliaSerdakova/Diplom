package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.Matchers.allOf;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;

import ru.iteco.fmhandroid.R;

public class EditNewsPage extends BasePage {

    private final CalendarPage calendarPage = new CalendarPage();

    private final TimePage timePage = new TimePage();
    // Константы ID для элементов формы
    private final int categoryFieldId = R.id.news_item_category_text_auto_complete_text_view;
    private final int titleFieldId = R.id.news_item_title_text_input_edit_text;
    private final int dateFieldId = R.id.news_item_publish_date_text_input_edit_text;
    private final int timeFieldId = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionFieldId = R.id.news_item_description_text_input_edit_text;
    private final int switcherId = R.id.switcher; // Переключатель "Active"
    private final int saveBtnId = R.id.save_button;
    public static final String EMPTY_FIELDS_ERROR = "Fill empty fields";







    // Проверка, что кнопка Save все еще видна (значит форма не закрыла

    public void checkNewsDate(String date) {
        onView(withText(date)).check(matches(isDisplayed()));
    }


}
