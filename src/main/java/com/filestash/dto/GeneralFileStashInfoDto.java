package com.filestash.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import com.filestash.domain.LogItem;
import com.filestash.utility.TimeUtility;

@Component
public class GeneralFileStashInfoDto {
	
	private long fileStashSize;
	private LocalDateTime lastModified;
	private String readableLastModified;
	private List<LogItem> logs;
	private String firstName = "Peprika";
	private String lastName = "Desilva";
	
	public GeneralFileStashInfoDto()
	{
		fileStashSize = 0;
		lastModified = null;
		logs = null;
	}

	public long getFileStashSize() {
		return fileStashSize;
	}

	public void setFileStashSize(long fileStashSize) {
		this.fileStashSize = fileStashSize;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
		this.setReadableLastModified(TimeUtility.getReadableFormat(this.lastModified));
	}

	public List<LogItem> getLogs() {
		return logs;
	}

	public void setLogs(List<LogItem> logs) {
		this.logs = logs;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	public String getReadableLastModified() {
		return readableLastModified;
	}

	public void setReadableLastModified(String readableLastModified) {
		this.readableLastModified = readableLastModified;
	}

}
