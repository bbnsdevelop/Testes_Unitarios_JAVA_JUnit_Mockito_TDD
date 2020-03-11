package br.com.bbnsdevelop.matchers;

import java.util.Calendar;

public class MatchersPropios {

	
	public static DiasSemanaMatcher caiEm(Integer diasSemana) {
		return new DiasSemanaMatcher(diasSemana);
	}
	
	
	public static DiasSemanaMatcher caiNaSegunda() {
		return new DiasSemanaMatcher(Calendar.MONDAY);
	}
}
