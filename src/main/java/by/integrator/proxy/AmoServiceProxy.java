package by.integrator.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="AMO-SERVICE-PROXY", url="https://fvantop.amocrm.ru")
public interface AmoServiceProxy {

    @GetMapping(value = "/api/v4/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getInfo(@RequestHeader("Bearer") String token);

    @GetMapping("/api/v4/contacts")
    JSONObject getAllContacts(@RequestHeader("Authorization") String token);

    @GetMapping("/api/v4/leads")
    JSONObject getAllLeads(@RequestHeader("Authorization") String token);

    @PostMapping(value = "/api/v4/contacts", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject addContact(@RequestHeader("Authorization") String token, @RequestBody String body);

    @PostMapping(value = "/oauth2/access_token", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getAccessAndRefreshToken(@RequestBody String body);

    @GetMapping("/api/v4/leads/pipelines")
    JSONObject getPipelines(@RequestHeader("Authorization") String token);

    @PostMapping(value = "/api/v4/leads", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject addLeads(@RequestHeader("Authorization") String token, @RequestBody String body);


    public static String bodyForHaveNewAccessToken = "{\n" +
            "  \"client_id\": \"61fdb7aa-5bcc-44a8-b696-cd5a02e44b9d\",\n" +
            "  \"client_secret\": \"DUZS0HL0IAp69wUZ3MXymCFsZ7ccBp2cA2D9mIEIm4cbfOAIz8syrriJeHCKBe5q\",\n" +
            "  \"grant_type\": \"refresh_token\",\n" +
            "  \"refresh_token\": \"def50200ba92e457a95089b24ea256f9bf906a7e83edeb7597424055b065d017af7c0642d12d85d8c72d44c2809570db62c20db7f96699f2c22474a1e4b9ed286c60111623bf60e89ce6a831a4e71b2bba7b5e371b1a393da4d28acafc5c1804b9684aaabc603ac089d4a23b03be1ec205c578fa168add317cee1e4348d9269933a5ef4aaeed3e31c4f79ef6fd030922fafd91e8938dfe390c6c0e0158ea6364f5a65792bc4fbd61a08ec12397ef585c3a82b57777bcaf848dfc4c7903a1f189ebb0992b9d7b54e07c923d8e4c56cf3fc8c1a8b0d27711105830bde0569faf80bbee99ced704faef96eb00d37ee5f41809186b02d5e3cbb56fff5b7e78e00e77300fcde04cc8d14647d7464947a394fbcb7388d7b975fbc11e80b7957bef3ec72fa614090838b201337a3d51ef1dc1bc869ee69b6fc61f9b2c20e80d47271584a1f7ae1aad6cf2c231911544298503eb6940ab9d7d72a4f1b24aab11d3b4b23f516abb55b6e79feda67721be5b33af469d7adb1d837448cbfea94d8c4edadf59a7ad53d5820d2ce16646f2bd9424df21bb6071c9d0cd468318a5b0e9a0b20e14a9651c0d628d1b99e28009c41757cd79b2a1ead3\",\n" +
            "  \"redirect_uri\": \"https://talk-me.ru/knowledge_base\"\n" +
            "}";

    public static String answerWithNewAccessToken = "\"token_type\": \"Bearer\",\n" +
            "\"expires_in\": 86400,\n" +
            "\"access_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImIxN2UxYTFjZjEyYTFmNzI0ZGI1YTQxNmQ0YTczY2U0ZTg0ZWYxM2ZiNmNiNzNjMGRmN2NiMjM1ZmZjNjU4NmY0YmQ4ZmUxMjZmZGY4YThjIn0.eyJhdWQiOiI2MWZkYjdhYS01YmNjLTQ0YTgtYjY5Ni1jZDVhMDJlNDRiOWQiLCJqdGkiOiJiMTdlMWExY2YxMmExZjcyNGRiNWE0MTZkNGE3M2NlNGU4NGVmMTNmYjZjYjczYzBkZjdjYjIzNWZmYzY1ODZmNGJkOGZlMTI2ZmRmOGE4YyIsImlhdCI6MTU5ODE3OTQyNSwibmJmIjoxNTk4MTc5NDI1LCJleHAiOjE1OTgyNjU4MjUsInN1YiI6IjYyMTQzNTQiLCJhY2NvdW50X2lkIjoyODk4NTI4Nywic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.R8W6hNEf-3UiupgI06HAfVyeY45BCtL_Vx9yfDD7liguRJ9YyLKRPsJKrymZTIPhEWPQgLlF_03Xhz79yW9S3HBHTX1KQhDof7sMUMw_CH4HIe3tJc7SSw0ifQj3mJIGhRr11_aYzhcKuOrk3cSU_E_VTK4ERTv8ji2uMgZ9uPLop71euWgxRZ7q_lWGm2OiLZSLJ-RrHruMV9AoGtNlwmpGEpNVlz9eGVOZYANZUMuB0W_rXu8z0zfFCV9Nv5QjXKFNj8M-KDa-aBfxLTY4A5r0dXmb2Vu-qp0Zr8WYqMBDkUfgGR8-pYyZLp5Wy_K0AfkeZ0ljIgIIj0IaNRpLQg\",\n" +
            "\"refresh_token\": \"def5020032c2a34fabf40d10277fe658eb9e1c3a540820de7f4407244e1294a82208ad47b444e4ba1ba6dfc548ddebd0f448f69569f2b196f2735e53677eaa3b3cd8f195bda198c15b62a9cff3f797710dde04d6502b467c4691be687fe95c90dee9f7840faed2ab725c3c57ce902bf380057cce9cb831aadea02550d7c16103e17401f2672e1e8c059ec40004658616964c59fdb2ae5b58f2a76e13de101e78b3ab3af2b287e7be0bfe22cb4a33601a05ef9346b5f6ffc975294e1b69482a5e1fd6152516e51ea2255fc1546d1b71a722776c50d9643d62f3de4991ca7f092529900252f9cdbdd9b8a2334b950a7277a2dfe1dd1a202ee2341af306821be4f2cbea2091ec42e70f12ee47d60b7bd68d36a42cc54e57d2b2cd6683c9ca0359101cb264a4a4cf291f31f7617fd8ff493f00f6ad39bb282ec03e021af74f93a5c57636b032787c2e5f25edc94eb831cfa365c4dfc9469dc4b9d09a8c08721539130bee1bc781c3857b7af376b0bd39f35e35fa64dc89972e9de15c5c4385d9f2a8977bef66bcea17a712aa5d35e9f399748b8f125dd976213f2069556b08f45918a9ddc05bbdd6fffb57991db5f162a5fe647c03c4\"\n" +
            "}";

}
