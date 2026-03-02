import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class IntramuralSystem {

	public static void main(String[] args) {

		IntramuralDatabase db = new IntramuralDatabase();
		db.connect();
		System.out.println("Successfully connected!\n");
		
		Scanner scanner = new Scanner(System.in);
		boolean parsing = true;
		
		while (parsing) {
			System.out.println("Welcome to the Intramural Database!");
			System.out.println("How can we help you today?");
			System.out.println("1. Manage Students");
			System.out.println("2. Manage Teams");
			System.out.println("3. Manage Team Members");
			System.out.println("4. Database Operations");
			System.out.println("5. Exit\n");
			System.out.print("Enter Option (1-5): ");
			
			int option = scanner.nextInt();
			scanner.nextLine();
			
			if (option == 1) {
				studentOption(db, scanner);
			} else if (option == 2) {
				teamOption(db, scanner);			
			} else if (option == 3) {
				teamMemberOption(db, scanner);
			} else if (option == 4) {
				databaseOperations(db, scanner);
			} else if (option == 5) {
				parsing = false;
			} else {
				System.out.println();
				System.out.println("\nNot a valid option!\n");
				System.out.println();
			}	
		}
		db.disconnect();
		System.out.println("\nSuccessfully disconnected!\n");
		scanner.close();
	}
	
	public static void studentOption(IntramuralDatabase db, Scanner scanner) {
		boolean exit = false;
		
		while (!exit) {
			System.out.println("\nManage Student Options:\n");
			System.out.println("1. Add Student");
			System.out.println("2. View Student");
			System.out.println("3. Update Student Email");
			System.out.println("4. Delete Student");
			System.out.println("5. Exit\n");
			
			System.out.print("Enter Option (1-5): ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			if (option == 1) {
				System.out.println("\nEnter Student Information:\n");
				System.out.print("Student ID: ");
				String studentId = scanner.nextLine();
				System.out.print("First Name: ");
				String firstName = scanner.nextLine();
				System.out.print("Last Name: ");
				String lastName = scanner.nextLine();
				System.out.print("UWL Email: ");
				String email = scanner.nextLine();
				System.out.print("Gradutation Year: ");
				int gradYear = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Eligible (True / False): ");
				boolean eligible = scanner.nextBoolean();
				scanner.nextLine();
				System.out.print("Gender: ");
				String gender = scanner.nextLine();	
				create_student_database(db, studentId, firstName, lastName, email, gradYear, eligible, gender);
				System.out.println("\nStudent Successfully Added!\n");
			} else if (option == 2) {
				System.out.print("\nEnter Student Information:\n");
				System.out.print("\nStudent ID: ");
				read_students_from_database2(db, scanner.nextLine());
			} else if (option == 3) {
				System.out.print("\nEnter Student Information:\n");
				System.out.print("\nStudent ID: ");
				String studentID2 = scanner.nextLine();
				System.out.print("New UWL Email: ");
				String newEmail = scanner.nextLine();
				IntramuralStudent tempStudent = new IntramuralStudent(studentID2);
				update_email_database(db, tempStudent, newEmail);
			} else if (option == 4) {
				System.out.println("\nEnter Student Information:\n");
				System.out.print("Student ID: ");
				IntramuralStudent deletedStudent = new IntramuralStudent(scanner.nextLine());
				delete_student_database(db, deletedStudent);
				System.out.print("\nStudent Successfully Deleted!\n");
			} else if (option == 5) {
				exit = true;
			} else {
				System.out.println("\nNot a valid option!\n");
			}
		}
	}
	
	public static void teamOption(IntramuralDatabase db, Scanner scanner) {
		boolean exit = false;
		
		while (!exit) {
			System.out.println("\nManage Team Options:\n");
			System.out.println("1. Create Team");
			System.out.println("2. View Team");
			System.out.println("3. Update Team Name");
			System.out.println("4. Delete Team");
			System.out.println("5. Exit\n");
			
			System.out.print("Enter Option (1-5): ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			if (option == 1) {
				System.out.println("\nEnter Team Information:\n");
				System.out.print("Team ID: ");
				int teamID = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Division ID: ");
				int divisionID = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Team Name: ");
				String teamName = scanner.nextLine();
				System.out.print("Captain ID: ");
				String captainID = scanner.nextLine();
				System.out.print("Max Players: ");
				int maxPlayers = scanner.nextInt();
				scanner.nextLine();			
				create_team_database(db, teamID, divisionID, teamName, captainID, 0, 0, 0, maxPlayers);
				System.out.print("\nTeam Successfully Created!\n");
			} else if (option == 2) {
				System.out.println("\nEnter Team Information:\n");
				System.out.print("Team ID: ");
				read_teams_from_database2(db, scanner.nextInt());
				scanner.nextLine();
				System.out.println();
			} else if (option == 3) {
				System.out.println("\nEnter Team Information:\n");
				System.out.print("Team ID: ");
				int teamID2 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("New Team Name: ");
				String newTeamName = scanner.nextLine();
				IntramuralTeam tempTeam = new IntramuralTeam(teamID2);
				update_team_name_database(db, tempTeam, newTeamName);
				System.out.print("\nTeam Name Successfully Updated!\n");
			} else if (option == 4) {
				System.out.println("\nEnter Team Information:\n");
				System.out.print("Team ID: ");
				IntramuralTeam deletedTeam = new IntramuralTeam(scanner.nextInt());
				scanner.nextLine();
				delete_team_database(db, deletedTeam);
				System.out.println("\nTeam Successfully Deleted!");
			} else if (option == 5) {
				exit = true;
			} else {
				System.out.println("\nNot a valid option!\n");
			}
		}
	}
	
	public static void teamMemberOption(IntramuralDatabase db, Scanner scanner) {
		boolean exit = false;
		
		while(!exit) {
			System.out.println("\nManage Team Member Options\n");
			System.out.println("1. Add Team Member");
			System.out.println("2. View Team Members");
			System.out.println("3. Update Team Member Position");
			System.out.println("4. Delete Team Member");
			System.out.println("5. Exit\n");
			
			System.out.print("Enter Option (1-5): ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			if (option == 1) {
				System.out.println("\nEnter Team Member Information:\n");
				System.out.print("Team ID: ");
				int teamID = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Student ID: ");
				String studentID = scanner.nextLine();
				System.out.print("Role: ");
				String role = scanner.nextLine();
				System.out.print("Join Date (YYYY-MM-DD): ");
				String joinDate = scanner.nextLine();
				create_team_member_database(db, teamID, studentID, role, joinDate, "NULL");
				System.out.print("\nTeam Member Successfully Added!\n");
			} else if (option == 2) {
				System.out.println("\nEnter Team Member Information:\n");
				System.out.print("Team ID: ");
				read_team_members_from_database(db, scanner.nextInt());
				scanner.nextLine();
				System.out.println();
			} else if (option == 3) {
				System.out.println("\nEnter Team Member Information:\n");
				System.out.print("Student ID: ");
				String studentID2 = scanner.nextLine();
				System.out.print("Team ID: ");
				int teamID2 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("New Position: ");
				String newPosition = scanner.nextLine();
				TeamMember tempTeamMember = new TeamMember(teamID2, studentID2);
				update_position_database(db, tempTeamMember, newPosition);
				System.out.print("Team Member Position Successfully Updated!\n");
			} else if (option == 4) {
				System.out.println("\nEnter Team Member Information:\n");
				System.out.print("Student ID: ");
				String studentID3 = scanner.nextLine();
				System.out.print("Team ID: ");
				int teamID3 = scanner.nextInt();
				scanner.nextLine();
				delete_team_member_database(db, new TeamMember(teamID3, studentID3));
				System.out.print("\nTeam Member Successfully Deleted!\n");
			} else if (option == 5) {
				exit = true;
			} else {
				System.out.println("\nNot a valid option!\n");
			}
		}
	}
	
	public static void databaseOperations(IntramuralDatabase db, Scanner scanner) {
		boolean exit = false;
		
		while (!exit) {
			System.out.println("\nDatabase Operations Options\n");
			System.out.println("1. Check How Many Ineligible Players a Team Has");
			System.out.println("2. Find Which Players Have Won the Most Games as a Captain");
			System.out.println("3. Find All Games on a Specific Date");
			System.out.println("4. Find Leagues With the Fewest Number of Teams");
			System.out.println("5. Find Players That are on Multiple Teams");
			System.out.println("6. Find Teams That Have the Biggest Roster Size");
			System.out.println("7. Exit\n");
			
			System.out.println("Enter Option (1-7): ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			if (option == 1) {
				System.out.println("\nIneligible Player Checker\n");
				System.out.print("Team Name Pattern (e.g., Epic%): ");
				String queryTeamName = scanner.nextLine();
				System.out.print("Limit Results to _ Teams: ");
				int limit = scanner.nextInt();
				scanner.nextLine();
				try {
					db.queryIneligiblePlayers(queryTeamName, limit);
				} catch (SQLException ex) {
					System.out.println("Something went wrong when running the Ineligible Player Checker!");
					ex.printStackTrace();
				}
			} else if (option == 2) {
				System.out.println("\nCaptain Wins Checker\n");
				System.out.print("Captain Name Pattern (e.g., L%): ");
				String queryCaptainName = scanner.nextLine();
				System.out.print("Limit Results to _ Captains: ");
				int limit2 = scanner.nextInt();
				scanner.nextLine();
				try {
					db.queryCaptainWins(queryCaptainName, limit2);
				} catch (SQLException ex) {
					System.out.println("Something went wrong when running the Captain Wins Checker!");
					ex.printStackTrace();
				}
			} else if (option == 3) {
				System.out.println("\nGames on Specific Date Checker\n");
				System.out.print("Date (MM/DD/YYYY): ");
				String gameDate = scanner.nextLine();
				System.out.print("Number Staff Required: ");
				int minStaff = scanner.nextInt();
				System.out.print("Limit Results to _ Games: ");
				int limit3 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Hide First _ Games: ");
				int offset = scanner.nextInt();
				scanner.nextLine();
				try {
					db.queryGamesByDate(gameDate, minStaff, limit3, offset);
				} catch (SQLException ex) {
					System.out.println("Something went wrong when running the Games on Specific Dates Checker!");
					ex.printStackTrace();
				}
			} else if (option == 4) {
				System.out.println("\nLeagues With Fewest Teams Checker\n");
				System.out.print("Most Teams a League can Have: ");
				int maxTeams = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Limit Results to _ Leagues: ");
				int limit4 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Hide First _ Leagues: ");
				int offset2 = scanner.nextInt();
				scanner.nextLine();
				try {
					db.queryLowParticipation(maxTeams, limit4, offset2);
				} catch (SQLException ex) {
					System.out.println("Something went wrong when running the Leagues With Fewest Teams Checker!");
					ex.printStackTrace();
				}
			} else if (option == 5) {
				System.out.println("\nPlayers on Multiple Teams Checker\n");
				System.out.print("Smallest Number of Teams a Player can be on: ");
				int minTeams = scanner.nextInt();
				scanner.nextLine();
				try {
					db.queryPlayersMultipleTeams(minTeams);
				} catch (SQLException ex) {
					System.out.println("Something went wrong when running the Players on Multiple Teams Checker!");
					ex.printStackTrace();
				}
			} else if (option == 6) {
				System.out.println("\nLargest Rosters Checker\n");
				System.out.print("Smallest Roster Size Allowed: ");
				int minPlayers = scanner.nextInt();
				scanner.nextLine();				
				try { 
					db.queryMostPlayers(minPlayers);
				} catch (SQLException ex) {
					System.out.println("Something went wrong when running the Largest Rosters Checker!");
					ex.printStackTrace();
				}
			} else if (option == 7) {
				exit = true;
			} else {
				System.out.println("\nNot a valid option!\n");
			}							
		}
	}
	
	public static void read_students_from_database(IntramuralDatabase db) {
		try{
			String query = "SELECT * FROM Student";
			ResultSet results = db.runQuery(query);	 
			ArrayList<IntramuralStudent> lst = new ArrayList<>();
			while(results.next()) {
				String student_id = results.getString("student_id");
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				String uwlEmail = results.getString("uwl_email");
				int gradYear = results.getInt("grad_year");
				boolean eligibility = results.getBoolean("eligibility");
				String gender = results.getString("gender");			
				IntramuralStudent s = new IntramuralStudent(student_id, firstName, lastName, uwlEmail, gradYear, eligibility, gender);
				lst.add(s);
			}		
			for(IntramuralStudent s : lst) {
				System.out.println(s);
			}
		} catch(SQLException e) {
			System.out.println("Something went wrong when reading the contents of the Student table!");
			e.printStackTrace();
		}
		
	}

	public static IntramuralStudent read_students_from_database2(IntramuralDatabase db, String studentId) {
		try {
			IntramuralStudent s = db.getIntramuralStudent(studentId);
		 	System.out.println(s.toString());
			return s;		 		 
		} catch(SQLException e) {
			System.out.println("Something went wrong when looking up a student!");
			e.printStackTrace();
			return null;
		}		
	}

	public static IntramuralStudent create_student_database(IntramuralDatabase db, String studentId, String firstName, String lastName, String uwlEmail, int gradYear, boolean eligibility, String gender) {
		IntramuralStudent s = new IntramuralStudent(studentId, firstName, lastName, uwlEmail, gradYear, eligibility, gender);
		try {
			db.insertIntramuralStudent(s);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when inserting a new student");
			ex.printStackTrace();
		}
		return s;
	}

	public static void update_email_database(IntramuralDatabase db, IntramuralStudent s, String email){
		try {
			db.updateIntramuralStudentUWLEmail(s, email);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when updating a student email!");
			ex.printStackTrace();
		}
	}
	
	public static void delete_student_database(IntramuralDatabase db, IntramuralStudent s){
		try {
			db.deleteIntramuralStudent(s);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when deleting a student!");
			ex.printStackTrace();
		}
	}
	
	public static void read_teams_from_database(IntramuralDatabase db) {
		try{
			String query = "SELECT * FROM Team";
			ResultSet results = db.runQuery(query);	 
			ArrayList<IntramuralTeam> lst = new ArrayList<>();
			while(results.next()) {
				int team_id = results.getInt("team_id");
				int division_id = results.getInt("division_id");
				String name = results.getString("name");
				String captain_id = results.getString("captain_id");
				int wins = results.getInt("wins");
				int losses = results.getInt("losses");
				int ties = results.getInt("ties");
				int numPlayers = results.getInt("num_players");			
				IntramuralTeam t = new IntramuralTeam(team_id, division_id, name, captain_id, wins, losses, ties, numPlayers);
				lst.add(t);
			}		
			for(IntramuralTeam t : lst) {
				System.out.println(t);
			}
		} catch(SQLException e) {
			System.out.println("Something went wrong when reading the contents of the Team table!");
			e.printStackTrace();
		}	
	}
	
	public static IntramuralTeam read_teams_from_database2(IntramuralDatabase db, int teamId){
		try {
			IntramuralTeam t = db.getIntramuralTeam(teamId);
		 	System.out.println(t.toString());
			return t;	 		 
		} catch(SQLException e) {
			System.out.println("Something went wrong when looking up a team by team_id!");
			e.printStackTrace();
			return null;
		}	
	}

	public static IntramuralTeam create_team_database(IntramuralDatabase db, int teamId, int divisionId, String name, String captainId, int wins, int losses, int ties, int numPlayers) {
		IntramuralTeam t = new IntramuralTeam(teamId, divisionId, name, captainId, wins, losses, ties, numPlayers);
		try {
			db.insertIntramuralTeam(t);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when inserting a new team!");
			ex.printStackTrace();
		}
		return t;
	}
	
	public static void update_team_name_database(IntramuralDatabase db, IntramuralTeam t, String name){
		try {
			db.updateIntramuralTeamName(t, name);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when updating a team name!");
			ex.printStackTrace();
		}
	}
	
	public static void delete_team_database(IntramuralDatabase db, IntramuralTeam t){
		try {
			db.deleteIntramuralTeam(t);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when deleting a team!");
			ex.printStackTrace();
		}
	}

	public static void read_team_members_from_database(IntramuralDatabase db, int teamId) {
		try{	 
			ArrayList<TeamMember> lst = db.getTeamMembers(teamId);
			for(TeamMember tm : lst) {
				System.out.println(tm.toString());
			}
		} catch(SQLException e) {
			System.out.println("Something went wrong when reading the contents of the TeamMember table!");
			e.printStackTrace();
		}		
	}

	public static TeamMember create_team_member_database(IntramuralDatabase db, int teamId, String studentId, String role, String joinDate, String position) {
		TeamMember tm = new TeamMember(teamId, studentId, role, joinDate, position);
		try {
			db.insertTeamMember(tm);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when inserting a new team member!");
			ex.printStackTrace();
		}
		return tm;
	}
	
	public static void update_position_database(IntramuralDatabase db, TeamMember tm, String position){
		try {
			db.updateTeamMemberPosition(tm, position);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when updating a position!");
			ex.printStackTrace();
		}
	}

	public static void delete_team_member_database(IntramuralDatabase db, TeamMember tm){
		try {
			db.deleteTeamMember(tm);
		} catch(SQLException ex) {
			System.out.println("Something went wrong when deleting a team member!");
			ex.printStackTrace();
		}
	}
}