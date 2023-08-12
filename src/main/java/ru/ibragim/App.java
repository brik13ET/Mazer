package ru.ibragim;

import java.util.Random;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import ru.ibragim.base.BaseSolver;
import ru.ibragim.base.Map;
import ru.ibragim.base.Turn;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
			
	

        Map dbm = new Map(40, 20, 20, new Random().nextInt());
		
		session.saveOrUpdate(dbm);
		System.out.println(dbm);
		System.out.println(dbm.toNiceString());

		BaseSolver sol;
		
		// select * from "Solution" s where s."MapID" = 10 and s."Solver" like '%DFS%'  order by s."MapID" asc, s."Solver" asc, s."Order" asc;
		

		sol = new DFSolver(dbm);
		System.out.println("Start Deep solving");
		sol.solve();

		for (Turn turn : sol.retieveData())
		{
			// System.out.println(turn);
			var fin = session.find(Turn.class, turn);
			if (fin != null && fin.equals(turn))
				session.merge(turn);
			else
				session.save(turn);
		}

		// System.out.println(dbm.toNiceString());
		System.out.println(sol);



		sol = new BFSolver(dbm);
		System.out.println("Start Wide solving");
		sol.solve();
		for (Turn turn : sol.retieveData()) {
			var fin = session.find(Turn.class, turn);
			if (fin != null && fin.equals(turn))
				session.merge(turn);
			else
				session.save(turn);
		}

		// System.out.println(dbm.toNiceString());
		System.out.println(sol);
		
		session.flush();
		t.commit();
		factory.close();
		session.close();

    }
}
