import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IntramuralDatabase {

		private String url = "jdbc:mysql://138.49.184.123:3306/";
		private String dbName = "marten3239_CS364Final";
		private String username = "marten3239";
		private String password;
		private Connection connection;
		
		public IntramuralDatabase() {
			password = "jGAtkULSChcAv!nS6";

		}
		
		public void connect() {
			try {
				url = url + dbName + "?";
				connection = DriverManager.getConnection(url, username, password);

			} catch (SQLException e) {
				System.out.println("Cannot connect!");
				System.out.println(e);
			}
		}
		
		public void disconnect() {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Cannot disconnect!");
			}
		}

		public ResultSet runQuery(String query) throws SQLException {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet results = stmt.executeQuery();
			return results;
		}
		
		public void insertIntramuralStudent(IntramuralStudent s) throws SQLException {
			String sql = "INSERT INTO Student (student_id, first_name, last_name, uwl_email, grad_year, eligibility, gender) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, s.getStudentId());
			stmt.setString(2, s.getFirstName());
			stmt.setString(3, s.getLastName());
			stmt.setString(4, s.getUWLEmail());
			stmt.setInt(5, s.getGradYear());
			stmt.setBoolean(6, s.isEligible());
			stmt.setString(7, s.getGender());
			int numRowsAffected = stmt.executeUpdate();
			System.out.println("Number of students affected: " + numRowsAffected);
		}
		
		public IntramuralStudent getIntramuralStudent(String studentId) throws SQLException {
			String query = "SELECT * FROM Student WHERE student_id = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, studentId);
			ResultSet results = stmt.executeQuery();
			IntramuralStudent s = null;
			if(results.next()) {
				String student_id = results.getString("student_id");
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				String uwlEmail = results.getString("uwl_email");
				int gradYear = results.getInt("grad_year");
				boolean eligibility = results.getBoolean("eligibility");
				String gender = results.getString("gender");							
				s = new IntramuralStudent(student_id, firstName, lastName, uwlEmail, gradYear, eligibility, gender);
			}
			return s;
		}
		
		public void updateIntramuralStudentUWLEmail(IntramuralStudent s, String uwlEmail) throws SQLException {
			String sql = "UPDATE Student SET uwl_email = ? WHERE student_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  uwlEmail);
			stmt.setString(2, s.getStudentId());
			stmt.executeUpdate();
			s.setUWLEmail(uwlEmail);;
			System.out.println();
			System.out.println("UWL Email Successfully Changed!");
		}
		
		public boolean deleteIntramuralStudent(IntramuralStudent s) throws SQLException {
			String sql = "DELETE FROM Student WHERE student_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  s.getStudentId());
			int numRowsAffected = stmt.executeUpdate();
			System.out.println("Number of students deleted: " + numRowsAffected);
			return numRowsAffected > 0;
		}
		
		public void insertIntramuralTeam(IntramuralTeam t) throws SQLException {
			String sql = "INSERT INTO Team (team_id, division_id, name, captain_id, wins, losses, ties, num_players) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  t.getTeamId());
			stmt.setInt(2,  t.getDivisionId());
			stmt.setString(3,  t.getName());
			stmt.setString(4,  t.getCaptainId());
			stmt.setInt(5,  t.getWins());
			stmt.setInt(6,  t.getLosses());
			stmt.setInt(7,  t.getTies());
			stmt.setInt(8,  t.getNumPlayers());
			int numRowsAffected = stmt.executeUpdate();
			System.out.println("Number of rows affected: " + numRowsAffected);
			
		}
		
		public IntramuralTeam getIntramuralTeam(int teamId) throws SQLException {
			String query = "SELECT * FROM Team WHERE team_id = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, teamId);
			ResultSet results = stmt.executeQuery();
			IntramuralTeam t = null;
			if(results.next()) {
				int team_id = results.getInt("team_id");
				int division_id = results.getInt("division_id");
				String name = results.getString("name");
				String captain_id = results.getString("captain_id");
				int wins = results.getInt("wins");
				int losses = results.getInt("losses");
				int ties = results.getInt("ties");
				int numPlayers = results.getInt("num_players");
							
				t = new IntramuralTeam(team_id, division_id, name, captain_id, wins, losses, ties, numPlayers);
			}
			return t;
		}
		
		public void updateIntramuralTeamName(IntramuralTeam t, String name) throws SQLException {
			String sql = "UPDATE Team SET name = ? WHERE team_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  name);
			stmt.setInt(2, t.getTeamId());
			stmt.executeUpdate();
			t.setName(name);
		}
		
		public boolean deleteIntramuralTeam(IntramuralTeam t) throws SQLException {
			String sql = "DELETE FROM Team WHERE team_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  t.getTeamId());
			int numRowsAffected = stmt.executeUpdate();
			return numRowsAffected > 0;
		}
		
		public void insertTeamMember(TeamMember tm) throws SQLException {
			String sql = "INSERT INTO TeamMember (team_id, student_id, role, join_date, position) " + "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  tm.getTeamId());
			stmt.setString(2,  tm.getStudentId());
			stmt.setString(3,  tm.getRole());
			stmt.setString(4,  tm.getJoinDate());
			stmt.setString(5,  tm.getPosition());
			int numRowsAffected = stmt.executeUpdate();
			System.out.println("Number of rows affected: " + numRowsAffected);
			
		}
		
		public ArrayList<TeamMember> getTeamMembers(int teamId) throws SQLException {
			String query = "SELECT * FROM TeamMember WHERE team_id = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, teamId);
			ResultSet results = stmt.executeQuery();
			ArrayList<TeamMember> team = new ArrayList<>();
			while(results.next()) {
				int team_id = results.getInt("team_id");
				String student_id = results.getString("student_id");
				String role = results.getString("role");
				String joinDate = results.getString("join_date");
				String position = results.getString("position");
				
				TeamMember tm = new TeamMember(team_id, student_id, role, joinDate, position);
				team.add(tm);
			}
			return team;
		}
		
		public void updateTeamMemberPosition(TeamMember tm, String position) throws SQLException {
			String sql = "UPDATE TeamMember SET position = ? WHERE team_id = ? AND student_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, position);
			stmt.setInt(2,  tm.getTeamId());
			stmt.setString(3, tm.getStudentId());
			stmt.executeUpdate();
			tm.setPosition(position);
		}
			
		public boolean deleteTeamMember(TeamMember tm) throws SQLException {
			String sql = "DELETE FROM TeamMember WHERE team_id = ? AND student_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, tm.getTeamId());
			stmt.setString(2,  tm.getStudentId());
			int numRowsAffected = stmt.executeUpdate();
			return numRowsAffected > 0;
		
		}
		
		public void queryIneligiblePlayers(String queryTeamName, int limit) throws SQLException {
			String sql = "SELECT t.name AS Team, COUNT(*) AS IneligibleCount\n"
					+ "FROM Team AS t\n"
					+ "INNER JOIN TeamMember AS tm ON tm.team_id = t.team_id\n"
					+ "INNER JOIN Student AS s ON s.student_id = tm.student_id\n"
					+ "WHERE s.eligibility = FALSE AND t.name LIKE ?\n"
					+ "GROUP BY t.team_id\n"
					+ "ORDER BY Count(*) DESC\n"
					+ "LIMIT ?;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  queryTeamName);
			stmt.setInt(2, limit);
			ResultSet results = stmt.executeQuery();
			System.out.println();
			System.out.println("Ineligible Player Checker Results:");
			while(results.next()) {
				String team = results.getString("Team");
				int count = results.getInt("IneligibleCount");
				System.out.println();
				System.out.println("Team Name: " + team + ", Ineligible Players: " + count);
			}
		}
		
		public void queryCaptainWins(String queryCaptainName, int limit2) throws SQLException {
			String sql = "SELECT s.first_name AS FirstName, s.last_name AS LastName, t.captain_id AS CaptainID, COUNT(gr.winning_team_id) AS Wins\n"
					+ "FROM Team AS t\n"
					+ "INNER JOIN Student AS s ON s.student_id = t.captain_id\n"
					+ "LEFT JOIN GameResult AS gr ON gr.winning_team_id = t.team_id\n"
					+ "WHERE s.first_name LIKE ?\n"
					+ "GROUP BY t.captain_id\n"
					+ "ORDER BY Wins DESC\n"
					+ "LIMIT ?;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  queryCaptainName);
			stmt.setInt(2, limit2);
			ResultSet results = stmt.executeQuery();
			System.out.println();
			System.out.println("Captains Wins Checker Results:");
			while(results.next()) {
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int wins = results.getInt("Wins");
				System.out.println();
				System.out.println("Captain: " + firstName + " " + lastName + ", Wins: " + wins);
			}
		}
		
		public void queryGamesByDate(String gameDate, int minStaff, int limit3, int offset) throws SQLException {
			String sql = "SELECT l.name AS League, g.game_date AS Date, g.start_time AS Time, v.name AS Venue, ht.name AS HomeTeam, at.name AS AwayTeam, wt.name AS WinningTeam, COUNT(gs.staff_id) AS StaffCount\n"
					+ "FROM Game AS g\n"
					+ "LEFT JOIN GameResult AS gr ON gr.game_id = g.game_id\n"
					+ "LEFT JOIN Team AS ht ON ht.team_id = g.home_team_id\n"
					+ "LEFT JOIN Team AS at ON at.team_id = g.away_team_id\n"
					+ "LEFT JOIN Team AS wt ON wt.team_id = gr.winning_team_id\n"
					+ "LEFT JOIN Venue AS v ON v.venue_id = g.venue_id\n"
					+ "LEFT JOIN League AS l ON l.league_id = g.league_id\n"
					+ "LEFT JOIN GameStaff AS gs ON gs.game_id = g.game_id\n"
					+ "WHERE g.game_date = ?\n"
					+ "GROUP BY g.game_id\n"
					+ "HAVING COUNT(gs.staff_id) > ?\n"
					+ "ORDER BY g.start_time\n"
					+ "LIMIT ?\n"
					+ "OFFSET ?;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  gameDate);
			stmt.setInt(2, minStaff);
			stmt.setInt(3, limit3);
			stmt.setInt(4,  offset);
			ResultSet results = stmt.executeQuery();
			System.out.println();
			System.out.println("Games on Specific Date Checker Results:");
			while(results.next()) {
				String league = results.getString("League");
				String time = results.getString("Time");
				String venue = results.getString("Venue");
				String homeTeam = results.getString("HomeTeam");
				String awayTeam = results.getString("AwayTeam");
				String winningTeam = results.getString("WinningTeam");
				int staffCount = results.getInt("StaffCount");
				System.out.println();
				System.out.println("League Name: " + league + ", Time: " + time + ", Venue: " + venue + ", Home Team: " + homeTeam + ", Away Team: " + awayTeam + ", Winning Team: " + winningTeam + ", Staff Count: " + staffCount);
			}
		}
		
		public void queryLowParticipation(int maxTeams, int limit4, int offset2) throws SQLException {
			String sql = "SELECT l.name AS League, COUNT(DISTINCT t.team_id) AS TeamCount\n"
					+ "FROM League AS l\n"
					+ "INNER JOIN Season AS se ON se.season_id = l.season_id\n"
					+ "INNER JOIN Sport AS s ON s.sport_id = se.sport_id\n"
					+ "LEFT JOIN Division AS d ON d.league_id = l.league_id\n"
					+ "LEFT JOIN Team AS t ON t.division_id = d.division_id\n"
					+ "GROUP BY l.league_id\n"
					+ "HAVING TeamCount < ?\n"
					+ "ORDER BY TeamCount ASC, League\n"
					+ "LIMIT ?\n"
					+ "OFFSET ?;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  maxTeams);
			stmt.setInt(2, limit4);
			stmt.setInt(3, offset2);
			ResultSet results = stmt.executeQuery();
			System.out.println();
			System.out.println("Leagues With Fewest Teams Checker Results:");
			while(results.next()) {
				String league = results.getString("League");
				int count = results.getInt("TeamCount");
				System.out.println();
				System.out.println("League: " + league + ", Number of Teams: " + count);
			}
		}
		
		public void queryPlayersMultipleTeams(int minTeams) throws SQLException {
			String sql = "SELECT s.student_id AS StudentID, s.first_name AS FirstName, s.last_name AS LastName, t.name AS Team\n"
					+ "FROM Student AS s\n"
					+ "INNER JOIN TeamMember AS tm ON tm.student_id = s.student_id\n"
					+ "INNER JOIN Team AS t ON t.team_id = tm.team_id\n"
					+ "WHERE tm.student_id IN (\n"
					+ "	SELECT student_id\n"
					+ "    FROM TeamMember\n"
					+ "    GROUP BY student_id\n"
					+ "    HAVING COUNT(DISTINCT team_id) > ?\n"
					+ ")\n"
					+ "ORDER BY s.student_id;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  minTeams);
			ResultSet results = stmt.executeQuery();
			System.out.println();
			System.out.println("Players on Multiple Teams Checker Results:");
			while(results.next()) {
				String studentId = results.getString("StudentID");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String teamName = results.getString("Team");
				System.out.println();
				System.out.println("Student ID: " + studentId + ", First Name: " + firstName + ", Last Name: " + lastName + ", Team Name: " + teamName);
			}
		}
		
		public void queryMostPlayers(int minPlayers) throws SQLException {
			String sql = "SELECT t.name AS TeamName, COUNT(tm.student_id) AS RosterSize\n"
					+ "FROM Team AS t\n"
					+ "INNER JOIN TeamMember AS tm ON tm.team_id = t.team_id\n"
					+ "WHERE t.team_id IN (\n"
					+ "	SELECT team_id\n"
					+ "    FROM TeamMember\n"
					+ "    GROUP by team_id\n"
					+ "    HAVING COUNT(student_id) >= ?\n"
					+ ")\n"
					+ "GROUP BY t.name\n"
					+ "ORDER BY RosterSize DESC;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  minPlayers);
			ResultSet results = stmt.executeQuery();
			System.out.println();
			System.out.println("Largest Rosters Checker Results:");
			while(results.next()) {
				String teamName = results.getString("TeamName");
				int rosterSize = results.getInt("RosterSize");
				System.out.println();
				System.out.println("Team: " + " " + teamName + ", Roster Size: " + rosterSize);
			}
		}
}