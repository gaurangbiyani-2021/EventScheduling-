package com.yash.model;


public class Events {
	private int event_id,user_id;
	private String name;
	private String event_date;
	private String start_time;
	private String end_time;
	private String description;
	
	public Events(int user_id, String name, String event_date, String start_time, String end_time, String description) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.event_date = event_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.description = description;
	}
	public Events(String name, String event_date, String start_time, String end_time, String description) {
		super();
		this.name = name;
		this.event_date = event_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.description = description;
	}
	public int getEvent_id() {
		return event_id;
	}
	public Events(int event_id, int user_id, String name, String event_date, String start_time, String end_time,
			String description) {
		super();
		this.event_id = event_id;
		this.user_id = user_id;
		this.name = name;
		this.event_date = event_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.description = description;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return name;
	}
	public void setEvent_name(String name) {
		this.name = name;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Events [event_id=" + event_id + ", name=" + name + ", event_date=" + event_date
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", description=" + description
				+ ", getEvent_id()=" + getEvent_id() + ", getEvent_name()=" + getEvent_name() + ", getEvent_date()="
				+ getEvent_date() + ", getStart_time()=" + getStart_time() + ", getEnd_time()=" + getEnd_time()
				+ ", getDescription()=" + getDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
}
