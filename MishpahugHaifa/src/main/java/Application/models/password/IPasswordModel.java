package Application.models.password;

import Application.entities.PasswordEntity;
import Application.entities.UserEntity;

public interface IPasswordModel {
    public PasswordEntity getByUserNickName(String nickname);
}
