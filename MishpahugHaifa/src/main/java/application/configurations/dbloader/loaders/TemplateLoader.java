package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.template.TemplateEntity;
import application.entities.template.XYTextValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Loads Template
 */
@Slf4j
public class TemplateLoader implements ILoader {
	
	@Autowired 
	LoaderDependencies data;

	private BufferedReader br;

	public TemplateLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load()  {
		for (int i = 0; i < 10; i++) {
			TemplateEntity templateEntity = new TemplateEntity();
			templateEntity.setName("item" + i);
			templateEntity.setPicture("n/a");
			Random r = new Random();
			Set<XYTextValue> data = new HashSet<>();
			for (int j = 0; j < 128; j++) {
				XYTextValue xyTextValue = new XYTextValue(50 * (r.nextInt(31) + 1),50 * (r.nextInt(18) + 1),16, "user!firstname");
				data.add(xyTextValue);
			}
			templateEntity.setItems(data);
			log.debug("DBLoadTest -> TemplateLoader -> templateEntity = " + templateEntity);
			this.data.templateRepository.save(templateEntity);
		}
	}
}