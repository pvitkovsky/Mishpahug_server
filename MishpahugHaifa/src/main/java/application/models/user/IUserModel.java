package application.models.user;

import java.util.HashMap;

import com.querydsl.core.types.Predicate;

public interface IUserModel {
	
	public UserEntity getByUsernameAndPassword(String username, String password); // TODO: username is primary key. refactor this into model.authorize pls

    public UserEntity getById(Integer userId);

    public UserEntity getByUserName(String name);
    
    public Iterable<UserEntity> getAll(Predicate predicate);

    public UserEntity add(UserEntity data);

    public UserEntity update(Integer userId,
                             HashMap<String, String> data);

    public void deleteByID(Integer userId);
    
}
