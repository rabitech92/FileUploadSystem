package com.example.demo.service;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	
	private final Path root=Paths.get("upload");
	
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not Initialize folder Upload");
			
		}
		
	}
	   public void save( MultipartFile file) {
	        
	        try {
	            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	            
	            
	        } catch (Exception e) {
	            
	            if( e instanceof FileAlreadyExistsException) {
	                
	                throw new RuntimeException("A file of that name already exist. ");
	            }
	            
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	    
	

}
