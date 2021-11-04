package model;


import java.io.Serializable;
import java.time.LocalDate;

public class Personas implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private int postalCode;
	private LocalDate birthday;
	private int phone;
	private int codphone = 34;
	
	public Personas() {
	}

    public Personas(String firstName, String lastName,String street,String city,
    		int postalCode,LocalDate birthday,int phone,int codphone) {
    	
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.birthday = birthday;
        this.phone = phone;
        this.codphone = codphone;  
    }
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getCodphone() {
		return codphone;
	}

	public void setCodphone(int codphone) {
		this.codphone = codphone;
	}
	
	public String toStringcodmasPhone(){
		return "+"+this.codphone+" "+this.phone;	
	}
	
	public String toStringCodPhone(){
		return String.valueOf(this.codphone);
	}
	
	public String toStringPhone(){
		return String.valueOf(this.phone);	
	}
	
	public String toStringPostalCode() {
		return String.valueOf(postalCode);
	}

	

}
