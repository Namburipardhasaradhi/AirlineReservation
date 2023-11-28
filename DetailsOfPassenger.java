package in.srkr.project3.com;


public class DetailsOfPassenger {
    private String FirstName;
    private String LastName;
    private int PassportId;
    private String Nationality;
    private String Gender;
    private String contactNo;
    private Seat seat;
    private static int TravelPrice;
	public DetailsOfPassenger(String FirstName,String LastName, int passportId, String nationality, String gender, 
			String contactNo, int travelPrice) {
		super();
		this.FirstName=FirstName;
		this.LastName=LastName;
		PassportId = passportId;
		Nationality = nationality;
		Gender = gender;
		this.contactNo = contactNo;
		this.seat = seat;
		TravelPrice = travelPrice;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getPassportId() {
		return PassportId;
	}
	public void setPassportId(int passportId) {
		PassportId = passportId;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public static int getTravelPrice() {
		return TravelPrice;
	}
	public static void setTravelPrice(int travelPrice) {
		TravelPrice = 8000;
	}
	@Override
	public String toString() {
		return "DetailsOfPassenger [FirstName=" + FirstName + ", LastName=" + LastName + ", PassportId=" + PassportId
				+ ", Nationality=" + Nationality + ", Gender=" + Gender + ", contactNo=" + contactNo + ", seat=" + seat
				+ "]";
	}
	
}
