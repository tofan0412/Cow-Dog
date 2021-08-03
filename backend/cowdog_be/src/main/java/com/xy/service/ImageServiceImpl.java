package com.xy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.Image;
import com.xy.entity.Member;
import com.xy.repository.ImageRepository;
import com.xy.repository.MemberRepository;

@Service
public class ImageServiceImpl implements ImageService{

	
	
	@Autowired
	ImageRepository imageRepo;
	@Autowired
	MemberRepository memRepo;
	
	
	@Override
	public int upload(Image image, String userId) {
		System.out.println("이미지 업로드~");
		System.out.println(image.toString());
		Member mem=memRepo.getBymemberid(userId);
		System.out.println(mem.toString());
		mem.setFile_path(image.getFile_path());
		mem.setImgFullPath(image.getImgFullPath());
		mem.setTitle("sdf");
		if(memRepo.save(mem)!=null) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public List<Image> getImageList() {
	    return imageRepo.findAll();
	 }
}
