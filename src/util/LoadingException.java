package util;
import java.util.Random;

public class LoadingException extends Exception {
    private static final int MAX_WAIT_TIME = 7000; // 최대 대기 시간 (7초)
    public LoadingException(String message) {
        super(message);
    }
    public static void loadingCustomer() throws LoadingException {
        Random random = new Random();
        //int waitTime = random.nextInt(MAX_WAIT_TIME); //랜덤 대기 시간 설정
        int waitTime = MAX_WAIT_TIME;
        try {
            Thread.sleep(waitTime); //지정한 시간 동안 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (waitTime >= 7000) {
            throw new LoadingException("[Error!] 현재 시스템의 성능 저하로 서비스가 원할하게 진행되지 못하고 있습니다. 잠시후 다시 시도해 주세요." +
                    "\n고객센터 번호: 02-8282-7575");
        }
    }
    public static void loadingEmployee() throws LoadingException {
        Random random = new Random();
        //int waitTime = random.nextInt(MAX_WAIT_TIME); //랜덤 대기 시간 설정
        int waitTime = MAX_WAIT_TIME;
        try {
            Thread.sleep(waitTime); //지정한 시간 동안 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (waitTime >= 7000) {
            throw new LoadingException("[Error!] 현재 시스템의 성능 저하로 서비스가 원할하게 진행되지 못하고 있습니다. 잠시후 다시 시도해 주세요." +
                    "\n전산팀 사내 유선번호: 047");
        }
    }

}