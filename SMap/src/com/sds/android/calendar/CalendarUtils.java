package com.sds.android.calendar;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

public class CalendarUtils {

	String gLoginAddr = "androidsmap@gmail.com";
	String gLoginPass = "smap5935";
	String postUrl = "http://www.google.com/calendar/feeds/default/private/full";

	public List<CalendarVO> getCalendarInfo(String startTime, String endTime) {

		ArrayList<CalendarVO> calendarVoList = new ArrayList<CalendarVO>();

		startTime = "2010-01-16T00:00:00";
		endTime = "2010-12-24T23:59:59";

		CalendarQuery myQuery = null;
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
		CalendarEventFeed resultFeed = null;

		try {
			myService.setUserCredentials(gLoginAddr, gLoginPass);
			myQuery = new CalendarQuery(new URL(postUrl));
			myQuery.setMinimumStartTime(DateTime.parseDateTime(startTime));
			myQuery.setMaximumStartTime(DateTime.parseDateTime(endTime));
			resultFeed = myService.query(myQuery, CalendarEventFeed.class);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if(resultFeed!=null){

		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			CalendarVO calendarVO = new CalendarVO();

			CalendarEventEntry firstMatchEntry = resultFeed.getEntries().get(i);

			calendarVO.setTitle(firstMatchEntry.getTitle().getPlainText());
			calendarVO.setContent(firstMatchEntry.getPlainTextContent());
			calendarVO.setLocation(firstMatchEntry.getLocations().get(0)
					.getValueString());

			List timeList = firstMatchEntry.getTimes();

			When eventTime = (When) timeList.get(0);
			DateTime _startTime = eventTime.getStartTime();
			DateTime _endTime = eventTime.getEndTime();
			String startTimeStr = _startTime.toUiString();
			String endTimeStr = _endTime.toUiString();

			calendarVO.setStartTime(startTimeStr);
			calendarVO.setEndTime(endTimeStr);
			
			calendarVoList.add(calendarVO);
		}
		}else{
			Log.d("kshgizmo", getClass().getSimpleName()+"result null");
		}

		return calendarVoList;
	}
	

}
