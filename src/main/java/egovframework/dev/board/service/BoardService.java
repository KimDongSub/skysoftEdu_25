package egovframework.dev.board.service;

import java.util.List;
import java.util.Map;

import egovframework.dev.board.vo.BoardVO;

public interface BoardService {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	public Map<String,Object> retrieveTestList(BoardVO  boardVO );
	public void createBoard(BoardVO boardVO);
	public BoardVO selectByPk(BoardVO boardVO);
	public void updateByPk(BoardVO boardVO);
	public void deleteByPk(BoardVO boardVO);
	public void deleteArrayByPk(BoardVO boardVO);
	public Map<String, Object> searchList(BoardVO boardVO);
}
