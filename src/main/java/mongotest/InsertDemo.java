package mongotest;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertDemo {
	public static void main(String[] args) throws UnknownHostException {
		// mở kết nối
		MongoClient mongoClient = MongoUtils.getMongoClient();
		DB db = mongoClient.getDB(MyConstants.DB_NAME);	

		// lấy ra colection có tên department
		// nếu chưa tồn tại thì nó sẽ tạo ra thế thôi\
		DBCollection dept = db.getCollection("Deparment");

		// insert document
		BasicDBObject doc1 = new BasicDBObject();
		doc1.append("_id", 10);
		doc1.append("dept_no", "D10");
		doc1.append("dept_name", "ACCOUNTING");
		doc1.append("location", "NEW YORK");
		dept.insert(doc1);
		// Insert Document 2

		BasicDBObject doc2 = new BasicDBObject();
		doc2.append("_id", 20);
		doc2.append("dept_no", "D20");
		doc2.append("dept_name", "RESEARCH");
		doc2.append("location", "DALLAS");
		doc2.append("description", "First department");
		dept.insert(doc2);

		// Insert Document 3
		BasicDBObject doc3 = new BasicDBObject();
		doc3.append("_id", 30);
		doc3.append("dept_no", "D30");
		doc3.append("dept_name", "SALES");
		doc3.append("location", "CHICAGO");
		dept.insert(doc3);

		// Insert Document 4
		BasicDBObject doc4 = new BasicDBObject();
		doc4.append("_id", 40);
		doc4.append("dept_no", "D40");
		doc4.append("dept_name", "OPERATIONS");
		doc4.append("location", "BOSTON");
		dept.insert(doc4);

		System.out.println("Done!");
	}
}
