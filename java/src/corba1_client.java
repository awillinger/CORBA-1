import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Random;

public class corba1_client
{
    private String[] random = new String[]{"Hallo", "Welt", "CORBA", "ORB", "omniOrb", "Hello", "Test", "Blubb"};
    public static void main(String args[]) 
    {
        try 
	{
            String setInfo, returnInfo;
            org.omg.CORBA.Object objRef;
            Echo echoRef;
            ORB orb = ORB.init(args, null);

            objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            // locate the service
            NameComponent nc =  new NameComponent("echo", "corba1");
            NameComponent nc2 = new NameComponent("Echo", "Object");

            NameComponent path[] = { nc,nc2 };
            echoRef = EchoHelper.narrow(ncRef.resolve(path));
	
            if (args.length > 0) 
	    {
		
                setInfo = args[0];
            } 
	    else 
	    {
		setInfo = new corba1_client().generateRandom();
            }

            System.out.println("Sending data to Server: "+setInfo);
            returnInfo = echoRef.echoString(setInfo);
            System.out.println("Response from Server: "+returnInfo);

        } 
	catch (Exception e) 
	{
            System.out.println("ERROR : " + e);
        }
    }
    private String generateRandom()
    {
	String out = "";
	
	Random r = new Random();
	int length = r.nextInt(5), index = -1;
	
	for(int i = 0; i < length; i++)
	{
		index = r.nextInt(random.length);
		out += this.random[index];
		out += " ";
	}
	
	return out;
    }
}
