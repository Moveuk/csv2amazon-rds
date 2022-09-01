package com.yournews.newsdatasetting;

import com.yournews.newsdatasetting.model.Division;
import com.yournews.newsdatasetting.model.Group;
import com.yournews.newsdatasetting.model.News;
import com.yournews.newsdatasetting.model.Section;
import com.yournews.newsdatasetting.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExcelDataController {

    private final NewsRepository newsRepository;
    private final SectionRepository sectionRepository;
    private final DivisionRepository divisionRepository;
    private final GroupRepository groupRepository;
    public static final String FILE_PATH = "C:\\Users\\ldu\\Downloads\\NewsResult_20220101-20220131 (1).xlsx";


    @GetMapping("/update")
    public void read() throws IOException, InvalidFormatException { // 2

        // 엑셀 파일 불러오기
        OPCPackage opcPackage = OPCPackage.open(new File(FILE_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        String sheetName = workbook.getSheetName(0);
        Sheet sheet = workbook.getSheet(sheetName);

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next(); //첫 줄 넘김

        long before = System.currentTimeMillis();
        System.out.println("현재 시간 : " + before);
        // 50배수로 넣을때마다 시간 측정
        Map<Integer, Long> timeList = new TreeMap<>(Comparator.comparingInt(i -> i));

        int indexNum = 1;

        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();

            //기고시간 세팅 : 01100201.20220131221144001 중 20220131221144001 만 떼옴
            String split = next.getCell(0).getStringCellValue().split("\\.")[1];

            // int[] date = {2022, 01, 31, 22, 11, 44} 년 월 일 시 분 초
            int year = Integer.parseInt(split.substring(0, 4));
            int month = Integer.parseInt(split.substring(4, 6));
            int dayOfMonth = Integer.parseInt(split.substring(6, 8));
            int hour = Integer.parseInt(split.substring(8, 10));
            int minute = Integer.parseInt(split.substring(10, 12));
            int second = Integer.parseInt(split.substring(12, 14));

            // News DB 저장
            News newsNoNormalEntity = News.builder()
                    .date(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second))
                    .press(next.getCell(2).getStringCellValue())
                    .author(next.getCell(3).getStringCellValue())
                    .title(next.getCell(4).getStringCellValue())
                    .person(next.getCell(11).getStringCellValue())
                    .location(next.getCell(12).getStringCellValue())
                    .organization(next.getCell(13).getStringCellValue())
                    .keyword(next.getCell(14).getStringCellValue())
                    .contents(next.getCell(16).getStringCellValue())
                    .url(next.getCell(17).getStringCellValue())
                    .build();

            newsRepository.save(newsNoNormalEntity);

            //통합 분류1,2,3 칼럼 정제

            List<Cell> unifiedClassificationList = new ArrayList<>();

            for (int i = 5; i < 8; i++) {
                if (Objects.nonNull(next.getCell(i))) {
                    unifiedClassificationList.add(next.getCell(i));
                }
            }

            //unifiedClassificationList null check완료
            //3 개 통합분류 중 있는 것만 추려짐

            for (Cell cell : unifiedClassificationList) {

                String[] classificationSplit = cell.getStringCellValue().trim().split(">");

                // 미분류 : classificationSplit.length = 1
                // 사회>날씨 : classificationSplit.length = 2
                // 스포츠>야구>한국프로야구 : classificationSplit.length = 3

                // foreach 문을 통해 null check 되었고 무조건 대분류는 존재하므로 바로 저장
                String sectionName = classificationSplit[0];
                Section sectionEntity = Section.builder()
                        .news(newsNoNormalEntity)
                        .sectionName(sectionName)
                        .build();

                sectionRepository.save(sectionEntity);

                if(classificationSplit.length >= 2) {
                    //length 2이상은 중분류있으므로 division 저장 실행

                    String divisionName = classificationSplit[1];
                    Division divisionEntity = Division.builder()
                            .section(sectionEntity)
                            .divisionName(divisionName)
                            .build();

                    divisionRepository.save(divisionEntity);

                    if (classificationSplit.length >= 3) {
                        //length 2이상은 소분류있으므로 group 저장 실행

                        String groupName = classificationSplit[2];
                        Group groupEntity = Group.builder()
                                .division(divisionEntity)
                                .groupName(groupName)
                                .build();

                        groupRepository.save(groupEntity);
                    }

                }

            }


            System.out.println(indexNum++);

            if (indexNum % 50 == 0) {
                timeList.put(indexNum, System.currentTimeMillis());
            }

            if (indexNum == 101) {
                break;
            }
        }

        workbook.close();
        opcPackage.close();

        System.out.println(" db 저장 완료");

        long after = System.currentTimeMillis();
        System.out.println("종료 후 현재 시간 : " + after);
        System.out.println();


        timeList.keySet().forEach(key -> {
            System.out.println("총 " + key + "개 넣는 시간 : " + (timeList.get(key) - before) + "ms");
        });
    }
}