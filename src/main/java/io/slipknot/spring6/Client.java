package io.slipknot.spring6;

import io.slipknot.spring6.payment.Payment;
import io.slipknot.spring6.payment.PaymentService;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws InterruptedException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
    PaymentService paymentService = beanFactory.getBean(PaymentService.class);

    Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(100));
    System.out.println("Payment: " + payment1);
    System.out.println("==================================\n");

    TimeUnit.SECONDS.sleep(1);

    Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(100));
    System.out.println("Payment: " + payment2);
    System.out.println("==================================\n");

    TimeUnit.SECONDS.sleep(2);

    Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(100));
    System.out.println("Payment: " + payment3);
  }

}
