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
        //String clientId = "8b2ac12f-b696-445c-b746-9f10ebee5cdc";
        //String clientSecret = "Oye3rwnjPT9Vu7r3RQKerp5pr2r4KAvQxOsL6s3rg0Pjam0jyMfdMCC10Rrub7Td";
        //String code = "def50200ac06cadf7a713bc266a4f408f7b3ec363d725db14a901c79f1b20ed7afba4adc9ffe210f96c818b17ae88a2fe605edeb4cf7989b3cb8e590ad53d9ad9e70c0dd8369be3c496e1c45e6070d52f476a1c720748197b1b44f3d889b395990a7d9860017983bf67caa3a20c9978558bca507107fc36de75e80665e21600c5d6c9087a8c42e3bf53f8205220421eaf906da9032703f0bae7a3d09134c639b3f5cd5ca50be7bcb9f093da60744a4fd39586ecbaeabf7eb398e9cbfdb35721f21e9694eca3b6d32e4fd6c726f858378c9599f77a060c59bd99584da2b29028ada03ea0422a3025207c9aa6fc516f1a77302af1f66355a7770e4db73dbea71e353bbae5143bd557fe9387a64a15c8683d9b2d64bd87e1d671213630e1aa8289005e44450fe74f8efbaa17ef75e0f830ec6f12b28d41d41354a5060d62fdd052e98af23491e786463ab2b1e79f8964e518414abd18f8c4dfc0a5e2a0b172e40e3b18a89b5809bb04b6d1b534a3caa48715b6f09fe72569a709976324f75d40c88b644fcc7d16acb299b35d9a9163499c9233b48d546fd150319ba87506f743faaff2018970aebd6fc9080111687625a906c1b439d274226770c6f2c7df307a9a9c7fcd669f64e5b58e2165b41eb0bb44ef87bb746eafddeca";
        //amoService.ExchangeAuthorizationCodeForAccessTokenAndRefreshToken(clientId, clientSecret, code);
        //amoService.RefreshAccessToken(clientId, clientSecret);
        amoService.createLeads();
        //System.out.println(amoService.getLeads());
        System.out.println(amoService.getPipelines());
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
