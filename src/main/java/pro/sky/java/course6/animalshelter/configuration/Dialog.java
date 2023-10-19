package pro.sky.java.course6.animalshelter.configuration;

public enum Dialog {

    SET_NAME_LAST_NAME("Введите, пожалуйста, ИМЯ И ФАМИЛИЮ:"),
    SET_PHONE_NUMBER("Введите, пожалуйста, НОМЕР ТЕЛЕФОНА:"),
    FINISH_REGISTER("Регистрация завершена")




    ;
    private final String text;

    Dialog(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
