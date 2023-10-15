package pro.sky.java.course6.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course6.animalshelter.configuration.Buttons;
import pro.sky.java.course6.animalshelter.configuration.Info;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.service.AnimalService;
import pro.sky.java.course6.animalshelter.service.UserService;


import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Данный класс обрабатывает сообщения телеграм-бота
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private Menu menu;
    private final AnimalService animalService;
    private final UserService userService;
    public TelegramBotUpdatesListener(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }



    /**
     * Инициализируем телеграмм бота
     */
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Считывает данные из консоли и возвращает текст
     *
     * @param updates
     * @return UpdatesListener.CONFIRMED_UPDATES_ALL
     */


    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null && update.message().text() != null) {
                var text = update.message().text();
                Long chatId = update.message().chat().id();
                if ("/start".equals(text)) {
                    User user = userService.findUserByChatId(chatId);
                   if (user == null) {
                       menu.sendPhoto(chatId, "/animal-shelter_menu.jpg");
                       sendMessageStart(chatId , Info.HELLO.getText());
                        userService.createUser(user);
                    } else {
                       sendMessageStart(chatId , "Выберите приют");
                                         }
                }
                 else {
                   var textID = update.message().text();
                    int animalID = Integer.parseInt(textID);
                    Animal animal = new Animal();
                    //    animalService.findAnimal(animalID);
//                    animal.setReport_text("отчёт");
//                    animal.setAge(animalID);
//                    animalService.createAnimal(animal);
//                    telegramBot.execute(new SendMessage(id_chat, "Опишите: " +
//                            "рацион животного. " +
//                            "Общее самочувствие и привыкание к новому месту. " +
//                            "Изменение в поведении: отказ от старых привычек, приобретение новых. " +
//                            "Прикрепите фото."));
                }

            }

            if (update.callbackQuery() != null) {
                Long chatId = update.callbackQuery().message().chat().id();
                CallbackQuery callbackQuery = update.callbackQuery();
                String data = callbackQuery.data();

                if (data.equals(Buttons.START_CAT.getText())) {
                    menu.sendMessageMenu(chatId, Info.HELLO_CAT.getText());
                } else if (data.equals(Buttons.START_DOG.getText())) {
                    menu.sendMessageMenu(chatId, Info.HELLO_DOG.getText());

                } else if (data.equals(Buttons.SECOND_MENU_INFO_CAT.getText())) {
                    menu.sendMessageMenu(chatId, Info.CAT_SHELTER.getText());
                    menu.sendPhoto(chatId, "/planCats.jpg");
                    menu.returnMainMenu(chatId, Buttons.RETURN_TO_MAIN_MENU.getText());

                } else if (data.equals(Buttons.SECOND_MENU_INFO_DOG.getText())) {
                    menu.sendMessageMenu(chatId, Info.DOG_SHELTER.getText());
                    menu.sendPhoto(chatId, "/planDogs.jpg");
                    menu.returnMainMenu(chatId, Buttons.RETURN_TO_MAIN_MENU.getText());

                } else if (data.equals(Buttons.SECOND_MENU_REPORT.getText())) {
                    menu.sendMessageMenu(chatId, "Напишите id животного");

                } else if (data.equals(Buttons.SECOND_MENU_VOLUNTEER.getText())) {
                    menu.sendMessageMenu(chatId, Info.DEVELOPMENT.getText());

                } else if (data.equals(Buttons.SECOND_MENU_TAKE_CAT.getText())) {
                    menu.sendMessageMenu(chatId, Buttons.START_CAT.getText());

                } else if (data.equals(Buttons.SECOND_MENU_TAKE_DOG.getText())) {
                    menu.sendMessageMenu(chatId, Buttons.START_DOG.getText());

                } else if (data.equals(Buttons.RETURN_TO_MAIN_MENU.getText())) {
                    sendMessageStart(chatId, Buttons.MAIN_MENU.getText());

                } else if (data.equals(Buttons.THIRD_MENU_RULES_CAT.getText())) {
                    menu.returnMainMenu(chatId, Info.RULES_CAT.getText());

                } else if (data.equals(Buttons.THIRD_MENU_RULES_DOG.getText())) {
                    menu.returnMainMenu(chatId, Info.RULES_DOG.getText());

                } else if (data.equals(Buttons.THIRD_MENU_ACCEPT.getText())) {

                    menu.returnMainMenu(chatId, Info.DEVELOPMENT.getText());

                } else if (data.equals(Buttons.THIRD_MENU_LIST.getText())) {
                    menu.returnMainMenu(chatId, Info.DOCUMENTS.getText());

                }else if (data.equals(Buttons.THIRD_MENU_QUESTIONS_CAT.getText())) {
                    menu.returnMainMenu(chatId, Info.QUESTIONS_CAT.getText());

                }else if (data.equals(Buttons.THIRD_MENU_QUESTIONS_DOG.getText())) {
                    menu.returnMainMenu(chatId, Info.QUESTIONS_DOG.getText());

                }

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    private void sendMessageStart(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(menu.createButtonsStart());
        telegramBot.execute(sendMessage);
    }

}








