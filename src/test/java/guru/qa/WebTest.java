package guru.qa;

import guru.qa.data.Language;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

    @Test
    @DisplayName("Позитивный тест на поиск")
    @Tag("WEB")
    void successfulSearchTest() {
        open("https://fon.bet/");
        $("[data-testid='btn.search']").click();
        $("[data-testid=stringEdit]").setValue("WTA");
        $("[data-testid=sportCategoryText]").shouldHave(text("WTA"));
    }

    @Disabled("PB-1234")
    @Test
    @DisplayName("Негативный тест на вход в систему")
    @Tag("WEB")
    void unsuccessfulLogInWithPhoneNumber() {
        open("https://fon.bet/");
        $("[data-testid='btn.logIn']").click();
        $("[name=login]").setValue("89631234567");
        $("[type=password]").setValue("Qwerty123").pressEnter();
        $(".text--kseTA").shouldHave(text("Логин или пароль указан неверно."));
    }

}
