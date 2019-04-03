package application.models.picture;

import application.entities.values.PictureValue;

import java.sql.Blob;

public interface IPictureModel {
    public PictureValue add(PictureValue data);
    public Blob[] getAllbyUser(Integer user_id);
    public PictureValue remove(Integer id);
}
