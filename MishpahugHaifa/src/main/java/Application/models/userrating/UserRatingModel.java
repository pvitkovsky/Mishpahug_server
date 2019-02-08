package Application.models.userrating;

import Application.entities.UserRatingItem;
import Application.repo.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class UserRatingModel implements IUserRatingModel {

    @Autowired
    UserRatingRepository userRatingRepository;

    @Override
    public UserRatingItem getByUser(Integer userId) {
        return userRatingRepository.findById(userId).orElse(null);
    }

    @Override
    public UserRatingItem update(Integer userId, HashMap<String, String> data) {
        return null;
    }

    @Override
    public UserRatingItem removeByUser(Integer userId) {
        return null;
    }
}
