package hevs.utils

import java.text.{ParseException, SimpleDateFormat}
import java.util.Date


/**
 * @author Pierre-André Mudry, HES-SO Valais 
 * @version 1.0
 */
object DateUtils {
  private val msPerHour = 60 * 60 * 1000
  private val msPerDay = 24 * msPerHour

  /**
   * Creates a date from a text representation of this date
   *
   * @param s
   * The text representation, formatted as "dd/MM/yyyy"
   * @return The corresponding date
   */
  def createDate(s: String): Date = {
    val sdf = new SimpleDateFormat("dd/MM/yyyy")
    var theDate: Date = null
    try theDate = sdf.parse(s)
    catch {
      case ex: ParseException =>
        System.err.println("Invalid date format specified !")
        ex.printStackTrace()
    }
    theDate
  }

  private def nMsec(a: Date, b: Date) = if (b.after(a)) b.getTime - a.getTime
  else a.getTime - b.getTime

  /**
   * Computes the number of hours between two dates
   *
   * @param a
   * The first date
   * @param b
   * The second date
   * @return The number of hours between the two dates
   */
  def nHours(a: Date, b: Date): Int = {
    val n = nMsec(a, b) / msPerHour
    n.toInt
  }

  /**
   * Computes the number of day between two dates
   *
   * @param a
   * The first date
   * @param b
   * The second date
   * @return The number of days between the two dates
   */
  def nDays(a: Date, b: Date): Int = {
    val nDays = nMsec(a, b) / msPerDay
    nDays.toInt
  }

  // Some samples
  def main(args: Array[String]): Unit = {
    val first = DateUtils.createDate("1/1/2000")
    val second = DateUtils.createDate("1/1/2001")
    // It also takes into account leap years (2000)
    System.out.println("There were " + DateUtils.nDays(first, second) + " days in 2000")
    // How old are you in days ?
    val birthdate = DateUtils.createDate("12/10/1977")
    val now = new Date
    System.out.println("You are " + DateUtils.nDays(now, birthdate) + " days old")
  }
}
