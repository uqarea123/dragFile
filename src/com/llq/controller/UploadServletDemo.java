package com.llq.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FilenameUtils;

public class UploadServletDemo extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}
		// Create a factory for disk-based file items

		String filePath="d:\\myUpload";
		
		FileItemFactory factory = newDiskFileItemFactory(getServletContext(),
				new File(filePath));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		/* FileItem */
		List<FileItem> items = null;
		try {
			// Parse the request
			items = (List<FileItem>) upload.parseRequest(request);
			String filename = null;
			for (FileItem fileItem : items) {
				if (!fileItem.isFormField()) {// file
					filename = FilenameUtils.getName(fileItem.getName());
					fileItem.write(new File(filePath +"\\"+ filename));
					response.getWriter()
							.write(filePath +"\\"+ filename);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The FileCleanerCleanup provides an instance of
	 * org.apache.commons.io.FileCleaningTracker. This instance must be used
	 * when creating a org.apache.commons.fileupload.disk.DiskFileItemFactory.
	 */
	public static DiskFileItemFactory newDiskFileItemFactory(
			ServletContext context, File repository) {
		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup
				.getFileCleaningTracker(context);
		DiskFileItemFactory factory = new DiskFileItemFactory(
				DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository);
		factory.setFileCleaningTracker(fileCleaningTracker);
		return factory;
	}

}
