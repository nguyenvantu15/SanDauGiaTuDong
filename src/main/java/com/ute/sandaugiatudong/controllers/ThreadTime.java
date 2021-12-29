package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.HistoryAuctionModels;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.UserModels;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

public class ThreadTime extends TimerTask {
    @Override
    public void run() {
        List<Product> listPro = ProductModels.findAll();
        LocalDateTime n = LocalDateTime.now();

//        String txtNowTmp = nowTmp.toString();

//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        LocalDateTime timenow = LocalDateTime.parse(txtNowTmp, df);

//        LocalDateTime now = LocalDateTime.parse(nowTmp.format(formatter));

        for (int i = 0; i < listPro.size(); i++) {
            LocalDateTime s = listPro.get(i).getTimeEnd();
            if (s.getYear() == n.getYear() && s.getMonthValue() == n.getMonthValue() && s.getDayOfMonth() == n.getDayOfMonth() && s.getHour() == n.getHour() && s.getMinute() == n.getMinute() && s.getSecond() == n.getSecond()) {
                System.out.println(listPro.get(i).getName());
                List<HistoryAuction> listAuction = HistoryAuctionModels.findByIdProduct1(listPro.get(i).getId());
                for (int j = 0; j < listAuction.size(); j++) {
                    User user = UserModels.findById(listAuction.get(j).getIdBidder());
                    String notify = "san pham: " + listPro.get(i).getName() + "da ket thuc dau gia";
                    sendMailNotifyBidder(notify, user.getEmail().trim());
                }
            }
        }
    }

    private void sendMailNotifyBidder(String notify, String reMail) {

        final String sendMail = "atttsystem@gmail.com";
        final String passSendMail = "ATTT19110306";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendMail, passSendMail);
            }
        });
        // Đăng nhập được email
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendMail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(reMail)
            );
            message.setSubject("Thông Báo:");
            message.setText(notify);
            Transport.send(message);
        } catch (Exception e) {

        }
    }

}