package application;

public class Location implements Comparable<Location> {

	private String nameofLocation;
	private linked_List<Martyr> martyr=new linked_List<>();
	@Override
	public int compareTo(Location o) {
		// TODO Auto-generated method stub
		return this.nameofLocation.toLowerCase().compareTo(o.nameofLocation.toLowerCase());
	}
	public String getNameofLocation() {
		return nameofLocation;
	}
	public void setNameofLocation(String nameofLocation) {
		this.nameofLocation = nameofLocation;
	}
	public linked_List<Martyr> getMartyr() {
		return martyr;
	}
	public void setMartyr(linked_List<Martyr> martyr) {
		this.martyr = martyr;
	}
	@Override
	public String toString() {
		return nameofLocation ;
	}
	
	
	
	
	
	
	
	
}
