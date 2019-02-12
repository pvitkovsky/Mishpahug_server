package Application.entities.values;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Application.entities.UserItem;

import javax.persistence.*;

@Embeddable
public class PictureValue {

	private UserItem userItemOwner;
	private String data;
    

}
