package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 
{
	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Log4j2
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("***** Creating User ******");
		Response response=UserEndPoints2.createUser(this.userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** User is Created ******");
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("***** Reading User Info ******");
		Response response=UserEndPoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		System.out.println("Username is:"+" "+userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** User Info is Displayed ******");
	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("***** Updating User ******");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		Response response=UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		System.out.println("Updated First Name is:"+" "+userpayload.getFirstName());
		response.then().log().body();
		Assert.assertEquals(response.statusCode(),200 );
		
		logger.info("***** User is Updated ******");
		
		//After Updating getting the same user details to ensure whether it is updated or not
		Response responseAfterUpdate=UserEndPoints2.readUser(this.userpayload.getUsername());
		System.out.println("Username is:"+" "+userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("***** Deleting the User ******");
		Response response=UserEndPoints2.deleteUser(this.userpayload.getUsername());
		response.then().log().body().statusCode(200); //Chai Validation
		Assert.assertEquals(response.statusCode(), 200); //TestNG Validation
		
		logger.info("***** User is Deleted ******");
	}

}
