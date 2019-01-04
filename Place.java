
/**
 * Place class
 * 
 * @author Joowon Kim
 */

import java.util.ArrayList;

public class Place implements Comparable<Place> {
	private ArrayList<String> zips = new ArrayList<String>();
	private String name;

	/**
	 * Constructor
	 * 
	 * @param it
	 *            zip code
	 * @param name
	 *            city name
	 */
	public Place(String it, String name) {
		zips.add(it);
		this.name = name;
	}

	/**
	 * @param name
	 *            setting city name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param zip
	 *            add zipcode
	 */
	public void addZips(String zip) {
		for (int i = 0; i < zips.size(); i++) {
			if (zips.get(i).equals(zip))
				break;
		}
		zips.add(zip);
	}

	/**
	 * Overrides toString method
	 * 
	 * @see java.lang.Object.toString()
	 * @return converts the element to string and returns
	 */
	public String toString() {
		String a = "";
		for (int i = 0; i < zips.size(); i++) {
			a += zips.get(i);
			if (i != zips.size() - 1)
				a += ", ";
		}
		return a;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @return int
	 */
	public int compareTo(Place p) {
		if (p.name.equals(name)) {
			return 0;
		} else if (p.name.compareTo(name) < 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
