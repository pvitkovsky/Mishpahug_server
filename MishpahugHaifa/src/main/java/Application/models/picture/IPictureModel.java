package Application.models.picture;

import java.sql.Blob;

import Application.entities.values.PictureValue;

public interface IPictureModel {
    public PictureValue add(PictureValue data);
    public Blob[] getAllbyUser(Integer user_id);
    public PictureValue remove(Integer id);
}
