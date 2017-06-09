package com.rmi.server.example.server;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rmi.server.example.shared.HelloMessage;
import com.rmi.server.example.shared.HelloResponse;
import com.rmi.server.example.shared.HelloService;

/**
 * @author
 */
public class HelloServiceImpl implements HelloService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
    	this.dataSource = dataSource;
    }
    public HelloResponse sayHello(HelloMessage message) {
    	
    	try(Connection conn = dataSource.getConnection()) {
    		logger.info(conn.toString());
         } catch(Exception e) {
             e.printStackTrace();
         }
    	
    	
    	try {
    		logger.info("sleep 10secs");
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	String messageText = message.getText();
    	logger.debug("sayHello() is called, message text: " + messageText);
        HelloResponse response = new HelloResponse();
        String text = "Hello from server, your message:  " + messageText;
        response.setText(text);
        return response;
    }

}
