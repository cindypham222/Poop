
import java.sql.SQLException;
import java.util.*;

import org.json.*;


public class GenresFactory extends BaseFactory {
	public ArrayList<Genres> GenerateGenres(String rawValue) throws SQLException {
		//Raw Value looks like this [{ 1: 1 }, { 2: 2 }, ... ] 
		//First parse array, then parse objects, then translate to your class
		Genres gen = new Genres();
		JSONArray array = this.parseJSONArray(rawValue);
		//How to get values of array as string now?
		//For every one of those values call this
		JSONObject obj = this.parseJSONObject(array);
		List<Genres> list = new ArrayList<>();
		if (obj != null) {
			for (int i = 0; i<array.length(); i++) {
				list.add(new Genres(array.getJSONObject(i).getString("name"),
						array.getJSONObject(i).getInt("id")));
			}
		}
		gen.insert(list);
		//Now translate from the objects to an array of POCO
		
		return new ArrayList<Genres>();
	}
	

}
