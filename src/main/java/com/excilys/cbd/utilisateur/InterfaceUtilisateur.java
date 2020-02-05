package com.excilys.cbd.utilisateur;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Scanner;

import com.excilys.cbd.dao.CompanyDAO;
import com.excilys.cbd.dao.ComputerDAO;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceCompany;
import com.excilys.cbd.service.ServiceComputer;
import com.excilys.cbd.Logger.Logging;

public class InterfaceUtilisateur 
{
	public static void AfficherInterface() throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("\t\t\t\t\t\tBonjour");
		System.out.println("\n\t\t\t\t\tQue souhaitez-vous faire ?");
		System.out.println("\t\t\t\tEntrez le chiffre correspondant � votre demande");
		System.out.println("\n\t\t\t1.Add a computer");
		System.out.println("\t\t\t2.Update computer by id");
		System.out.println("\t\t\t3.Delete a company by id");
		System.out.println("\t\t\t4.Find a computer by name");
		System.out.println("\t\t\t5.Display all computers");
		System.out.println("\t\t\t6.Display all companies");
		System.out.println("\t\t\t7.Find a company by id");
		System.out.println("\t\t\t8.Dispaly a page of computers");
		System.out.println("\t\t\t9.Delete a computer");
		System.out.println("\t\t\t10.Exit\n");

		Scanner clavier = new Scanner(System.in);
		int i = clavier.nextInt();
		if (i==1)
		{
			System.out.println("\n\t\t\tEntrez le nom du computeur, sa date d'introduction, sa date de disparition ainsi que l'id de sa company. \n\t\t\tSi vous n'avez pas toutes ces informations entrez le nom et \"null\" dans les autres champs ");
			System.out.println("\n\t\t\t\tnom : ");
			String name = clavier.next();
			System.out.println("\n\t\t\t\tdate d'introduction : \n\t\t\t\tsous la forme jj-mm-yyyy");
			String introducedString = clavier.next();
			LocalDate introduced;
			if (introducedString.compareTo("null")==0) {
				introduced = null;
			}
			else
			{
				Date dateIntro= new SimpleDateFormat("dd-MM-yyyy").parse(introducedString);
				introduced = dateIntro.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
			System.out.println("\n\t\t\t\tdate de disparition : \n\t\t\t\tsous la forme jj-mm-yyyy");
			String discontinuedString = clavier.next();
			LocalDate discontinued;
			if (discontinuedString.compareTo("null")==0)
			{
				discontinued = null;
			}
			else
			{
				Date dateDisco= new SimpleDateFormat("dd-MM-yyyy").parse(discontinuedString);
				discontinued =  dateDisco.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
			}
			System.out.println("\n\t\t\t\tid de la company : ");
			String idCompany = clavier.next();
			Long companyId;
			Company compa =null;
			if (idCompany.compareTo("null")==0)
			{
				companyId=0L;
			}
			else
			{
				companyId= Long.parseLong(idCompany);
				compa = ServiceCompany.getInstance().getCompany(companyId);
			}
			Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			 
			ServiceComputer.getInstance().addComputer(comp);
		}
		else
		{
			if (i==2)
			{
				System.out.println("\n\t\t\tEntrez l'id du computer � modifier ainsi que le nouveau nom du computeur, sa nouvelle date d'introduction, sa nouvelle date de disparition ainsi que l'id de sa company. \n\t\t\tSi vous n'avez pas toutes ces informations entrez le nom et l'id du computer et \"null\" dans les autres champs ");
				System.out.println("\n\t\t\t\tid du computer : ");
				Long computer_id = clavier.nextLong();
				System.out.println("\n\t\t\t\tnom : ");
				String name = clavier.next();
				System.out.println("\n\t\t\t\tdate d'introduction : \n\t\t\t\tsous la forme jj-mm-yyyy");
				String introducedString = clavier.next();
				Timestamp introduced;
				if (introducedString.compareTo("null")==0)
				{
					introduced = null;
				}
				else
				{
					Date dateIntro= new SimpleDateFormat("dd-MM-yyyy").parse(introducedString);
					introduced = new Timestamp(dateIntro.getTime());
				}
				System.out.println("\n\t\t\t\tdate de disparition : \n\t\t\t\tsous la forme jj-mm-yyyy");
				String discontinuedString = clavier.next();
				Timestamp discontinued;
				if (discontinuedString.compareTo("null")==0)
				{
					discontinued = null;
				}
				else
				{
					Date dateDisco= new SimpleDateFormat("dd-MM-yyyy").parse(discontinuedString);
					discontinued = new Timestamp(dateDisco.getTime());
				}
				System.out.println("\n\t\t\t\tid de la company : ");
				String id_company = clavier.next();
				Long company_id;
				if (id_company.compareTo("null")==0)
				{
					company_id=0L;
				}
				else
				{
					company_id = new Long(id_company);
				}
				//ServiceComputer.getInstance().editComputer();
			}
			else
			{
				if (i==3)
				{
					System.out.println("\n\t\t\tEntrez l'id du computer que vous voulez supprimer : ");
					Long companyId= clavier.nextLong();
					ServiceCompany.getInstance().getDeleteCompany(companyId);
				}
				else
				{
					if (i==4)
					{
						System.out.println("\n\t\t\tEntrez le nom du computer que vous voulez trouvez : ");
						String name= clavier.next();
						//ServiceComputer.getInstance().getComputer(name);
					}
					else
					{
						if (i==5)
						{
							ServiceComputer.getInstance().getComputerList();
						}
						else
						{
							if (i==6)
							{
								ServiceCompany.getInstance().getCompanyList();
							}
							else
							{
								if (i==7)
								{
									System.out.println("\n\t\t\tEntrez l'id de la company que vous voulez trouvez : ");
									Long companyId= clavier.nextLong();
									ServiceCompany.getInstance().getCompany(companyId);
								}
								else
								{
									if (i==8)
									{
										System.out.println("\n\t\t\tEntrez le nombre de computers que vous souhaitez afficher : ");
										int limit = clavier.nextInt();
										System.out.println("\n\t\t\tEntrez le num�ro du premier computer � afficher : ");
										int offset = clavier.nextInt();
										ServiceComputer.getInstance().getComputerListPaginer(limit, offset);
									}
									else
									{
										if (i==9)
										{
											System.out.println("\n\t\t\tEntrez l'id du computer que vous voulez supprimer.");
											Long computerId = clavier.nextLong();
											//ServiceComputer.getInstance().delete(computerId);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		clavier.close();
	}
}