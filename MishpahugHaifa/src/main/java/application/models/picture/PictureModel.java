package application.models.picture;

import java.sql.Blob;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import application.entities.values.PictureValue;

@Service
@Transactional
public class PictureModel implements  IPictureModel{
    @Override
    public PictureValue add(PictureValue data) {
        return null;
    }

    @Override
    public Blob[] getAllbyUser(Integer user_id) {
        return new Blob[0];
    }

    @Override
    public PictureValue remove(Integer id) {
        return null;
    }
}
