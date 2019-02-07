package Application.models.picture;

import Application.entities.PictureItem;

import java.sql.Blob;

public interface IPictureModel {
    public void add(PictureItem data);
    public Blob[] getbyUser(Integer user_id);
    public void remove(Integer id);
}
