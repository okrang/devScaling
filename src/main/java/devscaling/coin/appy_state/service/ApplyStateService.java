package devscaling.coin.appy_state.service;

// ApplyState, ApplyStateRepository 참조
import devscaling.coin.appy_state.model.ApplyState;
import devscaling.coin.appy_state.repository.ApplyStateRepository;

import devscaling.coin.member.model.Member;
import devscaling.coin.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// payload
import java.util.Map;

@Service
public class ApplyStateService {
    private final ApplyStateRepository applyStateRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ApplyStateService(ApplyStateRepository applyStateRepository, MemberRepository memberRepository){
        this.applyStateRepository = applyStateRepository;
        this.memberRepository = memberRepository;
    }

    // 신청 메소드
    public void request(Map<String, Object> payload){
        int memberId = (int)payload.get("memberId");
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        ApplyState applyState = new ApplyState();
        applyState.setSelectTeam((String)payload.get("selectTeam"));
        applyState.setApplyMonth((String)payload.get("applyMonth"));
        applyState.setMemberId(member);
        applyStateRepository.save(applyState);
    }

    // 완료 메소드
    public void complete(int id){
        ApplyState applyState = applyStateRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ApplyState not found"));
        applyState.setPayConfirm(true);
        applyStateRepository.save(applyState);
    }
}
