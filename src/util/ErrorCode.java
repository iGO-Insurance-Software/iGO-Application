package util;

public enum ErrorCode {
    SUCCESS(true, 100, "요청에 성공했습니다."),

    // LOGIN
    EMPTY_ID(false, 200, "아이디를 입력해주세요."),
    NOT_EXIST_ID(false, 202, "존재하지 않는 아이디입니다. ID를 확인하고 다시 로그인 해주세요."),
    CANNOT_ACCESS_MENU(false, 203, "접근 권한이 없어 해당 메뉴를 이용하실 수 없습니다."),
    LOADING_ERROR_CUSTOMER(false,204,"[Error!] 현재 시스템의 성능 저하로 서비스가 원할하게 진행되지 못하고 있습니다. 잠시후 다시 시도해 주세요." +
            "\n고객센터 번호: 02-8282-7575"),
    LOADING_ERROR_EMPLOYEE(false,205,"[Error!] 현재 시스템의 성능 저하로 서비스가 원할하게 진행되지 못하고 있습니다. 잠시후 다시 시도해 주세요." +
            "\n전산팀 사내 유선번호: 047"),

    // DATA
    NULL_DATA(false, 300, "데이터가 존재하지 않습니다."),

    // 사고접수팀
    ACCIDENTS_MORE_THAN_5YEARS_NOT_BE_ACCEPTED(false, 400, "발생한 지 5년이 넘은 사고는 접수할 수 없습니다."),

    // 은행원
    NO_RESPONSE_TO_CURRENT_CUSTOMER_INFO_REQ(false, 500, "현재 고객 정보 요청에 대한 응답이 없어 재요청하였습니다."),
    NO_RESPONSE_TO_REGISTER_REINSURANCE_INFO_REQ(false, 501, "현재 재보험 등록 요청에 대한 응답이 없어 재요청 하였습니다."),

    // 보상지급팀
    IS_BANK_INSPECTION_TIME(false,600,"[Error!]: 은행 점검 시간입니다.(00:00~01:30) 점검이 끝나면 다시 시도해주세요")
    ;



    private final boolean isSuccess;
    private final int code;
    private final String errorMessage;

    private ErrorCode(boolean isSuccess, int code, String errorMessage) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getErrorMessage() { return errorMessage; }
}