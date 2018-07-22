/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrator
 */
public class EmailUtil {

    public static void send_mail(String to, String text) throws MessagingException {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.smtp.host", "smtp.163.com");
        //发送端口
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("ds825456885@163.com", "ds960527");
            }
        });
        StringBuffer messageText = new StringBuffer();//内容以html格式发送,防止被当成垃圾邮件
        messageText.append("<h2>"+text+"</h2></br>");
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("ds825456885@163.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //设置主题
        message.setSubject("DonkeyKong Travelling System");
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(messageText.toString(), "text/html;charset=gb2312");
        //发送一封邮件
        Transport.send(message);
    }

}