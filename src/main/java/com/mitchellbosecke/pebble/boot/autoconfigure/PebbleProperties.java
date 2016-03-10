package com.mitchellbosecke.pebble.boot.autoconfigure;

import java.nio.charset.Charset;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.MimeType;

@ConfigurationProperties("pebble")
public class PebbleProperties {

	private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");
	private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

	public static final String DEFAULT_PREFIX = "templates/";
	public static final String DEFAULT_SUFFIX = ".pebble";

	private String prefix = DEFAULT_PREFIX;
	private String suffix = DEFAULT_SUFFIX;
	private Charset encoding = DEFAULT_ENCODING;
	private MimeType contentType = DEFAULT_CONTENT_TYPE;
	private boolean cache = true;

	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Charset getEncoding() {
		return encoding;
	}
	public void setEncoding(Charset encoding) {
		this.encoding = encoding;
	}
	public MimeType getContentType() {
		return contentType;
	}
	public void setContentType(MimeType contentType) {
		this.contentType = contentType;
	}
	public boolean isCache() {
		return cache;
	}
	public void setCache(boolean cache) {
		this.cache = cache;
	}

}