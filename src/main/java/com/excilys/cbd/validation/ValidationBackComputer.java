package com.excilys.cbd.validation;

import com.excilys.cbd.exceptions.DateException;
import com.excilys.cbd.exceptions.NameException;
import com.excilys.cbd.model.Computer;

public class ValidationBackComputer 
{
	public static void validationComputer(Computer comp) throws DateException, NameException
	{
			validationDate(comp);
			validationName(comp);
	}
	private static void validationDate (Computer comp) throws DateException
	{
		if (comp.getDiscontinued()==null || comp.getIntroduced()==null)
		{
			
		}
		else
		{
			if (comp.getDiscontinued().isAfter(comp.getIntroduced()))
			{
				
			}
			else
			{
				throw new DateException ();
			}
		}
	}
	private static void validationName (Computer comp) throws NameException
	{
		if (comp.getName() != null)
		{
			
		}
		else
		{
			throw new NameException();
		}
	}
}
