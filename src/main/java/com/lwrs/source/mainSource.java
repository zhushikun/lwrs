package com.lwrs.source;


import com.lwrs.bean.dto.resp.BaseRespDto;
import com.lwrs.enums.RespCodeEnum;
import com.lwrs.utils.Logger;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by zsk on 16/9/15.
 */
@Path("/pic")
public class mainSource {
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "./src/main/webapp/ugc/img/";
	private static final String SERVER_DOWNLOA_LOCATION_PATH = "./src/main/webapp/ugc/img";



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public String test(@QueryParam("param") String param){
		Logger.info(this, "param=" + param);
		Logger.warn(this, "warn param=" + param);
		Logger.error(this, "error param=" + param);
		throw new RuntimeException("test");

//		return new BaseRespDto(RespCodeEnum.OK, param).toString();
	}

	@GET
	@Path("/download")
	public Response download(@QueryParam("fileName") String fileName) throws IOException {
		if(StringUtils.isEmpty(fileName)){
			Response.status(200).entity(new BaseRespDto(RespCodeEnum.PARAM_INVALID, fileName).toString()).build();
		}

		String fullPath = String.format("%s%s", SERVER_DOWNLOA_LOCATION_PATH,
				'/'==fileName.charAt(0)? fileName : "/" + fileName);

		File file = new File(fullPath);
		Logger.info(this, "path="+fullPath);

		if(!file.exists()){
			Logger.info(this, "file no find, fileName="+fileName);
			return Response.status(200).header("Content-Type", MediaType.APPLICATION_JSON)
					.entity(new BaseRespDto(RespCodeEnum.OK, "not find file "+fileName).toString()).build();
		}

		final FileInputStream fileInputStream = new FileInputStream(file);
		StreamingOutput stream = new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				try {
					byte[] byteBuffer = new byte[1024];
					int len;
					while(-1 != (len = fileInputStream.read(byteBuffer))){
						output.write(byteBuffer, 0, len);
					}

				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
		//兼容浏览器 文件名乱码
//		String headerFileName = URLEncoder.encode(fileName,"UTF-8")
//				+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1")
//				+ ";filename*=UTF-8''"+URLEncoder.encode(fileName,"UTF-8");
		String headerFileName = "filename*=UTF-8''"+URLEncoder.encode(fileName,"UTF-8");


		Response response = Response.ok(stream).header("Content-Type", MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition",  "attachment;" + headerFileName).build();
		return response;
	}



	@POST
	@Path("/ugc")
	@Consumes(MediaType.MULTIPART_FORM_DATA+ ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("file") InputStream inputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader)
			throws UnsupportedEncodingException {
		String sourceName = contentDispositionHeader.getFileName();

		//jersey 1.X default use charSet=ISO-8859-1
		String fileName = new String(sourceName.getBytes("ISO-8859-1"), "UTF-8");
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER	+ fileName;

		// save the file to the server
		saveFile(inputStream, filePath);

		String output = "File saved to server location : " + filePath;

		return Response.status(200).entity(new BaseRespDto(RespCodeEnum.OK, output).toString()).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}



}
