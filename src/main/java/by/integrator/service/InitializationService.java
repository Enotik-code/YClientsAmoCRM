package by.integrator.service;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class InitializationService {


    @Autowired
    private AmoService amoService;


    public void initialize() throws IOException, ParseException, InterruptedException {
        /* List<User> allUsersFromMyClass = myClassService.getAllUser();
        while(true) {
            List<Result> allUsersFromTalkMe = talkMeService.getAllUsersFromTalkMe();

            for (Result userTalkMe : allUsersFromTalkMe) {
                boolean flag = false;
                for (User userMyClass : allUsersFromMyClass) {
                    if (userTalkMe.getPhone().equals(userMyClass.getPhone())) {
                        try {
                            if (userTalkMe.getTime().isAfter(userMyClass.getTime()) || userMyClass.getTime() == null) {
                                userMyClass.setTime(userTalkMe.getTime());
                                messageService.sendMessage(RequestDispatcher.chatId, messageService.getMessageWhereUpdateUser(userTalkMe));
                            }
                        } catch (Exception ex) {

                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    User user = User.builder()
                            .name(userTalkMe.getName())
                            .phone(userTalkMe.getPhone())
                            .email("email@gmail.com")
                            //.email(userTalkMe.getEmail())
                            .time(userTalkMe.getTime())
                            .build();
                    myClassService.createUser(user);
                    allUsersFromMyClass.add(user);
                    messageService.sendMessage(RequestDispatcher.chatId, messageService.getMessageWhereCreateNewUser(userTalkMe));
                }
            }
            Thread.sleep(3600);
        }*/
    }



   /* public void initializeForAmoCRM() throws IOException, ParseException, InterruptedException {
        List<User> allUsersFromMyClass = myClassService.getAllUser();
        List<User> allUsersFromMyClass = amoService.getAllContactsFromAmo();
        while(true) {
            List<Result> allUsersFromTalkMe = talkMeService.getAllUsersFromTalkMe();

            for (Result userTalkMe : allUsersFromTalkMe) {
                boolean flag = false;
                for (User userMyClass : allUsersFromMyClass) {
                    if (userTalkMe.getPhone().equals(userMyClass.getPhone())) {
                        try {
                            if (userTalkMe.getTime().isAfter(userMyClass.getTime()) || userMyClass.getTime() == null) {
                                userMyClass.setTime(userTalkMe.getTime());
                                messageService.sendMessage(RequestDispatcher.chatId, messageService.getMessageWhereUpdateUser(userTalkMe));
                            }
                        } catch (Exception ex) {

                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    User user = User.builder()
                            .name(userTalkMe.getName())
                            .phone(userTalkMe.getPhone())
                            .email("email@gmail.com")
                            //.email(userTalkMe.getEmail())
                            .time(userTalkMe.getTime())
                            .build();
                    myClassService.createUser(user);
                    allUsersFromMyClass.add(user);
                    messageService.sendMessage(RequestDispatcher.chatId, messageService.getMessageWhereCreateNewUser(userTalkMe));
                }
            }
            Thread.sleep(3600);
        }
    }
*/



}
