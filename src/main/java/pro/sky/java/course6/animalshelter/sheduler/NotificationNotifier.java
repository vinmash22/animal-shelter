package pro.sky.java.course6.animalshelter.sheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.AnimalRepository;
import pro.sky.java.course6.animalshelter.repository.UserRepository;
import pro.sky.java.course6.animalshelter.service.AnimalService;

import java.util.Date;

@EnableScheduling
@Component
public class NotificationNotifier {
    private final TelegramBot telegramBot;
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;
    private final AnimalService animalService;

    public NotificationNotifier(TelegramBot telegramBot, AnimalRepository animalRepository, UserRepository userRepository, AnimalService animalService) {
        this.telegramBot = telegramBot;
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
        this.animalService = animalService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void notifyTask() {
        Date date = new Date();
        animalRepository.findAll()
                .forEach(animal -> {
                    Date date1 = animal.getDate();
                    int days = (int) ((date.getTime() - date1.getTime()) / (60 * 1000));
                    User user = animal.getUser();
                    if (user.getId() != 1) {
                        if (animal.getReportText() == null || animal.getReportText().equals("text")
                                || animal.getReportText().equals("string") || animal.getReportText().equals("photo")) {
                            telegramBot.execute(new SendMessage(user.getChatId(), "Дорогой усыновитель, мы не получили отчет, либо отчет был не полным. Пожалуйста, подойди ответственнее к этому занятию." +
                                    " В противном случае волонтеры приюта будут обязаны самолично " +
                                    "проверять условия содержания животного"));
                            animal.setReportText(null);
                            animalService.createAnimal(animal);
                            if (days == 2) {
                                userRepository.findAll()
                                        .forEach(user1 -> {
                                            if (user1.getRole().equals("volunteer")) {
                                                telegramBot.execute(new SendMessage(user1.getChatId(), "У животного " + animal.getId() + " завтра истекает половина испытательного срока"));
                                            }
                                        });
                            } else if (days == 3) {
                                userRepository.findAll()
                                        .forEach(user1 -> {
                                            if (user1.getRole().equals("volunteer")) {
                                                telegramBot.execute(new SendMessage(user1.getChatId(), "У животного " + animal.getId() + " завтра истекает испытательный срок"));
                                            }
                                        });
                            } else if (days == 4) {
                                animal.setReportText("the trial period has been completed successfully");
                                animalService.createAnimal(animal);
                                telegramBot.execute(new SendMessage(user.getChatId(), "Поздравляем, Вы прошли испытательный срок!"));
                            }
                        } else if (animal.getReportText().equals("text, photo")) {
                            animal.setReportText(null);
                            animalService.createAnimal(animal);
                            if (days == 2) {
                                userRepository.findAll()
                                        .forEach(user1 -> {
                                            if (user1.getRole().equals("volunteer")) {
                                                telegramBot.execute(new SendMessage(user1.getChatId(), "У животного " + animal.getId() + " завтра истекает половина испытательного срока"));
                                            }
                                        });
                            } else if (days == 3) {
                                userRepository.findAll()
                                        .forEach(user1 -> {
                                            if (user1.getRole().equals("volunteer")) {
                                                telegramBot.execute(new SendMessage(user1.getChatId(), "У животного " + animal.getId() + " завтра истекает испытательный срок"));
                                            }
                                        });
                            } else if (days == 4) {
                                animal.setReportText("the trial period has been completed successfully");
                                animalService.createAnimal(animal);
                                telegramBot.execute(new SendMessage(user.getChatId(), "Поздравляем, Вы прошли испытательный срок!"));
                            }

                        }
                    }
                });

    }

}
