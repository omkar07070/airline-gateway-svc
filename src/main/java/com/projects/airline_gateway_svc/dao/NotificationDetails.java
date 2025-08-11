package com.projects.airline_gateway_svc.dao;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "notification_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "username")
    private String username;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "acc_balance")
    private BigDecimal accBalance;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "send_notification_mobile")
    private Boolean sendNotificationMobile;

    @Column(name = "send_notification_email")
    private Boolean sendNotificationEmail;
}