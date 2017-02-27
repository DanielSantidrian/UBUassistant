package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class CaseDescription implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.String keyWord1;


	public java.lang.String getKeyWord1()
		{
			return keyWord1;
		}
	public void setKeyWord1(java.lang.String keyWord10)
		{
			this.keyWord1 = keyWord10;
		}

	private java.lang.Integer id;


	public java.lang.Integer getId()
		{
			return id;
		}
	public void setId(java.lang.Integer id1)
		{
			this.id = id1;
		}

	private java.lang.String keyWord2;


	public java.lang.String getKeyWord2()
		{
			return keyWord2;
		}
	public void setKeyWord2(java.lang.String keyWord22)
		{
			this.keyWord2 = keyWord22;
		}

	private java.lang.String keyWord3;


	public java.lang.String getKeyWord3()
		{
			return keyWord3;
		}
	public void setKeyWord3(java.lang.String keyWord33)
		{
			this.keyWord3 = keyWord33;
		}

	private java.lang.String keyWord4;


	public java.lang.String getKeyWord4()
		{
			return keyWord4;
		}
	public void setKeyWord4(java.lang.String keyWord44)
		{
			this.keyWord4 = keyWord44;
		}

	private java.lang.String keyWord5;


	public java.lang.String getKeyWord5()
		{
			return keyWord5;
		}
	public void setKeyWord5(java.lang.String keyWord55)
		{
			this.keyWord5 = keyWord55;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("id",this.getClass());
		} 

	public String toString()		{
			return "["+ keyWord4 + " , " + keyWord3 + " , " + keyWord2 + " , " + keyWord1 + " , " + keyWord5 + " , " + id +"]";
		}

}
