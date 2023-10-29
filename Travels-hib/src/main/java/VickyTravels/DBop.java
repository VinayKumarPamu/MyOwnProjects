package VickyTravels;

import java.util.List;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBop {
	static Session session;
	static Transaction beginTransaction;
	public static void dbop() {
		Configuration config=new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(userdetails.class);
		config.addAnnotatedClass(journeyDetails.class);
		config.addAnnotatedClass(seating.class);
		System.out.println("done");
		SessionFactory sf=config.buildSessionFactory();
		session = sf.openSession();
	}
	public static void update(Object cls,String email) {
		beginTransaction = session.beginTransaction();
		if(retrive(email)!=null) {
			System.out.println("User Exists\nPlease login");
		}else {
			session.saveOrUpdate(cls);
			beginTransaction.commit();
			session.clear();
		}
	}
	public static userdetails retrive(String email) {
		userdetails user;
		try {
			user = session.createQuery("FROM userdetails WHERE email = :firstName", userdetails.class)
					.setParameter("firstName", email)
					.uniqueResult();
		}catch(NonUniqueResultException e) {
			return null;
		}

		return user;
	}
	public static List<journeyDetails> retriveJourney(String source,String destination) {
		List<journeyDetails> details;
		try {
			details=session.createQuery("FROM journeyDetails WHERE src=:source and dstn=:destination",journeyDetails.class)
					.setParameter("source", source).setParameter("destination", destination).list();
		}catch(NonUniqueResultException e) {
			return null;
		}
		session.clear();
		return details;
	}
	public static List<seating> retriveSeat(String journeyDT) {
		List<seating> seat;
		try {
			seat=session.createQuery("from seating where JourneyDate=:journey",seating.class)
					.setParameter("journey", journeyDT)
					.list();
		}catch(NonUniqueResultException e) {
			return null;
		}
		session.clear();
		return seat;
	}
	public static void editedUpdate(Object cls) {
		journeyDetails jrny = (journeyDetails) cls;
		beginTransaction = session.beginTransaction();
		System.out.println("");
		String hql = "UPDATE journeyDetails SET src = :source,dstn=:destination,passNum=:passnum WHERE journeyDate = :jrnyDT and mobNum=:mobnum";
		int updatedCount = session.createQuery(hql)
				.setParameter("source",jrny.getSource() )
				.setParameter("jrnyDT", jrny.getJourneyDate())
				.setParameter("destination", jrny.getDestination())
				.setParameter("passnum", jrny.getPassingerNum())
				.setParameter("mobnum", jrny.getMobNum())
				.executeUpdate();
		beginTransaction.commit();
		session.clear();
	}
	public static List<journeyDetails> retriveJourney(String journeyDt) {
		List<journeyDetails> details;
		try {
			details=session.createQuery("FROM journeyDetails WHERE journeyDate=:destination",journeyDetails.class)
					.setParameter("destination", journeyDt).list();
		}catch(NonUniqueResultException e) {
			return null;
		}
		session.clear();
		return details;
	}
}
