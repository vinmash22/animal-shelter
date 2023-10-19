package pro.sky.java.course6.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;

import pro.sky.java.course6.animalshelter.configuration.Buttons;
import pro.sky.java.course6.animalshelter.configuration.Info;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Menu {

    private final TelegramBot telegramBot;

    public Menu(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    /**
     * Стартовое меню
     */
    public Keyboard createButtonsStart() {
        InlineKeyboardButton button1 = new InlineKeyboardButton(Buttons.START_CAT.getText());
        button1.callbackData(Buttons.START_CAT.getText());
        InlineKeyboardButton button2 = new InlineKeyboardButton(Buttons.START_DOG.getText());
        button2.callbackData(Buttons.START_DOG.getText());
        Keyboard keyboard = new InlineKeyboardMarkup(button1, button2);
        return keyboard;
    }

    /**
     * Второе меню
     */
    public InlineKeyboardMarkup createSecondMenuCat() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton(Buttons.SECOND_MENU_INFO_CAT.getText());
        button1.callbackData(Buttons.SECOND_MENU_INFO_CAT.getText());
        InlineKeyboardButton button2 = new InlineKeyboardButton(Buttons.SECOND_MENU_TAKE_CAT.getText());
        button2.callbackData(Buttons.SECOND_MENU_TAKE_CAT.getText());
        InlineKeyboardButton button3 = new InlineKeyboardButton(Buttons.SECOND_MENU_REPORT.getText());
        button3.callbackData(Buttons.SECOND_MENU_REPORT.getText());
        InlineKeyboardButton button4 = new InlineKeyboardButton(Buttons.SECOND_MENU_VOLUNTEER.getText());
        button4.callbackData(Buttons.SECOND_MENU_VOLUNTEER.getText());
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        inlineKeyboardMarkup.addRow(button4);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createSecondMenuDog() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton(Buttons.SECOND_MENU_INFO_DOG.getText());
        button1.callbackData(Buttons.SECOND_MENU_INFO_DOG.getText());
        InlineKeyboardButton button2 = new InlineKeyboardButton(Buttons.SECOND_MENU_TAKE_DOG.getText());
        button2.callbackData(Buttons.SECOND_MENU_TAKE_DOG.getText());
        InlineKeyboardButton button3 = new InlineKeyboardButton(Buttons.SECOND_MENU_REPORT.getText());
        button3.callbackData(Buttons.SECOND_MENU_REPORT.getText());
        InlineKeyboardButton button4 = new InlineKeyboardButton(Buttons.SECOND_MENU_VOLUNTEER.getText());
        button4.callbackData(Buttons.SECOND_MENU_VOLUNTEER.getText());
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        inlineKeyboardMarkup.addRow(button4);
        return inlineKeyboardMarkup;
    }

    /**
     * Меню - Как взять животное из приюта
     */

    public InlineKeyboardMarkup createMenuTakeCat() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton(Buttons.THIRD_MENU_RULES_CAT.getText());
        button1.callbackData(Buttons.THIRD_MENU_RULES_CAT.getText());
        InlineKeyboardButton button2 = new InlineKeyboardButton(Buttons.THIRD_MENU_ACCEPT.getText());
        button2.callbackData(Buttons.THIRD_MENU_ACCEPT.getText());
        InlineKeyboardButton button3 = new InlineKeyboardButton(Buttons.THIRD_MENU_LIST.getText());
        button3.callbackData(Buttons.THIRD_MENU_LIST.getText());
        InlineKeyboardButton button4 = new InlineKeyboardButton(Buttons.THIRD_MENU_QUESTIONS_CAT.getText());
        button4.callbackData(Buttons.THIRD_MENU_QUESTIONS_CAT.getText());
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        inlineKeyboardMarkup.addRow(button4);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createMenuTakeDog() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton(Buttons.THIRD_MENU_RULES_DOG.getText());
        button1.callbackData(Buttons.THIRD_MENU_RULES_DOG.getText());
        InlineKeyboardButton button2 = new InlineKeyboardButton(Buttons.THIRD_MENU_ACCEPT.getText());
        button2.callbackData(Buttons.THIRD_MENU_ACCEPT.getText());
        InlineKeyboardButton button3 = new InlineKeyboardButton(Buttons.THIRD_MENU_LIST.getText());
        button3.callbackData(Buttons.THIRD_MENU_LIST.getText());
        InlineKeyboardButton button4 = new InlineKeyboardButton(Buttons.THIRD_MENU_QUESTIONS_DOG.getText());
        button4.callbackData(Buttons.THIRD_MENU_QUESTIONS_DOG.getText());
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        inlineKeyboardMarkup.addRow(button3);
        inlineKeyboardMarkup.addRow(button4);
        return inlineKeyboardMarkup;

    }

    public void returnMainMenu(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton(Buttons.RETURN_TO_MAIN_MENU.getText());
        button.callbackData(Buttons.RETURN_TO_MAIN_MENU.getText());
        sendMessage.replyMarkup(inlineKeyboardMarkup.addRow(button));
        telegramBot.execute(sendMessage);
    }



    public void sendMessageMenu(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        if (message.equals(Info.HELLO_CAT.getText())) {
            sendMessage.replyMarkup(createSecondMenuCat());
        } else if (message.equals(Info.HELLO_DOG.getText())) {
            sendMessage.replyMarkup(createSecondMenuDog());
        } else if (message.equals(Buttons.START_CAT.getText())) {
            sendMessage.replyMarkup(createMenuTakeCat());
        } else if (message.equals(Buttons.START_DOG.getText())) {
            sendMessage.replyMarkup(createMenuTakeDog());
        }
        telegramBot.execute(sendMessage);
    }

    public void sendPhoto(long chatId, String namePhoto) {
        try {
            byte[] photo = Files.readAllBytes(
                    Paths.get(TelegramBotUpdatesListener.class.getResource
                            (namePhoto).toURI()));
            SendPhoto sendPhoto = new SendPhoto(chatId, photo);

            if (namePhoto.equals("/animal-shelter_menu.jpg")) {
                sendPhoto.caption(Info.HI_NEW_USER.getText());
            } else if (namePhoto.equals("/planCats.jpg")) {
                sendPhoto.caption(Info.PLAN_NAME.getText());
            }
            telegramBot.execute(sendPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
