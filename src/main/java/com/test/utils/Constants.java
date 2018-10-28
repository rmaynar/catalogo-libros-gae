package com.test.utils;

/**
 * Constantes
 *
 * @author Ana Belen Carracedo
 *
 */
public final class Constants {

	public static final int MAX_ERRORES_ENVIO_FRONT = 10;
	public static final String EXT_DAT = ".dat";
	public static final String EXT_CTL = ".ctl";
	public static final String PRE_CESION_NAME = "TAXO_";
	public static final String PRE_CESION_NAME_SIN_GUION = "TAXO";
	public static final String CONF = "CONF";
	public static final String MCAT = "MCAT";
	public static final String MREL = "MREL";
	public static final String VCAT = "VCAT";
	public static final String VREL = "VREL";
	/**
	 * Código de origen : Configuración de catálogos mensual
	 */
	public static final String CCM = "CCM";
	/**
	 * Código de origen : Maestro de catálogos mensual
	 */
	public static final String MCM = "MCM";
	/**
	 * Código de origen : Valores de catálogos mensual
	 */
	public static final String VCM = "VCM";
	/**
	 * Código de origen : Maestro de relaciones mensual
	 */
	public static final String MRM = "MRM";
	/**
	 * Código de origen : Valores de relaciones mensual
	 */
	public static final String VRM = "VRM";
	/**
	 * Código de origen : Configuración de catálogos diario
	 */
	public static final String CCD = "CCD";
	/**
	 * Código de origen : Maestro de catálogos diario
	 */
	public static final String MCD = "MCD";
	/**
	 * Código de origen : Valores de catálogos diario
	 */
	public static final String VCD = "VCD";
	/**
	 * Código de origen : Maestro de relaciones diario
	 */
	public static final String MRD = "MRD";
	/**
	 * Código de origen : Valores de relaciones diario
	 */
	public static final String VRD = "VRD";

	/**
	 * Código maestro catálogos
	 */
	public static final String COD_MAESTRO_CATALOGOS = "C000";
	
	/**
	 * Código maestro relaciones
	 */
	public static final String COD_MAESTRO_RELACIONES = "C092";
	
	/*
	 * Código remitente HISC
	 */
	public static final String COD_REMITENTE_HISC = "HISC";
	
	public static final String NOMBRE_CESION_CONFIGURACION_MAESTRO_CATALOGOS = "ConfiguracionMaestraCatalogos";
	public static final String NOMBRE_CESION_MAESTRO_CATALOGOS = "MaestraCatalogos";
	public static final String NOMBRE_CESION_MAESTRO_RELACIONES = "MaestraRelaciones";
	public static final String NOMBRE_CESION_VALORES_CATALOGOS = "ValoresCatalogos";
	public static final String NOMBRE_CESION_VALORES_RELACIONES = "ValoresRelaciones";
	public static final String GUION_BAJO = "_";

	private Constants() {

	}

	public static final int SAVE_LIMIT_DS = 1000;
	public static final int ESTADO_PREBORRADOR = 0;
	public static final int ESTADO_BORRADOR = 1;
	/**
	 * Fichero con la configuración de los maestros
	 */
	public static final String NOMBRE_FICHERO_CONFIGURACION_MAESTROS = "configuracionCatalogos.csv";

	/**
	 * Fichero maestro catálogos
	 */
	public static final String NOMBRE_FICHERO_MAESTRO_CATALOGOS = "maestro_catalogos.csv";

	/**
	 * Fichero valores catálogos
	 */
	public static final String NOMBRE_FICHERO_VALORES_CATALOGOS = "valores_catalogos.csv";

	/**
	 * Fichero maestro catálogos
	 */
	public static final String NOMBRE_FICHERO_MAESTRO_RELACIONES = "maestro_relaciones.csv";

	/**
	 * Fichero valores relaciones
	 */
	public static final String NOMBRE_FICHERO_VALORES_RELACIONES = "valores_relaciones.csv";

	public static final String CATALOGO_REMITENTES = "C008";
	public static final String CATALOGO_PAISES = "C007";
	public static final String CATALOGO_ACTIVIDAD_HOLDING = "C039";
	public static final String CATALOGO_SECTOR_HOLDING = "C162";
	public static final String CATALOGO_RIESGO_HOLDING = "C164";
	public static final String CATALOGO_FINREP = "C311";
	public static final String CATALOGO_ACT_CIRBE = "C313";
	public static final String CATALOGO_NACE_LE = "C314";
	public static final String CATALOGO_CONTRAPARTIDA = "C312";
	public static final String CATALOGO_FSB = "C236";

	public static final String CRITICIDAD_OCH = "OCH";
	public static final String CRITICIDAD_RHL = "RHL";

	public static final String REMITENTE_HISC = "HISC";

	public static final String RELACION_R004 = "R004";

	/**
	 * Bucket que contiene el fichero de configuración
	 */
	public static final String PROPERTY_BUCKET_CONFIG = "bucket.config";

	/**
	 * Bucket que contendrá los ficheros procedentes de Oracle
	 */
	public static final String PROPERTY_BUCKET_INPUT = "bucket.input";
	/**
	 * Contiene los ficheros de upload de mantenimiento de valores y relaciones
	 */
	public static final String PROPERTY_BUCKET_ENVIO_CESIONES = "bucketEnvioCesiones";
	public static final String PROPERTY_EMAIL_ERROR_ENVIO_CESIONES = "emailErrorEnvioCesiones";
	public static final String PROPERTY_BUCKET_RETRIES_COUNT = "bucketRetriesCount";
	public static final String PROPERTY_BUCKET_TMP = "bucket.tmp";
	public static final String PROPERTY_BUCKET_CESIONES = "bucket.cesiones";
	public static final String PROPERTY_BUCKET_CESIONES_HISTORICO = "bucket.cesiones.history";
	public static final String PROPERTY_BUCKET_APP_DESTINO = "bucket.app.destino";
	//Indica el entorno
	public static final String PROPERTY_ENVIRONMENT = "environment";

	public static final String CARACTER_COMILLA_STRING = "\"";
	public static final String CARACTER_ESPACIO_BLANCO_STRING = " ";
	public static final char COLUMNA_SEPARATOR_PUNTOYCOMA = ';';
	public static final String COLUMNA_SEPARATOR_PUNTOYCOMA_STRING = ";";
	public static final char COLUMNA_SEPARATOR_COMA = ',';
	public static final char COLUMNA_SEPARATOR_PIPE = '|';
	public static final String COLUMNA_SEPARATOR_PIPE_STRING = "\\|";
	public static final char COLUMNA_SEPARATOR_ACENTO_CIRCUNFLEJO = '^';
	public static final String COLUMNA_SEPARATOR_ACENTO_CIRCUNFLEJO_STRING = "\\^";

	public static final String PATRON_FECHA_YYYYMM = "yyyyMM";
	public static final String PATRON_FECHA = "yyyyMMdd";
	public static final String PATRON_FECHA_GUION = "yyyy-MM-dd";
	public static final String PATRON_FECHA_HORA = "yyyyMMddHHmmss";
	public static final String PATRON_FECHA_PARA_MOSTRAR = "dd/MM/yyyy";
	public static final String PATRON_FECHA_HORA_PARA_MOSTRAR = "dd/MM/yyyy HH:mm";
	public static final String PATRON_FECHA_HORA_MILISEGUNDOS = "dd/MM/yy HH:mm:ss,SSSSSS";
	public static final String PATRON_FECHA_HORA_MILISEGUNDOS_ORACLE = "dd-MMM-yy hh.mm.ss.SSSSSS a";
	public static final String TZ_EUROPE_MADRID = "Europe/Madrid";

	public static final String UTF_8 = "UTF-8";
	public static final String ISO_8859_1 = "ISO-8859-1";

	public static final String ENCODING_FICHEROS = "ISO-8859-1";

	public static final String URL_CARGA_FICHEROS = "/task/backend/carga/ficheros";

	public static final String COD_SITUACION_ALTA = "A";
	public static final String COD_SITUACION_BAJA = "B";
	public static final String COD_SITUACION_EDICION = "E";
	public static final String XTI_ESTADO_VIGENTE = "V";
	public static final String XTI_ESTADO_NO_VIGENTE = "X";
	public static final String VISIBLE = "true";

	public static final String SIN_FECHA_FIN = "99999999";

	public static final String PARAM_REMITENTES = "remitentes";

	public static final String INDICE_CATALOGO_REMITENTE = "INDEX_CATALOGO_REMITENTE";
	public static final String INDICE_ADMINISTRACION_CATALOGOS = "INDEX_ADMINISTRACION_CATALOGOS";
	public static final String INDICE_ADMINISTRACION_RELACIONES = "INDEX_ADMINISTRACION_RELACIONES";
	public static final String INDICE_USUARIOS_RESPONSABLES = "INDEX_USUARIOS_RESPONSABLES";

	public static final String ITEMS_PAGE_DEFAULT = "items.page.default";

	/**
	 * Campo para recuperar en la pantalla de relaciones la parte de la derecha o la
	 * de la izquierda Tipo: 0-desde/1-hasta
	 */
	public static final int TIPO_RELACION_DESDE = 1;

	public static final String MOCK_NULOS = "mock.valores.null";

	public static final String SEPARADOR_CLAVE_DS = "-";
	public static final String LIMIT_ACCURACY = "limit.accuracy";

	public static final String ASCCENDENTE = "ASC";
	public static final String DESCENDENTE = "DESC";
	public static final String ORDEN_DESC_DS = "-";
	public static final String ORDER_BY_DEFAULT = "03";

	public static final String IGNORAR_FECHA_CARGA = "ignorar.fecha.carga";
	public static final String STR_TRUE = "true";
	public static final String STR_FALSE = "false";

	/**
	 * tamaño del Bloque para inserciones de fichero de valores de catálogo y
	 * valores de las relaciones de catálogos
	 */
	public static final String NUM_VALORES_DS = "bloque.insercion.ds";

	/**
	 * Lectura de entidades grandes de DS en bloques con cursor
	 */
	public static String BLOQUE_LECTURA_DS = "bloque.lectura.ds";

	/**
	 * Lectura de ficheros con offset
	 */
	public static final String CARGA_INICIAL = "inicial";
	public static final String OFFSET = "offset";
	public static final String STR_OFFSET_INICIAL = "0";
	public static final String MAX_BLOQUE_TAREA = "max.bloque.tarea";
	public static final String SLEEP_MEDIO_MINUTO = "sleep.medio.minuto";
	public static final String SLEEP_MINUTO = "sleep.minuto";
	public static final String SLEEP_SEGUNDO = "sleep.segundo";
	public static final String CATALOGOS_VALORES_LECTURA_FICHEROS_IGNORAR = "catalogos.valores.lectura.fichero.ignorar";

	public static final String ID_APLICACION_GNOTMA = "idAplicacionGnotma";
	public static final String ID_ACTIVIDAD_GNOTMA = "idActividadGnotma";
	public static final String URL_GNOTMA = "url.gnotma";
	public static final String ROBOT_DEV_GNOTMA = "robot.gnotma";

	public static final String NUM_CPOS_FICH_MAE_CATALOGO = "num.campos.fich.maestro.catalogo";
	public static final String NUM_CPOS_FICH_VAL_CATALOGO = "num.campos.fich.valores.catalogo";
	public static final String NUM_CPOS_FICH_MAE_RELACION = "num.campos.fich.maestro.relacion";
	public static final String NUM_CPOS_FICH_VAL_RELACION = "num.campos.fich.valores.relacion";

	/**
	 * Colas
	 */
	public static final String COLA_BATCH_CESIONES = "cesiones";
	public static final String COLA_BATCH_CESIONES_GENERICAS = "cesionesgenericas";
	public static final String COLA_BATCH_CALCULO_VIGENCIAS = "calculoVigencias";
	public static final String COLA_BATCH_POSTPROCESO = "postproceso";
	public static final String COLA_BATCH_POSTPROCESO_PERIODICO = "postprocesoperiodico";
	public static final String COLA_BATCH_PROCESO_MAPEO_ERRONEO = "procesomapeoerroneo";
	public static final String COLA_COMPROBACION_TAREAS = "comprobaciontareas";
	public static final String COLA_ELIMINAR_ENTIDAD_FICHERO_MAESTRO_CATALOGOS = "ficheromaestrocatalogos";
	public static final String COLA_ELIMINAR_ENTIDAD_FICHERO_MAESTRO_RELACIONES = "ficheromaestrorelaciones";
	public static final String COLA_ELIMINAR_ENTIDAD_FICHERO_VALORES_CATALOGOS = "ficherovalorescatalogos";
	public static final String COLA_ELIMINAR_ENTIDAD_FICHERO_VALORES_RELACIONES = "ficherovaloresrelaciones";

	public static final String COLA_BATCH_POSTPROCESO_CATALOGO = "postprocesocatalogo1";
	public static final String COLA_BATCH_POSTPROCESO_RELACION = "postprocesorelacion";

	public static final String COLA_BATCH_BORRADO_FICHERO_MAESTRO_CATALOGOS = "ficheroMaestroCatalogos";
	public static final String COLA_BATCH_BORRADO_FICHERO_MAESTRO_RELACIONES = "ficheroMaestroRelaciones";
	public static final String COLA_BATCH_BORRADO_FICHERO_VALORES_CATALOGOS = "ficheroValoresCatalogos";
	public static final String COLA_BATCH_BORRADO_FICHERO_VALORES_RELACIONES = "ficheroValoresRelaciones";

	public static final String REINTENTOS_POSTPROCESO = "reintentos.tarea.postproceso";
	public static final String REINTENTOS_CARGA_INICIAL = "reintentos.tarea.carga.inicial";

	/**
	 * Excel de descarga
	 */
	public static final String NOMBRE_FICHERO_DESCARGA = "nombre.fich.descarga";
	/**
	 * Cabecera de la petición..
	 */
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	/**
	 * Para la descarga del documento.
	 */
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	/**
	 * El nombre del fichero al exportar resultados de búsqueda a XLS.
	 */
	public static final String XLS_EXPORT_FILENAME = "export";
	/**
	 * La extensión del fichero XLSX.
	 */
	public static final String XLSX_EXTENSION = ".xlsx";
	/**
	 * Tipo de MIME para el XLSX
	 */
	public static final String APPLICATION_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static final String URL_DETALLE = "url.detalle";
	public static final String URL_MARCO = "url.marco.intranet";

	public static final String CELDA_SOLO_LECTURA = "|@|";
	public static final int NUM_ELEM_POSTPROCESO_CATALOGO = 250;

	public static final int NUM_ELEM_POSTPROCESO_RELACION = 250;

	public static final String TIPO_ORDER_HASTA = "0";

	public static final int NUM_ELEM_RESPONSABLES = 5;

	public static final String NUM_ELEM_LISTADO_USUARIOS = "200";

	public static final String CURSOR = "cursor";
	public static final String IS_VISUALIZACION = "isVisualizacion";

	public static final String NUM_ELEM = "numElem";
	public static final String UPLOAD_FILE_INI = "UPLOAD_MAN_VALORES_";
	public static final int NUM_ELEMS = 500;

	// public static final String TIPO_DESCARTAR = "0";

	public static final int TIPO_CAMBIO_ALTA = 0;
	public static final int TIPO_CAMBIO_MODIFICACION = 1;
	public static final int TIPO_CAMBIO_SIN_CAMBIO = 3;
	public static final int TIPO_CAMBIO_BAJA = 2;
	public static final int TIPO_CAMBIO_FECHA_INICIO = 4;
	public static final int TIPO_CAMBIO_FECHA_FIN = 5;

	public static final String NUM_CELDAS_MANT_VAL = "num.celdas.mantenimiento.valores";

	public static final String AMBITO_LOCAL = "L";
	public static final String AMBITO_HOLDING = "H";

	public static final String FILTRO_CARGA_MANUAL = "M";
	public static final String FILTRO_CARGA_DIARIA = "D";

	public static final String FILTRO_CERO = "0";
	public static final String FILTRO_UNO = "1";

	public static final int NUM_USUARIOS_A_MOSTRAR = 100;

	public static final String NUM_CELDAS_MANT_REL = "num.celdas.mantenimiento.relaciones";

	public static final int INT_TIPO_BORRADOR = 0;
	public static final int INT_TIPO_CONSOLIDADO = 1;

	public static final int ACCION_NUEVO = 0;

	public static final int ACCION_EDITAR = 1;

	public static final int ACCION_ELIMINAR = 2;

	public static final int SIN_ACCION = 3;

	public static final String FILTRO_TRUE = "0";

	public static final String RESPONSABLE_ZZZZZ = "ZZZZZ";

	public static final String TIPO_NOTIFICACION = "tipoNotificacion";
	public static final String ID_ENTIDAD = "idEntidad";
	public static final String TIPO_TAREA_NAME = "tipoTarea";
	public static final int TIPO_TAREA_VALORES = 0;
	public static final int TIPO_TAREA_MAPEOS = 1;
	public static final int TIPO_TAREA_CARGA_DIARIA = 2;
	public static final int TIPO_TAREA_PETICION_CREACION_VALORES = 3;
	public static final int TIPO_TAREA_PETICION_CREACION_MAPEOS = 4;

	public static final String TIPO_PETICION_VALORES_CATALOGO = "0";
	public static final String TIPO_PETICION_MAPEO_VALORES_CATALOGO = "1";
	public static final int TIPO_CONSOLIDADA = 0;
	public static final int TIPO_RECHAZADA = 1;
	public static final int TIPO_BORRADOR = 2;
	public static final int TIPO_CREADA = 3;
	public static final int TIPO_COMUNICADA = 4;
	public static final int TIPO_DESCARTADA = 5;

	public static final String QUEUE_NOTIFICACIONES = "notificaciones";
	public static final String FECH_0_MILS = "19700101";
	public static final String TIME_ZONE_ESP = "Europe/Madrid";

	public static final String IDIOMA_ES = "ES";
	public static final String IDIOMA_EN = "EN";

	public static final String INICIAL = "inicial";

	public static final String COMIENZO_NOMBRE_FICHERO_MAESTRO_CATALOGOS = "KYHI_HISC_MCD_";
	public static final String COMIENZO_NOMBRE_FICHERO_MAESTRO_RELACIONES = "KYHI_HISC_MRL_";
	public static final String COMIENZO_NOMBRE_FICHERO_VALORES_CATALOGOS = "KYHI_HISC_ACD_";
	public static final String COMIENZO_NOMBRE_FICHERO_VALORES_RELACIONES = "KYHI_HISC_ARL_";
	public static final String COMIENZO_NOMBRE_FICHERO_TESTIGO_CARGA_DIARIA = "KYHI_HISC_CTL_";

	public static final String EXTENSION_FICHERO_CSV = ".csv";

	public static final String EXTENSION_FICHERO_DAT = ".dat";

	public static final String EXTENSION_FICHERO_CTL = ".ctl";

	public static final String PARAM_REMITENTE = "remitente";

	public static final int NUM_ELEMENTOS_DS = 1000;
	public static final int NUM_ELEMENTOS_BORRAR_DS = 500;
	public static final int NUM_CATALOGOS_REMITENTES_INDEXAR = 200;

	public static final String SI = "S";
	public static final String NO = "N";

	public static final String FECHA_HASTA_SIN_VALOR = "99991231";

	public static final String URL_DIRECTORIO_CARGA_DIARIA = "finance/hisAdmin/";

	public static final String URL_DIRECTORIO_CESIONES_HIS = "fin/his/outbox/";

	public static final String URL_DIRECTORIO_CESIONES_GENERICAS = "fin/hisadm/outbox/";

	public static final String HORA_LIMITE_COMIENZO_CARGA_DIARIA = "horaLimiteComienzoCargaDiaria";

	public static final String HORA_LIMITE_EJECUCION_TAREAS = "horaLimiteEjecucionTareas";

	public static final String FECHA_AUDITORIA = "31/12/9999 00:00:00.000000";

	public static final String ESTADO_BAJA = "B";
	public static final String ESTADO_ALTA = "A";

	public static final String FECHA_INICIO_DEFECTO = "00000101";
	/**
	 * Valor por defecto de los catálogos
	 */
	public static final String XTI_VALOR_DEFECTO = "D";

	/**
	 * Longitud máxima del código de valor
	 */
	public static final int MAX_CODIGO_VALOR = 100;

	public static final String IDIOMA_EMAIL_ES = "es_ES";
	public static final String IDIOMA_EMAIL_EN = "es_EN";
	public static final String PLANTILLA_EMAIL_CREACION_PETICION_VALORES_CATALOGO = "plantillaEmailCreacionPeticionValores";
	public static final String PLANTILLA_EMAIL_CREACION_PETICION_MAPEO_VALORES_CATALOGO = "plantillaEmailCreacionPeticionMapeoValores";
	public static final String PLANTILLA_EMAIL_COMUNICACION_PETICION_VALORES_CATALOGO = "plantillaEmailComunicacionPeticionValores";
	public static final String PLANTILLA_EMAIL_COMUNICACION_PETICION_MAPEO_VALORES_CATALOGO = "plantillaEmailComunicacionPeticionMapeoValores";
	public static final String PLANTILLA_EMAIL_DESCARTAR_PETICION_VALORES_CATALOGO = "plantillaEmailDescartarPeticionValores";
	public static final String PLANTILLA_EMAIL_DESCARTAR_PETICION_MAPEO_VALORES_CATALOGO = "plantillaEmailDescartarPeticionMapeoValores";
	public static final String PLANTILLA_EMAIL_RECHAZAR_PETICION_VALORES_CATALOGO = "plantillaEmailRechazarPeticionValores";
	public static final String PLANTILLA_EMAIL_RECHAZAR_PETICION_MAPEO_VALORES_CATALOGO = "plantillaEmailRechazarPeticionMapeoValores";
	public static final String PLANTILLA_EMAIL_CONSOLIDAR_PETICION_VALORES_CATALOGO = "plantillaEmailConsolidarPeticionValores";
	public static final String PLANTILLA_EMAIL_CONSOLIDAR_PETICION_MAPEO_VALORES_CATALOGO = "plantillaEmailConsolidarPeticionMapeoValores";

	public static final String PLANTILLA_EMAIL_CERRAR_PETICION_MAPEO_VALORES_CATALOGO = "plantillaEmailCerrarPeticionMapeoValores";

	public static final String PLANTILLA_EMAIL_VALORES_CATALOGO = "plantillaEmailValores";
	public static final String PLANTILLA_EMAIL_ERRORES_VALORES_CATALOGO = "plantillaEmailErroresValores";
	public static final String PLANTILLA_EMAIL_MAPEO_VALORES_CATALOGO = "plantillaEmailMapeoValores";
	public static final String PLANTILLA_EMAIL_ERRORES_CESIONES = "plantillaEmailErroresCesiones";

	public static final String PARAM_SECUENCIAL_NOMBRE_FICHERO_CARGA_DIARIA = "000";

	public static final String COMIENZO_NOMBRE_ID_PETICION = "TAXO";

	public static final String ID_PETICION = "idPeticion";

	public static final String FILTRO_TIPO_TODAS = "99";

	public static final String MOTIVO_NO_PROCESAR_MAPEO = "motivo";

	/*
	 * Indica el origen de los datos a validar
	 */
	public static final boolean MANTENIMIENTO = true;
	public static final boolean CARGA_DIARIA = false;

	// Jersey Validation Errors
	public static final String VALIDATION_CODE = "CODE";
	public static final String VALIDATION_MSG = "MSG";
	public static final String ERRORS = "Errors";
	
	//Nuevos tipos de catálogo
	public static final String CATALOGO_TIPO_FUNCIONAL = "F";
	public static final String CATALOGO_TIPO_TECNICO = "T";
	
	/**
	 * Nombres por defecto de las cabeceras de los atributos
	 */
	
	public static final String CABECERA_ATRIBUTO_1 = "Atributo 1";
	public static final String CABECERA_ATRIBUTO_2 = "Atributo 2";
	public static final String CABECERA_ATRIBUTO_3 = "Atributo 3";
	public static final String CABECERA_ATRIBUTO_4 = "Atributo 4";
	public static final String CABECERA_ATRIBUTO_5 = "Atributo 5";
	public static final String CABECERA_ATRIBUTO_6 = "Atributo 6";
	public static final String CABECERA_ATRIBUTO_7 = "Atributo 7";
	public static final String CABECERA_ATRIBUTO_8 = "Atributo 8";
	public static final String CABECERA_ATRIBUTO_9 = "Atributo 9";
	public static final String CABECERA_ATRIBUTO_10 = "Atributo 10";
	public static final String CABECERA_ATRIBUTO_11 = "Atributo 11";
	public static final String CABECERA_ATRIBUTO_12 = "Atributo 12";
	public static final String CABECERA_ATRIBUTO_13 = "Atributo 13";
	public static final String CABECERA_ATRIBUTO_14 = "Atributo 14";
	public static final String CABECERA_ATRIBUTO_15 = "Atributo 15";
	public static final String CABECERA_ATRIBUTO_16 = "Atributo 16";
	public static final String CABECERA_ATRIBUTO_17 = "Atributo 17";
	public static final String CABECERA_ATRIBUTO_18 = "Atributo 18";
	public static final String CABECERA_ATRIBUTO_19 = "Atributo 19";
	public static final String CABECERA_ATRIBUTO_20 = "Atributo 20";

	//Nombre de creador de administración de catálogos/relaciones por defecto
	
	public static final String DEFAULT_ADMIN_CREATOR = "ORACLE";
	
}
