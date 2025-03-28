package com.shabet.ensthistory.api.admin.upload;

import com.shabet.ensthistory.domain.unit.Unit;
import com.shabet.ensthistory.domain.unit.UnitRepository;
import com.shabet.ensthistory.domain.unit.dto.UnitCsvDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/upload")
@CrossOrigin(origins = "http://localhost:5173") // ← Vite dev server 포트!
public class CsvUploadController {

    private final UnitRepository unitRepository;

    public CsvUploadController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @PostMapping("/unit")
    public ResponseEntity<String> uploadUnitCsv(@RequestParam("csvFile")MultipartFile file) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;

            // 1행: 헤더 컬럼명 → 인덱스 매핑
            Map<String, Integer> headerMap = new HashMap<String, Integer>();
            if ((line = reader.readLine()) != null) {
                String[] headers = line.split(",", -1);
                for (int i = 0; i < headers.length; i++) {
                    headerMap.put(headers[i].trim(), i);
                }
            }

            // 2행: 설명 행 (스킵)
            reader.readLine();

            // 데이터 행 파싱
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1); // 빈 칸도 유지

                UnitCsvDto dto = new UnitCsvDto();
                dto.setUnitName(tokens[headerMap.get("unit_name")]);
                dto.setUnitAbbr(tokens[headerMap.get("unit_abbr")]);
                dto.setUnitType(tokens[headerMap.get("unit_type")]);
                dto.setUnitOrder(Integer.parseInt(tokens[headerMap.get("unit_order")]));
                dto.setUnitAgency(tokens[headerMap.get("unit_agency")]);
                dto.setUnitKorName(tokens[headerMap.get("unit_kor_name")]);
                dto.setUnitEnSearch(tokens[headerMap.get("unit_en_search")]);
                if (headerMap.containsKey("unit_jp_search")) { // 없을 수도 있는 거
                    dto.setUnitJpSearch(tokens[headerMap.get("unit_jp_search")]);
                }
                dto.setUnitKorSearch(tokens[headerMap.get("unit_kor_search")]);

                Unit unit = new Unit(dto);
                unitRepository.save(unit);
            }

            return ResponseEntity.ok("업로드 성공~");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("업로드 실패... " + e.getMessage());
        }
    }
}
