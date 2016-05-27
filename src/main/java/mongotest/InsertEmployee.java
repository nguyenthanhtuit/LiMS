package mongotest;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertEmployee {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = MongoUtils.getMongoClient();
		DB db = mongoClient.getDB(MyConstants.DB_NAME);
		DBCollection emp = db.getCollection("Employee");
		
		BasicDBObject doc1 = new BasicDBObject();
		doc1.append("_id", 1);
		doc1.append("Name", "Mongo");
		doc1.append("emp_no", "E1");
		doc1.append("birtday",("15/11/1989"));
		doc1.append("dept_no", "D10");
		emp.insert(doc1);
		
		BasicDBObject doc2 = new BasicDBObject();
		doc2.append("_id", 2);
		doc2.append("Name", "Logo");
		doc2.append("emp_no", "E2");
		doc2.append("birtday", "18/1/1979");
		emp.insert(doc2);
		


		BasicDBObject doc3 = new BasicDBObject();
		doc3.append("_id", 3);
		doc3.append("Name", "BoBo");
		doc3.append("emp_no", "E3");
		doc3.append("birtday", "1/12/1989");
		emp.insert(doc3);
		
		
		BasicDBObject doc4 = new BasicDBObject();
		doc4.append("_id", 4);
		doc4.append("Name", "Monbo");
		doc4.append("emp_no", "E4");
		doc4.append("birtday", "15/9/1990");
		emp.insert(doc4);
		
		
		BasicDBObject doc5 = new BasicDBObject();
		doc5.append("_id", 5);
		doc5.append("Name", "Mono");
		doc5.append("emp_no", "E5");
		doc5.append("birtday", "18/11/1984");
		emp.insert(doc5);
		
		
		
		BasicDBObject doc6 = new BasicDBObject();
		doc6.append("_id", 6);
		doc6.append("Name", "Zoro");
		doc6.append("emp_no", "E6");
		doc6.append("birtday", "17/12/1985");
		emp.insert(doc6);
		
		
		System.out.println("done");
	}
}
