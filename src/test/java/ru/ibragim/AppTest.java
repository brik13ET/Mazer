package ru.ibragim;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		Class[] classes = new Class[] { ru.ibragim.model.Turn.class, ru.ibragim.model.Map.class};
		for (Class cl : classes) {
			System.out.printf("%s:\t%s\n", cl.getSimpleName(), cl.getName());
		}
	}
}
