package com.liquid.rest.test;

public class Tag {
	
	private String k;
	private String v;
	
	Tag(String key, String value) {
		this.k = key;
		this.v = value;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	
}
