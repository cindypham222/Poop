import org.json.*;

public abstract class BaseFactory {
	public JSONArray parseJSONArray(String jsonRaw) {
		JSONArray array = new JSONArray(jsonRaw);
		
		return array;
	}
	
	public JSONObject parseJSONObject(JSONArray array) {
		JSONObject obj = new JSONObject(array);
		return obj;
	}

}
