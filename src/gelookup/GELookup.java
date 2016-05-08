package gelookup;

public class GELookup {
	
	static final RequestManager rm = new RequestManager();
	
	static String getDetails(String item) {
		
		return rm.GET(URL.BASE_URL + URL.DETAILS + item);
	}
	
	public static String search(String searchTerm, int page) {
		
		return rm.GET(URL.BASE_URL + URL.SEARCH_ONE + searchTerm + URL.SEARCH_TWO + page);
	}

}
