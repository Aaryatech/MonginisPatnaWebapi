package com.ats.webapi.model;

public class SearchSpCakeResponse {
ErrorMessage errorMessage;
OrderSpecialCake specialCake;
public ErrorMessage getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(ErrorMessage errorMessage) {
	this.errorMessage = errorMessage;
}
public OrderSpecialCake getSpecialCake() {
	return specialCake;
}
public void setSpecialCake(OrderSpecialCake specialCake) {
	this.specialCake = specialCake;
}
@Override
public String toString() {
	return "SearchSpCakeResponse [errorMessage=" + errorMessage + ", specialCake=" + specialCake + "]";
}

}
