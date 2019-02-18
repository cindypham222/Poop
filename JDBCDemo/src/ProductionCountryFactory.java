
import java.sql.SQLException;
import java.util.*;

import org.json.*;


public class ProductionCountryFactory extends BaseFactory {
	public ArrayList<ProductionCountry> GenerateProductionCountry(String rawValue) throws SQLException {
		//Raw Value looks like this [{ 1: 1 }, { 2: 2 }, ... ] 
		//First parse array, then parse objects, then translate to your class
		ProductionCountry pc = new ProductionCountry();
		JSONArray array = this.parseJSONArray(rawValue);
		System.out.println(array);
		//How to get values of array as string now?
		//For every one of those values call this
		JSONObject obj = this.parseJSONObject(array);
		List<ProductionCountry> list = new ArrayList<>();
		if (obj != null) {
			for (int i = 0; i<array.length(); i++) {
				
				list.add(new ProductionCountry(array.getJSONObject(i).getString("name"),
						array.getJSONObject(i).getString("iso_3166_1")));
			}
		}
		pc.insert(list);
		//Now translate from the objects to an array of POCO
		
		return new ArrayList<ProductionCountry>();
	}
	

}
