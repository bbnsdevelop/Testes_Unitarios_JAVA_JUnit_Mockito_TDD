package br.com.bbnsdevelop.matchers;

import static br.com.bbnsdevelop.utils.DataUtils.isMesmaData;
import static br.com.bbnsdevelop.utils.DataUtils.obterDataComDiferencaDias;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class VerifyDMaisUmMatcher extends TypeSafeMatcher<Date>{
	
	private Integer qtdDias;
	
	public VerifyDMaisUmMatcher(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}

	@Override
	public void describeTo(Description description) {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_WEEK, this.qtdDias);
		String dataExtension = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
		description.appendText(dataExtension);
	}

	@Override
	protected boolean matchesSafely(Date item) {		
		return isMesmaData(item, obterDataComDiferencaDias(this.qtdDias));
	}

}
