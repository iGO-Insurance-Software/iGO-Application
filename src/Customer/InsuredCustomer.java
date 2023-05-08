package Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class InsuredCustomer extends Customer {

	private String familyHistory;

	private String healthCertificate;
	private String employmentCertificate;
	private String inheritanceCertificate;

	private String accidentCertificate;
	private String judgmentCertificate;

	private String medicalCertificate;
	private String bankbookCopy;

	public InsuredCustomer(){

	}

	public HashMap<String,String> sendReceiption(BufferedReader inputReader) throws IOException {
		HashMap accidentInfo = new HashMap<String,String>();

		System.out.println("<아래의 사고 정보를 기입하세요>");
		System.out.print("사고 발생 년-월-일(예시: 2023-01-01): ");
		String accidentYMD = inputReader.readLine().trim();
		System.out.print("\n사고 발생 시:분 (예시: 17:53): ");
		String accidentHM = inputReader.readLine().trim();
		String accidentDateStr = accidentYMD+" "+accidentHM;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date accidentDate;
		try {
			accidentDate = formatter.parse(accidentDateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다.");
		}
		accidentInfo.put("사고 일시",accidentDateStr);

		System.out.print("\n사고 장소 (예시: 거북골로 3-2길 횡단보도): ");
		String accidentPlace = inputReader.readLine();
		accidentInfo.put("사고 장소", accidentPlace);

		System.out.print("\n사고 유형 (예시: 교통사고): ");
		String accidentType = inputReader.readLine();
		accidentInfo.put("사고 유형", accidentType);

		System.out.print("\n사고 개요 (예시: 초록불에 횡단보도를 건너다가 마티즈 차량이 저를 쳤어요): ");
		String accidentOutline = inputReader.readLine();
		accidentInfo.put("사고 개요", accidentOutline);

		System.out.println("\n<손괴자 여부 체크>\n해당 사고에 손괴자가 존재하나요?\n1.네\n2.아니요");
		System.out.print("\nChoice: ");
		String userChoiceValue = inputReader.readLine().trim();
		switch(userChoiceValue){
			case "1" :
				System.out.print("\n손괴자 이름을 입력하세요 (예시: 홍길동): ");
				String destoryerName = inputReader.readLine().trim();
				accidentInfo.put("손괴자 이름",destoryerName);
				System.out.print("\n손괴자 전화번호를 입력하세요 (예시: 01012341234): ");
				String destoryerPhoneNum = inputReader.readLine().trim();
				accidentInfo.put("손괴자 전화번호",destoryerPhoneNum);
				break;
			case "2" :
				break;
			default :
				System.out.println("Please select from the menu");
		}
		System.out.println("1. 접수하기\n2. 긴급 접수하기\nx. 접수 취소하기 ");
		System.out.print("\nChoice: ");
		userChoiceValue = inputReader.readLine().trim();

		switch(userChoiceValue){
			case "1" :
				accidentInfo.put("긴급 여부","일반");
				break;
			case "2" :
				accidentInfo.put("긴급 여부","긴급");
				break;
			case "x" :
				accidentInfo=null;
				break;
			default :
				System.out.println("\n Please select from the menu");
		}

		//Exception: 5년이 지난 사고일 경우
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(accidentDate);
		calendar.add(Calendar.YEAR, 5);
		if (calendar.getTime().before(new Date())) {
			accidentInfo = null;
			try {
				throw new RuntimeException("발생한지 5년이 넘은 사고는 접수할 수 없습니다.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		return accidentInfo;
	}


	public boolean filloutInfo(){
		return false;
	}

	public boolean cancelReceiption(){
		return false;
	}

	public HashMap<String,String> getInfo(){
		return null;
	}

}