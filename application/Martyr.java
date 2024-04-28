package application;

public class Martyr implements Comparable<Martyr> {

	private String name;
	private String Date;
	private int age;
	private String location;
	private String District;
	private char Gender;

	@Override
	public String toString() {
		return "Martyr [name=" + name + ", Date=" + Date + ", age=" + age + ", location=" + location + ", District="
				+ District + ", Gender=" + Gender + "]";
	}

	public Martyr(String name, String date, int age, String location, String district, char gender) {
		super();
		this.name = name;
		Date = date;
		this.age = age;
		this.location = location;
		District = district;
		setGender(gender);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public char getGender() {
		return Gender;
	}

	public void setGender(char gender) {
		
		if(gender=='f'||gender=='m')
		Gender = gender;
		else 
		{
			System.out.println("sorry the gender is not valied");

		}
	}

	@Override
	public int compareTo(Martyr o) {
		return this.age-o.age;
	}

}
