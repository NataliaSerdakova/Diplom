package ru.iteco.fmhandroid.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class AppActivityTest3 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun appActivityTest3() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.main_menu_image_button), withContentDescription("Main menu"),
                childAtPosition(
                    allOf(
                        withId(R.id.container_custom_app_bar_include_on_fragment_main),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val materialTextView = onView(
            allOf(
                withId(android.R.id.title), withText("News"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.android.internal.view.menu.ListMenuItemView")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.edit_news_material_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_list_news_include),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.news_item_material_card_view),
                        0
                    ),
                    15
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.news_item_publish_time_text_input_edit_text), withText("09:15"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.news_item_publish_time_text_input_layout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatImageButton")),
                withContentDescription("Switch to text input mode for the time input."),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val appCompatEditText = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatEditText")), withText("17"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.TextInputTimePickerView")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("15"))

        val appCompatEditText2 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatEditText")), withText("15"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.TextInputTimePickerView")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatEditText")), withText("15"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.TextInputTimePickerView")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(pressImeActionButton())

        val appCompatEditText4 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatEditText")), withText("50"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.TextInputTimePickerView")),
                            1
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(click())

        val appCompatEditText5 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatEditText")), withText("50"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.TextInputTimePickerView")),
                            1
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("15"))

        val appCompatEditText6 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatEditText")), withText("15"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.TextInputTimePickerView")),
                            1
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
