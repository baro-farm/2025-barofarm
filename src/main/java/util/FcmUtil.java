package util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

public class FcmUtil {
    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY;
    static {
        Properties prop = new Properties();
        try {
            InputStream input = FcmUtil.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("config.properties 파일을 찾을 수 없습니다.");
            }
            prop.load(input);
            SERVER_KEY = prop.getProperty("fcm.serverKey");
            if (SERVER_KEY == null) {
                throw new RuntimeException("fcm.serverKey 값이 설정되어 있지 않습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException("config.properties 읽기 실패: " + e.getMessage(), e);
        }
    }
    
    public static void sendMessageTo(String fcmToken, String title, String body) throws Exception {
        // 요청할 JSON 데이터 생성
        String message = 
            "{" +
                "\"to\":\"" + fcmToken + "\"," +
                "\"notification\":{" +
                    "\"title\":\"" + title + "\"," +
                    "\"body\":\"" + body + "\"" +
                "}" +
            "}";

        // FCM 서버로 요청 보내기
        URL url = new URL(FCM_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
        conn.setRequestProperty("Content-Type", "application/json; UTF-8");


        try (OutputStream os = conn.getOutputStream()) {
            os.write(message.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
        
        int responseCode = conn.getResponseCode();
        System.out.println("[FCM 응답 코드] " + responseCode);

		/*
		 * if (responseCode != HttpURLConnection.HTTP_OK) { throw new
		 * RuntimeException("FCM 푸시 알림 전송 실패. 응답 코드: " + responseCode); }
		 */
    	// 서버로부터 받은 응답 내용 읽기
        try (InputStream is = conn.getInputStream();
             Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            String responseBody = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            System.out.println("[FCM 응답 본문] " + responseBody);
        } catch (Exception e) {
            // 실패했을 때는 에러 스트림 읽기
            InputStream es = conn.getErrorStream();
            if (es != null) {
                Scanner scanner = new Scanner(es, StandardCharsets.UTF_8.name());
                String errorBody = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                System.out.println("[FCM 에러 응답] " + errorBody);
            }
            throw new RuntimeException("FCM 푸시 알림 전송 실패: " + e.getMessage(), e);
        }
    }

}
