package drukmeter.model;

import drukmeter.model.impl.PascalDrukModelFactory;

/**
 * @author wijnand.schepens@hogent.be
 */
public class DrukModelFactoryProvider 
{
	private static DrukModelFactoryProvider instance = new DrukModelFactoryProvider();
	private DrukModelFactory drukModelFactory;
	
	private DrukModelFactoryProvider() 
	{
		drukModelFactory = new PascalDrukModelFactory();
	}
	
	public static DrukModelFactoryProvider getInstance() { 
		return instance;
	}
	
	public DrukModelFactory getDrukModelFactory() {
		return drukModelFactory;
	}
	
	public void loadDrukModelFactory(String factoryClassName) {
		try
		{
			Class klasse = Class.forName(factoryClassName);
			drukModelFactory = (DrukModelFactory) klasse.newInstance();
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
		{
			throw new RuntimeException("Kan model-factory niet laden", ex);
		}
	}
			
}
