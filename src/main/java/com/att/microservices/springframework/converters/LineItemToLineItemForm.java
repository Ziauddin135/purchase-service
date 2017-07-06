package com.att.microservices.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.att.microservices.springframework.commands.LineItemForm;
import com.att.microservices.springframework.domain.LineItem;

@Component
public class LineItemToLineItemForm implements Converter<LineItem, LineItemForm> {

	@Override
	public LineItemForm convert(LineItem lineItem) {
		LineItemForm lineItemForm = new LineItemForm();
		lineItem.setCartId(lineItemForm.getCartId());
		lineItem.setProductId(lineItemForm.getProductId());
		lineItem.setQuantity(lineItemForm.getQuantity());
		lineItem.setTotalPrice(lineItemForm.getTotalPrice());
		return lineItemForm;
	}
}
