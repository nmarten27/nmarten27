
public class IntramuralStudent {
	
	private String studentId;
	private String firstName;
	private String lastName;
	private String uwlEmail;
	private int gradYear;
	private boolean eligibility;
	private String gender;
	
	public IntramuralStudent(String studentId, String firstName, String lastName, String uwlEmail, int gradYear, boolean eligibility, String gender) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.uwlEmail = uwlEmail;
		this.gradYear = gradYear;
		this.eligibility = eligibility;
		this.gender = gender;
	}
	
	public IntramuralStudent(String studentID) {
		this.studentId = studentID;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUWLEmail() {
		return uwlEmail;
	}
	
	public int getGradYear() {
		return gradYear;
	}
	
	public boolean isEligible() {
		return eligibility;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUWLEmail(String uwlEmail) {
		this.uwlEmail = uwlEmail;
	}
	
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	
	public void setEligibility(boolean eligibility) {
		this.eligibility = eligibility;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String toString() {
		return "Student: [Student ID = " + studentId + ", First Name = " + firstName + ", Last Name = " + lastName + ", UWL Email = " + uwlEmail
				+ ", Grad Year = " + gradYear + ", Eligible = " + eligibility + ", Gender = " + gender + "]";
	}
}