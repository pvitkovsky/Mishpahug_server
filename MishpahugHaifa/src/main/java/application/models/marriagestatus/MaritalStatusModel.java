package application.models.marriagestatus;

import application.entities.MaritalStatusEntity;
import application.repositories.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MaritalStatusModel implements IMaritalStatusModel{

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Override
    public MaritalStatusEntity getByName(String name){
        return maritalStatusRepository.getByFullName(name);
    }

    @Override
    public List<MaritalStatusEntity> getAll() {
        return maritalStatusRepository.findAll();

    }
}
