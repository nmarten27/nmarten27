
public class IntramuralTeam {
	private int teamId;
	private int divisionId;
	private String name;
	private String captainId;
	private int wins;
	private int losses;
	private int ties;
	private int numPlayers;
	
	public IntramuralTeam(int teamId, int divisionId, String name, String captainId, int wins, int losses, int ties, int numPlayers) {
		this.teamId = teamId;
		this.divisionId = divisionId;
		this.name = name;
		this.captainId = captainId;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.numPlayers = numPlayers;
	}
	
	public IntramuralTeam(int teamID) {
		this.teamId = teamID;
	}
	
	public int getTeamId() {
		return teamId;
	}
	
	public int getDivisionId() {
		return divisionId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCaptainId() {
		return captainId;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public int getTies() {
		return ties;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCaptainId(String captainId) {
		this.captainId = captainId;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public void setTies(int ties) {
		this.ties = ties;
	}
	
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public String toString() {
		return "Team [Team ID = " + teamId + ", Division ID = " + divisionId + ", Team Name = " + name + ", Captain ID = " + captainId + ", Wins = " + wins + ", Losses = " + losses + ", Ties = " + ties + ", Num Players = " + numPlayers + "]";
	}
	
}