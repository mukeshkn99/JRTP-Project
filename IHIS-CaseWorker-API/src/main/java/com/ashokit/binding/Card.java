package com.ashokit.binding;


public class Card {
private Integer countPlans;
public Integer getCountPlans() {
	return countPlans;
}
public void setCountPlans(Integer countPlans) {
	this.countPlans = countPlans;
}
public Integer getAppPlans() {
	return appPlans;
}
public void setAppPlans(Integer appPlans) {
	this.appPlans = appPlans;
}
public Integer getApprovedStatus() {
	return approvedStatus;
}
public void setApprovedStatus(Integer approvedStatus) {
	this.approvedStatus = approvedStatus;
}
public Integer getDeniedStatus() {
	return deniedStatus;
}
public void setDeniedStatus(Integer deniedStatus) {
	this.deniedStatus = deniedStatus;
}
private Integer appPlans;
private Integer approvedStatus;
private Integer deniedStatus;
}
