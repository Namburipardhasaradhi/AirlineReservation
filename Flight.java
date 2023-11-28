package in.srkr.project3.com;
import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
		
		private int flightNO;
		private String flightName;
		private String FromCity;
		private String ToCity;
		private LocalDate LocalDate; 
	    private LocalTime LocalTime; 
		private String AirPortName;
		public Flight(int flightNO, String flightName, String fromCity, String toCity, java.time.LocalDate localDate,java.time.LocalTime localTime,
				String airPortName) {
			super();
			this.flightNO = flightNO;
			this.flightName = flightName;
			FromCity = fromCity;
			ToCity = toCity;
			LocalDate = localDate;
			LocalTime = localTime;
			AirPortName = airPortName;
		}
		public int getFlightNO() {
			return flightNO;
		}
		public void setFlightNO(int flightNO) {
			this.flightNO = flightNO;
		}
		public String getFlightName() {
			return flightName;
		}
		public void setFlightName(String flightName) {
			this.flightName = flightName;
		}
		public String getFromCity() {
			return FromCity;
		}
		public void setFromCity(String fromCity) {
			FromCity = fromCity;
		}
		public String getToCity() {
			return ToCity;
		}
		public void setToCity(String toCity) {
			ToCity = toCity;
		}
		public LocalDate getLocalDate() {
			return LocalDate;
		}
		public void setLocalDate(LocalDate localDate) {
			LocalDate = localDate;
		}
		public LocalTime getLocalTime() {
			return LocalTime;
		}
		public void setLocalTime(LocalTime localTime) {
			LocalTime = localTime;
		}
		public String getAirPortName() {
			return AirPortName;
		}
		public void setAirPortName(String airPortName) {
			AirPortName = airPortName;
		}
		@Override
		public String toString() {
			return "Flight [flightNO=" + flightNO + ", flightName=" + flightName + ", FromCity=" + FromCity
					+ ", ToCity=" + ToCity + ", LocalDate=" + LocalDate + ", LocalTime=" + LocalTime + ", AirPortName="
					+ AirPortName + "]";
		}	
}
