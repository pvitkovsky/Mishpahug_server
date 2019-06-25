package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.models.user.UserEntity;
import application.models.user.UserEntity.UserChoiceCategories;
import application.utils.choices.IChoices;
import application.utils.choices.UserChoices;

public class ChoicesLoader implements ILoader {

	@Autowired
	LoaderDependencies data;
	
	private BufferedReader br;

	public ChoicesLoader(BufferedReader br) {
		this.br = br;
	}

	UserChoices userChoices = new UserChoices();
	{
		userChoices.setChoices(UserChoiceCategories.RELIGION, new HashSet<>(Arrays.asList("Secular")));
		userChoices.setChoices(UserChoiceCategories.KITCHEN, new HashSet<>(Arrays.asList("Vegan", "Alcoholic", "Non-alcoholic")));
		userChoices.setChoices(UserChoiceCategories.MARITALSTATUS, new HashSet<>(Arrays.asList("It's Complicated")));
		userChoices.setChoices(UserChoiceCategories.GENDER, new HashSet<>(Arrays.asList("Not Specified")));
	}

	@Override
	public void load() {

		List<UserEntity> users = this.data.userRepository.findAll();
		users.forEach(u -> u.setChoices(new UserChoices(userChoices))); //defensive copying to avoid shared loading; 
		
	}
}
