package com.pluralsight.cfxdemo.orders;

import static org.junit.Assert.assertTrue;

import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderInquiryType;
import com.pluralsight.service.orders.Orders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("{classpath:*/test-beans.xml}")
public class DefaultOrdersEndpointTest {
	
	private Orders ordersService;
	private OrderInquiryType orderInquiryType;
	
//	@Autowired
//	private JaxWsProxyFactoryBean testOrdersClient;
	
	
	private JaxWsProxyFactoryBean jaxWsFactory = new JaxWsProxyFactoryBean();

	@Before
	public void setUp() throws Exception {
		
		jaxWsFactory.setAddress("http://localhost:8080/apache-cfx-jax-ws-demo/services/orders");
		jaxWsFactory.setServiceClass(Orders.class);
		jaxWsFactory.getInInterceptors().add(new LoggingInInterceptor());
		jaxWsFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		ordersService = jaxWsFactory.create(Orders.class);
		ObjectFactory factory = new ObjectFactory();
		orderInquiryType = factory.createOrderInquiryType();
		orderInquiryType.setAccountId(2000);
		orderInquiryType.setEan13(1234567890123L);
		orderInquiryType.setOrderQuantity(4);
		orderInquiryType.setUniqueOrderId(12345);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		OrderInquiryResponseType response = ordersService
				.processOrderPlacement(orderInquiryType);
		assertTrue(response.getAccount().getAccountId() == 2000);
	}
//	
//	@Test(expected = SOAPFaultException.class)
//	public void testProcessOrderPlacementFailureInvalidParameter() throws Exception{
//		ordersService.processOrderPlacement(null);
//	}

}
