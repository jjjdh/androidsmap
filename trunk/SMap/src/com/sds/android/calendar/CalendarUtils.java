//package com.sds.android.calendar;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.google.gdata.client.calendar.CalendarQuery;
//import com.google.gdata.client.calendar.CalendarService;
//import com.google.gdata.data.DateTime;
//import com.google.gdata.data.calendar.CalendarEventEntry;
//import com.google.gdata.data.calendar.CalendarEventFeed;
//import com.google.gdata.data.extensions.When;
//import com.google.gdata.util.AuthenticationException;
//import com.google.gdata.util.ServiceException;
//
//public class CalendarUtils {
//
//	// 구글 캘린더에서 사용할 Google Account Email 주소
//	String gLoginAddr = "androidsmap@gmail.com";
//	// Google Calendar 패스워드인 Google Account 패스워드
//	String gLoginPass = "smap5935";
//	String postUrl = "http://www.google.com/calendar/feeds/default/private/full";
//
//	public List<CalendarVO> getCalendarInfo(String startTime, String endTime) {
//
//		ArrayList<CalendarVO> calendarVoList = new ArrayList<CalendarVO>();
//
//		startTime = "2010-01-16T00:00:00";
//		endTime = "2010-12-24T23:59:59";
//
//		CalendarQuery myQuery = null;
//		// Google Calendar서비스에 접속
//		CalendarService myService = new CalendarService(
//				"exampleCo-exampleApp-1");
//		CalendarEventFeed resultFeed = null;
//
//		try {
//			// Google Calendar 서비스 인증
//			myService.setUserCredentials(gLoginAddr, gLoginPass);
//			myQuery = new CalendarQuery(new URL(postUrl));
//			myQuery.setMinimumStartTime(DateTime.parseDateTime(startTime));
//			myQuery.setMaximumStartTime(DateTime.parseDateTime(endTime));
//			resultFeed = myService.query(myQuery, CalendarEventFeed.class);
//
//		} catch (MalformedURLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (AuthenticationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
//			CalendarVO calendarVO = new CalendarVO();
//
//			CalendarEventEntry firstMatchEntry = resultFeed.getEntries().get(i);
//
//			calendarVO.setTitle(firstMatchEntry.getTitle().getPlainText());
//			calendarVO.setContent(firstMatchEntry.getPlainTextContent());
//			calendarVO.setLocation(firstMatchEntry.getLocations().get(0)
//					.getValueString());
//
//			//일정 가져오기 (시작,종료일시)
//			List timeList = firstMatchEntry.getTimes();
//
//			When eventTime = (When) timeList.get(0);
//			DateTime _startTime = eventTime.getStartTime();
//			DateTime _endTime = eventTime.getEndTime();
//			String startTimeStr = _startTime.toUiString();
//			String endTimeStr = _endTime.toUiString();
//
//			calendarVO.setStartTime(startTimeStr);
//			calendarVO.setEndTime(endTimeStr);
//			
//			calendarVoList.add(calendarVO);
//		}
//
//		return calendarVoList;
//	}
//	public static void main(String[] args) {
//		CalendarUtils util = new CalendarUtils();
//		System.out.println(util.getCalendarInfo("", ""));
//	}
//
//}
