package by.integrator.service;

import by.integrator.bean.User;
import by.integrator.proxy.AmoServiceProxy;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AmoService {

    @Autowired
    private AmoServiceProxy amoServiceProxy;

    static Logger log = Logger.getLogger(AmoService.class.getName());

    public static String refreshToken = "def5020055e2ffdce85053ea8c053c7b50d3a997e695e9321659ab611c7cdb069cb69c34ac5456e08e035dd45be876495b978267f4b9867f7f37ea686e9e7a6ae8d98c49f86fa45a969f87a66285e2cf3c56edf3eacb88f94d9ace379c5ee06e39ec74acee6b061ecdc64484c5e9b2bc7f16c0a5aecdf6d77d370a46b3c857fda74842ff00807560c2d64496b0350c1398915d9c9f2d188adcea42382a3bd85976e62611b5e271ee8b265e228e55b3d5f6e8f2d2bd0e5df1b12c32abb7dcfe7b22f4e223a7f8c7f7a56df3e0c464940b6b74c90d8264c5023d5ba9fc6432a57dd8e1f6db13e0f3cd6916d9dfc670822e2c72944c7fa702b40c8ae81fba55a82f3aa0f3f504d039248b2f05b09f55122856ffb0ab71641565e515fd22267f53ea748d88fe62c40a18b96b48c3df4650368dad5f6272d0564eecf06f3233656eb68e7720761bfe487950f19fb3677d41ecb59a92b4eda41d3d699ab5267d6f82e8847c03e382becd09dff9d76f33d8dccdf31933aa01d16b855f8ae3a31da6f6aca5a431f0e592698a2df083575b2c3810563f23576ed8f47dd925a83732029492a1e92edc80d09b5bf78d3aaadbd0bc7db6a86fff";
    public static String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJkMTMyZGQ1ZTUzNTIwYmYyODZkMTlmZjZiMTFkYWU4NjUwNDA0ODQ4NDllMjI1MjQ0OTIxNjlkZmQ4N2M1MzJmZDM1NGE3NzY3ZGQ4NmQ0In0.eyJhdWQiOiJlYjFmNDYyMS1hNGZmLTQxNDgtYWY1Zi0yZGMwNDkyZjgwMmQiLCJqdGkiOiJiZDEzMmRkNWU1MzUyMGJmMjg2ZDE5ZmY2YjExZGFlODY1MDQwNDg0ODQ5ZTIyNTI0NDkyMTY5ZGZkODdjNTMyZmQzNTRhNzc2N2RkODZkNCIsImlhdCI6MTU5ODE5NTcyMiwibmJmIjoxNTk4MTk1NzIyLCJleHAiOjE1OTgyODIxMjIsInN1YiI6IjYyNjQyNjIiLCJhY2NvdW50X2lkIjoyOTAxMDU4Niwic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.MfsgQzbN5o-jo6qjZrlg9OcNJzQ9NwFeBYW6rBLvxbIRnWx14QGjYhnKZ6-TkLsRvbiPMEBZ3tD1o4rL-X_AccqV51bJEehJJe6Mji1rI7StAqth88FY8uym9GTNSgAZrJ4h_dDUOQ8loXCcJNg8MxMA_dsEunFckvW2K77hjJBr6pXFrioGpKJz44qfE3HPEHuAeU0ih_-u0tFpPpxmfgdx_mG_jhgc_DUArUKjQo_T9XLwtKH9NgwZMn363j-O-TFEaOkpLAkpF1Nly0hRjp1v7Auqa16fBwwJ8QMlZaKdxjjklHzerse84Knn7QUK43hQAOw4yByIk0DKQLaJkg";

    public String getAccessToken(){
        return "Bearer " + accessToken;
    }

    @SneakyThrows
    public void ExchangeAuthorizationCodeForAccessTokenAndRefreshToken(String clientId, String clientSecret, String code) {
        String bodyForRequest = "{\n" +
                "  \"client_id\": " + clientId + ",\n" +
                "  \"client_secret\": " + clientSecret + ",\n" +
                "  \"grant_type\": \"authorization_code\",\n" +
                "  \"code\": " + code + ",\n" +
                "  \"redirect_uri\": \"https://talk-me.ru/knowledge_base\"\n" +
                "}";
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getAccessAndRefreshToken(bodyForRequest).toJSONString());
        refreshToken = jsonObject.get("refresh_token").toString();
        accessToken = jsonObject.get("access_token").toString();
    }

    @SneakyThrows
    public void RefreshAccessToken(String clientId, String clientSecret) {
        String bodyForRequest = "{\n" +
                "  \"client_id\": " + clientId + ",\n" +
                "  \"client_secret\": " + clientSecret + ",\n" +
                "  \"grant_type\": \"refresh_token\",\n" +
                "  \"refresh_token\": " + refreshToken + ",\n" +
                "  \"redirect_uri\": \"https://test.test\"\n" +
                "}";
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getAccessAndRefreshToken(bodyForRequest).toJSONString());
        refreshToken = jsonObject.get("refresh_token").toString();
        accessToken = jsonObject.get("access_token").toString();
    }

    @SneakyThrows
    public void createLeads(){
        amoServiceProxy.addLeads(getAccessToken(), getBodyForNewLeads());
    }

    @SneakyThrows
    public String getLeads()  {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getAllLeads(getAccessToken()).toJSONString());
        JSONObject embeddedObject = (JSONObject) jsonObject.get("_embedded");
        JSONArray leadsArray = (JSONArray) embeddedObject.get("leads");
        Iterator<JSONObject> iterator = leadsArray.iterator();
        List<String> leadsList = new ArrayList<>();
        while (iterator.hasNext()) {
            iterator.forEachRemaining(el ->
                leadsList.add(el.get("name").toString() + " ID:" + el.get("id").toString() + "\n"));
        }
                return leadsList.toString();
    }

    @SneakyThrows
    public String getPipelines(){
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getPipelines(getAccessToken()).toJSONString());
        JSONObject embeddedObject = (JSONObject) jsonObject.get("_embedded");
        JSONArray pipelines = (JSONArray) embeddedObject.get("pipelines");
        JSONObject embeddedFromPipelines = (JSONObject) pipelines.get(0);
        JSONObject statusesEmbedded = (JSONObject) embeddedFromPipelines.get("_embedded");
        JSONArray statuses = (JSONArray) statusesEmbedded.get("statuses");
        Iterator<JSONObject> iterator = statuses.iterator();
        List<String> leadsList = new ArrayList<>();
        while (iterator.hasNext()) {
            iterator.forEachRemaining(el ->
                    leadsList.add(el.get("name").toString() + " ID:" + el.get("pipeline_id").toString() + "\n"));
        }
        return leadsList.toString();
    }

    public String getBodyForGetAccessToken() {
        return "{\n" +
                "  \"client_id\": \"61fdb7aa-5bcc-44a8-b696-cd5a02e44b9d\",\n" +
                "  \"client_secret\": \"DUZS0HL0IAp69wUZ3MXymCFsZ7ccBp2cA2D9mIEIm4cbfOAIz8syrriJeHCKBe5q\",\n" +
                "  \"grant_type\": \"refresh_token\",\n" +
                "  \"refresh_token\": \"def50200917dfdd5889933e5f5fc466603efe56d5fd08fb41074c48aee9b00ba14627c8f9781830d734b4419eec247c833f1e96cc25dd6d0812a34a817e3cc48841284b3b9f57b92fb88f4718f28d75297553d6d99387f082f5ac78f9c7afdc8e829ecc0e94b6088c9588d86330e65bf0e65ba0e06b2abcfe1b7e22fdbc8675bedd8e37ac2596baa283c75ababfc816f050eb0c269440b2a358377683765df21edcf41d8f4ba77e1f5afc22016af8e09d65a180c4f2483a55423d9b800dc155a8a904c7e11f4015ccdc45fc5a45b27c89259da68996c00dac0730fb897c9ee3c80f372b9c71de8429f62fc2039c6eb0d3855e09a5d3417c2a51c409728da07a65769a5ddfcb21053de6b4d07f2621aac33a9e11a1b6d7962dbe1973ebd926d1c5147527d32569643d185555cc95b9857845dd22e49296aae90c6b0a1640c98d1dc67c5fa2c553cbfe655e37818ab7fee27e7d5862132d5cc49da612f6cf336ebf3c4854d7b8af9bdf06dcbaef82da1cd11468a11feb103f578160eebb1e74f89b25b0ab2ff8665ae560a1403c9b5760ae7bdffe1dbc503e0b98acd69f283864d3b88ec14f59d2629c876ed1eb6cb8ecfe71306a8\",\n" +
                "  \"redirect_uri\": \"https://talk-me.ru/knowledge_base\"\n" +
                "}";
    }


   /* public List<User> getAllContactsFromAmo() throws ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getAllContacts(AmoServiceProxy.token).toJSONString());
        JSONObject embedded = (JSONObject) jsonObject.get("embedded");
        JSONArray contacts = (JSONArray) embedded.get("contacts");
        Iterator<JSONObject> iteratorForUserListFromAmo = contacts.iterator();
        List<User> newUserListAfterRefactoring = new ArrayList<>();
        while (iteratorForUserListFromAmo.hasNext()) {
            iteratorForUserListFromAmo.forEachRemaining(el -> {
                newUserListAfterRefactoring.add(User.builder()
                        .id(Integer.valueOf(String.valueOf(el.get("id"))))
                        .name(String.valueOf(el.get("name")))
                        .phone(String.valueOf(el.get("phone")))
                        .email(String.valueOf(el.get("email")))
                        .build());
            });
        }
        return newUserListAfterRefactoring;
    }*/

    public String createBodyForNewUser(User user) {
        return "[{\n" +
                "            \"first_name\": \"" + getDefaultEmail(user) + "\",\n" +
                // "                    \"last_name\": \"Смирнов\",\n" +
                "                    \"custom_fields_values\": [\n" +
                "            {\n" +
                "                \"field_id\": 44201,\n" +
                "                    \"field_name\": \"Телефон\",\n" +
                "                    \"field_code\": \"PHONE\",\n" +
                "                    \"field_type\": \"multitext\",\n" +
                "                    \"values\": [\n" +
                "                {\n" +
                "                    \"value\": \"" + getDefaultPhone(user) + "\",\n" +
                "                        \"enum_id\": 64021,\n" +
                "                        \"enum_code\": \"WORK\"\n" +
                "                }\n" +
                "                        ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_id\": 44203,\n" +
                "                    \"field_name\": \"Email\",\n" +
                "                    \"field_code\": \"EMAIL\",\n" +
                "                    \"field_type\": \"multitext\",\n" +
                "                    \"values\": [\n" +
                "                {\n" +
                "                    \"value\": \"" + getDefaultName(user) + "\",\n" +
                "                        \"enum_id\": 64033,\n" +
                "                        \"enum_code\": \"WORK\"\n" +
                "                }\n" +
                "                        ]\n" +
                "            }\n" +
                "                ]\n" +
                "\n" +
                "        }]\n" +
                "    }\n" +
                "}]";
    }

    public String getDefaultEmail(User user) {
        return (user.getEmail() == "") ? "" : user.getEmail();
    }

    public String getDefaultPhone(User user) {
        return (user.getPhone() == "") ? "" : user.getPhone();
    }

    public String getDefaultName(User user) {
        return (user.getName() == "") ? "" : user.getName();
    }

    public String getBodyForNewLeads(){
        return "[\n" +
                "    {\n" +
                "        \"name\": \"Сделка для примера 1\",\n" +
                "        \"price\": 20000,\n" +
                "        \"pipeline_id\": 3502756\n" +
                "    }\n" +
                "]";
    }
}
