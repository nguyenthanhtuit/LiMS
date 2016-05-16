package mongotest;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class GettingCollection {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = MongoUtils.getMongoClient();
		DB db = mongoClient.getDB(MyConstants.DB_NAME);
		
		DBCollection dept = db.getCollection("Deparment");
		 System.out.println("Collection: "+dept);
		 
		 long rowCount = dept.count();
		 System.out.println("Document count:"+rowCount);
		 
		 Set<String> collections = db.getCollectionNames();
		 for(String collection:collections){
			 System.out.println(collection);
		 }
	}
}
