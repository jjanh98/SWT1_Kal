/*****************************************************************************************************
 * 	Project:			SWTKal.Base
 * 	
 *  creation date:		01.08.2007
 *
 * 
 *****************************************************************************************************
 *	date			| 	author		| 	reason for change
 *****************************************************************************************************
 *	01.08.2007			swtUser			initial version
 *  01.03.2008			ejbUser			using Collection<E> for teilnehmer association
 *  01.03.2008			ejbUser			internal representation of beginn and ende of type calendar
 *  									used in order to enable SQL TIMESTAMP in persistent objects
 *
 */
package swtkal.domain;

import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

/*****************************************************************************************************
 * Class Termin represents appointments that are managed by the swtKal server.
 * 
 * @author swtUser
 */
public class Termin extends Eintrag
{
	protected Calendar beginn;
	protected Calendar ende;
	protected boolean verschiebbar;
	
	protected Collection<Person> teilnehmer = new Vector<Person>();

	public Termin(int uniqueID)
	{
		super(uniqueID);
	}
	
	public Termin(Person besitzer, String kurzText, String langText,
			 Datum b, Datum e, boolean v, int uniqueID)
	{
		super(besitzer, kurzText, langText, uniqueID);

		beginn = b;
		ende = e;
		verschiebbar = v;
		
		teilnehmer.add(besitzer);	// the owner is a default participant
		System.out.println(getId());
	}

	public Termin(Person besitzer, String kurzText, String langText,
			 Datum b, Datum e, int uniqueID)
	{
		this(besitzer, kurzText, langText, b, e, false, uniqueID);
	}

	public Termin(Person besitzer, Datum b, Datum e, boolean v, int uniqueID)
	{
		this(besitzer, "", "", b, e, v, uniqueID);
	}

	public Termin(Person besitzer, Datum b, Datum e, int uniqueID)
	{
		this(besitzer, "", "", b, e, false, uniqueID);
	}

	public String toString()
	{
		return kurzText + " " + besitzer.toString();
	}

	public Datum getBeginn()
	{
		try
		{
			return (Datum) beginn;
		}
		catch (Exception e)
		{
			return new Datum(beginn.getTime());
		}
	}

	public Datum getEnde()
	{
		try
		{
			return (Datum) ende;
		}
		catch (Exception e)
		{
			return new Datum(ende.getTime());
		}
	}

	public boolean getVerschiebbar()
	{
		return verschiebbar;
	}

	public Collection<Person> getTeilnehmer()
	{
		if (teilnehmer instanceof Vector<?>)
			return teilnehmer;
		else
			return new Vector<Person>(teilnehmer);
	}

	public void setBeginn(Datum d)
	{
		beginn = d;
	}

	public void setEnde(Datum d)
	{
		ende = d;
	}

	public void setVerschiebbar(boolean v)
	{
		verschiebbar = v;
	}
	
	public void setTeilnehmer(Vector<Person> t)
	{
		teilnehmer = t;
	}
}