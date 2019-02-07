package Application.models.userrating;

import Application.entities.UserRatingItem;

import java.util.HashMap;

public interface IUserRatingModel {
    public UserRatingItem getByUser(Integer userId);
    public UserRatingItem update(Integer userId, HashMap<String, String> data);
    public UserRatingItem removeByUser(Integer userId);
}
