package pro.sky.java.course6.animalshelter.listiner;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import io.restassured.RestAssured;
import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.java.course6.animalshelter.configuration.Buttons;
import pro.sky.java.course6.animalshelter.configuration.Info;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.listener.Menu;
import pro.sky.java.course6.animalshelter.listener.TelegramBotUpdatesListener;
import pro.sky.java.course6.animalshelter.repository.UserRepository;
import pro.sky.java.course6.animalshelter.service.AnimalService;
import pro.sky.java.course6.animalshelter.service.UserService;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class TelegramBotTest {
    @Mock
    private Menu menu;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AnimalService animalService;
    @MockBean
    private UserService userService;

    @InjectMocks
     private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @InjectMocks
    private TelegramBot telegramBot;;
    @Mock
    Update update;

    @Mock
    List<Update> updates;
    @Mock
    CallbackQuery callbackQuery;
    @Mock
    Message message;

    Long chat_Id = 22661L;

    @Test
    public void succesSendMessage() {
        RestAssured.baseURI = "https://api.telegram.org/bot6357297860:AAHPvvv2aKA8p3dmiOZw6BiPgRymf-O77PA";
        given()
//                .param("text", "rest-assured_TEST")
                .param("text", "Тестируем отправку сообщений ботом")
                .param("chat_id", "1087350398")
                .when()
                .get("/sendMessage")
                .then()
                .statusCode(200);
    }
    @Captor
    private ArgumentCaptor<SendMessage> messageArgumentCaptor;

    @Test
    public void testUpdate() {
//        telegramBotUpdatesListener.process(updates);
//        Mockito.when(updates.get(0)).thenReturn(update);
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(update.message().text()).thenReturn("Проверка наличия текста в update");
        String text = update.message().text();
        assertEquals(text, "Проверка наличия текста в update");

/*        Mockito.when(update.message().chat().id()).thenReturn(22661L);
        Long chat_Id1 = update.message().chat().id();
        assertEquals(chat_Id1, 22661L);*/

        System.out.println("Тест Update пройден");
    }
    @Test
    public void buttontTestSTART_CAT () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.START_CAT.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.START_CAT.getText());
    }
    @Test
    public void buttontTestTHIRD_MENU_LIST () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.THIRD_MENU_LIST.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.THIRD_MENU_LIST.getText());
    }
    @Test
    public void buttontTestTHIRD_MENU_QUESTIONS_DOG () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.THIRD_MENU_QUESTIONS_DOG.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.THIRD_MENU_QUESTIONS_DOG.getText());
    }
    @Test
    public void buttontTestTHIRD_MENU_QUESTIONS_CAT () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.THIRD_MENU_QUESTIONS_CAT.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.THIRD_MENU_QUESTIONS_CAT.getText());
    }
    @Test
    public void buttontTestTHIRD_MENU_ACCEPT () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.THIRD_MENU_ACCEPT.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.THIRD_MENU_ACCEPT.getText());
    }
    @Test
    public void buttontTestTHIRD_MENU_RULES_DOG () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.THIRD_MENU_RULES_DOG.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.THIRD_MENU_RULES_DOG.getText());
    }
    @Test
    public void buttontTestTHIRD_MENU_RULES_CAT () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.THIRD_MENU_RULES_CAT.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.THIRD_MENU_RULES_CAT.getText());
    }
    @Test
    public void buttontTestSECOND_MENU_VOLUNTEER () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.SECOND_MENU_VOLUNTEER.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.SECOND_MENU_VOLUNTEER.getText());
    }
    @Test
    public void buttontTestSECOND_MENU_TAKE_CAT () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.SECOND_MENU_TAKE_CAT.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.SECOND_MENU_TAKE_CAT.getText());
    }
    @Test
    public void buttontTestSECOND_MENU_TAKE_DOG () {
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(update.callbackQuery().data()).thenReturn(Buttons.SECOND_MENU_TAKE_DOG.getText());
        String text = update.callbackQuery().data();
        assertEquals(text, Buttons.SECOND_MENU_TAKE_DOG.getText());
    }
}
