package util;

import java.util.Comparator;

import jcolibri.method.retrieve.RetrievalResult;

public class ResultsComparator implements Comparator<RetrievalResult>{

	@Override
	public int compare(RetrievalResult o1, RetrievalResult o2) {
		
		if(o1.getEval()<o2.getEval())
			return 1;
		else if(o1.getEval()>o2.getEval())
			return -1;
		else
			return 0;
	}

	

}
