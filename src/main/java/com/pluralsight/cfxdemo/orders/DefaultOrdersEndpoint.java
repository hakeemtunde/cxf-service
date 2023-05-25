package com.pluralsight.cfxdemo.orders;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderInquiryType;
import com.pluralsight.service.orders.Orders;

@WebService(portName="OrdersSOAP", serviceName="Orders",
endpointInterface="com.pluralsight.service.orders.Orders",
targetNamespace="http://www.pluralsight.com/service/Orders/")
public class DefaultOrdersEndpoint implements Orders {

	@Autowired
//	@Qualifier("orderService")
	private OrderService orderService;
	
	
	public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) {
//		ObjectFactory factory = new ObjectFactory();
//		OrderInquiryResponseType response = factory.createOrderInquiryResponseType();
//		AccountType account = factory.createAccountType();
//		account.setAccountId(1);
//		response.setAccount(account);
//		OrderServiceImpl orderService = new OrderServiceImpl();
		OrderInquiryResponseType response = orderService.processOrder(
				orderInquiry.getUniqueOrderId(), orderInquiry.getOrderQuantity(),
				orderInquiry.getAccountId(), orderInquiry.getEan13());
		return response;
	}

}
