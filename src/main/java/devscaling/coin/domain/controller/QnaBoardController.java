package devscaling.coin.domain.controller;

import devscaling.coin.domain.dto.QnaBoardRequestDto;
import devscaling.coin.domain.entity.QnaBoard;
import devscaling.coin.domain.service.QnaBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna/board")
public class QnaBoardController {

    private final QnaBoardService qnaBoardService;

    /**
     * 생성폼 조회
     */
    @GetMapping("/create")
    public String createQnaBoard(){
        return "";
    }

    /**
     * 생성폼 제출
     */
    @PostMapping("/create")
    public String createQnaBoard(@ModelAttribute("qnaBoard") QnaBoardRequestDto qnaBoardRequestDto){
        qnaBoardService.createQnaBoard(qnaBoardRequestDto);

        return "";
    }

    /**
     * 게시글 모두 조회
     * 이후 pageable 활용 예정
     */
    @GetMapping("/")
    public String getBoards(Model model){
        List<QnaBoard> qnaBoardList = qnaBoardService.getQnaBoards();
        model.addAttribute("qnaBoardList", qnaBoardList);
        return "";
    }

    /**
     * 단일 게시글 조회
     */
    @GetMapping("/{qnaBoardId}")
    public String getBoard(@PathVariable("qnaBoardId") Long id, Model model){
        model.addAttribute("qnaBoard",qnaBoardService.getQnaBoard(id));
        return "";
    }

    /**
     * 단일 게시글 수정 폼 조회
     */
    @GetMapping("/{qnaBoardId}/update")
    public String updateQnaBoard(@PathVariable("qnaBoardId") Long id,
                                 Model model){

        model.addAttribute("qnaBoard", qnaBoardService.getQnaBoard(id));
        return "";
    }

    /**
     * 단일 게시글 수정 요청
     */
    @PostMapping("/{qnaBoardId}/update")
    public String updateQnaBoard(@PathVariable("qnaBoardId") Long id,
                                 @ModelAttribute("qnaBoard") QnaBoard qnaBoard){
        qnaBoardService.updateQnaBoard(id, qnaBoard);
        return "redirect:/qna/board/"+ id + "/update";
    }


    /**
     * 단일 게시글 삭제
     */
    @PostMapping("/{qnaBoardId}/delete")
    public String deleteQnaBoard(@PathVariable("qnaBoardId") Long id){
        qnaBoardService.deleteQnaBoard(id);
        return "redirect:/qna/board/";
    }

}
