package application.utils.choices;

import java.util.Map;
import java.util.Set;

import javax.persistence.Embeddable;

@Embeddable
public interface IChoices<C extends IChoiceCategories> {
	
	public Map<? extends IChoiceCategories, ChoiceStore> getAll();
	public Set<String> getChoicesByCategory(C category);
	public void setChoices(C category, Set<String> choices);
	
}