import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import com.platform.util.SortInfo;

public class Ognl
{
  public static boolean isEmpty(Object o)
    throws IllegalArgumentException
  {
    if (o == null) {
      return true;
    }
    if ((o instanceof String))
    {
      if (((String)o).length() == 0) {
        return true;
      }
    }
    else if ((o instanceof Collection))
    {
      if (((Collection)o).isEmpty()) {
        return true;
      }
    }
    else if (o.getClass().isArray())
    {
      if (Array.getLength(o) == 0) {
        return true;
      }
    }
    else if ((o instanceof Map))
    {
      if (((Map)o).isEmpty()) {
        return true;
      }
    }
    else {
      return false;
    }
    return false;
  }
  
  public static boolean isNotEmpty(Object o)
  {
    return !isEmpty(o);
  }
  
  public static boolean isNotBlank(Object o)
  {
    return !isBlank(o);
  }
  
  public static boolean isNumber(Object o)
  {
    if (o == null) {
      return false;
    }
    if ((o instanceof Number)) {
      return true;
    }
    if ((o instanceof String))
    {
      String str = (String)o;
      if (str.length() == 0) {
        return false;
      }
      if (str.trim().length() == 0) {
        return false;
      }
      try
      {
        Double.parseDouble(str);
        return true;
      }
      catch (NumberFormatException e)
      {
        return false;
      }
    }
    return false;
  }
  
  public static boolean isBlank(Object o)
  {
    if (o == null) {
      return true;
    }
    if ((o instanceof String))
    {
      String str = (String)o;
      return isBlank(str);
    }
    return false;
  }
  
  public static boolean isBlank(String str)
  {
    if ((str == null) || (str.length() == 0)) {
      return true;
    }
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean checkOrderBy(String orderby, String validSortColumns)
    throws DataAccessException
  {
    if (isBlank(orderby)) {
      return false;
    }
    if ((orderby.indexOf("'") >= 0) || (orderby.indexOf("\\") >= 0)) {
      throw new IllegalArgumentException("orderBy:" + orderby + " has SQL Injection risk");
    }
    if ((orderby != null) && (orderby.length() > 50)) {
      throw new IllegalArgumentException("orderby.length() <= 50 must be true");
    }
    if (validSortColumns == null) {
      return true;
    }
    List<SortInfo> infos = SortInfo.parseSortColumns(orderby);
    String[] passColumns = validSortColumns.split(",");
    for (SortInfo info : infos)
    {
      String columnName = info.getColumnName();
      if (!isPass(passColumns, info, columnName)) {
        throw new InvalidDataAccessApiUsageException("orderby:[" + orderby + "] is invalid, only can orderby:" + validSortColumns);
      }
    }
    return true;
  }
  
  private static boolean isPass(String[] passColumns, SortInfo info, String columnName)
  {
    String[] arrayOfString = passColumns;int j = passColumns.length;
    for (int i = 0; i < j; i++)
    {
      String column = arrayOfString[i];
      if (column.equalsIgnoreCase(info.getColumnName())) {
        return true;
      }
    }
    return false;
  }
  
  public static String toDate(String str)
  {
    System.err.println("����toDate������");
    return "date_format(now(),'%Y-%c-%d %h:%i:%s')";
  }
}
