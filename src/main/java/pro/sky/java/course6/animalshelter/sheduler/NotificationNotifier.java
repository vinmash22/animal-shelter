package pro.sky.java.course6.animalshelter.sheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.AnimalRepository;
import pro.sky.java.course6.animalshelter.service.AnimalService;

import java.util.Date;

@EnableScheduling
@Component
public class NotificationNotifier {
    private final TelegramBot telegramBot;
    private final AnimalRepository animalRepository;
    private final AnimalService animalService;

    public NotificationNotifier(TelegramBot telegramBot, AnimalRepository animalRepository, AnimalService animalService) {
        this.telegramBot = telegramBot;
        this.animalRepository = animalRepository;
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
                        } else if (animal.getReportText().equals("text, photo")) {
                            animal.setReportText(null);
                            animalService.createAnimal(animal);
                        }
                        if (days >= 1  && days<=3) {
                            animal.setReportText("the trial period has been completed successfully");
                            animalService.createAnimal(animal);
                            telegramBot.execute(new SendMessage(user.getChatId(), "Поздравляем, Вы прошли испытательный срок!"));
                        }

                    }
                });

    }

//    public void trialPeriod() {
//        Date date = new Date();
//        animalRepository.findAll()
//                .forEach(animal -> {
//                    User user = animal.getUser();
//                    Date date1 = animal.getDate();
//                    int days = (int) ((date.getTime() - date1.getTime()) / (60 * 1000));
//                    if (days >= 1 ) {
//                        telegramBot.execute(new SendMessage(user.getChatId(), "Поздравляем, Date прошли испытательный срок!"));
//                    }
//                });
//
//    }
}
