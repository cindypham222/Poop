import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionCountry {
	public ProductionCountry(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public ProductionCountry() {
		
	}
	
	public String name;
	
	public String id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void insert(List<ProductionCountry> list) throws SQLException {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "C1ndyPh4m222");
		String statement = "Insert into production_country values (?,?)";
		PreparedStatement ps = myConn.prepareStatement(statement);
		for(ProductionCountry prod: list) {
			System.out.println(prod.getId() + " " + prod.getName());
			ps.setString(1, prod.getId());
			ps.setString(2, prod.getName());
			ps.execute();
		}
	}
	//On here you can now write a function that will help you insert into DB
	//IE Take that prepared statement and inject appropriate variables into it so it can execute
}
