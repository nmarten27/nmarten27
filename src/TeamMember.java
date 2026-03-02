
public class TeamMember {
	
	private int teamId;
	private String studentId;
	private String role;
	private String joinDate;
	private String position;
	
	public TeamMember(int teamId, String studentId, String role, String joinDate, String position) {
		this.teamId = teamId;
		this.studentId = studentId;
		this.role = role;
		this.joinDate = joinDate;
		this.position = position;
	}
	
	public TeamMember(int teamId, String studentId) {
		this.teamId = teamId;
		this.studentId = studentId;
	}
	
	public int getTeamId() {
		return teamId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String toString() {
		return "TeamMember [Team ID = " + teamId + ", Student ID = " + studentId + ", Role = " + role + ", Join Date = " + joinDate
				+ ", Position = " + position + "]";
	}
	
}
