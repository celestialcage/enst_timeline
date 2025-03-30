package com.shabet.ensthistory.api.admin.upload;

import com.shabet.ensthistory.service.csvupload.CharacterCsvUploadService;
import com.shabet.ensthistory.service.csvupload.UnitCsvUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/upload")
//@CrossOrigin(origins = "http://localhost:5173") // ← Vite dev server 포트! API 하나하나에 적용 (WebConfig에서 전역설정함)
public class CsvUploadController {

    // final 키워드로 불변성 보장
    private final UnitCsvUploadService unitCsvUploadService;
    private final CharacterCsvUploadService characterCsvUploadService;

    // 생성자 주입. autowired 안 써도 스프링이 주입해줌.
    // autowired는 현업에서 잘 안 쓴다고 한다.
    // Lombok으로 대체 가능(선언에 @RequiredArgsConstructor 쓰고 거기에 final 붙인 필드 있으면 걔만 자동 주입.)
    // 단위테스트에서 명시적으로 주입 가능, 순환 참조(의존) 감지가 컴파일 타임에 감지됨.
    public CsvUploadController(UnitCsvUploadService unitCsvUploadService, CharacterCsvUploadService characterCsvUploadService) {
        this.unitCsvUploadService = unitCsvUploadService;
        this.characterCsvUploadService = characterCsvUploadService;
    }

    @PostMapping("/unit")
    public ResponseEntity<String> uploadUnitCsv(@RequestParam("csvFile")MultipartFile file) {
        try {
            unitCsvUploadService.uploadFromCsv(file); // 전체 트랜잭션 처리
            return ResponseEntity.ok("업로드 성공~");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("업로드 실패... " + e.getMessage());
        }
    }
    @PostMapping("/character")
    public ResponseEntity<String> uploadCharacterCsv(@RequestParam("csvFile")MultipartFile file) {
        try {
            characterCsvUploadService.uploadCharacterWithRelation(file); // 전체 트랜잭션 처리
            return ResponseEntity.ok("업로드 성공~");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("업로드 실패... " + e.getMessage());
        }
    }
}
