package pack03.go.data;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

public class RequestJson {
	//★Request URL <= Request (요청) Chrome or 여러가지 웹브라우저 사용.
	//주소창에 어떤 주소값을 넣고(www.naver.com) enter key
	//또는 네이버에서 어떤 기사를 클릭(a tag 등등을 통해서 URL 요청)
	
	//여기는 지금 몰라도 되지만 한번 들어놓으면 좋음 => Response (응답)
	
	public static void main(String[] args) {
		//URL (공공데이터를 실제로 요청하기 위한 URL)
		//? 넘겨줘야할 변수들(Parametter) ex) 현.page, perPage, ServiceKey
		//?page=1&perPage=10&serviceKey=oimoul9nstxIUuNxS8uOCA61%2B78z4cjnutp%2FG1bF7oxnKmibDIrB9HvX7HaVaH7li4WC2t8DpB4b%2BSwXvoeRJg%3D%3D\r\n"
		
		String url = "https://api.odcloud.kr/api/3082925/v1/uddi:7c291067-a956-4811-a9ec-942b6979ff77_201709270816";//?앞에까지만 복붙
		// java => Https, Http로 된 인터넷으로 요청을 보내고 응답을 받아오기,
		//Jsoup <=이용
		// ? page=1
		String doc = null;
		try {
		
		doc = Jsoup.connect(url)
			.data("page", "1")
			.data("perPage", "10")
			.data("serviceKey","oimoul9nstxIUuNxS8uOCA61+78z4cjnutp/G1bF7oxnKmibDIrB9HvX7HaVaH7li4WC2t8DpB4b+SwXvoeRJg==")
			.timeout(5000)	//<= timeout 어떤 요청을 했을때 요청을 받은 서버가 일정시간 응답을 하지 않으면
						//요청한 쪽에서 통신을 끊고 그 다음처리를 한다 ※	1000 == 1초
			.userAgent("Chrome")
			.ignoreContentType(true)
			.execute()			//실행시키다
			.body();			//마지막 돌아오는 타입, 받아줘야하기 때문에 String 변수에 할당해야함.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(doc);
		//ArrayList로 바꾸기 doc <=
		//하나씩 출력하기
		// { kdy, value } <= 클래스(Object, 객체)
		//[] <= Array(배열, List 등 여러개의 데이터
		
		
		
		JSONParser jsonParser = new JSONParser();	//API 어떤 데이터 종류를 Json형태로 바꿔주는
		//API => Gson 사용할거임 나중에
		
		try {
			JSONObject jsonObj = (JSONObject) jsonParser.parse(doc);
			
			System.out.println(jsonObj.toString());
			
			JSONArray dataArray = (JSONArray) jsonObj.get("data");//object
			for(int i = 0; i < dataArray.size(); i++) {
				//System.out.println(dataArray.get(i));
				//JsonArray <= Elements <= JsonObject
				//전부 따로 출력할수 있게 내가 사용하는 변수 타입에 담고 출력해보기.
				JSONObject obj = (JSONObject) dataArray.get(i);
				
				//System.out.println(obj.toString());
				System.out.println(obj.get("연번") + "\t");
				System.out.println(obj.get("연락처") + "\t");
				System.out.println(obj.get("주메뉴") + "\t");
				System.out.println(obj.get("상 호") + "\t");
				System.out.println(obj.get("소재지") + "\n");
				
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}//main()
	
	
	
	
	
	
	
}//class
