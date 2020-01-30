package com.excilys.cbd.validation;

import com.excilys.cbd.model.Computer;

public class ValidationBackComputer 
{
	public static void validationComputer(Computer comp) throws Exception
	{
			validationDate(comp);
			validationName(comp);
	}
	private static void validationDate (Computer comp) throws Exception
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
				throw new Exception ("La date d'introduction n'est pas antérieur à la date de disparition ");
			}
		}
	}
	private static void validationName (Computer comp) throws Exception
	{
		if (comp.getName() != null)
		{
			
		}
		else
		{
			throw new Exception("Le nom doit être obligatoirement rempli");
		}
	}
}
