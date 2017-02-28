package com.filestash.services;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.filestash.domain.LogItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentServiceTest {

	@Autowired
	ContentService service;
	
	@Test
	public void smokeTest()
	{
		System.out.println("******");
		List<LogItem> info = service.getFileStashLogs(4, -1, -1, 100, 0);
	}
	
}
