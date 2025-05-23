package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Scanner;

import com.google.auth.oauth2.GoogleCredentials;

public class FcmUtil {
    private static final String FIREBASE_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String PROJECT_ID = "kosta-1213b"; // Firebase 콘솔에서 확인
    private static final String MESSAGING_URL = "https://fcm.googleapis.com/v1/projects/" + PROJECT_ID + "/messages:send";

    // access token 발급
    private static String getAccessToken() throws IOException {
        InputStream serviceAccountStream = FcmUtil.class.getClassLoader().getResourceAsStream("serviceAccountKey.json");
        if (serviceAccountStream == null) {
            throw new FileNotFoundException("serviceAccountKey.json not found in classpath");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(Collections.singleton(FIREBASE_SCOPE));

        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }

    // FCM 메시지 전송
    public static void sendMessageTo(String fcmToken, String title, String body) throws Exception {
        String accessToken = getAccessToken();

        String jsonMessage = "{\n" +
                "  \"message\": {\n" +
                "    \"token\": \"" + fcmToken + "\",\n" +
                "    \"notification\": {\n" +
                "      \"title\": \"" + title + "\",\n" +
                "      \"body\": \"" + body + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
		System.out.println("=== FcmUtil sendKockFarmAlarm 호출됨 ===");

        HttpURLConnection conn = (HttpURLConnection) new URL(MESSAGING_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type", "application/json; UTF-8");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonMessage.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        int responseCode = conn.getResponseCode();
        InputStream responseStream = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        try (Scanner scanner = new Scanner(responseStream, "UTF-8")) {
            String responseBody = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            if (responseCode >= 200 && responseCode < 300) {
                // 성공
                // System.out.println("[FCM 응답 본문] " + responseBody);
            } else {
                // 실패
                // System.err.println("[FCM 에러 응답] " + responseBody);
                throw new RuntimeException("FCM 전송 실패: " + responseBody);
            }
        }
    }
}
