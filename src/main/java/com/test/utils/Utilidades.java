package com.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.apache.commons.lang.text.StrBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import com.google.common.collect.Lists;

/**
 * Utilidades de la aplicación.
 *
 *
 */

public class Utilidades {

	private static final Logger LOG = Logger.getLogger(Utilidades.class.getName());

	public static final String LOCAL_ES = "es_ES";

	private Utilidades() {

	}

	/**
	 * Comprueba si el usuario conectado es desarrollador, tiene permisos de
	 * administración sobre la aplicación en la Google Developers Console.
	 *
	 * @return true si el usuario es desarrollador, false en caso contrario.
	 */
	public static boolean isDeveloper() {
		UserService userService = UserServiceFactory.getUserService();
		// No se puede comprobar si es admin si no hay usuario logado
		return userService.isUserLoggedIn() && userService.isUserAdmin();
	}

	/**
	 * Imprime una excepción junto a un mensaje pasado como parámetro.
	 *
	 * @param message
	 *            El mensaje a imprimir
	 * @param e
	 *            La excepción..
	 */
	public static String printException(final String message, Exception e) {
		StringWriter error = new StringWriter();
		e.printStackTrace(new PrintWriter(error));
		return message + ": " + error;
	}


	/**
	 * Recupera la fecha con formato de España - Controlando el horario de verano
	 *
	 * @return
	 */
	public static DateTime getDateTimeEsp() {
		return new DateTime().withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ESP)));
	}

	/**
	 * Recupera la fecha con formato de España - Controlando el horario de verano
	 *
	 * @return
	 */
	public static String getDateEsp(String pattern) {
		return getStringFromDateTime(
				new DateTime().withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ESP))),
				pattern);
	}

	/**
	 * Recupera la fecha con formato de España - Controlando el horario de verano
	 * Sin horas min y sg
	 *
	 * @return
	 */
	public static DateTime getDateEspFromString(String date) {
		DateTime fec = getDateTimeFromString(date, Constants.PATRON_FECHA);
		if (fec == null) {
			return null;
		}
		return fec.withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ESP)))
				.withTimeAtStartOfDay();
	}

	/**
	 * Devuelve una fecha formateada segun el patrón yyyyMMddHHmmss.
	 *
	 * @param date
	 *            La fecha a formatear.
	 * @return El String con la fecha en el formato especificado.
	 */
	public static String getFechaHoraFormateada(DateTime date) {
		return getStringFromDateTime(date, Constants.PATRON_FECHA_HORA);
	}

	public static String getFechaHoraFormateadaAnnoMesDia(DateTime date) {
		return getStringFromDateTime(date, Constants.PATRON_FECHA);
	}

	/**
	 * Devuelve una fecha formateada segun el patrón definido.
	 *
	 * @param date
	 *            La fecha a formatear.
	 * @param pattern
	 *            El patrón.
	 * @return El String con la fecha en el formato especificado.
	 */
	public static String getStringFromDateTime(DateTime date, String pattern) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf.print(date);
	}

	/**
	 * Devuelve la fecha como String con el patrón dd/MM/yyyy partiendo de un
	 * DateTime.
	 *
	 * @param date
	 *            La fecha.
	 * @return La fecha como String según el patrón dd/MM/yyyy.
	 */
	public static String getStringFromDateTimeToShow(DateTime date, boolean conHora) {
		if (conHora) {
			return getStringFromDateTime(date, Constants.PATRON_FECHA_HORA_PARA_MOSTRAR);
		} else {
			return getStringFromDateTime(date, Constants.PATRON_FECHA_PARA_MOSTRAR);
		}
	}

	/**
	 * Devuelve la fecha como DateTime de un String con el patrón yyyyMMdd.
	 *
	 * @param date
	 *            El String con la fecha.
	 * @return La fecha como DateTime
	 */
	public static DateTime getFechaDesdeString(String date) {
		return getDateTimeFromString(date, Constants.PATRON_FECHA);
	}

	/**
	 * Devuelve la fecha como DateTime de un String con el patrón dd-MMM-yy
	 * hh.mm.ss.SSSSSS a. (31-OCT-17 10.13.20.965480 PM)
	 *
	 * @param date
	 *            El String con la fecha.
	 * @return La fecha como DateTime
	 */
	public static DateTime getFechaHoraDesdeStringOracle(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.PATRON_FECHA_HORA_MILISEGUNDOS_ORACLE);
		SimpleDateFormat sdfFin = new SimpleDateFormat(Constants.PATRON_FECHA_HORA);
		String fechaOut = null;
		DateTime res = null;
		Date d = null;
		try {
			d = sdf.parse(date);
			fechaOut = sdfFin.format(d);
			res = getDateTimeFromString(fechaOut, Constants.PATRON_FECHA_HORA);
		} catch (ParseException e) {

			res = getDateTimeFromString(date, Constants.PATRON_FECHA_HORA);
			if (res == null) {
				res = getFechaHoraDesdeStringMills(date);
			}
			if (res == null) {
				res = getDateTimeFromString(date, Constants.PATRON_FECHA_HORA_MILISEGUNDOS);
			}
			if (res == null) {
				LOG.warning(String.format("No se puede convertir la fecha %s", date));
			}
		}
		return res;
	}

	/**
	 * Devuelve la fecha como DateTime de un String con el patrón dd/MM/yy
	 * HH:mm:ss,SSSSSS
	 *
	 * @param date
	 *            El String con la fecha.
	 * @return La fecha como DateTime
	 */
	public static DateTime getFechaHoraDesdeStringMills(String date) {

		DateTime res = null;
		SimpleDateFormat sdfFin = new SimpleDateFormat(Constants.PATRON_FECHA_HORA);
		try {
			String fechaOut = sdfFin.format(FORMAT_FECHA_HORA_MILISEGUNDOS.parse(date));
			res = getDateTimeFromString(fechaOut, Constants.PATRON_FECHA_HORA);
		} catch (ParseException e) {
			LOG.warning(String.format("No se puede convertir la fecha %s según el patrón %s", date, Constants.PATRON_FECHA_HORA));
		}

		return res;
	}

	/**
	 * Devuelve la fecha como DateTime de un String con el patrón yyyyMMddHHmmss.
	 *
	 * @param date
	 *            El String con la fecha.
	 * @return La fecha como DateTime
	 */
	public static DateTime getFechaHoraDesdeString(String date) {
		return getDateTimeFromString(date, Constants.PATRON_FECHA_HORA);
	}

	/**
	 * Devuelve uan fecha como DateTime.
	 *
	 * @param date
	 *            La fecha como String.
	 * @param pattern
	 *            El patrón.
	 * @return El DateTime correspondiente a la fecha según el patrón especificado o
	 *         null si la fecha de entrada es null.
	 */
	private static DateTime getDateTimeFromString(String date, String pattern) {
		DateTime res = null;
		if (date != null) {
			try {
				DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
				res = dtf.parseDateTime(date);
			} catch (Exception e) {
				LOG.severe("Error: " + e);
			}
		}
		if (res == null) {
			LOG.warning(String.format("No se puede convertir la fecha %s según el patrón %s", date, pattern));
		}
		return res;
	}

	private static final SimpleDateFormat FORMAT_FECHA_HORA_MILISEGUNDOS = new SimpleDateFormat(
			Constants.PATRON_FECHA_HORA_MILISEGUNDOS);
	private static final SimpleDateFormat FORMAT_FECHA_HORA = new SimpleDateFormat(Constants.PATRON_FECHA_HORA);

	/**
	 * Convierte una fecha (String) con milisegundos en formato sin slash y sin
	 * milisegundos
	 *
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static String getFechaFormateada(String fecha) throws ParseException, ArrayIndexOutOfBoundsException {
		try {
			return FORMAT_FECHA_HORA.format(FORMAT_FECHA_HORA_MILISEGUNDOS.parse(fecha));
		} catch (NumberFormatException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}

	public static DateTime getUltimoDiaMesAnterior() {
		DateTime hoy = new DateTime().withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ESP)));

		// Set the Date in First of the next Month:
		DateTime ultimoDiaMesAnterior = new DateTime(hoy.getYear(), hoy.getMonthOfYear(), 1, 0, 0, 0);
		// Now take away one day and now you have the last day in the month
		// correctly
		return ultimoDiaMesAnterior.minusDays(1);
	}

	public static DateTime getDiaAnterior() {
		DateTime hoy = new DateTime().withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ESP)));
		return hoy.minusDays(1);

	}

	/**
	 * Recupera el cursor a partir del String
	 *
	 * @param strCursor
	 * @return
	 */
	public static Cursor getCursorDS(String strCursor) {
		Cursor cursor = null;
		// Recuperando cursor
		if (strCursor == null) {
			LOG.info("Recuperando la primera página... ");
		} else {
			cursor = Cursor.fromWebSafeString(strCursor);
		}
		return cursor;
	}

	/**
	 * Recupera el cursor como String
	 *
	 * @param cursor
	 * @return
	 */
	public static String getStrCursorDS(Cursor cursor) {
		String strCursor = null;
		// Recuperando cursor
		if (cursor == null) {
			LOG.info("No hay más páginas... ");
		} else {
			strCursor = cursor.toWebSafeString();
		}
		return strCursor;
	}

	/**
	 * Transforma una lista de ScoredDocuments en una lista de Long con su id Para
	 * recuperar los ids de los resultados de la búsqueda en TSearch
	 */
	public static Collection<String> toCollectionString(Collection<ScoredDocument> documents) {
		Collection<String> strings = new ArrayList<>();
		for (ScoredDocument document : documents) {
			strings.add(document != null ? document.getId() : null);
		}
		return strings;
	}

	/**
	 * Clona un map para poder usarlo sin cambiar el inicial
	 */
	public static Map getClonMap(Map map) {
		return (Map) ((HashMap) map).clone();
	}


	public static boolean isEmpty(String... s) {
		for (String str : s) {
			if (StringUtil.isEmptyOrWhitespace(str)) {
				return true;
			}
		}
		return false;
	}

	public static List<List<Object>> getPartitionList(List<Object> lst, int size) {
		return Lists.partition(lst, size);
	}

	/**
	 * Compone el id de la entidad Catálogo Remitente
	 */
	public static String getIDCatalogo(String definidor, String remitente) {
		return definidor + Constants.SEPARADOR_CLAVE_DS + remitente;
	}

	/**
	 * Compone el id de la entidad de mapeos de catálogos
	 */
	public static String getIDMapeo(String codRelacion, String definidorRemitenteDesde,
			String definidorRemitenteHasta) {
		return codRelacion + Constants.SEPARADOR_CLAVE_DS + definidorRemitenteDesde + Constants.SEPARADOR_CLAVE_DS
				+ definidorRemitenteHasta;
	}


	public static String join(Collection collection, String separator) {
		return collection == null ? null : join(collection.iterator(), separator);
	}

	public static String join(Iterator iterator, String separator) {
		String sepString = "\"";
		if (iterator == null) {
			return null;
		} else if (!iterator.hasNext()) {
			return "";
		} else {
			Object first = iterator.next();
			if (!iterator.hasNext()) {
				return first != null ? sepString + first.toString() + sepString : "";
			} else {
				StrBuilder buf = new StrBuilder(256);
				if (first != null) {
					buf.append(sepString);
					buf.append(first);
					buf.append(sepString);
				}
				while (iterator.hasNext()) {
					if (separator != null) {
						buf.append(separator);
					}
					Object obj = iterator.next();
					if (obj != null) {
						buf.append(sepString);
						buf.append(obj);
						buf.append(sepString);
					}
				}

				return buf.toString();
			}
		}
	}

	/**
	 * Comprueba si al programar la siguiente ejecución se superará o no la hora
	 * límite.
	 *
	 * @param horaLimite
	 *            La hora límite.
	 * @param minutosEntreComprobaciones
	 *            Los minutos para lanzar la siguiente tarea.
	 * @return true si, partiendo de la hora actual, dentro del número de minutos
	 *         definido se superará la hora límite; false en caso contrario.
	 */
	public static boolean superaHoraLimite(String horaLimite) {
		DateTime now = now(false);
		DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");
		DateTime dateTimeLimit = dtf.parseDateTime(horaLimite);
		dateTimeLimit = dateTimeLimit.withDate(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth())
				.withZoneRetainFields(now.getZone());

		now = now.withDate(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth())
				.withZoneRetainFields(now.getZone());

		// Si no se supera la hora límite o se está trabajando en el entorno de
		// desarrollo local se devuelve false.
		LOG.info("Hora límite == " + dateTimeLimit);
		if (now.isBefore(dateTimeLimit)) {
			LOG.info("No supera la hora límite");
			return false;
		} else {
			LOG.info("Supera la hora límite");
			return true;
		}
	}

	/**
	 * Devuelve la fecha actual, en UTC o en zona horaria de Madrid (España).
	 *
	 * @param utcTime
	 *            true si se quiere en UTC, false en Europe/Madrid.
	 * @return DateTime actual.
	 */
	public static DateTime now(boolean utcTime) {
		if (utcTime) {
			return DateTime.now().withZone(DateTimeZone.UTC);
		} else {
			return DateTime.now().withZone(DateTimeZone.forID(Constants.TZ_EUROPE_MADRID));
		}
	}

	
	
	/**
	 * Saltamos el primer caracter por si es el BOM
	 * @param in
	 * @throws IOException
	 */
	public static void skipBomSymbol(BufferedReader in) throws IOException {
		
        in.mark(1);
        if (in.read() != 0xFEFF) {
        	in.reset();
        }
	}
}
