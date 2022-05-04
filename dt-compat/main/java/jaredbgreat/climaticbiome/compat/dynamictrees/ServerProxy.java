package jaredbgreat.climaticbiome.compat.dynamictrees;


public class ServerProxy implements IProxy {

	@Override
	public void preInit() {/*Do Nothing*/}

	@Override
	public void init() {/*Do Nothing*/}
	
	
	@Override
	public void output(Exception e) {
		System.err.println();
		System.err.println(" ********************");
		System.err.println(" * 	I AM THE SERVER * ");
		System.err.println(" ********************");
		System.err.println();
		if(e != null) {
			e.printStackTrace();
		}
		
	}
	

}
