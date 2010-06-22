import com.google.gdata.data.DateTime;


public class CalendarVO {
//	제목: 고객 미팅2
//	설명: 제품계약2
//	장소: 삼성동 코엑스
//	시작 일시: 2010-06-23 12:00
//	종료 일시: 2010-06-23 13:00
	String title;
	String content;
	String location;
	String startTime;
	String endTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
		public String toString() {
			return title+" / "+content+" / "+startTime+" / "+endTime+" / "+location;
		}

}
