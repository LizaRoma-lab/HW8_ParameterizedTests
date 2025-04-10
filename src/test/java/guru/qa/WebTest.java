package guru.qa;

import guru.qa.data.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @EnumSource(Language.class)
    @ParameterizedTest
    void fonbetSiteShouldDisplayCorrectTest(Language language) {
        open("https://fon.bet/");
        $("[data-testid='btn.languages']").click();
        $$("[data-testid='dd.language']").find(text(language.name())).click();
        $("[data-testid='btn.logIn']").shouldHave(text(language.description));
    }


    @ValueSource(strings = {
            "Циципас",
            "Филс",
            "Алькарас"
    })
    @ParameterizedTest
    void successfulSearchTest(String testData) {
        open("https://fon.bet/");
        $("[data-testid='btn.search']").click();

        $("[data-testid=stringEdit]").setValue(testData);
        $("[data-testid=teams]").shouldHave(text(testData));
        sleep(5000);
    }


    @DisplayName("Негативный тест на вход в систему")
    @Tag("WEB")
    @ParameterizedTest
    @CsvSource({
            "79123456789, invalidPassword, Логин или пароль указан неверно",
            "invalidEmail, randomPassword, Логин или пароль указан неверно"
    })
    void unsuccessfulLogInWithPhoneNumber(String userPhoneNumber, String password, String result) {
        open("https://fon.bet/");
        $("[data-testid='btn.logIn']").click();
        $("[name=login]").setValue(userPhoneNumber);
        $("[type=password]").setValue(password).pressEnter();
        $(".text--kseTA").shouldHave(text(result));
    }


}


