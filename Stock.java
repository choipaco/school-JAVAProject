package nilu;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.*;
public class Stock {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean ys = false;
		char repaet = 'n';
		
		try { 
			while(true) {
				Document doc = (null);

				System.out.println("--한국 주식 확인 프로그램--");
				System.out.println("1. 코스피 (대부분 대기업)");
				System.out.println("2. 코스닥 (대부분 중소기업)");
				System.out.println("원하는 시장 번호를 입력하세요.");
				String want = sc.next();
				sc.nextLine();
				System.out.println("원하는 주식을 자세히 입력해주세요.");
				String like = sc.nextLine();
				System.out.println("관련 주식을 찾는 중...");
				if(want.contains("코스피") || want.contains("1")) {
					doc = Jsoup.connect("https://finance.naver.com/sise/sise_quant.naver?sosok=0").get();
				}
				else if(want.contains("코스닥") || want.contains("2")){
					doc = Jsoup.connect("https://finance.naver.com/sise/sise_quant.naver?sosok=1").get();
				}
				
				Elements list1 = doc.select(".type_2 > tbody > tr > td > a");
				Elements list2 = doc.select(".type_2 > tbody > tr > td:nth-child(3)");
				Elements list3 = doc.select(".type_2 > tbody > tr > td:nth-child(4)");
				Elements list4 = doc.select(".type_2 > tbody > tr > td:nth-child(5)");
				
				System.out.println();
				for(int i = 0; i < list1.size(); ++i) {
					
					if(list1.get(i).text().contains(like)) {
						Elements list5 = list3.get(i).select("img");
						System.out.println("주식명:"+list1.get(i).text());
						System.out.println("현재가: "+ list2.get(i).text() + "원");
						System.out.print("전일비: ");
						if(list5.attr("alt").contains("하락")) {
							System.out.printf("-");
						}
						else {
							System.out.printf("+");
						}
						System.out.println(list3.get(i).text()+"원");
						System.out.println("등락률: " + list4.get(i).text());
						ys = true;
						System.out.println("---------------------------------------");
						Thread.sleep(200);
					}
				}
				
				Thread.sleep(1000);
				if(ys == false) {
					System.out.println("값을 불러오지 못했습니다 다시 하시겠습니까? y/n");
					repaet = sc.next().charAt(0);
					sc.nextLine();
					if(repaet == 'n') {
						break;
					}
					else if(repaet == 'y') {
						System.out.println("다시 시작합니다");
						System.out.println();
					}
					else {
						System.out.printf("비이상적인 입력으로 ");
						break;
					}
				}
				else if(ys == true) {
					System.out.println("정상적으로 값이 나왔습니다.\n프로그램을 재시작합니다.");
					System.out.println();
					Thread.sleep(1000);
				}
				
				
			}
			

		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println("시스템을 종료합니다.");
	}

}
