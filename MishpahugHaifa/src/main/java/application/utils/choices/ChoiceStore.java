package application.utils.choices;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
/**
 * Can't do nested collections of Embeddable in JPA;
 */
public class ChoiceStore {

	
	private String choicesStored;

	
	public ChoiceStore(Set<String> store) {
		this.choicesStored = String.join(",", store); //TODO: disallow ',' in ChoiceSets; 
	}

	@ElementCollection
	@CollectionTable
	public Set<String> getChoices() { //https://stackoverflow.com/a/19003565/8141533
		HashSet<String> res = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(choicesStored, ",");
		while(st.hasMoreTokens()) {
			res.add(st.nextToken());
		}
		return res;
	}

	@Override
	public String toString() {
		return getChoices().toString();
	}
	
}	
