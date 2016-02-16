package dataBaseEntities;

import dataBaseOperations.finishOperations;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@ManagedBean
@SessionScoped
@Entity
public class visa {

    private int id;
    private String userEmail;
    private String visa;

    @Id
    @TableGenerator(name = "g", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "g")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public void finish(String email) {
        setUserEmail(email);
        finishOperations db = new finishOperations();
        db.finish(this);
        sendConfirmationEmail(email);
    }

    public void sendConfirmationEmail(String clientEmail) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("khalid.mahmoud.basuiny@gmail.com", "01003123911");
                    }
                }
        );

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("khalid.mahmoud.basuiny@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(clientEmail));
            message.setSubject("Ghadeni");
            message.setText(" thank you for using Ghadeni ... we will call you when your order is delivered.");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
