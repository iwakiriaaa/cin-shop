package com.cinshop.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.PaymentMethod;
import com.cinshop.common.entity.Tax;
import com.cinshop.utility.MailSenderHelper;

@Service
public abstract class AbstractOrderService {
	protected AbstractCartService cartService;
	protected final String PAYMENT_METHOD = "paymentMethod";
	protected final String CREDIT_DETAIL = "creditDetail";

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected OrderUtility utility;

	@Autowired
	protected MailSenderHelper mailSenderHelper;

	public abstract Order saveOrder(Map<String, Object> orderInfo) ;


	protected List<OrderDetail> saveOrderDetail(Order order) {
		List<OrderDetail> details = new ArrayList<>();
		List<CartItem> cartItems = cartService.findCartItems();
		Iterator<CartItem> it = cartItems.iterator();
		while (it.hasNext()) {
			CartItem item = (CartItem) it.next();
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(item.getProduct());
			detail.setQuantity(item.getQuantity());

			Integer subTotal = item.getQuantity() * item.getProduct().getPrice();
			detail.setSubTotal(subTotal);
			details.add(detail);
		}
		return details;
	}

	public Integer getTotalIgnoretax() {
		List<CartItem> cartItems = cartService.findCartItems();
		Integer subTotal = 0;
		for (CartItem item : cartItems) {
			subTotal += item.getQuantity() * item.getProduct().getPrice();
		}
		return subTotal;
	}

	public Integer getTotalWithTax() {
		Tax tax = utility.getCurrentTax();
		return getTotalIgnoretax() + (getTotalIgnoretax() * tax.getTax() / 100);
	}

	public Integer getTotal() {
		return getShippingCost() + getTotalWithTax();
	}

	public Integer getShippingCost() {
		// ???????????????????????????????????????
		// ??????????????????????????????????????????
		return 500;
	}

	public Tax getCurrentTax() {
		return utility.getCurrentTax();
	}

	public List<PaymentMethod> findAllPaymentMethods() {

		return utility.findAllPaymentMethods();

	}

	/* setter - getter */

	public AbstractCartService getCartService() {
		return cartService;
	}

	public void setCartService(AbstractCartService cartService) {
		this.cartService = cartService;
	}

}
