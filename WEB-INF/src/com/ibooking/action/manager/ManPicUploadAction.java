package com.ibooking.action.manager;

import java.io.File;

import com.ibooking.action.base.*;

public class ManPicUploadAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	@Override
	public String execute() throws Exception {
		daoService.uploadPic(uploadFileName, upload);
		
		return fillManPicPage();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
