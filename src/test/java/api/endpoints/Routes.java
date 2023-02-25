package api.endpoints;
/*
Swagger URI ---> https://petstore.swagger.io

Create User(Post): https://petstore.swagger.io/v2/user
Get User(Get): https://petstore.swagger.io/v2/user/{username}
Update User(Put): https://petstore.swagger.io/v2/user/{username}
Delete User(Delete): https://petstore.swagger.io/v2/user/{username}
*/
public class Routes 
{
	public static String base_uri="https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String post_url=base_uri+"/user";
	public static String get_url=base_uri+"/user/{username}";
	public static String update_url=base_uri+"/user/{username}";
	public static String delete_url=base_uri+"/user/{username}";
	
	//Store Module
	
		//Here you can add the Store Module URL's
	
	//Pet Module
		//Here you can add the Store Module URL's
}
