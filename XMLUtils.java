package CommentSystem;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLUtils {
	
	public static Object[][] getTableArray() {
		   String[][] tabArray = null;
		
	      try {
	    	 String XMLFilePath = new java.io.File( "." ).getAbsolutePath().replace(".", "")+"Storage/UsersCommentsStorage.xml"; 
	         File inputFile = new File(XMLFilePath);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();

	         String expression = "//UsersList/User";	        
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
	            doc, XPathConstants.NODESET);
	         
	         int totalRows = nodeList.getLength();
	         tabArray=new String[totalRows][3];
	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	              
	               String username =eElement.getElementsByTagName("UserName").item(0).getTextContent();
	               String pass = eElement.getElementsByTagName("Password").item(0).getTextContent();
	               String comment =eElement.getElementsByTagName("Comment").item(0).getTextContent();  	   
	               String[] row = { username,pass,comment };
	               tabArray[i] = row; 
	            }
	         }
	         
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	      
	     return tabArray;
		}

}
