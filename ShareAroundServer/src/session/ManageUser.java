package session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Address;
import entity.Tag;
import entity.Group;
import entity.Reaction;
import entity.Sharing;
import entity.SharingType;
import entity.User;


/**
 * Session Bean implementation class ManageUser
 */
@Stateless
public class ManageUser implements ManageUserRemote {

	@PersistenceContext
	private EntityManager manager; 
		
	
	public void fillDatabase()
	{
		Address a1 = new Address("Konvalinková", "8/1", "96212", "Detva", "SK");
		Address a2 = new Address("Staré Grunty", "C2 65/2", "96969", "Bratislava", "SK");
		Address a3 = new Address("Staré Grunty", "C4 21/2", "96969", "Bratislava", "SK");
		Address a4 = new Address("Staré Grunty", "A4 75/2", "96969", "Bratislava", "SK");
		Address a5 = new Address("Staré Grunty", "B3 12/2", "96969", "Bratislava", "SK");
		Address a6 = new Address("Konvalinková", "8/9", "96212", "Detva", "SK");
		Address a7 = new Address("Konvalinková", "7/4", "96212", "Detva", "SK");
		Address a8 = new Address("Konvalinková", "8/2", "96212", "Detva", "SK");
		Address a9 = new Address("Konvalinková", "2/4", "96212", "Detva", "SK");
		Address a10 = new Address("Konvalinková", "2/4", "96212", "Detva", "SK");
		
		User u1 = new User("Peter", "Malý", "Petulo1","123456", a1, "petko@mail.com", "090912346");		
		User u2 = new User("Milan", "Hruška", "Mino45","123456", a2, "minko@mail.com", "0124649464");
		User u3 =new User("Martin", "Hruška", "Matomo","123456", a2, "matomo@mail.com", "0000569874");
		User u4 =new User("Peter", "Slepička", "Peso","123456", a3, "peso@mail.com", "0000545674");
		User u5 =new User("Irvin", "Novák", "inko4","123456", a4, "inko@mail.com", "7777555775");
		User u6 =new User("Azamat", "Horváth", "aho8","123456", a5, "aho8@mail.com", "0909632157");
		
		User u7 =new User("Marek", "Jablko", "maro8","123456", a6, "marek.jablko@mail.com", "090912346");
		User u8 =new User("Arpád", "Fulla", "killer7","123456", a7, "arpyf@mail.com", "0124649464");
		User u9 =new User("Ezekiel", "Dobrý", "treska","123456", a8, "ezod@mail.com", "7878965321");
		User u10 =new User("Arnold", "Hluchý", "dlonra","123456", a10, "arnoh@mail.com", "444465231");
		User u11 =new User("Jozef", "Hluchý", "jojo8","123456", a10, "jozko.hluchy@mail.com", "456123895");
		User u12 =new User("Ondrej", "Slepý", "onko","123456", a9, "ondryk@mail.com", "0909632157");
		
		addUser(u1);
		addUser(u2);
		addUser(u3);
		addUser(u4);
		addUser(u5);
		addUser(u6);
		addUser(u7);
		addUser(u8);
		addUser(u9);
		addUser(u10);
		addUser(u11);
		addUser(u12);
		
		List<User> members1 = new ArrayList<User>();
		members1.add(u2);
		members1.add(u3);
		members1.add(u4);
		members1.add(u5);
		members1.add(u6);
		Set<Sharing> sharing1 = new HashSet<Sharing>();
		Group g1 = new Group("Mladosť", members1, "Skupina pre internát Mladosť v Mlynskej doline", sharing1);
		
		manager.persist(g1);
		
		List<User> members2 = new ArrayList<User>();
		members2.add(u1);
		members2.add(u7);
		members2.add(u8);
		members2.add(u9);
		members2.add(u10);
		members2.add(u11);
		members2.add(u12);
		members2.add(u4);
		members2.add(u5);
		Set<Sharing> sharing2 = new HashSet<Sharing>();
		Group g2 = new Group("Za kotolňou", members2, "Červená a zelená bytovky za kotolňou", sharing2);
		
		manager.persist(g2);	
		
		Tag t1 = new Tag("potraviny");
		Tag t2 = new Tag("ovocie");
		Tag t3 = new Tag("jedlo");
		Tag t4 = new Tag("šperky");
		Tag t5 = new Tag("náhrdelník");
		
				
		manager.persist(t1);
		manager.persist(t2);
		manager.persist(t3);
		manager.persist(t4);
		manager.persist(t5);
		
		Set<Tag> ts1 = new HashSet<Tag>();
		
		ts1.add(t1);
		ts1.add(t2);
		ts1.add(t3);
		
		
		Set<Tag> ts2 = new HashSet<Tag>();
		ts2.add(t5);
		ts2.add(t4);
		
		SharingType st1 = new SharingType("Ponuka služba");
		SharingType st2 = new SharingType("Dopyt služba");
		SharingType st5 = new SharingType("Dopyt tovar");
		SharingType st3 = new SharingType("Predaj");
		SharingType st4 = new SharingType("Prenájom");
		SharingType st6 = new SharingType("Požičanie");
		SharingType st7 = new SharingType("Strata");
		SharingType st8 = new SharingType("Nález");
		SharingType st9 = new SharingType("Darovanie");
		
		manager.persist(st1);
		manager.persist(st2);
		manager.persist(st3);
		manager.persist(st4);
		manager.persist(st5);
		manager.persist(st6);
		manager.persist(st7);
		manager.persist(st8);
		manager.persist(st9);
		
		
		Sharing s1 = new Sharing(st9, u4, ts1, "Jablká", "Darujem 2 kg jabĺk", "zadarmo", g1, true, false, true);
		
		manager.persist(s1);
		
		Sharing s2 = new Sharing(st3, u4, ts1, "Jablká", "Predám 20 kg jabĺk", "0.50€/kg", g2, true, true, true);
		manager.persist(s2);
		
		Sharing s3 = new Sharing(st8, u12, ts2, "Náhrdelník", "Našiel som na chodníku pred bytovkou zlatý náhrdelník", "", g2, false, false, false);
		manager.persist(s3);
		
		Reaction r1 = new Reaction(u5, s2, "Dobrý deň, mal by som záujem o 10 kg. Môžme kedykoľvek po 17:00", true, false, false);
		
		manager.persist(r1);		
		
		Reaction r2 = new Reaction(u8, s2, "Mámm záujem", true, false, false);
		
		manager.persist(r2);
		
		Reaction r3 = new Reaction(u9, s2, "Chcem všetky", true, false, true);
		
		manager.persist(r3);
		
		Reaction r4 = new Reaction(u4, s3, "Moja priateľky podobný hľadá, môžte ma kontaktovať?", true, false, true);
		
		manager.persist(r4);
		
	}
	
		
    private void addUser(User u)
    {    	
    	manager.persist(u.getAddress());
    	manager.persist(u);		
    }

    
    public User getByUserName(String userName, String password)
    {
    	
    	User user = null;    	
    	
       	TypedQuery<User> query = manager.createNamedQuery("entity.user.getByUserName", User.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        List<User>users = query.getResultList();
    	
        if (users.size() == 1)
        {
        	user = users.get(0);
        }        

    	return user;
    }
    
}
