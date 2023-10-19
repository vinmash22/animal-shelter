package pro.sky.java.course6.animalshelter.configuration;

public enum Buttons {
    START_CAT("ПРИЮТ ДЛЯ КОШЕК"),
    START_DOG("ПРИЮТ ДЛЯ СОБАК"),

    SECOND_MENU_INFO_CAT("Информация о приюте кошек"),
    SECOND_MENU_INFO_DOG("Информация о приюте собак"),
    SECOND_MENU_TAKE_CAT("Взять кошку из приюта"),
    SECOND_MENU_TAKE_DOG("Взять собаку из приюта"),
    SECOND_MENU_REPORT("Прислать отчет о питомце"),
    SECOND_MENU_VOLUNTEER("Позвать волонтера"),

    THIRD_MENU_RULES_CAT(" Рекомендации и правила "),
    THIRD_MENU_RULES_DOG("Рекомендации и правила"),

    THIRD_MENU_ACCEPT("Принять контактные даные для связи"),
    THIRD_MENU_LIST("Список документов"),
    THIRD_MENU_QUESTIONS_CAT("Ответить на вопросы"),
    THIRD_MENU_QUESTIONS_DOG("Ответить на вопросы"),


    MAIN_MENU("ГЛАВНОЕ МЕНЮ"),
    RETURN_TO_MAIN_MENU("Вернуться в главое меню"),

    RULES_MENU_ACQUAINTANCE("Правила знакомства с животным"),
    RULES_MENU_DOCUMENTS("Список документов, чтобы взять животное из приюта"),
    RULES_MENU_TRANSPORT("Рекомендации по транспортировке животного"),
    RULES_MENU_HOME_CAT("Рекомендации по обустройству дома для котенка"),
    RULES_MENU_HOME_DOG("Рекомендации по обустройству дома для щенка"),
    RULES_MENU_ADULT("Рекомендации по обустройству дома для взрослого животного"),
    RULES_MENU_DISABILITIES("Рекомендации по обустройству дома для животного c ограниченными возможностями"),
    RULES_MENU_ADVICES_DOG_HANDLER("Cоветы кинолога"),
    RULES_MENU_DOG_HANDLERS("Рекомендации по кинологам"),
    RULES_MENU_REFUSAL("Причины отказа в выдаче животного"),
    CREATE_AND_SEND_CONTACT("Принять контактные данные"),
    CANSEL("Отменить")




    ;
    private final String text;

    Buttons(String text) {
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
