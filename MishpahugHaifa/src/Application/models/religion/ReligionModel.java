package Application.models.religion;

import Application.entities.ReligionItem;
import Application.repo.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReligionModel implements IReligionModel {

    @Autowired
    ReligionRepository religionRepository;

    @Override
    public ReligionItem getById(Integer id) {
        return religionRepository.findById(id).orElse(null);
    }

    @Override
    public List<ReligionItem> getAll() {
        return religionRepository.findAll();
    }

    @Override
    public ReligionItem add(ReligionItem data) {
        return null;
    }

    @Override
    public ReligionItem update(String name) {
        return null;
    }
}
