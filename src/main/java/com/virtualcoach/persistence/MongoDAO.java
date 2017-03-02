package com.virtualcoach.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.client.FindIterable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class MongoDAO {
	
	private MongoClient mongoClient;
	private MongoDatabase db;
	
	public MongoDAO(String db){
		this.mongoClient = new MongoClient();
		this.db = mongoClient.getDatabase(db);
	}
	
	public Document getDocument(String collection, String key, String value) {
		
		FindIterable<Document> iterable = this.db.getCollection(collection).find();
		Document doc = new Document();
		Iterator<Document> iter = iterable.iterator();
		
		boolean trovato = false;
		
		while (iter.hasNext() && !trovato){
			
			Document document = iter.next();
			String s = (String) document.get(key);
			
			if (s.equals(value)){
				doc = document;
				trovato = true;
			}
		}
		
		return doc;
		
	}

	public Document getGiornataCorrente() throws ParseException {
		
	GregorianCalendar gc = new GregorianCalendar();
	int g = gc.get(GregorianCalendar.DATE);
	int m = gc.get(GregorianCalendar.MONTH)+1;
	int a = gc.get(GregorianCalendar.YEAR);
	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String data = g + "/" + m + "/" + a;	
	Date dataCorrente = sdf.parse(data);
	
	FindIterable<Document> iterable = this.db.getCollection("giornate").find();
	Document giornata = new Document();
	Iterator<Document> iter = iterable.iterator();
	
	boolean trovato = false;
	
	while (iter.hasNext() && !trovato){
		
		Document document = iter.next();
		String i = (String) document.get("inizio");
    	String f = (String) document.get("fine");
    	Date inizio = new Date();
    	Date fine = new Date();
		inizio = sdf.parse(i);
		fine = sdf.parse(f);
		
		if (dataCorrente.equals(inizio) || dataCorrente.equals(fine) || (dataCorrente.after(inizio) && dataCorrente.before(fine))){
			trovato = true;
			giornata = document;
		}
	}
	
	return giornata;
	
	}
	
	public Document getGiornata() throws ParseException {
		
	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String data = "06/01/2017";	
	Date dataCorrente = sdf.parse(data);
	
	FindIterable<Document> iterable = this.db.getCollection("giornate").find();
	Document giornata = new Document();
	Iterator<Document> iter = iterable.iterator();
	
	boolean trovato = false;
	
	while (iter.hasNext() && !trovato){
		
		Document document = iter.next();
		String i = (String) document.get("inizio");
    	String f = (String) document.get("fine");
    	Date inizio = new Date();
    	Date fine = new Date();
		inizio = sdf.parse(i);
		fine = sdf.parse(f);
		
		if (dataCorrente.equals(inizio) || dataCorrente.equals(fine) || (dataCorrente.after(inizio) && dataCorrente.before(fine))){
			trovato = true;
			giornata = document;
		}
	}
	
	return giornata;
	
	}
	
}
