package egovframework.dev.board.service.impl;

import java.util.*;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import egovframework.dev.board.dao.BoardDAO;
import egovframework.dev.board.service.BoardService;
import egovframework.dev.board.vo.BoardVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("boardService")
public class BoardServiceImpl extends AbstractServiceImpl implements BoardService {

	@Resource(name="boardDAO")
	private BoardDAO boardDAO;

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	@Override
	public Map<String,Object> retrieveTestList(BoardVO boardVO) {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageNo());
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(3);

		int firstIndex = paginationInfo.getFirstRecordIndex()+1;
		int lastIndex = paginationInfo.getLastRecordIndex();

		boardVO.setFirstIndex(firstIndex);
		boardVO.setLastIndex(lastIndex);

		Map<String,Object> map = new HashMap<String,Object>();
		List<BoardVO> list = null;

		String boardType = boardVO.getBoardType();

		if(boardType=="" || boardType==null){
			list = boardDAO.selectTestList(boardVO);
		}else {
			list = boardDAO.selectTypeList(boardVO);
		}

		if(list.size()<=0){
			list=null;
		}
		map.put("list", list);

		if(list!=null){
			paginationInfo.setTotalRecordCount(list.get(0).getTtcnt());
		}
		map.put("paginationInfo", paginationInfo);

		return map;
	}

	@Override
	public void createBoard(BoardVO boardVO) {
		boardDAO.createBoard(boardVO);
	}

	@Override
	public BoardVO selectByPk(BoardVO boardVO) {
		return boardDAO.selectByPk(boardVO);
	}

	@Override
	public void updateByPk(BoardVO boardVO) {
		boardDAO.updateByPk(boardVO);
	}

	@Override
	public void deleteByPk(BoardVO boardVO) {
		boardDAO.deleteByPk(boardVO);
	}

	@Override
	public void deleteArrayByPk(BoardVO boardVO) {
		for(int i=0; i < boardVO.getSeqArray().length; i++){
			boardVO.setSeq(boardVO.getSeqArray()[i]);
			boardDAO.deleteByPk(boardVO);
		}
	}

	@Override
	public Map<String, Object> searchList(BoardVO boardVO) {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageNo());
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(3);

		int firstIndex = paginationInfo.getFirstRecordIndex()+1;
		int lastIndex = paginationInfo.getLastRecordIndex();

		boardVO.setFirstIndex(firstIndex);
		boardVO.setLastIndex(lastIndex);

		Map<String,Object> map = new HashMap<String,Object>();
		List<BoardVO> list = null;

		String boardType = boardVO.getBoardType();

		String title = boardVO.getTitle();
		String contents = boardVO.getContents();

		if(boardType==null){
			if(title==null && contents ==null){
				list = boardDAO.selectTestList(boardVO);
			}else if(title!=null && contents !=null){
				list = boardDAO.selectTCList(boardVO);
			}else if(title!=null && contents==null){
				list = boardDAO.selectTList(boardVO);
			}else if(contents!=null && title==null){
				list = boardDAO.selectCList(boardVO);
			}
		}else {
			if(title==null && contents ==null){
				list = boardDAO.selectTypeList(boardVO);
			}else if(title!=null && contents !=null){
				list = boardDAO.selectTCTypeList(boardVO);
			}else if(title!=null && contents==null){
				list = boardDAO.selectTTypeList(boardVO);
			}else if(contents!=null && title==null){
				list = boardDAO.selectCTypeList(boardVO);
			}
		}

		if(list.size()<=0){
			list=null;
		}
		map.put("list", list);

		if(list!=null){
			paginationInfo.setTotalRecordCount(list.get(0).getTtcnt());
		}
		map.put("paginationInfo", paginationInfo);

		return map;
	}

}
