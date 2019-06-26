package application.models.user;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import application.models.user.values.PictureValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }),
		@UniqueConstraint(columnNames = { "username" }) })
@ToString(exclude = { "pictureItems" })
@EqualsAndHashCode(of = { "userName" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserEntity { // TODO: implements ChangeableStatus<UserEntity>{ add
													// properties<UserEntity>
	@Id
	@Column(name = "User_Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@NotNull
	@lombok.NonNull
	@Column(name = "email", nullable = false)
	private String eMail;

	@NotNull
	@lombok.NonNull
	@Column(name = "username", length = 36, nullable = false)
	@Setter(AccessLevel.NONE)
	private String userName;

	@Column(name = "Encryted_Password", length = 128)
	/*
	 * @JsonInclude(Include.NON_NULL) on class or @JsonInclude(Include.NON_NULL)
	 * here to prevent this from being serialized as null
	 */
	private String encrytedPassword;

	@Column(name = "dateofbirth")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfBirth;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Setter(AccessLevel.NONE)
	private UserStatus status = UserStatus.ACTIVE; // TODO: refactor all these away

	public enum UserStatus implements StatusChanger {
		ACTIVE(u -> u.activate()), DEACTIVATED(u -> u.deactivate()), PENDINGFORDELETION(u -> u.putIntoDeletionQueue());

		private StatusChanger changer;

		private UserStatus(StatusChanger changer) {
			this.changer = changer;
		}

		@Override
		public void change(UserEntity user) {
			changer.change(user);
		}
	}

	@FunctionalInterface
	private interface StatusChanger {
		void change(UserEntity user);
	}

	@ElementCollection
	@CollectionTable
	@Column(name = "pictures")
	private Set<PictureValue> pictureItems = new HashSet<>();
	
	@Embedded
	@Column(name = "choices")
	@Setter(AccessLevel.NONE)
	private UserChoices choices = new UserChoices(); //TODO: fix lazy init exception on user delete;

	/**
	 * UserEntity: required fields, userName is immutable;
	 */
	public UserEntity(@NotNull String userName, @NotNull String email) {
		this(); // <- or instance variables won't be initialized;
		if (userName.length() > 36) {
			throw new IllegalArgumentException("userName too long");
		}
		this.userName = userName;
		this.eMail = email;
	}
	
	
	public void setChoices(UserChoices choices) {
		this.choices = choices;
	}

	/**
	 * Changes this user's status, validating the parameter
	 * 
	 * @param status must be equal to one of UserStatus values;
	 */
	public void changeStatus(String status) {
		UserStatus newStatus;
		try {
			newStatus = UserStatus.valueOf(status);
		} catch (Exception e) {
			throw new IllegalArgumentException("Not found UserStatus with name " + status);
		}
		newStatus.change(this);
	}

	/**
	 * Checks that the user is OK to delete and then unsubscribes him/her from
	 * everywhere, and his subscribers too;
	 */
	@PreRemove
	private void nullifyForRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("User must be first putIntoDeletionQueue");
		}
	}

	/**
	 * @return true if this user is activated
	 */
	public boolean isEnabled() {
		return status.equals(UserStatus.ACTIVE);
	}

	/**
	 * @return true if this user is pending for deletion
	 */
	public boolean isPendingForDeletion() {
		return status.equals(UserStatus.PENDINGFORDELETION);
	}

	/**
	 * Activate this user
	 */
	public void activate() {
		status = UserStatus.ACTIVE;
	}

	/**
	 * Deactivate this user
	 */
	public void deactivate() {
		status = UserStatus.DEACTIVATED;
	}

	/**
	 * Puts this user in the deletion queue
	 */
	public void putIntoDeletionQueue() {
		status = UserStatus.PENDINGFORDELETION;
	}



	
}
