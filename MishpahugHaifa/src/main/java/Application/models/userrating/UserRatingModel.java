package Application.models.userrating;

import java.util.HashMap;

import Application.entities.values.UserRatingValue;

public class UserRatingModel implements IUserRatingModel {

    @Override
    public UserRatingValue getByUser(Integer userId) {
        return null;
    }

    @Override
    public UserRatingValue update(Integer userId, HashMap<String, String> data) {
        return null;
    }

    @Override
    public UserRatingValue removeByUser(Integer userId) {
        return null;
    }
}
