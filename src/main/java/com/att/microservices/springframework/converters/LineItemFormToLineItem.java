package com.att.microservices.springframework.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.att.microservices.springframework.commands.LineItemForm;
import com.att.microservices.springframework.domain.LineItem;


@Component
public class LineItemFormToLineItem implements Converter<LineItemForm, LineItem> {

	@Override
	public LineItem convert(LineItemForm lineItemForm) {
		LineItem lineItem = new LineItem();
		if (lineItemForm.getId() != null && !StringUtils.isEmpty(lineItemForm.getId())) {
			lineItem.setId(new ObjectId(lineItemForm.getId()));
		}
		
		lineItem.setCartId(lineItemForm.getCartId());
		lineItem.setProductId(lineItemForm.getProductId());
		lineItem.setQuantity(lineItemForm.getQuantity());
		lineItem.setTotalPrice(lineItemForm.getTotalPrice());
		return lineItem;
	}
}
