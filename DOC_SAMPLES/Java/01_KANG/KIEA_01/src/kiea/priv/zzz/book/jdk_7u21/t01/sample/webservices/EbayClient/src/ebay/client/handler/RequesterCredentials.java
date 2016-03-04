package kiea.priv.zzz.book.jdk_7u21.t01.sample.webservices.EbayClient.src.ebay.client.handler;

/*
 * RequesterCredentials.java
 *
 * Created on August 29, 2006, 11:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author mode
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class RequesterCredentials implements SOAPHandler<SOAPMessageContext> {
    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileReader("ebay.properties"));
        }catch (FileNotFoundException fne) {
            System.out.println("Could not find ebay.properties");
            System.exit(1);
        }catch (IOException ioe) {
            System.out.println("Error reading ebay.properties " + ioe.getMessage());
            System.exit(1);
        }
    }

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        addRequesterCredentials(smc);
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        return true;
    }


    public void close(MessageContext messageContext) {
    }

    private void addRequesterCredentials(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean)
            smc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            SOAPMessage message = smc.getMessage();
            try {
                SOAPHeader header = message.getSOAPHeader();
                if (header == null) {
                    message.getSOAPPart().getEnvelope().addHeader();
                    header = message.getSOAPHeader();
                }
                SOAPElement heSecurity = header.addChildElement
                                        ("RequesterCredentials",
                                        "ebl",
                                        "urn:ebay:apis:eBLBaseComponents");
                heSecurity.addChildElement("eBayAuthToken", "ebl", "urn:ebay:apis:eBLBaseComponents").addTextNode((String) props.get("authToken"));
                SOAPElement userNameToken = heSecurity.addChildElement("Credentials", "ebl", "urn:ebay:apis:eBLBaseComponents");
                userNameToken.addChildElement("AppId", "ebl", "urn:ebay:apis:eBLBaseComponents").addTextNode((String) props.get("appID"));
                userNameToken.addChildElement("DevId", "ebl", "urn:ebay:apis:eBLBaseComponents").addTextNode((String) props.get("devID"));
                userNameToken.addChildElement("AuthCert", "ebl", "urn:ebay:apis:eBLBaseComponents").addTextNode((String) props.get("certID"));
                //message.writeTo(System.out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
