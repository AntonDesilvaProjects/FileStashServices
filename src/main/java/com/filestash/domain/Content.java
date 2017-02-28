package com.filestash.domain;

import org.springframework.stereotype.Component;

import com.filestash.utility.TimeUtility;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
public class Content {
	
	private int id;
	private int owner;
	private String name;
	private String type;
	private String path;
	private long size;
	private LocalDateTime uploadTime;
	private String readbleUploadTime;
	private LocalDateTime lastModified;
	private String readbleLastModified;
	private String image;
	private String author; //Whoever that created file/folder; not necessarily the owner
	
	public Content() {}
		
	public Content(int id, int owner, String name, String type, String path, long size, LocalDateTime uploadTime, LocalDateTime lastModified, String image) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.type = type;
		this.path = path;
		this.size = size;
		this.uploadTime = uploadTime;
		this.lastModified = lastModified;
		this.image = image;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public LocalDateTime getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
		this.setReadbleUploadTime(TimeUtility.getReadableFormat(this.uploadTime));
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
		this.setReadbleLastModified(TimeUtility.getReadableFormat(this.lastModified));
	}
	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getReadbleUploadTime() {
		return readbleUploadTime;
	}

	public void setReadbleUploadTime(String readbleUploadTime) {
		this.readbleUploadTime = readbleUploadTime;
	}

	public String getReadbleLastModified() {
		return readbleLastModified;
	}

	public void setReadbleLastModified(String readbleLastModified) {
		this.readbleLastModified = readbleLastModified;
	}

}
