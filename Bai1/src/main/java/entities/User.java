package entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	@NotNull(message = "First Name must be not null")
	@NotEmpty(message = "First Name must be not empty")
	@Size(min = 8, max = 50, message = "First Name must be between 8 and 50 characters")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "Last Name must be not null")
	@NotEmpty(message = "Last Name must be not empty")
	@Size(min = 8, max = 50, message = "Last Name must be between 8 and 50 characters")
	private String lastName;

	@Column(name = "email")
	@NotEmpty(message = "Email must be not empty")
	@Email(message = "Email should be valid")
	private String email;

	@Column(name = "password")
	@NotEmpty(message = "Password must be not empty")
	@Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
	@NotNull(message = "Password must be not null")
	private String password;

	@Column(name = "birthday")
	private Date birthday;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	public User(int id, String firstName, String lastName, String email, String password, Date birthday,
			Gender gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", birthday=" + birthday + ", gender=" + gender + "]";
	}

}
