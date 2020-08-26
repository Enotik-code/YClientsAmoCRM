package by.integrator.service;

import by.integrator.bean.User;
import by.integrator.proxy.AmoServiceProxy;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class AmoService {

    @Autowired
    private AmoServiceProxy amoServiceProxy;

    static Logger log = Logger.getLogger(AmoService.class.getName());


    public static String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY4YmUyNzk4MjRkNWNlMWJjZGQ4ZDVmNjg4OWVmYzZiZDlkMTQyNTAyMjE3YWZkYTIyZjFlMTJlZmI4M2JiZDRhNzU2NjlhYjRjNmJhMDY2In0.eyJhdWQiOiJlYjFmNDYyMS1hNGZmLTQxNDgtYWY1Zi0yZGMwNDkyZjgwMmQiLCJqdGkiOiI2OGJlMjc5ODI0ZDVjZTFiY2RkOGQ1ZjY4ODllZmM2YmQ5ZDE0MjUwMjIxN2FmZGEyMmYxZTEyZWZiODNiYmQ0YTc1NjY5YWI0YzZiYTA2NiIsImlhdCI6MTU5ODM5MjUxOCwibmJmIjoxNTk4MzkyNTE4LCJleHAiOjE1OTg0Nzg5MTgsInN1YiI6IjYyNjQyNjIiLCJhY2NvdW50X2lkIjoyOTAxMDU4Niwic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.lIl6ri6KCyTmOSIRjLMC3eajds1B_ssKLjBqyCCoI3Q1i2ndbG-HHiYIQ4aSth9ReZoK1Yy5WSgqI7qfMjjQ0S-LW9OvtN8J9TJ0WQuzQj3ckiSxGoSwshqkpMwRQPxfAbmtS2fZCD_Dc8zNkqfRMNSCxIcPnO7x3jiZfmacmlpKZ_DhWXLq5cGxIz9ftdyRii5U_2bbGSeqxiUl4GHYrpAin-AN8R5inXKod_aPFPTvTVzPPy6OJKl2eXlPtxE_XDeWXBz8F-Hw1_CrydJySF9V4nhOAv_3RFOIkGpMqQyENkFrBTWbVVWKBFjCG2Z7UF3xW374xter_FNGfRtXpQ";
    public static String refreshToken = "def5020068a7b17c33e5b03ada740c3da40cb4991132681bbea89f974336e4623c2308a6c1d39b4e9d66d90e648ef31f6b0705eedbbfba4336896c63b4857a65196dfb49be49c93929605b9c2a007dd436096240c959c78083fc44c3adfd649ce7a91cc81a5c32b4dfc9859cdbee90bd58c4eeaefb38951bfca2ac0bca2f1e0ea2282af0645e7d69d902eff53f84cdde60f0355061e9653840ed3ebb57b73356753bea8ce4158b38c72710afe3a6176a67e0cc5bda37cd58cf2ec84522305cac9c58f1b186ea6aaf21dc203598ab36c6b3719a19e630aabed08e15c2056221c2fda79260b42342b670e86c6d40486b4757c27602059cb1c1ae1abe93f897b6baf098bcae0a0e3fa34a7248305fde067a78d703f78e48da16530ba50135a122a3496eb8dd0f81a8bfd5308aef4ea99855956d572a91d0a40a216b4530e522b3a2abd3bdea132a9b6bce1ad30486c94f9e31a4e104f20414237c12b502a2b873c720500ccdcb26336a12389a887916a6d459b52aba264a3ea24068487ce9db0f74933d7348fd61b85d23a30c13689975fc6b48b660d2e3035ba7f8e1af32b0d2e40613f8029d8e54c9fa6dfc689b995a49ea429f41";

    public static Map<Long, String> statusMap = new HashMap<>();

    public String getAccessToken() {
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
    public void createLeads() {
        amoServiceProxy.addLeads(getAccessToken(), getBodyForNewLeads());
    }

    @SneakyThrows
    public String getLeads() {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getAllLeads(getAccessToken()).toJSONString());
        JSONObject embeddedObject = (JSONObject) jsonObject.get("_embedded");
        JSONArray leadsArray = (JSONArray) embeddedObject.get("leads");
        Iterator<JSONObject> iterator = leadsArray.iterator();
        List<String> leadsList = new ArrayList<>();
        while (iterator.hasNext()) {
            iterator.forEachRemaining(el ->{
                if (numberOfDaysUpTo30(((Long) el.get("updated_at")).intValue()) == 30){
                    updateLeadsIfLastUpdateMoreOneMonth((Long) el.get("id"));
                }
                    leadsList.add(el.get("name").toString() + " || ID:" + el.get("id").toString()
                            + " || Последняя дата: " + (Instant.ofEpochSecond((Long) el.get("updated_at"))) +
                            "  || Воронка: " + getStatusNameById((Long) el.get("status_id")) + " || Дней до перехода: " +
                            numberOfDaysUpTo30(((Long) el.get("updated_at")).intValue()) + " ||\n\n");
            });
        }
        return leadsList.toString();
    }


    @SneakyThrows
    public void updateLeadsIfLastUpdateMoreOneMonth(Long idLead){
        String body = "\n" +
                "    {\n" +
                "        \"status_id\": 34626007\n" +
                "    }\n";
        amoServiceProxy.updateLead(accessToken, body, idLead);
    }

    @SneakyThrows
    public String getPipelines() {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getPipelines(getAccessToken()).toJSONString());
        JSONObject embeddedObject = (JSONObject) jsonObject.get("_embedded");
        JSONArray pipelines = (JSONArray) embeddedObject.get("pipelines");
        JSONObject embeddedFromPipelines = (JSONObject) pipelines.get(0);
        JSONObject statusesEmbedded = (JSONObject) embeddedFromPipelines.get("_embedded");
        JSONArray statuses = (JSONArray) statusesEmbedded.get("statuses");
        Iterator<JSONObject> iterator = statuses.iterator();
        while (iterator.hasNext()) {
            iterator.forEachRemaining(el ->
                    statusMap.put((Long) el.get("id"), el.get("name").toString()));
        }
        return statusMap.toString();
    }

    @SneakyThrows
    public Long numberOfDaysUpTo30(int firstDate) {
        Instant instant = Instant.ofEpochSecond(firstDate);
        Date dateOfLastUpdate = Date.from(instant);
        Long diff = LocalDate.now().until(convertToLocalDateViaInstant(dateOfLastUpdate), ChronoUnit.DAYS);
        return diff + 30;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }



    public String getStatusNameById(Long id) {
        return statusMap.get(id).toString();
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

    public String getBodyForNewLeads() {
        return "[\n" +
                "    {\n" +
                "        \"name\": \"Сделка для примера 1\",\n" +
                "        \"price\": 20000,\n" +
                "        \"pipeline_id\": 3502756\n" +
                "    }\n" +
                "]";
    }
}
