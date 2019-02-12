package Application.models.picture;

import java.sql.Blob;

import Application.entities.values.PictureValue;

public class PictureModel implements  IPictureModel{
    @Override
    public void add(PictureValue data) {

    }

    @Override
    public Blob[] getbyUser(Integer user_id) {
        return new Blob[0];
    }

    @Override
    public void remove(Integer id) {

    }
}
