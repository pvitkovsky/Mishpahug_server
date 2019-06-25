package application.utils.choices;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.MapKeyColumn;

import application.models.user.UserEntity.UserChoiceCategories;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class UserChoices implements IChoices<UserChoiceCategories> {

	@ElementCollection
	@CollectionTable
	@MapKeyColumn(name="categories")
	@Column(name="choices")
	Map<UserChoiceCategories, ChoiceStore> store = new HashMap<>();
	
	/**
	 * To avoid shared collections; defensive constructor
	 * @param userChoices
	 */
	public UserChoices(UserChoices userChoices) {
		this.store = new HashMap<UserChoiceCategories, ChoiceStore>(userChoices.store);
	}
	
	public UserChoices(Map<UserChoiceCategories, ChoiceStore> allChoices) {
		this.store = new HashMap<UserChoiceCategories, ChoiceStore>(allChoices);
	}
	
	@Override
	public Map<UserChoiceCategories, ChoiceStore> getAll() {
		return new HashMap<>(store);
	}
	
	@Override
	public Set<String> getChoicesByCategory(UserChoiceCategories category) {
		return store.get(category).getChoices();
	}

	@Override
	public void setChoices(UserChoiceCategories category, Set<String> choices) {
		store.put(category, new ChoiceStore(choices));//TODO: validation baced on generic type;
	}

	@Override
	public String toString() {
		return store.toString();
	}




}
