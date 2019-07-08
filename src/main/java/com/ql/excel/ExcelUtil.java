package com.ql.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    private static Map tagDicMap2019 = ImmutableMap.builder()
            .put("水果捞",6000).put("猪脚饭",6001).put("花甲粉",6002).put("墨西哥菜",6003)
            .put("潮汕牛肉火锅",6004).put("眼镜",6005).put("医疗器械",6006)
            .build();
    private static Map<String,BatchUpdateVo> tagVOMap = Maps.newHashMap();//key:主营品类id-辅营品类id

    //根据具体的cell格式确定是否非法、应当获取哪种值。
    private static String getStringCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue());
            case Cell.CELL_TYPE_BLANK:
                return "";
            default:
                throw new RuntimeException();
        }
    }

    /**
     * 解析excel
     * @return
     */
    private static List<BatchUpdateVo> analysisExcel() throws IOException {

        // 写文件到服务器
        File serverFile = new File("target/classes/1.xlsx");
        InputStream inputStream = new FileInputStream(serverFile);
        if (!serverFile.getName().endsWith("xlsx")) {
            return null;
        }
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.rowIterator();
        List<Long> failsWmPoiIds = Lists.newArrayList();
        List<Long> backWmPoiIds = Lists.newArrayList();
        int row = 1;
        while (iterator.hasNext()) {
            System.out.println("第"+row+"行");
            Row currentRow = null;
            if(row == 1){
                row ++;
                iterator.next();
                continue;
            }
            currentRow = iterator.next();
            String wmPoiIdStr = getStringCellValue(currentRow.getCell(0));
            Long wmPoiId = Long.valueOf(wmPoiIdStr.trim());
            backWmPoiIds.add(wmPoiId);
            String primaryTagIdName = getStringCellValue(currentRow.getCell(8)).trim();
            if(StringUtils.isBlank(primaryTagIdName)){
                primaryTagIdName = getStringCellValue(currentRow.getCell(7)).trim();
            }
            Integer primaryTagId = (Integer) tagDicMap2019.get(primaryTagIdName);
            String attachTagIdName = getStringCellValue(currentRow.getCell(9)).trim();
            Integer attachTagId = 0;
            if(StringUtils.isNotBlank(attachTagIdName)){
                attachTagId = (Integer) tagDicMap2019.get(attachTagIdName);
            }else{

//                    try {
//                        WmPoiAggre aggre = wmPoiQueryThriftService.getWmPoiAggreByWmPoiIdWithSpecificField(wmPoiId, AGGRE_FIELDS_CATEGORY_REWRITE);
//                        attachTagId = getAttachTag(aggre);
//                    } catch (WmServerException | TException e) {
//                        logger.error("",e);
//                        //记录失败的门店ID
//                        failsWmPoiIds.add(wmPoiId);
//                        continue;
//                    }
            }
            BatchUpdateVo batchUpdateVo = tagVOMap.get(primaryTagId+"-"+attachTagId);
            if(batchUpdateVo == null){
                BatchUpdateVo vo = new BatchUpdateVo();
                vo.setForceUpdate(false);
                vo.setPrimaryTagId(primaryTagId.longValue());
                vo.setSecondaryTagId(attachTagId == 0 ? null : attachTagId.longValue());
                vo.setWmPoiIds(Lists.newArrayList(wmPoiId));
                tagVOMap.put(primaryTagId+"-"+attachTagId,vo);
            }else{
                List<Long> wmPoiIds = batchUpdateVo.getWmPoiIds();
                wmPoiIds.add(wmPoiId);
                batchUpdateVo.setWmPoiIds(wmPoiIds);
            }
            row ++;
        }
        List<BatchUpdateVo> batchUpdateVoList = Lists.newArrayList(
                Iterables.transform(
                        tagVOMap.entrySet(), new Function<Map.Entry<String, BatchUpdateVo>, BatchUpdateVo>(){
                            @Override
                            public BatchUpdateVo apply(final Map.Entry<String, BatchUpdateVo> input){
                                return input.getValue();
                            }
                        })
        );
        logger.info("-----------------------------------------------------------");
        logger.info("batchUpdateVoList:{}",batchUpdateVoList);
        logger.info("failsWmPoiIds:{}",failsWmPoiIds);
        logger.info("backWmPoiIds:{}",backWmPoiIds);
        logger.info("-----------------------------------------------------------");
        System.out.println(batchUpdateVoList);
        return batchUpdateVoList;
    }

    public static void main(String[] args) throws IOException {
        analysisExcel();
    }



    public static class BatchUpdateVo {
        private List<Long> wmPoiIds;
        private Long primaryTagId;
        private Long secondaryTagId;
        private boolean forceUpdate;

        public List<Long> getWmPoiIds() {
            return wmPoiIds;
        }

        public void setWmPoiIds(List<Long> wmPoiIds) {
            this.wmPoiIds = wmPoiIds;
        }

        public Long getPrimaryTagId() {
            return primaryTagId;
        }

        public void setPrimaryTagId(Long primaryTagId) {
            this.primaryTagId = primaryTagId;
        }

        public Long getSecondaryTagId() {
            return secondaryTagId;
        }

        public void setSecondaryTagId(Long secondaryTagId) {
            this.secondaryTagId = secondaryTagId;
        }

        private boolean valid() {
            if (wmPoiIds == null || wmPoiIds.size() == 0) {
                logger.warn("warn! wmPoiIds can't be null");
                return false;
            }
            if (primaryTagId == null || primaryTagId <= 0) {
                logger.warn("warn! primaryTagId can't be null");
                return false;
            }
            return true;
        }

        private boolean isSecondaryTagIdValid() {
            if (secondaryTagId == null || secondaryTagId <= 0) {
                logger.info("info! secondaryTagId is null");
                return false;
            }
            if (secondaryTagId.equals(primaryTagId)) {
                logger.info("info! secondaryTagId equals primaryTagId");
                return false;
            }
            return true;
        }

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
