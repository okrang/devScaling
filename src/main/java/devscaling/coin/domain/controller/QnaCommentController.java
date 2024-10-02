package devscaling.coin.domain.controller;


import devscaling.coin.domain.dto.QnaCommentRequestDto;
import devscaling.coin.domain.entity.QnaComment;
import devscaling.coin.domain.service.QnaCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QnaCommentController {


    private final QnaCommentService qnaCommentService;

    /**
     * 댓글 생성폼 조회
     */
    @GetMapping("/qna/{qnaBoardId}/comment/create")
    public String createQnaBoard(@PathVariable("qnaBoardId") Long boardId){
        return "";
    }

    /**
     * 댓글 생성폼 제출
     */
    @PostMapping("/qna/{qnaBoardId}/comment/create")
    public String createQnaBoard(@PathVariable("qnaBoardId") Long boardId,
                                 @ModelAttribute("qnaComment") QnaCommentRequestDto qnaCommentRequestDto){
        qnaCommentService.createQnaComment(boardId, qnaCommentRequestDto);

        return "";
    }

    /**
     * 댓글 모두 조회
     * 이후 pageable 활용 예정
     */
    @GetMapping("/qna/{qnaBoardId}/comment")
    public String getBoards(@PathVariable("qnaBoardId") Long boardId, Model model){
        List<QnaComment> qnaComments = qnaCommentService.getQnaComments(boardId);
        model.addAttribute("qnaComments", qnaComments);
        return "";
    }

    /**
     * 단일 댓글 조회
     */
    @GetMapping("/qna/{qnaBoardId}/comment/{qnaCommentId}")
    public String getBoard(@PathVariable("qnaBoardId") Long boardId, Long qnaCommentId,
                           Model model){
        model.addAttribute("qnaComment",qnaCommentService.getQnaComment(boardId, qnaCommentId));
        return "";
    }

    /**
     * 단일 댓글 수정 폼 조회
     */
    @GetMapping("/qna/{qnaBoardId}/comment/{qnaCommentId}/update")
    public String updateQnaBoard(@PathVariable("qnaBoardId") Long boardId, Long qnaCommentId,
                                 Model model){
        model.addAttribute("qnaComment",qnaCommentService.getQnaComment(boardId, qnaCommentId));
        return "";
    }

    /**
     * 단일 댓글 수정 요청
     */
    @PostMapping("/qna/{qnaBoardId}/comment/{qnaCommentId}/update")
    public String updateQnaBoard(@PathVariable("qnaBoardId") Long boardId, Long qnaCommentId,
                                 @ModelAttribute("qnaComment") QnaComment QnaComment){
        qnaCommentService.updateQnaComment(boardId, qnaCommentId, QnaComment);
        return "redirect:/qna/" + boardId + "/comment/" + qnaCommentId + "/update";
    }


    /**
     * 단일 댓글 삭제
     */
    @PostMapping("/qna/{qnaBoardId}/comment/{qnaCommentId}/delete")
    public String deleteQnaBoard(@PathVariable("qnaBoardId") Long boardId, Long qnaCommentId){
        qnaCommentService.deleteQnaComment(boardId, qnaCommentId);
        return "redirect:/qna/" + boardId;
    }

}
