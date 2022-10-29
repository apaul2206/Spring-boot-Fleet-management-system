package com.example.demo.Service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	public void init(MultipartFile file, String type, String id);

	  public void save(MultipartFile file,Path root);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();
}
