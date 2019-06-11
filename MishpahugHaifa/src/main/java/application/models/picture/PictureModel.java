package application.models.picture;

import application.entities.values.PictureValue;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Blob;

@Service
@Transactional
public class PictureModel implements IPictureModel {
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
