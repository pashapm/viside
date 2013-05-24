package hackday.viside;

import java.util.LinkedList;
import java.util.List;

public class UnitsManager {

	private static UnitsManager sInstance = new UnitsManager();
	
	public static UnitsManager getInstance() {
		return sInstance;
	}
	
	public List<Unit> mUnits = new LinkedList<Unit>();
	
}
