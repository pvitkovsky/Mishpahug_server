package Application.models.picture;

import java.sql.Blob;

import Application.entities.values.PictureValue;

public interface IPictureModel {
    public void add(PictureValue data);
    public Blob[] getbyUser(Integer user_id);
    public void remove(Integer id);
}
