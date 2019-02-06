package Application.models.picture;

import Application.entities.PictureItem;

import java.sql.Blob;

public class PictureModel implements  IPictureModel{
    @Override
    public void add(PictureItem data) {

    }

    @Override
    public Blob[] getbyUser(Integer user_id) {
        return new Blob[0];
    }

    @Override
    public void remove(Integer id) {

    }
}
