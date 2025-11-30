package model;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReporteCuentasCobrar extends Reportes<RegistroCuentaCobrar> {

    public ReporteCuentasCobrar(List<RegistroCuentaCobrar> registros) {
        super(registros);
        this.titulo = "Reporte de Cuentas por Cobrar";
    }

    @Override
    public void exportarPDF(String rutaArchivo) throws DocumentException {

        Document document = new Document();

        try {
            PdfWriter writer = prepararDocumento(document, rutaArchivo);

            agregarHeaderDocumento(document, writer);

            String[] encabezados = {"Cliente", "Monto total", "Monto pendiente", "Estado"};
            PdfPTable table = crearTabla(encabezados);

            for (RegistroCuentaCobrar r : registros) {
                agregarCeldaTexto(table, r.getCliente(), Element.ALIGN_LEFT);
                agregarCeldaTexto(table, String.format("%,.2f", r.getMontoTotal()), Element.ALIGN_RIGHT);
                agregarCeldaTexto(table, String.format("%,.2f", r.getMontoPendiente()), Element.ALIGN_RIGHT);
                agregarCeldaTexto(table, r.getEstado(), Element.ALIGN_CENTER);
            }

            document.add(table);

        } catch (Exception e) {
            System.out.println("Error generando PDF de cuentas por cobrar: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}