package pro.sky.java.course6.animalshelter.listiner;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import static io.restassured.RestAssured.given;

//@SpringBootTest
public class TelegramBotTest {
    @AfterEach
    void doAny(){
        System.out.println("AFTER");
    }

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

}
