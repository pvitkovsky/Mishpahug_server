package application.models.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;

import application.utils.choices.ChoiceCategories;
import application.utils.choices.ChoiceStore;
import application.utils.choices.IChoices;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class UserChoices implements IChoices<ChoiceCategories> {

	@ElementCollection
	@CollectionTable
	@MapKeyColumn(name="categories")
	@Column(name="choices")
	Map<ChoiceCategories, ChoiceStore> store = new HashMap<>();
	
	/**
	 * To avoid shared collections; defensive constructor
	 */
	public UserChoices(@NotNull UserChoices userChoices) {
		this.store = new HashMap<ChoiceCategories, ChoiceStore>(userChoices.store);
	}
	
	/**
	 * To create Choices from the DTO
	 */
	public UserChoices(@NotNull Map<ChoiceCategories, ChoiceStore> allChoices) {
		this.store = new HashMap<ChoiceCategories, ChoiceStore>(allChoices);
	}
	
	@Override
	public Map<ChoiceCategories, ChoiceStore> getAll() {
		return new HashMap<>(store);
	}
	
	@Override
	public Set<String> getChoicesByCategory(ChoiceCategories category) {
		return store.get(category).getChoices();
	}

	@Override
	public void setChoices(ChoiceCategories category, Set<String> choices) {
		store.put(category, new ChoiceStore(choices));//TODO: validation baced on generic type;
	}

	@Override
	public String toString() {
		return store.toString();
	}




}
