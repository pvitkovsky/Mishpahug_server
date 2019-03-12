package Application.models.password;

import Application.entities.PasswordEntity;

public interface IPasswordModel {
    public PasswordEntity getByUserNickName(String nickname);
}
