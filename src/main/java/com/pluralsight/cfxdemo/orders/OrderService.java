package com.pluralsight.cfxdemo.orders;

import com.pluralsight.schema.order.OrderInquiryResponseType;

public interface OrderService {

	public OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long ean13);
}
