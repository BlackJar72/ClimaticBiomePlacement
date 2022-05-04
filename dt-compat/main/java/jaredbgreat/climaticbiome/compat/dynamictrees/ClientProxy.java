package jaredbgreat.climaticbiome.compat.dynamictrees;


public class ClientProxy implements IProxy {

		
	@Override
	public void output(Exception e) {
		System.err.println();
		System.err.println("*********************");
		System.err.println("*  I AM THE CLIENT  *");
		System.err.println("*********************");
		System.err.println();
		if(e != null) {
			e.printStackTrace();
		}		
	}

	@Override
	public void preInit() {
    	DynamicTreeHelper.clientPreInit();
	}

	@Override
	public void init() {
    	DynamicTreeHelper.clientInit();
	}

}
