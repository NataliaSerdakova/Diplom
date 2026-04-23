package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.LoginPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();

    @Before
    public void setUp() {
        if (loginPage.isLoggedIn()) {
            loginPage.logout();
        }
    }

    @Test
    public void successfulAuthorization_1() {
        loginPage.logInToTheSystem("login2", "password2");
        loginPage.verifyTextIsVisible(LoginPage.MAIN_SCREEN_TITLE);
    }

    @Test
    public void invalidLogin_2() {
        loginPage.logInToTheSystem("test", "password2");
        loginPage.checkErrorToast(LoginPage.ERROR_INVALID_DATA);
    }

    @Test
    public void specialCharactersInLogin_4() {
        loginPage.logInToTheSystem("!@#$%^", "password2");
        loginPage.checkErrorToast(LoginPage.ERROR_INVALID_DATA);
    }

    @Test
    public void emptyLogin_6() {
        loginPage.logInToTheSystem("", "password2");
        loginPage.checkErrorToast(LoginPage.ERROR_EMPTY_FIELDS);
    }

    @Test
    public void loginRegister_9() {
        loginPage.logInToTheSystem("Login2", "password2");
        loginPage.checkErrorToast(LoginPage.ERROR_INVALID_DATA);
    }

    @Test
    public void invalidPassword_10() {
        loginPage.logInToTheSystem("login2", "test");
        loginPage.checkErrorToast(LoginPage.ERROR_INVALID_DATA);
    }

    @Test
    public void specialCharactersInPassword_13() {
        loginPage.logInToTheSystem("login2", "(&$#*@)");
        loginPage.checkErrorToast(LoginPage.ERROR_INVALID_DATA);
    }

    @Test
    public void emptyPassword_15() {
        loginPage.logInToTheSystem("login2", "");
        loginPage.checkErrorToast(LoginPage.ERROR_EMPTY_FIELDS);
    }

    @Test
    public void emptyLoginAndPassword_17() {
        loginPage.logInToTheSystem("", "");
        loginPage.checkErrorToast(LoginPage.ERROR_EMPTY_FIELDS);
    }

    @Test
    public void successfulLogout_18() {
        loginPage.ensureLoggedIn("login2", "password2");
        loginPage.logout();
        loginPage.verifyLoginPageIsDisplayed();
    }
}

