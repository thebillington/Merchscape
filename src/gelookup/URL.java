package gelookup;

public class URL {
	
	//Set the base URL for the GE
	public static final String BASE_URL = "http://services.runescape.com/m=itemdb_oldschool";
	
	//Set the base URL for the end points
	public static final String DETAILS = "/api/catalogue/detail.json?item=";
	public static final String SEARCH_ONE = "/api/catalogue/items.json?category=1&alpha=";
	public static final String SEARCH_TWO = "&page=";
	public static final String RESULTS = "/results.ws?query=";

}
