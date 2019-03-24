package application.models.marriagestatus;

import application.entities.MarriageStatusEntity;
import application.repositories.MarriageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MarriageStatusModel implements IMarriageStatusModel{

    @Autowired
    MarriageStatusRepository marriageStatusRepository;

    @Override
    public MarriageStatusEntity getByName(String name){
        return marriageStatusRepository.findByName(name);
    }

    @Override
    public List<MarriageStatusEntity> getAll() {
        return marriageStatusRepository.findAll();

    }
}
