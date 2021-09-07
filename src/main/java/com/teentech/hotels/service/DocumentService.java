package com.teentech.hotels.service;


import com.teentech.hotels.dto.ReservationSignatureDto;
import com.teentech.hotels.model.Hotel;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

@Service
public class DocumentService {

    public ByteArrayOutputStream updateTemplateDoc(ReservationSignatureDto reservation, Hotel hotel)
            throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        String sourceData = reservation.getSignature();

        String[] parts = sourceData.split(",");
        String imageString = parts[1];

        byte[] imageByte;

        imageByte = Base64.getDecoder().decode(imageString);
        InputStream is = new ByteArrayInputStream(imageByte);

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

            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "new", Units.toEMU(72*6), Units.toEMU(72*6/26*9));

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                doc.write(out);
                return out;
            }
        }
    }
}
