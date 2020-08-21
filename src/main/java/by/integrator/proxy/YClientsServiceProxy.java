package by.integrator.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="YCLIENTS-SERVICE-PROXY", url="https://api.yclients.com")

public interface YClientsServiceProxy {

    @PostMapping(value = "/api/v1/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject auth(@RequestHeader("Bearer partner_token") String token,@RequestBody String body );

    @GetMapping(value = "/api/v1/records", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getRecords(@RequestHeader("Bearer partner_token") String token);


    String bodyForAuth = "{\n" +
            "  \"login\": \"testuser@yclients.com\",\n" +
            "  \"password\": \"testpass\"\n" +
            "}";
    String partherToken = "";
    String bodyFromRecords = "{\n" +
            "  \"count\": 10,\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"company_id\": 4564,\n" +
            "      \"staff_id\": 9,\n" +
            "      \"services\": [\n" +
            "        {\n" +
            "          \"id\": 1,\n" +
            "          \"title\": \"Наращивание волос\",\n" +
            "          \"cost\": 100,\n" +
            "          \"manual_cost\": 100,\n" +
            "          \"cost_per_unit\": 100,\n" +
            "          \"discount\": 0,\n" +
            "          \"first_cost\": 100,\n" +
            "          \"amount\": 1\n" +
            "        }\n" +
            "      ],\n" +
            "      \"goods_transactions\": [],\n" +
            "      \"staff\": {\n" +
            "        \"id\": 9,\n" +
            "        \"api_id\": null,\n" +
            "        \"name\": \"Оксана\",\n" +
            "        \"specialization\": \"наращивание волос\",\n" +
            "        \"position\": {\n" +
            "          \"id\": 1,\n" +
            "          \"title\": \"Администратор\"\n" +
            "        },\n" +
            "        \"avatar\": \"http://yclients.com/images/no-master-sm.png\",\n" +
            "        \"avatar_big\": \"http://yclients.com/images/no-master.png\",\n" +
            "        \"rating\": 0,\n" +
            "        \"votes_count\": 0\n" +
            "      },\n" +
            "      \"client\": null,\n" +
            "      \"date\": \"2019-01-16 16:00:00\",\n" +
            "      \"datetime\": \"2019-01-16T16:00:00+09:00\",\n" +
            "      \"create_date\": \"2019-01-16T20:35:11+0900\",\n" +
            "      \"comment\": \"не записывать\",\n" +
            "      \"online\": false,\n" +
            "      \"visit_attendance\": 0,\n" +
            "      \"attendance\": 0,\n" +
            "      \"confirmed\": 1,\n" +
            "      \"seance_length\": 3600,\n" +
            "      \"length\": 3600,\n" +
            "      \"sms_before\": 0,\n" +
            "      \"sms_now\": 0,\n" +
            "      \"sms_now_text\": \"\",\n" +
            "      \"email_now\": 0,\n" +
            "      \"notified\": 0,\n" +
            "      \"master_request\": 0,\n" +
            "      \"api_id\": \"\",\n" +
            "      \"from_url\": \"\",\n" +
            "      \"review_requested\": 0,\n" +
            "      \"visit_id\": \"8262996\",\n" +
            "      \"created_user_id\": 1073232,\n" +
            "      \"deleted\": false,\n" +
            "      \"paid_full\": 0,\n" +
            "      \"prepaid\": false,\n" +
            "      \"prepaid_confirmed\": false,\n" +
            "      \"last_change_date\": \"2019-01-16T20:35:15+0900\",\n" +
            "      \"custom_color\": \"\",\n" +
            "      \"custom_font_color\": \"\",\n" +
            "      \"record_labels\": [],\n" +
            "      \"activity_id\": 0,\n" +
            "      \"custom_fields\": {},\n" +
            "      \"documents\": [\n" +
            "        {\n" +
            "          \"id\": 8172893,\n" +
            "          \"type_id\": 7,\n" +
            "          \"storage_id\": 0,\n" +
            "          \"user_id\": 746310,\n" +
            "          \"company_id\": 4564,\n" +
            "          \"number\": 4163,\n" +
            "          \"comment\": \"\",\n" +
            "          \"date_created\": \"2018-07-03 11:00:00\",\n" +
            "          \"category_id\": 0,\n" +
            "          \"visit_id\": 3,\n" +
            "          \"record_id\": 2,\n" +
            "          \"type_title\": \"Визит\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"consumables\": [],\n" +
            "      \"finance_transactions\": []\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9,\n" +
            "      \"company_id\": 4564,\n" +
            "      \"staff_id\": 49,\n" +
            "      \"services\": [],\n" +
            "      \"goods_transactions\": [],\n" +
            "      \"staff\": {\n" +
            "        \"id\": 49,\n" +
            "        \"api_id\": null,\n" +
            "        \"name\": \"Сергей\",\n" +
            "        \"specialization\": \"стилист\",\n" +
            "        \"position\": {\n" +
            "          \"id\": 1,\n" +
            "          \"title\": \"Администратор\"\n" +
            "        },\n" +
            "        \"avatar\": \"http://yclients.com/images/no-master-sm.png\",\n" +
            "        \"avatar_big\": \"http://yclients.com/images/no-master.png\",\n" +
            "        \"rating\": 0,\n" +
            "        \"votes_count\": 0\n" +
            "      },\n" +
            "      \"client\": null,\n" +
            "      \"date\": \"2019-01-16 16:00:00\",\n" +
            "      \"datetime\": \"2019-01-16T16:00:00+09:00\",\n" +
            "      \"create_date\": \"2019-01-16T20:35:11+0900\",\n" +
            "      \"comment\": \"\",\n" +
            "      \"online\": true,\n" +
            "      \"visit_attendance\": 1,\n" +
            "      \"attendance\": 1,\n" +
            "      \"confirmed\": 1,\n" +
            "      \"seance_length\": 10800,\n" +
            "      \"length\": 10800,\n" +
            "      \"sms_before\": 0,\n" +
            "      \"sms_now\": 0,\n" +
            "      \"sms_now_text\": \"\",\n" +
            "      \"email_now\": 0,\n" +
            "      \"notified\": 0,\n" +
            "      \"master_request\": 1,\n" +
            "      \"api_id\": \"\",\n" +
            "      \"from_url\": \"\",\n" +
            "      \"review_requested\": 0,\n" +
            "      \"visit_id\": \"8262996\",\n" +
            "      \"created_user_id\": 1073232,\n" +
            "      \"deleted\": false,\n" +
            "      \"paid_full\": 0,\n" +
            "      \"prepaid\": false,\n" +
            "      \"prepaid_confirmed\": false,\n" +
            "      \"last_change_date\": \"2017-01-09T20:45:30+0900\",\n" +
            "      \"custom_color\": \"f44336\",\n" +
            "      \"custom_font_color\": \"#ffffff\",\n" +
            "      \"record_labels\": [\n" +
            "        {\n" +
            "          \"id\": \"67345\",\n" +
            "          \"title\": \"Сотрудник не важен\",\n" +
            "          \"color\": \"#009800\",\n" +
            "          \"icon\": \"unlock\",\n" +
            "          \"font_color\": \"#ffffff\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"104474\",\n" +
            "          \"title\": \"важная категория\",\n" +
            "          \"color\": \"#3b2c54\",\n" +
            "          \"icon\": \"odnoklassniki\",\n" +
            "          \"font_color\": \"#ffffff\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"activity_id\": 0,\n" +
            "      \"custom_fields\": {},\n" +
            "      \"documents\": [\n" +
            "        {\n" +
            "          \"id\": 8172893,\n" +
            "          \"type_id\": 7,\n" +
            "          \"storage_id\": 0,\n" +
            "          \"user_id\": 746310,\n" +
            "          \"company_id\": 4564,\n" +
            "          \"number\": 4163,\n" +
            "          \"comment\": \"\",\n" +
            "          \"date_created\": \"2018-07-03 11:00:00\",\n" +
            "          \"category_id\": 0,\n" +
            "          \"visit_id\": 3,\n" +
            "          \"record_id\": 2,\n" +
            "          \"type_title\": \"Визит\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"consumables\": [],\n" +
            "      \"finance_transactions\": []\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
