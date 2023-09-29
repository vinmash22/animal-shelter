package pro.sky.java.course6.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course6.animalshelter.configuration.Info;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Данный класс обрабатывает сообщения телеграм-бота
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

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
            if (update.callbackQuery() != null) {
                Long chatId = update.callbackQuery().message().chat().id();
                CallbackQuery callbackQuery = update.callbackQuery();
                String data = callbackQuery.data();
                switch (data) {
                    case "ПРИЮТ ДЛЯ КОШЕК":
                        sendMessageCat(chatId, Info.HELLO_CAT.getText());
                        break;
                    case "ПРИЮТ ДЛЯ СОБАК":
                        sendMessageDog(chatId, Info.HELLO_DOG.getText());
                        break;
                    case "Правила для собак":
                        sendMessageDogMenu(chatId, Info.ROOLES_DOG.getText());
                        break;
                    case "Документы для собак":
                        sendMessageDogMenu(chatId, Info.DOCUMENTS_DOG.getText());
                        break;
                    case "Правила для кошек":
                        sendMessageCatMenu(chatId, Info.ROOLES_CAT.getText());
                        break;
                    case "Документы для кошек":
                        sendMessageCatMenu(chatId, Info.DOCUMENTS_CAT.getText());
                        break;
                }
            }
            if (update.message() != null && update.message().text() != null) {
                var text = update.message().text();
                Long chatId = update.message().chat().id();
                if ("/start".equals(text)) {
                    try {
                        byte[] photo = Files.readAllBytes(
                                Paths.get(TelegramBotUpdatesListener.class.getResource
                                        ("/animal-shelter_menu.jpg").toURI()));
                        sendPhotoStart(chatId, photo);
                        sendMessageStart(chatId, Info.HELLO.getText());
                    } catch (IOException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private Keyboard createButtonsStart() {
        InlineKeyboardButton button1 = new InlineKeyboardButton("ПРИЮТ ДЛЯ КОШЕК");
        button1.callbackData("ПРИЮТ ДЛЯ КОШЕК");
        InlineKeyboardButton button2 = new InlineKeyboardButton("ПРИЮТ ДЛЯ СОБАК");
        button2.callbackData("ПРИЮТ ДЛЯ СОБАК");
        Keyboard keyboard = new InlineKeyboardMarkup(button1, button2);
        return keyboard;
    }

    private InlineKeyboardMarkup createButtonsCatMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила для кошек");
        button1.callbackData("Правила для кошек");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Документы для кошек");
        button2.callbackData("Документы для кошек");
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        return inlineKeyboardMarkup;
    }


    private InlineKeyboardMarkup createButtonsDogMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила для собак");
        button1.callbackData("Правила для собак");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Документы для собак");
        button2.callbackData("Документы для собак");
        inlineKeyboardMarkup.addRow(button1);
        inlineKeyboardMarkup.addRow(button2);
        return inlineKeyboardMarkup;
    }

    private void sendMessageStart(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(createButtonsStart());
        telegramBot.execute(sendMessage);
    }

    private void sendMessageCat(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(createButtonsCatMenu());
        telegramBot.execute(sendMessage);
    }

    private void sendMessageDog(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(createButtonsDogMenu());
        telegramBot.execute(sendMessage);
    }

    private void sendMessageDogMenu(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
         telegramBot.execute(sendMessage);
    }
    private void sendMessageCatMenu(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
    }

    private void sendPhotoStart(long chatId, byte[] photo) {
        SendPhoto sendPhoto = new SendPhoto(chatId, photo);
        sendPhoto.caption("ПРИВЕТСТВУЕМ НОВОГО ПОЛЬЗОВАТЕЛЯ!");
        telegramBot.execute(sendPhoto);
    }


}








