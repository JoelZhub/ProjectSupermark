package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;

import java.util.List;

public abstract class Reportes<Clase>{

    protected String titulo;
    protected String fecha;
    protected List<Clase> registros;

    public Reportes(List<Clase> registros, String fecha) {
        this.registros = registros;
        this.fecha = fecha;
    }

    public Reportes(List<Clase> registros) {
        this.registros = registros;
        this.fecha = LocalDate.now().toString();
    }

    public abstract void exportarPDF(String rutaArchivo)
        throws IOException, DocumentException;

    protected void agregarHeaderDocumento(Document document, PdfWriter writer) throws DocumentException, IOException {

        String rutaLogo = "resource\\img\\logo.png";

        PdfPTable header = new PdfPTable(3);
        header.setWidthPercentage(100);
        header.setWidths(new int[]{1, 4, 1});
        header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        try {
            Image logo = Image.getInstance(rutaLogo);
            logo.scaleToFit(60, 60);

            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            header.addCell(logoCell);
        } catch (Exception e) {
            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            emptyCell.setBorder(Rectangle.NO_BORDER);
            header.addCell(emptyCell);
        }

        Font nombreFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        PdfPCell nombreCell = new PdfPCell(new Phrase("SUPERMARKET", nombreFont));
        nombreCell.setBorder(Rectangle.NO_BORDER);
        nombreCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nombreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        header.addCell(nombreCell);

        PdfPCell fecha = new PdfPCell(new Phrase(this.fecha));
        fecha.setHorizontalAlignment(Element.ALIGN_LEFT);
        fecha.setBorder(Rectangle.NO_BORDER);
        header.addCell(fecha);

        document.add(header);

        LineSeparator ls = new LineSeparator();
        ls.setLineWidth(1f);
        document.add(new Chunk(ls));

        document.add(Chunk.NEWLINE);

        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
        Paragraph titulo = new Paragraph(this.titulo, tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);
    }

    protected void agregarHeaderTabla(PdfPTable table, String[] encabezados){
        for(String e : encabezados){
            PdfPCell encabezado = new PdfPCell(new Phrase(e));
            encabezado.setBackgroundColor(new BaseColor(135, 171, 231));
            table.addCell(encabezado);
        }
    }

    protected PdfWriter prepararDocumento(Document document, String rutaArchivo) throws DocumentException, FileNotFoundException {
        PdfWriter writer = PdfWriter.getInstance(document, new java.io.FileOutputStream(rutaArchivo));
        document.open();
        return writer;
    }

    protected PdfPTable crearTabla(String[] encabezados) {
        PdfPTable table = new PdfPTable(encabezados.length);
        table.setWidthPercentage(100);
        agregarHeaderTabla(table, encabezados);
        return table;
    }

    protected void agregarCeldaTexto(PdfPTable table, String texto, int alineacion) {
        PdfPCell cell = new PdfPCell(new Phrase(texto));
        cell.setHorizontalAlignment(alineacion);
        table.addCell(cell);
    }

}
