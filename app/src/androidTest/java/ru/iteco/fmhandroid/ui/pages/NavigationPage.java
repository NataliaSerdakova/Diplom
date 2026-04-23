package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class NavigationPage extends BasePage {
    private final int mainMenuBtnId = R.id.main_menu_image_button;
    private final String menuMainText = "Main";
    private final String menuNewsText = "News";
    private final String menuAboutText = "About";
    private final int newsContainerId = R.id.all_news_cards_block_constraint_layout;
    private final int aboutVersionId = R.id.about_version_value_text_view;
    private final int aboutPrivacyPolicyId = R.id.about_privacy_policy_value_text_view;
    private final int aboutTermsOfUseId = R.id.about_terms_of_use_value_text_view;
    private final int aboutBackBtnId = R.id.about_back_image_button;
    private final int newsListOnMainId = R.id.container_list_news_include_on_fragment_main;
    private final int allNewsLinkId = R.id.all_news_text_view;
    private final int butterflyBtnId = R.id.our_mission_image_button;
    private final int quotesTitleId = R.id.our_mission_title_text_view;
    private final int quotesListId = R.id.our_mission_item_list_recycler_view;

    public void openNavigationMenu() {
        onView(isRoot()).perform(waitDisplayed(mainMenuBtnId, SHORT_TIMEOUT));
        onView(withId(mainMenuBtnId)).perform(click());
    }

    public void clickNews() {
        onView(withText(menuNewsText)).perform(click());
    }

    public void clickAbout() {
        onView(withText(menuAboutText)).perform(click());
    }

    public void clickMain() {
        onView(withText(menuMainText)).perform(click());
    }

    public void checkIsNewsPage() {
        onView(isRoot()).perform(waitDisplayed(newsContainerId, SHORT_TIMEOUT));
        onView(withId(newsContainerId)).check(matches(isDisplayed()));
    }

    public void checkIsAboutPage() {
        onView(isRoot()).perform(waitDisplayed(aboutVersionId, SHORT_TIMEOUT));
        onView(withId(aboutVersionId)).check(matches(isDisplayed()));
        onView(withId(aboutPrivacyPolicyId)).check(matches(isDisplayed()));
        onView(withId(aboutTermsOfUseId)).check(matches(isDisplayed()));
    }

    public void checkMenuElementsDisplayed() {
        onView(isRoot()).perform(waitTextDisplayed(menuNewsText, SHORT_TIMEOUT));
        onView(withText(menuMainText)).check(matches(isDisplayed()));
        onView(withText(menuNewsText)).check(matches(isDisplayed()));
        onView(withText(menuAboutText)).check(matches(isDisplayed()));
    }

    public void clickBack() {
        onView(isRoot()).perform(waitDisplayed(aboutBackBtnId, SHORT_TIMEOUT));
        onView(withId(aboutBackBtnId)).perform(click());
    }

    public void checkIsMainPage() {
        onView(isRoot()).perform(waitDisplayed(newsListOnMainId, SHORT_TIMEOUT));
        onView(withId(newsListOnMainId)).check(matches(isDisplayed()));
        onView(withId(allNewsLinkId)).check(matches(isDisplayed()));
    }

    public void clickButterfly() {
        onView(isRoot()).perform(waitDisplayed(butterflyBtnId, SHORT_TIMEOUT));
        onView(withId(butterflyBtnId)).perform(click());
    }

    public void checkIsQuotesPage() {
        onView(isRoot()).perform(waitDisplayed(quotesTitleId, SHORT_TIMEOUT));
        onView(withId(quotesListId)).check(matches(isDisplayed()));
    }

    public void clickAllNewsLink() {
        onView(isRoot()).perform(waitDisplayed(allNewsLinkId, SHORT_TIMEOUT));
        onView(withId(allNewsLinkId)).perform(click());
    }
}
