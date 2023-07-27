package ru.ibragim;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import ru.ibragim.model.Turn;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.printf("BlockCount:\t%d\n", (int)(40 * 20 * .2f));

		StandardServiceRegistry ssr =
			new StandardServiceRegistryBuilder()
			.configure("hibernate.cfg.xml")
			.build();
		Metadata meta = 
			new MetadataSources(ssr)
			.getMetadataBuilder()
			.build();

		SessionFactory factory = 
			meta
			.getSessionFactoryBuilder()
			.build();
		Session session = factory.openSession();
		var t = session.beginTransaction();

		session.clear();

        Map m = new Map(40, 20, 20, 76);
		session.save(m.toDbMap());

        System.out.println(m);
		BaseSolver sol;
		boolean ended;
		
		

		sol = new DFSolver(m);
		System.out.println("Start Deep solving");
		ended = sol.solve();

		for (Turn turn : sol.retieveData())
		{
			System.out.println(turn);
			var fin = session.find(Turn.class, turn);
			if (fin != null && fin.equals(turn))
				session.merge(turn);
			else
				session.save(turn);
		}

		System.out.println(sol.toString());
		System.out.println("isEnded:\t" + ended);



		sol = new BFSolver(m);
		System.out.println("Start Wide solving");
		ended = sol.solve();
		for (Turn turn : sol.retieveData()) {
			session.save(turn);
		}

		t.commit();
		
		factory.close();
		session.close();

		System.out.println(sol.toString());
		System.out.println("isEnded:\t" + ended);

    }
}
