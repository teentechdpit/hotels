package com.teentech.hotels.service;


import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.model.Hotel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service
public class DocumentService {

    public ByteArrayOutputStream updateTemplateDoc(ReservationDto reservation, Hotel hotel)
            throws IOException {
        File file = new ClassPathResource("\\templates\\ReservationTemplate.docx").getFile();
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(file))) {
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();

            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);

                    if (docText != null) {
                        long millis = System.currentTimeMillis();
                        Date todayDate = new Date(millis);
                        docText = docText.replace("${todayDate}", todayDate.toString());
                        docText = docText.replace("${name}", reservation.getName());
                        docText = docText.replace("${surname}", reservation.getSurname());
                        docText = docText.replace("${email}", reservation.getEmail());
                        docText = docText.replace("${startDate}", reservation.getStartDate().toString());
                        docText = docText.replace("${endDate}", reservation.getEndDate().toString());
                        docText = docText.replace("${hotelName}", hotel.getName());
                        docText = docText.replace("${hotelCity}", hotel.getCity());
                        docText = docText.replace("${hotelCountry}", hotel.getCountry());
                        xwpfRun.setText(docText, 0);
                    }
                }
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                doc.write(out);
                return out;
            }
        }
    }
}
