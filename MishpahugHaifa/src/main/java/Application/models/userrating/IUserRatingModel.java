package Application.models.userrating;

import java.util.HashMap;

import Application.entities.values.UserRatingValue;

public interface IUserRatingModel {
    public UserRatingValue getByUser(Integer userId);
    public UserRatingValue update(Integer userId, HashMap<String, String> data);
    public UserRatingValue removeByUser(Integer userId);
}
