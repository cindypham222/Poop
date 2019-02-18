import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionCompany {
	public ProductionCompany(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public ProductionCompany() {
		
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

	public void insert(List<ProductionCompany> list) throws SQLException {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "C1ndyPh4m222");
		String statement = "Insert into production_company values (?,?)";
		PreparedStatement ps = myConn.prepareStatement(statement);
		for(ProductionCompany prod: list) {
			System.out.println(prod.getId() + " " + prod.getName());
			ps.setInt(1, prod.getId());
			ps.setString(2, prod.getName());
			ps.execute();
		}
	}
	//On here you can now write a function that will help you insert into DB
	//IE Take that prepared statement and inject appropriate variables into it so it can execute
}
