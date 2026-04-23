package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import ru.iteco.fmhandroid.R;
public class ControlPanelFilterPage extends NewsFilterPage{


    private final int activeCheckboxId = R.id.filter_news_active_material_check_box;
    private final int notActiveCheckboxId = R.id.filter_news_inactive_material_check_box;

    @Override
    public void checkFilterFormIsLoaded() {
        super.checkFilterFormIsLoaded();
        onView(withId(activeCheckboxId)).check(matches(isDisplayed()));
        onView(withId(notActiveCheckboxId)).check(matches(isDisplayed()));
    }

    public void checkActiveCheckboxChecked() {
        onView(withId(activeCheckboxId)).check(matches(isChecked()));
    }

    public void checkNotActiveCheckboxChecked() {
        onView(withId(notActiveCheckboxId)).check(matches(isChecked()));
    }

    public void clickActiveCheckbox() {
        onView(withId(activeCheckboxId)).perform(click());
    }

    public void clickNotActiveCheckbox() {
        onView(withId(notActiveCheckboxId)).perform(click());
    }

    public void filterByStatus(boolean active, boolean notActive) {
        if (active) clickActiveCheckbox();
        if (notActive) clickNotActiveCheckbox();
        clickFilterSubmit();
    }
}


