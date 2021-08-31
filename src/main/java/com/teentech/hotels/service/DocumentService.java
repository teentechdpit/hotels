package com.teentech.hotels.service;


import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.model.Hotel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@Service
public class DocumentService {

    public void updateTemplateDoc(String input, String output, ReservationDto reservation, Hotel hotel)
            throws IOException {

        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(input)));) {
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();

            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);

                    if (docText != null) {
                        long millis = System.currentTimeMillis();
                        Date todayDate = new Date(millis); //example: 2021-08-27
                        docText = docText.replace("${todayDate}", todayDate.toString());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${name}", reservation.getName());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${surname}", reservation.getSurname());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${email}", reservation.getEmail());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${startDate}", reservation.getStartDate().toString());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${endDate}", reservation.getEndDate().toString());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${hotelName}", hotel.getName());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${hotelCity}", hotel.getCity());
                        xwpfRun.setText(docText, 0);

                        docText = docText.replace("${hotelCountry}", hotel.getCountry());
                        xwpfRun.setText(docText, 0);
                    }
                }
            }

            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);
            }
        }
    }
}
