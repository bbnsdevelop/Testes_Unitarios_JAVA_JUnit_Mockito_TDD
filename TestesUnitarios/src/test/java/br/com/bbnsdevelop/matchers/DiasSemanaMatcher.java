package br.com.bbnsdevelop.matchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.com.bbnsdevelop.utils.DataUtils;

public class DiasSemanaMatcher extends TypeSafeMatcher<Date>{
	
	private Integer diasSemana;
	
	public DiasSemanaMatcher(Integer diasSemana) {
		this.diasSemana = diasSemana;
	}

	@Override
	public void describeTo(Description description) {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_WEEK, this.diasSemana);
		String dataExtension = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
		description.appendText(dataExtension);
	}

	@Override
	protected boolean matchesSafely(Date item) {		
		return DataUtils.verificarDiaSemana(item, this.diasSemana);
	}

}
