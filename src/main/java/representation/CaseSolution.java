package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class CaseSolution implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.String answer;


	public java.lang.String getAnswer()
		{
			return answer;
		}
	public void setAnswer(java.lang.String answer6)
		{
			this.answer = answer6;
		}

	private java.lang.Integer Id;


	public java.lang.Integer getId()
		{
			return Id;
		}
	public void setId(java.lang.Integer Id7)
		{
			this.Id = Id7;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("Id",this.getClass());
		} 

	public String toString()		{
			return "["+ answer + " , " + Id +"]";
		}

}
