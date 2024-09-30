package devscaling.coin.appy_state.controller;

// ApplyState, ApplyStateService 참조
import devscaling.coin.appy_state.model.ApplyState;
import devscaling.coin.appy_state.service.ApplyStateService;

// Spring의 웹 관련 애너테이션(주로 RESTful API)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// payload
import java.util.Map;

@RestController
@RequestMapping("/api/applyState")
public class ApplyStateController {
    private final ApplyStateService applyStateService;

    public ApplyStateController(ApplyStateService applyStateService){
        this.applyStateService = applyStateService;
    }

    // 신청 API
    @PostMapping("/request")
    public ResponseEntity<String> request(@RequestBody Map<String, Object> payload){
        applyStateService.request(payload);
        return ResponseEntity.ok("신청 성공");
    }

    // 결제완료 API
    @PostMapping("/complete/{id}")
    public ResponseEntity<String> complete(@PathVariable int id){
        applyStateService.complete(id);
        return ResponseEntity.ok("완료");
    }
}
