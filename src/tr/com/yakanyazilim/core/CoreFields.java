package tr.com.yakanyazilim.core;

public class CoreFields {

	private String userName = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/satisvestok";
	private static String driver = "com.mysql.cj.jdbc.Driver";

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

}
