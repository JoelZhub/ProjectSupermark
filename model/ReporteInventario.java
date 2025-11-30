package model;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReporteInventario extends Reportes<RegistroInventario>{

    public ReporteInventario(List<RegistroInventario> productos) {
        super(productos);
        this.titulo = "Reporte de Inventario";
    }

    @Override
    public void exportarPDF(String rutaArchivo) throws DocumentException {

        Document document = new Document();

        try {
            PdfWriter writer = prepararDocumento(document, rutaArchivo);

            agregarHeaderDocumento(document, writer);

            String[] encabezados = {"Producto", "Unidad", "Precio unitario", "En stock", "Valor total"};
            PdfPTable table = crearTabla(encabezados);

            for (RegistroInventario r : registros) {
                agregarCeldaTexto(table, r.getNombre(), Element.ALIGN_LEFT);
                agregarCeldaTexto(table, r.getUnidad(), Element.ALIGN_LEFT);
                agregarCeldaTexto(table, String.format("%,.2f", r.getPrecio()), Element.ALIGN_RIGHT);
                agregarCeldaTexto(table, String.valueOf(r.getCantidad()), Element.ALIGN_CENTER);
                agregarCeldaTexto(table, String.format("%,.2f", r.getValorTotal()), Element.ALIGN_RIGHT);
            }

            document.add(table);

        } catch (Exception e) {
            System.out.println("Error generando PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
