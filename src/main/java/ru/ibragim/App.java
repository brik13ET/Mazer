package ru.ibragim;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.printf("BlockCount:\t%d\n", (int)(40 * 20 * .2f));
        Map m = new Map(40, 20, 20, 0);
        System.out.println(m);
		BaseSolver sol = new DFSolver(m);
		System.out.println("Start solving");
		boolean ended = sol.solve();
		System.out.println("isEnded:\t" + ended);
		// System.out.println(m.toStringDirected());
    }
}
