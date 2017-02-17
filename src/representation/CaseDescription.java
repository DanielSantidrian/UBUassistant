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


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("id",this.getClass());
		} 

	public String toString()		{
			return "["+ keyWord3 + " , " + keyWord2 + " , " + keyWord1 + " , " + id +"]";
		}

}
