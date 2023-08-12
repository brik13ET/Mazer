package ru.ibragim;

import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import ru.ibragim.base.BaseSolver;
import ru.ibragim.base.Map;
import ru.ibragim.base.Turn;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void DFSTest()
    {
		System.out.printf("BlockCount:\t%d\n", (int)(40 * 20 * .2f));
        Map m = new Map(40, 20, 20, 0);
        System.out.println(m);
		BaseSolver sol;
		boolean ended;
		
		sol = new DFSolver(m);
		System.out.println("Start Deep solving");
		ended = sol.solve();
        assertTrue( ended );
    }

	@Test
	public void BFSTest()
	{
		System.out.printf("BlockCount:\t%d\n", (int)(40 * 20 * .2f));
        Map m = new Map(40, 20, 20, 0);
        System.out.println(m);
		BaseSolver sol;
		boolean ended;
		
		sol = new BFSolver(m);
		System.out.println("Start Wide solving");
		ended = sol.solve();
        assertTrue( ended );
	}

	
	@Test
	public void echoClassNames()
	{
		Class[] classes = new Class[] { ru.ibragim.base.Turn.class, ru.ibragim.base.Map.class};
		for (Class cl : classes) {
			System.out.printf("%s:\t%s\n", cl.getSimpleName(), cl.getName());
		}
	}

	@Test
	public void pullMaps()
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
		System.out.println("Pulled maps");

		var qMaps = session.createQuery("from ru.ibragim.base.Map").list();
		System.out.println(qMaps.size());

		
		for (var m: qMaps)
		{
			if (!(m instanceof ru.ibragim.base.Map))
			{
				System.out.println("Bad Object");
				continue;
			}
			var castedMap = (ru.ibragim.base.Map)m;
			System.out.println(castedMap);
			System.out.println(castedMap.toNiceString());
		}

		session.clear();

		t.commit();
		factory.close();
		session.close();
	}

	
	@Test
	public void pullTurns()
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
		
		var qTurns = session.createQuery("select t from ru.ibragim.base.Turn t order by t.order").list();
		for (Object object : qTurns) {
			if (! (object instanceof Turn))
			{
				System.err.println("Bad object");
				continue;
			}
			Turn t = (Turn)object;
			System.out.println(t);
		}
		System.out.println();
		
		factory.close();
		session.close();
	}
}
