package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.admin.KeywordService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-07 16:05
 *
 * @author EGGE
 */
@RestController
public class WxSearchController {
    @Autowired
    KeywordService keywordService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("wx/search/index")
    public BaseRespVo searchIndex() {
        List<Keyword> keywords = keywordService.selectKeyWordIsDefault();
        Keyword defaultKeyword = new Keyword();
        if (keywords != null) {
            defaultKeyword = keywords.get((int) (Math.random() * keywords.size()));
        } else {
            defaultKeyword.setKeyword("更多精彩，等你发现！");
        }
        List<SearchHistory> historyKeywordList = keywordService.getLastHistoryKeywords(5);
        List<Keyword> hotkeywords = keywordService.selectKeyWordIsHot();
        List<Keyword> selectHotKeywords = new LinkedList<>();
        if (hotkeywords.size() <= 5) {
            selectHotKeywords = hotkeywords;
        } else {
            int i = hotkeywords.size();
            Set<Integer> select = new TreeSet<>();
            while (select.size() < 5) {
                select.add((int) (Math.random() * i));
            }
            for (int s : select) {
                selectHotKeywords.add(hotkeywords.get(s));
            }
        }
        Map data = new HashMap();
        data.put("hotKeywordList", selectHotKeywords);
        data.put("historyKeywordList", historyKeywordList);
        data.put("defaultKeyword", defaultKeyword);
        return BaseRespVo.success(data);
    }

}
