
// begin x509 connection
import java.util.Arrays;
import org.bson.Document;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;


System.setProperty("javax.net.ssl.trustStore","/etc/certs/mongodb/client.truststore");
System.setProperty("javax.net.ssl.trustStorePassword","mypass");

System.setProperty("javax.net.ssl.keyStore", "/etc/certs/mongodb/client.keystore");
System.setProperty("javax.net.ssl.keyStorePassword", "mypass");
String subject = "<your_subject>"; // CN=ChrisChoClient,OU=TestClientCertificateOrgUnit,O=TestClientCertificateOrg,L=TestClientCertificateLocality,ST=TestClientCertificateStateC=US
MongoCredential credential = MongoCredential.createMongoX509Credential(subject); 
MongoClientSettings settings = MongoClientSettings.builder()
        .credential(credential)
        .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress("localhost"))))
        .applyToSslSettings(builder -> builder.enabled(true)).build();
MongoClient client = MongoClients.create(settings);
// end x509 connection