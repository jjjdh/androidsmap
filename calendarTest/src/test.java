import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.google.gdata.client.Query;
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.data.extensions.Where;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
public class test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// 구글 캘린더에서 사용할 Google Account Email 주소

		String gLoginAddr = "androidsmap@gmail.com";
		// Google Calendar 패스워드인 Google Account 패스워드
		String gLoginPass = "smap5935";
		// Calendar 등록용：스케줄의 제목 (한글 사용가능)
		String title = "고객 미팅";
		// Calendar 등록용：장소 (한글 사용가능)
		String place = "삼성동 코엑스";
		// Calendar 등록용：내용 ( 한글 사용가능 )
		String memo = "제품계약";

		// Google Calendar용 서비스 URL 설정
		URL postUrl;
		try {
			postUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");
			
			// 이벤트 등록용 클래스 정의
			EventEntry myEntry = new EventEntry();
			// 스케줄의 타이틀 (title 은 앞에서 정의한 것입니다.)
			myEntry.setTitle(new PlainTextConstruct(title));
			// 스케줄 상세 정보 (memo 는 앞에서 정의한 것입니다.)
			myEntry.setContent(new PlainTextConstruct(memo));
			// 작성 애플리케이션명
			Person author = new Person("Google Calendar Application", null,	gLoginAddr);
			myEntry.getAuthors().add(author);
			// 개시 일시를 DateTime 형태 오브젝트로 설정
			DateTime startTime = new DateTime(new Date(2010 - 1900, 6 - 1, 23, 12, 0));
			startTime.setTzShift(9);
			// 종료 일시를 DateTime형태 오브젝트로 설정
			DateTime endTime = new DateTime(new Date(2010 - 1900, 6 - 1, 23, 13, 0));
			endTime.setTzShift(9);
			// 개시 종료 일시를 When형태 오브젝트에 대입해 이벤트 클래스에 추가
			When eventTimes = new When();
			eventTimes.setStartTime(startTime);
			eventTimes.setEndTime(endTime);
			myEntry.addTime(eventTimes);
			// 장소를 Where형태 오브젝트에 대입해 이벤트 클래스에 추가
			Where evLocation = new Where();
			// place 는 앞에서 정의한 것이다.
			evLocation.setValueString(place);
			myEntry.addLocation(evLocation);
			// Google Calendar서비스에 접속
			CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
			// Google Calendar 서비스 인증
			myService.setUserCredentials(gLoginAddr, gLoginPass);
			// 스케줄을 추가한다
			//EventEntry insertedEntry = myService.insert(postUrl, myEntry);
			// 특정 스케줄을 조작하는 리퀘스트를 취득
			//URL entryUrl = new URL(insertedEntry.getSelfLink().getHref());
			//EventEntry retrievedEntry = myService.getEntry(entryUrl, EventEntry.class);
			/*
			//특정의 스케줄을 찾는다
			Query myQuery = new Query(postUrl);
			myQuery.setFullTextQuery("고객 미팅");
			Feed myResultsFeed = myService.query(myQuery, Feed.class);
			/*
			if (myResultsFeed.getEntries().size() > 0)
			{
				Entry firstMatchEntry = myResultsFeed.getEntries().get(0); 
				System.out.println("Titie: " + firstMatchEntry.getTitle().getPlainText());
				System.out.println("Summary: " + firstMatchEntry.getSummary().getPlainText());
			}
			*/
			/*
			for (int i = 0; i < myResultsFeed.getEntries().size(); i++)
			{
				Entry firstMatchEntry = myResultsFeed.getEntries().get(i); 
				System.out.println("설명: " + firstMatchEntry.getPlainTextContent());
				System.out.println("장소: " + firstMatchEntry.getVersionId());
				System.out.println("Location: " + firstMatchEntry.getPublished().toUiString());
			}
			*/
			CalendarQuery myQuery = new CalendarQuery(postUrl);
			myQuery.setMinimumStartTime(DateTime.parseDateTime("2010-01-16T00:00:00"));
			myQuery.setMaximumStartTime(DateTime.parseDateTime("2010-12-24T23:59:59"));
			CalendarEventFeed resultFeed = myService.query(myQuery, CalendarEventFeed.class);
			//myService2.setUserCredentials("kindruth@gmail.com", "roskfl!@");
			
			for (int i = 0; i < resultFeed.getEntries().size(); i++)
			{
				CalendarEventEntry firstMatchEntry = resultFeed.getEntries().get(i);
				
				System.out.println("제목: " + firstMatchEntry.getTitle().getPlainText());
				System.out.println("설명: " + firstMatchEntry.getPlainTextContent());
				//firstMatchEntry.getLocations().get(0).toString();
				System.out.println("장소: " + firstMatchEntry.getLocations().get(0).getValueString());
				
				List timeList = firstMatchEntry.getTimes();

				for (int j=0; j<timeList.size();j++){

				When eventTime = (When) timeList.get(j);

				DateTime startTime1 = eventTime.getStartTime();

				DateTime endTime1 = eventTime.getEndTime();

				String startTimeStr = startTime1.toUiString();

				String endTimeStr = endTime1.toUiString();
				System.out.println("시작 일시: " + startTimeStr);
				System.out.println("종료 일시: " + endTimeStr);
				}
			}
			
			
			
			// 특정의 이벤트의 정보를 갱신
			myEntry.setTitle(new PlainTextConstruct("긴급 회의"));
			//URL editUrl = new URL(retrievedEntry.getEditLink(). getHref());
			//EventEntry updatedEntry = myService.update(editUrl, myEntry);

		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
