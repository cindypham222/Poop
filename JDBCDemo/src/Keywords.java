import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Keywords {
	public Keywords(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public Keywords() {
		
	}
	
	public String name;
	
	public int id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void insert(List<Keywords> list) throws SQLException {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "C1ndyPh4m222");
		String statement = "Insert into keywords values (?,?)";
		PreparedStatement ps = myConn.prepareStatement(statement);
		for(Keywords key: list) {
			ps.setInt(1, key.getId());
			ps.setString(2, key.getName());
			ps.execute();
		}
	}
	//On here you can now write a function that will help you insert into DB
	//IE Take that prepared statement and inject appropriate variables into it so it can execute
}
