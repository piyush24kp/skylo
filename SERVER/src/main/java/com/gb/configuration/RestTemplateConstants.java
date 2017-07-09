package com.gb.configuration;

public enum RestTemplateConstants {
	
	HEADER_KEY("Content-Disposition"," "),
	ALLOW_ORIGIN("Access-Control-Allow-Origin", "*"),
	ALLOW_METHODS("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE"),
	MAX_AGE("Access-Control-Max-Age", "3600"),
	ALLOW_CREDENTIALS("Access-Control-Allow-Credentials", "true"),
	ALLOW_HEADERS("Access-Control-Allow-Headers", "content-type"),
	ACCEPT("Accept", "application/json"),
	AUTHORIZATION("Authorization", ""),
	X_MS_CORELATION_ID("MS-CorrelationId",""),
	MS_CONTRACT_VERSION("MS-Contract-Version","v1"),
	X_MS_TRACKING_ID("MS-RequestId", ""),
	API_VERSION("api-version", "2015-03-31"),
	CONTENT_TYPE_URL_ENCODED("Content-Type", "application/x-www-form-urlencoded"),
	CONTENT_TYPE_JSON("Content-Type", "application/json"),
	X_AUTH_TOKEN("X-Auth-Token", ""),
	X_LOCALE("X-Locale", "en-US"),
	X_Frame_Options("X-Frame-Options","SAMEORIGIN");
	
	private String name; 
	private String value;

	RestTemplateConstants(final String name, final String value) {
		this.name= name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.getValue();
	}

}
