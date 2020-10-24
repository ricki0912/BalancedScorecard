package balancedScorecard.metas;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import balancedScorecard.indicadores.Indicadores_controler;
import balancedScorecard.indicadores.TipoIndicador;
import balancedScorecard.indicadores.TipoMedicion;
import funciones.ColoresPersonalizados;
import funciones.Conexion;
import funciones.Funciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Meta_controler implements Initializable {
	
	
	@FXML
    private ProgressBar jfxProgressBarLineaTiempo;

    @FXML
    private Label labelFechaInicial;

    @FXML
    private Label labelFechaFinal;

    @FXML
    private Label labelValorActualIncial;
    
    @FXML
    private Label labelFechaMeta;
	
	
    @FXML
    private Label labelValorActual;
    

	private int tipo_indicador = -1;

	public int getTipo_indicador() {
		return tipo_indicador;
	}

	public void setTipo_indicador(int tipo_indicador) {
		this.tipo_indicador = tipo_indicador;
		mostrarTipoIndicador();
	
	}

	
	public void mostrarTipoIndicador() {
		if (Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD == tipo_indicador) {
			jFXTextFieldVarCantidadNumeroNum.setVisible(true);
			jFXTextFieldVarCantidadNumeroNum.setText("");
			jFXTextFieldVarCantidadEspecificaPor.setVisible(false);
			lineDivision.setVisible(false);
			jFXTextFieldVarCantidadTotalPor.setVisible(false);
		} else if (Indicadores_controler.TIPO_INDICADOR_PORCENTAJE == tipo_indicador) {
			jFXTextFieldVarCantidadNumeroNum.setVisible(false);
			jFXTextFieldVarCantidadEspecificaPor.setVisible(true);
			jFXTextFieldVarCantidadTotalPor.setVisible(true);
			lineDivision.setVisible(true);
			jFXTextFieldVarCantidadEspecificaPor.setText("");
			jFXTextFieldVarCantidadTotalPor.setText("");
		}
		jFXDatePickerFechaLogro.setValue(LocalDate.now());
		
	}

	private int id_indicador = -1;

	public int getId_indicador() {
		return id_indicador;
	}

	public void setId_indicador(int id_indicador) {
		this.id_indicador = id_indicador;
		mostrarDatos();
		mostrarSeleccionarDatosIndicador();
	}

	@FXML
	private JFXTextField jfxTextFieldFormula;

	@FXML
	private Arc ArcMetaAzul;

	@FXML
	private Arc ArcMetaVerde;

	@FXML
	private Arc ArcMetaPrecaucion;

	@FXML
	private Arc ArcMetaPeligro;

	@FXML
	private Label labelLogroAlcanzado;

	@FXML
	private Arc ArcFlechaIndicador;

	@FXML
	private Label labelValorMaximo;

	@FXML
	private Label labelValorMinimoPeligro;

	@FXML
	private JFXTextField jFXTextFieldVarCantidadEspecificaPor;

	@FXML
	private JFXTextField jFXTextFieldVarCantidadTotalPor;

	@FXML
	private Line lineDivision;

	@FXML
	private JFXTextField jFXTextFieldVarCantidadNumeroNum;

	@FXML
	private JFXDatePicker jFXDatePickerFechaLogro;

	@FXML
	private Label labelInformacion;

	@FXML
	private TableView<LogrosAlcanzados> tableViewLogrosPorPeriodo;

	@FXML
	private TableColumn<LogrosAlcanzados, String> tableColumnNro;

	@FXML
	private TableColumn<LogrosAlcanzados, String> tableColumnFecha;

	@FXML
	private TableColumn<LogrosAlcanzados, String> tableColumnLogroAlcanzado;

	@FXML
	private TableColumn<LogrosAlcanzados, String> tableColumnLogroAlcanzadoPeriodo;

	@FXML
	private TableColumn<LogrosAlcanzados, String> tableColumnLogroAlcanzadoPeriodoFraccion;
	@FXML
	private TableColumn<LogrosAlcanzados, String> tableColumnOpciones;

	@FXML
	private LineChart<String, Number> lineChartLoggrosAlcanzados;
	private XYChart.Series xYChartSeriesMetaEstablecidad = new XYChart.Series();
	private XYChart.Series xYChartSeriesPeligro = new XYChart.Series();
	private XYChart.Series xYChartSeriesPrecaucion = new XYChart.Series();
	private XYChart.Series xYChartSeriesMetaLogradaPeriodo = new XYChart.Series();
	private XYChart.Series xYChartSeriesMetaLogradaAcumulativa = new XYChart.Series();

	@FXML
	private Button buttonGuardar;

	@FXML
	private Button buttonCancelar;

	@FXML
	private JFXButton buttonClose;

	private ObservableList<LogrosAlcanzados> arrayTableView = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarTableViewMetas();
		

		buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				insertarDatos();
				mostrarDatos();
			}
		});

		buttonClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				JFXButton button = (JFXButton) event.getSource();
				Stage stage = (Stage) button.getScene().getWindow();
				stage.close();

			}
		});

		tableViewLogrosPorPeriodo.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mostrarDatosIndicador(tableViewLogrosPorPeriodo.getSelectionModel().getSelectedItem().getId());
			}
		});

	}

	private void inicializarTableViewMetas() {
		tableViewLogrosPorPeriodo.setItems(arrayTableView);
		tableColumnNro.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		tableColumnLogroAlcanzadoPeriodoFraccion
				.setCellValueFactory(new PropertyValueFactory<>("logroAlcanzadoPeriodoFraccion"));

		tableColumnLogroAlcanzadoPeriodo.setCellValueFactory(new PropertyValueFactory<>("logroAlcanzadoPeriodo"));
		tableColumnLogroAlcanzado.setCellValueFactory(new PropertyValueFactory<>("logroAlcanzado"));

		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));

	}

	public void insertarDatos() {
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
		styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));
		styleClass.removeAll(Collections.singleton("labelInformacioncorrecto"));

		String mensaje = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rsset = null;
		try {

			String valor = "campos_vacios";
			if (tipo_indicador == Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD) {
				valor = jFXTextFieldVarCantidadNumeroNum.getText().trim();
			} else if (tipo_indicador == Indicadores_controler.TIPO_INDICADOR_PORCENTAJE) {
				valor = String.valueOf((Double.parseDouble(jFXTextFieldVarCantidadEspecificaPor.getText().trim())
						/ Double.parseDouble(jFXTextFieldVarCantidadTotalPor.getText().trim())));

			}

			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(
					" INSERT INTO META_LOGRADA(VALOR_ACTUAL,TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, DATO_NUMERO_UNO, "
							+ " DATO_PORCENTAJE_UNO, DATO_PORCENTAJE_DOS, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL,"
							+ " META_VERDE_INICIAL, META_VERDE_FINAL, RESPONSABLE, META_LOGRADA, FECHA, ID_INDICADOR ) SELECT VALOR_ACTUAL,TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS,"
							+ " ?,  ?, ?, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL, META_VERDE_INICIAL, META_VERDE_FINAL, RESPONSABLE, "
							+ " ( SELECT IF(COUNT(*)>0,IF(META_ROJO_INICIAL<META_VERDE_FINAL, MAX(META_LOGRADA)+'"
							+ valor + "',MIN(META_LOGRADA)-'" + valor + "'),0) AS META_LOGRADA_ULTIMO   "
							+ " FROM META_LOGRADA WHERE ID_INDICADOR=?) " + ", ?, ? FROM   INDICADOR WHERE ID=?;");

			if (tipo_indicador == Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD) {
				pst.setDouble(1, Double.parseDouble(jFXTextFieldVarCantidadNumeroNum.getText().trim()));
				;
				pst.setNull(2, java.sql.Types.DOUBLE);
				;
				pst.setNull(3, java.sql.Types.DOUBLE);
				;
			} else if (tipo_indicador == Indicadores_controler.TIPO_INDICADOR_PORCENTAJE) {
				pst.setNull(1, java.sql.Types.DOUBLE);
				;
				pst.setDouble(2, Double.parseDouble(jFXTextFieldVarCantidadEspecificaPor.getText().trim()));
				;
				pst.setDouble(3, Double.parseDouble(jFXTextFieldVarCantidadTotalPor.getText().trim()));
				;

				
			}

			pst.setInt(4, id_indicador);
			java.util.Date date = java.util.Date
					.from(jFXDatePickerFechaLogro.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			pst.setDate(5, sqlDate);
			pst.setInt(6, id_indicador);
			pst.setInt(7, id_indicador);

			int rs = pst.executeUpdate();
			if (rs == 1) {
				labelInformacion.setText("Inserción Correcta");
				;

			} else {
				labelInformacion.setText("Error :(...");
				;

			}
			conn.close();
			pst.close();
			if (!styleClass.contains("labelInformacioncorrecto")) {
				styleClass.add("labelInformacioncorrecto");

			}

		} catch (Exception e) {

			if (!styleClass.contains("labelInformacionincorrecto")) {
				styleClass.add("labelInformacionincorrecto");

			}
			labelInformacion.setText("Error :(..." + e.getMessage());
			;
			e.printStackTrace();

		} finally {
			try {
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}
				if (rsset != null) {
					rsset.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public JFXTextField getjFXTextFieldVarCantidadEspecificaPor() {
		return jFXTextFieldVarCantidadEspecificaPor;
	}

	public void setjFXTextFieldVarCantidadEspecificaPor(JFXTextField jFXTextFieldVarCantidadEspecificaPor) {
		this.jFXTextFieldVarCantidadEspecificaPor = jFXTextFieldVarCantidadEspecificaPor;
	}

	public JFXTextField getjFXTextFieldVarCantidadTotalPor() {
		return jFXTextFieldVarCantidadTotalPor;
	}

	public void setjFXTextFieldVarCantidadTotalPor(JFXTextField jFXTextFieldVarCantidadTotalPor) {
		this.jFXTextFieldVarCantidadTotalPor = jFXTextFieldVarCantidadTotalPor;
	}

	public JFXTextField getjFXTextFieldVarCantidadNumeroNum() {
		return jFXTextFieldVarCantidadNumeroNum;
	}

	public void setjFXTextFieldVarCantidadNumeroNum(JFXTextField jFXTextFieldVarCantidadNumeroNum) {
		this.jFXTextFieldVarCantidadNumeroNum = jFXTextFieldVarCantidadNumeroNum;
	}

	
	public void inicializarLineChart(){
		
	}
	
	public void mostrarDatos() {

		tableViewLogrosPorPeriodo.getItems().clear();
		// lineChartLoggrosAlcanzados.getData().clear();
		xYChartSeriesPeligro.getData().clear();
		xYChartSeriesPrecaucion.getData().clear();
		xYChartSeriesMetaEstablecidad.getData().clear();
		xYChartSeriesMetaLogradaAcumulativa.getData().clear();
		xYChartSeriesMetaLogradaPeriodo.getData().clear();

		xYChartSeriesPeligro.setName("Peligro");
		xYChartSeriesPrecaucion.setName("Precaucion");
		xYChartSeriesMetaEstablecidad.setName(" Meta ");
		xYChartSeriesMetaLogradaPeriodo.setName("Logro por Periodo");
		xYChartSeriesMetaLogradaAcumulativa.setName("Logro Acumulativo");

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rsset = null;
		try {

			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(
					"  SELECT ID,  VALOR_ACTUAL,TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, DATO_NUMERO_UNO, "
							+ " DATO_PORCENTAJE_UNO, DATO_PORCENTAJE_DOS, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL,"
							+ " META_VERDE_INICIAL, META_VERDE_FINAL, RESPONSABLE, META_LOGRADA, FECHA, ID_INDICADOR, "
							+ " RETONAR_VALOR_ACUMULADO(?,(SELECT FECHA_INICIO FROM indicador WHERE ID=?), FECHA) AS VALOR_ACUMULATIVO"
							+ " FROM META_LOGRADA WHERE ID_INDICADOR=? ORDER BY FECHA;");

			pst.setInt(1, id_indicador);
			pst.setInt(2, id_indicador);
			pst.setInt(3, id_indicador);
			/*
			 * if(){
			 * 
			 * } pst.setDouble(1,D)); pst.setDouble(1,D));
			 * 
			 * java.util.Date date
			 * =java.util.Date.from(jFXDatePickerFechaMeta.getValue().
			 * atStartOfDay(ZoneId.systemDefault()).toInstant()); java.sql.Date
			 * sqlDate = new java.sql.Date(date.getTime());
			 * pst.setDate(2,sqlDate); pst.setInt(3, idIndicador); pst.setInt(4,
			 * idIndicador);
			 * 
			 * 
			 * 
			 * 
			 * 
			 */

			rsset = pst.executeQuery();
			int contador = 1;
			int id_ultima_meta = -1;
			while (rsset.next()) {

				id_ultima_meta = rsset.getInt("ID");
				int id_meta = rsset.getInt("ID");
				;
				String logroAlcFracc = "";
				String logroAlc = "";
				String valorActualAcumulativo = "";
				if (Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD == tipo_indicador) {
					logroAlcFracc = rsset.getDouble("DATO_NUMERO_UNO") + "";
					logroAlc = rsset.getDouble("DATO_NUMERO_UNO") + " U.";
					valorActualAcumulativo = rsset.getDouble("VALOR_ACUMULATIVO") + " UNID.";
					labelValorActual.setText(rsset.getDouble("VALOR_ACUMULATIVO") + " UNID.");
				} else if (Indicadores_controler.TIPO_INDICADOR_PORCENTAJE == tipo_indicador) {
					valorActualAcumulativo = Funciones
							.decimalReducido((rsset.getDouble("VALOR_ACUMULATIVO") * 100) + "") + "%";
					logroAlcFracc = rsset.getDouble("DATO_PORCENTAJE_UNO") + "/"
							+ rsset.getDouble("DATO_PORCENTAJE_DOS");
					logroAlc = String.valueOf(
							(rsset.getDouble("DATO_PORCENTAJE_UNO") / rsset.getDouble("DATO_PORCENTAJE_DOS")) * 100)
							+ "%";

					
					jFXTextFieldVarCantidadTotalPor.setText(rsset.getString("DATO_PORCENTAJE_DOS"));
					
					labelValorActual.setText(Funciones
							.decimalReducido((rsset.getDouble("VALOR_ACUMULATIVO") * 100) + "") + "%");
				}

				xYChartSeriesMetaEstablecidad.getData().add(
						new XYChart.Data(String.valueOf(rsset.getDate("FECHA")), rsset.getDouble("META_VERDE_FINAL")));

				xYChartSeriesMetaLogradaAcumulativa.getData().add(
						new XYChart.Data(String.valueOf(rsset.getDate("FECHA")), rsset.getDouble("VALOR_ACUMULATIVO")));

				if (Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD == tipo_indicador) {
					xYChartSeriesMetaLogradaPeriodo.getData().add(new XYChart.Data(
							String.valueOf(rsset.getDate("FECHA")), rsset.getDouble("DATO_NUMERO_UNO")));

				} else if (Indicadores_controler.TIPO_INDICADOR_PORCENTAJE == tipo_indicador) {
					xYChartSeriesMetaLogradaPeriodo.getData()
							.add(new XYChart.Data(String.valueOf(rsset.getDate("FECHA")),
									rsset.getDouble("DATO_PORCENTAJE_UNO") / rsset.getDouble("DATO_PORCENTAJE_DOS")));

				}

				xYChartSeriesPeligro.getData().add(
						new XYChart.Data(String.valueOf(rsset.getDate("FECHA")), rsset.getDouble("META_ROJO_FINAL")));

				xYChartSeriesPrecaucion.getData().add(new XYChart.Data(String.valueOf(rsset.getDate("FECHA")),
						rsset.getDouble("META_AMARILLO_FINAL")));

				LogrosAlcanzados auxiliarLogrosAlcanzados = new LogrosAlcanzados(rsset.getInt("ID"), contador,
						rsset.getDate("FECHA").toString(), logroAlcFracc, logroAlc, valorActualAcumulativo);
				auxiliarLogrosAlcanzados.getOpciones().getChildren()
						.remove(auxiliarLogrosAlcanzados.getOpciones().getjFXButtonEditar());
				auxiliarLogrosAlcanzados.getOpciones()
						.setPrefWidth(auxiliarLogrosAlcanzados.getOpciones().getjFXButtonEliminar().getPrefWidth());
				auxiliarLogrosAlcanzados.getOpciones().getjFXButtonEliminar()
						.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {

								Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar :( ?",
										ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
								alert.showAndWait();

								if (alert.getResult() == ButtonType.YES) {
									eliminarMeta(id_meta);
									mostrarDatos();
								}
							}
						});
				tableViewLogrosPorPeriodo.getItems().add(auxiliarLogrosAlcanzados);
				contador++;

			}
			mostrarDatosIndicador(id_ultima_meta);

			conn.close();
			pst.close();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}
				if (rsset != null) {
					rsset.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		lineChartLoggrosAlcanzados.getData().addAll(xYChartSeriesPeligro, xYChartSeriesPrecaucion,
				xYChartSeriesMetaEstablecidad, xYChartSeriesMetaLogradaAcumulativa, xYChartSeriesMetaLogradaPeriodo);

	}

	public void eliminarMeta(int id_meta) {

		String mensaje = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rsset = null;
		try {

			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(" delete from meta_lograda where id = ?;");
			pst.setInt(1, id_meta);
			int rs = pst.executeUpdate();

			conn.close();
			pst.close();

		} catch (SQLException e) {

			Alert alert = new Alert(AlertType.ERROR, "Error :( ?" + e.getMessage(), ButtonType.CANCEL);
			alert.showAndWait();

			e.printStackTrace();

		} finally {
			try {
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}
				if (rsset != null) {
					rsset.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public void mostrarDatosIndicador(int id_meta_lograda) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rsset = null;
		try {

			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(
					"  SELECT ID,  VALOR_ACTUAL,TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, DATO_NUMERO_UNO, "
							+ " DATO_PORCENTAJE_UNO, DATO_PORCENTAJE_DOS, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL,"
							+ " META_VERDE_INICIAL, META_VERDE_FINAL, RESPONSABLE, META_LOGRADA, FECHA, ID_INDICADOR ,"
							+ "RETONAR_VALOR_ACUMULADO(META_LOGRADA.ID_INDICADOR,(SELECT FECHA_INICIO FROM indicador WHERE ID=META_LOGRADA.ID_INDICADOR), FECHA) AS VALOR_ACUMULATIVO"
							+ " FROM META_LOGRADA WHERE ID=? ;");
			pst.setInt(1, id_meta_lograda);
			// pst.setInt(2, id_meta_lograda);
			// pst.setInt(3, id_meta_lograda);

			rsset = pst.executeQuery();
			while (rsset.next()) {

				if (Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD == tipo_indicador) {
					labelLogroAlcanzado
							.setText(Funciones.decimalReducido(rsset.getDouble("VALOR_ACUMULATIVO") + "0000") + "U.");
				} else if (Indicadores_controler.TIPO_INDICADOR_PORCENTAJE == tipo_indicador) {
					labelLogroAlcanzado
							.setText(Funciones.decimalReducido(rsset.getDouble("VALOR_ACUMULATIVO") + "0000") + "%");

				}

				mostrarSemaforo(rsset.getDouble("META_ROJO_INICIAL"), rsset.getDouble("META_ROJO_FINAL"),
						rsset.getDouble("META_AMARILLO_FINAL"), rsset.getDouble("META_VERDE_FINAL"),
						rsset.getDouble("VALOR_ACUMULATIVO"));
			
				
				
				
				
			}

			conn.close();
			pst.close();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}
				if (rsset != null) {
					rsset.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public void mostrarSemaforo(double peligroIncial, double peligro, double precaucion, double meta,
			double metaAlcanzada) {

		double tamano = 0;
		double anguloPeligro = 0;
		double anguloPrecaucion = 0;
		double anguloMetaVerde = 0;
		double anguloMetaSuperadaAzul = 0;
		double anguloMetaAlcanzada = 0;

		if (meta > peligroIncial) {
			if (metaAlcanzada > meta) {
				tamano = metaAlcanzada - peligroIncial;

			} else {
				tamano = meta - peligroIncial;
			}

			// angulo peligro
			anguloPeligro = ((peligro - peligroIncial) * 180) / tamano;
			ArcMetaPeligro.setStartAngle(180 - anguloPeligro);
			ArcMetaPeligro.setLength(anguloPeligro);

			// angulo Precaucion
			anguloPrecaucion = ((precaucion - peligroIncial) * 180) / tamano;
			ArcMetaPrecaucion.setStartAngle(180 - anguloPrecaucion);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);

			// angulo Meta
			anguloMetaVerde = ((meta - peligroIncial) * 180) / tamano;
			ArcMetaVerde.setStartAngle(180 - anguloMetaVerde);
			ArcMetaVerde.setLength(anguloMetaVerde);

			// angulo metaoptima

			// angulo meta alcanzada indicasor
			anguloMetaAlcanzada = ((metaAlcanzada - peligroIncial) * 180) / tamano;

			ArcFlechaIndicador.setStartAngle(180 - anguloMetaAlcanzada - 1);

			labelValorMaximo.setText(meta + "");
			labelValorMinimoPeligro.setText(peligroIncial + "");

			// new hilosIndicador(anguloMetaAlcanzada);

		} else {
			if (metaAlcanzada < meta) {
				tamano = peligroIncial - metaAlcanzada;

			} else {
				tamano = peligroIncial - meta;
			}

			// angulo peligro
			anguloPeligro = ((peligroIncial - peligro) * 180) / tamano;
			ArcMetaPeligro.setStartAngle(0);
			ArcMetaPeligro.setLength(anguloPeligro);

			// angulo Precaucion
			anguloPrecaucion = ((peligroIncial - precaucion) * 180) / tamano;
			ArcMetaPrecaucion.setStartAngle(0);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);

			// angulo Meta
			anguloMetaVerde = ((peligroIncial - meta) * 180) / tamano;
			ArcMetaVerde.setStartAngle(0);
			ArcMetaVerde.setLength(anguloMetaVerde);

			// angulo metaoptima

			// angulo meta alcanzada

			anguloMetaAlcanzada = ((peligroIncial - metaAlcanzada) * 180) / tamano;
			ArcFlechaIndicador.setStartAngle(anguloMetaAlcanzada - 1);

			labelValorMaximo.setText(peligroIncial + "");
			labelValorMinimoPeligro.setText(meta + "");
		}

	}
	
	

	public void mostrarIntervalosFechas(LocalDate fechaInicio, LocalDate fechaFinal, LocalDate fechaActual) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
		Date fecha_Inicial;
		Date fecha_Final;
		Date fecha_actual;
		int dias_inicio_final=0;
		int dias_inicio_actual=0;
		
		try {
			fecha_Inicial = dateFormat.parse(fechaInicio.toString());
	
			fecha_Final=dateFormat.parse(fechaFinal.toString());
			fecha_actual=dateFormat.parse(fechaActual.toString());
			
			
			dias_inicio_final=(int) ((fecha_Final.getTime()-fecha_Inicial.getTime())/86400000);
			dias_inicio_actual=(int) ((fecha_actual.getTime()-fecha_Inicial.getTime())/86400000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("cronometro xD  ");
		System.out.println(dias_inicio_actual);
		System.out.println(dias_inicio_final);
		System.out.println(dias_inicio_actual/dias_inicio_final);
		
		Double porCentaje=Double.parseDouble(dias_inicio_actual+"")/Double.parseDouble(dias_inicio_final+"");
		jfxProgressBarLineaTiempo.setProgress(porCentaje);
	

		
		
		
	}

	public void mostrarSeleccionarDatosIndicador() {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(
					" SELECT NOMBRE, VALOR_ACTUAL, FORMULA,META_ROJO_INICIAL,META_ROJO_FINAL,META_AMARILLO_INICIAL,"
							+ " META_AMARILLO_FINAL,META_VERDE_INICIAL,META_VERDE_FINAL,RESPONSABLE," + ""
							+ "TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, FECHA_INICIO, FECHA_FINAL, MEDIR_POR "
							+ "" + " FROM INDICADOR WHERE ID=?;");
			pst.setInt(1, id_indicador);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				
				labelFechaInicial.setText(rs.getDate("FECHA_INICIO").toString());
				labelFechaFinal.setText(rs.getDate("FECHA_FINAL").toString());
				labelFechaMeta.setText(rs.getString("META_VERDE_FINAL"));
				
				if (Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD == tipo_indicador) {
					labelValorActualIncial.setText(rs.getDouble("VALOR_ACTUAL")+"U.");
					labelFechaMeta.setText(rs.getString("META_VERDE_FINAL")+"U.");

				} else if (Indicadores_controler.TIPO_INDICADOR_PORCENTAJE == tipo_indicador) {
					labelValorActualIncial.setText((rs.getDouble("VALOR_ACTUAL")*100)+"%");
					labelFechaMeta.setText((rs.getDouble("META_VERDE_FINAL")*100)+"%");
				

				}
				
				///
				
				/*xYChartSeriesMetaEstablecidad.getData().add(
						new XYChart.Data(String.valueOf(rs.getDate("FECHA_INICIO")), rs.getDouble("META_VERDE_FINAL")));
				xYChartSeriesMetaEstablecidad.getData().add(
						new XYChart.Data(String.valueOf(rs.getDate("FECHA_FINAL")), rs.getDouble("META_VERDE_FINAL")));
				
				
				
				xYChartSeriesMetaLogradaAcumulativa.getData().add(
						new XYChart.Data(String.valueOf(rs.getDate("FECHA_INICIO")), 0));

				xYChartSeriesMetaLogradaAcumulativa.getData().add(
						new XYChart.Data(String.valueOf(rs.getDate("FECHA_FINAL")), 0));
				
				
				
				if (Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD == tipo_indicador) {
					xYChartSeriesMetaLogradaPeriodo.getData().add(new XYChart.Data(
							String.valueOf(rs.getDate("FECHA_INICIO")), 0));
					xYChartSeriesMetaLogradaPeriodo.getData().add(new XYChart.Data(
							String.valueOf(rs.getDate("FECHA_FINAL")), 0));
					

				} else if (Indicadores_controler.TIPO_INDICADOR_PORCENTAJE == tipo_indicador) {
					xYChartSeriesMetaLogradaPeriodo.getData()
							.add(new XYChart.Data(String.valueOf(rs.getDate("FECHA_INICIO")),0));
					xYChartSeriesMetaLogradaPeriodo.getData()
					.add(new XYChart.Data(String.valueOf(rs.getDate("FECHA_FINAL")),
							0));

				}

				xYChartSeriesPeligro.getData().add(
						new XYChart.Data(String.valueOf(rs.getDate("FECHA_INICIO")), rs.getDouble("META_ROJO_FINAL")));
				xYChartSeriesPeligro.getData().add(
						new XYChart.Data(String.valueOf(rs.getDate("FECHA_FINAL")), rs.getDouble("META_ROJO_FINAL")));
				
				xYChartSeriesPrecaucion.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA_INICIO")),
						rs.getDouble("META_AMARILLO_FINAL")));
				
				xYChartSeriesPrecaucion.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA_FINAL")),
						rs.getDouble("META_AMARILLO_FINAL")));
				
				*/
				
				///
				
				Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {

						super.updateItem(item, empty);

						this.setDisable(false);
						this.setBackground(null);
						this.setTextFill(Color.BLACK);

						/*
						if (item.isAfter(LocalDate.now())) {
							this.setDisable(true);
						}
						*/

						int day = item.getDayOfMonth();
						if (day == 15 || day == 30) {

							Paint color = Color.RED;
							BackgroundFill fill = new BackgroundFill(color, null, null);

							this.setBackground(new Background(fill));
							this.setTextFill(Color.WHITESMOKE);
						}

						DayOfWeek dayweek = item.getDayOfWeek();
						if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
							this.setTextFill(Color.GREEN);
						}
					}
				};


				
				//asdf
				jFXDatePickerFechaLogro.setDayCellFactory(dayCellFactory);
				mostrarIntervalosFechas(rs.getDate("FECHA_INICIO").toLocalDate(), rs.getDate("FECHA_FINAL").toLocalDate(), LocalDate.now());
			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();

				}
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

}
