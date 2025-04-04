package guru.qa.data;

public enum Language {
    RU("Войти"),
    EN("Log in");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
