
import java.sql.SQLException;
import java.util.*;

import org.json.*;


public class SpokenLanguageFactory extends BaseFactory {
	public ArrayList<SpokenLanguage> GenerateSpokenLanguage(String rawValue) throws SQLException {
		//Raw Value looks like this [{ 1: 1 }, { 2: 2 }, ... ] 
		//First parse array, then parse objects, then translate to your class
		SpokenLanguage pc = new SpokenLanguage();
		JSONArray array = this.parseJSONArray(rawValue);
		//How to get values of array as string now?
		//For every one of those values call this
		JSONObject obj = this.parseJSONObject(array);
		ArrayList<SpokenLanguage> list = new ArrayList<>();
		if (obj != null) {
			for (int i = 0; i<array.length(); i++) {
				list.add(new SpokenLanguage(array.getJSONObject(i).getString("name"),
						array.getJSONObject(i).getString("iso_639_1")));
			}
		}
		pc.insert(list);
		//Now translate from the objects to an array of POCO
		
		return list;
		//return new ArrayList<SpokenLanguage>();
	}
	

}
