package application.models.gender;

import application.entities.GenderEntity;
import application.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;

@Service
@Transactional
public class GenderModel implements IGenderModel{

    @Autowired
    GenderRepository genderRepository;

    @Override
    public GenderEntity getByName(String name){
        return genderRepository.findByName(name);
    }

    @Override
    public List<GenderEntity> getAll() {
        return null;
    }
}
