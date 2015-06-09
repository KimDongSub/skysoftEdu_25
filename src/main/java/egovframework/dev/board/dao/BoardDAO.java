package egovframework.dev.board.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import egovframework.dev.board.vo.BoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	@SuppressWarnings("unchecked")
	public List<BoardVO> selectTestList(BoardVO boardVO) {
		return list("boardDAO.selectTestList", boardVO);
	}

	public void createBoard(BoardVO boardVO) {
		insert("boardDAO.createBoard", boardVO);
	}

	public BoardVO selectByPk(BoardVO boardVO) {
		return (BoardVO) selectByPk("boardDAO.selectByPk", boardVO);
	}

	public void updateByPk(BoardVO boardVO) {
		update("boardDAO.updateByPk", boardVO);
	}

	public void deleteByPk(BoardVO boardVO) {
		delete("boardDAO.deleteByPk", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectTypeList(BoardVO boardVO) {
		return list("boardDAO.selectTypeList", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectTCList(BoardVO boardVO) {
		return list("boardDAO.selectTCList", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectTList(BoardVO boardVO) {
		return list("boardDAO.selectTList", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectCList(BoardVO boardVO) {
		return list("boardDAO.selectCList", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectTCTypeList(BoardVO boardVO) {
		return list("boardDAO.selectTCTypeList", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectTTypeList(BoardVO boardVO) {
		return list("boardDAO.selectTTypeList", boardVO);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVO> selectCTypeList(BoardVO boardVO) {
		return list("boardDAO.selectCTypeList", boardVO);
	}

}
