package br.gov.msq.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author marco.nascimento
 */
public class UploadFile extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIR = "/home/tulio/AmbienteDesenvolvimento/ms/phonegap/testFileUploadDir/";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	        	
	        	log("FieldName="+item.getFieldName());
	        	log("FileName="+item.getName());
	        	log("ContentType="+item.getContentType());
	        	log("Size in bytes="+item.getSize());
	            
	            File file = new File(UPLOAD_DIR + "UPLOAD_" + new SimpleDateFormat("dd_MM_yyyy_HHmmss").format(new Date()));
	            file.createNewFile();
	            
	            copy(item.getInputStream(), file);
	        }
	    } catch (FileUploadException e) {
	        throw new ServletException(e.getMessage());
	    }
	}
	
	private void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
