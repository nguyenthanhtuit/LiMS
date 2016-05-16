package mongotest;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoUtils {
	public static final String HOST="dev.oohhay.com";
	public static final int PORT = 27017;
	
	public static final String USERNAME = "ohhay";
	public static final String PASSWORD = "qb12345";
	
	
	//connect dbmongo k co bao mat
	public static MongoClient getMongoClient_1() throws UnknownHostException{
		MongoClient mongoClient = new MongoClient(HOST,PORT);
		return mongoClient;
	}
	
	//connect dbmongo co bao mat
	public static MongoClient getMongoClient_2() throws UnknownHostException{
		MongoCredential credential = MongoCredential.createCredential(USERNAME, MyConstants.DB_NAME, PASSWORD.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(HOST,PORT),Arrays.asList(credential));
		return mongoClient;
	}
	
	//select type connect
	public static MongoClient getMongoClient() throws UnknownHostException{
		return getMongoClient_2();
	}
	
	
	public static void ping() throws UnknownHostException{
		MongoClient mongoClient = getMongoClient();
		System.out.println("List all DB:");
		
		//danh sách các database name 
		List<String> dbNames = mongoClient.getDatabaseNames();
		for(String dbName : dbNames){
			System.out.println("database name : "+dbName);
		}
		System.out.println("===END===");
	}
	public static void main(String[] args) throws UnknownHostException {
		ping();
	}
}
