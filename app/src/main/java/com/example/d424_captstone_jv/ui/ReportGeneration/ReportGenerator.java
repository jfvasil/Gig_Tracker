package com.example.d424_captstone_jv.ui.ReportGeneration;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.d424_captstone_jv.Entities.Gig;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ReportGenerator {
    public static void generateGigReport(Context context, List<Gig> gigs, String startDate, String endDate) {
        try {
            String fileName = "Gig_Report.pdf";
            File file;
            OutputStream outputStream;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                ContentResolver resolver = context.getContentResolver();
                ContentValues values = new ContentValues();
                values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
                values.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");
                values.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

                Uri uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                 outputStream = resolver.openOutputStream(uri);



            } else {

                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
                outputStream = new FileOutputStream(file);
            }

            if(outputStream != null) {
                PdfWriter writer = new PdfWriter(outputStream);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                document.add(new Paragraph("Gig Payment Report"));
                document.add(new Paragraph("-------------------------------"));
                document.add(new Paragraph("From: " + startDate + " To: " + endDate));

                for (Gig gig : gigs) {
                    document.add(new Paragraph("Venue: " + gig.getVenue()));
                    document.add(new Paragraph("Date: " + gig.getDate()));
                    document.add(new Paragraph("Expected Payment: $" + gig.getExpectedPayment()));
                    document.add(new Paragraph("Actual Payment: $" + gig.getActualPayment()));
                    document.add(new Paragraph("-------------------------------"));
                }

                document.close();
                outputStream.close();
                Toast.makeText(context, "Report saved to Downloads", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to save report", Toast.LENGTH_SHORT).show();
        }
    }

}


