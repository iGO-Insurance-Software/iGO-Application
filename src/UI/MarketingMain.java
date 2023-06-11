package UI;

import Employee.Employee;
import Employee.MarketingTeam;
import Survey.Survey;
import Dao.MarketingTeamDao;
import Dao.SurveyDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static UI.Main.*;

public class MarketingMain {
    private MarketingTeam marketingTeam;
    private MarketingTeamDao marketingTeamDao;
    private SurveyDao surveyDao;

    public MarketingMain(Employee currentEmployee) {
        this.marketingTeam = (MarketingTeam) currentEmployee;
        this.marketingTeamDao = new MarketingTeamDao();
        this.surveyDao = new SurveyDao();
    }

    public boolean showMarketingMenu(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        String userChoiceValue;
        while (isRemain) {
            System.out.println("1. 설문조사 결과 보기");
            System.out.println("2. 광고 등록하기");
            System.out.println("x. 로그아웃");
            System.out.print("\nChoice: ");
            userChoiceValue = inputReader.readLine().trim();
            switch (userChoiceValue) {
                case "1":
                    ArrayList<Survey> survey = surveyDao.retrieveAll();
                    for (int i = 0; i < survey.size(); i ++)  {
                        System.out.println("설문 내용 : " + survey.get(i).getSurveyQuestionContent());
                        System.out.println("설문 결과 (몇번 선택 되었는지 횟수) : " + survey.get(i).getSelectedCount());
                        System.out.println("==================================");
                    }
                    break;
                case "2":
                    System.out.println("광고하고자 하는 상품의 이름을 적어주세요. (x. 뒤로가기)");
                    System.out.print("상품 이름: ");
                    String adName = inputReader.readLine().trim();
                    if (adName.equals("x")) { System.out.println("광고 등록을 취소합니다."); break; }
                    System.out.println("광고하고자 하는 상품의 설명을 적어주세요. (x. 뒤로가기)");
                    System.out.print("상품 설명: ");
                    String adDescription = inputReader.readLine().trim();
                    if (adDescription.equals("x")) { System.out.println("광고 등록을 취소합니다."); break; }
                    MarketingTeam marketingTeam = marketingTeamDao.retrieveById(currentEmployee.getId());
                    marketingTeam.setAdName(adName);
                    marketingTeam.setAdDescription(adDescription);
                    marketingTeamDao.update(marketingTeam);
                    System.out.println("광고 등록이 완료되었습니다.");
                    break;
                case "x":
                    return false;
            }
        }
        return true;
    }
}
