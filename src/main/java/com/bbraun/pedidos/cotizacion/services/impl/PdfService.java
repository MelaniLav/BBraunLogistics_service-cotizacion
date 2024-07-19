package com.bbraun.pedidos.cotizacion.services.impl;


import com.bbraun.pedidos.cotizacion.models.dto.CotizacionDtoPDF;
import com.bbraun.pedidos.cotizacion.models.dto.DetalleDtoPDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PdfService {

    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);

    @Autowired
    private ResourceLoader resourceLoader;
   public byte[] generatePdfFromTemplate(CotizacionDtoPDF cotizacion, String templatePath) throws IOException {
       ByteArrayOutputStream baos = new ByteArrayOutputStream();

       Resource resource = resourceLoader.getResource("classpath:" + templatePath);
       InputStream inputStream = resource.getInputStream();
       PDDocument document = PDDocument.load(inputStream);
       PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

       if (acroForm == null) {
           logger.error("El documento PDF no contiene un formulario AcroForm");
           throw new IOException("El documento PDF no contiene un formulario AcroForm");
       }

       SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
       String fechaFormateada = sdf.format(cotizacion.getFecha_emision());


       fillField(acroForm, "nombrecliente", cotizacion.getNombrecliente());
       fillField(acroForm, "dnicliente", cotizacion.getDnicliente());
       fillField(acroForm, "fechaemision",fechaFormateada );
       fillField(acroForm, "departamento", cotizacion.getDepartamento());
       fillField(acroForm, "montototal", Math.round(cotizacion.getMontototal()) + ".00");
       fillField(acroForm, "impuestos", Math.round(cotizacion.getImpuestos()) + ".00");
       fillField(acroForm, "total", Math.round(cotizacion.getTotal()) + ".00");
       float xProducto = 70;
       float xCantidad = 240;
       float xPrecioUnitario = 350;
       float xSubtotal = 485;
       float yStart = 420; // Ajusta esta posición según tu diseño
       float yOffset = 20;
       PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0), PDPageContentStream.AppendMode.APPEND, true,true);
       contentStream.setFont(PDType1Font.HELVETICA, 13);

       for (int i=0; i<cotizacion.getDetalles().size(); i++){
           DetalleDtoPDF detalle = cotizacion.getDetalles().get(i);
           float yPosition = yStart - (i * yOffset);

           contentStream.beginText();
           contentStream.newLineAtOffset(xProducto, yPosition);
           contentStream.showText(detalle.getProducto());
           contentStream.endText();

           contentStream.beginText();
           contentStream.newLineAtOffset(xCantidad, yPosition);
           contentStream.showText(detalle.getCantidad().toString());
           contentStream.endText();

           contentStream.beginText();
           contentStream.newLineAtOffset(xPrecioUnitario, yPosition);
           contentStream.showText( detalle.getPreciounitario().toString());
           contentStream.endText();


           contentStream.beginText();
           contentStream.newLineAtOffset(xSubtotal, yPosition);
           contentStream.showText(Math.round(detalle.getSubtotal()) + ".00");
           contentStream.endText();
       }

       contentStream.close();

       acroForm.flatten();

       document.save(baos);
       document.close();

       return baos.toByteArray();

   }


   private void fillField(PDAcroForm acroForm, String fieldName, String value) throws IOException {
       PDField field = acroForm.getField(fieldName);
       if(field != null){
           field.setValue(value);
       }else {
           logger.warn("No se encontró el campo de formulario: " + fieldName);
       }
   }
}
