package egovframework.dev.draft.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import egovframework.dev.draft.dao.DraftDAO;
import egovframework.dev.draft.service.DraftService;
import egovframework.dev.draft.vo.DraftVO;
import egovframework.dev.test.vo.TestVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.terracotta.agent.repkg.de.schlichtherle.io.FileOutputStream;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

	@Service("draftService")
	public class DraftServiceImpl extends AbstractServiceImpl implements DraftService {

		@Resource(name="draftDAO")
		private DraftDAO draftDAO;

	    /**
	     * 리스트(L)
	     * @param TestVO 검색VO
	     * @return List<TestVO>
	     */

		@Override
		public List<DraftVO> draftTestList(DraftVO srchVO) {
			return draftDAO.selectDraftList(srchVO);
		}

		@Override
		public List<DraftVO> selectBaseInfo(DraftVO srchVO) {

			return draftDAO.selectBaseInfo(srchVO);
		}

		@Override
		public DraftVO selectUserInfo(DraftVO srchVO) {
			return draftDAO.selectUserInfo(srchVO);
		}

		@Override
		public void draftInsert(DraftVO srchVO) throws Exception {

			srchVO.setUseYn("N");
			if(srchVO.getFile().getSize()>0){
				srchVO = fileWrite(srchVO);
			}

			int seq = draftDAO.draftInsert(srchVO);
			srchVO.setSeq(seq);
			draftDAO.draftFileInsert(srchVO);
		}


		private DraftVO fileWrite(DraftVO vo) throws Exception{
			MultipartFile file = vo.getFile();
			String realFileNm = file.getOriginalFilename();
			String saveFileNm = URLEncoder.encode(realFileNm,"UTF-8");
			InputStream inputStream = file.getInputStream();

			File newFile = new File("C:/Users/Skysoft_D001/Desktop/saveFile/"+realFileNm);
			if(!newFile.exists()){
				newFile.createNewFile();
			}
			OutputStream outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1){
				 outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			vo.setSaveFileNm(saveFileNm);
			vo.setRealFileNm(realFileNm);
			vo.setUseYn("Y");
			return vo;
		}

		@Override
		public DraftVO selectByPk(DraftVO srchVO) {
			return draftDAO.selectByPk(srchVO);
		}

		@Override
		public void draftRecovery(DraftVO srchVO) {
			draftDAO.draftRecovery(srchVO);
		}

		@Override
		public void draftRevice(DraftVO srchVO) {
			draftDAO.draftRevice(srchVO);
		}

		@Override
		public byte[] fileDownload(DraftVO srchVO, HttpServletResponse response) {
			File file = new File("C:/Users/Skysoft_D001/Desktop/saveFile/"+srchVO.getRealFileNm());

			byte[] bytes = null;
			String fn = null;
			try {
				  bytes = FileCopyUtils.copyToByteArray(file);
				  // http에 한글은 적용할 수없기때문에 영문으로 인코딩하여 적용
				  fn = new String(srchVO.getRealFileNm().getBytes(), "iso_8859_1");
			} catch (IOException e) {
				e.printStackTrace();
			}

			response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
	        response.setContentLength(bytes.length);
			return bytes;
		}

		@Override
		public void draftFileUpdate(DraftVO srchVO) throws Exception {
			if(srchVO.getFile().getSize()>0){
				srchVO = fileWrite(srchVO);
			}
			draftDAO.draftFileUpdate(srchVO);
		}

		@Override
		public int draftCount(DraftVO srchVO) {
			return draftDAO.draftCount(srchVO);
		}

}
