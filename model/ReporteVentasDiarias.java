package model;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReporteVentasDiarias extends Reportes<RegistroVentaDiaria>{

    public ReporteVentasDiarias(List<RegistroVentaDiaria> ventas, String fecha) {
        super(ventas, fecha);
        this.titulo = "Reporte de Ventas Diarias";
    }

    @Override
    public void exportarPDF(String rutaArchivo) throws DocumentException {

        Document document = new Document();

        try {
            PdfWriter writer = prepararDocumento(document, rutaArchivo);

            agregarHeaderDocumento(document, writer);

            String[] encabezados = {"Producto", "Precio", "Cantidad", "Total"};
            PdfPTable table = crearTabla(encabezados);

            for (RegistroVentaDiaria r : registros) {
                agregarCeldaTexto(table, r.getNombreProducto(), Element.ALIGN_LEFT);
                agregarCeldaTexto(table, String.format("%,.2f", r.getPrecio()), Element.ALIGN_RIGHT);
                agregarCeldaTexto(table, String.valueOf(r.getCantidadVendida()), Element.ALIGN_CENTER);
                agregarCeldaTexto(table, String.format("%,.2f", r.getTotal()), Element.ALIGN_RIGHT);
            }

            document.add(table);

        } catch (Exception e) {
            System.out.println("Error generando PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}