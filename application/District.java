package application;

public class District implements Cloneable, Comparable<District> {
	@Override
	public String toString() {
		return name;
	}

	private String name;
	private linked_List<Location> location = new linked_List<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public linked_List<Location> getLocation() {
		return location;

	}

	public void setLocation(linked_List<Location> location) {
		this.location = location;
	}

	@Override
	public int compareTo(District o) {
		// TODO Auto-generated method stub
		return this.name.toLowerCase().compareTo(o.name.toLowerCase());
	}

}
