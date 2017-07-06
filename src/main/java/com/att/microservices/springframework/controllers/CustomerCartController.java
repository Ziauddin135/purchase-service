package com.att.microservices.springframework.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.microservices.springframework.commands.LineItemForm;
import com.att.microservices.springframework.domain.Cart;
import com.att.microservices.springframework.domain.LineItem;
import com.att.microservices.springframework.domain.Order;
import com.att.microservices.springframework.services.CartService;
import com.att.microservices.springframework.services.LineItemService;
import com.att.microservices.springframework.services.OrderService;

@RestController
@RequestMapping(value = "/purchase-service/v1/cart")
public class CustomerCartController {

	@Autowired
	CartService cartService;

	@Autowired
	LineItemService lineItemService;

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/getItemCounts", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public String getItemCounts(@RequestParam Map<String, String> requestParams, HttpServletRequest request,
			HttpServletResponse response) {
		return "" + lineItemService.getLineItemsCount(requestParams.get("custId"));
	}

	public List<LineItemForm> getLineItems() {
		return new ArrayList<LineItemForm>();
	}

	@RequestMapping(value = "/deleteItem/{id}/{custId}", method = RequestMethod.GET)
	public void deleteLineIem(@PathVariable("id") String id, @PathVariable("custId") String custId,
			HttpServletRequest request, HttpServletResponse response) {
		lineItemService.deleteLineItem(custId, id);
	}

	@RequestMapping(value = "/saveOrder/{custId}", method = RequestMethod.GET)
	public @ResponseBody List<Order> saveOrder(@PathVariable("custId") String custId, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("saved orders received from feign client");
		return orderService.saveOrder(custId);

	}

	@RequestMapping(value = "/getOrder/{custId}", method = RequestMethod.GET, produces = { "application/json",
	"application/xml" })
	public @ResponseBody List<Order> getOrdersByCustId(@PathVariable("custId") String custId) {
		System.out.println("getting orders requested from feign client for >"+custId);
		return orderService.getOrdersByCustId(custId);
	}

	@RequestMapping(value = "/getCartItems/{custId}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public @ResponseBody List<LineItem> getCartItems(@PathVariable("custId") String custId, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("reaching...............custId" + custId);
		// System.out.println("reaching...............custId"+requestParams.get("items"));
		System.out.println("reaching...............");
		Cart cart = cartService.getCartByCustId(custId);
		List<LineItem> list = lineItemService.getLineItemsByCartId(cart.getId().toHexString());
		System.out.print("getCartItems list >>>" + list);
		return list;
		// return "redirect:/product/list";
	}

	@RequestMapping(value = "/addToCart/{id}/{custId}/{price}", method = RequestMethod.GET, produces = {
			"application/json", "application/xml" })
	public void addToCart(@PathVariable("id") String id, @PathVariable("custId") String custId,
			@PathVariable("price") int price, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("reaching...............id" + id);
		System.out.println("reaching...............custId" + custId);
		// System.out.println("reaching...............custId"+requestParams.get("items"));
		System.out.println("reaching...............");
		lineItemService.saveLineItem(id, custId, price);
		// return "redirect:/product/list";
	}
}
