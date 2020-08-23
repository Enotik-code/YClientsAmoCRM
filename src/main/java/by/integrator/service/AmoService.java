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

    public static String refreshToken = "def5020032c2a34fabf40d10277fe658eb9e1c3a540820de7f4407244e1294a82208ad47b444e4ba1ba6dfc548ddebd0f448f69569f2b196f2735e53677eaa3b3cd8f195bda198c15b62a9cff3f797710dde04d6502b467c4691be687fe95c90dee9f7840faed2ab725c3c57ce902bf380057cce9cb831aadea02550d7c16103e17401f2672e1e8c059ec40004658616964c59fdb2ae5b58f2a76e13de101e78b3ab3af2b287e7be0bfe22cb4a33601a05ef9346b5f6ffc975294e1b69482a5e1fd6152516e51ea2255fc1546d1b71a722776c50d9643d62f3de4991ca7f092529900252f9cdbdd9b8a2334b950a7277a2dfe1dd1a202ee2341af306821be4f2cbea2091ec42e70f12ee47d60b7bd68d36a42cc54e57d2b2cd6683c9ca0359101cb264a4a4cf291f31f7617fd8ff493f00f6ad39bb282ec03e021af74f93a5c57636b032787c2e5f25edc94eb831cfa365c4dfc9469dc4b9d09a8c08721539130bee1bc781c3857b7af376b0bd39f35e35fa64dc89972e9de15c5c4385d9f2a8977bef66bcea17a712aa5d35e9f399748b8f125dd976213f2069556b08f45918a9ddc05bbdd6fffb57991db5f162a5fe647c03c4";
    public static String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImIxN2UxYTFjZjEyYTFmNzI0ZGI1YTQxNmQ0YTczY2U0ZTg0ZWYxM2ZiNmNiNzNjMGRmN2NiMjM1ZmZjNjU4NmY0YmQ4ZmUxMjZmZGY4YThjIn0.eyJhdWQiOiI2MWZkYjdhYS01YmNjLTQ0YTgtYjY5Ni1jZDVhMDJlNDRiOWQiLCJqdGkiOiJiMTdlMWExY2YxMmExZjcyNGRiNWE0MTZkNGE3M2NlNGU4NGVmMTNmYjZjYjczYzBkZjdjYjIzNWZmYzY1ODZmNGJkOGZlMTI2ZmRmOGE4YyIsImlhdCI6MTU5ODE3OTQyNSwibmJmIjoxNTk4MTc5NDI1LCJleHAiOjE1OTgyNjU4MjUsInN1YiI6IjYyMTQzNTQiLCJhY2NvdW50X2lkIjoyODk4NTI4Nywic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.R8W6hNEf-3UiupgI06HAfVyeY45BCtL_Vx9yfDD7liguRJ9YyLKRPsJKrymZTIPhEWPQgLlF_03Xhz79yW9S3HBHTX1KQhDof7sMUMw_CH4HIe3tJc7SSw0ifQj3mJIGhRr11_aYzhcKuOrk3cSU_E_VTK4ERTv8ji2uMgZ9uPLop71euWgxRZ7q_lWGm2OiLZSLJ-RrHruMV9AoGtNlwmpGEpNVlz9eGVOZYANZUMuB0W_rXu8z0zfFCV9Nv5QjXKFNj8M-KDa-aBfxLTY4A5r0dXmb2Vu-qp0Zr8WYqMBDkUfgGR8-pYyZLp5Wy_K0AfkeZ0ljIgIIj0IaNRpLQg";

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
                "        \"created_by\": 0,\n" +
                "        \"price\": 20000,\n" +
                "        \"pipeline_id\": 3452836,\n" +
                "        \"custom_fields_values\": [\n" +
                "            {\n" +
                "                \"field_id\": 294471,\n" +
                "                \"values\": [\n" +
                "                    {\n" +
                "                        \"value\": \"Наш первый клиент\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Сделка для примера 2\",\n" +
                "        \"price\": 10000,\n" +
                "        \"_embedded\": {\n" +
                "            \"tags\": [\n" +
                "                {\n" +
                "                    \"id\": 2719\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "]";
    }
}
