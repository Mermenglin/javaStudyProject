import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Meimengling
 * @date 2021-7-5 15:47
 */
public class ExcelToJson {

    public static void main(String[] args) {
        try {
            FileInputStream inp = new FileInputStream("E:\\mml\\Github\\javaStudyProject\\poi-test\\src\\main\\resources\\净饮滤芯购买地址后台配置表2021-04-06.xlsx");
            Workbook workbook = WorkbookFactory.create(inp);
            //获取sheet数
//            int sheetNum = workbook.getNumberOfSheets();
//            for (int s = 0; s < sheetNum; s++) {
            // Get the Sheet of s.
            Sheet sheet = workbook.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            List<Entity> list = new ArrayList<>();
            for (int i = 1; i < rownum; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int colnum = row.getPhysicalNumberOfCells();
                    if (colnum > 3) {
                        Cell cell = row.getCell(0);
                        String sn8 = getCellValue(cell);
                        String key = "consumable.buy.url.0xED." + sn8;
                        Entity entity = new Entity();
                        entity.setConfKey(key);
                        List<Value> values = new ArrayList<>();
                        int k = 0;
                        Value value = new Value();
                        for (int j = 4; j < colnum; j++) {

                            int num = (j - 4) % 4;

                            switch (num) {
                                case 0:
                                    value.setName(row.getCell(j).getStringCellValue());
                                    break;
                                case 2:
                                    Cell cell6 = row.getCell(j);
                                    if (cell6 != null) {
                                        value.setMaxDay(getCellValue(cell6));
                                    }
                                    break;
                                // todo url 未添加进去
                                case 3:
                                    Cell cell7 = row.getCell(j);
                                    if (cell7 != null) {
                                        value.setUrl(getCellValue(cell7));
                                    }
                                    break;
                                default:
                                    break;
                            }

                            if (k != (j + 1 - 4) / 4) {
                                k = (j + 1 - 4) / 4;
                                if (value.getName() != null && !"".equals(value.getName())) {
                                    values.add(value);
                                }
                                value = new Value();
                            }
                        }
                        entity.setConfValue(JSONObject.toJSONString(values));
                        list.add(entity);
                    }
                }
            }
            System.out.println(JSONObject.toJSONString(list, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
