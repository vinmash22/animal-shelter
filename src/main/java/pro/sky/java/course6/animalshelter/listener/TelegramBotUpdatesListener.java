package pro.sky.java.course6.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.java.course6.animalshelter.configuration.Buttons;
import pro.sky.java.course6.animalshelter.configuration.Dialog;
import pro.sky.java.course6.animalshelter.configuration.Info;
import pro.sky.java.course6.animalshelter.configuration.UserState;
import pro.sky.java.course6.animalshelter.service.AnimalService;
import pro.sky.java.course6.animalshelter.service.UserService;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.coyote.http11.Constants.CONNECTION;

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

    public Statement createStatement() throws SQLException {
        return null;
    }

    @Autowired
    private final Map<Long, UserState> states = new HashMap<>();


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

    @Value("${path.to.reports.folder}")
    private String reportsDir;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate date = LocalDate.now();




    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);


            if (update.message() != null && update.message().text() != null) {
                var text = update.message().text();
                Long chatId = update.message().chat().id();
                if ("/start".equals(text)) {
                    menu.sendPhoto(chatId, "/animal-shelter_menu.jpg");
                    sendMessageStart(chatId, Info.HELLO.getText());
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
                    menu.sendMessageMenu(chatId, Info.DEVELOPMENT.getText());

                } else if (data.equals(Buttons.SECOND_MENU_VOLUNTEER.getText())) {
                    menu.sendMessageMenu(chatId, Info.CALL_VOLUNTEER.getText());

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
//                    menu.returnMainMenu(chatId, Info.DEVELOPMENT.getText());
                  /*
                  menu for receiving data
                   */
                    SendMessage sendMessage = new SendMessage(chatId, Dialog.SET_NAME_LAST_NAME.getText());
                    telegramBot.execute(sendMessage);
                    Long userId = update.message().from().id();
                    String message = update.message().text();
                    if (states.containsKey(userId) && states.get(userId).equals(UserState.ENTER_NAME)) {
                        try {
                            saveUserName(userId, message);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        states.put(userId, UserState.ENTER_NAME);
                    } else if (states.containsKey(userId) && states.get(userId).equals(UserState.ENTER_PHONE)) {
                        try {
                            savePhone(userId, message);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        states.remove(userId);
                    }
                    if (update.message().text().equals("/enterUserData")) {
                        states.put(userId, UserState.ENTER_NAME);
                    }

                } else if (data.equals(Buttons.THIRD_MENU_LIST.getText())) {
                    menu.returnMainMenu(chatId, Info.DOCUMENTS.getText());

                } else if (data.equals(Buttons.THIRD_MENU_QUESTIONS_CAT.getText())) {
                    menu.returnMainMenu(chatId, Info.QUESTIONS_CAT.getText());

                } else if (data.equals(Buttons.THIRD_MENU_QUESTIONS_DOG.getText())) {
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

    private void sendMessage(SendMessage message) {
        SendResponse response = telegramBot.execute(message);
        if (response != null && !response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }

    private void saveUserName(Long userId, String message) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION);
//            Statement stmt = connection.createStatement();
            String query="INSERT INTO 'users' (id, name)" + "VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(Math.toIntExact(userId),message);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void savePhone(Long userId, String message) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(CONNECTION);
            String query="INSERT INTO 'users' (id, phone)" + "VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(Math.toIntExact(userId),message);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}








